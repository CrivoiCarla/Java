package org.Repository;

import org.Classes.*;
import org.Database.DatabaseConfiguration;
import org.Services.ReadWrite;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.Services.Service.*;

public class AngajatRepository
{
    private static AngajatRepository angajatRepository;
    private AngajatRepository() { }
    public static AngajatRepository getInstance()
    {
        if (angajatRepository == null) angajatRepository = new AngajatRepository();

        return angajatRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE ANGAJAT " +
                "(id INTEGER PRIMARY KEY, " +
                "firstname varchar(40), " +
                "lastname varchar(30), " +
                "email varchar(40), " +
                "address varchar(100), " +
                "birthdate date, " +
                "rol varchar(40), " +
                "sucursala varchar(60))";


        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.execute(createTableSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addData()
    {
        String selectSql = "SELECT * FROM ANGAJAT";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Angajat> angajati = ReadWrite.readAngajat();

                for (Angajat a : angajati)
                {
                    addAngajat(a.getAngajatId(), a.getFirstName(), a.getLastName(), a.getEmail(),
                            a.getAddress(), a.getBirthDate(),  a.getRol().getRol(),
                            a.getSucursala().getName());
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addAngajat(int id, String firstname, String lastname, String email, Address a,
                           LocalDate date, String rol, String sucursala)
    {
        String insertAngajatSql = "INSERT INTO ANGAJAT(id, firstname, lastname, email, address, birthdate, " +
                "rol,  sucursala) VALUES(" + id + ", '" + firstname + "', '" + lastname +
                "', '" + email + "', '" + a.toString() + "', DATE '" + date + "', '" +
                rol + "', '" + sucursala + "')";

//        String insertAngajatSql = "INSERT INTO ANGAJAT(id, firstname, lastname, email, address, birthdate, " +
//                "rol,  sucursala) VALUES(" + ang + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertAngajatSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayAngajat()
    {
        String selectSql = "SELECT * FROM ANGAJAT";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Angajat ID: " + resultSet.getInt(1));
                System.out.println("First name: " + resultSet.getString(2));
                System.out.println("Last name: " + resultSet.getString(3));
                System.out.println("Email: " + resultSet.getString(4));
                System.out.println("Address: " + resultSet.getString(5));
                System.out.println("Birth Date: " + resultSet.getDate(6).toString());
                System.out.println("Role name: " + resultSet.getString(7));
                System.out.println("Shop: " + resultSet.getString(8));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing employees!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Angajat getAngajatById(int id)
    {
        String selectSql = "SELECT * FROM ANGAJAT WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAngajat(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }

    }

    public void updateAngajatFullName(String firstname, String lastname, int id)
    {
        String updateNameSql = "UPDATE ANGAJAT SET firstname=?, lastname=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql))
        {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAngajatById(int id)
    {
        String deleteStudentSql = "DELETE FROM ANGAJAT WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Angajat mapToAngajat(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next())
        {
            String[] a = resultSet.getString(5).split(",");
            Address address = new Address(a[0], a[1], a[2], Integer.parseInt(a[3]));

            LocalDate date = LocalDate.parse(resultSet.getString(6));

            Rol rol = new Rol();
            String domeniu = resultSet.getString(7);

            for (Rol r: roles)
            {
                if (domeniu.equals(r.getRol()))
                {
                    rol = r;
                    break;
                }
            }


            Sucursala sucursala = new Sucursala();
            String suc = resultSet.getString(8);

            for (Sucursala s : sucursale)
            {
                if (suc.equals(s.getName()))
                {
                    sucursala = s;
                    break;
                }
            }

            return new Angajat(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), address, date, rol, sucursala);


        }
        return null;
    }
}
