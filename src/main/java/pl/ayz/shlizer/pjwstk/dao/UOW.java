package pl.ayz.shlizer.pjwstk.dao;

public interface UOW {

	public void markNew(EntityBase ent, UOWDao dao);
	public void markDeleted(EntityBase ent, UOWDao dao);
	public void markUpdated(EntityBase ent, UOWDao dao);
	public void commit();
	public void close();
}
