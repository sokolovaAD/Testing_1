package model;

public class Main {
    public static void main (String[] args) {
        Person person = new Person ("Люди");
        Dolphin dolphin = new Dolphin("Дельфины");
        Truth truth = new Truth();
        System.out.println(person.getAnimalType());
        System.out.println(person.activity.getType());
        System.out.println(person.activity.getDescription());
        System.out.println(person.getIdeology());
        System.out.println(dolphin.getAnimalType());
        System.out.println(dolphin.activity.getType());
        System.out.println(dolphin.activity.getDescription());
        System.out.println(dolphin.getIdeology());
        System.out.println(truth.makeStatus(person, dolphin, "Дельфины"));

    }
}
