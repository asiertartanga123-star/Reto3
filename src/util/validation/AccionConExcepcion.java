package util.validation;

@FunctionalInterface
public interface AccionConExcepcion {
	void ejecutar() throws Exception;
}