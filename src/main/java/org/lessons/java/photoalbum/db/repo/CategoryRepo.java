package org.lessons.java.photoalbum.db.repo;

import org.lessons.java.photoalbum.db.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
