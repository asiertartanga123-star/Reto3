package exportadorXML;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Entrada;
import model.Pelicula;
import model.Usuario;

/**
 * Clase encargada de exportar el catálogo del cine a un fichero XML.
 * <p>
 * Genera un documento XML estructurado con tres secciones principales:
 * clientes, películas y entradas. Aplica validaciones sobre los datos
 * antes de escribirlos, garantizando su conformidad con el esquema XSD asociado.
 * </p>
 */
public class ExportadorXML {

    /**
     * Lista de géneros cinematográficos permitidos.
     * Corresponde a la restricción de enumeración definida en el XSD
     * para el elemento {@code <Genero>}.
     */
    private static final List<String> GENEROS_VALIDOS =
        List.of("TERROR", "COMEDIA", "DRAMA", "ACCION", "CIENCIA_FICCION");
    /**
     * Expresión regular que valida el formato de una dirección de correo electrónico.
     * Corresponde a la restricción de patrón definida en el XSD
     * para el elemento {@code <Correo>}.
     */
    private static final String PATRON_CORREO =
            "[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}";

 

    /**
     * Genera un fichero XML con el catálogo completo del cine.
     * <p>
     * El documento resultante tiene la siguiente estructura:
     * <pre>
     * {@code
     * <Cine version="1.0">
     *   <Clientes>
     *     <Cliente User="..."> ... </Cliente>
     *   </Clientes>
     *   <Peliculas>
     *     <Pelicula id="..."> ... </Pelicula>
     *   </Peliculas>
     *   <Entradas>
     *     <Entrada tipo="..."> ... </Entrada>
     *   </Entradas>
     * </Cine>
     * }
     * </pre>
     * Antes de invocar este método se debe haber realizado la recopilación
     * de películas, clientes y entradas, almacenando cada colección en su lista correspondiente.
     * </p>
     *
     * @param listaPeliculas lista de películas a exportar
     * @param listaUsuarios  lista de usuarios/clientes a exportar
     * @param listaEntradas  lista de entradas vendidas a exportar
     * @param ruta           ruta del sistema de ficheros donde se guardará el XML generado
     * @throws IllegalArgumentException si algún dato no supera las validaciones
     *                                  (correo inválido, edad fuera de rango, género no permitido,
     *                                  valoración fuera de rango o nombre de usuario incorrecto)
     */

