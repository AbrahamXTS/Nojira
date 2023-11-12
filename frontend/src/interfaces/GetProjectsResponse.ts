export interface ProjectsResponse {
    ok:      boolean;
    message: string;
    body:    Project[];
}

export interface Project {
    projectId:   string;
    projectName: string;
    description: string;
    owner:       Owner;
}

interface Owner {
    ownerId:       string;
    ownerFullName: string;
}
