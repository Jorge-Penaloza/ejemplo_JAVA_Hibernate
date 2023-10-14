 SELECT
    arbol.idHuerto,
    huerto.nombre,
    huerto.ubicacion,
    huerto.superficie,
    arbol.idVariedad,
    variedad.nombre AS "nombreVariedad"
 FROM
    arbol
    INNER JOIN variedad ON arbol.idVariedad = variedad.id
    INNER JOIN huerto ON arbol.idHuerto = huerto.id
    INNER JOIN especie ON variedad.idEspecie = especie.id

    WHERE
    	especie.nombre = 'Cerezo'

 GROUP BY
    arbol.idHuerto,
    huerto.nombre,
    huerto.ubicacion,
    huerto.superficie,
    arbol.idVariedad,
    variedad.nombre;
