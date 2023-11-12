import { SuccesfulAuthenticationResponse } from "@/interfaces";

export const setAuthUser = (user: SuccesfulAuthenticationResponse["body"]) => {
	window.localStorage.setItem("authUser", JSON.stringify(user));
};

export const getAuthUser = (): SuccesfulAuthenticationResponse["body"] | null => {
	const user = window.localStorage.getItem("authUser");

	if (user) {
		return JSON.parse(user);
	}

	return null;
};

export const deleteAuthUser = () => {
	window.localStorage.removeItem("authUser");
};
