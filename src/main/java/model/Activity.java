package model;

public class Activity {
    private ActivityType type;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Activity (ActivityType type, String description) {
            this.description = description;
            this.type = type;
    }
}
