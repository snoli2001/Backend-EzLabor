package com.ezlabor.hiring.resource.postulation;

import com.ezlabor.hiring.domain.model.Postulation;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class SavePostulationResource {
    @PositiveOrZero
    private float desiredPayment;
    @NotNull
    @NotBlank
    @Lob
    private String description;
}
