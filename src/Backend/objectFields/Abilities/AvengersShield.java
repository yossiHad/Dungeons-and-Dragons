package Backend.objectFields.Abilities;

public class AvengersShield extends Ability {
    private int remainingCoolDown;
    private int coolDown;

    public AvengersShield(int coolDown, int abilityRange){
        super(abilityRange);
        this.coolDown = coolDown;
        remainingCoolDown = 0;
    }

    public void tick(){
        remainingCoolDown = remainingCoolDown == 0 ? 0 : remainingCoolDown - 1;
    }

    public boolean cast(){
        boolean cast = remainingCoolDown == 0;
        remainingCoolDown = cast ? coolDown : remainingCoolDown - 1;
        return cast;
    }

    public String CollDownString(){
        return remainingCoolDown + "/" + coolDown;
    }
}
