package test.test_hodei;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Suite de pruebas unitarias para la clase {@link Promocion}.
 *
 * <p>Cubre todos los métodos de aserción exigidos por la rúbrica:</p>
 * <ul>
 *   <li>{@code assertTrue} / {@code assertFalse}</li>
 *   <li>{@code assertEquals}</li>
 *   <li>{@code assertNull} / {@code assertNotNull}</li>
 *   <li>{@code assertSame} / {@code assertNotSame}</li>
 *   <li>{@code fail}</li>
 * </ul>
 *
 * <p>Además incluye un test con <em>mock</em> de Mockito sobre la interfaz
 * {@link GestorPromociones} para demostrar la verificación de interacciones.</p>
 *
 * <p><strong>Dependencias Maven necesarias:</strong></p>
 * <pre>{@code
 * <dependency>
 *     <groupId>junit</groupId>
 *     <artifactId>junit</artifactId>
 *     <version>4.13.2</version>
 *     <scope>test</scope>
 * </dependency>
 * <dependency>
 *     <groupId>org.mockito</groupId>
 *     <artifactId>mockito-core</artifactId>
 *     <version>4.11.0</version>
 *     <scope>test</scope>
 * </dependency>
 * }</pre>
 *
 * @author  Tu Nombre
 * @version 1.0
 * @since   2024
 * @see     Promocion
 * @see     GestorPromociones
 */
public class PromocionTest {

    /**
     * Promoción activa con un 20 % de descuento.
     * Se inicializa en {@link #setUp()} antes de cada test.
     */
    private Promocion promoActiva;

    /**
     * Promoción marcada como inactiva, usada para comprobar
     * los casos negativos de las aserciones.
     * Se inicializa en {@link #setUp()} antes de cada test.
     */
    private Promocion promoInactiva;

    // ── Preparación ────────────────────────────────────────────────────────────

    /**
     * Inicializa los objetos compartidos antes de cada método de test.
     *
     * <p>JUnit 4 llama a este método antes de ejecutar cada {@code @Test},
     * garantizando que los tests son independientes entre sí.</p>
     */
    @Before
    public void setUp() {
        // Promo válida: activa, 20 % de descuento, con código
        promoActiva   = new Promocion("Verano 2024", 20, true,  "VER2024");

        // Promo inválida: mismos datos pero desactivada
        promoInactiva = new Promocion("Expirada",    10, false, "EXP001");
    }

    // ── 1. assertTrue ──────────────────────────────────────────────────────────

    /**
     * Verifica que una promoción activa con descuento mayor que 0
     * es considerada válida por {@link Promocion#isValida()}.
     *
     * <p>Usa {@code assertTrue(boolean)} sin mensaje.</p>
     */
    @Test
    public void testPromocionActivaEsValida() {
        // promoActiva tiene activa=true y descuento=20 → isValida() debe ser true
        assertTrue(promoActiva.isValida());
    }

    /**
     * Verifica que la promoción activa devuelve {@code true} en {@link Promocion#isActiva()}.
     *
     * <p>Usa {@code assertTrue(String, boolean)} con mensaje descriptivo para
     * facilitar la localización del fallo si el test no supera.</p>
     */
    @Test
    public void testPromocionEstaActiva() {
        assertTrue("La promo 'Verano 2024' debería estar activa", promoActiva.isActiva());
    }

    // ── 2. assertFalse ─────────────────────────────────────────────────────────

    /**
     * Verifica que una promoción inactiva NO es válida aunque tenga descuento > 0.
     *
     * <p>Usa {@code assertFalse(boolean)} sin mensaje.</p>
     */
    @Test
    public void testPromocionInactivaNoEsValida() {
        // promoInactiva tiene activa=false → isValida() debe ser false
        assertFalse(promoInactiva.isValida());
    }

    /**
     * Verifica que la promoción expirada devuelve {@code false} en {@link Promocion#isActiva()}.
     *
     * <p>Usa {@code assertFalse(String, boolean)} con mensaje para identificar el fallo.</p>
     */
    @Test
    public void testPromocionExpiradaNoEstaActiva() {
        assertFalse("La promo 'Expirada' NO debería estar activa", promoInactiva.isActiva());
    }

    // ── 3. assertEquals ────────────────────────────────────────────────────────

    /**
     * Verifica que al aplicar un 20 % de descuento sobre 10,00 € el resultado es 8 €.
     *
     * <p>Se convierte a {@code long} para usar la firma
     * {@code assertEquals(long expected, long actual)} exigida en la rúbrica.</p>
     */
    @Test
    public void testAplicarDescuentoVeintePorciento() {
        double precioOriginal = 10.0;

        // 10.0 - (10.0 * 20 / 100) = 10.0 - 2.0 = 8.0 → como long es 8
        long esperado = 8L;
        assertEquals(esperado, (long) promoActiva.aplicarDescuento(precioOriginal));
    }

