document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("container");
    const signInBtn = document.getElementById("login");
    const signUpBtn = document.getElementById("register");

    // Animación entre formularios
    if (signInBtn && signUpBtn && container) {
        signInBtn.addEventListener("click", () => container.classList.remove("active"));
        signUpBtn.addEventListener("click", () => container.classList.add("active"));
    }

    // 🟢 REGISTRO DE USUARIO
    const registrarForm = document.getElementById("registrarForm");
    if (registrarForm) {
        registrarForm.addEventListener("submit", function(event) {
            event.preventDefault();

            const formData = new FormData(this);

            fetch("http://localhost/proyecto/principal/registrar.php", {
                method: "POST",
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeRegistro");
                if (mensaje) {
                    mensaje.textContent = data.message;
                    mensaje.style.color = data.status === "success" ? "green" : "red";
                }

                if (data.status === "success") {
                    alert("✅ " + data.message);
                    setTimeout(() => {
                        container.classList.remove("active"); // Vuelve al login
                    }, 1500);
                } else {
                    alert("⚠️ " + data.message);
                }
            })
            .catch(error => {
                console.error("❌ Error en el registro:", error);
                alert("Ocurrió un error inesperado en el registro.");
            });
        });
    }

    // 🔐 LOGIN DE USUARIO
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function(event) {
            event.preventDefault();

            const formData = new FormData(this);

            fetch("http://localhost/proyecto/principal/procesar_login.php", {
                method: "POST",
                body: formData,
                credentials: "include" // Asegura que se usen las cookies de sesión
            })
            .then(response => response.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeLogin");
                if (mensaje) {
                    mensaje.textContent = data.message;
                    mensaje.style.color = data.status === "success" ? "green" : "red";
                }

                if (data.status === "success") {
                    alert("✅ " + data.message);
                    setTimeout(() => {
                        let destino = "../principal/index.html";

                        switch (parseInt(data.rol)) {
                            case 1:
                                destino = "../Usuarios/index.php";
                                break;
                            case 2:
                                destino = "../Fundacion/index.html";
                                break;
                            case 3:
                                destino = "../Veterinaria/index.html";
                                break;
                        }

                        window.location.href = destino;
                    }, 1500);
                } else {
                    alert("⚠️ " + data.message);
                }
            })
            .catch(error => {
                console.error("❌ Error en el login:", error);
                alert("Ocurrió un error inesperado en el inicio de sesión.");
            });
        });
    }

    // 🔴 CERRAR SESIÓN
    const cerrarSesionBtn = document.getElementById("cerrarSesion");
    if (cerrarSesionBtn) {
        cerrarSesionBtn.addEventListener("click", () => {
            fetch("../principal/logout.php")
            .then(() => {
                window.location.href = "../principal/login.php"; // Redirige al login
            })
            .catch(error => {
                console.error("❌ Error al cerrar sesión:", error);
                alert("Ocurrió un error al cerrar sesión.");
            });
        });
    }
});