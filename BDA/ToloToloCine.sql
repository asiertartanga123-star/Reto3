DROP DATABASE IF EXISTS CINE_TOLOTOLO;
CREATE DATABASE CINE_TOLOTOLO;
USE CINE_TOLOTOLO;

-- =====================
-- TABLAS
-- =====================

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
    VALORACION INT CHECK (VALORACION BETWEEN 1 AND 5),
    RUTAIMG VARCHAR(100)
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

-- =====================
-- SALAS
-- =====================

INSERT INTO SALA VALUES
(01, 10, '2D'),  (02, 10, '3D'), (03, 10, '2D'),  (04, 10, '2D'),  (05, 10, '3D'), (06, 10, '2D');

-- =====================
-- PELICULAS 
-- =====================

INSERT INTO PELICULA (TITULO, DURACION_MIN, SINOPSIS, GENERO, DIRECTOR, VALORACION, RUTAIMG) VALUES
('La Sospecha De Sofia',105,'Sofia descubre que su entorno más cercano le oculta una peligrosa verdad que amenaza su vida.','TERROR','Ana Villareal',4,'../img/posters/La Sospecha De Sofia.png'),
('Tron Ares',120,'Un nuevo programa rebelde surge en la Red amenazando con destruir el mundo digital y el real.','CIENCIA_FICCION','Joachim Ronning',4,'../img/posters/Tron Ares.png'),
('The Smashing Machine',118,'La historia real de Mark Kerr, luchador de MMA que lucha contra sus demonios dentro y fuera del octágono.','DRAMA','Benny Safdie',4,'../img/posters/The Smashing Machine.png'),
('Una Batalla Tras Otra',132,'Un veterano de guerra enfrenta sin descanso una serie de conflictos que pondrán a prueba su resistencia y su humanidad.','ACCION','Paul Verhoeven',4,'../img/posters/Una Batalla Tras Otra.png'),
('Avatar, El Sentido Del Agua',192,'Jake Sully y Neytiri luchan por proteger a su familia cuando una antigua amenaza regresa a Pandora.','CIENCIA_FICCION','James Cameron',5,'../img/posters/Avatar, El Sentido Del Agua.png'),
('AfterBurn',98,'Tras un accidente en una misión secreta, una agente debe sobrevivir en territorio hostil antes de que sea demasiado tarde.','ACCION','Marcus Cole',3,'../img/posters/AfterBurn.png'),
('Bala Perdida',92,'Un mecánico con un pasado criminal usa sus habilidades para ayudar a la policía a cazar a un peligroso traficante.','ACCION','Guillaume Pierret',4,'../img/posters/Bala Perdida.png'),
('Chainsaw Man El Arco De Reze',110,'Denji se enfrenta a Reze, una misteriosa chica con poderes de bomba que esconde una agenda letal.','ACCION','Ryū Nakayama',5,'../img/posters/Chainsaw Man: El Arco De Reze.png'),
('La Hermanastra Fea',95,'Una comedia disparatada sobre dos hermanastras opuestas que deben convivir tras la boda de sus padres.','COMEDIA','Rachel Green',3,'../img/posters/La Hermanastra Fea.png'),
('Regreso Al Futuro',116,'Marty McFly viaja accidentalmente al pasado en un DeLorean y debe asegurarse de que sus padres se conozcan.','CIENCIA_FICCION','Robert Zemeckis',5,'../img/posters/Regreso Al Futuro.png'),
('Predator BadLands',115,'Una cazadora solitaria descubre que no es la única depredadora en las tierras salvajes cuando un Predator marca su territorio.','ACCION','Dan Trachtenberg',4,'../img/posters/Predator: BadLands.png'),
('La Novia Cadaver',77,'Un joven ensaya sus votos nupciales en el bosque y accidentalmente se casa con el espíritu de una novia asesinada.','TERROR','Tim Burton',5,'../img/posters/La Novia Cadaver.png'),
('Jesus La Luz Del Mundo',110,'Un relato épico sobre la vida, milagros y sacrificio de Jesús de Nazaret narrado con una visión cinematográfica moderna.','DRAMA','Christopher Spencer',4,'../img/posters/Jesus La Luz Del Mundo.png'),
('Kpop Demon Hunters',103,'Un grupo de idol coreanas lleva una doble vida como cazadoras de demonios que amenazan el mundo desde las sombras.','ACCION','Maggie Leung',3,'../img/posters/Kpop Demon Hunters.png'),
('El Alucinante Mundo De Norman',90,'Norman, un niño peculiar capaz de hablar con los muertos, debe salvar a su pueblo de una maldición de 300 años.','COMEDIA','Sam Fell',4,'../img/posters/El Alucinante Mundo De Norman.png'),
('Telefono Negro 2',108,'El Abductor regresa con nuevas víctimas y un teléfono negro que conecta a los atrapados con los que ya no pueden escapar.','TERROR','Scott Derrickson',4,'../img/posters/Telefono Negro 2.png'),
('La Novia',119,'En el Chicago de los años 30, una científica ayuda a Frankenstein a crear una compañera, desatando consecuencias imprevistas sobre la identidad y la libertad.','TERROR','Maggie Gyllenhaal',4,'../img/posters/La Novia.png'),
('El Mago Del Kremlin',124,'Un asesor político en las sombras del Kremlin revela los secretos más oscuros del poder ruso mientras navega entre lealtades y traiciones.','DRAMA','Olivier Assayas',4,'../img/posters/El Mago Del Kremlin.png'),
('Hoppers',101,'Mabel, una joven científica, transfiere su mente a un castor robot para infiltrarse en el reino animal y salvar un ecosistema en peligro.','CIENCIA_FICCION','Daniel Chong',4,'../img/posters/Hoppers.png'),
('Te Van A Matar',97,'En una noche caótica, una mujer debe sobrevivir a una violenta secta que la ha elegido como objetivo.','ACCION','Kirill Sokolov',3,'../img/posters/Te Van A Matar.png'),
('La Grazia',112,'El presidente saliente de Italia se enfrenta al ocaso de su mandato mientras dilemas morales y familiares lo obligan a confrontar quién realmente es.','DRAMA','Paolo Sorrentino',5,'../img/posters/La Grazia.png'),
('El Ultimo Heredero',108,'Un hombre descubre que es el único heredero de una fortuna familiar.','COMEDIA','John Patton Ford',4,'../img/posters/El Ultimo Heredero.png'),
('Peaky Blinders El Hombre Inmortal',135,'Tommy Shelby regresa a la Birmingham de la Segunda Guerra Mundial.','ACCION','Tom Harper',5,'../img/posters/Peaky Blinders.png'),
('Super Mario Galaxy La Pelicula',105,'Mario y sus amigos enfrentan una amenaza cósmica.','COMEDIA','Aaron Horvath',4,'../img/posters/Super Mario Galaxy.png'),
('Pillion',99,'Un hombre reservado es arrastrado al mundo del motociclismo extremo.','DRAMA','Harry Lighton',3,'../img/posters/Pillion.png'),
('El Testimonio De Ann Lee',117,'Historia de la líder espiritual Ann Lee.','DRAMA','Francisca Alegria',4,'../img/posters/Ann Lee.png'),
('Torrente Presidente',100,'Torrente lidera una campaña electoral disparatada.','COMEDIA','Santiago Segura',4,'../img/posters/Torrente.png');

