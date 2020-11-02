package com.searchengine.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchengine.SearchData;

@Service
public class TopicService {

	@Autowired
	private SearchRepository searchRepository;
	
	public List<SearchData> getAllTopic() {
		
		List<SearchData> topics = new ArrayList<>();
		searchRepository.findAll().forEach(topics::add);
		return topics;
	}
	
	public void addTopic(SearchData topic) {
		searchRepository.save(topic);
	}
}
