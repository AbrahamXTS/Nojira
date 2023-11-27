import { Link } from "react-router-dom";
import { ColumnDef } from "@tanstack/react-table";
import { Badge, Center, Checkbox, Link as LinkChakra } from "@chakra-ui/react";

import { Task } from "@/interfaces";
import { Avatar } from "@/components";

const STATUS_COLORS = {
	"Por hacer": "gray",
	"En progreso": "purple",
	Finalizada: "green",
};

export const tasksTableColumns: ColumnDef<Task>[] = [
	{
		id: "select",
		header: ({ table }) => (
			<Checkbox
				colorScheme="purple"
				isChecked={table.getIsAllPageRowsSelected()}
				onChange={() => table.toggleAllPageRowsSelected()}
			/>
		),
		cell: ({ row }) => (
			<Checkbox
				colorScheme="purple"
				isChecked={row.getIsSelected()}
				onChange={() => row.toggleSelected()}
			/>
		),
		enableHiding: false,
		enableSorting: false,
	},
	{
		accessorKey: "title",
		enableSorting: true,
		header: "Título",
		enableHiding: false,
		cell: ({ getValue, row: { original } }) => (
			<LinkChakra as={Link} to={`tasks/${original.taskId}`}>
				{getValue() as string}
			</LinkChakra>
		),
	},
	{
		accessorKey: "status",
		enableSorting: true,
		header: "Estatus",
		cell: ({ getValue }) => (
			<Badge
				colorScheme={STATUS_COLORS[getValue() as keyof typeof STATUS_COLORS]}
			>
				{getValue() as string}
			</Badge>
		),
	},
	{
		accessorFn: (row) => row.assignedTo.ownerFullName,
		enableSorting: true,
		header: "Asignación",
		cell: ({ getValue }) => (
			<Center>
				<Avatar name={getValue() as string} />
			</Center>
		),
	},
];
