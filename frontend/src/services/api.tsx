import axios from "axios";

import { getAuthUser } from "@/utils";

const bearerToken = getAuthUser()?.token ?? "";

export const axiosSecured = axios.create({
	baseURL: import.meta.env.VITE_BACKEND_API_URL,
	headers: {
		Authorization: `Bearer ${bearerToken}`,
	},
});

axiosSecured.interceptors.response.use(
	(response) => {
		return response;
	},
	(error) => {
		const status = error.response.status;

		if (status === 401 || status === 403) {
			window.localStorage.removeItem("authUser");

			window.location.href = "/auth/login";
			return;
		}

		return Promise.reject(error);
	},
);
