package com.example.ServiceImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Service.FileService;

@Service
public class FileServiceImpl
        implements FileService {

    private final Path uploadDirectory =
            Paths.get("uploads");

    @Override
    public String uploadFile(
            MultipartFile file)
            throws IOException {

        if (file == null ||
                file.isEmpty()) {

            throw new RuntimeException(
                    "File is empty");
        }

        Files.createDirectories(
                uploadDirectory);

        String fileName =
                file.getOriginalFilename();

        if (fileName == null ||
                fileName.isBlank()) {

            throw new RuntimeException(
                    "Invalid file name");
        }

        Path filePath =
                uploadDirectory
                        .resolve(fileName)
                        .normalize();

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption
                        .REPLACE_EXISTING
        );

        return fileName;
    }

    @Override
    public Resource downloadFile(
            String fileName) {

        try {

            Path filePath =
                    uploadDirectory
                            .resolve(fileName)
                            .normalize();

            Resource resource =
                    new UrlResource(
                            filePath.toUri());

            if (!resource.exists()) {

                throw new RuntimeException(
                        "File not found");
            }

            return resource;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to download file");
        }
    }
}