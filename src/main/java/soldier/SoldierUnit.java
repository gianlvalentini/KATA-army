package soldier;

public interface SoldierUnit {

    SoldierUnitType getUnitType();
    int train();
    SoldierUnitType upgrade();
}
