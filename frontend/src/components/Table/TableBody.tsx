import { Table, flexRender } from "@tanstack/react-table";
import { Tbody, Td, Tr, useColorMode } from "@chakra-ui/react";

interface Props<T> {
	table: Table<T>;
}

export function TableBody<T>({ table }: Readonly<Props<T>>) {
	const { colorMode } = useColorMode();

	return (
		<Tbody>
			{table.getRowModel().rows.length > 0
				? table.getRowModel().rows.map((row) => (
						<Tr
							_hover={{
								bgColor:
									colorMode === "light" ? "gray.100" : "gray.600",
							}}
							borderTopWidth={0.5}
							data-state={row.getIsSelected() && "selected"}
							key={row.id}
						>
							{row.getVisibleCells().map((cell) => (
								<Td key={cell.id}>
									{flexRender(
										cell.column.columnDef.cell,
										cell.getContext(),
									)}
								</Td>
							))}
						</Tr>
				  ))
				: null}
		</Tbody>
	);
}
