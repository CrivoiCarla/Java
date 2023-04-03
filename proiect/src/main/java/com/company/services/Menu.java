package com.company.services;

import java.util.Scanner;

public class Menu
{
    // singleton class for an interactive menu

    private static Menu menu = null;
    private static final Service serviciu = new Service();

    // private constructor
    private Menu() {}

    private void showMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action.");
        System.out.println("1: Add new prima");
        System.out.println("2: Print all prime");
        System.out.println("3: Add new role");
        System.out.println("4: Print all role");
        System.out.println("5: Add vanzare");
        System.out.println("6: Print vanzari");
        System.out.println("7: Add new sucursala");
        System.out.println("8: Print all sucursale");
        System.out.println("9: Add new angajat");
        System.out.println("10: Print all angajati");
        System.out.println("11: Print angajati sorted alphabetically");
        System.out.println("0: Exit");
        System.out.println("-----------------------------------");
    }

    public void runMenu()
    {
        showMenu();

        Scanner reader = new Scanner(System.in);
        int option;
        String check;

        do
        {
            option = reader.nextInt();
            reader.nextLine();

            switch (option) {
                case 0 -> {
                    serviciu.closeConnection();
                    System.out.println("Exiting program..");
                }
                case 1 -> {
                    serviciu.addBonus();
                    System.out.println("-----------------------------------");
                }
                case 2 -> {
                    serviciu.printBonus();
                    System.out.println("-----------------------------------");
                }
                case 3 -> {
                    serviciu.addRol();
                    System.out.println("-----------------------------------");
                }
                case 4 -> {
                    serviciu.printRol();
                    System.out.println("-----------------------------------");
                }
                case 5 -> {
                    serviciu.addVanzare();
                    System.out.println("-----------------------------------");
                }
                case 6 -> {
                    serviciu.printVanzari();
                    System.out.println("-----------------------------------");
                }
                case 7 -> {
                    serviciu.addSucursala();
                    System.out.println("-----------------------------------");
                }
                case 8 -> {
                    serviciu.printSucursale();
                    System.out.println("-----------------------------------");
                }
                case 9 -> {
                    serviciu.addAngajat();
                    System.out.println("-----------------------------------");
                }
                case 10 -> {
                    serviciu.printAngajati();
                    System.out.println("-----------------------------------");
                }
                case 11 -> {
                    serviciu.printSortedAngajati();
                    System.out.println("-----------------------------------");
                }
                default -> System.out.println("Invalid option. Try again.");
            }

            if (option != 0)
            {
                System.out.println("Do you want another action? y/n");
                check = reader.nextLine();
                check = check.toLowerCase();

                if (check.equals("y")) showMenu();
                else {
                    option = 0;
                    serviciu.closeConnection();
                    System.out.println("Exiting program...");
                }
            }

        } while (option != 0);

        reader.close();
    }

    public static Menu getInstance()
    {
        if (menu == null) menu = new Menu();

        return menu;
    }
}
