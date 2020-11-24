import army.Army;
import army.ArmyPrototype;
import army.Civilization;
import soldier.Soldier;

import static soldier.SoldierUnitType.*;

public class Start {

    public static void main(String[] args) throws CloneNotSupportedException {

        ArmyPrototype armyPrototype = new ArmyPrototype();

        Army chinaArmy1 = armyPrototype.createArmy(Civilization.China);
        Army chinaArmy2 = armyPrototype.createArmy(Civilization.China);
        Army bizantineArmy1 = armyPrototype.createArmy(Civilization.Byzantine);
        Army bizantineArmy2 = armyPrototype.createArmy(Civilization.Byzantine);
        Army englandArmy1 = armyPrototype.createArmy(Civilization.England);
        Army englandArmy2 = armyPrototype.createArmy(Civilization.England);

        System.out.println(chinaArmy1.trainSoldier().getForce());
        System.out.println(chinaArmy2.trainSoldier(pikeman).getForce());
        bizantineArmy1.trainUnit(archer).forEach(soldier -> System.out.println(soldier.getForce()));
        bizantineArmy2.trainAll().forEach(soldier -> System.out.println(soldier.getForce()));

        System.out.println(englandArmy1.transformSoldier(pikeman).getUnit());
        System.out.println(englandArmy2.transformSoldier(archer).getUnit());
        System.out.println(englandArmy2.transformSoldier(cavalier));

        System.out.println(chinaArmy1.attack(bizantineArmy1));
        System.out.println(chinaArmy2.attack(englandArmy1));
        System.out.println(bizantineArmy2.attack(englandArmy2));
        System.out.println(englandArmy1.attack(englandArmy2));
    }
}
