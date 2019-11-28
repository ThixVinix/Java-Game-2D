package interfaces;

import abstracts.Entity;
import enums.EntityActionEnum;

public interface Mediator {

	public boolean notify(EntityActionEnum action, Entity entitySolicitante);
	
}
