package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;
import pl.ayz.shlizer.pjwstk.dao.imp.MajorDao;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.Major;

/***********************************************************
 * MysqlMajor
 * Klasa zwraca konkretne zapytania dla transakcji MySql.
 * 
 * @author Shlizer
 */

public class MysqlMajor extends MysqlMain<Major> implements MajorDao {

	public MysqlMajor(MysqlUOW uow) {
		super(uow);
	}

	@Override
	protected String getTableName() {
		return "Major";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE IF NOT EXISTS Major ("
				+ "id int(11) primary key auto_increment,"
				+ "name VARCHAR(50)"
				+ ")";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO Major(id,name) VALUES (NULL,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Major SET"
				+ "name=?"
				+ "WHERE id=?";
	}

	@Override
	protected void setInsertQuery(Major s) throws SQLException {
		update.setString(1, s.getName());
	}

	@Override
	protected void setUpdateQuery(Major s) throws SQLException {
		update.setString(1, s.getName());
		update.setInt(2, s.getId());
	}

	@Override
	protected Major build(ResultSet rs) throws SQLException {
		Major m = new Major();
		m.setName(rs.getString("name"));
		m.setId(rs.getInt("id"));
		return m;
	}
}
