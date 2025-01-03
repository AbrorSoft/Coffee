package org.abror.service;

import org.abror.service.dto.FileDTO;

/**
 * @author Abror
 * @see org.abror.service
 * @since 9/21/2024 7:45 PM
 */
public interface FileDownloadService {
    /**
     *
     * @param filename
     * @return
     */
    byte[] downloadFileContent(String filename);

    /**
     *
     * @param filename
     * @return
     */
    FileDTO downloadFile(String filename);
}
