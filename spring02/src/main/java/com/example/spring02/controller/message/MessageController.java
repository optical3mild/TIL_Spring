package com.example.spring02.controller.message;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.message.dto.MessageDTO;
import com.example.spring02.service.message.MessageService;

@RestController
@RequestMapping("messages/*")
public class MessageController {
	
	@Inject
	MessageService messageService;
	
	// ResponseEntity : 리턴값=json+에러메시지
	@RequestMapping(value="/", method=RequestMethod.POST) // ~messages/ 까지 요청 시 작동.
	// ResponseEntity<String> : String의 형태로 리턴값(json+에러메시지)가 넘어감.
	public ResponseEntity<String> addMessage(@RequestBody MessageDTO dto) {
		ResponseEntity<String> entity = null;
		try {
			messageService.addMessage(dto);
			entity = new ResponseEntity<>("success", HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400
		}
		return entity;
	}
}
