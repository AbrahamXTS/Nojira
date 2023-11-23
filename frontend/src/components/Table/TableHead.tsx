import { IconSelector } from "@tabler/icons-react";
import { Table, flexRender } from "@tanstack/react-table";
import { Box, Button, Icon, Th, Thead, Tr, useColorMode } from "@chakra-ui/react";

interface Props<T> {
	table: Table<T>;
}

export function TableHead<T>({ table }: Readonly<Props<T>>) {
	const { colorMode } = useColorMode();

	return (
		<Thead>
			{table.getHeaderGroups().map((headerGroup) => (
				<Tr key={headerGroup.id}>
					{headerGroup.headers.map((header) => (
						<Th key={header.id} py={1}>
							{header.column.getCanSort() ? (
								<Button
									onClick={() => header.column.toggleSorting()}
									rightIcon={<Icon as={IconSelector} />}
									variant="ghost"
								>
									{flexRender(
										header.column.columnDef.header,
										header.getContext(),
									)}
								</Button>
							) : (
								<Box
									color={colorMode === "light" ? "black" : "white"}
									fontSize="md"
									fontWeight="semibold"
									textTransform="capitalize"
								>
									{flexRender(
										header.column.columnDef.header,
										header.getContext(),
									)}
								</Box>
							)}
						</Th>
					))}
				</Tr>
			))}
		</Thead>
	);
}
