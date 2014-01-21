package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;
import pl.ayz.shlizer.pjwstk.dao.UOW;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.Student;

/***********************************************************
 * MysqlMain
 * Klasa abstrakcyjna, po której dziedzicz¹ elementy modelu bazy danych
 * 
 * @author Shlizer
 * @param <E>
 */

public abstract class MysqlMain<E extends EntityBase> {
	protected UOW uow;
	protected Statement query;

	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement select;

	protected MysqlMain(MysqlUOW uow) {
		this.uow = uow;
	}

	protected abstract String getTableName();
	protected abstract String getCreateQuery();
	protected abstract String getInsertQuery();
	protected abstract String getUpdateQuery();

	protected abstract void setInsertQuery(E ent) throws SQLException;
	protected abstract void setUpdateQuery(EntityBase ent) throws SQLException;
	protected abstract E build(ResultSet rs) throws SQLException;
}
