package org.lessons.java.photoalbum;

import org.lessons.java.photoalbum.db.pojo.Category;
import org.lessons.java.photoalbum.db.pojo.Message;
import org.lessons.java.photoalbum.db.pojo.Photo;
import org.lessons.java.photoalbum.db.pojo.Role;
import org.lessons.java.photoalbum.db.pojo.User;
import org.lessons.java.photoalbum.db.serv.CategoryService;
import org.lessons.java.photoalbum.db.serv.MessageService;
import org.lessons.java.photoalbum.db.serv.PhotoService;
import org.lessons.java.photoalbum.db.serv.RoleService;
import org.lessons.java.photoalbum.db.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Role user = new Role("USER");
		Role admin = new Role("ADMIN");
		
		roleService.save(user);
		roleService.save(admin);
		
		final String testPws = new BCryptPasswordEncoder().encode("test");
		
		User testUser = new User("user", testPws, user);
		User testAdmin = new User("admin", testPws, admin);
		
		userService.save(testUser);
		userService.save(testAdmin);
		
		System.out.println("----------------------------");
		System.out.println("Users seeded.");
		
		Category cat1 = new Category("Lanscapes");
		Category cat2 = new Category("Macro");
		Category cat3 = new Category("Black and White");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		
		System.out.println("----------------------------");
		System.out.println("Categories seeded.");
		
		Photo ph1 = new Photo("lorem picsum 1", "lorem ipsum sidet omen", "https://picsum.photos/200/300?random=1", true, cat1);
		Photo ph2 = new Photo("lorem picsum 2", "lorem ipsum sidet omen", "https://picsum.photos/200/300?random=2", true, cat1, cat2);
		Photo ph3 = new Photo("lorem picsum 3", "lorem ipsum sidet omen", "https://picsum.photos/200/300?random=3", true, cat1, cat2, cat3);
		
		photoService.save(ph1);
		photoService.save(ph2);
		photoService.save(ph3);
		
		System.out.println("----------------------------");
		System.out.println("Photos seeded.");
		
		Message m1 = new Message("test@email.com", "lorem ipsum dolor amet");
		Message m2 = new Message("test2@email.com", "lorem ipsum dolor amet 2");
		
		messageService.save(m1);
		messageService.save(m2);
		
		System.out.println("----------------------------");
		System.out.println("Everything is seeded.");
		System.out.println("----------------------------");
	}
}
