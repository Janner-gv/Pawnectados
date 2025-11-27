document.addEventListener("DOMContentLoaded", function () {

    const container = document.getElementById("container");

    // ============================
    //  BOTONES LOGIN / REGISTRO
    // ============================
    const signInBtn = document.getElementById("login");
    const signUpBtn = document.getElementById("register");

    if (signInBtn && signUpBtn) {
        signInBtn.addEventListener("click", () => container.classList.remove("active"));
        signUpBtn.addEventListener("click", () => container.classList.add("active"));
    }

    // ============================
    //  CAMPOS CONDICIONALES
    // ============================
    const tipoUsuarioSelect = document.getElementById("tipoUsuario");

    const camposJuridica = document.getElementById("camposJuridica");
    const camposFundacion = document.getElementById("camposFundacion");
    const camposVeterinaria = document.getElementById("camposVeterinaria");
    const docsEmpresa = document.getElementById("docsEmpresa");

    // Función para quitar required cuando está oculto
    function manejarRequired(div, activo) {
        const inputs = div.querySelectorAll("input, select");
        inputs.forEach(i => {
            if (activo) {
                i.setAttribute("required", "true");
            } else {
                i.removeAttribute("required");
            }
        });
    }

    tipoUsuarioSelect.addEventListener("change", () => {
        const tipo = tipoUsuarioSelect.value;

        // Ocultar todo
        camposJuridica.style.display = "none";
        camposFundacion.style.display = "none";
        camposVeterinaria.style.display = "none";
        docsEmpresa.style.display = "none";

        // Quitar required de todos
        manejarRequired(camposJuridica, false);
        manejarRequired(camposFundacion, false);
        manejarRequired(camposVeterinaria, false);
        manejarRequired(docsEmpresa, false);

        // Mostrar los campos correspondientes
        if (tipo === "Juridica") {
            camposJuridica.style.display = "block";
            docsEmpresa.style.display = "block";
            manejarRequired(camposJuridica, true);
            manejarRequired(docsEmpresa, true);
        } else if (tipo === "FUNDACION") {
            camposFundacion.style.display = "block";
            docsEmpresa.style.display = "block";
            manejarRequired(camposFundacion, true);
            manejarRequired(docsEmpresa, true);
        } else if (tipo === "VETERINARIA") {
            camposVeterinaria.style.display = "block";
            docsEmpresa.style.display = "block";
            manejarRequired(camposVeterinaria, true);
            manejarRequired(docsEmpresa, true);
        }
    });

    // ============================
    //           REGISTRO
    // ============================
    const registrarForm = document.getElementById("registrarForm");
    if (registrarForm) {
        registrarForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const formData = new FormData(this);

            fetch("/api/registro", {
                method: "POST",
                body: formData
            })
            .then(res => res.json())
            .then(data => {
                const mensaje = document.getElementById("mensajeRegistro");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {
                    registrarForm.reset();
                    camposJuridica.style.display = "none";
                    camposFundacion.style.display = "none";
                    camposVeterinaria.style.display = "none";
                    docsEmpresa.style.display = "none";

                    setTimeout(() => container.classList.remove("active"), 1500);
                }
            })
            .catch(err => console.error("Error en registro:", err));
        });
    }

    // ============================
    //           LOGIN
    // ============================
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const formData = new FormData(this);
            const params = new URLSearchParams();
            formData.forEach((value, key) => params.append(key, value));

            fetch("/api/login", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params
            })
            .then(res => res.json())
            .then(data => {

                const mensaje = document.getElementById("mensajeLogin");
                mensaje.textContent = data.message;
                mensaje.style.color = data.status === "success" ? "green" : "red";

                if (data.status === "success") {

                    // Verificación pendiente
                    if (data.verificacion === "pendiente") {
                        setTimeout(() => window.location.href = "/pendiente-verificacion", 600);
                        return;
                    }

                    // Redirección por rol
                    setTimeout(() => {
                        let destino = "/";

                        switch (data.rol) {
                            case "ROLE_USER":
                                destino = "/usuarios/usuarios";
                                break;
                            case "ROLE_PERSONA_JURIDICA":
                                destino = "/usuarios/usuarios";
                                break;
                            case "ROLE_FUNDACION":
                                destino = "/fundacion/fundaciones";
                                break;
                            case "ROLE_VETERINARIA":
                                destino = "/veterinaria/veterinaria";
                                break;
                            case "ROLE_ADMIN":
                                destino = "/admin/Dashboard";
                                break;
                            default:
                                destino = "/Home";
                        }

                        window.location.href = destino;

                    }, 500);
                }
            })
            .catch(err => console.error("Error login:", err));
        });
    }

});
