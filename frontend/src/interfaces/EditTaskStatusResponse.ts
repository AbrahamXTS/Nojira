import { Task } from ".";

export interface EditTaskStatusResponse {
	body: Task;
	message: string;
	ok: boolean;
}
