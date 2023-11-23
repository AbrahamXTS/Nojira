import {
	Avatar,
	Box,
	Card,
	CardBody,
	CardFooter,
	CardHeader,
	Center,
	Grid,
	GridItem,
	HStack,
	Heading,
	Image,
	LinkBox,
	LinkOverlay,
	Text,
	useColorMode,
} from "@chakra-ui/react";
import { Link } from "react-router-dom";

import { Project } from "@/interfaces";
import NewProjectImageURL from "@/assets/new-project.svg";

interface Props {
	projects: Project[];
}

export const ProjectsGrid = ({ projects }: Props) => {
	const { colorMode } = useColorMode();

	return (
		<Center mt={10}>
			{projects.length > 0 ? (
				<Grid gap={3} templateColumns="repeat(3, 1fr)">
					{projects.map(
						({
							description,
							projectName,
							projectId,
							owner: { ownerFullName },
						}) => (
							<LinkBox as={GridItem} key={projectId}>
								<Card
									bgColor={colorMode === "light" ? "" : "gray.800"}
									w="80"
								>
									<CardHeader>
										<HStack justifyContent="space-between">
											<Box>
												<Heading size="md">
													{projectName}
												</Heading>
												<Text fontSize={10} mt={1}>
													{projectId}
												</Text>
											</Box>
											<Avatar
												size="sm"
												src={`https://api.dicebear.com/7.x/icons/svg?seed=${projectName}`}
											/>
										</HStack>
									</CardHeader>
									<CardBody py={1}>
										<LinkOverlay
											as={Link}
											to={`/projects/${projectId}`}
										>
											<Text>{description}</Text>
										</LinkOverlay>
									</CardBody>
									<CardFooter flexDir="column" gap={1}>
										<Text fontSize="sm" fontWeight="semibold">
											Dueño
										</Text>
										<Text fontSize="sm">{ownerFullName}</Text>
									</CardFooter>
								</Card>
							</LinkBox>
						),
					)}
				</Grid>
			) : (
				<HStack spacing={5}>
					<Image src={NewProjectImageURL} h="80" />
					<Text fontSize="xl" fontWeight="bold">
						¡Hey! ¿Qué tal si empezamos <br /> creando un nuevo proyecto?
					</Text>
				</HStack>
			)}
		</Center>
	);
};
