document.addEventListener("DOMContentLoaded", function () {
  const toggleBtn = document.getElementById("toggleSidebar");
  const sidebar = document.getElementById("sidebar");

  if (toggleBtn && sidebar) {
    toggleBtn.addEventListener("click", () => {
      sidebar.classList.toggle("active");
    });
  }

  // === GRÁFICA DE DONACIONES ===
  const ctxDonaciones = document.getElementById("donacionesChart")?.getContext("2d");
  if (ctxDonaciones) {
    new Chart(ctxDonaciones, {
      type: "bar",
      data: {
        labels: ["Ene", "Feb", "Mar", "Abr", "May", "Jun"],
        datasets: [{
          label: "Donaciones ($)",
          data: [1200, 900, 1500, 1800, 950, 2100],
          backgroundColor: [
            "#4e73df",
            "#1cc88a",
            "#36b9cc",
            "#f6c23e",
            "#e74a3b",
            "#858796"
          ],
          borderRadius: 6,
          borderSkipped: false,
          borderWidth: 1,
          borderColor: "#fff"
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
          tooltip: {
            enabled: true,
            backgroundColor: "#1e3d59",
            titleColor: "#fff",
            bodyColor: "#fff"
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            ticks: { color: "#333" },
            grid: { color: "#eee" }
          },
          x: {
            ticks: { color: "#333" },
            grid: { display: false }
          }
        }
      }
    });
  }

  // === GRÁFICA DE TRATAMIENTOS POR FUNDACIÓN ===
  const ctxTratamientos = document.getElementById("tratamientosChart")?.getContext("2d");
  if (ctxTratamientos) {
    new Chart(ctxTratamientos, {
      type: "doughnut",
      data: {
        labels: ["Fundación Patitas", "Ayuda Animal", "VetCare"],
        datasets: [{
          label: "Tratamientos",
          data: [22, 16, 10],
          backgroundColor: ["#20c997", "#fd7e14", "#6610f2"],
          hoverOffset: 10,
          borderWidth: 3,
          borderColor: "#fff"
        }]
      },
      options: {
        responsive: true,
        cutout: '65%',
        plugins: {
          legend: {
            position: "bottom",
            labels: { color: "#1e3d59", font: { weight: "bold" } }
          },
          tooltip: {
            backgroundColor: "#1e3d59",
            bodyColor: "#fff"
          }
        }
      }
    });
  }
});
// === ANIMALES REGISTRADOS POR MES ===
const ctxAnimales = document.getElementById("animalesChart")?.getContext("2d");
if (ctxAnimales) {
  fetch("/admin/animales/por-mes")
    .then(res => res.json())
    .then(data => {
      const labels = Object.keys(data);
      const valores = Object.values(data);

      new Chart(ctxAnimales, {
        type: "line",
        data: {
          labels: labels,
          datasets: [{
            label: "Animales Registrados",
            data: valores,
            fill: true,
            backgroundColor: "rgba(78, 115, 223, 0.2)",
            borderColor: "#4e73df",
            tension: 0.3,
            pointBackgroundColor: "#4e73df",
            pointRadius: 5
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: { position: "top" },
            tooltip: {
              backgroundColor: "#1e3d59",
              bodyColor: "#fff"
            }
          },
          scales: {
            y: {
              beginAtZero: true,
              ticks: { color: "#333" },
              grid: { color: "#eee" }
            },
            x: {
              ticks: { color: "#333" },
              grid: { display: false }
            }
          }
        }
      });
    })
    .catch(error => console.error("Error al cargar gráfica de animales:", error));
}
