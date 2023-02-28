package Laborator2;

public class Exemplu1 {
    //comentariu o linie
    /*
    Mai multe linii
    */

    /**
     *
     * @param args  documentatie
     */

    /*
    public -> cel mai permisiv
    private -> cel mai restrictiv
    default-package -> accesibil doar din pachetul din care face parte
    protected ->  vizibil din acelasi pachet si in alt pachet prin mostenire
     */

    //membrii unei clase : campuri si metode
            //Ex camp
    private int numarLaborator;
        //metoda
    private int getNumarLaborator(){
        return numarLaborator;
    }
    public void setNumarLaborator(int numarLaborator){
        this.numarLaborator=numarLaborator;

    }
    public static void main(String[] args){


    }
}
