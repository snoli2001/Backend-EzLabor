package com.ezlabor.accounts.controller;

import com.ezlabor.accounts.domain.model.Comment;
import com.ezlabor.accounts.domain.service.CommentService;
import com.ezlabor.accounts.resource.CommentResource;
import com.ezlabor.accounts.resource.SaveCommentResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/publications/{publicationId}/comments")
    public Page<CommentResource> getAllCommentsByPublicationId(@PathVariable Long publicationId, Pageable pageable) {
        List<CommentResource> comments = commentService.getAllCommentsByPublicationId(publicationId, pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(comments, pageable, comments.size());
    }

    @GetMapping("/users/{userId}/comments")
    public Page<CommentResource> getAllCommentsByUserId(@PathVariable Long userId, Pageable pageable) {
        List<CommentResource> comments = commentService.getAllCommentsByUserId(userId, pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(comments, pageable, comments.size());
    }

    @GetMapping("/publications/{publicationId}/comments/{commentId}")
    public CommentResource getCommentByIdAndPublicationId(@PathVariable Long publicationId, @PathVariable Long commentId) {
        return convertToResource(commentService.getCommentByIdAndPublicationId(publicationId, commentId));
    }

    @PostMapping("/publications/{publicationId}/comments")
    public CommentResource createComment(@PathVariable Long publicationId, @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(publicationId, convertToEntity(resource)));
    }

    @PutMapping("/publications/{publicationId}/comments/{commentId}")
    public CommentResource updateComment(@PathVariable Long publicationId,
                                         @PathVariable Long commentId,
                                         @Valid @RequestBody SaveCommentResource resource) {
       return convertToResource(commentService.updateComment(publicationId, commentId, convertToEntity(resource)));
    }

    @DeleteMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long publicationId, @PathVariable Long commentId) {
        return commentService.deleteComment(publicationId, commentId);
    }

    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }
}
