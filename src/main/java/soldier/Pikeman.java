package soldier;

import static soldier.SoldierUnitType.archer;
import static soldier.SoldierUnitType.pikeman;

public class Pikeman implements SoldierUnit {

    @Override
    public SoldierUnitType getUnitType() {
        return pikeman;
    }

    @Override
    public int train() {
        return 3;
    }

    @Override
    public SoldierUnitType upgrade() {
        return archer;
    }
}
