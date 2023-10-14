
import java.sql.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
* Clase principal que permite administrar huertos, especies, variedades y árboles en una base de datos.
* Proporciona funcionalidades como listar las variedades de cerezos por huerto,
* listar el total de árboles por variedad y huerto, ingresar nuevas variedades,
* ingresar nuevas especies e ingresar nuevos árboles.
*/
public class AdministradorDeHuertos {
    private Connection connection;
    public AdministradorDeHuertos(Connection connection) {
        this.connection = connection;
    }
    /**
    * Muestra las variedades de cerezos por huerto en la base de datos.
    * Lee una consulta SQL desde un archivo y la ejecuta.
    * La consulta devuelve el nombre del huerto y el nombre de la variedad de cerezo.
    * Muestra los resultados por consola.
    */
    public void listarVariedadesCerezosPorHuerto() {
        try {
            // Ruta del archivo de consulta SQL
            String filePath = "SQL/queryVariedadesxHuerto.sql";

            // Leer la consulta SQL desde el archivo
            String sqlQuery = readQueryFromFile(filePath);

            //System.out.println(sqlQuery);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            System.out.println("Variedades de cerezos por huerto:");
            while (resultSet.next()) {
                String huerto = resultSet.getString("nombre");
                String variedad = resultSet.getString("nombreVariedad");
                System.out.println(huerto + ": " + variedad);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Permite ingresar una nueva variedad en la base de datos.
    * Solicita al usuario el ID, nombre y ID de la especie a la que pertenece la variedad.
    * Los valores ingresados se utilizan para ejecutar una instrucción INSERT en la tabla Variedad de la base de datos.
    * Muestra un mensaje indicando si la nueva variedad fue ingresada correctamente.
    */
    public void listarTotalArbolesPorVariedadYHuerto() {
        try {
            // Ruta del archivo de consulta SQL
            String filePath = "SQL/TotalArbolesPorVariedadYHuerto.sql";

            // Leer la consulta SQL desde el archivo
            String sqlQuery = readQueryFromFile(filePath);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            System.out.println("Total de árboles por variedad y huerto:");
            while (resultSet.next()) {
                String huerto = resultSet.getString("huerto");
                String variedad = resultSet.getString("variedad");
                int totalArboles = resultSet.getInt("total_arboles");
                System.out.println(huerto + " - " + variedad + ": " + totalArboles + " árbol(es)");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Permite ingresar una nueva variedad en la base de datos.
    * Solicita al usuario el ID, nombre y ID de la especie a la que pertenece la variedad.
    * Los valores ingresados se utilizan para ejecutar una instrucción INSERT en la tabla Variedad de la base de datos.
    * Muestra un mensaje indicando si la nueva variedad fue ingresada correctamente.
    */
    public void ingresarNuevaVariedad() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingreso de nueva variedad:");
        System.out.print("ID: ");
        int idVariedad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Nombre: ");
        String nombreVariedad = scanner.nextLine();

        System.out.print("ID de la especie a la que pertenece: ");
        int idEspecie = scanner.nextInt();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Variedad (id, nombre, idEspecie) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, idVariedad);
            preparedStatement.setString(2, nombreVariedad);
            preparedStatement.setInt(3, idEspecie);
            preparedStatement.executeUpdate();
            System.out.println("Nueva variedad ingresada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Realiza una consulta en la base de datos para obtener el total de árboles por variedad y huerto.
    * La consulta se lee desde un archivo de consulta SQL y se ejecuta en la base de datos.
    * Los resultados se imprimen en la consola, mostrando el nombre del huerto, la variedad y el total de árboles.
    */
    public void ingresarNuevaEspecie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingreso de nueva especie:");
        System.out.print("ID: ");
        int idEspecie = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Nombre: ");
        String nombreEspecie = scanner.nextLine();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Especie (id, nombre) VALUES (?, ?)");
            preparedStatement.setInt(1, idEspecie);
            preparedStatement.setString(2, nombreEspecie);
            preparedStatement.executeUpdate();
            System.out.println("Nueva especie ingresada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Permite ingresar un nuevo árbol en la base de datos.
    * Se solicita al usuario ingresar el ID, ID del huerto al que pertenece y ID de la variedad a la que pertenece.
    * Los datos ingresados se insertan en la tabla "Arbol".
    */
    public void ingresarNuevoArbol() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingreso de nuevo árbol:");
        System.out.print("ID: ");
        int idArbol = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("ID del huerto al que pertenece: ");
        int idHuerto = scanner.nextInt();
        System.out.print("ID de la variedad a la que pertenece: ");
        int idVariedad = scanner.nextInt();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Arbol (id, idHuerto, idVariedad) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, idArbol);
            preparedStatement.setInt(2, idHuerto);
            preparedStatement.setInt(3, idVariedad);
            preparedStatement.executeUpdate();
            System.out.println("Nuevo árbol ingresado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Punto de entrada principal del programa.
    *
    * @param args los argumentos de la línea de comandos
    */
    public static void main(String[] args) {
        try {
            // Crear conexión a la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agricultura", "root", "");

            // Crear objeto HuertoManager
            AdministradorDeHuertos huertoManager = new AdministradorDeHuertos(connection);

            // Menú
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 6) {
                System.out.println("---------------------");
                System.out.println("MENU");
                System.out.println("---------------------");
                System.out.println("1. Listar las variedades de cerezos por huerto");
                System.out.println("2. Listar el total de árboles por variedad y huerto");
                System.out.println("3. Ingreso de nuevas variedades");
                System.out.println("4. Ingreso de nuevas especies");
                System.out.println("5. Ingreso de nuevos árboles");
                System.out.println("6. Salir");
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        huertoManager.listarVariedadesCerezosPorHuerto();
                        break;
                    case 2:
                        huertoManager.listarTotalArbolesPorVariedadYHuerto();
                        break;
                    case 3:
                        huertoManager.ingresarNuevaVariedad();
                        break;
                    case 4:
                        huertoManager.ingresarNuevaEspecie();
                        break;
                    case 5:
                        huertoManager.ingresarNuevoArbol();
                        break;
                    case 6:
                        System.out.println("Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Lee el contenido de un archivo de consulta y lo devuelve como una cadena.
    *
    * @param filePath la ruta del archivo de consulta
    * @return una cadena con el contenido del archivo de consulta
    */
    public static String readQueryFromFile(String filePath) {
        StringBuilder queryBuilder = new StringBuilder();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                queryBuilder.append(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return queryBuilder.toString();
    }
}
