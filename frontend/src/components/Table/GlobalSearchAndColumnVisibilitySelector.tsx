import {
	Button,
	Checkbox,
	HStack,
	Icon,
	Input,
	InputGroup,
	InputLeftElement,
	Menu,
	MenuButton,
	MenuItem,
	MenuList,
} from "@chakra-ui/react";
import { Table } from "@tanstack/react-table";
import { Dispatch, SetStateAction } from "react";
import { IconChevronDown, IconSearch } from "@tabler/icons-react";

interface Props<T> {
	globalFilter: string;
	setGlobalFilter: Dispatch<SetStateAction<string>>;
	table: Table<T>;
}

export function GlobalSearchAndColumnVisibilitySelector<T>({
	globalFilter,
	setGlobalFilter,
	table,
}: Readonly<Props<T>>) {
	return (
		<HStack pb={6}>
			<InputGroup>
				<InputLeftElement pointerEvents="none">
					<Icon as={IconSearch} color="gray.300" />
				</InputLeftElement>
				<Input
					onChange={(e) => setGlobalFilter(e.target.value)}
					placeholder="Buscar"
					value={globalFilter}
				/>
			</InputGroup>

			<Menu closeOnSelect={false}>
				<MenuButton
					as={Button}
					rightIcon={<Icon as={IconChevronDown} ml={3} />}
				>
					Columnas
				</MenuButton>
				<MenuList>
					{table
						.getAllLeafColumns()
						.filter((column) => column.getCanHide())
						.map((column) => (
							<MenuItem key={column.id}>
								<Checkbox
									isChecked={column.getIsVisible()}
									onChange={column.getToggleVisibilityHandler()}
									textTransform="capitalize"
									w="full"
								>
									{String(column.columnDef.header ?? column.id)}
								</Checkbox>
							</MenuItem>
						))}
				</MenuList>
			</Menu>
		</HStack>
	);
}
