package Backend.objectFields.Abilities;

import Backend.objectFields.Fields.Mana;

public class Blizzard extends Ability{
    private Mana mana;
    private int spellPower;
    private int hitsCount;

    public Blizzard(int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(abilityRange);
        mana = new Mana(manaPool, manaCost);
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
    }

    public String ManaString(){
        return mana.toString();
    }
    public int getMana(){
        return mana.getMana();
    }
    public int getCurrentMana(){
        return mana.getCurrentMana();
    }

    public String spellString(){
        return spellPower + "";
    }

    public void updateLevel(int manaAddition,int spellAddition,int level){
        mana.updateLevel(manaAddition,level);
        spellPower = spellPower + (spellAddition * level);
    }

    @Override
    public boolean cast() {
        return mana.cast();
    }

    public void tick(int level) {
        mana.tick(level);
    }

    public int getHitsCount(){
        return hitsCount;
    }

    public int getSpellPower(){
        return spellPower;
    }
}
