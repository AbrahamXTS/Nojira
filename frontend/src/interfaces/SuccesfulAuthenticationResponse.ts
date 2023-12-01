export interface SuccesfulAuthenticationResponse {
	body: Body;
	message: string;
	ok: boolean;
}

interface Body {
	email: string;
	fullName: string;
	token: string;
	userId: string;
}
