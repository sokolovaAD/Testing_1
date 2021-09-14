package model;

public class Dolphin extends Creature{
    public Activity activity;

    public Dolphin (String type) {
            setAnimalType(type);
            setPlanet("Земля");
            this.activity = new Activity(ActivityType.ENTERTAINMENT, "Плескаться в воде и развлекаться.");
            think();
    }


    @Override
    public void think() {
        if (this.activity.getType() == ActivityType.ENTERTAINMENT) {
            setIdeology("Дельфины разумнее людей");
        }
        else setIdeology("Я никчемный дельфин");
    }
}
