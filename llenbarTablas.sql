-- Insertar datos de huertos
INSERT INTO Huerto (id, nombre, ubicacion, superficie) VALUES
(1, 'Huerto El granjero', 'Chile', 100),
(2, 'Huerto La entrada', 'Chile', 200),
(3, 'Huerto Los puntales', 'Chile', 150);

-- Insertar datos de especie
INSERT INTO Especie (id, nombre) VALUES
(1, 'Cerezo');

-- Insertar datos de árboles
INSERT INTO Arbol (id, idHuerto, idEspecie) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 2, 1),
(5, 2, 1),
(6, 2, 1),
(7, 3, 1),
(8, 3, 1),
(9, 3, 1),
(10, 3, 1);



-- Insertar datos de variedades
INSERT INTO Variedad (id, nombre, idEspecie) VALUES
(1, 'Bing', 1),
(2, 'Stella', 1),
(3, 'Lapins', 1),
(4, 'Rainier', 1),
(5, 'Santina', 1);
