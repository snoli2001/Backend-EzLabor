package com.acme.ezlabor.accounts.domain.model;

import com.acme.ezlabor.accounts.domain.model.background.Knowledge;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "freelancer_id")
@Table(name = "freelancers")
@Data
public class Freelancer extends Profile{
    @NotBlank
    @NotNull
    private String firstname;
    @NotBlank
    @NotNull
    private String lastname;
    private Date birthDate;
    @NotBlank
    @NotNull
    private String phone;
    private String description;
    private String profession;
}
