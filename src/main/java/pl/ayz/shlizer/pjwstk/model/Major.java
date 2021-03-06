package pl.ayz.shlizer.pjwstk.model;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;

/***********************************************************
 * Major
 * Klasa reprezentująca tabelę kierunków w Javie
 * 
 * @author Shlizer
 */

public class Major extends EntityBase {

	private String name;

	public Major() {
	}
	
	public Major(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
