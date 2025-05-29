package com.example.demo.controller;

import java.util.UUID;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entity.Test;
import com.example.demo.jpa.DemoRepository;
import com.example.demo.service.Test_service;

@RestController
public class Test_controller {

	@Autowired
	@Lazy
	DemoRepository demoRepository;

	@Autowired
	@Lazy
	Test_service test_service;

	@GetMapping("/set/{name}")
	public ResponseEntity<String> name(@PathVariable String name) {
		String uni_id = UUID.randomUUID().toString();
		Integer maxSrno = demoRepository.findMaxId();
		Test test = new Test();
		test.setName(name);
		test.setUni_id(uni_id);
		demoRepository.save(test);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@GetMapping("/insert/batch")
	public ResponseEntity<String> insert() {
		test_service.insertInBatch();
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@PostMapping("/translate")
	public String translate(@RequestBody String intent) {
		test_service.insertInBatch();
		return translate2(intent);

	}

	public String translate2(String intent) {
//		String intent = "Ramadan Timetable 2025 for MUMBAi";
		String url = "https://api.mymemory.translated.net/get?q=" + intent + "!&langpair=en|ur";
		String resp = WebClient.create().get().uri(url).retrieve().bodyToMono(String.class).block();
		System.out.println(resp);
		JSONObject jso = new JSONObject(resp);
		String translation = jso.getJSONObject("responseData").getString("translatedText");

//		String decodedText = StringEscapeUtils.unescapeJava(translation);

		return translation;
	}
}
