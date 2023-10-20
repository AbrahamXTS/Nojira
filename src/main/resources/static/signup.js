console.log("Botón de cierre hace clic");
const closeButtons = document.querySelectorAll(".close-btn");
closeButtons.forEach((closeButton) => {
    closeButton.addEventListener("click", () => {
        const input = closeButton.parentElement.querySelector("input");
        if (input) {
            input.value = "";
        }
    });
});


