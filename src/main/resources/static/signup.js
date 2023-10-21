// TODO: Hacer que los cleanInputButtons unicamente se muestren si hay algo en el input

const cleanInputButtons = document.querySelectorAll(".close-btn");

cleanInputButtons.forEach((closeButton) => {
    closeButton.addEventListener("click", () => {
        const input = closeButton.parentElement.querySelector("input");

        if (input) {
            input.value = "";
        }
    });
});
