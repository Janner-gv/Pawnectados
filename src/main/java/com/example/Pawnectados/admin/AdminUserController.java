package com.example.Pawnectados.admin;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UsuarioService usuarioService;

    // ✅ Mostrar formulario de registro desde el dashboard
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || usuario.getRol() != 4) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "admin/CrearUsuario";
    }

    // ✅ Registrar nuevo usuario desde formulario
    @PostMapping("/registrar")
    @ResponseBody
    public ResponseEntity<?> registrarUsuarioDesdeAdmin(@ModelAttribute Usuario usuario) {
        String mensaje = usuarioService.registrarUsuario(usuario);
        Map<String, Object> res = new HashMap<>();
        res.put("status", mensaje.equals("Usuario registrado") ? "success" : "error");
        res.put("message", mensaje);
        return ResponseEntity.ok(res);
    }

    // ✅ Mostrar todos los usuarios registrados
    @GetMapping("/usuarios")
    public String listarUsuarios(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || usuario.getRol() != 4) {
            return "redirect:/login";
        }
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        model.addAttribute("usuariosLista", usuarios);
        return "admin/Usuarios";
    }
}
