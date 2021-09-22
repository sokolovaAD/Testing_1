package modelTest;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TruthTest {
    private Truth truth;
    private Person person;
    private Dolphin dolphin;

    @BeforeEach
    void init() {
        this.truth = new Truth();
        this.person = new Person("Люди");
        this.dolphin = new Dolphin("Дельфины");
    }

    @Test
    void truthTest() {
        truth.makeStatus(person, dolphin, "Люди");
        Assertions.assertEquals(truth.status, "Истина такова, какой кажется");
        Assertions.assertFalse(truth.isFact);

        truth.makeStatus(person, dolphin,"Дельфины");
        Assertions.assertEquals(truth.status, "Истина зачастую совсем не такова, какой кажется");
        Assertions.assertTrue(truth.isFact);

        truth.makeStatus(person, dolphin, "Жабы");
        Assertions.assertEquals(truth.status, "Жабы" + " классные, и это истина");
        Assertions.assertTrue(truth.isFact);
    }

}
