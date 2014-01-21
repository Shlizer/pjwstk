package pl.ayz.shlizer.pjwstk.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import pl.ayz.shlizer.pjwstk.dao.EntityBase;
import pl.ayz.shlizer.pjwstk.dao.DBOperation;
import pl.ayz.shlizer.pjwstk.dao.UOW;
import pl.ayz.shlizer.pjwstk.dao.UOWDao;

/***********************************************************
 * MysqlMain
 * Klasa abstrakcyjna, po której dziedzicz¹ elementy modelu bazy danych
 * 
 * @author Shlizer
 */

public class MysqlUOW implements UOW {

	private Map<EntityBase, UOWDao> tasks;
	private Connection connection;
	
	public MysqlUOW() {
		tasks = new HashMap<EntityBase, UOWDao>();
		connection = getConnection();
	}
	
	public Connection getConnection() {
		try
		{
			if(connection==null||connection.isClosed()) {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/";
				String dbName = "pjwstk";
				String userName = "pjwstk";
				String password = "qaz123";
				
				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url+dbName,userName,password);
			}
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(Exception e) {  // brak klasy sterownika
        	System.out.println("Brak klasy sterownika");
         	System.out.println(e);
         	System.exit(1);
		}
		return connection;
	}

	public void markNew(EntityBase ent, UOWDao dao) {
		ent.setOperation(DBOperation.insert);
		tasks.put(ent, dao);
	}

	public void markDeleted(EntityBase ent, UOWDao dao) {
		ent.setOperation(DBOperation.delete);
		tasks.put(ent, dao);
	}

	public void markUpdated(EntityBase ent, UOWDao dao) {
		ent.setOperation(DBOperation.update);
		tasks.put(ent, dao);
	}

	public void commit() {
		Connection connection = getConnection();
		
		try {
			connection.setAutoCommit(false);
		
			connection.setAutoCommit(true);
			connection.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if(connection!=null && !connection.isClosed()){
				connection.close();
				connection=null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
