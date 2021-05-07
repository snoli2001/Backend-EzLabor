package com.ezlabor.accounts.resource.freelancer;

import com.ezlabor.accounts.resource.ProfileResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class FreelancerResource extends ProfileResource {

    private Date birthDate;
    private String phone;
    private String description;
    private String profession;

}
