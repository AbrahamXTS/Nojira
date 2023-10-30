import {
	Card,
	CardBody,
	Center,
	FormControl,
	FormLabel,
	HStack,
	Heading,
	Input,
	InputGroup,
	InputLeftElement,
	Link as ChakraLink,
	VStack,
	Text,
	Button,
} from "@chakra-ui/react";
import { Link } from "react-router-dom";
import { IconBrandBunpo, IconLock, IconMail, IconUser } from "@tabler/icons-react";

export const SignupPage = () => {
	return (
		<Center h="100vh">
			<Card w="lg">
				<CardBody p={12}>
					<VStack>
						<HStack>
							<IconBrandBunpo color="#7e22ce" size={100} />
							<Heading as="h1" color="#7e22ce" size="3xl">
								Nojira
							</Heading>
						</HStack>
						<Heading color="#9ca3af" size="lg">
							Crea tu cuenta
						</Heading>
					</VStack>

					<VStack mt={10} spacing={5}>
						<FormControl>
							<FormLabel>Nombre completo</FormLabel>
							<InputGroup>
								<InputLeftElement pointerEvents="none">
									<IconUser color="#6B7280" size={20} />
								</InputLeftElement>
								<Input
									placeholder="John Doe"
									type="email"
									variant="filled"
								/>
							</InputGroup>
						</FormControl>

						<FormControl>
							<FormLabel>Correo electrónico</FormLabel>
							<InputGroup>
								<InputLeftElement pointerEvents="none">
									<IconMail color="#6B7280" size={20} />
								</InputLeftElement>
								<Input
									placeholder="hello@example.com"
									type="email"
									variant="filled"
								/>
							</InputGroup>
						</FormControl>

						<FormControl>
							<FormLabel>Contraseña</FormLabel>
							<InputGroup>
								<InputLeftElement pointerEvents="none">
									<IconLock color="#6B7280" size={20} />
								</InputLeftElement>
								<Input
									placeholder="**********"
									type="password"
									variant="filled"
								/>
							</InputGroup>
						</FormControl>

						<FormControl>
							<FormLabel>Confirma tu contraseña</FormLabel>
							<InputGroup>
								<InputLeftElement pointerEvents="none">
									<IconLock color="#6B7280" size={20} />
								</InputLeftElement>
								<Input
									placeholder="**********"
									type="password"
									variant="filled"
								/>
							</InputGroup>
						</FormControl>
					</VStack>

					<Center mt={7}>
						<Button
							_hover={{
								bgColor: "#661CA6",
							}}
							color="#ffffff"
							bgColor="#7e22ce"
						>
							Registrate
						</Button>
					</Center>

					<VStack fontSize="xs" mt={5} pl={4}>
						<Text>
							¿Ya tienes tu cuenta?
							<ChakraLink as={Link} to="/auth/login">
								{" "}
								Inicia sesión
							</ChakraLink>
						</Text>
					</VStack>
				</CardBody>
			</Card>
		</Center>
	);
};
