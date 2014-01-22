package pl.ayz.shlizer.pjwstk.dao;


public interface UOWDao {

	public void execAdd(EntityBase e);
	public void execDelete(EntityBase e);
	public void execUpdate(EntityBase e);
}
