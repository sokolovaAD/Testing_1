package modelTest;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreaturesTest {
    private Dolphin dolphin;
    private Person person;
    private String name = "Some creatures";

    @BeforeEach
    void init() {
        dolphin = new Dolphin(name);
        person = new Person(name);
    }

    @Test
    public void dolphinBasicFieldsTest() {
       Assertions.assertEquals(dolphin.activity.getType(), ActivityType.ENTERTAINMENT);
       Assertions.assertEquals(this.dolphin.activity.getDescription(), "Плескаться в воде и развлекаться.");
       Assertions.assertEquals(this.dolphin.getIdeology(), "Дельфины разумнее людей");
       Assertions.assertEquals(this.dolphin.getAnimalType(), name);
       Assertions.assertEquals(this.dolphin.getPlanet(), "Земля");
    }

    @Test
    public void creatureSetFieldsTest() {
        dolphin.setAnimalType("New type");
        person.setAnimalType("Another new type");
        Assertions.assertEquals(dolphin.getAnimalType(), "New type");
        Assertions.assertEquals(person.getAnimalType(), "Another new type");

        dolphin.setIdeology("Im the best");
        person.setIdeology("Im looser");
        Assertions.assertEquals(dolphin.getIdeology(), "Im the best");
        Assertions.assertEquals(person.getIdeology(), "Im looser");

        dolphin.setPlanet("Mars");
        person.setPlanet("Moon");
        Assertions.assertEquals(dolphin.getPlanet(), "Mars");
        Assertions.assertEquals(person.getPlanet(), "Moon");

        dolphin.activity.setType(ActivityType.INVENTING);
        dolphin.think();
        Assertions.assertEquals(dolphin.activity.getType(), ActivityType.INVENTING);
        Assertions.assertEquals(dolphin.getIdeology(), "Я никчемный дельфин");

        person.activity.setType(ActivityType.ENTERTAINMENT);
        person.think();
        Assertions.assertEquals(person.activity.getType(), ActivityType.ENTERTAINMENT);
        Assertions.assertEquals(person.getIdeology(), "Я никчемный человек");
    }


    @Test
    public void personBasicFieldsTest() {
        Assertions.assertEquals(this.person.activity.getType(), ActivityType.INVENTING);
        Assertions.assertEquals(this.person.activity.getDescription(), "Колесо, Нью-Йорк, война и т.д.");
        Assertions.assertEquals(this.person.getIdeology(), "Люди разумнее дельфинов");
        Assertions.assertEquals(this.person.getAnimalType(), name);
        Assertions.assertEquals(this.person.getPlanet(), "Земля");
    }

}
