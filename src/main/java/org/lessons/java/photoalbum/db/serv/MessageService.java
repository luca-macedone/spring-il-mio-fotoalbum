package org.lessons.java.photoalbum.db.serv;

import java.util.List;
import java.util.Optional;

import org.lessons.java.photoalbum.db.pojo.Message;
import org.lessons.java.photoalbum.db.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageRepo messageRepo;
	
	public List<Message> findAll(){
		return messageRepo.findAll();
	}
	
	public Optional<Message> findById(int id) {
		return messageRepo.findById(id);
	}
	
	public List<Message> findByEmail(String email){
		return messageRepo.findByEmail(email);
	}
	
	public Message save(Message message) {
		return messageRepo.save(message);
	}
	
	public void deleteMessage(Message message) {
		messageRepo.delete(message);
	}
}
