package tests.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.validation.ValidacionAdmin;

class ValidacionAdminTests {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	// ===========================================
    // 		    AssertTrue y AssertFalse
    // ===========================================
	
	@Test
	void testEsCorreoValido() {
		assertTrue(ValidacionAdmin.esCorreoValido("Benito123@gmail.com"));
		assertFalse(ValidacionAdmin.esCorreoValido("Benito123mail.com"));
	}
	
	// ===========================================
    // 		          AssertEquals
    // ===========================================
	
	@Test
	void testUsernameVacio() {
	    assertEquals("Empty user", ValidacionAdmin.errorUsuario("", "Juan", "Perez", "juan@gmail.com", 25, "1234"));
	}
	
	@Test
	void testTipoInvalido() {
		assertEquals("Must be 2D or 3D", ValidacionAdmin.errorSala(2, 120, "4D"));
	}
	
	// ===========================================
    // 		          AssertThrows
    // ===========================================
	
	@Test
	void testEnteroInvalido() {
	    assertThrows(IllegalArgumentException.class, () -> { ValidacionAdmin.validarEntero("abc");});
	}
	
	// ===========================================
    // 		          AssertNull
    // ===========================================
	
	@Test
	void testUsuarioValido() {
	    assertNull(ValidacionAdmin.errorUsuario("user1", "Juan", "Perez", "juan@gmail.com", 25, "1234"));
	}
	
	@Test
	void TestPeliculaValida() {
	    assertNull(ValidacionAdmin.errorPelicula("Matrix", 120, "Unos agentes..", 9, "Accion", "Wachowski"));
	}
	
}
