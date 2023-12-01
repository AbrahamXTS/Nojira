import {
	DndContext,
	DragEndEvent,
	DragOverEvent,
	DragOverlay,
	DragStartEvent,
	PointerSensor,
	TouchSensor,
	useSensor,
	useSensors,
} from "@dnd-kit/core";
import { Box, Grid, Portal } from "@chakra-ui/react";
import { useEffect, useMemo, useState } from "react";
import { SortableContext, arrayMove } from "@dnd-kit/sortable";

import { STATUS_CATALOG, Task } from "@/interfaces";

import { TaskCard } from "./TaskCard";
import { ColumnContainer } from "./ColumnContainer";
import { DroppingZoneInfo, DraggableInfo } from "./types";

const columnNames: DroppingZoneInfo[] = Object.keys(STATUS_CATALOG).map((status) => ({
	id: status,
	title: status.toUpperCase(),
}));

interface Props {
	data: Task[];
}

export function KanbanBoard({ data }: Readonly<Props>) {
	const [columns, setColumns] = useState<DroppingZoneInfo[]>(columnNames);

	const columnsId = useMemo(() => columns.map((column) => column.id), [columns]);

	const [activeTask, setActiveTask] = useState<DraggableInfo | null>(null);

	const [activeColumn, setActiveColumn] = useState<DroppingZoneInfo | null>(null);

	const [tasks, setTasks] = useState<DraggableInfo[]>([]);

	useEffect(() => {
		setTasks(
			data.map((item) => ({
				columnId: item.status,
				content: item,
				id: item.taskId,
			})),
		);
	}, [data]);

	const sensors = useSensors(
		useSensor(TouchSensor),
		useSensor(PointerSensor, {
			activationConstraint: {
				distance: 10,
			},
		}),
	);

	return (
		<Box w="full">
			<DndContext
				onDragEnd={onDragEnd}
				onDragOver={onDragOver}
				onDragStart={onDragStart}
				sensors={sensors}
			>
				<Grid
					gap={5}
					templateColumns={[
						"1fr",
						"repeat(2, 1fr)",
						"repeat(2, 1fr)",
						"repeat(3,1fr)",
					]}
				>
					<SortableContext items={columnsId}>
						{columns.map((column) => (
							<ColumnContainer
								key={column.id}
								column={column}
								content={tasks.filter(
									(item) => item.columnId === column.id,
								)}
							/>
						))}
					</SortableContext>

					<Portal>
						<DragOverlay>
							{activeColumn && (
								<ColumnContainer
									column={activeColumn}
									content={tasks.filter(
										(item) => item.columnId === activeColumn.id,
									)}
								/>
							)}

							{activeTask && <TaskCard task={activeTask} />}
						</DragOverlay>
					</Portal>
				</Grid>
			</DndContext>
		</Box>
	);

	function onDragStart(event: DragStartEvent) {
		if (event.active.data.current?.type === "Column") {
			return setActiveColumn(event.active.data.current.column);
		}

		if (event.active.data.current?.type === "Task") {
			return setActiveTask(event.active.data.current.task);
		}
	}

	function onDragEnd(event: DragEndEvent) {
		setActiveTask(null);
		setActiveColumn(null);

		const { active, over } = event;

		if (!over) return;

		const overId = over.id;
		const activeId = active.id;

		if (activeId === overId) return;

		const isActiveAColumn = active.data.current?.type === "Column";

		if (!isActiveAColumn) return;

		setColumns((columns) => {
			const activeColumnIndex = columns.findIndex((col) => col.id === activeId);

			const overColumnIndex = columns.findIndex((col) => col.id === overId);

			return arrayMove(columns, activeColumnIndex, overColumnIndex);
		});
	}

	function onDragOver(event: DragOverEvent) {
		const { active, over } = event;

		if (!over) return;

		const overId = over.id;
		const activeId = active.id;

		if (activeId === overId) return;

		const isOverATask = over.data.current?.type === "Task";
		const isActiveATask = active.data.current?.type === "Task";
		const isOverAColumn = over.data.current?.type === "Column";

		if (!isActiveATask) return;

		if (isActiveATask && isOverATask) {
			setTasks((tasks) => {
				const overIndex = tasks.findIndex((task) => task.id === overId);
				const activeIndex = tasks.findIndex((task) => task.id === activeId);

				if (tasks[activeIndex].columnId !== tasks[overIndex].columnId) {
					tasks[activeIndex].columnId = tasks[overIndex].columnId;
					return arrayMove(tasks, activeIndex, overIndex - 1);
				}

				return arrayMove(tasks, activeIndex, overIndex);
			});
		}

		if (isActiveATask && isOverAColumn) {
			setTasks((tasks) => {
				const activeIndex = tasks.findIndex((task) => task.id === activeId);

				// The task moves over the same column
				if (tasks[activeIndex].columnId === overId) {
					return tasks;
				}

				tasks[activeIndex].columnId = overId;
				return arrayMove(tasks, activeIndex, activeIndex);
			});
		}
	}
}
