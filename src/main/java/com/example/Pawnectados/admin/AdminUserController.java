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
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DonacionService donacionService;

    @Autowired
    private AnimalService animalService;

    private boolean esAdmin(Usuario usuario) {
        return usuario != null && usuario.getRol() == 4;
    }

    // 🟢 DASHBOARD
    @GetMapping("/Dashboard")
    public String mostrarDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(usuario)) return "redirect:/login";

        model.addAttribute("totalUsuarios", usuarioService.contarUsuariosPorRol(1));
        model.addAttribute("totalFundaciones", usuarioService.contarUsuariosPorRol(2));
        model.addAttribute("totalVeterinarias", usuarioService.contarUsuariosPorRol(3));
        model.addAttribute("totalDonaciones", donacionService.contarDonaciones());
        model.addAttribute("totalAnimales", animalService.contarAnimales());

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
        if (u == null) return "redirect:/admin/usuarios";

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

    // 🟢 GRÁFICA (por si usas Chart.js)
    @GetMapping("/animales/por-mes")
    @ResponseBody
    public Map<String, Long> obtenerAnimalesPorMes(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(usuario)) return Map.of();
        return animalService.obtenerAnimalesPorMes();
    }
}