CREATE TABLE Huerto (
  id INT PRIMARY KEY,
  nombre VARCHAR(255),
  ubicacion VARCHAR(255),
  superficie DOUBLE
);

CREATE TABLE Especie (
  id INT PRIMARY KEY,
  nombre VARCHAR(255)
);

CREATE TABLE Variedad (
  id INT PRIMARY KEY,
  nombre VARCHAR(255),
  idEspecie INT,
  FOREIGN KEY (idEspecie) REFERENCES Especie(id)
);

CREATE TABLE Arbol (
  id INT PRIMARY KEY,
  idHuerto INT,
  idEspecie INT,
  FOREIGN KEY (idHuerto) REFERENCES Huerto(id),
  FOREIGN KEY (idEspecie) REFERENCES Especie(id)
);



