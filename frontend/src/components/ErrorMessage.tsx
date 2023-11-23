import {
	Alert,
	AlertDescription,
	AlertIcon,
	AlertTitle,
	Button,
	Center,
	Text,
	VStack,
} from "@chakra-ui/react";

interface Props {
	retryFunction?: () => void;
}

export function ErrorMessage({ retryFunction }: Readonly<Props>) {
	return (
		<Center h="calc(100vh - 64px)">
			<Alert
				alignItems="center"
				flexDirection="column"
				justifyContent="center"
				status="error"
				textAlign="center"
				variant="subtle"
			>
				<AlertIcon boxSize="40px" mt={3} />
				<AlertTitle mt={5} mb={1} fontSize="lg">
					¡Oh no! :(
				</AlertTitle>
				<AlertDescription maxWidth="sm" my={3}>
					<Text>
						Algo salió mal mientras procesabamos tu petición. Nuestro
						equipo ya se encuentra trabajando en ello.
					</Text>
					{retryFunction ? (
						<VStack mt={5}>
							<Text fontWeight="bold">
								¿Quisieras intentarlo de nuevo?
							</Text>
							<Button colorScheme="red" onClick={retryFunction}>
								Reintentar
							</Button>
						</VStack>
					) : null}
				</AlertDescription>
			</Alert>
		</Center>
	);
}
