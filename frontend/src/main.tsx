import React from "react";
import ReactDOM from "react-dom/client";
import { ChakraProvider } from "@chakra-ui/react";
import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

import { AuthValidator, ErrorMessage } from "@/components";

import { PageNotFound } from "@/pages/PageNotFound";
import { LoginPage, SignupPage } from "@/pages/auth";
import { Layout, ProjectsPage, TasksPage } from "@/pages/projects";
import { TaskPage } from "@/pages/projects/[projectId]/[taskId]";

const router = createBrowserRouter([
	{
		path: "*",
		element: <PageNotFound />,
	},
	{
		path: "/",
		element: <Navigate to="/projects" />,
	},
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
		errorElement: <ErrorMessage />,
		children: [
			{
				path: "",
				element: <Layout />,
				children: [
					{
						path: "",
						element: <ProjectsPage />,
					},
					{
						path: ":projectId",
						element: <TasksPage />,
					},
					{
						path: ":projectId/tasks/:taskId",
						element: <TaskPage />,
					},
				],
			},
		],
	},
]);

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<ChakraProvider
			resetCSS
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
