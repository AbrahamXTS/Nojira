import {
	AlertDialog,
	AlertDialogBody,
	AlertDialogContent,
	AlertDialogFooter,
	AlertDialogHeader,
	AlertDialogOverlay,
	Button,
} from "@chakra-ui/react";
import { useRef } from "react";

interface Props {
	confirmMessage: string;
	isLoading?: boolean;
	isOpen: boolean;
	message: string;
	onClose: () => void;
	onConfirm: () => void;
	title?: string;
}

export const ConfirmDialog = ({
	confirmMessage,
	isLoading = false,
	isOpen,
	message,
	onClose,
	onConfirm,
	title = "¿Realmente deseas realizar esto?",
}: Props) => {
	const cancelRef = useRef(null);

	return (
		<AlertDialog
			closeOnOverlayClick={false}
			closeOnEsc={false}
			isCentered
			isOpen={isOpen}
			leastDestructiveRef={cancelRef}
			onClose={onClose}
		>
			<AlertDialogOverlay>
				<AlertDialogContent>
					<AlertDialogHeader fontSize="lg" fontWeight="bold">
						{title}
					</AlertDialogHeader>

					<AlertDialogBody>{message}</AlertDialogBody>

					<AlertDialogFooter>
						<Button
							colorScheme="red"
							isLoading={isLoading}
							onClick={onClose}
							ref={cancelRef}
							variant="solid"
						>
							Cancelar
						</Button>
						<Button
							_hover={{
								bgColor: "#661CA6",
							}}
							bgColor="#7e22ce"
							color="#ffffff"
							isLoading={isLoading}
							onClick={onConfirm}
							ml={3}
						>
							{confirmMessage}
						</Button>
					</AlertDialogFooter>
				</AlertDialogContent>
			</AlertDialogOverlay>
		</AlertDialog>
	);
};
