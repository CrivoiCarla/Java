package org.Services;

import java.util.Scanner;

public class Menu
{
    // singleton class for an interactive menu

    private static Menu menu = null;
    private static final Service serviciu = new Service();



    // private constructor
    private Menu()
    {
      //  serviciu.configureTables();
        System.out.println("Tabele configurate");
        serviciu.loadData();
    }

    private void showMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action.");
        System.out.println("1: Add new bonus");
        System.out.println("2: Print all bonuses");
        System.out.println("3: Add new role");
        System.out.println("4: Print all roles");
        System.out.println("5: Add sale");
        System.out.println("6: Print sales");
        System.out.println("7: Add new store");
        System.out.println("8: Print all stores");
        System.out.println("9: Add new employee");
        System.out.println("10: Print all employees");
        System.out.println("11: Print employees sorted alphabetically");
        //--------------------ETAPA 2---------------------------------//
        System.out.println("12: Print employees by a given id");
        System.out.println("13: Update employees name by a given id");
        System.out.println("14: Delete employees by a given id");
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
                //--------------------ETAPA 2---------------------------------//
                case 12-> {
                    serviciu.printAngajatById();
                    System.out.println("-----------------------------------");
                }
                case 13-> {
                    serviciu.updateAngajat();
                    System.out.println("-----------------------------------");
                }
                case 14-> {
                    serviciu.deleteAngajatById();
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
