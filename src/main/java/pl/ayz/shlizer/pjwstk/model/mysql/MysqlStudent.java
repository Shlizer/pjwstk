package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.Major;
import pl.ayz.shlizer.pjwstk.model.Student;

public class MysqlStudent extends MysqlMain<Student> {

	public MysqlStudent(MysqlUOW uow) {
		super(uow);
	}

	@Override
	protected String getTableName() {
		return "Student";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE IF NOT EXISTS Student ("
				+ "id int(11) primary key auto_increment,"
				+ "name VARCHAR(50),"
				+ "surname VARCHAR(50),"
				+ "major int(11)"
				+ ")";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO Student(id,name,surname,major) VALUES (NULL,?,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Student SET"
				+ "(name,surname,major)=(?,?,?)"
				+ "WHERE id=?";
	}

	@Override
	protected void setInsertQuery(Student s) throws SQLException {
		update.setString(1, s.getName());
		update.setString(2, s.getSurname());
		update.setInt(3, s.getMajor().getId());
	}

	@Override
	protected void setUpdateQuery(Student s) throws SQLException {
		update.setString(1, s.getName());
		update.setString(2, s.getSurname());
		update.setInt(3, s.getMajor().getId());
		update.setInt(4, s.getId());
	}

	@Override
	protected Student build(ResultSet rs) throws SQLException {
		Major m = new Major(rs.getInt("id"));
		Student s = new Student();
		
		s.setName(rs.getString("name"));
		s.setSurname(rs.getString("surname"));
		s.setMajor(m);
		s.setId(rs.getInt("id"));
		return s;
	}
}
