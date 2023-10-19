package org.lessons.java.photoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.photoalbum.db.pojo.Category;
import org.lessons.java.photoalbum.db.pojo.Photo;
import org.lessons.java.photoalbum.db.serv.CategoryService;
import org.lessons.java.photoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PhotoService photoService;
	
	@GetMapping
	public String categories__index(Model model) {
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		return "categories/category_index";
	}
	
	@GetMapping("/create")
	public String categories__create(Model model) {
		List<Photo> photos = photoService.findAll();
		Category category = new Category();
		
		model.addAttribute("photos", photos);
		model.addAttribute("category", category);
		
		return "categories/category_form";
	}
	
	@PostMapping("/create")
	public String categories__store(Model model, 
			@Valid @ModelAttribute Category category, 
			BindingResult bindingResult) {
		
		categoryService.save(category);
		List<Photo> photos = category.getPhotos();
		for(Photo photo : photos) {
			photo.addCategories(category);
		}
		
		return "redirect:/categories";
	}
	
	@GetMapping("/edit/{category_id}")
	public String categories__edit(Model model,
			@PathVariable("category_id") int id) {
		Optional<Category> categoryOpt = categoryService.findById(id);
		if(!categoryOpt.isEmpty()) {
			Category category = categoryOpt.get();
			
			model.addAttribute(category);
			
			return "categories/category_form";
		}
		return "not_found";
	}
	
	@PostMapping("/edit/{category_id}")
	public String category__update(Model model,
			@Valid @ModelAttribute Category category,
			BindingResult bindingResult,
			@PathVariable("category_id") int id) {
		category.setId(id);
		System.out.println(category);
		categoryService.save(category);
		/*
		List<Photo> photos = photoService.findAll();
		for(Photo photo : photos) {
			if(category.hasPhoto(photo)) {
				if(!photo.hasCategory(category)) {					
					photo.addCategories(category);
				}
			}else photo.removeCategories(category);
			
			photoService.save(photo);
		}
		*/
		
		return "redirect:/categories";
	}
	
	@PostMapping("/delete/{category_id}")
	public String category__delete(@PathVariable("category_id") int id) {
		Optional<Category> categoryOpt = categoryService.findById(id);
		if(!categoryOpt.isEmpty()) {
			Category category = categoryOpt.get();
			List<Photo> photos = photoService.findAll();
			
			for(Photo photo : photos) {
				if(photo.hasCategory(category)) {
					photo.removeCategories(category);
				}
				
				photoService.save(photo);
			}
			categoryService.deleteCategory(category);
			
			return "redirect:/categories";
		}
		return "not_found";
	}
	
	
	
}
