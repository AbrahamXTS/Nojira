const inputContainers = document.querySelectorAll(".input-container");
inputContainers.forEach(function (container) {
  const input = container.querySelector("input");
  const closeButton = container.querySelector(".close-btn");

  // Agregar un controlador de eventos al campo de entrada
  input.addEventListener("input", function () {
    if (input.value) {
      closeButton.classList.remove("hidden");
    } else {
      closeButton.classList.add("hidden");
    }
  });

  // Agregar un controlador de eventos para el botón cerrar
  closeButton.addEventListener("click", function () {
    input.value = "";
    closeButton.classList.add("hidden");
  });
});



