package com.example.jponline_backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "http://localhost:4200") // Permitir chamadas do frontend Angular
public class ImageUploadController {

    private static final String UPLOAD_DIR = "src/main/resources/static/imgs/";

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma imagem enviada.");
        }

        try {
            // Cria o diretório, se não existir
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            // Define o caminho do arquivo
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, file.getBytes());

            // Retorna a URL da imagem (acessível pelo Angular)
            String imageUrl = "" + fileName;
            return ResponseEntity.ok().body("{\"imageUrl\": \"" + imageUrl + "\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagem.");
        }
    }
}
