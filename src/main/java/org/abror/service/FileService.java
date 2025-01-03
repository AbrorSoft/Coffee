package org.abror.service;

import org.abror.domain.enumeration.FileStorageStrategy;

/**
 * @author Abror
 * @see org.abror.service
 * @since 9/21/2024 6:57 PM
 */
public interface FileService {
    /**
     *
     * @return
     */
    FileStorageStrategy strategy();
}
