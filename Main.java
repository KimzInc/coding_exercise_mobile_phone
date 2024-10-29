import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("0723083003");

    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printMenu();
        while (!quit) {
            System.out.println("\nEnter action: (6 to show available actions) ");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 0 -> {
                    quit = true;
                    System.out.println("System shutting down...");
                }
                case 1 -> mobilePhone.printContacts();
                case 2 -> addNewContact();
                case 3 -> updateContact();
                case 4 -> removeContact();
                case 5 -> queryForContact();
                case 6 -> printMenu();
            }
        }
    }
    private static void startPhone() {
        System.out.print("Phone starting... \n");
    }
    private static void printMenu() {
        String menu = """
                 Available actions:
                 0: - to shutdown
                 1: - to print contacts
                 2: - to add a new contact
                 3: - to update an existing contact
                 4: - to delete an existing contact
                 5: - to query if an existing contact exists
                 6: - to print a list of available actions
                 Enter a number for which action you would like to perform:""";
        System.out.print(menu + " ");
    }

    private static void addNewContact() {
        System.out.print("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);

        if(mobilePhone.addNewContact(newContact)){
            System.out.println("New contact added: name = "+ name + " phoneNumber = "+ phoneNumber);
        }else{
            System.out.println("Cannot add, " + name + " Contact already exists");
        }
    }
    private static void updateContact() {
        System.out.print("Enter existing contact name: ");
        String name = scanner.nextLine();
        mobilePhone.queryContact(name);

        Contact existingContact = mobilePhone.queryContact(name);
        if(existingContact == null){
            System.out.println("Contact does not exist");
        }else {
            System.out.println("Enter new contact name: ");
            String newName = scanner.nextLine();
            System.out.println("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();
            Contact newContact = Contact.createContact(newName, newPhoneNumber);
            if (mobilePhone.updateContact(existingContact, newContact)) {
                System.out.println("Contact updated");
            } else {
                System.out.println("Contact update failed");
            }
        }
    }

    private static void removeContact() {
        System.out.print("Enter contact name: ");
        String existingName = scanner.nextLine();
        mobilePhone.queryContact(existingName);

        Contact existingContact = mobilePhone.queryContact(existingName);
        if(existingContact == null){
            System.out.println("Contact does not exist");
        }else {
            if (mobilePhone.removeContact(existingContact)) {
                System.out.println("Contact removed");
            } else {
                System.out.println("Contact update failed");
            }
        }
    }
    private static void queryForContact() {
        System.out.print("Enter existing contact name: ");
        String existingName = scanner.nextLine();
        mobilePhone.queryContact(existingName);

        Contact existingContact = mobilePhone.queryContact(existingName);
        if(existingContact == null){
            System.out.println("Contact does not exist");
        }else {
            System.out.println("Name: " + existingContact.getName() + " PhoneNumber is: " + existingContact.getPhoneNumber());
        }
    }
}