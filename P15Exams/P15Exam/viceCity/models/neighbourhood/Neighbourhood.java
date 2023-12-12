package P15Exams.P15Exam.viceCity.models.neighbourhood;

import P15Exams.P15Exam.viceCity.models.players.Player;

import java.util.Collection;

public interface Neighbourhood {
    void action(Player mainPlayer, Collection<Player> civilPlayers);
}
