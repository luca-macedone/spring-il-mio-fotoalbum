package org.lessons.java.photoalbum.db.repo;

import java.util.List;

import org.lessons.java.photoalbum.db.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {
	public List<Message> findByEmail(String email);
}
