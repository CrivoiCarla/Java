package Classes;

import java.time.LocalDate;

public class Vanzare {
    private int vanzareId;
    private int suma;
    private  boolean tip; // true - incasare, false - return
    private Sucursala magazin;
    private LocalDate dataVanzare;

    public Vanzare() { }

    public Vanzare(int vanzareId, int suma, boolean tip, Sucursala magazin, LocalDate dataVanzare) {
        this.vanzareId = vanzareId;
        this.suma = suma;
        this.tip = tip;
        this.magazin = magazin;
        this.dataVanzare = dataVanzare;
    }

    public int getVanzareId() {
        return vanzareId;
    }

    public void setVanzareId(int vanzareId) {
        this.vanzareId = vanzareId;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public boolean getTip() {
        return tip;
    }

    public void setTip(boolean tip) {
        this.tip = tip;
    }

    public Sucursala getMagazin() {
        return magazin;
    }

    public void setMagazin(Sucursala magazin) {
        this.magazin = magazin;
    }

    public LocalDate getDataVanzare() {
        return dataVanzare;
    }

    public void setDataVanzare(LocalDate dataVanzare) {
        this.dataVanzare = dataVanzare;
    }

    @Override
    public String toString() {
        return "Vanzare" +
                "Id Vanzare: " + vanzareId +
                "\nSuma: " + suma +
                "\nTip: " + tip +
                "(1- incasare/ 0- retur)\nMagazin: " + magazin +
                "\nData vanzare: " + dataVanzare ;
    }


}