-- =====================
-- USUARIOS
-- =====================

INSERT INTO USUARIO VALUES
('admin','Carlos','Mendoza','carlos.mendoza@email.com',28,'admin123'),
('user1','Laura','Jiménez','laura.jimenez@email.com',34,'1234'),
('user2','Miguel','Torres','miguel.torres@email.com',22,'1234'),
('jgarcia','Juan','García','juan.garcia@gmail.com',25,'pass1234'),
('mlopez','María','López','maria.lopez@hotmail.com',32,'pass1234'),
('cperez','Carlos','Pérez','carlos.perez@gmail.com',19,'pass1234'),
('amoreno','Ana','Moreno','ana.moreno@yahoo.com',45,'pass1234'),
('dsanchez','David','Sánchez','david.sanchez@gmail.com',28,'pass1234'),
('lmartinez','Laura','Martínez','laura.martinez@outlook.com',38,'pass1234'),
('pjimenez','Pablo','Jiménez','pablo.jimenez@gmail.com',21,'pass1234'),
('nruiz','Nuria','Ruiz','nuria.ruiz@hotmail.com',55,'pass1234'),
('itorres','Iván','Torres','ivan.torres@gmail.com',17,'pass1234'),
('sfernandez','Sofía','Fernández','sofia.fernandez@yahoo.com',30,'pass1234');

-- =====================
-- ENTRADAS
-- =====================

