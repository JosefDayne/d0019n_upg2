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

    /**
     * Constructor
     * @param kontoNummer kontoNummer
     */
    public Konto(String kontoNummer){
        this.kontoNummer = kontoNummer;
    }

    /**
     * Return kontoNummer
     * @return kontoNummer
     */
    public String getKontoNummer(){
        return this.kontoNummer;
    }

    /**
     * Return saldo
     * @return saldo
     */
    public int getSaldo(){
        return this.saldo;
    }

    /**
     * deposit money
     * @param num amount
     */
    public void deposit(int num){
        this.saldo+=num;
        System.out.println("Nytt saldo: " + this.saldo);
    }

    /**
     * withdraw money
     * @param num amount
     * @return true if succes, false if not
     */
    public boolean withdrawal(int num){
        if (num<=saldo){
           saldo -= num;
            System.out.println("Nytt saldo: " + this.saldo);
            return true;
        }else{
            return false;
        }
    }
    
}
