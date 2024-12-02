package org.abror.repository;

import org.abror.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<File> {

    /**
     *
     * @param fileName
     * @return
     */
    Optional<File> findFirstByName(String fileName);
}
