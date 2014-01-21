package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.Major;
import pl.ayz.shlizer.pjwstk.model.Student;

public class MysqlStudent extends MysqlMain {

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
		return "INSERT INTO client(id,name,surname,major) VALUES (NULL,?,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Student SET"
				+ "(name,surname,major)=(?,?,?)"
				+ "WHERE id=?";
	}

	@Override
	protected void setInsertQuery(EntityBase ent) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setUpdateQuery(Student ent) throws SQLException {
		update.setString(1, ent.getName());
		update.setString(2, ent.getSurname());
		//update.setString(3, ent.getEmail());
		//update.setString(4, ent.getNumber());
		update.setInt(5, ent.getId());
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
