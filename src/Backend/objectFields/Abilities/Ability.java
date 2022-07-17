package Backend.objectFields.Abilities;

public abstract class Ability {
    protected int range;

    public Ability(int range){
        this.range = range;
    }

    public abstract boolean cast();
    public int getRange(){
        return range;
    }

}
