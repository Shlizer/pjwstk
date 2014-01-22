package pl.ayz.shlizer.pjwstk.model;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;

/***********************************************************
 * Student
 * Klasa reprezentuj¹ca tabelê studentów w Javie
 * 
 * @author Shlizer
 */

public class Student extends EntityBase {

	private String name;
	private String surname;
	private Major major;

	public Student() {
	}
	public Student(int id) {
		this.id = id;
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
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	
}
