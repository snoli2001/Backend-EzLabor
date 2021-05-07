package com.ezlabor.hiring.domain.model;

import com.ezlabor.common.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "offers")
public class Offer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
