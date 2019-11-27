package mediadores;

import enums.EntityActionEnum;
import interfaces.Mediator;

public class EntityMediator implements Mediator {

	@Override
	public EntityActionEnum notificar(EntityActionEnum action) {
		switch (action) {
		case ATACAR:

			break;
		case DEFENDER:

			break;

		case DESVIAR:

			break;

		case RECEBER_DANO:

			break;

		case ALTERAR_VIDA_MAX:

			break;
		case GANHAR_CHANCE_ACERTO:

			break;

		case GANHAR_VIDA:

			break;
		case PERDER_VIDA:

			break;

		case GANHAR_ATAQUE:

			break;
		case PERDER_ATAQUE:

			break;

		case GANHAR_DEFESA:

			break;

		case PERDER_DEFESA:

			break;

		case PERDER_CHANCE_ACERTO:

			break;
		case GANHAR_VELOCIDADE_MOVIMENTO:

			break;
		case PERDER_VELOCIDADE_MOVIMENTO:

			break;
		default:
			break;
		}
		return action;
	}



}
