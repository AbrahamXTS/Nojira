import axios from "axios";

import { getAuthUser } from "@/utils";

const bearerToken = getAuthUser()?.token ?? "";

export const axiosSecured = axios.create({
	baseURL: import.meta.env.VITE_BACKEND_API_URL,
	headers: {
		Authorization: `Bearer ${bearerToken}`,
	},
});
