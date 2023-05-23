package org.Repository;

import org.Classes.Rol;
import org.Classes.Prima;
import org.Database.DatabaseConfiguration;
import org.Services.ReadWrite;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PrimaRepository
{
    private static PrimaRepository primaRepository;
    private PrimaRepository() { }
    public static PrimaRepository getInstance()
    {
        if (primaRepository == null) primaRepository = new PrimaRepository();

        return primaRepository;
    }
    public void createTable() {
        String createTableSql = "CREATE TABLE PRIMA (" +
                "id NUMBER(10), " +
                "angajat NUMBER(10)," +
                "eveniment VARCHAR2(40), " +
                "suma NUMBER)";

        String createSequenceSql = "CREATE SEQUENCE PRIMA_SEQ START WITH 1 INCREMENT BY 1";

        String createTriggerSql = "CREATE OR REPLACE TRIGGER PRIMA_TRIGGER " +
                "BEFORE INSERT ON PRIMA " +
                "FOR EACH ROW " +
                "BEGIN " +
                "  SELECT PRIMA_SEQ.NEXTVAL INTO :new.id FROM DUAL; " +
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

//    public void createTable()
//    {
//        String createTableSql = "CREATE TABLE  PRIMA " +
//                "(id int PRIMARY KEY AUTO_INCREMENT, " +
//                "angajat int," +
//                "eveniment varchar(40), " +
//                "suma int)";
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

    public void addData()
    {
        String selectSql = "SELECT * FROM PRIMA";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Prima> prime = ReadWrite.readPrima();

                for (Prima p : prime)
                {
                    addPrima(p.getAngajatId(), p.getEveniment(), p.getSuma());
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addPrima(int angajatId, String ev, int suma)
    {
        String insertPrimaSql = "INSERT INTO PRIMA(angajat, eveniment, suma) VALUES("
                + angajatId + ", '" + ev + "', " + suma + ")";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertPrimaSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayPrima()
    {
        String selectSql = "SELECT * FROM PRIMA";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Angajat ID: " + resultSet.getInt(2));
                System.out.println("Eveniment Name: " + resultSet.getString(3));
                System.out.println("Suma: " + resultSet.getInt(4));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing bonus!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
