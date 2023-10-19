package org.lessons.java.photoalbum.controller;

import java.util.List;

import org.lessons.java.photoalbum.db.pojo.Message;
import org.lessons.java.photoalbum.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public String messages__index(Model model, @RequestParam(required = false) String email) {
		List<Message> messages = email == null 
				? messageService.findAll()
				: messageService.findByEmail(email);
		
		model.addAttribute("messages", messages);
		
		return "messages/messages_index";
	}
}
