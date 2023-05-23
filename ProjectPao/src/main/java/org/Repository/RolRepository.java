package org.Repository;
import org.Classes.*;
import org.Database.DatabaseConfiguration;
import org.Services.ReadWrite;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RolRepository
{
    private static RolRepository rolRepository;
    private RolRepository() { }
    public static RolRepository getInstance()
    {
        if (rolRepository == null) rolRepository = new RolRepository();

        return rolRepository;
    }

//    public void createTable()
//    {
//        String createTableSql = "CREATE TABLE  ROL " +
//                "(id int PRIMARY KEY AUTO_INCREMENT, " +
//                "name varchar(40), " +
//                "salariu int)";
//
//        Connection connection = DatabaseConfiguration.getDatabaseConnection();
//
//        try (Statement stmt = connection.createStatement())
//        {
//            stmt.execute(createTableSql);
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
    public void createTable() {
    String createTableSql = "CREATE TABLE ROL (" +
            "id NUMBER(10), " +
            "name VARCHAR2(40), " +
            "salariu NUMBER)";

    String createSequenceSql = "CREATE SEQUENCE ROL_SEQ START WITH 1 INCREMENT BY 1";

    String createTriggerSql = "CREATE OR REPLACE TRIGGER ROL_TRIGGER " +
            "BEFORE INSERT ON ROL " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  SELECT ROL_SEQ.NEXTVAL INTO :new.id FROM DUAL; " +
            "END;";

    Connection connection = DatabaseConfiguration.getDatabaseConnection();

    try (Statement stmt = connection.createStatement()) {
        stmt.execute(createTableSql);
        stmt.execute(createSequenceSql);
        stmt.execute(createTriggerSql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void addData()
    {
        String selectSql = "SELECT * FROM ROL";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Rol> rols = ReadWrite.readRol();

                for (Rol r : rols)
                {
                    addRol(r.getRol(), r.getSalary());
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addRol(String name, int salary)
    {
        String insertRolSql = "INSERT INTO ROL(name, salariu) VALUES('"
                + name + "', " + salary + ")";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertRolSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayRol()
    {
        String selectSql = "SELECT * FROM ROL";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Salary: " + resultSet.getInt(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing roles!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
