package com.ridonit.alm.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {

    @NotNull
    @NotEmpty
    private Integer id;

    @NotNull
    @NotEmpty(message = "language long name is mandatory")
    @NotBlank(message = "language long name is mandatory")
    private String languageLong;

    @NotNull
    @NotEmpty(message = "language short name is mandatory")
    @NotBlank(message = "language short name is mandatory")
    private String languageShort;
}
