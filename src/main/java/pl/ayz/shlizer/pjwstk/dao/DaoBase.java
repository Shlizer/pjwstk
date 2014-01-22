package pl.ayz.shlizer.pjwstk.dao;

import java.util.List;

public abstract class DaoBase<E extends EntityBase> 
	implements Dao<E>, UOWDao{
	
	UOW uow;
	
	protected DaoBase(UOW uow)
	{
		this.uow=uow;
	}
	
	public void save(E ent)
	{
		uow.markNew(ent, this);
	}
	
	public void update(E ent)
	{
		uow.markUpdated(ent, this);
	}
	
	public void delete(E ent)
	{
		uow.markDeleted(ent, this);
	}
	
	public abstract E get(int id);
	public abstract List<E> getAll();
	public abstract void execAdd(EntityBase ent);
	public abstract void execDelete(EntityBase ent);
	public abstract void execUpdate(EntityBase ent);
}
