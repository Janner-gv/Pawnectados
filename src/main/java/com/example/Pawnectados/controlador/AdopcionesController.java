package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.FormularioAdopcion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdopcionesController {

    @GetMapping("/adopcion")
    public String mostrarVistaAdopcion() {
        return "adopcion/Adopcion"; // Ruta HTML
    }

    @PostMapping("/procesar-adopcion")
    public String procesarAdopcion(@ModelAttribute FormularioAdopcion form) {
        System.out.println("📋 Nueva solicitud de adopción:");
        System.out.println("Nombre: " + form.getNombre());
        System.out.println("Correo: " + form.getCorreo());
        System.out.println("Teléfono: " + form.getTelefono());
        System.out.println("Animal: " + form.getTipoAnimal()); // Esto será el nombre del animal
        System.out.println("Recursos: " + form.getRecursos());
        System.out.println("Espacio/Tiempo: " + form.getEspacioTiempo());
        System.out.println("Tipo de mascota buscada: " + form.getTipoMascota());

        return "redirect:/adopcion";
    }
}
