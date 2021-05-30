package com.ezlabor.hiring.domain.service;

import com.ezlabor.hiring.domain.model.Postulation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostulationService {
    List<Postulation> getAllPostulationsByOfferId(Long offerId);
    Optional<Postulation> getPostulationByIdAndOfferId(Long postulationId, Long id);
    Postulation createPostulation(Long postulationId,Long freelancerId, Postulation postulation);
    Postulation updatePostulation(Long postulationId, Long id, Postulation postulationDetails);
    Postulation AcceptPostulation(Long offerId, Long id);
    Postulation rejectPostulation(Long offerId, Long id);
    ResponseEntity<?> deletePostulation(Long postulationId, Long id);
}
