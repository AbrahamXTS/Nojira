export interface SuccesfulAuthenticationResponse {
	ok: boolean;
	message: string;
	body: Body;
}

interface Body {
	email: string;
	fullName: string;
	token: string;
}
