package pl.ayz.shlizer.pjwstk.dao;

public interface UOWDao {

	public void persistAdd(EntityBase ent);
	public void persistDelete(EntityBase ent);
	public void persistUpdate(EntityBase ent);
}
