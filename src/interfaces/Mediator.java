package interfaces;

import enums.EntityActionEnum;

public interface Mediator {

	EntityActionEnum notificar(EntityActionEnum action);
	
}
