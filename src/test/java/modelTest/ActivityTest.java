package modelTest;

import model.Activity;
import model.ActivityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActivityTest {
    private Activity activity;

    @BeforeEach
    void init() {
        this.activity = new Activity(ActivityType.ENTERTAINMENT, "some desc");
    }

    @Test
    public void createActivityTest() {
        Assertions.assertEquals(activity.getType(), ActivityType.ENTERTAINMENT);
        Assertions.assertEquals(activity.getDescription(), "some desc");
    }

    @Test
    public void setActivityTest() {
        activity.setType(ActivityType.INVENTING);
        activity.setDescription("new desc");

        Assertions.assertEquals(activity.getType(), ActivityType.INVENTING);
        Assertions.assertEquals(activity.getDescription(), "new desc");
    }
}
