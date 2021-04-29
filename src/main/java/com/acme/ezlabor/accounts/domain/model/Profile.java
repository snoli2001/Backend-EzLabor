package com.acme.ezlabor.accounts.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;


@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Profile extends User {

    private String webPage;
    private String facebookLink;
    private String instagramLink;
    private String twitterLink;
    private String imageUrl;

}
