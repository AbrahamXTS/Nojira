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

import { CreateProjectRequest } from "@/interfaces";

interface Props {
	createProject: (project: CreateProjectRequest) => boolean;
	isLoading: boolean;
	isOpen: boolean;
	onClose: () => void;
}

export const CreateProjectModal = ({
	createProject,
	isLoading,
	isOpen,
	onClose,
}: Props) => {
	const [projectData, setProjectData] = useState<CreateProjectRequest>({
		title: "",
		description: "",
	});

	return (
		<Modal
			closeOnOverlayClick={isLoading}
			isCentered
			isOpen={isOpen}
			onClose={onClose}
			size="lg"
		>
			<ModalOverlay />
			<ModalContent>
				<ModalHeader>Crear un nuevo proyecto</ModalHeader>
				<ModalCloseButton />
				<ModalBody>
					<VStack
						as="form"
						id="create-project"
						onSubmit={(e) => {
							e.preventDefault();

							if (createProject(projectData)) {
								setProjectData({
									title: "",
									description: "",
								});
							}
						}}
						spacing={5}
					>
						<FormControl isRequired>
							<FormLabel>Nombre del proyecto: </FormLabel>
							<Input
								onChange={(e) =>
									setProjectData((prev) => ({
										...prev,
										title: e.target.value,
									}))
								}
								placeholder="Proyecto de ejemplo"
								type="text"
								value={projectData.title}
							/>
						</FormControl>

						<FormControl isRequired>
							<FormLabel>Descripción del proyecto</FormLabel>
							<Textarea
								onChange={(e) =>
									setProjectData((prev) => ({
										...prev,
										description: e.target.value,
									}))
								}
								placeholder="Descripción del proyecto de ejemplo"
								resize="none"
								value={projectData.description}
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
						form="create-project"
						isLoading={isLoading}
						type="submit"
					>
						Crear proyecto
					</Button>
				</ModalFooter>
			</ModalContent>
		</Modal>
	);
};
