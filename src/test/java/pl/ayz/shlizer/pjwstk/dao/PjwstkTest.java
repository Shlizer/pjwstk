package pl.ayz.shlizer.pjwstk.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pl.ayz.shlizer.pjwstk.dao.imp.*;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;
import pl.ayz.shlizer.pjwstk.model.*;
import pl.ayz.shlizer.pjwstk.model.mysql.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PjwstkTest {

	MajorDao major;
	MarkDao mark;
	StudentDao student;
	SubjectDao[] subject;

	Connection connection = null;
	private Statement drop;
	
	@BeforeClass
	public static void initialize()
	{}
	
	@Before
	public void setUp()
	{
		/*******************************************************
		 * TWORZENIE TABEL
		 */
		// TWORZYMY KIERUNKI
		String[] majorAdd = {
				"Kierunek1",
				"Kierunek2"
		};

		for(int i=0; i<majorAdd.length; i++) {
			try {
				MysqlUOW uow = new MysqlUOW();
				
				Major m = new Major();
				m.setName(majorAdd[i]);
				
				uow.commit();
			} catch (Exception err) {
				// TODO Auto-generated catch block
				err.printStackTrace();
			}
		}

		// TWORZYMY PRZEDMIOTY
		HashMap<String,Integer> subjectAdd = new HashMap<String,Integer>();
		subjectAdd.put("Przedmiot1", 1);
		subjectAdd.put("Przedmiot2", 2);
		subjectAdd.put("Przedmiot3", 2);
		
		for(Entry<String,Integer> entry : subjectAdd.entrySet()) {
			MysqlUOW uow = new MysqlUOW();
			
			Subject s = new Subject();
			s.setName(entry.getKey());
			s.setMajor(new Major(entry.getValue()));
			
			uow.markNew(s, (UOWDao) uow);
			uow.commit();
		}
	}
	
	@After
	public void teardown()
	{
		try{
			if(connection!=null && !connection.isClosed())
			{	
				drop.executeUpdate("Drop table Client");
				connection.close();
				connection = null;
			}}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
	}
	
	@Test
	public void testGet() {
		Student s1 = student.get(0);
		Student s2 = student.get(2);
		Student s3 = student.get(0);
		
		assertNotNull("Zwrócono null mimo ze obiekt jest w bazie",s1);
		assertNull("zwrócono wartosc mimo, ¿e nie ma takiego obiektu w bazie",s2);
		assertEquals(s1.getName(),"Jan");
		assertEquals(s1.getSurname(), "Nowak");
		assertNotSame(s1,s3);
		
	}

}
