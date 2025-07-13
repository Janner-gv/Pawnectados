package com.example.Pawnectados.admin;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/reportes")
public class AdminReporteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public void exportarUsuarios(@RequestParam(required = false) String nombre,
                                 @RequestParam(required = false) Integer rol,
                                 HttpSession session,
                                 HttpServletResponse response) throws IOException {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || usuario.getRol() != 4) {
            response.sendRedirect("/login");
            return;
        }

        List<Usuario> usuarios = usuarioRepository.findAll().stream()
                .filter(u -> nombre == null || u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(u -> rol == null || u.getRol() == rol)
                .toList();

        // Configurar encabezado de respuesta para Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Usuarios");

        String[] columnas = {"ID", "Nombre", "Correo", "Teléfono", "Dirección", "Rol"};

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
        }

        int rowIdx = 1;
        for (Usuario u : usuarios) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(u.getId());
            row.createCell(1).setCellValue(u.getNombre());
            row.createCell(2).setCellValue(u.getEmail());
            row.createCell(3).setCellValue(u.getTelefono());
            row.createCell(4).setCellValue(u.getDireccion());
            row.createCell(5).setCellValue(u.getRol());
        }

        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
