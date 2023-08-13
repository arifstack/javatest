package com.arifandi.rekrutmen.dto;

import lombok.Data;

@Data
public class JobResponseDTO {

    private static final long serialVersionUID = -232016847842798660L;
    private String id;
    private String type;
    private String url;
    private String createdAt;
    private String company;
    private String companyURL;
    private String location;
    private String title;
    private String description;
    private String howToApply;
    private String companyLogo;
}
