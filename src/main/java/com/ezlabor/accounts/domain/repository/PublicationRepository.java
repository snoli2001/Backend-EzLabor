package com.ezlabor.accounts.domain.repository;

import com.ezlabor.accounts.domain.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByUserId(Long userId);
    Optional<Publication> findByIdAndUserId(Long publicationId, Long userId);
}
