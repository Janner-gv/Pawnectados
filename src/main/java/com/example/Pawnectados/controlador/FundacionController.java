package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fundacion")
public class FundacionController {

    private boolean esFundacion(Usuario u) {
        return u != null && u.getRol() == 2;
    }

    // ✅ Página principal para la fundación — fundacion/fundaciones.html
    @GetMapping("/fundaciones") // 👈 CORREGIDO
    public String fundaciones(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundaciones"
                : "redirect:/login";
    }
    // ✅ Página: adopta.html
    @GetMapping("/adopta")
    public String adopta(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/adopta"
                : "redirect:/login";
    }

    // ✅ Página: apadrina.html
    @GetMapping("/apadrina")
    public String apadrina(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/apadrina"
                : "redirect:/login";
    }

    // ✅ Página: apoyanos.html
    @GetMapping("/apoyanos")
    public String apoyanos(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/apoyanos"
                : "redirect:/login";
    }

    // ✅ Página: hogares.html
    @GetMapping("/hogares")
    public String hogares(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/hogares"
                : "redirect:/login";
    }

    // ✅ Página: blog.html
    @GetMapping("/blog")
    public String blog(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/blog"
                : "redirect:/login";
    }

    // ✅ Página: contactenos.html
    @GetMapping("/contactenos")
    public String contactenos(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/contactenos"
                : "redirect:/login";
    }

    // Puedes agregar más rutas si tienes nuevos HTML en esa carpeta.
}
