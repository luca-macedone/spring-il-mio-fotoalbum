package org.lessons.java.photoalbum.db.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true)
	@NotNull
	private String username;
	
	@Column(nullable=false)
	@NotNull 
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Photo> photos;
	
	public User() {}
	public User(String username, String password, Role... roles) {
		setUsername(username);
		setPassword(password);
		setRoles(new HashSet<Role>(Arrays.asList(roles)));
	}
	
	public Integer getId() {
		return id;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] User:\n"
				+ "title: " + getUsername() + "\n";
	}
	
	@Override
	public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(
									r.getName()
								)
				).toList();
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override 
	public boolean isAccountNonExpired() { 
		return true; 
	}
	@Override 
	public boolean isAccountNonLocked() { 
		return true; 
	}
	@Override 
	public boolean isCredentialsNonExpired() { 
		return true; 
	}
	@Override 
	public boolean isEnabled() { 
		return true; 
	}
}
