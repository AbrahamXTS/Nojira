import { Task } from ".";

export interface EditTaskRequest extends Task {
	projectId: string;
}
