export interface DeleteTaskResponse {
	body: Body;
	message: string;
	ok: boolean;
}

interface Body {
	taskId: string;
}
