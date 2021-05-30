package com.ezlabor.hiring.domain.service;

import com.ezlabor.hiring.domain.model.Meeting;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MeetingService {
    List<Meeting> getAllMeetingsByOfferId(Long offerId);
    Optional<Meeting> getMeetingByIdAndOfferId(Long meetingId, Long id);
    Meeting CreateMeeting(Long offerId,Long postulationId, Meeting meeting);
    Meeting updateMeeting(Long offerId, Long meetingId, Meeting meetingDetails);
    ResponseEntity<?> deleteMeeting(Long offerId, Long meetingId);
}
