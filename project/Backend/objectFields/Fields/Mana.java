package Backend.objectFields.Fields;

public class Mana {
    private final int TICK_ADDITION = 1;

    private int manaPool;
    private int currentMana;
    private int cost;

    public Mana(int manaPool, int manaCost){
        this.manaPool = manaPool;
        currentMana = manaPool / 4;
        this.cost = manaCost;
    }

    public String toString(){
        return currentMana + "/" + manaPool;
    }

    public void updateLevel(int manaAddition, int level){
        manaPool = manaPool + (manaAddition * level);
        currentMana = Math.min(currentMana + (manaPool / 4),manaPool);
    }

    public void tick(int level){
        currentMana = Math.min(manaPool, currentMana + (TICK_ADDITION * level));
    }

    public boolean cast(){
        boolean cast = currentMana >= cost;
        currentMana = cast ? currentMana - cost : currentMana;
        return cast;
    }
    public int getMana(){
        return manaPool;
    }
    public int getCurrentMana(){
        return currentMana;
    }
}
