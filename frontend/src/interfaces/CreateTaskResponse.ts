import { Task } from ".";

export interface CreateTaskResponse {
	body: Task;
	message: string;
	ok: boolean;
}
