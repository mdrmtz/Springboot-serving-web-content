package globant.svp.core.service;

import globant.svp.core.domain.Entity;

import java.util.List;
import java.util.Map;

public interface GenericService {

	public Entity addService(Entity entity);

	public void deleteService(Entity entity);

	public List<? extends Entity> getALlEntity();

	public List<? extends Entity> getAllEntityByRequestParam(
			Map<String, String> requestParam);
}
