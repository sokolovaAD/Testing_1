package model;

public class Truth {
    public String status;
    public boolean isFact;

    public Truth() {}

    public String makeStatus(Person person, Dolphin dolphin, String whoseTruth) {
        if (person.activity.getType() == ActivityType.INVENTING && dolphin.activity.getType() == ActivityType.ENTERTAINMENT) {
        switch (whoseTruth) {
            case "Люди": {
                    this.status = "Истина такова, какой кажется";
                    isFact = false;
                    return status;
                }
            case "Дельфины": {
                    this.status = "Истина зачастую совсем не такова, какой кажется";
                    isFact = true;
                    return ("Широко известен и очень важен тот факт, что " + status);
                }
            default: {
                this.status = whoseTruth + " классные, и это истина";
                isFact = true;
                return ("Всем известно, что " + whoseTruth + " всегда правы, это и есть истина");
            }
        }
    }
        return ("Истина не определена");
    }
}
