package interfaces;

import abstracts.Entity;
import enums.EntityActionEnum;

public interface Mediator {

	public boolean notify(Entity entitySolicitante, EntityActionEnum action);
	
}
