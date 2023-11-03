export interface SuccesfulAuthenticationResponse {
	ok: boolean;
	message: string;
	body: Body;
}

interface Body {
	userId: string;
	email: string;
	fullName: string;
	token: string;
}
