package org.abror.service.mapper;

import org.abror.domain.File;
import org.abror.service.dto.FileDTO;
import org.mapstruct.Mapper;

/**
 * @author Nurislom
 * @see org.abror.service.mapper
 * @since 9/21/2024 7:29 PM
 */
@Mapper(componentModel = "spring")
public interface FileMapper extends EntityMapper<FileDTO, File> {

    /**
     *
     * @param file
     * @param content
     * @return
     */
    FileDTO toDto(File file, byte[] content);
}
