package org.Services;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.Classes.*;

public class ReadWrite {
    public ReadWrite() {
    }

    public static void writePrima(int angjId, String ev, int suma) {
        String file = "src/main/java/org/files/prima.csv";


        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String primaString = "" + angjId + "," + ev + "," + suma;
            pw.println(primaString);
            pw.flush();
            pw.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public static List<Prima> readPrima() {
        List<Prima> prime = new ArrayList();
        String file = "src/main/java/org/files/prima.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                int angId = Integer.parseInt(row[0]);
                String ev = row[1];
                int suma = Integer.parseInt(row[2]);
                prime.add(new Prima(angId, ev, suma));
            }

            reader.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return prime;
    }

    public static void writeRol(String name, int salariu) {
        String file = "src/main/java/org/files/rol.csv";

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String rolString = name + "," + salariu;
            pw.println(rolString);
            pw.flush();
            pw.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public static List<Rol> readRol() {
        List<Rol> roluri = new ArrayList();
        String file = "src/main/java/org/files/rol.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
//                System.out.println(row[0]);
//                System.out.println(row[1]);
                String name = row[0];
                int salariu = Integer.parseInt(row[1]);
                roluri.add(new Rol(name, salariu));
            }

            reader.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return roluri;
    }

    public static void writeVanzare(int vanzareId, int suma, boolean tip, Sucursala sucursala,LocalDate d){
        String file = "src/main/java/org/files/vanzare.csv";

        try{
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String vanzareString = "" + vanzareId + "," + suma + "," + tip+ "," + sucursala.toString() + "," + d.toString();
            pw.write(vanzareString);
            pw.flush();
            pw.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    public static List<Vanzare> readVanzare(){
        List<Vanzare> vanzari = new ArrayList();
        String file = "src/main/java/org/files/vanzare.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                int vanzareId = Integer.parseInt(row[0]);
                int suma = Integer.parseInt(row[1]);
                boolean tip = Boolean.parseBoolean(row[2]);


                String sucursala = row[3];
                Sucursala suc = new Sucursala();
                Iterator var24 = Service.sucursale.iterator();

                while(var24.hasNext()) {
                    Sucursala s = (Sucursala)var24.next();
                    if (suc.equals(s.getName())) {
                        suc = s;
                        break;
                    }
                }

                LocalDate d = LocalDate.parse(row[4]);
                Vanzare vanzare = new Vanzare(vanzareId, suma, tip, suc, d);
                vanzari.add(vanzare);
            }

            reader.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return vanzari;

    }


    public static void writeSucursala(String name, Address a) {
        String file = "src/main/java/org/files/sucursala.csv";;

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String sucursalaString = name + "," + a.toString();
            pw.println(sucursalaString);
            pw.flush();
            pw.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public static List<Sucursala> readSucursala() {
        List<Sucursala> sucursale = new ArrayList();
        String file = "src/main/java/org/files/sucursala.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String name = row[0];
                String city = row[1];
                String county = row[2];
                String street = row[3];
                int number = Integer.parseInt(row[4]);
                Address a = new Address(city, county, street, number);
                Sucursala m = new Sucursala(name, a);
                sucursale.add(m);
            }

            reader.close();
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return sucursale;
    }



    public static void writeAngajat(int angId, String firstName, String lastName, String email, Address a, LocalDate d, String rol,  String sucursala) {
        String file = "src/main/java/org/files/angajat.csv";

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String angString = "" + angId + "," + firstName + "," + lastName + "," + email + "," + a.toString() + "," + d.toString() + "," + rol  + "," + sucursala;
            pw.println(angString);
            pw.flush();
            pw.close();
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

    public static List<Angajat> readAngajat() {
        List<Angajat> angajati = new ArrayList();
        String file = "src/main/java/org/files/angajat.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                int id = Integer.parseInt(row[0]);
                String firstName = row[1];
                String lastName = row[2];
                String email = row[3];
                String city = row[4];
                String county = row[5];
                String street = row[6];
                int number = Integer.parseInt(row[7]);
                LocalDate date = LocalDate.parse(row[8]);
                String rol = row[9];
                String sucursala = row[10];
                Address a = new Address(city, county, street, number);
                Rol rol1 = new Rol();
                Iterator var19 = Service.roles.iterator();

                while(var19.hasNext()) {
                    Rol r = (Rol)var19.next();
                    if (rol1.equals(r.getRol())) {
                        rol1 = r;
                        break;
                    }
                }

                Sucursala suc = new Sucursala();
                Iterator var24 = Service.sucursale.iterator();

                while(var24.hasNext()) {
                    Sucursala s = (Sucursala)var24.next();
                    if (suc.equals(s.getName())) {
                        suc = s;
                        break;
                    }
                }

                Angajat ang = new Angajat(id, firstName, lastName, email, a, date, rol1, suc);

                angajati.add(ang);
            }

            reader.close();
        } catch (Exception var23) {
            var23.printStackTrace();
        }

        return angajati;
    }
}
