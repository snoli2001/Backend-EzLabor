package com.ezlabor.accounts.service;

import com.ezlabor.accounts.domain.model.Comment;
import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.accounts.domain.model.Publication;
import com.ezlabor.accounts.domain.repository.CommentRepository;
import com.ezlabor.accounts.domain.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    @TestConfiguration
    static class CommentServiceImpTestConfiguration {
        @Bean
        public CommentService commentService() { return new CommentServiceImpl(); }
    }
    @Test
    public void whenGetAllCommentsByPublicationId() {
        //Arrange
        Long idPublication = 1L;
        Publication publication = new Publication();
        publication.setId(idPublication);
        publication.setContent("Sample content");

        List<Comment> commentList = new ArrayList<>();

        Long commentId = 2L;
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setContent("Sample comment");

        Long commentId1 = 3L;
        Comment comment1 = new Comment();
        comment1.setId(commentId1);
        comment1.setContent("Sample comment 2");

        when(commentRepository.findByPublicationId(idPublication)).thenReturn(commentList);

        //Act
        List<Comment> foundComments = commentService.getAllCommentsByPublicationId(idPublication);

        //Assert
        assertThat(foundComments.get(0).getPublication()).isEqualTo(publication);
        assertThat(foundComments.size()).isEqualTo(2);
    }
}