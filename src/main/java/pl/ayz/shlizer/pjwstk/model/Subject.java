package pl.ayz.shlizer.pjwstk.model;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;

/***********************************************************
 * Subject
 * Klasa reprezentuj¹ca tabelê przedmiotów w Javie
 * 
 * @author Shlizer
 */

public class Subject extends EntityBase {

	private String name;
	private Major major;

	public Subject() {
	}
	public Subject(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
}
