/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0019n_upg2;

/**
 *
 * @author Filip
 */
public class Konto {
    private String kontoNummer;
    private int saldo = 0;

    public Konto(String kontoNummer){
        this.kontoNummer = kontoNummer;
    }

    public String getKontoNummer(){
        return this.kontoNummer;
    }

    public int saldo(){
        return this.saldo;
    }
    
}
