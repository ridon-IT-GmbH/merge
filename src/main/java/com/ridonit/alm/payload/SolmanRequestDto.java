package com.ridonit.alm.payload;

import lombok.Data;

import java.util.List;

@Data
public class SolmanRequestDto {

    private HeaderDto header;
    private List<PartnerDto> partners;
    private List<CustomerDto> customers;
    private List<TextDto> richTexts;
    private List<TextDto> texts;

}
