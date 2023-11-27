export interface GetTasksResponse {
	body: Body;
	message: string;
	ok: boolean;
}

interface Body {
	projectId: string;
	projectName: string;
	tasks: Task[];
}

export interface Task {
	taskId: string;
	title: string;
	description: string;
	status: string;
	times: Times;
	assignedTo: AssignedTo;
}

interface AssignedTo {
	ownerId: string;
	ownerFullName: string;
}

interface Times {
	estimated: number;
	used: number;
}
