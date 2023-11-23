import { Project } from ".";

export interface CreateProjectResponse {
	body: Project;
	message: string;
	ok: boolean;
}
