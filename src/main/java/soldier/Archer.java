package soldier;

import static soldier.SoldierUnitType.*;

public class Archer implements SoldierUnit {

    @Override
    public SoldierUnitType getUnitType() {
        return archer;
    }

    @Override
    public int train() {
        return 7;
    }

    @Override
    public SoldierUnitType upgrade() {
        return cavalier;
    }
}
