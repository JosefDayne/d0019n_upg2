package d0019n_upg2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Filip & Josef
 */
public class Bank {
    private String name, inp;                                      // name = namn på banken (onödigt), inp = variabel för input från användaren
    private int inpInt;
    private final ArrayList<Kund> userList;
    private Scanner scanner = new Scanner(System.in);              // input från användaren
    private Kund tmpUser, currentUser = null, bankir = new Kund("0");      // temp (läs under), currentUser är inloggade användaren
    private Konto tmpKonto;                 // temp för att komma åt rätt konto från listan

    //TODO massor

    /**å
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bank bank = new Bank("Nordbanken");
        bank.start();
        //testfunktioner
        //bank.createUser();
        //bank.createUser();
    }

    public Bank(String name){               // konstruktor
        this.name = name;
        userList = new ArrayList<>();
    }
    
    private void start(){
        System.out.println("Välkommen till " + name);

        while(true) {       // main loop
            while (currentUser == null) {               // loopar så länge currentUser är null, dvs ingen inloggad
                System.out.printf("Vad vill du göra? %n" +
                        "1. Logga in %n" +
                        "2. Avsluta %n");
                inp = scanner.next();              // tar input från användaren
                switch (inp) {
                    case "1":
                        login();
                        break;
                    case "2":
                        System.exit(0);         // stänger av programmet
                    default:
                        System.out.println("1 - 2 Tack!");
                        break;
                }
            }
            while (currentUser != null) {           // när currentUser inte är null, dvs någon är inloggad
                System.out.printf("Vad vill du göra? %n" +
                        "1. Lista konton %n" +
                        "2. Insättning %n" +
                        "3. Uttag %n" +
                        "4. Logga ut%n");
                inp = scanner.next();           // uppdaterar input
                switch (inp) {
                    case "1":
                        currentUser.printKonto();
                        System.out.println();
                        break;
                    case "2":
                        something(1);
                        break;
                    case "3":
                        something(2);
                        break;
                    case "4":
                        // log out
                        System.out.println("Välkommen åter");
                        currentUser = null;             // logga ut
                        break;
                    default:
                        System.out.println("1 - 4 Tack!\n");
                        break;
                }

            }
        }
    }

    private void something(int someRandomInteger) {
        System.out.println("Ange kontonummer ");
        inp = scanner.next();
        System.out.println("Ange belopp");
        inpInt = scanner.nextInt();
        if (someRandomInteger == 1){
            currentUser.withdrawal(inp, inpInt);
        }else{
            currentUser.deposit(inp, inpInt);
        }
    }
        

    private void login(){
        System.out.println("Personnummer: (10 siffror)");
        inp = scanner.next();
        if (inp.equalsIgnoreCase("Bankir")){    // bankir som pnr = login som bankir, inget pw (säker bank)
            bankir();
        }
        else if (userExist(inp)){       // Om det inte är bakir kollar den ifall det finns någon kund/användare med det personnumret
            for (int i = 0; i < userList.size(); i++){  // går igenom hela listan, inga problem här
                tmpUser = userList.get(i);              // Pekare till varje objekt är sparat i listan och pekaren sparas till tmpUser så vi kommer åt listan inne i dem
                if (tmpUser.getPnr().equals(inp)){      // currentUser blir samma som tmpUser om pnr var lika.
                    currentUser = tmpUser;
                }
            }
            System.out.println("Välkommen " + currentUser.getPnr() + "\n");     // Success

        }
        else{                                           // varken bankir eller användare som finns
            System.out.println("Fel! Försök igen!\n");
        }
    }

    private void bankir(){
        currentUser = bankir;
        while(currentUser!=null){                   // inga konstigheter? Samma som förut
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
                    removeUser();
                    break;
                case "5":
                    removeKonto();
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
        inp = scanner.next();                           // uppdaterar input
        if (luhn(inp) && !userExist(inp)){              // om luhn är OK och det inte finns någon användare med
            tmpUser = new Kund(inp);                    // Skapar en ny Kund med personnumret och
            userList.add(tmpUser);                      // sparar den till listan
            System.out.println("Ny användare tillagd\n");
        }
        else{
            System.out.println("Fel, Försök igen\n");
        }
    }

    private void createKonto(){
        System.out.println("Var vänlig ange kundens personnummer (10 siffror) ");
        inp = scanner.next();                                       // uppdaterar input så programmet vet vilken användare som ska få nytt konto
        if (userExist(inp)){
            //TODO Kolla om konto existerar med samma nummer        // kanske kontoExist() för att kolla(?)
            System.out.println("Var vänlig ange kontonummer ");
            inp = scanner.next();                                   // uppdaterar input för att bestämma kontonummer
            tmpKonto = new Konto(inp);                              // Skapar nytt konto och kör in det i listan
            tmpUser.addKonto(tmpKonto);                             // Vi vet att tmpUser är rätt pga userExist()
        }else{
            System.out.println("Försök igen");
        }
    }

    private void printUsers(){
        for (int i = 0; i < userList.size(); i++){                 // kollar igenom hela listan för att printa ut varje kund och deras konton
            tmpUser = userList.get(i);                              // sparar till tmpUser för att komma åt listan och pnr
            System.out.printf(tmpUser.getPnr() + ": ");             // kallar getPnr som returnerar pnr
            tmpUser.printKonto();                                   // liknande funktion inne i Kund som printar alla konton
            System.out.println();                                   // Man kan ha return på dessa istället för print. Kanske bättre att göra så men kvittar i det här programmet.
        }

    }

    private void removeUser(){                                      // Tar bort en användare, då försvinner listan med alla dens konton
        System.out.println("Ange personnummer (10 siffror)");
        inp = scanner.next();
        if (userExist(inp)) {
            userList.remove(tmpUser);
        }
    }

    private void removeKonto(){             // Tar bort konto från någon viss användare
        System.out.println("Ange personnummer (10 siffror)");
        inp = scanner.next();
        if (userExist(inp)){
            System.out.println("Ange kontonummer ");
            inp = scanner.next();
            tmpUser.removeKonto(inp);
        }
    }


    private boolean userExist(String pnr){                          // kollar om användaren existerar
        for (int i = 0; i < userList.size(); i++){                  // går igenom varje objekt i listan
            tmpUser = userList.get(i);                              // sparar över så vi kommer åt getPnr för att jämföra
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
