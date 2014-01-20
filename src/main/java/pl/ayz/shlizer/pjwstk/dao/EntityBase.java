package pl.ayz.shlizer.pjwstk.dao;

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
