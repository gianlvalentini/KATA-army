package army;

import java.util.HashMap;
import static army.Civilization.*;
import static army.Recruiting.recruitArmy;

public class ArmyPrototype {

    private HashMap<Civilization, Army> prototypes = new HashMap<Civilization, Army>();

    public ArmyPrototype() {

        ChinaArmy chinaArmy = new ChinaArmy(recruitArmy(China));
        ByzantineArmy byzantineArmy = new ByzantineArmy(recruitArmy(Byzantine));
        EnglandArmy englandArmy = new EnglandArmy(recruitArmy(England));

        prototypes.put(China, chinaArmy);
        prototypes.put(Byzantine, byzantineArmy);
        prototypes.put(England, englandArmy);
    }

    public Army createArmy(Civilization civilization) throws CloneNotSupportedException {
        return (Army) prototypes.get(civilization).clone();
    }
}
