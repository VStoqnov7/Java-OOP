package P15Exams.P15Exam.viceCity.core;

import P15Exams.P15Exam.viceCity.common.ConstantMessages;
import P15Exams.P15Exam.viceCity.core.interfaces.Controller;
import P15Exams.P15Exam.viceCity.models.guns.Gun;
import P15Exams.P15Exam.viceCity.models.guns.Pistol;
import P15Exams.P15Exam.viceCity.models.guns.Rifle;
import P15Exams.P15Exam.viceCity.models.neighbourhood.GangNeighbourhood;
import P15Exams.P15Exam.viceCity.models.neighbourhood.Neighbourhood;
import P15Exams.P15Exam.viceCity.models.players.CivilPlayer;
import P15Exams.P15Exam.viceCity.models.players.MainPlayer;
import P15Exams.P15Exam.viceCity.models.players.Player;
import P15Exams.P15Exam.viceCity.repositories.interfaces.GunRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private Collection<Player> civilPlayers;
    private Player mainPlayer;

    private GunRepository gunRepository;


    public ControllerImpl() {
        this.civilPlayers = new ArrayList<>();
        this.mainPlayer = new MainPlayer();
        this.gunRepository = new GunRepository();
    }

    @Override
    public String addPlayer(String name) {

        Player player = new CivilPlayer(name);
        civilPlayers.add(player);
        return String.format(ConstantMessages.PLAYER_ADDED,name);
    }

    @Override
    public String addGun(String type, String name) {

        Gun gun = null;
        switch (type){
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return String.format(ConstantMessages.GUN_TYPE_INVALID);
        }

        this.gunRepository.add(gun);

        return String.format(ConstantMessages.GUN_ADDED,name,type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (gunRepository.getModels().isEmpty()){
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        List<Gun> guns = new ArrayList<>(gunRepository.getModels());
        if (name.equals("Vercetti")){
            Gun gun = guns.get(0);
            mainPlayer.getGunRepository().add(gun);
            gunRepository.remove(gun);

            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER,gun.getName(),"Tommy Vercetti");
        }

        Player player = civilPlayers.stream().filter(player1 -> player1.getName().equals(name)).findFirst().orElse(null);
        if (player != null){
            Gun gun = guns.get(0);
            player.getGunRepository().add(gun);
            gunRepository.remove(gun);

            return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER,gun.getName(),name);
        }

        return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
    }

    @Override
    public String fight() {

        Neighbourhood neighbourhood = new GangNeighbourhood();

        boolean isValidMainPlayer = this.mainPlayer.getGunRepository().getModels().isEmpty();
        boolean isValidCivilPlayers = civilPlayers.stream()
                .allMatch(player -> player.getGunRepository().getModels().isEmpty());


        if (isValidMainPlayer && isValidCivilPlayers){
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }

        neighbourhood.action(this.mainPlayer,this.civilPlayers);

        StringBuilder sb = new StringBuilder();
        sb.append(ConstantMessages.FIGHT_HAPPENED);
        sb.append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE,this.mainPlayer.getLifePoints()));
        sb.append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,this.civilPlayers.stream().filter(player -> !player.isAlive()).count()));
        sb.append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE,this.civilPlayers.stream().filter(Player::isAlive).count()));
        return sb.toString().trim();
    }
}