    /**
     * Verifica que el porcentaje de descuento almacenado al construir
     * la promoción coincide con el valor que se pasó al constructor.
     *
     * <p>Usa {@code assertEquals(String, long, long)} con mensaje.</p>
     */
    @Test
    public void testDescuentoAlmacenado() {
        assertEquals("El descuento almacenado debe ser 20", 20L, (long) promoActiva.getDescuento());
    }

    // ── 4. assertNull ──────────────────────────────────────────────────────────

    /**
     * Verifica que una promoción creada con {@code codigoPromo = null}
     * devuelve {@code null} en {@link Promocion#getCodigoPromo()}.
     *
     * <p>Usa {@code assertNull(Object)} sin mensaje.</p>
     */
    @Test
    public void testCodigoPromoEsNuloCuandoNoSeAsigna() {
        // Se pasa null explícitamente al constructor como código de promo
        Promocion sinCodigo = new Promocion("Flash", 5, true, null);
        assertNull(sinCodigo.getCodigoPromo());
    }

    /**
     * Mismo caso que el anterior pero usando {@code assertNull(String, Object)}
     * para mostrar el mensaje de error si el campo no es nulo.
     */
    @Test
    public void testCodigoPromoNuloConMensaje() {
        Promocion sinCodigo = new Promocion("Flash", 5, true, null);
        assertNull("El código debería ser null al no asignarse", sinCodigo.getCodigoPromo());
    }

    // ── 5. assertNotNull ───────────────────────────────────────────────────────

    /**
     * Verifica que el objeto {@code promoActiva} creado en {@link #setUp()}
     * no es {@code null}.
     *
     * <p>Usa {@code assertNotNull(Object)} sin mensaje.</p>
     */
    @Test
    public void testPromocionCreadaNoEsNula() {
        assertNotNull(promoActiva);
    }

    /**
     * Verifica que el nombre de la promoción activa no es {@code null},
     * usando {@code assertNotNull(String, Object)} con mensaje descriptivo.
     */
    @Test
    public void testNombrePromocionNoEsNuloConMensaje() {
        assertNotNull("El nombre de la promoción no puede ser null", promoActiva.getNombre());
    }

    // ── 6. assertSame ──────────────────────────────────────────────────────────

    /**
     * Verifica que dos variables que apuntan al mismo objeto en memoria
     * superan la comprobación de identidad referencial.
     *
     * <p>La asignación {@code Promocion referencia = promoActiva} no crea
     * un nuevo objeto, solo copia la referencia. Ambas variables apuntan
     * a la misma dirección de memoria en el heap, por lo que
     * {@code assertSame} debe pasar.</p>
     *
     * <p>Usa {@code assertSame(Object, Object)} sin mensaje.</p>
     */
    @Test
    public void testMismaReferenciaDeObjeto() {
        // Copia de referencia, NO copia del objeto
        Promocion referencia = promoActiva;
        assertSame(promoActiva, referencia);
    }

    /**
     * Mismo caso que el anterior pero usando {@code assertSame(String, Object, Object)}
     * con mensaje para identificar el fallo si no son el mismo objeto.
     */
    @Test
    public void testMismaReferenciaConMensaje() {
        Promocion referencia = promoActiva;
        assertSame("Deben ser exactamente el mismo objeto en memoria", promoActiva, referencia);
    }

    // ── 7. assertNotSame ───────────────────────────────────────────────────────

    /**
     * Verifica que dos instancias creadas con {@code new}, aunque tengan
     * exactamente los mismos datos, son objetos distintos en memoria.
     *
     * <p>{@code assertNotSame} comprueba la identidad referencial ({@code !=}),
     * no la igualdad de contenido ({@code equals}), por lo que debe pasar
     * siempre que se usen dos llamadas a {@code new}.</p>
     *
     * <p>Usa {@code assertNotSame(Object, Object)} sin mensaje.</p>
     */
    @Test
    public void testDiferentesInstanciasNoSonLaMisma() {
        // Nuevo objeto con los mismos parámetros → diferente dirección en heap
        Promocion copia = new Promocion("Verano 2024", 20, true, "VER2024");
        assertNotSame(promoActiva, copia);
    }

    /**
     * Verifica que {@code promoActiva} y {@code promoInactiva}, creadas como
     * objetos separados, no son la misma referencia.
     *
     * <p>Usa {@code assertNotSame(String, Object, Object)} con mensaje.</p>
     */
    @Test
    public void testDosPromosDistintasConMensaje() {
        assertNotSame("promoActiva y promoInactiva deben ser objetos distintos",
                      promoActiva, promoInactiva);
    }

    // ── 8. fail ────────────────────────────────────────────────────────────────

