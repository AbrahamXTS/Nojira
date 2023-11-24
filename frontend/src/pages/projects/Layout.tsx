import {
	Avatar,
	Box,
	Container,
	Flex,
	IconButton,
	Menu,
	MenuButton,
	MenuItem,
	MenuList,
	Text,
	useColorMode,
} from "@chakra-ui/react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { IconBrandBunpo, IconLogout, IconMoon, IconSun } from "@tabler/icons-react";

import { deleteAuthUser, getAuthUser } from "@/utils";

export const Layout = () => {
	const authUser = getAuthUser();
	const navigate = useNavigate();
	const { colorMode, toggleColorMode } = useColorMode();

	return (
		<Box
			_dark={{
				bg: "gray.700",
			}}
			as="section"
			bg="gray.50"
			minH="100vh"
		>
			<Box transition=".3s ease">
				<Flex
					_dark={{
						bg: "gray.800",
					}}
					align="center"
					as="header"
					bg="white"
					borderBottomWidth="1px"
					borderColor="blackAlpha.300"
					h="16"
					justify="space-between"
					px="4"
					w="full"
				>
					<Flex align="center" as={Link} to="/projects">
						<IconBrandBunpo color="#7e22ce" size="30" />
						<Text fontSize="3xl" fontWeight="bold" ml="2">
							Nojira
						</Text>
					</Flex>

					<Flex gap={5}>
						<IconButton
							aria-label="Change color mode"
							bgColor="transparent"
							icon={colorMode === "light" ? <IconSun /> : <IconMoon />}
							onClick={toggleColorMode}
							size="sm"
						/>

						<Menu>
							<MenuButton>
								<Avatar
									bgColor="#7e22ce"
									color="white"
									cursor="pointer"
									name={authUser?.fullName}
									size="sm"
								/>
							</MenuButton>
							<MenuList>
								<MenuItem
									icon={<IconLogout />}
									onClick={() => {
										navigate("/auth/login");
										deleteAuthUser();
									}}
								>
									Cerrar sesion
								</MenuItem>
							</MenuList>
						</Menu>
					</Flex>
				</Flex>

				<Container as="main" centerContent maxW="container.xl">
					<Outlet />
				</Container>
			</Box>
		</Box>
	);
};
