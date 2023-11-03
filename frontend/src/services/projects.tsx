import { useMutation } from "@tanstack/react-query";

import {
	CreateProjectRequest,
	CreateProjectResponse,
	ProjectsResponse,
} from "@/interfaces";

import { axiosSecured } from "./api";
import { getAuthUser } from "@/utils";

export const useGetProjectsService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: () => {
			return axiosSecured.get<ProjectsResponse>(`user/${user?.userId}/projects`);
		},
	});
};

export const useCreateProjectService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: (request: CreateProjectRequest) => {
			return axiosSecured.post<CreateProjectResponse>(
				`user/${user?.userId}/projects`,
				{
					...request,
				},
			);
		},
	});
};
