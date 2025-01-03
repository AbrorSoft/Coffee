package org.abror.config;

import java.io.File;
import lombok.Data;
import org.abror.domain.enumeration.FileStorageStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Abror
 * @see org.abror.config
 * @since 9/21/2024 8:26 PM
 */
@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    /**
     *
     */
    private String fileStorageBasePath = "." + File.separator + "uploads";

    /**
     *
     */
    private FileStorageStrategy strategy = FileStorageStrategy.FILE_SYSTEM;
}
