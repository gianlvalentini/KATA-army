package soldier;

public class Soldier {

    private SoldierUnit unit;
    private int force;

    //TODO this might be into the STATE patron (SoldierUnit)
    int pikemanInitForce = 5;
    int archerInitForce = 10;
    int cavalierInitForce = 20;

    public Soldier(SoldierUnitType soldierUnitType) {

        switch(soldierUnitType) {

            case pikeman:
                unit = new Pikeman();
                force = pikemanInitForce;
                break;

            case archer:
                unit = new Archer();
                force = archerInitForce;
                break;

            case cavalier:
                unit = new Cavalier();
                force = cavalierInitForce;
                break;

        }
    }

    public void train() {

        setForce(force + unit.train());
    }

    public void trasform() {

        switch (unit.upgrade()) {

            case archer:
                setUnit(new Archer());
                if(force < archerInitForce) {
                    setForce(archerInitForce);
                }
                break;

            case cavalier:
                setUnit(new Cavalier());
                if(force < cavalierInitForce) {
                    setForce(cavalierInitForce);
                }
                break;

        }
    }

    //TODO Battle


    //GETTERS and SETTERS

    public SoldierUnit getUnit() {
        return unit;
    }

    public void setUnit(SoldierUnit unit) {
        this.unit = unit;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public SoldierUnitType getUnitType() {
        return unit.getUnitType();
    }

}
