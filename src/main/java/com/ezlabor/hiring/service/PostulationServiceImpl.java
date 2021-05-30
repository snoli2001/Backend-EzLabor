package com.ezlabor.hiring.service;

import com.ezlabor.accounts.domain.repository.FreelancerRepository;
import com.ezlabor.common.exception.ResourceNotFoundException;
import com.ezlabor.hiring.domain.model.Postulation;
import com.ezlabor.hiring.domain.repository.OfferRepository;
import com.ezlabor.hiring.domain.repository.PostulationRepository;
import com.ezlabor.hiring.domain.service.PostulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostulationServiceImpl implements PostulationService {
    @Autowired
    private PostulationRepository postulationRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private FreelancerRepository freelancerRepository;
    @Override
    public List<Postulation> getAllPostulationsByOfferId(Long offerId) {
        return postulationRepository.findAllByOfferId(offerId);
    }

    @Override
    public Optional<Postulation> getPostulationByIdAndOfferId(Long postulationId, Long id) {
        return postulationRepository.findByIdAndOfferId(postulationId,id);
    }

    @Override
    public Postulation createPostulation(Long offerId,Long freelancerId, Postulation postulation) {
        return offerRepository.findById(offerId).map(offer -> {
            return freelancerRepository.findById(freelancerId).map(freelancer -> {
                postulation.setState(Postulation.PostState.UNANSWERED);
                postulation.setOffer(offer);
                postulation.setFreelancer(freelancer);
                return postulationRepository.save(postulation);
            }).orElseThrow(() -> new ResourceNotFoundException(
                    "Freelancer", "Id", freelancerId
            ));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Offer", "Id", offerId
        ));
    }

    @Override
    public Postulation updatePostulation(Long offerId, Long id, Postulation postulationDetails) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return postulationRepository.findById(id).map(postulation -> {
            postulation.setDescription(postulationDetails.getDescription());
            postulation.setDesiredPayment(postulationDetails.getDesiredPayment());
            return postulationRepository.save(postulation);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Postulation", "Id", id
        ));
    }

    @Override
    public Postulation AcceptPostulation(Long offerId, Long id) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return postulationRepository.findById(id).map(postulation -> {
            postulation.setState(Postulation.PostState.ACCEPTED);
            return postulationRepository.save(postulation);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Postulation", "Id", id
        ));
    }

    @Override
    public Postulation rejectPostulation(Long offerId, Long id) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return postulationRepository.findById(id).map(postulation -> {
            postulation.setState(Postulation.PostState.REJECTED);
            return postulationRepository.save(postulation);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Postulation", "Id", id
        ));
    }

    @Override
    public ResponseEntity<?> deletePostulation(Long offerId, Long id) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );
        return postulationRepository.findById(id).map(postulation -> {
            postulationRepository.delete(postulation);
            return ResponseEntity.ok().build();
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Postulation","Id", id
        ));
    }
}
