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

    // Mostrar formulario de registro
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        Animal animal = new Animal();
        animal.setUsuario(usuario);
        model.addAttribute("animal", animal);
        return "animales/RegistrarAnimal"; // ruta de tu archivo HTML
    }

    // Guardar animal
    @PostMapping("/guardar")
    public String guardarAnimal(@ModelAttribute("animal") Animal animal,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session,
                                Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        animal.setUsuario(usuario);
        String mensaje = animalService.guardarAnimal(animal, file);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("animal", new Animal()); // Para limpiar el formulario
        return "animales/RegistrarAnimal";
    }

    // Mostrar animales registrados (opcional)
    @GetMapping("/lista")
    public String listarAnimales(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        List<Animal> animales = animalService.obtenerTodos();
        model.addAttribute("animales", animales);
        return "animales/ListaAnimales"; // crea esta vista si la necesitas
    }

    // Eliminar animal (opcional)
    @PostMapping("/eliminar/{id}")
    public String eliminarAnimal(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        animalService.eliminarAnimal(id);
        return "redirect:/animales/lista";
    }
}
