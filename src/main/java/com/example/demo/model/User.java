package com.example.demo.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

import com.example.demo.model.enumerator.Gender;

@Entity
@Table(name="UserTable")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Usr")
	private long ID_usr;
	
	@NotNull
	@NotEmpty
	@Size(min=3, max=25)
	@Column(name="Name_Usr")
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(min=3, max=25)
	@Column(name="Surname_Usr")
	private String surname;
	
	@NotNull
	@NotEmpty
	@Size(max=40)
	@Email
	@Column(name="Email_Usr")
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(min=8, max=25)
	@Column(name="Password_Usr")
	private String password;
	
	@NotNull
	@NotEmpty
	@Column(name="BirthDate_Usr")
	private String birthDate;
	
	@NotNull
	@Column(name="Gender_Usr")
	Gender gender;
	
	@ManyToMany(mappedBy="marathonParticipants")
	Collection<Marathon> marathons;
	
	public User() {}
	
	
	public User(String name, String surname, String email, 
			String password, String birthDate, Gender gender) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public long getID_usr() {
		return ID_usr;
	}
	@Override
	public String toString() {
		return "User [ID_usr=" + ID_usr + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + ", birthDate=" + birthDate + ", gender=" + gender + "]";
	}
	
	
	

}
