package org.Repository;


import org.Classes.*;
import org.Database.DatabaseConfiguration;
import org.Services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SucursalaRepository
{
    private static SucursalaRepository sucursalaRepository;
    private SucursalaRepository() { }
    public static SucursalaRepository getInstance()
    {
        if (sucursalaRepository == null) sucursalaRepository = new SucursalaRepository();

        return sucursalaRepository;
    }

//    public void createTable()
//    {
//        String createTableSql = "CREATE TABLE  SUCURSALA " +
//                "(id int PRIMARY KEY AUTO_INCREMENT, " +
//                "name varchar(60), " +
//                "address varchar(100))";
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
    String createSequenceSql = "CREATE SEQUENCE sucursala_seq START WITH 1 INCREMENT BY 1";

    String createTableSql = "CREATE TABLE SUCURSALA (" +
            "id NUMBER(10) DEFAULT sucursala_seq.NEXTVAL PRIMARY KEY, " +
            "name VARCHAR2(60), " +
            "address VARCHAR2(100))";

    String createTriggerSql = "CREATE TRIGGER sucursala_trigger " +
            "BEFORE INSERT ON SUCURSALA " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  SELECT sucursala_seq.NEXTVAL INTO :new.id FROM dual; " +
            "END;";

    Connection connection = DatabaseConfiguration.getDatabaseConnection();

    try (Statement stmt = connection.createStatement()) {
        stmt.execute(createSequenceSql);
        stmt.execute(createTableSql);
        stmt.execute(createTriggerSql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void addData()
    {
        String selectSql = "SELECT * FROM SUCURSALA";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Sucursala> magazine = ReadWrite.readSucursala();

                for (Sucursala s : magazine)
                {
                    addSucursala(s.getName(), s.getAddress());
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addSucursala(String name, Address a)
    {
        String insertSsucursalaSql = "INSERT INTO SUCURSALA(name, address) VALUES('"
                + name + "','" + a.toString() + "')";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertSsucursalaSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displaySucursala()
    {
        String selectSql = "SELECT * FROM SUCURSALA";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Address: " + resultSet.getString(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing magazine!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
