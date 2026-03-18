-- CREATE DATABASE CINE_TOLOTOLO;
USE CINE_TOLOTOLO;
/*
CREATE TABLE USUARIO (
    USUARIO VARCHAR(20) PRIMARY KEY,
    NOMBRE VARCHAR(20),
    APELLIDO VARCHAR(20),
	CORREO VARCHAR(50) UNIQUE NOT NULL,
    EDAD INT,
    CONTRASENA VARCHAR(60) NOT NULL
);

CREATE TABLE SALA (
    NUM_SALA INT AUTO_INCREMENT PRIMARY KEY,
    AFORO INT CHECK (AFORO > 0) NOT NULL,
    TIPO_TRANSMISION ENUM('2D','3D')
);

CREATE TABLE PELICULA (
    ID_PELICULA INT AUTO_INCREMENT PRIMARY KEY,
    TITULO VARCHAR(50),
    DURACION_MIN INT,
    SINOPSIS VARCHAR(255),
    GENERO ENUM('TERROR','COMEDIA','DRAMA','ACCION','CIENCIA_FICCION'),
    DIRECTOR VARCHAR(50),
    VALORACION INT CHECK (VALORACION BETWEEN 1 AND 5)
);

CREATE TABLE ENTRADA (
    USUARIO VARCHAR(20),
    NUM_SALA INT,
    ID_PELICULA INT,
    NUM_BUTACA INT,
    PRECIO INT,
    FECHA_TRANSMISION DATE,
    FECHA_ADQUIERE DATE DEFAULT (CURRENT_DATE) NOT NULL,
    PRIMARY KEY (USUARIO, NUM_SALA, ID_PELICULA, FECHA_TRANSMISION, NUM_BUTACA),
    FOREIGN KEY (USUARIO) REFERENCES USUARIO(USUARIO)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (NUM_SALA) REFERENCES SALA(NUM_SALA)
		ON UPDATE CASCADE,
    FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID_PELICULA)
		ON UPDATE CASCADE
);
*/
-- INSERTS DE SALAS Y PELICULAS

-- INSERT SALAS
-- INSERT INTO SALA (AFORO, TIPO_TRANSMISION)VALUES (80,'2D'),(80,'3D'),(60,'3D'),(100,'2D'),(50,'2D');

-- INSERT PELICULAS
/*
INSERT INTO PELICULA (TITULO, DURACION_MIN, SINOPSIS, GENERO, DIRECTOR, VALORACION) VALUES
('La Sospecha De Sofia',105, 'Sofia descubre que su entorno más cercano le oculta una peligrosa verdad que amenaza su vida.','TERROR','Ana Villareal',4),
('Tron Ares', 120, 'Un nuevo programa rebelde surge en la Red amenazando con destruir el mundo digital y el real.','CIENCIA_FICCION','Joachim Ronning',4),
('The Smashing Machine',118, 'La historia real de Mark Kerr, luchador de MMA que lucha contra sus demonios dentro y fuera del octágono.','DRAMA','Benny Safdie',4),
('Una Batalla Tras Otra',132, 'Un veterano de guerra enfrenta sin descanso una serie de conflictos que pondrán a prueba su resistencia y su humanidad.','ACCION','Paul Verhoeven',4),
('Avatar, El Sentido Del Agua',192, 'Jake Sully y Neytiri luchan por proteger a su familia cuando una antigua amenaza regresa a Pandora.','CIENCIA_FICCION','James Cameron',5),
('AfterBurn',98,'Tras un accidente en una misión secreta, una agente debe sobrevivir en territorio hostil antes de que sea demasiado tarde.','ACCION','Marcus Cole',3),
('Bala Perdida',92,'Un mecánico con un pasado criminal usa sus habilidades para ayudar a la policía a cazar a un peligroso traficante.','ACCION','Guillaume Pierret',4),
('Chainsaw Man: El Arco De Reze', 110, 'Denji se enfrenta a Reze, una misteriosa chica con poderes de bomba que esconde una agenda letal.','ACCION','Ryū Nakayama',5),
('La Hermanastra Fea',95,'Una comedia disparatada sobre dos hermanastras opuestas que deben convivir tras la boda de sus padres.','COMEDIA','Rachel Green',3),
('Regreso Al Futuro',116, 'Marty McFly viaja accidentalmente al pasado en un DeLorean y debe asegurarse de que sus padres se conozcan.','CIENCIA_FICCION','Robert Zemeckis',5),
('Predator: BadLands',115, 'Una cazadora solitaria descubre que no es la única depredadora en las tierras salvajes cuando un Predator marca su territorio.',  'ACCION','Dan Trachtenberg',4),
('La Novia Cadaver',77,'Un joven ensaya sus votos nupciales en el bosque y accidentalmente se casa con el espíritu de una novia asesinada.','TERROR','Tim Burton',5),
('Jesus La Luz Del Mundo',110, 'Un relato épico sobre la vida, milagros y sacrificio de Jesús de Nazaret narrado con una visión cinematográfica moderna.','DRAMA','Christopher Spencer',4),
('Kpop Demon Hunters',103, 'Un grupo de idol coreanas lleva una doble vida como cazadoras de demonios que amenazan el mundo desde las sombras.','ACCION','Maggie Leung',3),
('El Alucinante Mundo De Norman', 90, 'Norman, un niño peculiar capaz de hablar con los muertos, debe salvar a su pueblo de una maldición de 300 años.','COMEDIA','Sam Fell',4),
('Telefono Negro 2',108, 'El Abductor regresa con nuevas víctimas y un teléfono negro que conecta a los atrapados con los que ya no pueden escapar.','TERROR','Scott Derrickson',4);
*/

-- PROCEDURE Y FUNCTINOS
DELIMITER //
-- PROCEDURE (VENTANA DE CATALOGO) VerPelicula
CREATE PROCEDURE VerPeliculas()  
BEGIN
	DECLARE idPeli INT;
    DECLARE titulo VARCHAR(50);
    DECLARE duracion INT;
    DECLARE sinopsis VARCHAR(255);
    DECLARE gen ENUM('TERROR','COMEDIA','DRAMA','ACCION','CIENCIA_FICCION');
	DECLARE dir VARCHAR(50);
	DECLARE valor INT; 
    
	DECLARE fin INT DEFAULT 0;
	DECLARE C CURSOR FOR SELECT ID_PELICULA, TITULO, DURACION_MIN, SINOPSIS, GENERO, DIRECTOR, VALORACION FROM PELICULA;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
    OPEN C;
    
	FETCH C INTO idPeli, titulo, duracion, sinopsis, gen, dir, valor;
    WHILE fin = 0 DO
        FETCH C INTO idPeli, titulo, duracion, sinopsis, gen, dir, valor;
    END WHILE;
    
    CLOSE C;
END //
call VerPeliculas();
