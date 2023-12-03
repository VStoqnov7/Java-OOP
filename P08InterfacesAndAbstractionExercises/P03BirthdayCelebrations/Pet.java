package P08InterfacesAndAbstractionExercises.P03BirthdayCelebrations;

public class Pet implements Birthable{

    private String name;
    private String birthDate;

    public Pet(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }
    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String toString() {
        return String.format("%s",this.birthDate);
    }
}
