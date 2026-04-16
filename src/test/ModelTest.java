package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Entrada;

public class ModelTest {
	
	@Test
	public void testEntrada() {
		Entrada entrada = new Entrada();

		LocalDateTime fechaTransmision = LocalDateTime.now();
		LocalDateTime fechaAdquiere = LocalDateTime.now();

		entrada.setUsuario("juan01");
		entrada.setNumSala(1);
		entrada.setIdPelicula(101);
		entrada.setNumButaca(15);
		entrada.setPrecio(10);
		entrada.setFechaTransmision(fechaTransmision);
		entrada.setFechaAdquiere(fechaAdquiere);

		assertAll("Test setters entrada", () -> assertEquals("juan01", entrada.getUsuario()),
				() -> assertEquals(1, entrada.getNumSala()), () -> assertEquals(101, entrada.getIdPelicula()),
				() -> assertEquals(15, entrada.getNumButaca()), () -> assertEquals(10, entrada.getPrecio()),
				() -> assertEquals(fechaTransmision, entrada.getFechaTransmision()),
				() -> assertEquals(fechaAdquiere, entrada.getFechaAdquiere())
		);
	}
}
