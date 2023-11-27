import {
	Box,
	Button,
	ButtonGroup,
	Center,
	Flex,
	FormControl,
	FormLabel,
	HStack,
	Heading,
	Input,
	InputGroup,
	InputRightAddon,
	Select,
	Textarea,
	VStack,
	useDisclosure,
	useToast,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { IconCheck, IconEdit, IconTrash } from "@tabler/icons-react";

import {
	useDeleteTaskService,
	useEditTaskService,
	useGetParticipantsFromProjectService,
	useGetTaskService,
} from "@/services";
import { Participant, STATUS_CATALOG, Task } from "@/interfaces";
import { ConfirmDialog, ErrorMessage, Spinner } from "@/components";

export const TaskPage = () => {
	const toast = useToast();
	const navigate = useNavigate();
	const { projectId, taskId } = useParams();
	const editTaskAlertDisclosure = useDisclosure();
	const deleteTaskAlertDisclosure = useDisclosure();

	const editTaskService = useEditTaskService();
	const deleteTaskService = useDeleteTaskService();
	const getTaskService = useGetTaskService();
	const getParticipantsFromProjectService = useGetParticipantsFromProjectService();

	const [task, setTask] = useState<Task>({} as Task);
	const [prevEditTaskInfo, setPrevEditTaskInfo] = useState({} as Task);
	const [participants, setParticipants] = useState<Participant[]>([]);
	const [isOnEditMode, setIsOnEditMode] = useState(false);

	const fetchTask = () => {
		if (!projectId || !taskId) {
			return navigate("/projects");
		}

		getTaskService.mutate(
			{ projectId, taskId },
			{
				onSuccess: ({ data: { body } }) => {
					setTask(body);
					setPrevEditTaskInfo(body);
				},
			},
		);
	};

	const fetchParticipantsFromProject = () => {
		if (!projectId) {
			return navigate("/projects");
		}

		getParticipantsFromProjectService.mutate(projectId, {
			onSuccess: ({ data: { body } }) => {
				setParticipants(body);
			},
		});
	};

	const deleteTask = () => {
		if (!projectId || !taskId) {
			return navigate("/projects");
		}

		deleteTaskService.mutate(
			{ projectId, taskId },
			{
				onSuccess: () => {
					toast({
						title: "Tarea eliminada correctamente",
						description:
							"Se ha eliminado correctamente la tarea solicitada.",
						status: "success",
					});

					navigate(`/projects/${projectId}`, { replace: true });
				},
			},
		);
	};

	const editTask = () => {
		if (!projectId) {
			return navigate("/projects");
		}

		editTaskService.mutate({
			...task,
			projectId,
		});
	};

	useEffect(() => {
		fetchTask();
		fetchParticipantsFromProject();
	}, [projectId, taskId]);

	return (
		<>
			{getTaskService.isPending ? (
				<Center h="calc(100vh - 64px)">
					<Spinner />
				</Center>
			) : getTaskService.isSuccess ? (
				<Box my={20} w="full">
					<Heading
						borderBottom="5px solid #7e22ce"
						mx="auto"
						pb="1px"
						size="lg"
						textAlign="center"
						w={["full", "max-content"]}
					>
						{task.title}
					</Heading>

					<Flex justifyContent={["center", "flex-end"]} mt={3}>
						<ButtonGroup mt={5}>
							<Button
								_hover={{
									bgColor: "#661CA6",
								}}
								bgColor="#7e22ce"
								color="#ffffff"
								leftIcon={<IconTrash />}
								onClick={deleteTaskAlertDisclosure.onOpen}
							>
								Eliminar tarea
							</Button>
							<Button
								colorScheme="gray"
								leftIcon={isOnEditMode ? <IconCheck /> : <IconEdit />}
								onClick={() => {
									if (isOnEditMode) {
										editTaskAlertDisclosure.onOpen();
									}

									return setIsOnEditMode((prev) => !prev);
								}}
							>
								{isOnEditMode ? "Guardar cambios" : "Editar tarea"}
							</Button>
						</ButtonGroup>
					</Flex>

					<Center mt={10}>
						<VStack as="form" spacing={5} w="full">
							<HStack flexDir={["column", "row"]} spacing={3} w="full">
								<FormControl>
									<FormLabel>Nombre de la tarea:</FormLabel>
									<Input
										isDisabled={!isOnEditMode}
										onChange={(e) =>
											setTask((prev) => ({
												...prev,
												title: e.target.value,
											}))
										}
										type="text"
										value={task.title}
									/>
								</FormControl>
								<FormControl>
									<FormLabel>Estatus:</FormLabel>
									<Select
										isDisabled={!isOnEditMode}
										onChange={(e) =>
											setTask((prev) => ({
												...prev,
												status: e.target.value,
											}))
										}
										value={task.status}
									>
										{Object.keys(STATUS_CATALOG).map((status) => (
											<option key={status} value={status}>
												{status}
											</option>
										))}
									</Select>
								</FormControl>
							</HStack>
							<FormControl>
								<FormLabel>Descripción de la tarea:</FormLabel>
								<Textarea
									isDisabled={!isOnEditMode}
									onChange={(e) =>
										setTask((prev) => ({
											...prev,
											description: e.target.value,
										}))
									}
									resize="none"
									value={task.description}
								/>
							</FormControl>
							<FormControl>
								<FormLabel>Asignada a:</FormLabel>
								<Select
									isDisabled={!isOnEditMode}
									onChange={(e) =>
										setTask((prev) => ({
											...prev,
											assignedTo: {
												...prev.assignedTo,
												ownerId: e.target.value,
											},
										}))
									}
									value={task.assignedTo.ownerId}
								>
									{participants.map((participant) => (
										<option
											key={participant.userId}
											value={participant.userId}
										>
											{participant.fullName}
										</option>
									))}
								</Select>
							</FormControl>
							<HStack spacing={3} w="full">
								<FormControl>
									<FormLabel>Tiempo estimado:</FormLabel>
									<InputGroup>
										<Input
											isDisabled={!isOnEditMode}
											onChange={(e) =>
												setTask((prev) => ({
													...prev,
													times: {
														...prev.times,
														estimated:
															e.target.valueAsNumber,
													},
												}))
											}
											type="number"
											value={task.times.estimated}
										/>
										<InputRightAddon>minutos</InputRightAddon>
									</InputGroup>
								</FormControl>
								<FormControl>
									<FormLabel>Tiempo utilizado:</FormLabel>
									<InputGroup>
										<Input
											isDisabled={!isOnEditMode}
											onChange={(e) =>
												setTask((prev) => ({
													...prev,
													times: {
														...prev.times,
														used: e.target.valueAsNumber,
													},
												}))
											}
											type="number"
											value={task.times.used}
										/>
										<InputRightAddon>minutos</InputRightAddon>
									</InputGroup>
								</FormControl>
							</HStack>
						</VStack>
					</Center>

					<ConfirmDialog
						confirmMessage="Sí, editar"
						isLoading={editTaskService.isPending}
						isOpen={editTaskAlertDisclosure.isOpen}
						message={`¿Deseas guardar los cambios realizados sobre esta tarea?`}
						title="¿Guardar cambios?"
						onClose={() => {
							setTask(prevEditTaskInfo);
							editTaskAlertDisclosure.onClose();
						}}
						onConfirm={() => {
							editTask();
							editTaskAlertDisclosure.onClose();

							toast({
								title: "¡Cambios realizados correctamente!",
								description:
									"Hemos guardado tus cambios correctamente",
								status: "success",
							});
						}}
					/>

					<ConfirmDialog
						confirmMessage="Sí, eliminar"
						isLoading={deleteTaskService.isPending}
						isOpen={deleteTaskAlertDisclosure.isOpen}
						message={`Estás a punto de eliminar la tarea "${task.title}"`}
						onClose={deleteTaskAlertDisclosure.onClose}
						onConfirm={deleteTask}
					/>
				</Box>
			) : (
				<ErrorMessage
					retryFunction={() => {
						getTaskService.reset();
						fetchTask();
					}}
				/>
			)}
		</>
	);
};
