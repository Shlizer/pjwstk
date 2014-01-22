package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.Major;
import pl.ayz.shlizer.pjwstk.model.Subject;

/***********************************************************
 * MysqlSubject
 * Klasa zwraca konkretne zapytania dla transakcji MySql.
 * 
 * @author Shlizer
 */

public class MysqlSubject extends MysqlMain<Subject> {

	public MysqlSubject(MysqlUOW uow) {
		super(uow);
	}

	@Override
	protected String getTableName() {
		return "Mark";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE IF NOT EXISTS Subject ("
				+ "id int(11) primary key auto_increment,"
				+ "name varchar(32),"
				+ "major int(11)"
				+ ")";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO Subject(id,name,major) VALUES (NULL,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Mark SET"
				+ "(name,major)=(?,?)"
				+ "WHERE id=?";
	}

	@Override
	protected void setInsertQuery(Subject s) throws SQLException {
		update.setString(1, s.getName());
		update.setInt(2, s.getMajor().getId());
	}

	@Override
	protected void setUpdateQuery(Subject s) throws SQLException {
		update.setString(1, s.getName());
		update.setInt(2, s.getMajor().getId());
		update.setInt(3, s.getId());
	}

	@Override
	protected Subject build(ResultSet rs) throws SQLException {
		Subject s = new Subject();
		s.setName((rs.getString("name")));
		s.setMajor(new Major(rs.getInt("major")));
		s.setId(rs.getInt("id"));
		return s;
	}
}
