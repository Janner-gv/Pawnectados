package com.example.Pawnectados.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/veterinaria")
public class VeterinariaController {

    private boolean esVeterinaria(Authentication auth) {
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_VETERINARIA"));
    }

    /** Página principal veterinaria */
    @GetMapping("/index")
    public String paginaPrincipal(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/index" : "redirect:/login";
    }

    /** Información básica */
    @GetMapping("/quienes-somos")
    public String quienesSomos(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/quienes-somos" : "redirect:/login";
    }

    @GetMapping("/equipo")
    public String equipoMedico(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/equipo" : "redirect:/login";
    }

    @GetMapping("/instalaciones")
    public String instalaciones(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/instalaciones" : "redirect:/login";
    }

    @GetMapping("/ubicacion")
    public String ubicacion(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/ubicacion" : "redirect:/login";
    }

    @GetMapping("/contacto")
    public String contacto(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/contacto" : "redirect:/login";
    }

    /** Servicios */
    @GetMapping("/servicios")
    public String servicios(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/servicios" : "redirect:/login";
    }

    /** Funcionalidades interactivas */
    @GetMapping("/citas")
    public String agendamiento(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/citas" : "redirect:/login";
    }

    @GetMapping("/emergencias")
    public String emergencias(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/emergencias" : "redirect:/login";
    }

    @GetMapping("/historial")
    public String historialMascotas(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/historial" : "redirect:/login";
    }

    /** Tienda (opcional) */
    @GetMapping("/tienda")
    public String tienda(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/tienda" : "redirect:/login";
    }

    /** Blog y noticias */
    @GetMapping("/blog")
    public String blog(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/blog" : "redirect:/login";
    }

    @GetMapping("/casos-exito")
    public String casosExito(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/casos-exito" : "redirect:/login";
    }

    @GetMapping("/eventos")
    public String eventos(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/eventos" : "redirect:/login";
    }

    @GetMapping("/adopciones")
    public String adopciones(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/adopciones" : "redirect:/login";
    }

    /** Comunidad y confianza */
    @GetMapping("/testimonios")
    public String testimonios(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/testimonios" : "redirect:/login";
    }

    @GetMapping("/galeria")
    public String galeriaMascotas(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/galeria" : "redirect:/login";
    }

    /** Extras */
    @GetMapping("/faq")
    public String faq(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/faq" : "redirect:/login";
    }

    @GetMapping("/politicas")
    public String politicas(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/politicas" : "redirect:/login";
    }

    @GetMapping("/boletin")
    public String boletin(Authentication auth) {
        return esVeterinaria(auth) ? "veterinaria/boletin" : "redirect:/login";
    }
}
