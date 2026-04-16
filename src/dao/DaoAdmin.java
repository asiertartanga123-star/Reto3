package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DaoAdmin  {

    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    public DaoAdmin() {
        this.configFile = ResourceBundle.getBundle("configGlobal");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlBD, userBD, passwordBD);
    }
    
    public List<Object[]> ejecutarConsulta(String sql) throws SQLException {
        List<Object[]> filas = new ArrayList<>();
        
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1); // getObject lee cualquier tipo automáticamente
                }
                filas.add(fila);
            }
        }
        return filas;
    }
    
    // =========================
    // 			USUARIO
    // =========================

    public void insertarUsuario(String userName, String nombre, String apellido, String correo, int edad, String contraseña) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.INSERT_USUARIO)) {

            ps.setString(1, userName);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, correo);
            ps.setInt(5, edad);
            ps.setString(6, contraseña);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarUsuario(String userName) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.BORRAR_USUARIO)) {

            ps.setString(1, userName);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarUsuario(String nombre, String apellido, String correo, int edad, String contraseña, String userName) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.ACTUALIZAR_USUARIO)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setInt(4, edad);
            ps.setString(5, contraseña);
            ps.setString(6, userName);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 		   PELICULA
    // =========================

    public void insertarPelicula(String titulo, int duracion, String sinopsis, int valoracion, String genero, String director) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.INSERT_PELICULA)) {

            ps.setString(1, titulo);
            ps.setInt(2, duracion);
            ps.setString(3, sinopsis);
            ps.setInt(4, valoracion);
            ps.setString(5, genero);
            ps.setString(6, director);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarPelicula(int idPelicula) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.BORRAR_PELICULA)) {

            ps.setInt(1, idPelicula);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarPelicula(int idPelicula, String titulo, int duracion, String sinopsis, int valoracion, String genero, String director) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.ACTUALIZAR_PELICULA)) {

            ps.setString(1, titulo);
            ps.setInt(2, duracion);
            ps.setString(3, sinopsis);
            ps.setInt(4, valoracion);
            ps.setString(5, genero);
            ps.setString(6, director);
            ps.setInt(7, idPelicula);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // =========================
    // 			 SALA
    // =========================

    public void insertarSala(int numSala, int aforo, String tipo) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.INSERT_SALA)) {

            ps.setInt(1, numSala);
            ps.setInt(2, aforo);
            ps.setString(3, tipo); 

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarSala(int numSala) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.BORRAR_SALA)) {

            ps.setInt(1, numSala);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarSala(int numSala, int aforo, String tipo) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.ACTUALIZAR_SALA)) {

            ps.setInt(1, aforo);
            ps.setString(2, tipo);
            ps.setInt(3, numSala);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    //          ENTRADA
    // =========================

    public void insertarEntrada(String userName, int numSala, int idPelicula, int butaca, int precio, Date fechaTr, Date fechaAd) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.INSERT_ENTRADA)) {

            ps.setString(1, userName);
            ps.setInt(2, numSala);
            ps.setInt(3, idPelicula);
            ps.setInt(4, butaca);
            ps.setInt(5, precio);
            ps.setDate(6, fechaTr);
            ps.setDate(7, fechaAd);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarEntrada(String userName, int numSala, int idPelicula, Date fecha) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(Sentencias.BORRAR_ENTRADA)) {

            ps.setString(1, userName);
            ps.setInt(2, numSala);
            ps.setInt(3, idPelicula);
            ps.setDate(4, fecha);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarEntrada(String userName, int numSala, int idPelicula, Date fecha,
            int butaca, int precio) {
				
    	try (Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement(Sentencias.ACTUALIZAR_ENTRADA)) {
				
			ps.setInt(1, butaca);
			ps.setInt(2, precio);
			
			ps.setString(3, userName);
			ps.setInt(4, numSala);
			ps.setInt(5, idPelicula);
			ps.setDate(6, fecha);
				
			ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        
}
