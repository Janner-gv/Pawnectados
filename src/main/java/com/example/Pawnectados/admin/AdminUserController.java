package com.example.Pawnectados.admin;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.services.AnimalService;
import com.example.Pawnectados.services.DonacionService;
import com.example.Pawnectados.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DonacionService donacionService;

    @Autowired
    private AnimalService animalService;

    // 🔹 Verifica si el usuario tiene rol ADMIN
    private boolean esAdmin(Usuario usuario) {
        if (usuario == null || usuario.getRoles() == null) return false;
        return usuario.getRoles().stream()
                .anyMatch(r -> r.getNombre().equalsIgnoreCase("ROLE_ADMIN"));
    }

    // 🟢 DASHBOARD
    @GetMapping("/Dashboard")
    public String mostrarDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(usuario)) return "redirect:/login";

        // Todos los conteos como Long
        Long totalUsuarios = usuarioService.contarUsuariosPorRol("ROLE_USER");
        Long totalFundaciones = usuarioService.contarUsuariosPorRol("ROLE_FUNDACION");
        Long totalVeterinarias = usuarioService.contarUsuariosPorRol("ROLE_VETERINARIA");
        Long totalDonaciones = donacionService.contarDonaciones();
        long totalAnimales = animalService.contarAnimales();

        model.addAttribute("totalUsuarios", totalUsuarios);
        model.addAttribute("totalFundaciones", totalFundaciones);
        model.addAttribute("totalVeterinarias", totalVeterinarias);
        model.addAttribute("totalDonaciones", totalDonaciones);
        model.addAttribute("totalAnimales", totalAnimales);

        return "admin/Dashboard";
    }

    // 🟢 LISTADO DE USUARIOS
    @GetMapping("/Usuarios")
    public String listarUsuarios(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(usuario)) return "redirect:/login";

        List<Usuario> usuarios = usuarioService.obtenerTodos();
        model.addAttribute("usuariosLista", usuarios);
        return "admin/GestionUsuarios";
    }

    // 🟢 FORMULARIO DE EDICIÓN
    @GetMapping("/usuarios/{id}/editar")
    public String editarUsuario(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(usuario)) return "redirect:/login";

        Usuario u = usuarioService.obtenerPorId(id);
        if (u == null) return "redirect:/admin/Usuarios";

        model.addAttribute("usuario", u);
        return "admin/EditarUsuarios";
    }

    // 🟢 PROCESAR ACTUALIZACIÓN
    @PostMapping("/usuarios/{id}/actualizar")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute Usuario usuarioActualizado, HttpSession session) {
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(admin)) return "redirect:/login";

        usuarioService.actualizarUsuario(id, usuarioActualizado);
        return "redirect:/admin/Usuarios";
    }

    // 🟢 ELIMINAR USUARIO
    @PostMapping("/usuarios/{id}/eliminar")
    public String eliminarUsuario(@PathVariable Long id, HttpSession session) {
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(admin)) return "redirect:/login";

        usuarioService.eliminarUsuario(id);
        return "redirect:/admin/Usuarios";
    }
}
