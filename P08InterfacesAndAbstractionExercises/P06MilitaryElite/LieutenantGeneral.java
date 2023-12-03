package P08InterfacesAndAbstractionExercises.P06MilitaryElite;

import java.util.Collection;

public interface LieutenantGeneral extends Private {

    Collection<Soldier> getPrivates();

    void addPrivate(Soldier soldier);
}
