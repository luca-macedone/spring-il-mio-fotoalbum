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
		
		User testAdmin = new User("admin", testPws, admin);
		User testUser = new User("user", testPws, user);
		User testUser2 = new User("user2", testPws, user);
		User testUser3 = new User("user3", testPws, user);
		
		userService.save(testAdmin);
		userService.save(testUser);
		userService.save(testUser2);
		userService.save(testUser3);
		
		System.out.println("----------------------------");
		System.out.println("Users seeded.");
		
		Category cat1 = new Category("Lanscapes");
		Category cat2 = new Category("Macro");
		Category cat3 = new Category("Black and White");
		Category cat4 = new Category("Night");
		Category cat5 = new Category("Food");
		Category cat6 = new Category("Wedding");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		categoryService.save(cat6);
		
		System.out.println("----------------------------");
		System.out.println("Categories seeded.");
		
		Photo ph1 = new Photo("lorem picsum 1", "Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.", "https://picsum.photos/1200/1000?random=1", true, testUser, cat1);
		Photo ph2 = new Photo("lorem picsum 2", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=2", false, testUser, cat1, cat2);
		Photo ph3 = new Photo("lorem picsum 3", "È universalmente riconosciuto che un lettore che osserva il layout di una pagina viene distratto dal contenuto testuale se questo è leggibile. ", "https://picsum.photos/1200/1000?random=3", true, testUser, cat4, cat2, cat3);
		Photo ph4 = new Photo("lorem picsum 4", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=4", true, testUser, cat5, cat6);
		Photo ph5 = new Photo("lorem picsum 5", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=5", false, testUser2, cat3, cat1);
		Photo ph6 = new Photo("lorem picsum 6", "Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.", "https://picsum.photos/1200/1000?random=6", true, testUser2, cat1, cat6, cat5);
		Photo ph7 = new Photo("lorem picsum 7", "Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.", "https://picsum.photos/1200/1000?random=7", true, testUser2, cat1);
		Photo ph8 = new Photo("lorem picsum 8", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=8", true, testUser2, cat4, cat2);
		Photo ph9 = new Photo("lorem picsum 9", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=9", true, testUser3, cat1, cat6, cat5);
		Photo ph10 = new Photo("lorem picsum 10", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=10", true, testUser3, cat1, cat3);
		Photo ph11 = new Photo("lorem picsum 11", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random1=11", true, testUser3, cat3, cat2);
		Photo ph12 = new Photo("lorem picsum 12", "È universalmente riconosciuto che un lettore che osserva il layout di una pagina viene distratto dal contenuto testuale se questo è leggibile. ", "https://picsum.photos/1200/1000?random=12", true, testUser3, cat2);
		Photo ph13 = new Photo("lorem picsum 13", "È universalmente riconosciuto che un lettore che osserva il layout di una pagina viene distratto dal contenuto testuale se questo è leggibile. ", "https://picsum.photos/1200/1000?random=13", true, testUser2, cat3, cat2);
		Photo ph14 = new Photo("lorem picsum 14", "lorem ipsum sidet omen", "https://picsum.photos/1200/1000?random=14", true, testUser, cat4);
		
		photoService.save(ph1);
		photoService.save(ph2);
		photoService.save(ph3);
		photoService.save(ph4);
		photoService.save(ph5);
		photoService.save(ph6);
		photoService.save(ph7);
		photoService.save(ph8);
		photoService.save(ph9);
		photoService.save(ph10);
		photoService.save(ph11);
		photoService.save(ph12);
		photoService.save(ph13);
		photoService.save(ph14);
		
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
