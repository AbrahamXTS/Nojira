import { Table } from "@tanstack/react-table";
import { Button, ButtonGroup, HStack, Select, Text } from "@chakra-ui/react";

interface Props<T> {
	table: Table<T>;
}

export function PaginationControls<T>({ table }: Readonly<Props<T>>) {
	return (
		<HStack gap={3} justifyContent="space-between" py={4}>
			<HStack>
				<Text fontSize="sm">
					Página
					<Text as="span" fontWeight="bold">
						{` ${
							table.getState().pagination.pageIndex + 1
						} de ${table.getPageCount()}`}
					</Text>
				</Text>
				<Select
					borderRadius="lg"
					onChange={(e) => {
						table.setPageSize(Number(e.target.value));
					}}
					size="sm"
					value={table.getState().pagination.pageSize}
					w={32}
				>
					{[5, 10, 20, 30, 40].map((pageSize) => (
						<option key={pageSize} value={pageSize}>
							Mostrar {pageSize}
						</option>
					))}
				</Select>
			</HStack>

			<ButtonGroup>
				<Button
					isDisabled={!table.getCanPreviousPage()}
					onClick={() => table.previousPage()}
					size="sm"
					variant="outline"
				>
					Anterior
				</Button>
				<Button
					isDisabled={!table.getCanNextPage()}
					onClick={() => table.nextPage()}
					size="sm"
					variant="outline"
				>
					Siguiente
				</Button>
			</ButtonGroup>
		</HStack>
	);
}
