import { Project } from ".";

export interface CreateProjectResponse {
	ok: boolean;
	message: string;
	body: Project;
}
