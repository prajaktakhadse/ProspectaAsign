package com.prospectassign;

import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/entries/{category}")
	public List<ResultDTO> getEntriesHandler(@PathVariable("category") String category){
		
		
		Data d= restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		
		List<Entry> entries= d.getEntries();
	

		
		
		List<ResultDTO> list= entries.stream().filter(e -> e.getCategory().equals(category)).map(e -> new ResultDTO(e.getTitle(), e.getDescription())).toList();
		
		
		
		
		 
		return list;
		
	}
	

	
	
}
