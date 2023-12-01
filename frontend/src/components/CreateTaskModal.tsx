import {
	Button,
	FormControl,
	FormLabel,
	Input,
	Modal,
	ModalBody,
	ModalCloseButton,
	ModalContent,
	ModalFooter,
	ModalHeader,
	ModalOverlay,
	Textarea,
	VStack,
} from "@chakra-ui/react";
import { useState } from "react";

interface Props {
	createTask: (title: string, description: string) => boolean;
	isLoading: boolean;
	isOpen: boolean;
	onClose: () => void;
}

export const CreateTaskModal = ({ createTask, isLoading, isOpen, onClose }: Props) => {
	const [taskData, setTaskData] = useState({
		title: "",
		description: "",
	});

	return (
		<Modal
			closeOnEsc={!isLoading}
			closeOnOverlayClick={!isLoading}
			isCentered
			isOpen={isOpen}
			onClose={onClose}
			size="lg"
		>
			<ModalOverlay />
			<ModalContent>
				<ModalHeader>Crear una nueva tarea</ModalHeader>
				<ModalCloseButton isDisabled={isLoading} />
				<ModalBody>
					<VStack
						as="form"
						id="create-task"
						onSubmit={(e) => {
							e.preventDefault();

							const isSuccess = createTask(taskData.title, taskData.description);

							if (isSuccess) {
								setTaskData({
									title: "",
									description: "",
								});
							}
						}}
						spacing={5}
					>
						<FormControl isRequired>
							<FormLabel>Titulo de la tarea: </FormLabel>
							<Input
								onChange={(e) =>
									setTaskData((prev) => ({
										...prev,
										title: e.target.value,
									}))
								}
								placeholder="Tarea de ejemplo"
								type="text"
								value={taskData.title}
							/>
						</FormControl>

						<FormControl isRequired>
							<FormLabel>Descripción de la tarea</FormLabel>
							<Textarea
								onChange={(e) =>
									setTaskData((prev) => ({
										...prev,
										description: e.target.value,
									}))
								}
								placeholder="Descripción de la tarea de ejemplo"
								resize="none"
								value={taskData.description}
							/>
						</FormControl>
					</VStack>
				</ModalBody>

				<ModalFooter>
					<Button
						_hover={{
							bgColor: "#661CA6",
						}}
						bgColor="#7e22ce"
						color="#ffffff"
						form="create-task"
						isLoading={isLoading}
						type="submit"
					>
						Crear tarea
					</Button>
				</ModalFooter>
			</ModalContent>
		</Modal>
	);
};
