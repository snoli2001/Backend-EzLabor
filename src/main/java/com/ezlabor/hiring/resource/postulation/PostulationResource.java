package com.ezlabor.hiring.resource.postulation;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.hiring.domain.model.Offer;
import com.ezlabor.hiring.domain.model.Postulation;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Data
@AllArgsConstructor
public class PostulationResource {
    private Long id;
    private float desiredPayment;
    private String description;
    private Postulation.PostState state;
    private Long freelancerId;
    private String firstname;
    private String lastname;
}
