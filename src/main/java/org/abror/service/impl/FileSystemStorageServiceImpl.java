package org.abror.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.abror.repository.FileContentStore;
import org.abror.repository.FileRepository;
import org.abror.service.dto.FileDTO;
import org.abror.service.mapper.FileMapper;
import org.abror.service.mapper.FileSystemMapper;
import org.abror.web.rest.errors.BadRequestAlertException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Nurislom
 * @see org.abror.service.impl
 * @since 9/21/2024 6:58 PM
 */
@Slf4j
@Service
public class FileSystemStorageServiceImpl implements FileSystemStorageService {

    private final FileMapper fileMapper;
    private final FileRepository fileRepository;
    private final FileSystemMapper fileSystemMapper;
    private final FileContentStore fileContentStore;

    public FileSystemStorageServiceImpl(FileMapper fileMapper,
                                        FileRepository fileRepository,
                                        FileSystemMapper fileSystemMapper,
                                        FileContentStore fileContentStore) {

        this.fileMapper = fileMapper;
        this.fileRepository = fileRepository;
        this.fileSystemMapper = fileSystemMapper;
        this.fileContentStore = fileContentStore;
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public FileDTO uploadFile(File file) {
        log.debug("Request to upload file: {}", file.getName());
        return uploadFile(file.getName(), readAllBytes(file));
    }

    private byte[] readAllBytes(File file) {
        byte[] byteArray = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return byteArray;
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public FileDTO uploadFile(MultipartFile file) {
        log.debug("Request to upload file: {}", file.getName());
        return tryUploadFile(file);
    }

    private FileDTO tryUploadFile(MultipartFile file) {
        try {
            var fileDto = fileSystemMapper.toFileDto(file);
            return uploadFile(fileDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param fileName
     * @param fileBytes
     * @return
     */
    @Override
    public FileDTO uploadFile(String fileName, byte[] fileBytes) {
        log.debug("Request to upload file: {}", fileName);
        var fileDto = fileSystemMapper.toFileDto(fileName, fileBytes);
        return uploadFile(fileDto);
    }

    /**
     *
     * @param fileDTO
     * @return
     */
    @Override
    public FileDTO uploadFile(FileDTO fileDTO) {
        log.debug("Request to upload fileDto: {}", fileDTO);

        if (fileDTO.getMimeType() == null) {
            fileDTO.setMimeType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        }

        var file = fileMapper.toEntity(fileDTO);
        uploadFileContentStore(fileDTO, file);
        file = fileRepository.save(file);

        return fileMapper.toDto(file);
    }

    private void uploadFileContentStore(FileDTO fileDTO, org.abror.domain.File file) {
        log.debug("Request to upload file to FileContentStore: filename: {}", fileDTO.getName());
        fileContentStore.setContent(file, new ByteArrayInputStream(fileDTO.getContent()));
    }

    /**
     *
     * @param filename
     * @return
     */
    @Override
    public byte[] downloadFileContent(String filename) {
        log.debug("Request to download file content : {}", filename);
        var file = downloadFile(filename);
        return file.getContent();
    }

    /**
     *
     * @param filename
     * @return
     */
    @Override
    public FileDTO downloadFile(String filename) {
        log.debug("Request to download file : {}", filename);
        var optionalFile = fileRepository.findFirstByName(filename);

        if (optionalFile.isEmpty()) {
            throw new BadRequestAlertException("File not found", "File", "notFound");
        }
        return tryDownloadFile(optionalFile.orElseThrow());
    }

    /**
     *
     * @param file
     * @return
     */
    private FileDTO tryDownloadFile(org.abror.domain.File file) {
        try {
            InputStream content = fileContentStore.getContent(file);
            if (content == null) {
                throw new BadRequestAlertException("File not found", "File", "notFound");
            }
            return fileMapper.toDto(file, IOUtils.toByteArray(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
