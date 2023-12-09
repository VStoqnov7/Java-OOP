package P15Exams.P05Exam.TaskForUnitTestings.java.P8ToyStore;

public class Toy {
    private String manufacturer;
    private String toyId;

    public Toy(String manufacturer, String toyId)
    {
        this.setManufacturer(manufacturer);
        this.setToyId(toyId);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    private void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getToyId() {
        return toyId;
    }

    private void setToyId(String toyId) {
        this.toyId = toyId;
    }
}
