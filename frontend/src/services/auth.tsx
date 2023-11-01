import { useMutation } from "@tanstack/react-query";

import {
	LoginRequest,
	SignupRequest,
	SuccesfulAuthenticationResponse,
} from "@/interfaces";

import { axiosSecured } from "./api";

export const useLoginService = () => {
	return useMutation({
		mutationFn: (data: LoginRequest) => {
			return axiosSecured.post<SuccesfulAuthenticationResponse>("/auth/login", {
				...data,
			});
		},
	});
};

export const useSignupService = () => {
	return useMutation({
		mutationFn: (data: SignupRequest) => {
			return axiosSecured.post<SuccesfulAuthenticationResponse>("/auth/signup", {
				...data,
			});
		},
	});
};
