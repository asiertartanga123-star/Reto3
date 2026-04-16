package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Empleado;

class EmpleadoTest {

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado(1, "Ana García", "Ingeniería", 3000.00);
    }

    @Test
    void testConstructorCorrecto() {
        assertEquals(1, empleado.getId());
        assertEquals("Ana García", empleado.getNombre());
        assertEquals("Ingeniería", empleado.getDepartamento());
        assertEquals(3000.00, empleado.getSalario(), 0.001);
        assertTrue(empleado.isActivo());
    }

    @Test
    void testConstructorIdInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Empleado(0, "Juan", "RRHH", 2000));
    }

    @Test
    void testConstructorNombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Empleado(2, "", "RRHH", 2000));
    }

    @Test
    void testConstructorSalarioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Empleado(2, "Juan", "RRHH", -500));
    }

    @Test
    void testAplicarAumento() {
        empleado.aplicarAumento(10);
        assertEquals(3300.00, empleado.getSalario(), 0.001);
    }

    @Test
    void testAplicarAumentoPorcentajeInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> empleado.aplicarAumento(0));
    }

    @Test
    void testAplicarReduccion() {
        empleado.aplicarReduccion(50);
        assertEquals(1500.00, empleado.getSalario(), 0.001);
    }

    @Test
    void testAplicarReduccionPorcentajeInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> empleado.aplicarReduccion(-10));
    }

    @Test
    void testTransferir() {
        empleado.transferir("Marketing");
        assertEquals("Marketing", empleado.getDepartamento());
    }

    @Test
    void testTransferirDepartamentoVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> empleado.transferir(""));
    }

    @Test
    void testDarDeBaja() {
        empleado.darDeBaja();
        assertFalse(empleado.isActivo());
    }

    @Test
    void testDarDeBajaDobleVezLanzaExcepcion() {
        empleado.darDeBaja();
        assertThrows(IllegalStateException.class, () -> empleado.darDeBaja());
    }

    @Test
    void testReactivar() {
        empleado.darDeBaja();
        empleado.reactivar();
        assertTrue(empleado.isActivo());
    }

    @Test
    void testEqualsConMismoId() {
        Empleado otro = new Empleado(1, "Otro Nombre", "Otro Dpto", 5000);
        assertEquals(empleado, otro);
    }

    @Test
    void testNotEqualsConDistintoId() {
        Empleado otro = new Empleado(2, "Ana García", "Ingeniería", 3000);
        assertNotEquals(empleado, otro);
    }
}

