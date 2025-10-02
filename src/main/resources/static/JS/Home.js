// --- AOS (Animaciones al hacer scroll) ---
AOS.init({
    duration: 1000,
    easing: 'ease-in-out',
    once: true
});

// --- Scroll suave para enlaces internos ---
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    });
});

// --- Inicializar mapa Leaflet ---
const mapa = L.map('mapa').setView([4.7110, -74.0721], 12); // Coordenadas de Bogotá por defecto

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; OpenStreetMap contributors'
}).addTo(mapa);

// Ejemplo de marcadores para fundaciones y veterinarias
const lugares = [
    { nombre: "Fundación Huellitas Felices", coords: [4.712, -74.065], tipo: "Fundación" },
    { nombre: "Veterinaria Mascotas Saludables", coords: [4.710, -74.080], tipo: "Veterinaria" },
    { nombre: "Fundación Patitas Alegres", coords: [4.715, -74.070], tipo: "Fundación" },
    { nombre: "Veterinaria Animal Care", coords: [4.705, -74.075], tipo: "Veterinaria" }
];

lugares.forEach(lugar => {
    const marker = L.marker(lugar.coords).addTo(mapa);
    marker.bindPopup(`<strong>${lugar.nombre}</strong><br>Tipo: ${lugar.tipo}`);
});

// --- Formulario Newsletter ---
const newsletterForm = document.getElementById('newsletterForm');

newsletterForm.addEventListener('submit', function(e) {
    e.preventDefault();
    const emailInput = this.querySelector('input[type="email"]');
    const email = emailInput.value.trim();

    if (email === "") {
        alert("Por favor, ingresa tu correo electrónico.");
        return;
    }

    // Aquí podrías hacer un fetch a tu backend si quieres guardar el correo
    console.log("Correo registrado:", email);

    alert("¡Gracias por suscribirte!");
    emailInput.value = ""; // Limpiar input
});

// --- Animación de hero (cambio de fondo) opcional ---
const heroSlide = document.querySelector('.hero-slide');
const imagenes = [
    'https://images.unsplash.com/photo-1558788353-f76d92427f16',
    'https://images.unsplash.com/photo-1517849845537-4d257902454a',
    'https://images.unsplash.com/photo-1601758123927-2187d57a1c51'
];

let indice = 0;
setInterval(() => {
    indice = (indice + 1) % imagenes.length;
    heroSlide.style.backgroundImage = `url('${imagenes[indice]}')`;
}, 8000);
