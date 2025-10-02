document.addEventListener("DOMContentLoaded", () => {
  // Carrusel tipo libro (manual)
  const paginas = document.querySelectorAll(".libro .pagina");
  const btnAnterior = document.getElementById("anterior");
  const btnSiguiente = document.getElementById("siguiente");
  let indice = 0;

  function mostrarPagina(i) {
    paginas.forEach((pagina, idx) => {
      pagina.classList.toggle("active", idx === i);
    });
  }

  if (btnSiguiente && btnAnterior) {
    btnSiguiente.addEventListener("click", () => {
      indice = (indice + 1) % paginas.length;
      mostrarPagina(indice);
    });

    btnAnterior.addEventListener("click", () => {
      indice = (indice - 1 + paginas.length) % paginas.length;
      mostrarPagina(indice);
    });

    // Mostrar la primera página
    mostrarPagina(indice);
  }

  // Carruseles Bootstrap (#videoCarousel, #animalCarousel, #menuCarousel)
  // ⚡ No necesitan código extra, Bootstrap ya los maneja.
});
