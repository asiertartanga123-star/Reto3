package exception;

public class SalaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public enum TipoError {
        SALA_NO_ENCONTRADA,
        AFORO_INVALIDO,
        SALA_LLENA,
        SALA_CERRADA,
        ID_INVALIDO
    }

    private final TipoError tipo;
    private final int idSala;

    public SalaException(TipoError tipo, int idSala) {
        super(buildMessage(tipo, idSala));
        this.tipo   = tipo;
        this.idSala = idSala;
    }

    private static String buildMessage(TipoError tipo, int idSala) {
        switch (tipo) {
            case SALA_NO_ENCONTRADA: return "Room #" + idSala + " does not exist in the database.";
            case AFORO_INVALIDO:     return "Room #" + idSala + " has an invalid capacity (must be > 0).";
            case SALA_LLENA:         return "Room #" + idSala + " is at full capacity.";
            case SALA_CERRADA:       return "Room #" + idSala + " is currently closed.";
            case ID_INVALIDO:        return "ID " + idSala + " is not a valid room number.";
            default:                 return "Unknown error in room #" + idSala + ".";
        }
    }

    public TipoError getTipo()  { return tipo;   }
    public int       getIdSala(){ return idSala;  }
}
