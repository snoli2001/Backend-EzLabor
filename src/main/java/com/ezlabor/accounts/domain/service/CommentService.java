package com.ezlabor.accounts.domain.service;

import com.ezlabor.accounts.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByPublicationId(Long publicationId);
    List<Comment> getAllCommentsByUserId(Long userId);
    Comment getCommentByIdAndPublicationId(Long publicationId, Long commentId);
    Comment createComment(Long publicationId, Comment comment);
    Comment updateComment(Long publicationId, Long commentId, Comment commentRequest);
    ResponseEntity<?> deleteComment(Long publicationId, Long commentId);
}
