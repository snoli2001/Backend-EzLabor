package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.Comment;
import com.ezlabor.accounts.domain.model.Publication;
import com.ezlabor.accounts.domain.repository.CommentRepository;
import com.ezlabor.accounts.domain.repository.PublicationRepository;
import com.ezlabor.accounts.domain.repository.UserRepository;
import com.ezlabor.accounts.domain.service.PublicationService;
import com.ezlabor.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Publication> getAllPublicationsByUserId(Long userId, Pageable pageable) {
        return publicationRepository.findByUserId(userId, pageable);
    }

    @Override
    public Publication getPublicationByIdAndUserId(Long publicationId, Long userId) {
        return publicationRepository.findByIdAndUserId(publicationId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("User or publication not found."));
    }

    @Override
    public Publication createPublication(Long userId, Publication publication) {
        return userRepository.findById(userId).map(user -> {
            publication.setUser(user);
            return publicationRepository.save(publication);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Publication updatePublication(Long userId, Long publicationId, Publication publicationRequest) {
        if(!userRepository.existsById(userId))
            throw  new ResourceNotFoundException("User", "Id", userId);
        return publicationRepository.findById(publicationId).map(publication -> {
            publication.setContent(publicationRequest.getContent());
            publication.setFormatId(publicationRequest.getFormatId());
            publication.setImageUrl(publicationRequest.getImageUrl());
            publication.setVideoUrl(publicationRequest.getVideoUrl());
            return publicationRepository.save(publication);
        }).orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
    }

    @Override
    public ResponseEntity<?> deletePublication(Long userId, Long publicationId) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
        Pageable pageable = PageRequest.of(1, 1000);
        Page<Comment> commentPage = commentRepository.findByPublicationId(publicationId, pageable);

        commentPage.forEach(comment -> { commentRepository.delete(comment); });

        publicationRepository.delete(publication);
        return ResponseEntity.ok().build();
    }
}
