package pl.ayz.shlizer.pjwstk.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import pl.ayz.shlizer.pjwstk.dao.DBOperation;
import pl.ayz.shlizer.pjwstk.dao.EntityBase;
import pl.ayz.shlizer.pjwstk.dao.UOW;
import pl.ayz.shlizer.pjwstk.dao.UOWDao;

/***********************************************************
 * MysqlMain
 * Klasa obs³uguj¹ca po³¹czenie z baz¹ i obs³ugê transakcji
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
		catch(SQLException err)
		{
			err.printStackTrace();
		}
		catch(Exception err) {  // brak klasy sterownika
        	System.out.println("Brak klasy sterownika");
         	System.out.println(err);
         	System.exit(1);
		}
		return connection;
	}

	public void markNew(EntityBase e, UOWDao dao) {
		e.setOperation(DBOperation.insert);
		tasks.put(e, dao);
	}

	public void markDeleted(EntityBase e, UOWDao dao) {
		e.setOperation(DBOperation.delete);
		tasks.put(e, dao);
	}

	public void markUpdated(EntityBase e, UOWDao dao) {
		e.setOperation(DBOperation.update);
		tasks.put(e, dao);
	}

	public void commit() {
		Connection connection = getConnection();
		
		try {
			connection.setAutoCommit(false);

			for(EntityBase e : tasks.keySet() )
			{
				switch(e.getOperation())
				{
					case insert:
					{
						tasks.get(e).execAdd(e);
					}
					case delete:
					{
						tasks.get(e).execDelete(e);
					}
					case update:
					{
						tasks.get(e).execUpdate(e);
					}
					default:
						break;
				}
			}
				
			connection.setAutoCommit(true);
			connection.commit();
		}
		catch(SQLException err) {
			err.printStackTrace();
		}
	}

	public void close() {
		try {
			if(connection!=null && !connection.isClosed()){
				connection.close();
				connection=null;
			}
		}
		catch(SQLException err) {
			err.printStackTrace();
		}
	}

}
