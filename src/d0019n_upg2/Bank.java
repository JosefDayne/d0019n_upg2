package d0019n_upg2;

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
    private boolean loop;

    //idé för inloggad
    private Kund onlineUser;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bank bank = new Bank("Nordbanken");
        bank.start();
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

        loop = true;        // loop activated
        while (loop) {
            System.out.printf("Vad vill du göra? %n" +
                    "1. Logga in %n" +
                    "2. Skapa ny användare %n" +
                    "3. Avsluta %n");
            inp = scanner.next();
            switch (inp) {
                case "1":
                    login();
                    break;
                case "2":
                    createUser();
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("1 - 3 Tack!");
                    break;
            }
        }
    }
        

    private void login(){
        Kund tmpKund;
        System.out.println("Personnummer: (10 siffror)");
        inp = scanner.next();
        if (userExist(inp)){
            for (int i = 0; i < userList.size(); i++){
                tmpKund = userList.get(i);
                if (tmpKund.getPnr().equals(inp)){
                    onlineUser = tmpKund;
                    loop = false;       // deactivate loop from start(), successful login
                }
            }
            System.out.println("Välkommen " + onlineUser.getPnr() + "\n");     // Success
            loggedIn();
        }
        else{
            System.out.println("Fel! Försök igen!\n");
        }
    }

    private void loggedIn(){
        loop = true;
        while(loop){
            System.out.printf("Vad vill du göra? %n" +
                    "1. Lista konton %n" +
                    "2. Insättning %n" +
                    "3. Uttag %n" +
                    "4. Logga ut%n");
            inp = scanner.next();
            switch (inp) {
                case "1":
                    // lista konton
                    break;
                case "2":
                    // insättning
                    break;
                case "3":
                    // uttag
                    break;
                case "4":
                    // log out
                    System.exit(0);
                default:
                    System.out.println("1 - 4 Tack!\n");
                    break;
            }

        }
    }
    
    private void createUser(){
        //TODO kolla om det finns användare med samma pnr
        System.out.println("Personnummer: (10 siffror)");
        inp = scanner.next();
        if (luhn(inp) && !userExist(inp)){
            Kund user = new Kund(inp);
            userList.add(user);
            System.out.println("Ny användare tillagd\n");
        }
        else{
            System.out.println("Fel, Försök igen\n");
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
