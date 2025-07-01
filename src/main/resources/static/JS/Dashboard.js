window.addEventListener("DOMContentLoaded", () => {
  // Botón para abrir/cerrar sidebar
  const toggleBtn = document.getElementById("toggleSidebar");
  const sidebar = document.getElementById("sidebar");

  toggleBtn.addEventListener("click", () => {
    sidebar.classList.toggle("hidden");
  });

  // Submenús
  const submenuToggles = document.querySelectorAll(".submenu-toggle");
  submenuToggles.forEach((toggle) => {
    toggle.addEventListener("click", function (e) {
      e.preventDefault();
      this.classList.toggle("active");
      this.nextElementSibling.classList.toggle("active");
    });
  });

  // Gráfica: Donaciones por Fundación
  const ctxDonaciones = document.getElementById("donacionesChart").getContext("2d");
  const donacionesChart = new Chart(ctxDonaciones, {
    type: "bar",
    data: {
      labels: ["Fundación Patitas", "Ayuda Animal", "Huellitas"],
      datasets: [{
        label: "Donaciones ($)",
        data: [1200, 850, 640],
        backgroundColor: ["#4e73df", "#1cc88a", "#f6c23e"]
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { display: true }
      }
    }
  });

  // Gráfica: Tratamientos por Veterinaria
  const ctxTratamientos = document.getElementById("tratamientosChart").getContext("2d");
  const tratamientosChart = new Chart(ctxTratamientos, {
    type: "pie",
    data: {
      labels: ["VetLife", "AnimalCare", "Patitas Salud"],
      datasets: [{
        label: "Tratamientos activos",
        data: [15, 10, 5],
        backgroundColor: ["#36b9cc", "#e74a3b", "#858796"]
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: "bottom" }
      }
    }
  });
});
