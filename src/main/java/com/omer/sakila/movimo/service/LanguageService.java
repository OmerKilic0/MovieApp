package com.omer.sakila.movimo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Language;
import com.omer.sakila.movimo.repository.LanguageRepository;

@Service
public class LanguageService {

	private LanguageRepository languageRepository;
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	public List<Language> getAllLanguages(){
		return languageRepository.findAll();
	}
	
	public Language addLanguage(String name) {
        Language language = new Language();
        language.setName(name);
        language.setLastUpdate(new Date());
        return languageRepository.save(language);
    }
}