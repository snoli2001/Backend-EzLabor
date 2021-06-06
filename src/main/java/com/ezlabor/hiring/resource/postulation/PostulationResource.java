package com.ezlabor.hiring.resource.postulation;

import com.ezlabor.accounts.domain.model.Freelancer;
import com.ezlabor.hiring.domain.model.Offer;
import com.ezlabor.hiring.domain.model.Postulation;

import javax.persistence.*;

public class PostulationResource {
    private Long id;
    private float desiredPayment;
    private String description;
    private Postulation.PostState state;
}
