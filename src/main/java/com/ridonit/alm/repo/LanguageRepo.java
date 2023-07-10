package com.ridonit.alm.repo;

import com.ridonit.alm.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepo extends JpaRepository<Language, Integer> {
    Optional<Language> findByLanguageLong(String longname);
    Optional<Language> findByLanguageShort(String shortname);
}
