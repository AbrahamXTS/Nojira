import { Task } from ".";

export interface EditTaskDescriptionResponse {
	body: Task;
	message: string;
	ok: boolean;
}
