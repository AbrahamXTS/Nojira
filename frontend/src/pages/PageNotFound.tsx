import { Link } from "react-router-dom";
import { Button, Heading, Image, Text, VStack } from "@chakra-ui/react";

import NotFoundImageURL from "@/assets/404.svg";

export const PageNotFound = () => {
	return (
		<VStack h="100vh" justifyContent="center" spacing={6}>
			<Image src={NotFoundImageURL} w={80} />
			<VStack spacing={4} p={3}>
				<Heading textAlign="center">PÁGINA NO ENCONTRADA</Heading>
				<Text textAlign="center">
					Buscamos esta página por todas partes, pero no la encontramos.
				</Text>
				<Text>¿Estás seguro de que la URL es correcta?</Text>I
				<Button
					_hover={{
						bgColor: "#661CA6",
					}}
					as={Link}
					bgColor="#7e22ce"
					color="#ffffff"
					mt={2}
					to="/projects"
				>
					Ir al inicio
				</Button>
			</VStack>
		</VStack>
	);
};
