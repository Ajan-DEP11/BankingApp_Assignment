import java.util.Scanner;

public class BankAppAssignment{
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String NEW_ACCOUNT = "➕ Open New Account";
        

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[] customerIds = new String[0];
        String[] customerNames = new String[0];

        String screen = DASHBOARD;


        final String APP_TITLE = String.format("%s%s%s",
        COLOR_BLUE_BOLD, screen, RESET);

        System.out.println(CLEAR);
        System.out.println("-".repeat(45));
        System.out.println("\t" + APP_TITLE );
        System.out.println("-".repeat(45));

        mainLoop:
        do{

        switch(screen){
            case DASHBOARD: 
                System.out.println("\t[1]. Open New Account");
                System.out.println("\t[2]. Exit\n");
                System.out.println("-".repeat(45));
                System.out.print("\tEnter an option to continue: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option){
                    case 1: screen = NEW_ACCOUNT; break;
                    case 2: System.out.println(CLEAR); System.exit(0);
                    default: continue;
                }
                break;
                case NEW_ACCOUNT:
                    String id="";
                    String name;
                    boolean valid;
                    double initial_dep;

                    //Account ID
                    
                    System.out.printf("\tNew Account ID: SDB-%05d \n", (customerIds.length + 1));
                    //for(int i = 0; i<customerIds.length; i++){
                   // id = String.format("SDB-%05d",(i+1));
                    //customerIds[i] = id;
                

                    //Name validation
                    do{
                        valid = true;
                        System.out.print("\tEnter Customer's Name: ");
                        name = scanner.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name can't be Empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf(ERROR_MSG,"Invalid Name");
                                valid = false;
                                break;
                            }
                        }
                    }while(!valid);

                    //Initial deposit
                    do{
                        valid = true;
                        System.out.print("\tEnter the Initial Deposit: Rs.");
                        initial_dep = scanner.nextDouble();
                        scanner.nextLine();
                        if(initial_dep < 5000){
                            System.out.printf(ERROR_MSG,"Insuficiant Initial Bank Balance..Minimum Amount Should be Rs.5000.00");
                            valid = false;
                            break;
                        }

                    }while(!valid);

                    String[] newCustomerIds = new String[customerIds.length + 1];
                    String[] newCustomerNames = new String[customerNames.length + 1];
                    for (int i = 0; i < customerIds.length; i++) {
                        id = String.format("SDB-%05d",(i+1));
                        newCustomerIds[i] = customerIds[i];
                        newCustomerNames[i] = customerNames[i];
                    }
                    newCustomerIds[newCustomerIds.length - 1] = id;
                    newCustomerNames[newCustomerIds.length - 1] = name;
                    customerIds = newCustomerIds;
                    customerNames = newCustomerNames;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                    String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                }
            }while(true);
        }
    }
        
    