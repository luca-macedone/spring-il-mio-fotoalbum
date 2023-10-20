package org.lessons.java.photoalbum.db.repo;

import java.util.List;

import org.lessons.java.photoalbum.db.pojo.Photo;
import org.lessons.java.photoalbum.db.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer>{
	public List<Photo> findByTitle(String title);
	public List<Photo> findByTitleContaining(String title);
	public List<Photo> findByUserAndTitleContaining(User user, String title);
	public List<Photo> findByVisible(boolean visible);
	public List<Photo> findByUser(User user);
	public List<Photo> findByUserAndTitle(User user, String title);
}
