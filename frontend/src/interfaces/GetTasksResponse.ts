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
	asignedTo: AsignedTo;
	description: string;
	status: string;
	taskId: string;
	times: Times;
	title: string;
}

interface AsignedTo {
	userId: string;
	userName: string;
}

interface Times {
	estimated: number;
	used: number;
}