INSERT INTO ENTRADA VALUES
('jgarcia',1,1,12,8,'2026-03-10','2026-03-08'),
('jgarcia',2,5,7,10,'2026-03-15','2026-03-13'),
('mlopez',3,8,22,9,'2026-03-11','2026-03-09'),
('mlopez',1,12,5,8,'2026-03-18','2026-03-16'),
('mlopez',4,23,45,10,'2026-03-22','2026-03-20'),
('cperez',2,10,33,10,'2026-03-12','2026-03-10'),
('cperez',5,3,18,7,'2026-03-25','2026-03-23'),
('amoreno',4,21,67,8,'2026-03-14','2026-03-12'),
('amoreno',1,6,3,8,'2026-03-28','2026-03-26'),
('dsanchez',3,15,41,9,'2026-03-13','2026-03-11'),
('dsanchez',2,2,29,10,'2026-03-20','2026-03-18'),
('dsanchez',5,18,11,7,'2026-03-27','2026-03-25'),
('lmartinez',1,24,55,8,'2026-03-16','2026-03-14'),
('lmartinez',3,9,38,9,'2026-03-29','2026-03-27'),
('pjimenez',4,7,14,8,'2026-03-17','2026-03-15'),
('pjimenez',2,11,26,10,'2026-03-30','2026-03-28'),
('nruiz',5,4,9,7,'2026-03-19','2026-03-17'),
('itorres',1,16,72,8,'2026-03-21','2026-03-19'),
('itorres',3,20,50,9,'2026-03-31','2026-03-29'),
('sfernandez',2,13,19,10,'2026-03-23','2026-03-21'),
('sfernandez',4,27,88,8,'2026-04-01','2026-03-30');

-- ======================
-- PROCEDURE Y FUNCTINOS
-- ======================

-- PROCEDURE (VENTANA DE CATALOGO) VerPelicul
DELIMITER //
CREATE PROCEDURE VerPeliculas()  
BEGIN
	DECLARE idPeli INT;
    DECLARE tit VARCHAR(50);
    DECLARE duracion INT;
    DECLARE sinop VARCHAR(255);
    DECLARE gen ENUM('TERROR','COMEDIA','DRAMA','ACCION','CIENCIA_FICCION');
	DECLARE dir VARCHAR(50);
	DECLARE valor INT; 
    DECLARE ruta VARCHAR(100);
    
	DECLARE fin INT DEFAULT 0;
	DECLARE C CURSOR FOR SELECT ID_PELICULA, TITULO, DURACION_MIN, SINOPSIS, GENERO, DIRECTOR, VALORACION, RUTAIMG FROM PELICULA;
	DECLARE CONTINUE HANDLER FOR 1329 SET fin = 1;
    OPEN C;
    
	FETCH C INTO idPeli, tit, duracion, sinop, gen, dir, valor, ruta;
    IF fin = 1 THEN
        SELECT 'No se encontraron películas en la base de datos.' AS mensaje;
    ELSE
        WHILE fin = 0 DO
            SELECT idPeli, tit, duracion, sinop, gen, dir, valor, ruta;
            FETCH C INTO idPeli, tit, duracion, sinop, gen, dir, valor, ruta;
        END WHILE;
    END IF;
    		
    CLOSE C;
END //
call VerPeliculas();

-- PROCEDURE PARA RANKING DE USUARIOS

