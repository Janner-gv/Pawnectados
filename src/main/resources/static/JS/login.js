document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("container");
    const signInBtn = document.getElementById("login");
    const signUpBtn = document.getElementById("register");

    if (signInBtn && signUpBtn && container) {
        signInBtn.addEventListener("click", () => container.classList.remove("active"));
        signUpBtn.addEventListener("click", () => container.classList.add("active"));
    }

    // 🟢 REGISTRO
    const registrarForm = document.getElementById("registrarForm");
    if (registrarForm) {
        registrarForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const formData = new FormData(this);

            fetch("http://localhost:8080/api/registro", {
                method: "POST",
                body: formData
            })
            .then(res => res.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeRegistro");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    alert("✅ " + data.message);
                    container.classList.remove("active");
                }
            })
            .catch(error => {
                alert("❌ Error al registrar");
                console.error(error);
            });
        });
    }

    // 🔐 LOGIN
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const formData = new FormData(this);

            fetch("http://localhost:8080/api/login", {
                method: "POST",
                body: formData,
                credentials: "include"
            })
            .then(res => res.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeLogin");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    alert("✅ " + data.message);

                    setTimeout(() => {
                        // Redirección según rol
                        switch (parseInt(data.rol)) {
                            case 1:
                                window.location.href = "/usuario/index"; // debe ser una ruta definida en tu controlador
                                break;
                            case 2:
                                window.location.href = "/fundacion/index";
                                break;
                            case 3:
                                window.location.href = "/veterinaria/index";
                                break;
                            case 4:
                                window.location.href = "/admin/Dashboard";
                                break
                            default:
                                window.location.href = "/";
                        }
                    }, 1500);
                }
            })
            .catch(error => {
                alert("❌ Error en el login");
                console.error(error);
            });
        });
    }

    // 🔴 CERRAR SESIÓN (opcional: aún no está implementado)
});
