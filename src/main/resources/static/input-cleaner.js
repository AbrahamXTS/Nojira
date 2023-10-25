const inputContainers = document.querySelectorAll(".input-container");

inputContainers.forEach((container) => {
	const input = container.querySelector("input");
	const closeButton = container.querySelector(".close-btn");

	input.addEventListener("input", () => {
		if (input.value === "") {
			closeButton.classList.add("hidden");
		} else {
			closeButton.classList.remove("hidden")
		}
	});

	closeButton.addEventListener("click", () => {
		closeButton.classList.add("hidden");
		input.value = "";
	});
});
