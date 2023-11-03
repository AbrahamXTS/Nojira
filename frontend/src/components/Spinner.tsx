import { Spinner as SpinnerChakra } from "@chakra-ui/react";

export const Spinner = () => {
	return (
		<SpinnerChakra
			color="#7e22ce"
			emptyColor="gray.200"
			size="xl"
			speed="0.65s"
			thickness="4px"
		/>
	);
};
