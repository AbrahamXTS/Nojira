import { Task } from "@/interfaces";

export type Id = string | number;

export type DroppingZoneInfo = {
	id: Id;
	title: string;
};

export type DraggableInfo = {
	id: Id;
	columnId: Id;
	content: Task;
};
