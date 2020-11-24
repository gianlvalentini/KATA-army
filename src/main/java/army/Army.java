package army;

import bank.Banker;
import soldier.Soldier;
import soldier.SoldierUnitType;

import java.util.List;
import java.util.stream.Collectors;

import static bank.Action.train;
import static bank.Action.transform;
import static java.util.Comparator.comparing;
import static soldier.SoldierUnitType.cavalier;

public abstract class Army implements Cloneable {

    private List<Soldier> soldiers;
    private int coins;

    Banker banker = Banker.getInstance();

    public Army(List<Soldier> soldiers) {
        this.soldiers = soldiers;
        this.coins = 1000;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //TRAIN

    public Soldier trainSoldier() { //train the weakest soldier

        Soldier soldier = soldiers.stream().min(comparing(Soldier::getForce)).get();

        train(soldier);

        return soldier;
    }

    public Soldier trainSoldier(SoldierUnitType unit) { //train the weakest soldier from the unit

        List<Soldier> unitSoldiers = soldiers.stream().filter(soldier -> soldier.getUnitType().equals(unit)).collect(Collectors.toList());
        Soldier soldier =  unitSoldiers.stream().min(comparing(Soldier::getForce)).get();

        train(soldier);

        return soldier;
    }

    public List<Soldier> trainUnit(SoldierUnitType unit) {

        List<Soldier> unitSoldiers = soldiers.stream().filter(soldier -> soldier.getUnitType().equals(unit)).collect(Collectors.toList());

        train(unitSoldiers);

        return unitSoldiers;
    }

    public List<Soldier> trainAll() {

        train(soldiers);

        return soldiers;
    }

    private void train (Soldier soldier) {

        int totalCost = banker.checkBalance(train, soldier);

        if(coins >= totalCost) {
            soldier.train();
            pay(totalCost);

        } else
            //throw new InsufficientBalanceException(String.format("You don't have enough coins to train this soldier \n" +
            //        "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));
            System.out.println(String.format("You don't have enough coins to train this soldier \n" +
                            "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));

    }

    private void train (List<Soldier> soldiers) {

        int totalCost = banker.checkBalance(train, soldiers);

        if(coins >= totalCost) {
            soldiers.forEach(Soldier::train);
            pay(totalCost);

        } else
            //throw new InsufficientBalanceException(String.format("You don't have enough coins to train these soldiers \n" +
            //        "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));
            System.out.println(String.format("You don't have enough coins to train these soldier \n" +
                    "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));

    }

    //TRANSFORM

    public Soldier transformSoldier(SoldierUnitType unit) { //transforms the strongest soldier of the unit

        if(unit.equals(cavalier)) {
            //throw new InvalidTransformationException("the unit type cannot be transform");
            System.out.println("the unit [cavalier] cannot be transform");
            return null;
        }

        List<Soldier> unitSoldiers = soldiers.stream().filter(soldier -> soldier.getUnitType().equals(unit)).collect(Collectors.toList());
        Soldier soldier =  unitSoldiers.stream().max(comparing(Soldier::getForce)).get();

        transform(soldier);

        return soldier;

    }

    private void transform(Soldier soldier) {

        int totalCost = banker.checkBalance(transform, soldier);

        if(coins >= totalCost) {
            soldier.trasform();
            pay(totalCost);

        } else
        //    throw new InsufficientBalanceException(String.format("You don't have enough coins to transform this soldier \n" +
        //            "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));
            System.out.println(String.format("You don't have enough coins to transform this soldier \n" +
                    "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));
    }

    private void transform(List<Soldier> soldiersToTransform) {

        int totalCost = banker.checkBalance(transform, soldiersToTransform);

        if(coins >= totalCost) {
            soldiersToTransform.forEach(Soldier::trasform);
            pay(totalCost);

        } else
        //    throw new InsufficientBalanceException(String.format("You don't have enough coins to transform these soldiers \n" +
        //            "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));
            System.out.println(String.format("You don't have enough coins to transform these soldiers \n" +
                    "Your balance is: [%d coins], and this cost: [%d coins]", coins, totalCost));
    }

    //BATTLE

    public String attack(Army opponent) {

        int opponentForce = opponent.totalArmyForce();
        int myForce = totalArmyForce();

        if(myForce > opponentForce) {

            winBattle();
            opponent.loseBattle();

            return "you win!";

        } else if(myForce < opponentForce) {

            loseBattle();
            opponent.winBattle();

            return "you lose!";

        } else {

            drawBattle();
            opponent.drawBattle();

            return "it's a draw!";

        }
    }

    public void winBattle() { //win 100 coins

        int reward = 100;

        setCoins(coins + reward);
    }

    public void loseBattle() { //lose the best 2 soldiers

        int deadSoldier = 2;

        for (int i = 0; i < deadSoldier; i++) {

            try{
                soldiers.remove(soldiers.stream().max(comparing(Soldier::getForce)).get());
            }catch (Exception e) {
                System.out.println(String.format("Error while trying to remove dead soldiers after lose the battle, message: %s", e.getMessage()));
            }

        }
    }

    public void drawBattle() { //lose the worst soldier

        int deadSoldier = 1;

        for (int i = 0; i < deadSoldier; i++) {

            try{
                soldiers.remove(soldiers.stream().min(comparing(Soldier::getForce)).get());
            }catch (Exception e) {
                System.out.println(String.format("Error while trying to remove dead soldiers after lose the battle, message: %s", e.getMessage()));
            }
        }
    }

    public int totalArmyForce() {
        return soldiers.stream().mapToInt(Soldier::getForce).sum();
    }

    //PAY

    private void pay(int cost) { coins -= cost; }

    //GETTERS and SETTERS

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
