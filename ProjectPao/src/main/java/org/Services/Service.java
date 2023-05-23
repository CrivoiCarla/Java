package org.Services;

import org.Classes.*;
import org.Database.DatabaseConfiguration;
import org.Repository.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Service
{
    public static List<Prima> bonus = new ArrayList<>();
    public static List<Rol> roles = new ArrayList<>();

    public static List<Sucursala> sucursale = new ArrayList<>();
    public static List<Angajat> angajati = new ArrayList<>();

    public static List<Vanzare> vanzari = new ArrayList<>();

    //------------------------------------------------------------------------------------------------------------------//
    AngajatRepository angajatRepository= AngajatRepository.getInstance();
    PrimaRepository primaRepository = PrimaRepository.getInstance();
    SucursalaRepository sucursalaRepository = SucursalaRepository.getInstance();
    VanzareRepository vanzareRepository = VanzareRepository.getInstance();
    RolRepository rolRepository = RolRepository.getInstance();
    AuditService auditService = AuditService.getInstance();


    public Service() {
    }

    public void loadData() {
        //extrag date din csv
        angajati= ReadWrite.readAngajat();
        bonus = ReadWrite.readPrima();
        roles = ReadWrite.readRol();
        sucursale = ReadWrite.readSucursala();
        vanzari = ReadWrite.readVanzare();

        // salveaza din CSV in baza de date daca aceasta este goala
        this.rolRepository.addData();
        this.angajatRepository.addData();
        this.primaRepository.addData();
        this.sucursalaRepository.addData();
        this.vanzareRepository.addData();


        try {
            this.auditService.logAction("load data");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
    //------------------------------------------------------------------------------------------------------------------//
    public void addBonus()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Employee ID: ");
        int angajatId;

        while(true)
        {
            try
            {
                angajatId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID employee: ");
            }
        }

        System.out.print("Name of the Bonus: ");
        String eveniment = reader.nextLine().toUpperCase();

        int suma;

        System.out.print("Suma: ");
        while(true)
        {
            try
            {
                suma = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Suma: ");
            }
        }

        bonus.add(new Prima(angajatId, eveniment, suma));
        ReadWrite.writePrima(angajatId, eveniment, suma);
        PrimaRepository.addPrima(angajatId, eveniment, suma);

        try
        {
            auditService.logAction("add prima");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printBonus()
    {
        primaRepository.displayPrima();

        try
        {
            auditService.logAction("print prime");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void addRol()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name of the role: ");
        String rol = reader.nextLine();
        rol = rol.substring(0,1).toUpperCase() + rol.substring(1).toLowerCase();

        System.out.print("Salary for the role: ");
        int salary;

        while(true)
        {
            try
            {
                salary = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Salary for the role: ");
            }
        }

        roles.add(new Rol(rol, salary));
        ReadWrite.writeRol(rol, salary);
        rolRepository.addRol(rol, salary);


        try
        {
            auditService.logAction("add rol");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printRol()
    {
        rolRepository.displayRol();

        try
        {
            auditService.logAction("print roles");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }



    public void addSucursala()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Shop name: ");
        String name = reader.nextLine();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

        System.out.print("Address of the shop: ");
        Address a = readAddress();

        sucursale.add(new Sucursala(name, a));
        ReadWrite.writeSucursala(name, a);
        sucursalaRepository.addSucursala(name, a);

        try
        {
            auditService.logAction("add magazin");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printSucursale()
    {

        sucursalaRepository.displaySucursala();
        try
        {
            auditService.logAction("print magazine");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addAngajat() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Angajat ID: ");

        while(true) {
            int angajatId;
            try {
                angajatId = Integer.parseInt(reader.nextLine());
                boolean check = true;
                Iterator var4 = angajati.iterator();

                while(var4.hasNext()) {
                    Angajat s = (Angajat)var4.next();
                    if (s.getAngajatId() == angajatId) {
                        check = false;
                        break;
                    }
                }

                if (!check) {
                    throw new Exception();
                }
            } catch (NumberFormatException var17) {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID employee: ");
                continue;
            } catch (Exception var18) {
                System.out.println("Employee with given id already exists. Try again!");
                System.out.print("ID employee: ");
                continue;
            }

            System.out.print("First name: ");
            String firstName = reader.nextLine();
            String var10000 = firstName.substring(0, 1).toUpperCase();
            firstName = var10000 + firstName.substring(1).toLowerCase();
            System.out.print("Last name: ");
            String lastName = reader.nextLine();
            var10000 = lastName.substring(0, 1).toUpperCase();
            lastName = var10000 + lastName.substring(1).toLowerCase();

            while(true) {
                System.out.print("Email: ");
                String email = reader.nextLine().toLowerCase();
                if (email.contains("@")) {
                    System.out.print("Address: ");
                    Address a = this.readAddress();
                    System.out.print("Birth date: ");
                    LocalDate d = this.readDate();
                    Rol rol = new Rol();
                    boolean check = true;

                    while (check) {
                        System.out.print("Domain name: ");
                        String domain = reader.nextLine();
                        var10000 = domain.substring(0, 1).toUpperCase();
                        domain = var10000 + domain.substring(1).toLowerCase();
                        Iterator var11 = roles.iterator();

                        while (var11.hasNext()) {
                            Rol dom = (Rol) var11.next();
                            if (dom.getRol().equalsIgnoreCase(domain)) {
                                rol = dom;
                                check = false;
                            }
                        }

                        if (check) {
                            System.out.println("Role doesn't exist. Try again!");
                        }
                    }


                    Sucursala magazin = new Sucursala();
                    check = true;

                    while (check) {
                        System.out.print("Magazin : ");
                        String h = reader.nextLine();
                        var10000 = h.substring(0, 1).toUpperCase();
                        h = var10000 + h.substring(1).toLowerCase();
                        Iterator var27 = sucursale.iterator();

                        while (var27.hasNext()) {
                            Sucursala hs = (Sucursala) var27.next();
                            if (hs.getName().equalsIgnoreCase(h)) {
                                magazin = hs;
                                check = false;
                            }
                        }

                        if (check) {
                            System.out.println("Shop doesn't exist. Try again!");
                        }
                    }

                    angajati.add(new Angajat(angajatId, firstName, lastName, email, a, d, rol, magazin));
                    ReadWrite.writeAngajat(angajatId, firstName, lastName, email, a, d, rol.getRol(), magazin.getName());
                    angajatRepository.addAngajat(angajatId, firstName, lastName, email, a, d, rol.getRol(), magazin.getName());

                    try
                    {
                        auditService.logAction("add angajat");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Not a valid email address! Try again!");
                }

            }
        }
    }

    public void printAngajati()
    {
        angajatRepository.displayAngajat();
        try
        {
            auditService.logAction("prints angajat");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printSortedAngajati() {
        List<Angajat> sortedAngajati = angajati.stream().sorted(Comparator.comparing(Person::getLastName)).toList();
        System.out.println("angajati succesfully sorted.");
        Iterator var2 = sortedAngajati.iterator();

        while(var2.hasNext()) {
            Angajat a = (Angajat) var2.next();
            System.out.print(a.toString());
        }

    }
    public void addVanzare(){

        Scanner reader = new Scanner(System.in);

        int vanzareId;
        do {
            System.out.print("Vanzare Id: ");
            try {
                vanzareId = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vanzare Id trebuie să fie un număr întreg.");
                continue;
            }
            break;
        } while (true);

        int suma;
        do {
            System.out.print("Suma: ");
            try {
                suma = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Suma trebuie să fie un număr întreg.");
                continue;
            }
            break;
        } while (true);

        boolean tip;
        do {
            System.out.print("Tip (incasare/return): ");
            String input = reader.nextLine();
            if (input.equalsIgnoreCase("incasare")) {
                tip = true;
                break;
            } else if (input.equalsIgnoreCase("return")) {
                tip = false;
                break;
            }
            System.out.println("Tipul de vanzare trebuie să fie incasare sau return.");
        } while (true);

        Sucursala magazin = new Sucursala();


        boolean check = true;

        while (check) {
            System.out.print("Sucursala name: ");
            String h = reader.nextLine();
            String var10000 = h.substring(0, 1).toUpperCase();
            h = var10000 + h.substring(1).toLowerCase();
            Iterator var27 = sucursale.iterator();

            while (var27.hasNext()) {
                Sucursala hs = (Sucursala) var27.next();
                if (hs.getName().equalsIgnoreCase(h)) {
                    magazin = hs;
                    check = false;
                }
            }

            if (check) {
                System.out.println("Sucursala doesn't exist. Try again!");
            }
        }
        LocalDate dataVanzare = this.readDate();

        vanzari.add(new Vanzare(vanzareId, suma, tip, magazin, dataVanzare));
    }

    public void printVanzari()
    {
        for (Vanzare vanzare : vanzari)
        {
            System.out.println(vanzare.toString());
        }
    }
    public LocalDate readDate()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Day: ");
        int day;

        while(true)
        {
            try
            {
                day = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Day: ");
            }
        }

        System.out.print("Month: ");
        int month;

        while(true)
        {
            try
            {
                month = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Month: ");
            }
        }

        System.out.print("Year: ");
        int year;

        while(true)
        {
            try
            {
                year = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Year: ");
            }
        }

        return LocalDate.of(year, month, day);
    }
    public Address readAddress() {
        Scanner reader = new Scanner(System.in);
        System.out.print("City: ");
        String city = reader.nextLine();
        String var10000 = city.substring(0, 1).toUpperCase();
        city = var10000 + city.substring(1).toLowerCase();
        System.out.print("County: ");
        String county = reader.nextLine();
        var10000 = county.substring(0, 1).toUpperCase();
        county = var10000 + county.substring(1).toLowerCase();
        System.out.print("Street: ");
        String street = reader.nextLine();
        var10000 = street.substring(0, 1).toUpperCase();
        street = var10000 + street.substring(1).toLowerCase();
        System.out.print("Number: ");

        while(true) {
            try {
                int number = Integer.parseInt(reader.nextLine());
                return new Address(city, county, street, number);
            } catch (NumberFormatException var7) {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Number: ");
            }
        }
    }

    //-------------------------------------------ETAPA 2-------------------------------------------//
    public void configureTables() {
        this.angajatRepository.createTable();
        this.sucursalaRepository.createTable();
        this.vanzareRepository.createTable();
        this.primaRepository.createTable();
        this.rolRepository.createTable();


        try {
            this.auditService.logAction("configure tables");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    //-------------------------------------------ETAPA 2-------------------------------------------//
    public void printAngajatById() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Employee ID: ");

        int anjatId;
        while(true) {
            try {
                anjatId = Integer.parseInt(reader.nextLine());
                break;
            } catch (NumberFormatException var5) {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID employee: ");
            }
        }

        if (this.angajatRepository.getAngajatById(anjatId) != null) {
            System.out.println(this.angajatRepository.getAngajatById(anjatId).toString());
        } else {
            System.out.println("No existing employee with this id!");
        }

        try {
            this.auditService.logAction("print employee by id");
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public void updateAngajat() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Employee ID: ");

        int angajatId;
        while(true) {
            try {
                angajatId = Integer.parseInt(reader.nextLine());
                break;
            } catch (NumberFormatException var7) {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Employee ID: ");
            }
        }

        boolean check = false;
        Iterator var4 = angajati.iterator();

        while(var4.hasNext()) {
            Angajat a = (Angajat) var4.next();
            if (angajatId == a.getAngajatId()) {
                check = true;
                break;
            }
        }

        if (check) {
            System.out.print("New first name: ");
            String firstName = reader.nextLine();
            String var10000 = firstName.substring(0, 1).toUpperCase();
            firstName = var10000 + firstName.substring(1).toLowerCase();
            System.out.print("New last name: ");
            String lastName = reader.nextLine();
            var10000 = lastName.substring(0, 1).toUpperCase();
            lastName = var10000 + lastName.substring(1).toLowerCase();
            this.angajatRepository.updateAngajatFullName(firstName, lastName, angajatId);
            System.out.println("Employee updated succesfully!");
        } else {
            System.out.println("No existing employee with this id!");
        }

        try {
            this.auditService.logAction("update employee");
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void deleteAngajatById() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Employee ID: ");

        int angajatID;
        while(true) {
            try {
                angajatID = Integer.parseInt(reader.nextLine());
                break;
            } catch (NumberFormatException var7) {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Employee ID: ");
            }
        }

        boolean check = false;
        Iterator var4 = angajati.iterator();

        while(var4.hasNext()) {
            Angajat a = (Angajat) var4.next();
            if (angajatID == a.getAngajatId()) {
                check = true;
                break;
            }
        }

        if (check) {
            this.angajatRepository.deleteAngajatById(angajatID);
            System.out.println("Employee deleted succesfully!");
        } else {
            System.out.println("No existing employee with this id!");
        }

        try {
            this.auditService.logAction("delete employee by id");
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void closeConnection() {
        DatabaseConfiguration.closeDatabaseConnection();

        try {
            this.auditService.logAction("Close connection with database.");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

}
