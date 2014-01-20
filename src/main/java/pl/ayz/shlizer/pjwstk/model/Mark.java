package pl.ayz.shlizer.pjwstk.model;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;

public class Mark extends EntityBase {

	private Student student;
	private Subject subject;
	private float mark;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	
}
