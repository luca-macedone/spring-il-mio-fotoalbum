package org.lessons.java.photoalbum.db.repo;

import org.lessons.java.photoalbum.db.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
