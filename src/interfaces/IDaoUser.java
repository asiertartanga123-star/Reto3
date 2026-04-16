package interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Entrada;
import model.Usuario;
import model.user.Ranking;

public interface IDaoUser {
	public Usuario obtenerUsuario(String usuario) throws Exception;
	public ArrayList<model.user.TicketInfo> obtenerTicketsUsuario(String usuario) throws Exception;
	public ArrayList<Ranking> mostrarRanking(String user_name, LocalDate fecha) throws Exception;
	public boolean actualizarUsuario(Usuario user) throws Exception;
	public boolean eliminarEntrada(Entrada entrada) throws Exception;
}
