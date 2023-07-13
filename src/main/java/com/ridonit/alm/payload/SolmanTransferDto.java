package com.ridonit.alm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SolmanTransferDto {

    @JsonProperty("AlmId")
    private String almId;

    @JsonProperty("Json")
    private String json;
}
