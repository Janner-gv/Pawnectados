package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Animal;
import com.example.Pawnectados.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private boolean esUsuario(Usuario u) {
        return u != null && u.getRol() == 1;
    }

    /** Página principal después del login (usuarios.html) */
    @GetMapping("/usuarios")
    public String paginaPrincipal(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/usuarios"
                : "redirect:/login";
    }

    /** Dashboard */
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/dashboard"
                : "redirect:/login";
    }
    @GetMapping("/RegistrarAnimal")
    public String registrarAnimal(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || usuario.getRol() != 1) return "redirect:/login";

        model.addAttribute("animal", new Animal());
        return "usuarios/RegistrarAnimal"; // ← asegúrate que esta ruta coincida
    }


    /** Formulario de adopción */
    @GetMapping("/adopcion")
    public String formularioAdopcion(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "adopcion/Adopcion"
                : "redirect:/login";
    }

    /** Donar */

    @GetMapping("/Fundacion")
    public String donaciones(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "Fundacion/fundaciones"
                : "redirect:/login";
    }

    /** Animales registrados por el usuario */
    @GetMapping("/mis-animales")
    public String misAnimales(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/misanimales"
                : "redirect:/login";
    }

    /** Páginas informativas del footer */
    @GetMapping("/terminos")
    public String terminos(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/Terminos"
                : "redirect:/login";
    }

    @GetMapping("/politica-privacidad")
    public String privacidad(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/Politica"
                : "redirect:/login";
    }

    @GetMapping("/contacto")
    public String contacto(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/Contacto"
                : "redirect:/login";
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros(HttpSession session) {
        return esUsuario((Usuario) session.getAttribute("usuario"))
                ? "usuarios/SobreNosotros"
                : "redirect:/login";
    }
}
