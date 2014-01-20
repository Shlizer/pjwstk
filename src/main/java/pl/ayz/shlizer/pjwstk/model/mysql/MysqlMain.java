package pl.ayz.shlizer.pjwstk.model.mysql;

import java.sql.PreparedStatement;
import java.sql.Statement;

import pl.ayz.shlizer.pjwstk.dao.UnitOfWork;
import pl.ayz.shlizer.pjwstk.dao.mysql.MysqlUnitOfWork;

public abstract class MysqlMain {
	protected UnitOfWork uow;
	protected Statement query;

	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement select;

	protected MysqlMain(MysqlUnitOfWork uow) {
		this.uow = uow;
	}
}
