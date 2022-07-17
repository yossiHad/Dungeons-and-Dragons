package Backend.Units;

import Backend.objectFields.Abilities.Blizzard;

import java.util.List;

public class Mage extends Player {
    private final int MANA_ADDITION = 25;
    private final int SPELL_POWER_ADDITION = 10;

    private Blizzard Blizzard;

    public Mage(String name, int healthAmount, int attackPoints, int defencePoints,int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(name,healthAmount,attackPoints,defencePoints);
        Blizzard = new Blizzard(manaPool,manaCost,spellPower,hitsCount,abilityRange);
    }

    public String toString(){
        String output = super.toString();
        return String.format(output + "Mana: %s,  SpellPower: %s",Blizzard.ManaString(),Blizzard.spellString());
    }
    @Override
    public void levelUp(){
        int attackBefore = attackPoints;
        int healthBefore = health.getHealthPool();
        int defenceBefore = defencePoints;
        int spellBefore = Blizzard.getSpellPower();
        int manaBefore = Blizzard.getMana();
        super.levelUp();
        Blizzard.updateLevel(MANA_ADDITION,SPELL_POWER_ADDITION,level);
        messageCall(String.format("you just leveled up to level - %d: Attack:%d (+%d),  Health:%d (+%d),  Defence:%d (+%d),  Spell Power:%d (+%d),  Mana:%d (+%d)",level,attackPoints,(attackPoints - attackBefore),health.getHealthPool(),(health.getHealthPool() - healthBefore),defencePoints,(defencePoints - defenceBefore),Blizzard.getSpellPower(),(Blizzard.getSpellPower() - spellBefore),Blizzard.getMana(),(Blizzard.getMana() - manaBefore)));
    }
    public int getManaPool(){
        return Blizzard.getMana();
    }
    public int getCurrentMana(){
        return Blizzard.getCurrentMana();
    }
    public int getSpellPower(){
        return Blizzard.getSpellPower();
    }

    public void cast(List<Enemy> enemies){
        if(Blizzard.cast()) {
            messageCall(String.format("%s used Blizard\n",getName()));
            int hits = 0;
            int hitsCount = Blizzard.getHitsCount();
            while (hits < hitsCount && !enemies.isEmpty()){
                int random = valueGenerator.getValue(enemies.size() - 1);
                Enemy enemy = enemies.get(random);
                int abilityAttack = Blizzard.getSpellPower();
                cast(enemy,abilityAttack);
                if(!enemy.alive())
                    enemies.remove(enemy);
                hits++;
            }
        }
        else{
            messageCall(String.format("%s tried to use Blizard but didn't have enough mana",getName()));
            tick();
        }
    }
    public void tick(){
        Blizzard.tick(level);
    }
    public int getRange(){
        return Blizzard.getRange();
    }
}
