package org.abror.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.abror.service.FileStorageService;
import org.abror.service.dto.FileDTO;
import org.abror.web.rest.errors.BadRequestAlertException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileResource {

    private final FileStorageService fileStorageService;

    public FileResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /**
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        log.debug("REST request to upload file.");
        if (multipartFile.isEmpty()) {
            throw new BadRequestAlertException("File null", "File", "fileNull");
        }
        FileDTO result = fileStorageService.uploadFile(multipartFile);
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @param filename
     * @return
     */
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        // Load file as Resource
        FileDTO file = fileStorageService.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getContent()));
    }
}
