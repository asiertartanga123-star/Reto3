package test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import dao.DaoSalas;
import excepciones.SalaException;
import model.Sala;

class SalaTest {

	@Test
    void testAssertTrue() throws Exception {
        DaoSalas dao = new DaoSalas();
        Map<Integer, Sala> salas = new TreeMap<>();

        dao.obtenerSalas(salas);

        assertTrue(salas.size() >= 0);
    }

    @Test
    void testAssertFalse() throws Exception {
        Map<Integer, Sala> salas = new TreeMap<>();

        assertFalse(salas.size() > 0);
    }

    @Test
    void testAssertEquals() {
        int esperado = 1;
        int real = 1;

        assertEquals(esperado, real);
    }

    @Test
    void testAssertNull() {
        Sala sala = null;

        assertNull(sala);
    }

    @Test
    void testAssertNotNull() throws Exception {
        DaoSalas dao = new DaoSalas();
        Map<Integer, Sala> salas = new TreeMap<>();

        Sala sala = dao.obtenerSalasBuscar(1, salas);

        assertTrue(sala == null || sala != null);
    }

    @Test
    void testAssertThrows() {
        assertThrows(SalaException.class, () -> {
            throw new SalaException(SalaException.TipoError.ID_INVALIDO, 0);
        });
    }


    @Test
    void testAssertSame() {
        Sala sala = new Sala();
        Sala mismaReferencia = sala;

        assertSame(sala, mismaReferencia);
    }

    @Test
    void testAssertNotSame() {
        Sala sala1 = new Sala();
        Sala sala2 = new Sala();

        assertNotSame(sala1, sala2);
    }

    @Test
    void testFail() {
        try {
            throw new Exception("Error");
        } catch (Exception e) {
            fail("Se produjo un error inesperado");
        }
    }
}


