-- Adaptar a la estructura real de tu MySQL antes de ejecutar.
CREATE DATABASE IF NOT EXISTS cineflow;
USE cineflow;

CREATE TABLE IF NOT EXISTS usuarios (
    usuario VARCHAR(50) PRIMARY KEY,
    contrasena INT NOT NULL,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes (
    usuario VARCHAR(50) PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    FOREIGN KEY (usuario) REFERENCES usuarios(usuario) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS administradores (
    usuario VARCHAR(50) PRIMARY KEY,
    FOREIGN KEY (usuario) REFERENCES usuarios(usuario) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS peliculas (
    codigo_pelicula INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    genero INT,
    duracion INT,
    director VARCHAR(100),
    actores_principales VARCHAR(255),
    sinopsis VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS salas (
    numero_sala INT PRIMARY KEY,
    capacidad INT NOT NULL,
    estado BOOLEAN NOT NULL,
    formato VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS butacas (
    fila CHAR(1) NOT NULL,
    numero INT NOT NULL,
    estado BOOLEAN NOT NULL,
    PRIMARY KEY (fila, numero)
);

CREATE TABLE IF NOT EXISTS funciones (
    codigo_funcion INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT NOT NULL,
    sala_id INT NOT NULL,
    horario DATETIME NOT NULL,
    es_subtitulada BOOLEAN NOT NULL,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(codigo_pelicula),
    FOREIGN KEY (sala_id) REFERENCES salas(numero_sala)
);

CREATE TABLE IF NOT EXISTS productos (
    codigo_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS reservas (
    codigo_reserva INT AUTO_INCREMENT PRIMARY KEY,
    cliente_usuario VARCHAR(50) NOT NULL,
    fecha_compra DATETIME NOT NULL,
    FOREIGN KEY (cliente_usuario) REFERENCES clientes(usuario)
);

CREATE TABLE IF NOT EXISTS tickets (
    codigo_ticket INT AUTO_INCREMENT PRIMARY KEY,
    funcion_id INT NOT NULL,
    butaca_fila CHAR(1) NOT NULL,
    butaca_numero INT NOT NULL,
    precio INT NOT NULL,
    reserva_id INT NOT NULL,
    FOREIGN KEY (funcion_id) REFERENCES funciones(codigo_funcion),
    FOREIGN KEY (butaca_fila, butaca_numero) REFERENCES butacas(fila, numero),
    FOREIGN KEY (reserva_id) REFERENCES reservas(codigo_reserva)
);
