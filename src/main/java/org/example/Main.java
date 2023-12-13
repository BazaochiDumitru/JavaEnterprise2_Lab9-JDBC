package org.example;

// Importăm clasele necesare din pachetul java.sql pentru a lucra cu JDBC
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Inițializăm obiectul Connection pentru a gestiona conexiunea la baza de date.
        Connection connection = null;
        // Inițializăm obiectul Statement pentru a executa instrucțiuni SQL.
        Statement statement = null;
        // Inițializăm obiectul ResultSet pentru a păstra rezultatele interogării.
        ResultSet rs = null;
        try {
            // Conectăm la baza de date MariaDB folosind DriverManager și specificăm URL-ul, numele utilizatorului și parola.
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jdbc", "root", "root");

            // Cream un obiect Statement pentru a executa instrucțiuni SQL pe conexiunea noastră.
            statement = connection.createStatement();

            // Executăm o interogare SQL pentru a selecta toate înregistrările din tabela "emp".
            rs = statement.executeQuery("select * from emp");

            // Iterăm prin rezultatele interogării și le afișăm în consolă.
            while (rs.next()) {
                System.out.format("%10s", rs.getInt("empno") + " ");
                System.out.format("%10s", rs.getString("ename") + " ");
                System.out.format("%10s", rs.getString("job") + " ");
                System.out.format("%10s", rs.getInt("mgr") + " ");
                System.out.format("%10s", rs.getString("hiredate") + " ");
                System.out.format("%10s", rs.getFloat("sal") + " ");
                System.out.format("%10s", rs.getFloat("comm") + " ");
                System.out.format("%10s", rs.getInt("deptno") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestionăm orice excepții SQL care pot apărea.
        } finally {
            // În blocul finally, ne asigurăm că resursele (conexiune, instrucțiune și rezultat) sunt închise corect pentru a evita scurgeri de resurse.
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}