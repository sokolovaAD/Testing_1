package model;

public class Person extends Creature {

    Truth truth;
    public Activity activity;

    public Person (String type) {
        setAnimalType(type);
        setPlanet("Земля");
        this.activity = new Activity(ActivityType.INVENTING, "Колесо, Нью-Йорк, война и т.д.");
        think();
    }

    @Override
    public void think() {
        if (this.activity.getType() == ActivityType.INVENTING) {
            setIdeology("Люди разумнее дельфинов");
        }
        else setIdeology("Я никчемный человек");
    }
}
