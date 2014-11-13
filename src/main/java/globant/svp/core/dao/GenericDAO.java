package globant.svp.core.dao;

import globant.svp.core.domain.Entity;

import java.util.List;
import java.util.Map;

public interface GenericDAO {
	
	public void addEntity(Entity entity);
	public void deleteEntity(Entity entity);
	public List<? extends Entity> getAllEntity();
	public List<? extends Entity> getAllEntityByRequestParam(Map<String, String> requestParam);
	public void deleteEntityById(String id, String value);
}
