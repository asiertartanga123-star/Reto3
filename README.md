Proyecto: Nombre del Proyecto
📝 Descripción Breve descripción del proyecto: qué hace, objetivos y contexto. 🧰 Tecnologías utilizadas Java 8 / JavaFX 🗄️ MySQL 🔌 JDBC (conector MySQL) Mockito (testing con JARs) Herramientas: NetBeans / VS Code / IntelliJ IDEA

📚 Dependencias Este proyecto utiliza las siguientes librerías externas: · Conector JDBC MySQL (ej: mysql-connector-java-x.x.x.jar) · Mockito (ej: mockito-core-x.x.x.jar) · ⚠️ Otras dependencias necesarias (si aplica) Asegúrate de añadir los .jar al proyecto: · En NetBeans: Properties → Libraries → Add JAR/Folder · En IntelliJ: File → Project Structure → Modules → Dependencies 🗄️ Base de datos El proyecto incluye el script de base de datos: bd.sql ▶️ Cómo usarlo 1. Crear una base de datos en MySQL 2. Importar el fichero bd.sql a. Desde consola: mysql -u usuario -p nombre_bd < bd.sql b. O usando una herramienta gráfica como MySQL Workbench 📦 Instalación 📥 Clonar el repositorio git clone https://github.com/usuario/repositorio.git 📁 Importar el proyecto · Abrir el proyecto en tu IDE (Eclipse/ IntelliJ / VS Code)

➕ Añadir dependencias manualmente 1. Descargar: a. Conector JDBC de MySQL b. Librerías de Mockito 2. Añadir los .jar al proyecto (ver sección anterior) ▶️ Ejecución 1. Configurar la conexión a la base de datos en el proyecto 2. Ejecutar la aplicación desde el main Tests El proyecto incluye pruebas unitarias usando Mockito. Para ejecutarlas: · Desde el IDE → Ejecutar tests · Asegúrate de que los JAR de Mockito están correctamente añadidos
# Reto3
>>>>>>> hodei
