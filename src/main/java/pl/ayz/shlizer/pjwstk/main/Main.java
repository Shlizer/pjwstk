package pl.ayz.shlizer.pjwstk.main;

import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUnitOfWork;
import pl.ayz.shlizer.pjwstk.model.*;
import pl.ayz.shlizer.pjwstk.model.mysql.*;

public class Main {

	public static void main(String[] args) {
			MysqlUnitOfWork uow = new MysqlUnitOfWork();
			//MysqlStudent dao = new MysqlStudent(uow);
		System.out.println("koniec");
	}

}