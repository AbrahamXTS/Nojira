export interface GetProjectsResponse {
	body: Project[];
	message: string;
	ok: boolean;
}

export interface Project {
	description: string;
	owner: Owner;
	projectId: string;
	projectName: string;
}

interface Owner {
	ownerFullName: string;
	ownerId: string;
}
