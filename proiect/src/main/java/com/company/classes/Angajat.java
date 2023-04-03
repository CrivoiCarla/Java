package com.company.classes;

import java.time.LocalDate;

public class Angajat extends Person
{
    private int angajatId;
    private Rol rol;
    private Sucursala sucursala;

    public Angajat() { }

    public Angajat(int angajatId, String firstName, String lastName, String email, Address address,
                   LocalDate birthDate, Rol rol,  Sucursala sucursala)
    {
        super(firstName, lastName, email, address, birthDate);
        this.angajatId = angajatId;
        this.rol = rol;
        this.sucursala = sucursala;
    }

    public int getAngajatId()
    {
        return angajatId;
    }

    public void setAngajatId(int angajatId)
    {
        this.angajatId = angajatId;
    }

    public Rol getRol()
    {
        return rol;
    }

    public void setRol(Rol rol)
    {
        this.rol = rol;
    }


    public Sucursala getSucursala()
    {
        return sucursala;
    }

    public void setSucursala(Sucursala sucursala)
    {
        this.sucursala = sucursala;
    }

    @Override
    public String toString()
    {
        String s;
        s = "\nAngajat ID: " + angajatId + "\nFirst name: " + firstName + "\nLast name: " + lastName +
                "\nEmail: " + email + "\nAddress: " + address.toString() +
                "\nBirth Date: " + birthDate.toString();
        s = s + "\nRol: " + rol.getRol() + "\nSucursala: " + sucursala.getName() +"\n";

        return s;
    }
}
