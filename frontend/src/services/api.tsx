import axios from "axios";

import { getAuthUser } from "@/utils";

export const axiosSecured = axios.create({
	baseURL: import.meta.env.VITE_BACKEND_API_URL,
});

axiosSecured.interceptors.request.use(
	(config) => {
		const token = getAuthUser()?.token ?? "";

		if (token) {
			config.headers.Authorization = `Bearer ${token}`;
		}

		return config;
	},
	(error) => {
		return Promise.reject(error);
	},
);

axiosSecured.interceptors.response.use(
	(response) => {
		return response;
	},
	(error) => {
		const status = error.response.status;

		if (
			(status === 401 || status === 403) &&
			window.location.pathname !== "/auth/login"
		) {
			window.localStorage.removeItem("authUser");

			window.location.href = "/auth/login";

			return;
		}

		return Promise.reject(error);
	},
);
