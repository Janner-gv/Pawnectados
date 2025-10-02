package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Animal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private boolean esUsuario(Authentication auth) {
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
    }

    /** Página principal después del login (usuarios.html) */
    @GetMapping("/usuarios")
    public String paginaPrincipal(Authentication auth) {
        return esUsuario(auth) ? "usuarios/usuarios" : "redirect:/login";
    }

    /** Dashboard */
    @GetMapping("/dashboard1")
    public String dashboard1(Authentication auth) {
        return esUsuario(auth) ? "usuarios/dashboard1" : "redirect:/login";
    }

    /** Registrar Animal */
    @GetMapping("/RegistrarAnimal")
    public String registrarAnimal(Model model, Authentication auth) {
        if (!esUsuario(auth)) return "redirect:/login";

        model.addAttribute("animal", new Animal());
        return "usuarios/RegistrarAnimal";
    }

    /** Formulario de adopción */
    @GetMapping("/adopcion")
    public String formularioAdopcion(Authentication auth) {
        return esUsuario(auth) ? "adopcion/Adopcion" : "redirect:/login";
    }

    /** Donar */
    @GetMapping("/Donaciones")
    public String Donaciones(Authentication auth) {
        return esUsuario(auth) ? "usuarios/Donaciones" : "redirect:/login";
    }

    /** Animales registrados por el usuario */
    @GetMapping("/mis-animales")
    public String misAnimales(Authentication auth) {
        return esUsuario(auth) ? "usuarios/misanimales" : "redirect:/login";
    }

    /** Páginas informativas del footer */
    @GetMapping("/terminos")
    public String terminos(Authentication auth) {
        return esUsuario(auth) ? "usuarios/Terminos" : "redirect:/login";
    }

    @GetMapping("/politica-privacidad")
    public String privacidad(Authentication auth) {
        return esUsuario(auth) ? "usuarios/Politica" : "redirect:/login";
    }

    @GetMapping("/contacto")
    public String contacto(Authentication auth) {
        return esUsuario(auth) ? "usuarios/Contacto" : "redirect:/login";
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros(Authentication auth) {
        return esUsuario(auth) ? "usuarios/SobreNosotros" : "redirect:/login";
    }
}
