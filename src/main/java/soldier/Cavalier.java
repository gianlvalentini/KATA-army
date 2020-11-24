package soldier;

import static soldier.SoldierUnitType.*;

public class Cavalier implements SoldierUnit {

    @Override
    public SoldierUnitType getUnitType() {
        return cavalier;
    }

    @Override
    public int train() {
        return 10;
    }

    @Override
    public SoldierUnitType upgrade() {
        return cavalier;
    }
}
