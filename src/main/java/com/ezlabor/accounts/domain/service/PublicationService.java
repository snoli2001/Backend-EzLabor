package com.ezlabor.accounts.domain.service;

import com.ezlabor.accounts.domain.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PublicationService {
    Page<Publication> getAllPublicationsByUserId(Long userId, Pageable pageable);
    Publication getPublicationByIdAndUserId(Long publicationId, Long userId);
    Publication createPublication(Long userId, Publication publication);
    Publication updatePublication(Long userId, Long publicationId, Publication publicationRequest);
    ResponseEntity<?> deletePublication(Long userId, Long publicationId);
}
