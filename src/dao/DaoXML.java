package dao;

import model.Entrada;
import model.Pelicula;
import model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * DAO exclusivo para la exportación XML.
 * Recupera todos los datos necesarios de la base de datos
 * sin interferir con los DAOs existentes.
 */
public class DaoXML {

    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    public DaoXML() {
        this.configFile = ResourceBundle.getBundle("configGlobal");
        this.urlBD      = this.configFile.getString("Conn");
        this.userBD     = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    /** Obtiene todas las películas */
    public List<Pelicula> obtenerTodasPeliculas() throws Exception {
        List<Pelicula> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
             PreparedStatement stmt = con.prepareStatement(Sentencias.GET_ALL_PELICULAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("ID_PELICULA"));
                p.setTitulo(rs.getString("TITULO"));
                p.setDuracionMin(rs.getInt("DURACION_MIN"));
            //    p.setSinopsis(rs.getString("SINOPSIS"));
                p.setGenero(rs.getString("GENERO"));
                p.setDirector(rs.getString("DIRECTOR"));
                p.setValoracion(rs.getInt("VALORACION"));
                p.setRutaImg(rs.getString("RUTAIMG"));
                lista.add(p);
            }
        }
        return lista;
    }

    /** Obtiene todos los usuarios */
    public List<Usuario> obtenerTodosUsuarios() throws Exception {
        List<Usuario> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
             PreparedStatement stmt = con.prepareStatement(Sentencias.GET_ALL_USUARIOS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setUsuario(rs.getString("USUARIO"));
                u.setNombre(rs.getString("NOMBRE"));
                u.setApellido(rs.getString("APELLIDO"));
                u.setCorreo(rs.getString("CORREO"));
                u.setEdad(rs.getInt("EDAD"));
                u.setContrasena(rs.getString("CONTRASENA"));
                lista.add(u);
            }
        }
        return lista;
    }

    /** Obtiene todas las entradas */
    public List<Entrada> obtenerTodasEntradas() throws Exception {
        List<Entrada> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
             PreparedStatement stmt = con.prepareStatement(Sentencias.GET_ALL_ENTRADAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entrada e = new Entrada();
                e.setUsuario(rs.getString("USUARIO"));
                e.setNumSala(rs.getInt("NUM_SALA"));
                e.setIdPelicula(rs.getInt("ID_PELICULA"));
                e.setNumButaca(rs.getInt("NUM_BUTACA"));
                e.setPrecio(rs.getInt("PRECIO"));
                e.setFechaTransmision(rs.getTimestamp("FECHA_TRANSMISION").toLocalDateTime());
                e.setFechaAdquiere(rs.getTimestamp("FECHA_ADQUIERE").toLocalDateTime());
                lista.add(e);
            }
        }
        return lista;
    }
}