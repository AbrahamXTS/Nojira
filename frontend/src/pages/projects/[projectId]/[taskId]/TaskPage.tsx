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
	useEditTaskAssignmentService,
	useEditTaskDescriptionService,
	useEditTaskStatusService,
	useEditTaskTimesService,
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

	const editTaskAssignmentService = useEditTaskAssignmentService();
	const editTaskDescriptionService = useEditTaskDescriptionService();
	const editTaskStatusService = useEditTaskStatusService();
	const editTaskTimesService = useEditTaskTimesService();
	const deleteTaskService = useDeleteTaskService();
	const getTaskService = useGetTaskService();
	const getParticipantsFromProjectService = useGetParticipantsFromProjectService();

	const [task, setTask] = useState<Task>({} as Task);
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

					navigate(`/projects/${projectId}`);
				},
			},
		);
	};

	const editTask = () => {
		if (!projectId) {
			return navigate("/projects");
		}

		let success = true;

		editTaskDescriptionService.mutate(
			{
				description: task.description,
				taskId: task.taskId,
				title: task.title,
				projectId,
			},
			{
				onError: () => (success = false),
			},
		);

		editTaskStatusService.mutate(
			{
				statusId: STATUS_CATALOG[task.status as keyof typeof STATUS_CATALOG],
				taskId: task.taskId,
				projectId,
			},
			{
				onError: () => (success = false),
			},
		);

		editTaskTimesService.mutate(
			{
				estimated: task.times.estimated,
				used: task.times.used,
				taskId: task.taskId,
				projectId,
			},
			{
				onError: () => (success = false),
			},
		);

		editTaskAssignmentService.mutate(
			{
				newOwnerId: task.asignedTo.userId,
				projectId: projectId,
				taskId: task.taskId,
			},
			{
				onError: () => (success = false),
			},
		);

		return success;
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
						w="max-content"
					>
						{task.title}
					</Heading>

					<Flex justifyContent="flex-end" mt={3}>
						<ButtonGroup>
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
							<HStack spacing={3} w="full">
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
											asignedTo: {
												...prev.asignedTo,
												userId: e.target.value,
											},
										}))
									}
									value={task.asignedTo.userId}
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
						isLoading={
							editTaskAssignmentService.isPending ||
							editTaskDescriptionService.isPending ||
							editTaskStatusService.isPending ||
							editTaskTimesService.isPending
						}
						isOpen={editTaskAlertDisclosure.isOpen}
						message={`¿Deseas guardar los cambios realizados sobre esta tarea?`}
						title="¿Guardar cambios?"
						onClose={editTaskAlertDisclosure.onClose}
						onConfirm={() => {
							if (editTask()) {
								editTaskAlertDisclosure.onClose();

								toast({
									title: "¡Cambios realizados correctamente!",
									description:
										"Hemos guardado tus cambios correctamente",
									status: "success",
								});
							}
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
