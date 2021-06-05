package com.ezlabor.hiring.domain.model;

import com.ezlabor.accounts.domain.model.Employer;
import com.ezlabor.common.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "offers")
@Data
public class Offer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false, updatable = false)
    private Employer employer;
    private float paymentAmount;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Postulation> postulations;
    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Requirement> requirements;
}
