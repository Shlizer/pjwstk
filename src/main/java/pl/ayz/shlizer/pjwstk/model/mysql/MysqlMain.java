package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.ayz.shlizer.pjwstk.dao.DaoBase;
import pl.ayz.shlizer.pjwstk.dao.EntityBase;
import pl.ayz.shlizer.pjwstk.dao.UOW;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;

/***********************************************************
 * MysqlMain
 * Klasa abstrakcyjna, po której dziedzicz¹ elementy modelu bazy danych
 * Zwraca konkretne zapytania dla transakcji MySql.
 * 
 * @author Shlizer
 */

public abstract class MysqlMain<E extends EntityBase> extends DaoBase<E> {
	protected UOW uow;
	protected Statement query;

	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement select;
	protected PreparedStatement selectId;

	protected abstract String getTableName();
	protected abstract String getCreateQuery();
	protected abstract String getInsertQuery();
	protected abstract String getUpdateQuery();

	protected abstract void setInsertQuery(E e) throws SQLException;
	protected abstract void setUpdateQuery(E e) throws SQLException;
	protected abstract E build(ResultSet rs) throws SQLException;

	protected String getDropTable() {
		return "DROP TABLE "+this.getTableName();
	}
	
	protected MysqlMain(MysqlUOW uow) {
		super(uow);
		this.uow = uow;
		
		try {
			Connection connection = uow.getConnection();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean exist = false;
			
			query =connection.createStatement();
			
			while(rs.next())
			{
				if(rs.getString("TABLE_NAME").equalsIgnoreCase(getTableName()))
				{
					exist = true;
					break;
				}
			}
			if(!exist)
			{
				query.executeUpdate(getCreateQuery());
			}
			
			insert = connection.prepareStatement(getInsertQuery());
			
			update = connection.prepareStatement(getUpdateQuery());
			
			delete = connection.prepareStatement(""
					+ "delete from "+getTableName()+" where id=?");
			
			selectId = connection.prepareStatement(""
					+ "select * from "+getTableName()+" where id=?");
			
			select = connection.prepareStatement(""
					+ "select * from "+getTableName());
			
		} catch (SQLException err) {
			
			err.printStackTrace();
		}
		
	}
	
	public void execAdd(EntityBase entity) {
		E e = (E) entity;
		
		try 
		{
			setInsertQuery(e);
			insert.executeUpdate();
			
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	

	public void execUpdate(EntityBase entity) {
		E e = (E) entity;
		
		try
		{
			setUpdateQuery(e);
			update.executeUpdate();
		} catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	
	public void execDelete(EntityBase entity) {
		E e = (E) entity;
		
		try 
		{
			delete.setInt(1, e.getId());
			delete.executeUpdate();

		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	
	public E get(int id) {
		try {
			selectId.setInt(1, id);
			ResultSet rs = selectId.executeQuery();
			while(rs.next()){
				return build(rs);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return null;
	}
	
	
	public List<E> getAll() {
		List<E> clients = new ArrayList<E>();
		
		try
		{
			ResultSet rs = select.executeQuery();
			while(rs.next()) {
				clients.add(build(rs));
			}
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return clients;
	}
}
