CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS eventos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    videojuego VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    fecha VARCHAR(20) NOT NULL,
    hora VARCHAR(10) NOT NULL,
    modalidad VARCHAR(20) NOT NULL,
    descripcion TEXT NOT NULL,
    organizador VARCHAR(100) NOT NULL,
    requisitos TEXT NOT NULL,
    enlace_inscripcion VARCHAR(255) NOT NULL,
    foto_url VARCHAR(255) NOT NULL,
    destacado BOOLEAN DEFAULT FALSE
);