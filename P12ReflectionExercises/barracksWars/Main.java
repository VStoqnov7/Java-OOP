package P12ReflectionExercises.barracksWars;

import P12ReflectionExercises.barracksWars.data.UnitRepository;
import P12ReflectionExercises.barracksWars.interfaces.Repository;
import P12ReflectionExercises.barracksWars.interfaces.Runnable;
import P12ReflectionExercises.barracksWars.interfaces.UnitFactory;
import P12ReflectionExercises.barracksWars.core.factories.UnitFactoryImpl;
import P12ReflectionExercises.barracksWars.core.Engine;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
