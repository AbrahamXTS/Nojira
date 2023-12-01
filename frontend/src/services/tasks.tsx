import { useMutation } from "@tanstack/react-query";

import {
	CreateTaskRequest,
	CreateTaskResponse,
	DeleteTaskRequest,
	DeleteTaskResponse,
	EditTaskRequest,
	EditTaskDescriptionResponse,
	GetTaskRequest,
	GetTaskResponse,
	GetTasksResponse,
} from "@/interfaces";
import { getAuthUser } from "@/utils";

import { axiosSecured } from "./api";

export const useGetTaskService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({ projectId, taskId }: GetTaskRequest) => {
			return axiosSecured.get<GetTaskResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}`,
			);
		},
	});
};

export const useGetTasksService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: (projectId: string) => {
			return axiosSecured.get<GetTasksResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks`,
			);
		},
	});
};

export const useCreateTaskService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({ description, projectId, title }: CreateTaskRequest) => {
			return axiosSecured.post<CreateTaskResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks`,
				{
					title,
					description,
				},
			);
		},
	});
};

export const useEditTaskService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: (request: EditTaskRequest) => {
			return axiosSecured.put<EditTaskDescriptionResponse>(
				`user/${user?.userId}/projects/${request.projectId}/tasks/${request.taskId}`,
				{
					...request,
				},
			);
		},
	});
};

export const useDeleteTaskService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({ projectId, taskId }: DeleteTaskRequest) => {
			return axiosSecured.delete<DeleteTaskResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}`,
			);
		},
	});
};
