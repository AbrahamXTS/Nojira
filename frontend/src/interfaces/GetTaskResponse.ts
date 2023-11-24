import { Task } from ".";

export interface GetTaskResponse {
	body: Task;
	message: string;
	ok: boolean;
}
