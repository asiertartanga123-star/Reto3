package model;

/**
 * Representa una promoción o descuento aplicable a la compra de entradas de cine.
 *
 * <p>Cada promoción tiene un nombre identificativo, un porcentaje de descuento,
 * un estado de activación y, opcionalmente, un código alfanumérico que el cliente
 * puede introducir en taquilla o en la web.</p>
 *
 * <p>El porcentaje de descuento debe estar siempre comprendido entre {@code 0} y
 * {@code 100} (ambos inclusive). Si se intenta crear una promoción fuera de ese
 * rango se lanzará una {@link IllegalArgumentException}.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>{@code
 * Promocion promo = new Promocion("Verano 2024", 20, true, "VER2024");
 * double precioFinal = promo.aplicarDescuento(10.00); // → 8.00
 * }</pre>
 *
 * @author  Hodei
 * @version 1.0
 * @since   2024
 */
public class Promocion {

    /**
     * Nombre descriptivo de la promoción.
     * Se muestra al cliente en taquilla y en la app del cine.
     */
    private String nombre;

    /**
     * Porcentaje de descuento que aplica la promoción.
     * Valor entero comprendido entre {@code 0} y {@code 100}.
     */
    private int descuento;

    /**
     * Indica si la promoción está actualmente activa.
     * Una promoción inactiva no aplica ningún descuento aunque se invoque
     * {@link #aplicarDescuento(double)}.
     */
    private boolean activa;

    /**
     * Código alfanumérico que el cliente puede introducir para activar la promoción.
     * Puede ser {@code null} si la promoción se aplica de forma automática
     * sin necesidad de código.
     */
    private String codigoPromo;

    /**
     * Número mínimo de entradas que debe adquirir el cliente para que la
     * promoción sea aplicable. El valor por defecto es {@code 1}.
     */
    private int minimoEntradas;

    // ── Constructor ────────────────────────────────────────────────────────────

    /**
     * Construye una nueva {@code Promocion} con los datos indicados.
     *
     * <p>El número mínimo de entradas se inicializa automáticamente a {@code 1}.
     * Puede modificarse posteriormente con {@link #setMinimoEntradas(int)}.</p>
     *
     * @param nombre      nombre descriptivo de la promoción; no debe ser {@code null}
     * @param descuento   porcentaje de descuento, entre {@code 0} y {@code 100}
     * @param activa      {@code true} si la promoción está en vigor;
     *                    {@code false} si está desactivada
     * @param codigoPromo código alfanumérico de la promoción; puede ser {@code null}
     *                    si no requiere código de activación
     * @throws IllegalArgumentException si {@code descuento} es menor que {@code 0}
     *                                  o mayor que {@code 100}
     */
    public Promocion(String nombre, int descuento, boolean activa, String codigoPromo) {
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException(
                "El descuento debe estar entre 0 y 100, pero se recibió: " + descuento
            );
        }
        this.nombre         = nombre;
        this.descuento      = descuento;
        this.activa         = activa;
        this.codigoPromo    = codigoPromo;
        this.minimoEntradas = 1;
    }

    // ── Métodos de negocio ─────────────────────────────────────────────────────

    /**
     * Aplica el descuento de esta promoción al precio indicado.
     *
     * <p>Si la promoción está inactiva ({@link #isActiva()} devuelve {@code false}),
     * el precio se devuelve sin modificar.</p>
     *
     * <p>Fórmula aplicada:</p>
     * <pre>{@code
     * precioFinal = precio - (precio * descuento / 100.0)
     * }</pre>
     *
     * @param precio precio original en euros; debe ser mayor o igual a {@code 0}
     * @return precio resultante tras aplicar el descuento, o el precio original
     *         si la promoción no está activa
     */
    public double aplicarDescuento(double precio) {
        // Si la promoción no está activa, se devuelve el precio sin modificar
        if (!activa) return precio;

        // Aplica la reducción porcentual al precio original
        return precio - (precio * descuento / 100.0);
    }

    /**
     * Indica si esta promoción se considera válida para ser aplicada.
     *
     * <p>Una promoción es válida cuando cumple <strong>ambas</strong> condiciones:</p>
     * <ul>
     *   <li>Está marcada como activa ({@code activa == true}).</li>
     *   <li>Su porcentaje de descuento es mayor que {@code 0}.</li>
     * </ul>
     *
     * @return {@code true} si la promoción está activa y su descuento es mayor que cero;
     *         {@code false} en cualquier otro caso
     */
    public boolean isValida() {
        // Una promo con descuento 0 no tiene sentido aplicar aunque esté "activa"
        return activa && descuento > 0;
    }

    // ── Getters ────────────────────────────────────────────────────────────────

    /**
     * Devuelve el nombre descriptivo de la promoción.
     *
     * @return nombre de la promoción; no será {@code null} si se construyó correctamente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el porcentaje de descuento de la promoción.
     *
     * @return entero entre {@code 0} y {@code 100} que representa el descuento
     */
    public int getDescuento() {
        return descuento;
    }

    /**
     * Indica si la promoción está actualmente activa.
     *
     * @return {@code true} si la promoción está activa; {@code false} si está desactivada
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Devuelve el código alfanumérico de la promoción.
     *
     * @return código de la promoción, o {@code null} si no tiene código asignado
     */
    public String getCodigoPromo() {
        return codigoPromo;
    }

    /**
     * Devuelve el número mínimo de entradas requerido para aplicar la promoción.
     *
     * @return número mínimo de entradas; el valor por defecto es {@code 1}
     */
    public int getMinimoEntradas() {
        return minimoEntradas;
    }

    // ── Setters ────────────────────────────────────────────────────────────────

    /**
     * Activa o desactiva la promoción.
     *
     * @param activa {@code true} para activarla; {@code false} para desactivarla
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Asigna o actualiza el código alfanumérico de la promoción.
     *
     * @param codigoPromo nuevo código; puede ser {@code null} para indicar
     *                    que la promoción no requiere código
     */
    public void setCodigoPromo(String codigoPromo) {
        this.codigoPromo = codigoPromo;
    }

    /**
     * Establece el número mínimo de entradas necesario para poder aplicar
     * la promoción.
     *
     * @param minimoEntradas número mínimo de entradas; debe ser mayor que {@code 0}
     */
    public void setMinimoEntradas(int minimoEntradas) {
        this.minimoEntradas = minimoEntradas;
    }

    // ── Object ─────────────────────────────────────────────────────────────────

    /**
     * Devuelve una representación textual de la promoción con todos sus campos,
     * útil para depuración y registros de log.
     *
     * @return cadena con el formato:
     *         {@code Promocion{nombre='X', descuento=Y%, activa=Z, codigo='W'}}
     */
    @Override
    public String toString() {
        return "Promocion{nombre='" + nombre + "', descuento=" + descuento
               + "%, activa=" + activa + ", codigo='" + codigoPromo + "'}";
    }
}
