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
	createProject: (project: CreateProjectRequest) => void;
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
		description: "",
		projectName: "",
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
					<VStack spacing={5}>
						<FormControl>
							<FormLabel>Nombre del proyecto: </FormLabel>
							<Input
								onChange={(e) =>
									setProjectData((prev) => ({
										...prev,
										projectName: e.target.value,
									}))
								}
								placeholder="Proyecto de ejemplo"
								type="text"
								value={projectData.projectName}
							/>
						</FormControl>

						<FormControl>
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
						isLoading={isLoading}
						onClick={() => {
							createProject(projectData);

							setProjectData({
								description: "",
								projectName: "",
							});
						}}
					>
						Crear proyecto
					</Button>
				</ModalFooter>
			</ModalContent>
		</Modal>
	);
};
