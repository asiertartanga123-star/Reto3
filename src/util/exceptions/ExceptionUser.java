package util.exceptions;

import java.time.Duration;
import java.time.LocalDateTime;

public class ExceptionUser {
	public static void validarReembolso(model.user.TicketInfo ticket) throws ExceptionToloTolo {

		LocalDateTime fechaTransmision = ticket.getFecha_emision();
		LocalDateTime ahora = LocalDateTime.now();

		long minutos = Duration.between(fechaTransmision, ahora).toMinutes();

		if (minutos > 30) {
			throw new ExceptionToloTolo(
					"El ticket no puede eliminarse porque han pasado mas de 30 minutos desde la transmision");
		}
	}
}
