package com.searchengine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.searchengine.model.TopicService;

@RestController
public class SearchEngineController {

	@Autowired
	private TopicService topicService;
	
	@CrossOrigin(origins = "*")
	@PostMapping("search")
	public List<SearchData> getData(@RequestBody String data) throws IOException {
		List<SearchData> list = topicService.getAllTopic();
		List<SearchData> newList = list.stream().filter(s -> s.getData().startsWith(data)).limit(10).collect(Collectors.toList());
		return newList;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("addData")
	public List<SearchData> print() throws IOException {
		addData();
		return topicService.getAllTopic();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("display")
	public List<SearchData> displayResult(@RequestBody String data) {
		List<SearchData> list = topicService.getAllTopic();
		List<SearchData> newList = list.stream().filter(s -> s.getData().startsWith(data)).collect(Collectors.toList());
		return newList;
	}
		
	private void addData() throws IOException {	
		File resource = new ClassPathResource("Data.txt").getFile();
		List<String> list = Files.lines(Paths.get(resource.getPath())).collect(Collectors.toList());
		for (int i = 0; i < list.size(); i++) {
			SearchData obj = new SearchData();
			obj.setData(list.get(i));
			topicService.addTopic(obj);
		}
	}
}
