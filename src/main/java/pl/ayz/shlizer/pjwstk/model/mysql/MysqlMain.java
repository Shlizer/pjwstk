package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.PreparedStatement;
import java.sql.Statement;

import pl.ayz.shlizer.pjwstk.dao.UOW;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUOW;

/***********************************************************
 * MysqlMain
 * Klasa abstrakcyjna, po której dziedzicz¹ elementy modelu bazy danych
 * 
 * @author Shlizer
 */

public abstract class MysqlMain {
	protected UOW uow;
	protected Statement query;

	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement select;

	protected MysqlMain(MysqlUOW uow) {
		this.uow = uow;
	}
}
