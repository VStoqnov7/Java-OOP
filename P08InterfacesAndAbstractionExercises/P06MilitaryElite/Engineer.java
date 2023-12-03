package P08InterfacesAndAbstractionExercises.P06MilitaryElite;

import java.util.Collection;

public interface Engineer extends SpecialisedSoldier {

    Collection<RepairImpl> getRepairs();

    void addRepair(RepairImpl repair);
}
