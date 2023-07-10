package com.ridonit.alm.controller;

import com.ridonit.alm.Service.LanguageService;
import com.ridonit.alm.model.Language;
import com.ridonit.alm.payload.LanguageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="languages")
@Slf4j
@RequiredArgsConstructor
public class LanguageController {

    private final ModelMapper modelMapper;

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getLanguages() throws Exception {
        List<Language> languages = languageService.findAll();
        List<LanguageDto> resultList = createLanguageDtoList(languages);

        return new ResponseEntity<>(resultList, HttpStatus.OK);

    }

    @GetMapping(value = "/name/{longname}")
    public ResponseEntity<LanguageDto> getLanguage(@PathVariable("longname") String longname) throws Exception {
        Language language = languageService.findByLongName(longname);
        LanguageDto result = createLanguageDto(language);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    protected List<LanguageDto> createLanguageDtoList(List<Language> languages) {
        return languages.stream()
            .map(language -> modelMapper.map(language, LanguageDto.class))
            .collect(Collectors.toList());
    }

    protected LanguageDto createLanguageDto(Language language) {
        return modelMapper.map(language, LanguageDto.class);
    }
}
