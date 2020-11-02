package com.searchengine.model;

import org.springframework.data.repository.CrudRepository;

import com.searchengine.SearchData;

public interface SearchRepository extends CrudRepository<SearchData, String> {

	
}
