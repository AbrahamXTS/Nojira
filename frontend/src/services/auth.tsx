import { useMutation } from "@tanstack/react-query";

import {
	LoginRequest,
	SignupRequest,
	SuccesfulAuthenticationResponse,
} from "@/interfaces";

import { axiosSecured } from "./api";
import { useToast } from "@chakra-ui/react";
import { AxiosError } from "axios";

export const useLoginService = () => {
	const toast = useToast();

	return useMutation({
		mutationFn: (data: LoginRequest) => {
			return axiosSecured.post<SuccesfulAuthenticationResponse>("/auth/login", {
				...data,
			});
		},
		onError: (error: AxiosError<SuccesfulAuthenticationResponse>) => {
			toast({
				title: error?.response?.data?.message ?? "Oh no! Algo salió mal mientras procesabamos tu solicitud",
				status: "error",
			});
		},
	});
};

export const useSignupService = () => {
	const toast = useToast();

	return useMutation({
		mutationFn: (data: SignupRequest) => {
			return axiosSecured.post<SuccesfulAuthenticationResponse>("/auth/signup", {
				...data,
			});
		},
		onError: (error: AxiosError<SuccesfulAuthenticationResponse>) => {
			toast({
				title: error?.response?.data?.message ?? "Oh no! Algo salió mal mientras procesabamos tu solicitud",
				status: "error",
			});
		},
	});
};
