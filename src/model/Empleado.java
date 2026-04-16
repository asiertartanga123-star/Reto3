package model;

import java.util.Objects;

/**
 * Clase que representa un Empleado dentro del sistema de gestión.
 */
public class Empleado {

    private int id;
    private String nombre;
    private String departamento;
    private double salario;
    private boolean activo;

    // ─── Constructor ────────────────────────────────────────────────────────────

    public Empleado(int id, String nombre, String departamento, double salario) {
       
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.activo = true;
    }

 
    public void aplicarAumento(double porcentaje) {
        if (porcentaje <= 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }
        this.salario += this.salario * (porcentaje / 100);
    }

    public void aplicarReduccion(double porcentaje) {
        
        double nuevoSalario = this.salario - this.salario * (porcentaje / 100);
        this.salario = nuevoSalario;
    }

    public void transferir(String nuevoDepartamento) {
        this.departamento = nuevoDepartamento.trim();
    }

 
    public void darDeBaja() {
       
        this.activo = false;
    }

    public void reactivar() {
      
        this.activo = true;
    }

   
    public String getDescripcion() {
        return String.format("Empleado[id=%d, nombre='%s', departamento='%s', salario=%.2f, activo=%b]",
                id, nombre, departamento, salario, activo);
    }

    // ─── Getters y Setters ───────────────────────────────────────────────────────

    public int getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre.trim();
    }

    public String getDepartamento() { return departamento; }

    public double getSalario() { return salario; }

    public boolean isActivo() { return activo; }

    // ─── equals / hashCode / toString ───────────────────────────────────────────

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado e = (Empleado) o;
        return id == e.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return getDescripcion(); }
}