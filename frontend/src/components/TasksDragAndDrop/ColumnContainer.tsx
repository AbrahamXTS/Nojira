import { useMemo } from "react";
import { CSS } from "@dnd-kit/utilities";
import { SortableContext, useSortable } from "@dnd-kit/sortable";
import { Box, Card, Flex, GridItem, Text, useColorMode } from "@chakra-ui/react";

import { TaskCard } from "./TaskCard";
import { DroppingZoneInfo, DraggableInfo } from "./types";

interface Props {
	column: DroppingZoneInfo;
	content: DraggableInfo[];
}

export const ColumnContainer = ({ column, content }: Props) => {
	const contentIds = useMemo(() => {
		return content.map((content) => content.id);
	}, [content]);

	const { setNodeRef, attributes, listeners, transform, transition, isDragging } =
		useSortable({
			id: column.id,
			data: {
				type: "Column",
				column,
			},
		});

	const style = {
		transition,
		transform: CSS.Transform.toString(transform),
	};

	const { colorMode } = useColorMode();

	if (isDragging) {
		return (
			<Box
				bg={colorMode === "light" ? "gray.100" : "gray.800"}
				border="2px solid #7e22ce"
				h="500px"
				maxH="500px"
				opacity="0.4"
				ref={setNodeRef}
				rounded="md"
				style={style}
				w="full"
			/>
		);
	}

	return (
		<Card
			{...attributes}
			{...listeners}
			align="start"
			as={GridItem}
			bg={colorMode === "light" ? "gray.100" : "gray.800"}
			h="500px"
			maxH="500px"
			py={3}
			ref={setNodeRef}
			rounded="md"
			style={style}
			w="full"
		>
			<Text fontSize="xs" fontWeight="bold" px={5}>
				{column.title} {content.length}
			</Text>

			<Flex
				flexDirection="column"
				flexGrow={1}
				gap={4}
				overflowX="hidden"
				overflowY="auto"
				p={2}
				w="full"
			>
				<SortableContext items={contentIds}>
					{content.map((item) => (
						<TaskCard key={item.id} task={item} />
					))}
				</SortableContext>
			</Flex>
		</Card>
	);
};
