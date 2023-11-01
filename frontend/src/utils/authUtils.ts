import { User } from "@/interfaces";

export const setAuthUser = (user: User) => {
	window.localStorage.setItem("authUser", JSON.stringify(user));
};

export const getAuthUser = (): User | null => {
	const user = localStorage.getItem("authUser");

	if (user) {
		return JSON.parse(user);
	}

	return null;
};

export const deleteAuthUser = () => {
	window.localStorage.removeItem("authUser");
};
