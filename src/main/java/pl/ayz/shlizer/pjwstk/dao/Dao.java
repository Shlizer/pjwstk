package pl.ayz.shlizer.pjwstk.dao;

import java.util.List;

public interface Dao<E extends EntityBase> {

	public void save(E e);
	public void delete(E e);
	public List<E> getAll();
	public E get(int id);
	public void update(E e);
}
