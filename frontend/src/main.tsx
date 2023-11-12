import React from "react";
import ReactDOM from "react-dom/client";
import { ChakraProvider } from "@chakra-ui/react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

import { AuthValidator } from "@/components";

import { LoginPage, SignupPage } from "@/pages/auth";
import { Layout, Projects } from "@/pages/projects";
import { PageNotFound } from "@/pages/PageNotFound";

const router = createBrowserRouter([
	{
		path: "/auth/login",
		element: <LoginPage />,
	},
	{
		path: "/auth/signup",
		element: <SignupPage />,
	},
	{
		path: "/projects",
		element: <AuthValidator />,
		children: [
			{
				path: "",
				element: <Layout />,
				children: [
					{
						path: "",
						element: <Projects />,
					},
					{
						path: ":projectId",
						element: null
					}
				],
			},
		],
	},
	{
		path: "*",
		element: <PageNotFound />,
	},
]);

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<ChakraProvider
			toastOptions={{
				defaultOptions: {
					duration: 5000,
					isClosable: true,
					position: "bottom-right",
				},
			}}
		>
			<QueryClientProvider client={queryClient}>
				<RouterProvider router={router} />
			</QueryClientProvider>
		</ChakraProvider>
	</React.StrictMode>,
);
