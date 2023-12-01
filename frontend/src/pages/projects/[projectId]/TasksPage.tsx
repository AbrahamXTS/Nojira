import {
	Box,
	Button,
	ButtonGroup,
	Center,
	Flex,
	Heading,
	IconButton,
	useDisclosure,
	useToast,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { IconEye, IconPlus } from "@tabler/icons-react";
import { useNavigate, useParams } from "react-router-dom";

import {
	CreateTaskModal,
	ErrorMessage,
	KanbanBoard,
	Spinner,
	Table,
} from "@/components";
import { Task } from "@/interfaces";
import { useCreateTaskService, useGetTasksService } from "@/services";

import { tasksTableColumns } from "./TasksTableColumns";

export const TasksPage = () => {
	const toast = useToast();
	const navigate = useNavigate();
	const { projectId } = useParams();
	const createTaskModalDisclosure = useDisclosure();

	const getTasksService = useGetTasksService();
	const createTaskService = useCreateTaskService();

	const [tasks, setTasks] = useState<Task[]>([]);
	const [projectName, setProjectName] = useState("");
	const [isTableView, setIsTableView] = useState(false);

	const fetchTasks = () => {
		if (!projectId) {
			return navigate("/projects");
		}

		getTasksService.mutate(projectId, {
			onSuccess: ({ data }) => {
				setProjectName(data.body.projectName);
				setTasks(data.body.tasks);
			},
		});
	};

	const createTask = (title: string, description: string) => {
		let isSuccess = false;

		createTaskService.mutate(
			{
				title,
				description,
				projectId: projectId ?? "",
			},
			{
				onSuccess: ({ data: { body } }) => {
					setTasks((prev) => [body, ...prev]);
					createTaskModalDisclosure.onClose();

					isSuccess = true;
				},
				onError: () => {
					toast({
						status: "error",
						title: "Oh no! :(",
						description:
							"Algo salió mal mientras creabamos la tarea solicitada. Por favor, intentalo más tarde.",
					});
				},
			},
		);

		return isSuccess;
	};

	useEffect(() => {
		fetchTasks();
	}, [projectId]);

	return (
		<>
			{getTasksService.isPending ? (
				<Center h="calc(100vh - 64px)">
					<Spinner />
				</Center>
			) : getTasksService.isSuccess ? (
				<Box my={20} w="full">
					<Heading
						borderBottom="5px solid #7e22ce"
						mx="auto"
						pb="1px"
						size="lg"
						textAlign="center"
						w={["full", "max-content"]}
					>
						{projectName}
					</Heading>

					<Flex justifyContent="flex-end" mt={3}>
						<ButtonGroup mt={5} w={["full", "auto"]}>
							<Button
								_hover={{
									bgColor: "#661CA6",
								}}
								bgColor="#7e22ce"
								color="#ffffff"
								leftIcon={<IconPlus />}
								onClick={createTaskModalDisclosure.onOpen}
								w="full"
							>
								Crear nueva tarea
							</Button>
							<IconButton
								aria-label="Change view"
								icon={<IconEye />}
								onClick={() => setIsTableView((actual) => !actual)}
							/>
						</ButtonGroup>
					</Flex>

					<Center mt={10}>
						{isTableView ? (
							<Table columns={tasksTableColumns} data={tasks} />
						) : (
							<KanbanBoard data={tasks} />
						)}
					</Center>

					<CreateTaskModal
						createTask={createTask}
						isLoading={createTaskService.isPending}
						isOpen={createTaskModalDisclosure.isOpen}
						onClose={createTaskModalDisclosure.onClose}
					/>
				</Box>
			) : (
				<ErrorMessage
					retryFunction={() => {
						getTasksService.reset();
						fetchTasks();
					}}
				/>
			)}
		</>
	);
};
