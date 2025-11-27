package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.*;
import com.example.Pawnectados.repositorios.*;
import com.example.Pawnectados.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestAttributes;

import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AuthoController {

    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private RolRepository rolRepo;
    @Autowired private PersonaJuridicaService personaJuridicaService;
    @Autowired private FundacionService fundacionService;
    @Autowired private VeterinariaService veterinariaService;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    private final String UPLOAD_DIR = "uploads/"; // Carpeta para guardar documentos

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam String email, @RequestParam String password){
        Map<String,Object> response = new HashMap<>();
        Optional<Usuario> usuarioOpt = usuarioRepo.findByEmail(email);

        if(usuarioOpt.isEmpty()){
            response.put("status","error");
            response.put("message","Usuario no encontrado");
            return response;
        }

        Usuario u = usuarioOpt.get();
        if(!passwordEncoder.matches(password,u.getPassword())){
            response.put("status","error");
            response.put("message","Contraseña incorrecta");
            return response;
        }

        // Autenticación Spring Security
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Rol r: u.getRoles()) authorities.add(new SimpleGrantedAuthority(r.getNombre()));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(u.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT",
                SecurityContextHolder.getContext(), RequestAttributes.SCOPE_SESSION);
        RequestContextHolder.currentRequestAttributes().setAttribute("usuario", u, RequestAttributes.SCOPE_SESSION);

        String rol = u.getRoles().stream().map(Rol::getNombre).findFirst().orElse("ROLE_USER");
        String verificacion = "aprobado";

        switch(rol){
            case "ROLE_PERSONA_JURIDICA" ->
                    verificacion = personaJuridicaService.obtenerPorUsuarioId(u.getId_usuario())
                            .map(p -> p.getEstadoVerificacion()==EstadoVerificacion.PENDIENTE ? "pendiente":"aprobado")
                            .orElse("pendiente");
            case "ROLE_FUNDACION" ->
                    verificacion = fundacionService.obtenerPorUsuarioId(u.getId_usuario())
                            .map(f -> f.getEstadoVerificacion()==EstadoVerificacion.PENDIENTE ? "pendiente":"aprobado")
                            .orElse("pendiente");
            case "ROLE_VETERINARIA" ->
                    verificacion = veterinariaService.obtenerPorUsuarioId(u.getId_usuario())
                            .map(v -> v.getEstadoVerificacion()==EstadoVerificacion.PENDIENTE ? "pendiente":"aprobado")
                            .orElse("pendiente");
        }

        response.put("status","success");
        response.put("message","Ingreso exitoso");
        response.put("rol",rol);
        response.put("verificacion",verificacion);
        return response;
    }

    // ================= REGISTRO =================
    @PostMapping("/registro")
    public Map<String,Object> registrar(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required=false) String telefono,
            @RequestParam(required=false) String direccion,
            @RequestParam String tipoUsuario,
            @RequestParam(required=false) String razonSocial,
            @RequestParam(required=false) String nit,
            @RequestParam(required=false) MultipartFile documento,
            @RequestParam(required=false) String mision,
            @RequestParam(required=false) String paginaWeb,
            @RequestParam(required=false) String nombreClinica,
            @RequestParam(required=false) String licencia,
            @RequestParam(required=false) String servicios,
            @RequestParam(required=false) String horarios,
            @RequestParam(required=false) String direccionVeterinaria,
            @RequestParam(required=false) String telefonoVeterinaria
    ) throws Exception {
        Map<String,Object> response = new HashMap<>();

        if(usuarioRepo.existsByEmail(email)){
            response.put("status","error");
            response.put("message","Correo ya registrado");
            return response;
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setDireccion(direccion);
        u.setPassword(passwordEncoder.encode(password));

        // Asignar rol
        String rolName = switch(tipoUsuario.toUpperCase()){
            case "JURIDICA" -> "ROLE_PERSONA_JURIDICA";
            case "FUNDACION" -> "ROLE_FUNDACION";
            case "VETERINARIA" -> "ROLE_VETERINARIA";
            default -> "ROLE_USER";
        };

        Rol rol = rolRepo.findByNombre(rolName)
                .orElseGet(() -> {
                    Rol r = new Rol();
                    r.setNombre(rolName);
                    return rolRepo.save(r);
                });

        u.setRoles(Set.of(rol));
        usuarioRepo.save(u);

        // Guardar archivo si existe
        if(documento != null && !documento.isEmpty()){
            File carpeta = new File(UPLOAD_DIR);
            if(!carpeta.exists()) carpeta.mkdirs();
            String ruta = UPLOAD_DIR + UUID.randomUUID() + "_" + documento.getOriginalFilename();
            documento.transferTo(new File(ruta));
        }

        // Registro específico
        switch(rolName){
            case "ROLE_PERSONA_JURIDICA" -> {
                PersonaJuridica pj = new PersonaJuridica();
                pj.setUsuario(u);
                pj.setEstadoVerificacion(EstadoVerificacion.PENDIENTE);
                pj.setRazonSocial(razonSocial);
                pj.setNit(nit);
                pj.setDireccion(direccion);
                pj.setTelefono(telefono);
                personaJuridicaService.registrarPersonaJuridica(pj);
            }
            case "ROLE_FUNDACION" -> {
                fundacionService.registrarFundacion(u,
                        razonSocial != null ? razonSocial : "",
                        telefono != null ? telefono : "",
                        mision != null ? mision : "",
                        paginaWeb != null ? paginaWeb : ""
                );
            }
            case "ROLE_VETERINARIA" -> {
                veterinariaService.registrarVeterinaria(
                        u,
                        nombreClinica != null ? nombreClinica : "",
                        servicios != null ? servicios : "Servicios iniciales",
                        horarios != null ? horarios : "Horarios iniciales",
                        licencia != null ? licencia : "",
                        direccionVeterinaria != null ? direccionVeterinaria : "",
                        telefonoVeterinaria != null ? telefonoVeterinaria : ""
                );
            }
        }

        response.put("status","success");
        response.put("message","Registrado correctamente");
        response.put("rol_asignado",rolName);
        return response;
    }

    // ================= LOGOUT =================
    @PostMapping("/logout")
    public Map<String,Object> logout(HttpSession session){
        session.invalidate();
        SecurityContextHolder.clearContext();
        return Map.of("status","success","message","Sesión cerrada");
    }
}
