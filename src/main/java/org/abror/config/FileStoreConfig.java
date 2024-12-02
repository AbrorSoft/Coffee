package org.abror.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration class for setting up file storage in the file system.
 * <p>
 * This class configures the file system root for storing files and
 * provides a {@link FileSystemResourceLoader} for accessing resources in the file system.
 * The class also ensures that the necessary directories are created with appropriate
 * permissions.
 * </p>
 */
@Slf4j
@Configuration
@EnableFilesystemStores
@AllArgsConstructor
public class FileStoreConfig {

    private final StorageProperties properties;

    /**
     * Creates a {@link File} representing the root directory for file storage.
     * If the directory specified by {@code properties.getFileStorageBasePath()} does not exist,
     * it will be created with appropriate permissions based on the operating system.
     *
     * @return the root directory as a {@link File} instance.
     */
    @Bean
    public File fileSystemRoot() {
        log.warn("File content path for storing files : {}", properties.getFileStorageBasePath());
        final Path path = Paths.get(properties.getFileStorageBasePath());

        if (!Files.exists(path)) {
            mkdir(path);
        }
        return new File(properties.getFileStorageBasePath());
    }

    /**
     * Creates a {@link FileSystemResourceLoader} for loading resources from the file system.
     * Uses the root directory provided by {@link #fileSystemRoot()}.
     *
     * @return a {@link FileSystemResourceLoader} pointing to the file system root.
     */
    @Bean
    public FileSystemResourceLoader fileSystemResourceLoader() {
        log.warn("File content absolute root path : {}", fileSystemRoot().getAbsolutePath());
        return new FileSystemResourceLoader(fileSystemRoot().getAbsolutePath());
    }

    /**
     * Creates the directory structure specified by the given {@code path}, if it does not exist.
     * <p>
     * If the file system supports POSIX file attributes, the directory is created with full permissions
     * for the owner, group, and others. If POSIX attributes are not supported, the directory is created
     * with default permissions.
     * </p>
     *
     * @param path the directory path to be created if it does not already exist.
     */
    private void mkdir(Path path) {
        log.warn("Path not exists, mkdir all : {}", properties.getFileStorageBasePath());
        if (FileSystems.getDefault().supportedFileAttributeViews().contains("posix")) {
            Set<PosixFilePermission> fullPermission = new HashSet<>();
            fullPermission.add(PosixFilePermission.OWNER_EXECUTE);
            fullPermission.add(PosixFilePermission.OWNER_READ);
            fullPermission.add(PosixFilePermission.OWNER_WRITE);

            fullPermission.add(PosixFilePermission.GROUP_EXECUTE);
            fullPermission.add(PosixFilePermission.GROUP_READ);
            fullPermission.add(PosixFilePermission.GROUP_WRITE);

            fullPermission.add(PosixFilePermission.OTHERS_EXECUTE);
            fullPermission.add(PosixFilePermission.OTHERS_READ);
            fullPermission.add(PosixFilePermission.OTHERS_WRITE);

            tryMkdir(path, fullPermission);
        } else {
            new File(properties.getFileStorageBasePath()).mkdir();
        }
    }

    /**
     * Attempts to create directories for the specified {@code path} with the given POSIX permissions.
     * If an error occurs, it logs the exception message.
     *
     * @param path           the directory path to create.
     * @param fullPermission the set of POSIX file permissions to apply to the directory.
     */
    private void tryMkdir(Path path, Set<PosixFilePermission> fullPermission) {
        try {
            Files.createDirectories(path, PosixFilePermissions.asFileAttribute(fullPermission));
            outputLS(path);
            Files.setPosixFilePermissions(path, fullPermission);
            outputLS(path);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Could not create path : " + e.getLocalizedMessage());
        }
    }

    /**
     * Outputs the contents of the parent directory of the specified {@code path} to the console.
     * <p>
     * Executes a system command to list files and their attributes within the parent directory
     * of the specified path. Useful for debugging purposes to verify directory permissions and contents.
     * </p>
     *
     * @param path the path whose parent directory will be listed.
     * @throws IOException if an I/O error occurs while listing directory contents.
     */
    private static void outputLS(Path path) throws IOException {
        System.out.println(
                new java.util.Scanner(Runtime.getRuntime()
                        .exec("ls -alt " + path.toAbsolutePath() + "/..")
                        .getInputStream())
                        .useDelimiter("\\A")
                        .next()
        );
    }
}
