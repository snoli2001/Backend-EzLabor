package com.ezlabor.hiring.resource.Offer;
import lombok.Data;

import java.util.Date;
@Data
public class OfferResource {
    private Long id;
    private String title;
    private String description;
    private float paymentAmount;
    private Date startDate;
    private Date endDate;
    private int monthDuration;
    private boolean isActive;
}
