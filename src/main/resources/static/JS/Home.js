document.addEventListener("DOMContentLoaded", function () {
    // ====== AOS ======
    if (AOS) AOS.init({ duration: 900, easing: 'ease-in-out', once: true });

    // ====== Smooth scroll ======
    document.querySelectorAll('a[href^="#"]').forEach(a => {
        a.addEventListener('click', function (e) {
            const href = this.getAttribute('href');
            if (!href || href === "#") return;
            e.preventDefault();
            const t = document.querySelector(href);
            if (t) t.scrollIntoView({ behavior: 'smooth', block: 'start' });
        });
    });

    // ====== Leaflet map ======
    const mapaEl = document.getElementById("mapa");
    if (mapaEl) {
        const mapa = L.map('mapa').setView([4.7110, -74.0721], 12);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap contributors'
        }).addTo(mapa);

        const lugares = [
            { nombre: 'Fundación Huellitas Felices', coords: [4.712, -74.065] },
            { nombre: 'Veterinaria Mascotas Saludables', coords: [4.71, -74.08] },
            { nombre: 'Fundación Patitas Alegres', coords: [4.715, -74.07] }
        ];

        lugares.forEach(l => {
            L.marker(l.coords).addTo(mapa).bindPopup(`<strong>${l.nombre}</strong>`);
        });
    }

    // ====== Newsletter ======
    const form = document.getElementById("newsletterForm");
    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault();
            const input = this.querySelector("input[type=email]");
            if (!input.value.trim()) {
                alert("Por favor ingresa un correo válido.");
                return;
            }
            alert("¡Gracias por suscribirte!");
            input.value = "";
        });
    }

    // ====== KPIs animados ======
    const kpis = {
        adopt: document.getElementById("kpi-adopt"),
        fund: document.getElementById("kpi-fund"),
        users: document.getElementById("kpi-users")
    };

    function animateCounter(el, to) {
        let start = 0;
        const duration = 1200;
        const step = Math.ceil(to / (duration / 20));
        const iv = setInterval(() => {
            start += step;
            if (start >= to) {
                el.textContent = `+${to}`;
                clearInterval(iv);
            } else {
                el.textContent = `+${start}`;
            }
        }, 20);
    }

    if (kpis.adopt) {
        animateCounter(kpis.adopt, 500);
        animateCounter(kpis.fund, 40);
        animateCounter(kpis.users, 2000);
    }
});
