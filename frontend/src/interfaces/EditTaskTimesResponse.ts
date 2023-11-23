import { Task } from ".";

export interface EditTaskTimesResponse {
	body: Task;
	message: string;
	ok: boolean;
}
