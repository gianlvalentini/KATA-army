package bank;

import soldier.Soldier;
import soldier.SoldierUnitType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static soldier.SoldierUnitType.*;

public class Banker {

    private static Banker instance = new Banker();
    private HashMap<SoldierUnitType, Integer> trainFee = new HashMap<SoldierUnitType, Integer>();
    private HashMap<SoldierUnitType, Integer> transformFee = new HashMap<SoldierUnitType, Integer>();

    private Banker() {

        trainFee.put(pikeman, 10);
        trainFee.put(archer, 20);
        trainFee.put(cavalier, 30);
        transformFee.put(pikeman, 30);
        transformFee.put(archer, 40);
    }

    public static Banker getInstance() {
        return instance;
    }

    public int checkBalance (Action action, Soldier soldier) {

        List<Soldier> soldiers = new ArrayList<>();
        soldiers.add(soldier);

        return getCost(action, soldiers);
    }

    public int checkBalance (Action action, List<Soldier> soldiers) {

        return getCost(action, soldiers);
    }

    private int getCost(Action action, List<Soldier> soldiers) {

        int totalCost = 0;

        switch (action) {
            case train:
                totalCost = soldiers.stream().mapToInt(soldier -> trainFee.get(soldier.getUnitType())).sum();
                break;

            case transform:
                totalCost = soldiers.stream().mapToInt(soldier -> transformFee.get(soldier.getUnitType())).sum();
                break;
        }

        return totalCost;
    }
}
