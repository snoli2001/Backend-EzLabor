package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.Comment;
import com.ezlabor.accounts.domain.repository.CommentRepository;
import com.ezlabor.accounts.domain.repository.PublicationRepository;
import com.ezlabor.accounts.domain.repository.UserRepository;
import com.ezlabor.accounts.domain.service.CommentService;
import com.ezlabor.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Page<Comment> getAllCommentsByPublicationId(Long publicationId, Pageable pageable) {
        return commentRepository.findByPublicationId(publicationId, pageable);
    }

    @Override
    public Page<Comment> getAllCommentsByUserId(Long userId, Pageable pageable) {
        return commentRepository.findByUserId(userId, pageable);
    }

    @Override
    public Comment getCommentByIdAndPublicationId(Long publicationId, Long commentId) {
        return commentRepository.findByIdAndPublicationId(publicationId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication with ID " + publicationId
                + " and Comment ID " + commentId + " not found"));
    }

    @Override
    public Comment createComment(Long publicationId, Comment comment) {
        return publicationRepository.findById(publicationId).map(publication -> {
            comment.setPublication(publication);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
    }

    @Override
    public Comment updateComment(Long publicationId, Long commentId, Comment commentRequest) {
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", "Id", publicationId);
        return commentRepository.findById(commentId).map(comment -> {
            comment.setContent(commentRequest.getContent());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long publicationId, Long commentId) {
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", "Id", publicationId);
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment", "Id", commentId));
    }
}
