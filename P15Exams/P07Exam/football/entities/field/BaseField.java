package P15Exams.P07Exam.football.entities.field;

import P15Exams.P07Exam.football.common.ConstantMessages;
import P15Exams.P07Exam.football.common.ExceptionMessages;
import P15Exams.P07Exam.football.entities.player.Player;
import P15Exams.P07Exam.football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseField implements Field{

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;  //Todo capacity


    public BaseField(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();

    }
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public int sumEnergy() {
        return supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    @Override
    public void addPlayer(Player player) {
        if (this.players.size() < this.capacity){
            players.add(player);
        }else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }

    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player player : players) {
            player.stimulation();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):",this.getName(),this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append("Player: ");
        sb.append(this.players.stream().map(Player::getName).collect(Collectors.joining(" ")));
        sb.append(System.lineSeparator());
        sb.append(String.format("Supplement: %d",supplements.size()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Energy: %d",this.sumEnergy()));

        return sb.toString().trim();
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
