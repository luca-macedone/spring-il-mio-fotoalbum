package org.lessons.java.photoalbum;

import java.util.ArrayList;
import java.util.List;

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
		
		User testAdmin = new User("admin", testPws, admin);
		User testUser = new User("mario82", testPws, user);
		User testUser2 = new User("luca", testPws, user);
		User testUser3 = new User("irene", testPws, user);
		
		userService.save(testAdmin);
		userService.save(testUser);
		userService.save(testUser2);
		userService.save(testUser3);
		
		System.out.println("----------------------------");
		System.out.println("Users seeded.");
		
		Category cat1 = new Category("Forest");
		Category cat2 = new Category("Outdoor");
		Category cat3 = new Category("Costume");
		Category cat4 = new Category("Person");
		Category cat5 = new Category("Autumn colors");
		Category cat6 = new Category("Vegetable");
		Category cat7 = new Category("Autumn Leaves");
		Category cat8 = new Category("Action");
		Category cat9 = new Category("Adventure");
		Category cat10 = new Category("Landscape");
		Category cat11 = new Category("Aerial Photography");
		Category cat12 = new Category("Beach");
		
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		categoryService.save(cat6);
		categoryService.save(cat7);
		categoryService.save(cat8);
		categoryService.save(cat9);
		categoryService.save(cat10);
		categoryService.save(cat11);
		categoryService.save(cat12);
		
		System.out.println("----------------------------");
		System.out.println("Categories seeded.");
		
		List<Photo> photos = new ArrayList<Photo>();
		
		photos.add(
				new Photo(
						"Leaves road",
						"Concrete road between trees",
						"https://images.pexels.com/photos/1563356/pexels-photo-1563356.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", 
						true, 
						testUser3, 
						cat1
						)
				);
		photos.add(
				new Photo(
						"Yellow Leaves",
						"Bunch of yellow leaves",
						"https://images.pexels.com/photos/3082731/pexels-photo-3082731.jpeg", 
						true, 
						testUser2, 
						cat7
						)
				);
		photos.add(
				new Photo(
						"Spookie forest",
						"Person wearing mask holding a bag surrounded with tall trees",
						"https://images.pexels.com/photos/3179835/pexels-photo-3179835.jpeg", 
						true, 
						testUser, 
						cat1,
						cat2,
						cat3,
						cat4
						)
				);
		photos.add(
				new Photo(
						"Underwater snorkling",
						"Split-level photography of man underwater",
						"https://images.pexels.com/photos/2765871/pexels-photo-2765871.jpeg", 
						true, 
						testUser3, 
						cat4,
						cat8
						)
				);
		photos.add(
				new Photo(
						"Mad Mud",
						"Person riding bike making trek on thin air",
						"https://images.pexels.com/photos/71104/utah-mountain-biking-bike-biking-71104.jpeg", 
						true, 
						testUser2, 
						cat4,
						cat8,
						cat9
						)
				);
		photos.add(
				new Photo(
						"Wow!",
						"A picturesque shot of haukland beach, Norway",
						"https://images.pexels.com/photos/12312751/pexels-photo-12312751.jpeg", 
						true, 
						testUser, 
						cat9,
						cat10
						)
				);
		photos.add(
				new Photo(
						"Snow Cap",
						"Landscape photography of snow capped mountain",
						"https://images.pexels.com/photos/2613946/pexels-photo-2613946.jpeg", 
						true, 
						testUser2, 
						cat10
						)
				);
		photos.add(
				new Photo(
						"Mad Mud",
						"Person riding bike making trek on thin air",
						"https://images.pexels.com/photos/2880718/pexels-photo-2880718.jpeg", 
						true, 
						testUser, 
						cat4,
						cat8,
						cat9
						)
				);
		photos.add(
				new Photo(
						"Sri Lanka's Wave",
						"Aerial photography of person standing on seashore",
						"https://images.pexels.com/photos/1007676/pexels-photo-1007676.jpeg", 
						true, 
						testUser2, 
						cat11,
						cat12,
						cat4
						)
				);
		
		
		for(Photo p : photos){
			photoService.save(p);
		}
		
		System.out.println("----------------------------");
		System.out.println("Photos seeded.");
		
		Message m1 = new Message("test@email.com", "lorem ipsum dolor amet");
		Message m2 = new Message("test2@email.com", "lorem ipsum dolor amet 2");
		
		messageService.save(m1);
		messageService.save(m2);
		
		System.out.println("----------------------------");
		System.out.println("Messages seeded.");
		
		System.out.println("----------------------------");
		System.out.println("Everything is seeded.");
		System.out.println("----------------------------");
	}
}
