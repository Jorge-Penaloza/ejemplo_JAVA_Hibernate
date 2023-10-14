 SELECT
    h.nombre AS huerto,
    v.nombre AS variedad,
    COUNT(*) AS total_arboles
 FROM
    Huerto h
    INNER JOIN Arbol a ON h.id = a.idHuerto
    INNER JOIN Variedad v ON a.idVariedad = v.id
 GROUP BY
    h.nombre,
    v.nombre;