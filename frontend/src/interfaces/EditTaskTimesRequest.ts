export interface EditTaskTimesRequest {
	estimated: number;
	projectId: string;
	taskId: string;
	used: number;
}