    public void exportarCatalogo(List<Pelicula> listaPeliculas,
                                 List<Usuario>  listaUsuarios,
                                 List<Entrada>  listaEntradas,
                                 String         ruta) {

        try {
            /* Inicialización del constructor de documentos DOM */
            DocumentBuilderFactory tolotolo_cine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = tolotolo_cine.newDocumentBuilder();

            Document doc = constructor.newDocument();

            /*
             * Elemento raíz del documento.
             * El atributo "version" tiene valor fijo "1.0" (fixed en XSD).
             */
            Element root = doc.createElement("Cine");
            root.setAttribute("version", "1.0");
            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:noNamespaceSchemaLocation", "xsd/cine.xsd");
            doc.appendChild(root);

            /* Nodos padre de cada sección del catálogo */
            Element etiquetaPadreC = doc.createElement("Clientes");
            root.appendChild(etiquetaPadreC);

            Element etiquetaPadreP = doc.createElement("Peliculas");
            root.appendChild(etiquetaPadreP);

            Element etiquetaPadreE = doc.createElement("Entradas");
            root.appendChild(etiquetaPadreE);

            /* ── SECCIÓN CLIENTES ─────────────────────────────────────────────── */
            for (Usuario usu : listaUsuarios) {

                Element usuNode = doc.createElement("Cliente");

                /*
                 * Atributo "User": identificador único del cliente.
                 */
                String userVal = String.valueOf(usu.getUsuario());
                usuNode.setAttribute("User", userVal);
                etiquetaPadreC.appendChild(usuNode);

                Element nombre = doc.createElement("Nombre");
                nombre.setTextContent(usu.getNombre());
                usuNode.appendChild(nombre);

                Element apellido = doc.createElement("Apellido");
                apellido.setTextContent(usu.getApellido());
                usuNode.appendChild(apellido);

                /*
                 * Elemento "Correo": dirección de correo del cliente.
                 * Validado contra PATRON_CORREO antes de escribirse (xs:pattern en XSD).
                 */
                String correoVal = usu.getCorreo();
                if (!correoVal.matches(PATRON_CORREO)) {
                    throw new IllegalArgumentException(
                            "Correo '" + correoVal + "' no tiene formato válido");
                }
                Element correo = doc.createElement("Correo");
                correo.setTextContent(correoVal);
                usuNode.appendChild(correo);

                /*
                 * Elemento "Edad": edad del cliente en años.
                 * Restringida al rango [0, 120] (xs:minInclusive / xs:maxInclusive en XSD).
                 */
                int edadVal = usu.getEdad();
                if (edadVal < 0 || edadVal > 120) {
                    throw new IllegalArgumentException(
                            "Edad " + edadVal + " fuera de rango [0, 120]");
                }
                Element edad = doc.createElement("Edad");
                edad.setTextContent(String.valueOf(edadVal));
                usuNode.appendChild(edad);

                Element contraseña = doc.createElement("Contrasena");
                contraseña.setTextContent(usu.getContrasena());
                usuNode.appendChild(contraseña);
            }

            /* ── SECCIÓN ENTRADAS ─────────────────────────────────────────────── */
            for (Entrada ent : listaEntradas) {

                Element entNode = doc.createElement("Entrada");
				etiquetaPadreE.appendChild(entNode);           
                             
                Element user = doc.createElement("Usuario");
                user.setTextContent(ent.getUsuario());
                entNode.appendChild(user);

                Element numSala = doc.createElement("NumeroSala");
                numSala.setTextContent(String.valueOf(ent.getNumSala()));
                entNode.appendChild(numSala);

                Element idPelicula = doc.createElement("IdPelicula");
                idPelicula.setTextContent(String.valueOf(ent.getIdPelicula()));
                entNode.appendChild(idPelicula);

                Element numButaca = doc.createElement("NumeroButaca");
                numButaca.setTextContent(String.valueOf(ent.getNumButaca()));
                entNode.appendChild(numButaca);

                Element prec = doc.createElement("Precio");
                prec.setTextContent(String.valueOf(ent.getPrecio()));
                entNode.appendChild(prec);

                Element fechTras = doc.createElement("FechaTransmision");
                fechTras.setTextContent(String.valueOf(ent.getFechaTransmision()));
                entNode.appendChild(fechTras);

                Element fechAd = doc.createElement("FechaAdquisicion");
                fechAd.setTextContent(String.valueOf(ent.getFechaAdquiere()));
                entNode.appendChild(fechAd);
            }          

            /* ── SECCIÓN PELÍCULAS ────────────────────────────────────────────── */
            for (Pelicula peli : listaPeliculas) {

                Element peliNode = doc.createElement("Pelicula");

                /* Atributo "id": identificador único de la película (use="required" en XSD). */
                peliNode.setAttribute("id", String.valueOf(peli.getIdPelicula()));
                etiquetaPadreP.appendChild(peliNode);

                Element tit = doc.createElement("Titulo");
                tit.setTextContent(peli.getTitulo());
                peliNode.appendChild(tit);

                Element dura = doc.createElement("Duracion");
                dura.setTextContent(String.valueOf(peli.getDuracionMin()));
                peliNode.appendChild(dura);

                /*
                 * Elemento "Sinopsis": resumen argumental de la película.
                 * Es opcional: solo se incluye en el XML si el dato está informado
                 * (minOccurs="0" en XSD).
                 */
                String sinopsisVal = peli.getSinopsis();
                if (sinopsisVal != null && !sinopsisVal.isEmpty()) {
                    Element sinop = doc.createElement("Sinopsis");
                    sinop.setTextContent(sinopsisVal);
                    peliNode.appendChild(sinop);
                } else {
                    // Si no hay sinopsis, se pone la imagen del cartel
                    Element cartel = doc.createElement("Cartel");
                    cartel.setTextContent(peli.getRutaImg());
                    peliNode.appendChild(cartel);
                }

                /*
                 * Elemento "Genero": género cinematográfico de la película.
                 * Debe pertenecer a GENEROS_VALIDOS (xs:enumeration en XSD).
                 */
                String generoVal = peli.getGenero();
                if (!GENEROS_VALIDOS.contains(generoVal)) {
                    throw new IllegalArgumentException(
                            "Género '" + generoVal + "' no válido. Permitidos: " + GENEROS_VALIDOS);
                }
                Element gen = doc.createElement("Genero");
                gen.setTextContent(generoVal);
                peliNode.appendChild(gen);

                Element dir = doc.createElement("Director");
                dir.setTextContent(peli.getDirector());
                peliNode.appendChild(dir);

                Element rutaImg = doc.createElement("ruta");
                rutaImg.setTextContent(peli.getRutaImg());
                peliNode.appendChild(rutaImg);
                /*
                 * Elemento "Valoracion": puntuación de la película.
                 * Restringida al rango [0.0, 5.0] (xs:minInclusive / xs:maxInclusive en XSD).
                 */
                double valoracionVal = peli.getValoracion();
                if (valoracionVal < 0.0 || valoracionVal > 5.0) {
                    throw new IllegalArgumentException(
                            "Valoración " + valoracionVal + " fuera de rango [0.0, 5.0]");
                }
                Element val = doc.createElement("Valoracion");
                val.setTextContent(String.valueOf(valoracionVal));
                peliNode.appendChild(val);
            }

            /* ── ESCRITURA DEL FICHERO XML ────────────────────────────────────── */

            /* Configuración del transformador: indentación de 4 espacios */
            TransformerFactory instanciaTransformador = TransformerFactory.newInstance();
            Transformer transformador = instanciaTransformador.newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            /* Conversión del árbol DOM al fichero de destino */
            DOMSource fuente = new DOMSource(doc);
            StreamResult resultado = new StreamResult(new File(ruta));
            transformador.transform(fuente, resultado);

            System.out.println("Archivo XML generado con éxito en: " + ruta);

        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("Error al generar el XML: " + e.getMessage());
            System.err.println("Causa: " + e.getCause());
        }
    }
}