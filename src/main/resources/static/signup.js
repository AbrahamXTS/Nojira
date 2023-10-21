// TODO: Hacer que los cleanInputButtons unicamente se muestren si hay algo en el input

const cleanInputButtons = document.querySelectorAll(".close-btn");

cleanInputButtons.forEach((cleanInputButton) => {
  cleanInputButton.addEventListener("click", () => {
    const input = cleanInputButton.parentElement.querySelector("input");

    if (input) {
      input.value = "";
    }
  });
});
