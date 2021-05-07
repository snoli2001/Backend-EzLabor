package com.ezlabor.accounts.resource.employer;

import com.ezlabor.accounts.resource.ProfileResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployerResource extends ProfileResource {

    private String personalPhone;
    private String description;
    private String companyPhone;
    private String companyName;
    private String companyWeb;
    private String contactCompanyEmail;
}
