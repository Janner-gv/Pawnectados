package com.example.Pawnectados.controlador;

import com.example.Pawnectados.FormularioAdopcion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Adopciones {

    @GetMapping("/adopcion")
    public String mostrarVistaAdopcion() {
        return "adopcion/Adopcion"; // Carga el HTML de adopción
    }

    @PostMapping("/procesar-adopcion")
    public String procesarAdopcion(@ModelAttribute FormularioAdopcion form) {
        System.out.println("📋 Nueva solicitud de adopción:");
        System.out.println("Nombre: " + form.getNombre());
        System.out.println("Correo: " + form.getCorreo());
        System.out.println("Teléfono: " + form.getTelefono());
        System.out.println("Animal: " + form.getTipoAnimal());
        System.out.println("Recursos: " + form.getRecursos());
        System.out.println("Espacio/Tiempo: " + form.getEspacioTiempo());
        System.out.println("Tipo de mascota buscada: " + form.getTipoMascota());

        return "redirect:/adopcion";
    }
}
