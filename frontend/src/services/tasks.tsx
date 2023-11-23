import { useMutation } from "@tanstack/react-query";

import {
	CreateTaskRequest,
	CreateTaskResponse,
	DeleteTaskRequest,
	DeleteTaskResponse,
	EditTaskAssignmentRequest,
	EditTaskAssignmentResponse,
	EditTaskDescriptionRequest,
	EditTaskDescriptionResponse,
	EditTaskStatusRequest,
	EditTaskStatusResponse,
	EditTaskTimesRequest,
	EditTaskTimesResponse,
	GetTaskRequest,
	GetTaskResponse,
	GetTasksResponse,
} from "@/interfaces";
import { getAuthUser } from "@/utils";

import { axiosSecured } from "./api";

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

export const useEditTaskAssignmentService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({ projectId, taskId, newOwnerId }: EditTaskAssignmentRequest) => {
			return axiosSecured.put<EditTaskAssignmentResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}/assignment`,
				{
					newOwnerId,
				},
			);
		},
	});
};

export const useEditTaskDescriptionService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({
			description,
			projectId,
			taskId,
			title,
		}: EditTaskDescriptionRequest) => {
			return axiosSecured.put<EditTaskDescriptionResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}}`,
				{
					title,
					description,
				},
			);
		},
	});
};

export const useEditTaskStatusService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({ projectId, statusId, taskId }: EditTaskStatusRequest) => {
			return axiosSecured.put<EditTaskStatusResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}/status`,
				{
					taskId,
					statusId,
				},
			);
		},
	});
};

export const useEditTaskTimesService = () => {
	const user = getAuthUser();

	return useMutation({
		mutationFn: ({ estimated, projectId, taskId, used }: EditTaskTimesRequest) => {
			return axiosSecured.put<EditTaskTimesResponse>(
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}/time`,
				{
					estimated,
					taskId,
					used,
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
				`user/${user?.userId}/projects/${projectId}/tasks/${taskId}}`,
			);
		},
	});
};
