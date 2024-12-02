package org.abror.domain;

import jakarta.persistence.*;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.util.Objects;

/**
 * A File
 */
@Entity
@Table(name = "file")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ContentId
    @Column(name = "name")
    private String name;

    @ContentLength
    @Column(name = "content_length")
    private Long contentLength;

    @MimeType
    @Column(name = "mime_type")
    private String mimeType = MimeTypeUtils.ALL_VALUE;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        File file = (File) object;
        return Objects.equals(id, file.id) && Objects.equals(name, file.name) && Objects.equals(contentLength, file.contentLength) && Objects.equals(mimeType, file.mimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentLength, mimeType);
    }

    @Override
    public String toString() {
        return "File{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", contentLength=" + contentLength +
            ", mimeType='" + mimeType + '\'' +
            '}';
    }
}
