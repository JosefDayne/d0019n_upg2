/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class Kund {
    private String pnr;
    private final ArrayList<Kund> kontoList;
    
    public Kund(String pnr){
        this.pnr = pnr;
       kontoList = new ArrayList<>();
    }
    
    public String getPnr(){
        return this.pnr;
    }
    
    
}
