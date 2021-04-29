package com.acme.ezlabor.accounts.domain.model.background;

import com.acme.ezlabor.accounts.domain.model.Freelancer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "knowledges")
public class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "freelancer_id", nullable = false)
    @JsonIgnore
    private Freelancer freelancer;
    @NotNull
    private String name;
    @Lob
    private String description;
    @Enumerated(value = EnumType.STRING)
    private KnowledgeLevel level;

    public enum KnowledgeLevel {
        BASIC,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }
}
