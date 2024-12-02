package org.abror.service.mapper;

import org.apache.tika.Tika;
import org.abror.service.dto.FileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Nurislom
 * @see org.abror.service.mapper
 * @since 9/21/2024 7:17 PM
 */
@Service
public class FileSystemMapper {

    private final Tika tika;

    public FileSystemMapper(Tika tika) {
        this.tika = tika;
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public FileDTO toFileDto(MultipartFile file) throws IOException {
        FileDTO fileDTO = new FileDTO();

        fileDTO.setName(file.getName());
        fileDTO.setContent(file.getBytes());
        fileDTO.setMimeType(tika.detect(file.getName()));
        fileDTO.setContentLength(file.getResource().contentLength());

        return fileDTO;
    }

    /**
     *
     * @param filename
     * @param fileBytes
     * @return
     */
    public FileDTO toFileDto(String filename, byte[] fileBytes) {
        FileDTO fileDTO = new FileDTO();

        fileDTO.setName(filename);
        fileDTO.setContent(fileBytes);
        fileDTO.setMimeType(tika.detect(filename));

        return fileDTO;
    }
}
