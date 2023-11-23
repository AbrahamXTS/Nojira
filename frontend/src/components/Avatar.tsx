import { memo } from "react";
import { Avatar as AvatarChakra, Tooltip } from "@chakra-ui/react";

interface Props {
	name: string;
}

export const Avatar = memo(({ name }: Props) => {
	return (
		<Tooltip label={name}>
			<AvatarChakra
				bg="#7e22ce"
				name={name}
				size="sm"
				src={`https://api.dicebear.com/7.x/micah/svg?seed=${name}`}
			/>
		</Tooltip>
	);
});
