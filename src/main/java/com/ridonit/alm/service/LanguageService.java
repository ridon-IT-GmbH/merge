package com.ridonit.alm.service;

import com.google.common.collect.Lists;
import com.ridonit.alm.model.Language;
import com.ridonit.alm.repo.LanguageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepo languageRepo;

    public List<Language> findAll() throws Exception {
       return languageRepo.findAll();
    }

    public Language findByLongName(String languageLong) throws Exception {
        return languageRepo.findByLanguageLong(languageLong).orElseThrow(() -> new EntityNotFoundException("language not found"));
    }

    public Language findByShortName(String languageShort) throws Exception {
        return languageRepo.findByLanguageShort(languageShort).orElseThrow(() -> new EntityNotFoundException("language not found"));
    }

    private List<Language> createLanguages() {
        List<Language> languages = Lists.newArrayList();
        Language ger = new Language();
        ger.setId(1);
        ger.setLanguageLong("Deutsch");
        ger.setLanguageShort("de");
        languages.add(ger);

        Language eng = new Language();
        eng.setId(2);
        eng.setLanguageLong("Englisch");
        eng.setLanguageShort("en");
        languages.add(eng);

        Language esp = new Language();
        esp.setId(3);
        esp.setLanguageLong("Spanisch");
        esp.setLanguageShort("es");
        languages.add(esp);

        return languages;
    }


}
