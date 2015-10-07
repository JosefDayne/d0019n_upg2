package bank;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Filip & Josef
 */
public class Bank {
    private String name, inp;
    private final ArrayList<Kund> userList;
    private Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bank bank = new Bank("Nordbanken");
        bank.start();
        System.out.println("skitprogram");
        //testfunktioner
        //bank.createUser("9612317199");
        //bank.createUser("9401213716");
    }
    
    public Bank(String name){
        this.name = name;
        userList = new ArrayList<>();
    }
    
    private void start(){
        System.out.println("Välkommen till " + name);
        while(true){
            System.out.printf("Vad vill du göra? %n",
                    "1. Logga in %n",
                    "2. Skapa ny användare %n",
                    "3. Avsluta %n");
            inp = scanner.next();
            if (inp.equals("1")){
                //do login stuff
            }else if(inp.equals("2")){
                //do create user stuff
            }else if(inp.equals("3")){
                System.exit(0);
            }else{
                System.out.println("Försök igen");
            }
        }
    }
        
    /*
    // Onödig när vi inte har pw
    private boolean login(String pnr){
        return userExist(pnr);
    }*/
    
    private void createUser(String pnr){        
        //TODO kolla om det finns användare med samma pnr
        if (luhn(pnr) && userExist(pnr)){
            Kund user = new Kund(pnr);
            userList.add(user);
        }
    }
    
    private boolean userExist(String pnr){
        Kund tmpKund;
        for (int i = 0; i < userList.size(); i++){
            tmpKund = userList.get(i);
            if (tmpKund.getPnr().equals(pnr)){
                return true;
            }
        }
        return false; 
    }
    
    private boolean luhn(String pnr){
        ArrayList<Integer> pnrList = new ArrayList<>();     // Stores numbers for Luhn
        int ctrlNum = Character.getNumericValue(pnr.charAt(9));        // Control number
        for (int i = 0; i < 9; i++){
            int tmp = Character.getNumericValue(pnr.charAt(i));        // Converts char to int
            if (i%2 == 0){
                tmp*=2;
                if (tmp > 9){
                    tmp%=10;                                           // split and add both to the list
                    pnrList.add(1);                                    // if over 10
                    pnrList.add(tmp);
                }else{
                    pnrList.add(tmp);
                }
            }else{
                pnrList.add(tmp);
            }            
        }        
        int result = 0;
        for (int i: pnrList){
            result += i;
        }   
        result = 10-result%10;                  // Result = next ten - result, for example 50-41 = 9;
        return (result == ctrlNum);            
    }
    
    
}
