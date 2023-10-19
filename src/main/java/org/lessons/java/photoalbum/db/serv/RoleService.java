package org.lessons.java.photoalbum.db.serv;

import java.util.List;

import org.lessons.java.photoalbum.db.pojo.Role;
import org.lessons.java.photoalbum.db.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll(){
		return roleRepo.findAll();
	}
	
	public Role findById(int id) {
		return roleRepo.findById(id).get();	
	}
	
	public void save(Role role) {
		roleRepo.save(role);
	}
}
