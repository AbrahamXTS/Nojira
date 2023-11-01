import { Link } from "react-router-dom";
import { IconPlus } from "@tabler/icons-react";
import {
	Box,
	Button,
	Center,
	Flex,
	HStack,
	Heading,
	Image,
	Text,
} from "@chakra-ui/react";

import NewProjectImageURL from "@/assets/NewProject.svg";

export const Projects = () => {
	return (
		<Box mt={20} w="full">
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
						as={Link}
						_hover={{
							bgColor: "#661CA6",
						}}
						bgColor="#7e22ce"
						color="#ffffff"
						leftIcon={<IconPlus />}
						to="/projects/new"
					>
						Crear nuevo proyecto
					</Button>
				</Flex>

				<Center mt={10}>
					<HStack spacing={5}>
						<Image src={NewProjectImageURL} h="80" />
						<Text fontSize="xl" fontWeight="bold">
							¡Hey! ¿Qué tal si empezamos <br /> creando un nuevo
							proyecto?
						</Text>
					</HStack>
				</Center>
			</Box>
		</Box>
	);
};
