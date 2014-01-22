package pl.ayz.shlizer.pjwstk.dao;


/***********************************************************
 * EntityBase
 * Klasa abstrakcyjna, po kt�rej dziedzicz� elementy modelu bazy danych
 * Reprezentacja zapytania, powo�uj�c si� na obiekt tabeli w Javie
 * 
 * @author Shlizer
 */

public abstract class EntityBase {

	protected int id;
	protected DBOperation operation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DBOperation getOperation() {
		return operation;
	}

	public void setOperation(DBOperation operation) {
		this.operation = operation;
	}

}
