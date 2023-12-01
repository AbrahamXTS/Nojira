import {
	Box,
	Button,
	Center,
	Flex,
	Heading,
	useDisclosure,
	useToast,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { IconPlus } from "@tabler/icons-react";

import { CreateProjectRequest, Project } from "@/interfaces";
import { useCreateProjectService, useGetProjectsService } from "@/services";
import { CreateProjectModal, ErrorMessage, ProjectsGrid, Spinner } from "@/components";

export const ProjectsPage = () => {
	const toast = useToast();
	const createProjectModalDisclosure = useDisclosure();

	const getProjectsService = useGetProjectsService();
	const createProjectService = useCreateProjectService();

	const [projects, setProjects] = useState<Project[]>([]);

	const fetchProjects = () => {
		getProjectsService.mutate(undefined, {
			onSuccess: ({ data: { body } }) => {
				setProjects(body);
			},
		});
	};

	const createProject = (project: CreateProjectRequest) => {
		let isSuccess = false;

		createProjectService.mutate(project, {
			onSuccess: ({ data: { body } }) => {
				setProjects((prev) => [...prev, body]);
				createProjectModalDisclosure.onClose();

				isSuccess = true;
			},
			onError: () => {
				toast({
					status: "error",
					title: "Oh no! :(",
					description:
						"Algo salió mal mientras creabamos el proyecto solicitado. Por favor, intentalo más tarde.",
				});
			},
		});

		return isSuccess;
	};

	useEffect(() => {
		fetchProjects();
	}, []);

	return (
		<>
			{getProjectsService.isPending ? (
				<Center h="calc(100vh - 64px)">
					<Spinner />
				</Center>
			) : getProjectsService.isSuccess ? (
				<Box my={20} w="full">
					<Heading
						borderBottom="5px solid #7e22ce"
						mx="auto"
						pb="1px"
						size="lg"
						w="max-content"
					>
						Proyectos
					</Heading>

					<Flex justifyContent="flex-end" mt={3}>
						<Button
							_hover={{
								bgColor: "#661CA6",
							}}
							bgColor="#7e22ce"
							color="#ffffff"
							leftIcon={<IconPlus />}
							mt={5}
							onClick={createProjectModalDisclosure.onOpen}
							w={["full", "auto"]}
						>
							Crear nuevo proyecto
						</Button>
					</Flex>

					<ProjectsGrid projects={projects} />

					<CreateProjectModal
						createProject={createProject}
						isLoading={createProjectService.isPending}
						isOpen={createProjectModalDisclosure.isOpen}
						onClose={createProjectModalDisclosure.onClose}
					/>
				</Box>
			) : (
				<ErrorMessage
					retryFunction={() => {
						getProjectsService.reset();
						fetchProjects();
					}}
				/>
			)}
		</>
	);
};
