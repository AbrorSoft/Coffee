package org.abror.service;

import java.io.File;
import org.abror.service.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Abror
 * @see org.abror.service
 * @since 9/21/2024 6:57 PM
 */
public interface FileUploadService {
    /**
     *
     * @param file
     * @return
     */
    FileDTO uploadFile(File file);

    /**
     *
     * @param file
     * @return
     */
    FileDTO uploadFile(MultipartFile file);

    /**
     *
     * @param fileName
     * @param fileBytes
     * @return
     */
    FileDTO uploadFile(String fileName, byte[] fileBytes);

    /**
     *
     * @param fileDTO
     * @return
     */
    FileDTO uploadFile(FileDTO fileDTO);
}
