/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0019n_upg2;

import java.util.ArrayList;

/**
 * Class for users of the bank
 * @author Filip
 */
public class Kund {
    private String pnr;
    private final ArrayList<Konto> kontoList;
    private Konto tmpKonto;

    /**
     * Constructor
     * @param pnr pnr of Kund
     */
    public Kund(String pnr){
        this.pnr = pnr;
        kontoList = new ArrayList<>();
    }

    /**
     * Returns pnr
     * @return pnr
     */
    public String getPnr(){
        return this.pnr;
    }

    /**
     * Add Konto to kontoList
     * @param konto Konto to be added
     */
    public void addKonto(Konto konto){
        kontoList.add(konto);
    }

    /**
     * Print all Konto of Kund
     */
    public void printKonto(){
        for (int i = 0; i < kontoList.size(); i++){
            tmpKonto = kontoList.get(i);
            System.out.printf(tmpKonto.getKontoNummer() + " Saldo: " + tmpKonto.getSaldo() + " ");
        }
    }

    /**
     * Remove Konto
     * @param kontoNmr Konto to be removed
     */
    public void removeKonto(String kontoNmr){
        for (int i = 0; i < kontoList.size(); i++){ // Less på att skriva kommentarer, lägg till kommentarer om du inte fattar så fixar jag
            tmpKonto = kontoList.get(i);
            if (tmpKonto.getKontoNummer().equals(kontoNmr)){
                kontoList.remove(tmpKonto);
                System.out.println(tmpKonto.getKontoNummer() + " Borttaget");
            }else{
                System.out.println(kontoNmr + " hittade inte, försök igen");
            }
        }
    }

    /**
     * Check if Konto exists
     * @param kontoNmr kontoNummer to be checked
     * @return true if exists, false if not
     */
    public boolean kontoExist(String kontoNmr){
        for (int i = 0; i < kontoList.size(); i++){
            tmpKonto = kontoList.get(i);
            if (tmpKonto.getKontoNummer().equals(kontoNmr)){
                return true;
            }
        }return false;
    }

    /**
     * Deposit money
     * @param kontoNmr Konto for deposit
     * @param tjugo amount of deposit
     */
    public void deposit(String kontoNmr, int tjugo){
        tmpKonto.deposit(tjugo);
    }

    /**
     * Withdraw money
     * @param kontoNmr Konto for withdrawal
     * @param tjugo amount of deposit
     */
    public void withdrawal(String kontoNmr, int tjugo){
        tmpKonto.withdrawal(tjugo);
    }
    
}
