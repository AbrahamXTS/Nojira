export interface GetParticipantsFromProjectResponse {
	body: Participant[];
	message: string;
	ok: boolean;
}

export interface Participant {
	email: string;
	fullName: string;
	userId: string;
}
