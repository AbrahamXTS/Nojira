import { useEffect, useState } from "react";
import { IconPlus } from "@tabler/icons-react";
import { Box, Button, Flex, Heading, useDisclosure, useToast } from "@chakra-ui/react";

import { CreateProjectRequest, Project } from "@/interfaces";
import { CreateProjectModal, ProjectsGrid } from "@/components";
import { useCreateProjectService, useGetProjectsService } from "@/services";

export const Projects = () => {
	const toast = useToast();
	const getProjectsService = useGetProjectsService();
	const createProjectService = useCreateProjectService();
	const createProjectModalDisclosure = useDisclosure();

	const [projects, setProjects] = useState<Project[]>([]);

	useEffect(() => {
		getProjectsService.mutate(undefined, {
			onSuccess: ({ data: { body } }) => {
				setProjects(body);
			},
			onError: () => {
				toast({
					status: "error",
					title: "Oh no! :(",
					description:
						"Algo salió mal mientras obteniamos todas las tareas. Por favor, intentalo más tarde.",
				});
			},
		});
	}, []);

	const createProject = (project: CreateProjectRequest) => {
		createProjectService.mutate(project, {
			onSuccess: ({ data: { body } }) => {
				setProjects((prev) => [...prev, body]);
				createProjectModalDisclosure.onClose();
			},
			onError: () => {
				toast({
					status: "error",
					title: "Oh no! :(",
					description:
						"Algo salió mal mientras creabamos la tarea. Por favor, intentalo más tarde.",
				});
			},
		});
	};

	return (
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

			<Box mt={3}>
				<Flex justifyContent="flex-end">
					<Button
						_hover={{
							bgColor: "#661CA6",
						}}
						bgColor="#7e22ce"
						color="#ffffff"
						leftIcon={<IconPlus />}
						onClick={createProjectModalDisclosure.onOpen}
					>
						Crear nuevo proyecto
					</Button>
				</Flex>
			</Box>

			<ProjectsGrid
				isLoading={getProjectsService.isPending}
				projects={projects}
			/>

			<CreateProjectModal
				createProject={createProject}
				isLoading={createProjectService.isPending}
				isOpen={createProjectModalDisclosure.isOpen}
				onClose={createProjectModalDisclosure.onClose}
			/>
		</Box>
	);
};
