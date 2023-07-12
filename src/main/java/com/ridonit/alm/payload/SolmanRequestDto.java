package com.ridonit.alm.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SolmanRequestDto {

    private HeaderDto header;
    private List<PartnerDto> partners;
    private List<CustomerDto> customers;
    private List<TextDto> richTexts;
    private List<TextDto> texts;

}
