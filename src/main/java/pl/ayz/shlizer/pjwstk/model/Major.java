package pl.ayz.shlizer.pjwstk.model;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;

public class Major extends EntityBase {

	private String name;

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
