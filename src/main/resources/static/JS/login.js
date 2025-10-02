document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("container");
    const signInBtn = document.getElementById("login");
    const signUpBtn = document.getElementById("register");

    // Toggle login/registro
    if (signInBtn && signUpBtn && container) {
        signInBtn.addEventListener("click", () => container.classList.remove("active"));
        signUpBtn.addEventListener("click", () => container.classList.add("active"));
    }

    // ================= REGISTRO =================
    const registrarForm = document.getElementById("registrarForm");
    if (registrarForm) {
        registrarForm.addEventListener("submit", function (event) {
            event.preventDefault();
            const formData = new FormData(this);

            // Validaciones
            const password = formData.get("password");
            const telefono = formData.get("telefono");
            const contraseñaValida = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test(password);
            const telefonoValido = /^\d{10}$/.test(telefono);

            if (!contraseñaValida) {
                alert("La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula y un número.");
                return;
            }
            if (!telefonoValido) {
                alert("El número de teléfono debe tener exactamente 10 dígitos.");
                return;
            }

            const params = new URLSearchParams(formData);

            fetch("/api/registro", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params
            })
            .then(response => response.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeRegistro");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    setTimeout(() => container.classList.remove("active"), 1500);
                }
            })
            .catch(error => {
                console.error("❌ Error en el registro:", error);
                alert("Ocurrió un error inesperado al registrar el usuario.");
            });
        });
    }

    // ================= LOGIN =================
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function (event) {
            event.preventDefault();
            const formData = new FormData(this);
            const params = new URLSearchParams(formData);

            fetch("/api/login", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params
            })
            .then(response => response.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeLogin");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    setTimeout(() => {
                        let destino = "/";
                        switch (data.rol) {
                            case "ROLE_USER": destino = "usuarios/usuarios"; break;
                            case "ROLE_FUNDACION": destino = "fundacion/fundaciones"; break;
                            case "ROLE_VETERINARIA": destino = "veterinaria/index"; break;
                            case "ROLE_ADMIN": destino = "admin/Dashboard"; break;
                        }
                        window.location.href = destino;
                    }, 1000);
                }
            })
            .catch(error => {
                console.error("❌ Error en el login:", error);
                alert("Ocurrió un error inesperado al iniciar sesión.");
            });
        });
    }
});
