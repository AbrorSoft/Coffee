package org.abror.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author Abror
 * @see org.abror.service.dto
 * @since 9/21/2024 7:03 PM
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private Long id;

    private String name;

    @JsonIgnore
    private byte[] content;

    private Long contentLength;

    private String mimeType;

    public FileDTO withId(Long id) {
        this.id = id;
        return this;
    }

    public FileDTO withName(String name) {
        this.name = name;
        return this;
    }

    public FileDTO withContent(byte[] content) {
        this.content = content;
        return this;
    }

    public FileDTO withContentLength(Long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    public FileDTO withMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }
}
