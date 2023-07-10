package com.ridonit.alm.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BoredDto {

    @NotNull
    @NotEmpty
    private Integer key;

    @NotNull
    @NotEmpty
    private String type;

    @NotNull
    @NotEmpty
    private String activity;

    private String link;

    private Integer participants;

    private BigDecimal price;

    private Double accessibility;
}
