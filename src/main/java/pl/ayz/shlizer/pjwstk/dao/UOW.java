package pl.ayz.shlizer.pjwstk.dao;

public interface UOW {

	public void markNew(EntityBase ent, UnitOfWorkDao dao);
	public void markDeleted(EntityBase ent, UnitOfWorkDao dao);
	public void markUpdated(EntityBase ent, UnitOfWorkDao dao);
	public void commit();
	public void close();
}