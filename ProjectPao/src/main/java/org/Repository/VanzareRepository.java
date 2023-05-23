package org.Repository;


import org.Classes.*;
import org.Database.DatabaseConfiguration;
import org.Services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.Classes.*;
import org.Database.DatabaseConfiguration;
import org.Services.ReadWrite;

public class VanzareRepository
{
    private static VanzareRepository vanzareRepository;
    private VanzareRepository() { }
    public static VanzareRepository getInstance()
    {
        if (vanzareRepository == null) vanzareRepository = new VanzareRepository();

        return vanzareRepository;
    }

//    public void createTable()
//    {
//        String createTableSql = "CREATE TABLE  VANZARE " +
//                "(id int PRIMARY KEY AUTO_INCREMENT, " +
//                "suma int,"+
//                "tip boolean,"+
//                "magazin varchar(60), " +
//                "data date)";
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
    String createSequenceSql = "CREATE SEQUENCE vanzare_seq START WITH 1 INCREMENT BY 1";

    String createTableSql = "CREATE TABLE VANZARE (" +
            "id NUMBER(10) PRIMARY KEY, " +
            "suma NUMBER," +
            "tip NUMBER(1)," +
            "magazin VARCHAR2(60), " +
            "data DATE)";

    String createTriggerSql = "CREATE TRIGGER vanzare_trigger " +
            "BEFORE INSERT ON VANZARE " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  SELECT vanzare_seq.NEXTVAL INTO :new.id FROM dual; " +
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
        String selectSql = "SELECT * FROM VANZARE";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Vanzare> vanzari = ReadWrite.readVanzare();
                for (Vanzare v : vanzari)
                {
                    addVanzare(v.getVanzareId() ,v.getSuma(),v.getTip(),v.getMagazin().getName(),v.getDataVanzare());
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addVanzare(int id, int suma, boolean tip, String sucursala, LocalDate date)
    {
        int tipValue = tip ? 0 : 1;

        String insertVanzareSql = "INSERT INTO VANZARE(id, suma, tip, magazin, data) VALUES("
                + id + ", " + suma + ", " + tip + ", '" + sucursala + "', DATE '" + date + "')";


        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertVanzareSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayVanzare()
    {
        String selectSql = "SELECT * FROM VANZARE";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Vanzare id: " + resultSet.getInt(1));
                System.out.println("Suma: " + resultSet.getInt(2));
                System.out.println("Tip: " + resultSet.getBoolean(3));
                System.out.println("Magazin: " + resultSet.getString(4));
                System.out.println("Data: " + resultSet.getDate(5));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing vanzari!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
