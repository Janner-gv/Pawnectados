package com.example.Pawnectados.models;

public class FormularioAdopcion {
    private String nombre;
    private String correo;
    private String telefono;
    private String tipoAnimal;
    private String recursos;
    private String espacioTiempo;
    private String tipoMascota;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoAnimal() { return tipoAnimal; }
    public void setTipoAnimal(String tipoAnimal) { this.tipoAnimal = tipoAnimal; }

    public String getRecursos() { return recursos; }
    public void setRecursos(String recursos) { this.recursos = recursos; }

    public String getEspacioTiempo() { return espacioTiempo; }
    public void setEspacioTiempo(String espacioTiempo) { this.espacioTiempo = espacioTiempo; }

    public String getTipoMascota() { return tipoMascota; }
    public void setTipoMascota(String tipoMascota) { this.tipoMascota = tipoMascota; }
}
