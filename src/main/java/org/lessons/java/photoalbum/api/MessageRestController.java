package org.lessons.java.photoalbum.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.photoalbum.api.dto.MessageDto;
import org.lessons.java.photoalbum.db.pojo.Message;
import org.lessons.java.photoalbum.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/messages")
public class MessageRestController {
	@Autowired
	private MessageService messageService;
	
	@PostMapping
	public ResponseEntity<Integer> saveMessage(@RequestBody MessageDto messageDto){
		Message message = new Message(messageDto);
		message = messageService.save(message);
		
		return new ResponseEntity<>(message.getId(), HttpStatus.OK);
	}
	
}
