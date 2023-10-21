const inputContainers = document.querySelectorAll(".input-container");
inputContainers.forEach(function (container) {
  const input = container.querySelector("input");
  const closeButton = container.querySelector(".close-btn");

  // Agregar un controlador de eventos al campo de entrada
  input.addEventListener("input", function () {
    if (input.value) {
      closeButton.style.display = "block";
    } else {
      closeButton.style.display = "none";
    }
  });

  // Agregar un controlador de eventos para el botón cerrar
  closeButton.addEventListener("click", function () {
    input.value = "";
    closeButton.style.display = "none";
  });
});