CREATE PROCEDURE RANKING_USUARIOS_ENTRADAS_HASTA (IN P_USUARIO VARCHAR(20), IN P_FECHA DATE)
BEGIN
    -- VARIABLES PARA GUARDAR LOS CAMPOS
	DECLARE V_USUARIO VARCHAR(20);
	DECLARE V_NOMBRE VARCHAR(20);
	DECLARE V_TOTAL INT;
	DECLARE V_POSICION INT DEFAULT 0;
	DECLARE FIN INT DEFAULT 0;
	DECLARE USUARIO_EN_TOP10 BOOLEAN DEFAULT FALSE;
	DECLARE USUARIO_INSERTADO BOOLEAN DEFAULT FALSE;

    -- CURSOR CON RANKING HASTA LA FECHA P_FECHA
	DECLARE CUR_RANKING CURSOR FOR
		SELECT U.USUARIO, U.NOMBRE, COUNT(*) AS TOTAL_ENTRADAS
		FROM USUARIO U
		JOIN ENTRADA E ON U.USUARIO = E.USUARIO
		WHERE E.FECHA_ADQUIERE <= P_FECHA
		GROUP BY U.USUARIO, U.NOMBRE
		ORDER BY TOTAL_ENTRADAS DESC;

	-- CONTROL DE FIN DE CURSOR
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET FIN = 1;

	-- CONTROL DE EXCEPCIONES SQL 
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			SELECT
			NULL AS POSICION_TOP,
			NULL AS USUARIO,
			NULL AS NOMBRE,
			NULL AS TOTAL_ENTRADAS
			WHERE FALSE;
		END;

    -- TABLA TEMPORAL DE RESULTADOS
	DROP TEMPORARY TABLE IF EXISTS TMP_RESULTADO;
	CREATE TEMPORARY TABLE TMP_RESULTADO (
		POSICION_TOP INT,
		USUARIO VARCHAR(20),
		NOMBRE VARCHAR(20),
		TOTAL_ENTRADAS INT
	); 

	OPEN CUR_RANKING;
	FETCH CUR_RANKING INTO V_USUARIO, V_NOMBRE, V_TOTAL;

	WHILE FIN = 0 DO
		SET V_POSICION = V_POSICION + 1;
		IF V_POSICION <= 10 THEN
			INSERT INTO TMP_RESULTADO VALUES (V_POSICION, V_USUARIO, V_NOMBRE, V_TOTAL);
				IF V_USUARIO = P_USUARIO THEN
					SET USUARIO_EN_TOP10 = TRUE;
				END IF;
		ELSE
			IF V_USUARIO = P_USUARIO AND USUARIO_EN_TOP10 = FALSE AND USUARIO_INSERTADO = FALSE THEN
				INSERT INTO TMP_RESULTADO VALUES (11, V_USUARIO, V_NOMBRE, V_TOTAL);
			SET USUARIO_INSERTADO = TRUE;
			END IF;
		END IF;
		FETCH CUR_RANKING INTO V_USUARIO, V_NOMBRE, V_TOTAL;
	END WHILE;
	CLOSE CUR_RANKING;
	
    -- RESULTADO FINAL
	SELECT POSICION_TOP, USUARIO, NOMBRE, TOTAL_ENTRADAS FROM TMP_RESULTADO ORDER BY POSICION_TOP;
END//
DELIMITER ;
call RANKING_USUARIOS_ENTRADAS_HASTA ('luis03','2026-03-17');

-- FUNCION PARA SABER EL AFORO LIBRE DE LAS SALAS
DELIMITER //
CREATE FUNCTION DISPONIBILIDAD_AFORO(D_NUM_SALA INT) 
RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE AFORO_OCUPADO INT;
    DECLARE AFORO_TOTAL INT;

    SELECT AFORO INTO AFORO_TOTAL 
    FROM SALA 
    WHERE NUM_SALA = D_NUM_SALA;

    SELECT COUNT(*) INTO AFORO_OCUPADO 
    FROM ENTRADA E
    INNER JOIN SALA S ON S.NUM_SALA = E.NUM_SALA 
    WHERE S.NUM_SALA = D_NUM_SALA;

    IF AFORO_OCUPADO >= AFORO_TOTAL * 1 THEN
        RETURN 3;
    ELSEIF AFORO_OCUPADO >= AFORO_TOTAL * 0.6 THEN
        RETURN 2;
    ELSEIF AFORO_OCUPADO >= AFORO_TOTAL * 0.3 THEN
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;

END //

DELIMITER //
CREATE PROCEDURE SP_HISTORIAL_USUARIO(
    IN P_USUARIO VARCHAR(20)
)
BEGIN
    SELECT  P.TITULO,
            P.GENERO,
            S.TIPO_TRANSMISION,
            E.FECHA_TRANSMISION,
            E.NUM_BUTACA,
            E.PRECIO,
            E.FECHA_ADQUIERE
    FROM    ENTRADA  E
    JOIN    PELICULA P ON P.ID_PELICULA = E.ID_PELICULA
    JOIN    SALA   S ON S.NUM_SALA    = E.NUM_SALA
    WHERE   E.USUARIO = P_USUARIO
    ORDER BY E.FECHA_TRANSMISION DESC;

    SELECT  COUNT(*)     AS TOTAL_ENTRADAS,
            SUM(PRECIO) AS GASTO_TOTAL
    FROM    ENTRADA
    WHERE   USUARIO = P_USUARIO;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SP_RESUMEN_INGRESOS_SALA(
    IN P_FECHA_DESDE DATE,
    IN P_FECHA_HASTA DATE
)
BEGIN
    SELECT  S.NUM_SALA,
            S.TIPO_TRANSMISION,
            S.AFORO,
            COUNT(E.NUM_BUTACA) AS ENTRADAS_VENDIDAS,
            SUM(E.PRECIO) AS INGRESOS_TOTAL,
            ROUND(COUNT(E.NUM_BUTACA) * 100.0 / S.AFORO, 1) AS PCT_OCUPACION
    FROM    SALA S
    LEFT JOIN ENTRADA E
           ON  E.NUM_SALA = S.NUM_SALA
          AND E.FECHA_TRANSMISION BETWEEN P_FECHA_DESDE AND P_FECHA_HASTA
    GROUP BY S.NUM_SALA, S.TIPO_TRANSMISION, S.AFORO
    ORDER BY INGRESOS_TOTAL DESC;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SP_PELICULAS_POR_DIRECTOR(
    IN P_DIRECTOR VARCHAR(50)
)
BEGIN
    SELECT  ID_PELICULA,
            TITULO,
            GENERO,
            DURACION_MIN,
            VALORACION
    FROM    PELICULA
    WHERE   DIRECTOR LIKE CONCAT('%', P_DIRECTOR, '%')
    ORDER BY VALORACION DESC;
