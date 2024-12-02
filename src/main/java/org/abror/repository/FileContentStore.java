package org.abror.repository;

import org.abror.domain.File;
import org.springframework.content.commons.store.ContentStore;

public interface FileContentStore extends ContentStore<File, Long> {
}
