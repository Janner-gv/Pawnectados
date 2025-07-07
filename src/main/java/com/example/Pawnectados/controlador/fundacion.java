package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fundacion")
public class fundacion {

    /** Rol 3 = Fundación */
    private boolean esFundacion(Usuario u) {
        return u != null && u.getRol() == 3;
    }

    /** Landing de la fundación —> /fundacion/index */
    @GetMapping("/index")
    public String indexFundacion(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }

    /** Lista de los animales que la fundación tiene a cargo —> /fundacion/animales */
    @GetMapping("/animales")
    public String listarAnimales(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }

    /** Formulario para agregar un nuevo animal —> /fundacion/animales/nuevo */
    @GetMapping("/animales/nuevo")
    public String nuevoAnimal(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }

    /** Formulario para editar un animal existente —> /fundacion/animales/editar/{id} */
    @GetMapping("/animales/editar/{id}")
    public String editarAnimal(@PathVariable Long id, HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }

    /** Ver solicitudes de adopción recibidas —> /fundacion/solicitudes */
    @GetMapping("/solicitudes")
    public String verSolicitudes(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }

    /** Ver donaciones recibidas —> /fundacion/donaciones */
    @GetMapping("/donaciones")
    public String verDonaciones(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }

    /** Editar perfil de la fundación —> /fundacion/perfil */
    @GetMapping("/perfil")
    public String editarPerfil(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundacion"
                : "redirect:/login";
    }
}
