📌 Proyecto: TolotoloCines

📝 Descripción

Breve descripción del proyecto:
Este proyecto consiste en una aplicación desarrollada en Java que permite mostrar los datos del cine al usuario. Su objetivo principal es mejorar la accesibilidad dentro del contexto de ocio cinematografico.

---

## 🧰 Tecnologías utilizadas

* ☕ Java 8 / JavaFX
* 🗄️ MySQL (Local instance MySQL80)
* 🔌 JDBC (Conector MySQL)
* 🧪 Mockito (testing con JARs)

### 💻 Herramientas de desarrollo

* NetBeans
* Mysql
* Eclipse
* Visual Studio Code

---

## 📚 Dependencias

Este proyecto utiliza las siguientes librerías externas:

* Conector JDBC MySQL
  (ej: mysql-connector-java-8.0.15.jar)

* Mockito
  (ej: byte-buddy-1.18.8.jar, byte-buddy-agent-1.17.7.jar, mockito-core-5.23.0 .jar, objenesis-3.3 .jar)

**En NetBeans:**
`Properties → Libraries → Add JAR/Folder`

**En IntelliJ:**
`File → Project Structure → Modules → Dependencies`

---

## 🗄️ Base de datos

El proyecto incluye el script de base de datos:

📄 `ToloToloCine.sql`

### ▶️ Cómo usarlo

1. Crear una base de datos en MySQL
2. Importar el fichero:

**Desde consola:**

```bash
mysql -u usuario -p nombre_bd < ToloToloCine.sql
```

**O con herramienta gráfica:**
Usando MySQL Workbench u otra similar.

---

## 📦 Instalación

### 📥 Clonar el repositorio

```bash
git clone [https://github.com/usuario/repositorio.git](https://github.com/asiertartanga123-star/Reto3.git)
```

### 📁 Importar el proyecto

Abrir el proyecto en tu IDE preferido:

* Eclipse
* IntelliJ
* VS Code

---

## ➕ Añadir dependencias manualmente

1. Descargar:

   * Conector JDBC de MySQL
   * Librerías de Mockito

2. Añadir los `.jar` al proyecto (ver sección Dependencias)

---

## ▶️ Ejecución

1. Configurar la conexión a la base de datos en el proyecto
2. Ejecutar la aplicación desde la clase `main`

---

## 🧪 Tests

El proyecto incluye pruebas unitarias usando Mockito.

### ▶️ Ejecutar tests

* Desde el IDE → Ejecutar tests
* Asegúrate de que los JAR de Mockito están correctamente añadidos

---

## 📌 Notas

* Verifica la configuración de la base de datos antes de ejecutar
* Comprueba que todas las dependencias están correctamente importadas

---

## 👨‍💻 Autor

Hodei Torres, Joel , Jair , Asier Prieto  

---