END //
DELIMITER ;

DELIMITER //
DROP FUNCTION IF EXISTS CANTIDAD_USUARIOS_SALA_FECHA;

DELIMITER //
CREATE FUNCTION CANTIDAD_USUARIOS_SALA_FECHA(
    P_NUM_SALA INT,
    P_FECHA DATE
)
RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE V_TOTAL INT;

    SELECT COUNT(*) INTO V_TOTAL
    FROM ENTRADA
    WHERE NUM_SALA = P_NUM_SALA
      AND FECHA_TRANSMISION = P_FECHA;  -- ✅ CORREGIDO

    RETURN COALESCE(V_TOTAL, 0);
END //
DELIMITER ;

-- Sala 1, 2025-06-01 → 10
SELECT CANTIDAD_USUARIOS_SALA_FECHA(1, '2025-06-01');

DELIMITER //

DROP FUNCTION IF EXISTS PRECIO_ENTRADA;

DELIMITER //
CREATE FUNCTION PRECIO_ENTRADA(
    P_EDAD INT,
    P_NUM_SALA INT
)
RETURNS DECIMAL(6,2)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE V_TIPO ENUM('2D','3D');
    DECLARE V_BASE DECIMAL(6,2);
    DECLARE V_PRECIO DECIMAL(6,2);

    SELECT TIPO_TRANSMISION INTO V_TIPO   -- ✅ CORREGIDO
    FROM SALA
    WHERE NUM_SALA = P_NUM_SALA;

    SET V_BASE = CASE 
                    WHEN V_TIPO = '3D' THEN 12.00
                    ELSE 8.00
                 END;

    SET V_PRECIO = CASE
                      WHEN P_EDAD < 18 THEN V_BASE * 0.80
                      WHEN P_EDAD >= 65 THEN V_BASE * 0.75
                      ELSE V_BASE
                   END;

    RETURN V_PRECIO;
END //
DELIMITER ;

-- Sala 3D (sala 2), usuario de 16 años → 9.60
SELECT PRECIO_ENTRADA(16, 2);

DELIMITER //

CREATE PROCEDURE comprobarEntrada(
    IN p_sala INT,
    IN p_pelicula INT,
    IN p_butaca INT,
    IN p_fecha DATE
)
BEGIN
    DECLARE butaca_ocupada INT;
    DECLARE total_ocupadas INT;
    DECLARE aforo_sala INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SELECT 'Error in the check' AS Message;
    END;

    -- comprobar si la pelicula existe
    IF NOT EXISTS (
        SELECT 1 FROM PELICULA WHERE ID_PELICULA = p_pelicula
    ) THEN
        SELECT 'Movie does not exist' AS Message;

    -- comprobar si la sala existe
    ELSEIF NOT EXISTS (
        SELECT 1 FROM SALA WHERE NUM_SALA = p_sala
    ) THEN
        SELECT 'Hall does not exist' AS Message;

    ELSE
        -- comprobar si la butaca esta ocupada
        SELECT COUNT(*) INTO butaca_ocupada
        FROM ENTRADA
        WHERE NUM_SALA = p_sala
          AND FECHA_TRASMISION = p_fecha
          AND NUM_BUTACA = p_butaca;

        IF butaca_ocupada > 0 THEN
            SELECT 'Seat already taken' AS Message;

        ELSE
            -- comprobar aforo
            SELECT AFORO INTO aforo_sala
            FROM SALA
            WHERE NUM_SALA = p_sala;

            SELECT COUNT(*) INTO total_ocupadas
            FROM ENTRADA
            WHERE NUM_SALA = p_sala
              AND FECHA_TRASMISION = p_fecha;

            IF total_ocupadas >= aforo_sala THEN
                SELECT 'Full hall' AS Message;
            ELSE
                SELECT 'Valid ticket' AS Message;
            END IF;

        END IF;
    END IF;

END //

DELIMITER ;