package modelTest;

import model.Activity;
import model.ActivityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivityTest {
    private Activity activity;

    @BeforeEach
    void init() {
        this.activity = new Activity(ActivityType.ENTERTAINMENT, "some desc");
    }

    @Test
    void createActivityTest() {
        Assertions.assertEquals(activity.getType(), ActivityType.ENTERTAINMENT);
        Assertions.assertEquals(activity.getDescription(), "some desc");
    }

    @Test
    void setActivityTest() {
        activity.setType(ActivityType.INVENTING);
        activity.setDescription("new desc");

        Assertions.assertEquals(activity.getType(), ActivityType.INVENTING);
        Assertions.assertEquals(activity.getDescription(), "new desc");
    }
}
