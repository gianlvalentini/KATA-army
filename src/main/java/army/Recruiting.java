package army;

import soldier.Soldier;
import soldier.SoldierUnitType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static soldier.SoldierUnitType.*;

public class Recruiting {

    public static List<Soldier> recruitArmy(Civilization civilization) {

        int pikemanQuantity = 0, archerQuantity = 0, cavalierQuantity = 0;
        List<List<Soldier>> soldiersByUnit = new ArrayList<List<Soldier>>();

        switch (civilization) {

            case China:
                pikemanQuantity = 2;
                archerQuantity = 25;
                cavalierQuantity = 2;
                break;

            case Byzantine:
                pikemanQuantity = 5;
                archerQuantity = 8;
                cavalierQuantity = 15;
                break;

            case England:
                pikemanQuantity = 10;
                archerQuantity = 10;
                cavalierQuantity = 10;
                break;
        }

        soldiersByUnit.add(recruitSoldiersUnit(pikeman, pikemanQuantity));
        soldiersByUnit.add(recruitSoldiersUnit(archer, archerQuantity));
        soldiersByUnit.add(recruitSoldiersUnit(cavalier, cavalierQuantity));

        return soldiersByUnit.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public static List<Soldier> recruitSoldiersUnit(SoldierUnitType soldierUnitType, int quantity) {

        List<Soldier> soldiers = new ArrayList<Soldier>();

        for(int i = 0; i < quantity; i++) {

            soldiers.add(new Soldier(soldierUnitType));
        }

        return soldiers;
    }
}
