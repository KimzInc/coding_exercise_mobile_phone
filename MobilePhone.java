import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String number) {
        myNumber = number;
        myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact newContact) {
        int position = findContact(newContact.getName());
        if (position >= 0) {
            System.out.println("Contact "+ newContact.getName() + " already exists");
            return false;
        } else{
            myContacts.add(newContact);
            return true;
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int index = findContact(oldContact);
        if (index < 0) {
            return false;
        }else if(findContact(newContact.getName()) != -1) {
            System.out.println("Contact with name " + newContact.getName() + " already exists"
            + " Update was not successful");
            return false;
        }
        myContacts.set(index, newContact);
        return true;
    }

    public boolean removeContact(Contact contact) {
      int index = findContact(contact);
      if (index < 0) {
          return false;
      }
      myContacts.remove(index);
      return true;
    }

    private int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }
        return null;

    }

    public void printContacts() {
        System.out.println("Contact List");
        for (int i = 0; i < myContacts.size(); i++) {
            System.out.println((i + 1) + ". " + myContacts.get(i).getName() +
                    " -> " + myContacts.get(i).getPhoneNumber());
        }

    }

}
