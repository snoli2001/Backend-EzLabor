package com.ezlabor.hiring.service;

import com.ezlabor.common.exception.ResourceNotFoundException;
import com.ezlabor.hiring.domain.model.Meeting;
import com.ezlabor.hiring.domain.repository.MeetingRepository;
import com.ezlabor.hiring.domain.repository.OfferRepository;
import com.ezlabor.hiring.domain.repository.PostulationRepository;
import com.ezlabor.hiring.domain.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private PostulationRepository postulationRepository;
    @Override
    public List<Meeting> getAllMeetingsByOfferId(Long offerId) {
        return meetingRepository.findAllByOfferId(offerId);
    }

    @Override
    public Optional<Meeting> getMeetingByIdAndOfferId(Long offerId, Long id) {
        return meetingRepository.findMeetingByIdAndOfferId(offerId, id);
    }

    @Override
    public Meeting CreateMeeting(Long offerId,Long postulationId, Meeting meetingDetails) {
        return offerRepository.findById(offerId).map(offer -> {
            return postulationRepository.findById(postulationId).map(postulation -> {
                meetingDetails.setOffer(offer);
                meetingDetails.setPostulation(postulation);
                return meetingRepository.save(meetingDetails);
            })  .orElseThrow(() -> new ResourceNotFoundException(
                    "Postulation", "Id", postulationId
            ));
        })   .orElseThrow(() -> new ResourceNotFoundException(
                "Offer", "Id", offerId
        ));
    }

    @Override
    public Meeting updateMeeting(Long offerId, Long meetingId, Meeting meetingDetails) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return meetingRepository.findById(meetingId).map(meeting -> {
            meeting.setMeetingDate(meetingDetails.getMeetingDate());
            meeting.setDescription(meetingDetails.getDescription());
            return meetingRepository.save(meeting);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Meeting","Id", meetingId
        ));
    }

    @Override
    public ResponseEntity<?> deleteMeeting(Long offerId, Long meetingId) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return meetingRepository.findById(meetingId)
                .map(meeting -> {
                    meetingRepository.delete(meeting);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Meeting","Id", meetingId
                ));
    }
}
