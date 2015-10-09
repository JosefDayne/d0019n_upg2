/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0019n_upg2;

import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class Kund {
    private String pnr;
    private final ArrayList<Konto> kontoList;
    private Konto tmpKonto;
    
    public Kund(String pnr){
        this.pnr = pnr;
        kontoList = new ArrayList<>();
    }
    
    public String getPnr(){
        return this.pnr;
    }

    public void addKonto(Konto konto){
        kontoList.add(konto);
    }

    public void printKonto(){
        for (int i = 0; i < kontoList.size(); i++){
            tmpKonto = kontoList.get(i);
            System.out.printf(tmpKonto.getKontoNummer() + " Saldo: " + tmpKonto.getSaldo() + " ");
        }
    }

    public void removeKonto(String kontoNmr){
        for (int i = 0; i < kontoList.size(); i++){
            tmpKonto = kontoList.get(i);
            if (tmpKonto.getKontoNummer().equals(kontoNmr)){
                kontoList.remove(tmpKonto);
                System.out.println(tmpKonto.getKontoNummer() + " Borttaget");
            }
        }
    }

    public void deposit(String kontoNmr, int tjugo){
        //TODO Bra om de gör något
    }

    public void withdrawal(String kontoNmr, int tjugo){
        //TODO Bra om de gör något
    }
    
}
