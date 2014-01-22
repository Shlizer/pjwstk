package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.Major;
import pl.ayz.shlizer.pjwstk.model.Mark;
import pl.ayz.shlizer.pjwstk.model.Student;
import pl.ayz.shlizer.pjwstk.model.Subject;

/***********************************************************
 * MysqlMark
 * Klasa zwraca konkretne zapytania dla transakcji MySql.
 * 
 * @author Shlizer
 */

public class MysqlMark extends MysqlMain<Mark> {

	public MysqlMark(MysqlUOW uow) {
		super(uow);
	}

	@Override
	protected String getTableName() {
		return "Mark";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE IF NOT EXISTS Mark ("
				+ "id int(11) primary key auto_increment,"
				+ "student int(11),"
				+ "subject int(11),"
				+ "mark float"
				+ ")";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO Mark(id,student,subject,mark) VALUES (NULL,?,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Mark SET"
				+ "(student,subject,mark)=(?,?,?)"
				+ "WHERE id=?";
	}

	@Override
	protected void setInsertQuery(Mark m) throws SQLException {
		update.setInt(1, m.getStudent().getId());
		update.setInt(2, m.getSubject().getId());
		update.setFloat(3, m.getMark());
	}

	@Override
	protected void setUpdateQuery(Mark m) throws SQLException {
		update.setInt(1, m.getStudent().getId());
		update.setInt(2, m.getSubject().getId());
		update.setFloat(3, m.getMark());
		update.setInt(4, m.getId());
	}

	@Override
	protected Mark build(ResultSet rs) throws SQLException {
		Mark m = new Mark();
		m.setStudent(new Student(rs.getInt("student")));
		m.setSubject(new Subject(rs.getInt("subject")));
		m.setMark(rs.getInt("mark"));
		m.setId(rs.getInt("id"));
		return m;
	}
}
