package org.abror.service.impl;

import org.abror.domain.enumeration.FileStorageStrategy;
import org.abror.service.FileStorageService;

/**
 * @author Abror
 * @see org.abror.service.impl
 * @since 9/21/2024 7:46 PM
 */
public interface FileSystemStorageService extends FileStorageService {
    /**
     *
     * @return
     */
    @Override
    default FileStorageStrategy strategy() {
        return FileStorageStrategy.FILE_SYSTEM;
    }
}
