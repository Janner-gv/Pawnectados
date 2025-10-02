package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Animal;
import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.services.AnimalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Mostrar formulario de registro de animal
    @GetMapping("/RegistrarAnimal")
    public String mostrarFormularioRegistro(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        Animal animal = new Animal();
        animal.setUsuario(usuario);
        model.addAttribute("animal", animal);
        return "usuarios/RegistrarAnimal";
    }

    // Guardar un animal
    @PostMapping("/guardar")
    public String guardarAnimal(@ModelAttribute("animal") Animal animal,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session,
                                Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        animal.setUsuario(usuario);
        String mensaje = animalService.guardarAnimal(animal, file);

        model.addAttribute("mensajeExito", mensaje);
        model.addAttribute("animal", new Animal());
        return "usuarios/RegistrarAnimal";
    }

    // Listar animales del usuario
    @GetMapping("/lista")
    public String listarAnimales(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        // ✅ Usamos Long para ID
        List<Animal> animales = animalService.obtenerPorUsuario(usuario.getId_usuario());
        model.addAttribute("animales", animales);
        return "animales/ListaAnimales";
    }

    // Eliminar animal por ID
    @PostMapping("/eliminar/{id}")
    public String eliminarAnimal(@PathVariable Long id, HttpSession session) {  // Cambiado a Long
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        animalService.eliminarAnimal(id);
        return "redirect:/animales/lista";
    }
}
