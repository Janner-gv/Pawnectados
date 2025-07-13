document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("container");
    const signInBtn = document.getElementById("login");
    const signUpBtn = document.getElementById("register");

    if (signInBtn && signUpBtn && container) {
        signInBtn.addEventListener("click", () => container.classList.remove("active"));
        signUpBtn.addEventListener("click", () => container.classList.add("active"));
    }

    // ✅ REGISTRO
    const registrarForm = document.getElementById("registrarForm");
    if (registrarForm) {
        registrarForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const formData = new FormData(this);
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

            fetch("http://localhost:8080/api/registro", {
                method: "POST",
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeRegistro");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    alert("✅ " + data.message);
                    setTimeout(() => container.classList.remove("active"), 1500);
                } else {
                    alert("⚠️ " + data.message);
                }
            })
            .catch(error => {
                console.error("❌ Error en el registro:", error);
                alert("Ocurrió un error inesperado.");
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
            .then(response => response.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeLogin");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    alert("✅ " + data.message);
                    setTimeout(() => {
                        let destino = "/";
                        switch (parseInt(data.rol)) {
                            case 1: destino = "/usuarios/usuarios"; break;
                            case 2: destino = "/fundacion/fundaciones"; break;
                            case 3: destino = "/Veterinaria/veterinarias"; break;
                            case 4: destino = "/admin/Dashboard"; break;
                        }
                        window.location.href = destino;
                    }, 1500);
                } else {
                    alert("⚠️ " + data.message);
                }
            })
            .catch(error => {
                console.error("❌ Error en el login:", error);
                alert("Ocurrió un error inesperado al iniciar sesión.");
            });
        });
    }
});
