import {
	ColumnDef,
	ColumnFiltersState,
	SortingState,
	VisibilityState,
	getCoreRowModel,
	getFilteredRowModel,
	getPaginationRowModel,
	getSortedRowModel,
	useReactTable,
} from "@tanstack/react-table";
import { useState } from "react";
import { Box, Table as TableChakra, TableContainer } from "@chakra-ui/react";

import { TableHead } from "./TableHead";
import { TableBody } from "./TableBody";
import { PaginationControls } from "./PaginationControls";
import { GlobalSearchAndColumnVisibilitySelector } from "./GlobalSearchAndColumnVisibilitySelector";

interface Props<T> {
	columns: ColumnDef<T>[];
	data: T[];
}

export function Table<T>({ columns, data }: Readonly<Props<T>>) {
	const [columnFilters, setColumnFilters] = useState<ColumnFiltersState>([]);

	const [columnVisibility, setColumnVisibility] = useState<VisibilityState>({});

	const [globalFilter, setGlobalFilter] = useState("");

	const [rowSelection, setRowSelection] = useState({});

	const [sorting, setSorting] = useState<SortingState>([]);

	const table = useReactTable({
		columns,
		data,
		getCoreRowModel: getCoreRowModel(),
		getFilteredRowModel: getFilteredRowModel(),
		getPaginationRowModel: getPaginationRowModel(),
		getSortedRowModel: getSortedRowModel(),
		onColumnFiltersChange: setColumnFilters,
		onColumnVisibilityChange: setColumnVisibility,
		onGlobalFilterChange: setGlobalFilter,
		onRowSelectionChange: setRowSelection,
		onSortingChange: setSorting,
		initialState: {
			pagination: {
				pageSize: 5,
			},
		},
		state: {
			columnFilters,
			columnVisibility,
			globalFilter,
			rowSelection,
			sorting,
		},
	});

	return (
		<Box w="full">
			<GlobalSearchAndColumnVisibilitySelector
				globalFilter={globalFilter}
				setGlobalFilter={setGlobalFilter}
				table={table}
			/>

			<TableContainer borderRadius="lg" borderWidth={0.5}>
				<TableChakra variant="unstyled">
					<TableHead table={table} />
					<TableBody table={table} />
				</TableChakra>
			</TableContainer>

			<PaginationControls table={table} />
		</Box>
	);
}
