import { Link } from "react-router-dom";
import { CSS } from "@dnd-kit/utilities";
import { useSortable } from "@dnd-kit/sortable";
import { IconInfoCircle, IconMenu } from "@tabler/icons-react";
import {
	Box,
	HStack,
	Icon,
	Link as LinkChakra,
	Text,
	VStack,
	useColorMode,
} from "@chakra-ui/react";

import { Avatar } from "../Avatar";
import { DraggableInfo } from "./types";

interface Props {
	task: DraggableInfo;
}

export const TaskCard = ({ task }: Props) => {
	const { setNodeRef, attributes, listeners, transform, transition, isDragging } =
		useSortable({
			id: task.id,
			data: {
				type: "Task",
				task,
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
				bg={colorMode === "light" ? "gray.50" : "gray.700"}
				borderWidth="2px"
				cursor="grab"
				h="100px"
				minH="100px"
				opacity="0.3"
				ref={setNodeRef}
				rounded="md"
				style={style}
			/>
		);
	}

	return (
		<HStack
			{...attributes}
			{...listeners}
			bg={colorMode === "light" ? "gray.50" : "gray.700"}
			cursor="grab"
			h="100px"
			justify="space-between"
			minH="100px"
			p={3}
			pos="relative"
			ref={setNodeRef}
			rounded="md"
			style={style}
		>
			<VStack align="start" h="full" justifyContent="space-between">
				<LinkChakra
					as={Link}
					fontWeight="bold"
					to={`tasks/${task.content.taskId}`}
				>
					{task.content.title}
				</LinkChakra>
				<HStack>
					<Icon as={IconInfoCircle} color="green" />
					<Text fontSize="sm">{task.content.taskId}</Text>
				</HStack>
			</VStack>
			<VStack h="full" justifyContent="space-between">
				<Avatar name={task.content.assignedTo.ownerFullName} />
				<Icon as={IconMenu} color="yellow.400" />
			</VStack>
		</HStack>
	);
};
