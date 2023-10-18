package org.lessons.java.photoalbum.db.sev;

import java.util.List;
import java.util.Optional;

import org.lessons.java.photoalbum.db.pojo.Photo;
import org.lessons.java.photoalbum.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll(){
		return photoRepo.findAll();
	}
	
	public Optional<Photo> findById(int id){
		return photoRepo.findById(id);
	}
	
	public List<Photo> findByTitle(String title){
		return photoRepo.findByTitleContaining(title);
	}
	
	public Photo save(Photo photo) {
		return photoRepo.save(photo);
	}
	
	public void deletePhoto(Photo photo) {
		photoRepo.delete(photo);
	}
}