    /**
     * Verifica que el constructor lanza {@link IllegalArgumentException}
     * cuando el descuento supera el 100 %.
     *
     * <p>El patrón usado es:</p>
     * <ol>
     *   <li>Se intenta ejecutar la operación que debería fallar.</li>
     *   <li>Si <strong>no</strong> lanza la excepción esperada, se llama a
     *       {@code fail(String)} para que el test falle con un mensaje claro.</li>
     *   <li>Si sí lanza la excepción, el bloque {@code catch} la captura
     *       y el test termina con éxito.</li>
     * </ol>
     */
    @Test
    public void testDescuentoMayorQueCienLanzaExcepcion() {
        try {
            // Un descuento del 150 % es inválido → debe lanzar IllegalArgumentException
            new Promocion("Invalida", 150, true, "INV");

            // Si llegamos aquí es que NO se lanzó la excepción → fallo explícito
            fail("Debería haberse lanzado IllegalArgumentException para descuento=150");
        } catch (IllegalArgumentException e) {
            // Comportamiento esperado: la excepción fue lanzada → el test pasa
        }
    }

    /**
     * Verifica que el constructor también lanza {@link IllegalArgumentException}
     * cuando el descuento es negativo.
     *
     * <p>Usa {@code fail()} sin mensaje en la variante más simple.</p>
     */
    @Test
    public void testDescuentoNegativoLanzaExcepcion() {
        try {
            // Un descuento de -5 % es inválido
            new Promocion("Negativa", -5, true, "NEG");

            // Si no se lanzó la excepción, el test debe fallar
            fail("Debería haberse lanzado IllegalArgumentException para descuento=-5");
        } catch (IllegalArgumentException e) {
            // Comportamiento esperado
        }
    }

    // ── 9. Mock con Mockito ────────────────────────────────────────────────────

    /**
     * Verifica el comportamiento del sistema usando un <em>mock</em> de
     * {@link GestorPromociones} creado con Mockito.
     *
     * <p>El test demuestra tres conceptos clave de Mockito:</p>
     * <ol>
     *   <li><strong>Creación del mock:</strong> {@code mock(Class)} genera
     *       un objeto falso que implementa la interfaz sin código real.</li>
     *   <li><strong>Configuración (stubbing):</strong> {@code when(...).thenReturn(...)}
     *       define qué debe devolver el mock ante llamadas concretas.</li>
     *   <li><strong>Verificación:</strong> {@code verify(..., times(n))} comprueba
     *       que un método fue invocado exactamente {@code n} veces.</li>
     * </ol>
     *
     * <p>Los asserts usados en este test son {@code assertNotNull}, {@code assertEquals},
     * {@code assertNull} y {@code assertTrue}, reutilizando los métodos de la rúbrica
     * en un contexto de integración con la dependencia mockeada.</p>
     */
    @Test
    public void testMockGestorPromociones() {

        // ── 1. Crear el mock ──────────────────────────────────────────────────
        // Mockito genera una implementación vacía de la interfaz en tiempo de ejecución
        GestorPromociones gestorMock = mock(GestorPromociones.class);

        // ── 2. Configurar comportamiento (stubbing) ───────────────────────────
        // Cuando se busque "VER2024" → devolver promoActiva
        when(gestorMock.buscarPorCodigo("VER2024")).thenReturn(promoActiva);

        // Cuando se busque un código que no existe → devolver null
        when(gestorMock.buscarPorCodigo("INEXISTENTE")).thenReturn(null);

        // Cuando se aplique la promo con precio 10.0 → indicar éxito (true)
        when(gestorMock.aplicarPromocion("VER2024", 10.0)).thenReturn(true);

        // ── 3. Ejecutar las llamadas al mock ──────────────────────────────────
        Promocion resultado     = gestorMock.buscarPorCodigo("VER2024");
        Promocion noEncontrada  = gestorMock.buscarPorCodigo("INEXISTENTE");
        boolean   promoAplicada = gestorMock.aplicarPromocion("VER2024", 10.0);

        // ── 4. Verificar resultados con asserts de la rúbrica ─────────────────

        // assertNotNull: el mock devolvió un objeto para el código conocido
        assertNotNull("El mock debe devolver la promo para VER2024", resultado);

        // assertEquals: el descuento de la promo devuelta es 20
        assertEquals("El descuento de la promo devuelta debe ser 20",
                     20L, (long) resultado.getDescuento());

        // assertNull: código inexistente → el mock devuelve null
        assertNull("Un código inexistente debe devolver null", noEncontrada);

        // assertTrue: la operación de aplicar la promo fue exitosa
        assertTrue("La promo debe haberse aplicado correctamente", promoAplicada);

        // ── 5. Verificar interacciones con el mock ────────────────────────────
        // Comprueba que cada método fue invocado exactamente una vez
        verify(gestorMock, times(1)).buscarPorCodigo("VER2024");
        verify(gestorMock, times(1)).buscarPorCodigo("INEXISTENTE");
        verify(gestorMock, times(1)).aplicarPromocion("VER2024", 10.0);
    }
}
