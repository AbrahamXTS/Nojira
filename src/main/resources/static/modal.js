const modal = document.querySelector("#modal");

if (modal) {
  modal.classList.add("opacity-100");

  modal.querySelectorAll(".close-btn").forEach((closeBtn) => {
    closeBtn.addEventListener("click", () => {
      modal?.classList.remove("opacity-100");
      setTimeout(() => {
        modal.remove();
      }, 100);
    });
  });
}
