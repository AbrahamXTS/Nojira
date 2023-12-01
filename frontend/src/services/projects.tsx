import { useMutation } from "@tanstack/react-query";

import {
	CreateProjectRequest,
	CreateProjectResponse,
	GetParticipantsFromProjectResponse,
	GetProjectsResponse,
} from "@/interfaces";
import { getAuthUser } from "@/utils";

import { axiosSecured } from "./api";

export const useGetProjectsService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: () => {
			return axiosSecured.get<GetProjectsResponse>(
				`user/${user?.userId}/projects`,
			);
		},
	});
};

export const useGetParticipantsFromProjectService = () => {
	return useMutation({
		mutationFn: (projectId: string) => {
			return axiosSecured.get<GetParticipantsFromProjectResponse>(
				`projects/${projectId}/users`,
			);
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
