package pl.ayz.shlizer.pjwstk.dao;


public interface UOW {

	public void markNew(EntityBase e, UOWDao dao);
	public void markDeleted(EntityBase e, UOWDao dao);
	public void markUpdated(EntityBase e, UOWDao dao);
	public void commit();
	public void close();
}
