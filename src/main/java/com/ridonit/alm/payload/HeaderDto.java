package com.ridonit.alm.payload;

import lombok.Data;

@Data
public class HeaderDto {
    private String priority;
    private String description;
    private String processType;
}
