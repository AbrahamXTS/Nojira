import { Outlet, Navigate } from "react-router-dom";

import { getAuthUser } from "@/utils";

export const AuthValidator = () => {
	if (getAuthUser()) {
		return <Outlet />;
	} else {
		return <Navigate to="/auth/login" />;
	}
};
