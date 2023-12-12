package P15Exams.P14Exam.aquarium.entities.aquariums;

import P15Exams.P14Exam.aquarium.common.ConstantMessages;
import P15Exams.P14Exam.aquarium.common.ExceptionMessages;
import P15Exams.P14Exam.aquarium.entities.decorations.Decoration;
import P15Exams.P14Exam.aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium{

    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;


    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();

    }


    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() < capacity){
            this.fish.add(fish);
        }else {
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);

    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fish1 : fish) {
            fish1.eat();
        }
    }

    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):",this.name,this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        if (fish.isEmpty()){
            sb.append("Fish: none");
        }else {
            sb.append(String.format("Fish: %s",this.fish.stream().map(Fish::getName).collect(Collectors.joining(" "))));
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Decorations: %d",decorations.size()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Comfort: %d",decorations.stream().mapToInt(Decoration::getComfort).sum()));

        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
