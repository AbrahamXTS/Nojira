import { Task } from ".";

export interface EditTaskAssignmentResponse {
	body: Task;
	message: string;
	ok: boolean;
}
