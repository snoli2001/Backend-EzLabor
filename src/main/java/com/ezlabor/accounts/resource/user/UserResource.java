package com.ezlabor.accounts.resource.user;

import com.ezlabor.common.AccountType;
import lombok.Data;

@Data
public class UserResource {
    private Long id;
    private String accountType;
}
