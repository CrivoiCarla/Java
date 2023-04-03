package com.company.classes;

import java.io.Serializable;

public class Prima implements Serializable
{
    private int angajatId;
    private String eveniment;
    private int suma;

    public Prima() { }

    public Prima(int angajatId, String eveniment, int suma)
    {
        this.angajatId = angajatId;
        this.eveniment = eveniment;
        this.suma = suma;
    }

    public int getAngajatId() {
        return angajatId;
    }

    public void setAngajatId(int angajatId) {
        this.angajatId = angajatId;
    }

    public String getEveniment()
    {
        return eveniment;
    }

    public void setEveniment(String eveniment)
    {
        this.eveniment = eveniment;
    }

    public int getSuma()
    {
        return suma;
    }

    public void setSuma(int suma)
    {
        this.suma = suma;
    }

    @Override
    public String toString()
    {
        return "Angajat ID: " + angajatId + "\nEveniment: " + eveniment + "\nSuma: " + suma + "\n";
    }
}
