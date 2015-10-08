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
    private Kund tmpUser, currentUser = null, bankir = new Kund("0000000000");
    private Konto tmpKonto;

    //TODO massor

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

        while(true) {       // main loop
            while (currentUser == null) {
                System.out.printf("Vad vill du göra? %n" +
                        "1. Logga in %n" +
                        "2. Avsluta %n");
                inp = scanner.next();
                switch (inp) {
                    case "1":
                        login();
                        break;
                    case "2":
                        System.exit(0);
                    default:
                        System.out.println("1 - 2 Tack!");
                        break;
                }
            }
            while (currentUser != null) {
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
                        System.out.println("Välkommen åter");
                        currentUser = null;
                        break;
                    default:
                        System.out.println("1 - 4 Tack!\n");
                        break;
                }

            }
        }
    }
        

    private void login(){
        Kund tmpKund;
        System.out.println("Personnummer: (10 siffror)");
        inp = scanner.next();
        if (inp.equalsIgnoreCase("Bankir")){
            bankir();
        }
        else if (userExist(inp)){
            for (int i = 0; i < userList.size(); i++){
                tmpKund = userList.get(i);
                if (tmpKund.getPnr().equals(inp)){
                    currentUser = tmpKund;
                }
            }
            System.out.println("Välkommen " + currentUser.getPnr() + "\n");     // Success

        }
        else{
            System.out.println("Fel! Försök igen!\n");
        }
    }

    private void bankir(){
        currentUser = bankir;
        while(currentUser!=null){
            System.out.printf("Vad vill du göra? %n" +
                    "1. Lägg till kunder%n" +
                    "2. Lägg till konton %n" +
                    "3. Lista kunder och deras konton %n" +
                    "4. Ta bort Kunder %n" +
                    "5. Ta bort Konton %n" +
                    "6. Logga ut%n");
            inp = scanner.next();
            switch (inp) {
                case "1":
                    createUser();
                    break;
                case "2":
                    createKonto();
                    break;
                case "3":
                    printUsers();
                    break;
                case "4":
                    // remove kunder
                    break;
                case "5":
                    // ta bort konton
                    break;
                case "6":
                    // log out
                    currentUser = null;
                    break;
                default:
                    System.out.println("1 - 6 Tack!\n");
                    break;
            }
        }

    }

    private void createUser(){
        System.out.println("Personnummer: (10 siffror)");
        inp = scanner.next();
        if (luhn(inp) && !userExist(inp)){
            tmpUser = new Kund(inp);
            userList.add(tmpUser);
            System.out.println("Ny användare tillagd\n");
        }
        else{
            System.out.println("Fel, Försök igen\n");
        }
    }

    private void createKonto(){
        System.out.println("Var vänlig ange kundens personnummer (10 siffror) ");
        inp = scanner.next();
        if (userExist(inp)){
            //TODO Kolla om konto existerar med samma nummer
            System.out.println("Var vänlig ange kontonummer ");
            inp = scanner.next();
            tmpKonto = new Konto(inp);
            tmpUser.addKonto(tmpKonto);
        }else{
            System.out.println("Försök igen");
        }
    }

    private void printUsers(){
        for (int i = 0; i < userList.size(); i++){
            tmpUser = userList.get(i);
            System.out.printf(tmpUser.getPnr() + ": ");
            tmpUser.printKonto();
            System.out.println();
        }

    }

    private void removeUser(){

    }

    private void removeKonto(){

    }

    private boolean userExist(String pnr){
        for (int i = 0; i < userList.size(); i++){
            tmpUser = userList.get(i);
            if (tmpUser.getPnr().equals(pnr)){
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
