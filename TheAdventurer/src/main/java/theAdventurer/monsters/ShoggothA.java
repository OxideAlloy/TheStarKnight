package theAdventurer.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class ShoggothA extends AbstractMonster {
    public static final String ID = "Shoggoth_A";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP_MIN = 20;
    private static final int HP_MAX = 24;

    private static final int BLOCK_AMT = 8;
    private static final int HEAL_AMT = 4;
    private static final byte BITE_DAMAGE = 8;



    //Bottom Jaw of Shoggoth
    public ShoggothA(float x, float y) {
        super(NAME, "Shoggoth_A", HP_MAX, 0.0F, 0.0F, 200.0F, 150.0F, (String)null, x, y);
//      (String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
//      this(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, false);

        this.type = EnemyType.ELITE;

        this.setHp(HP_MIN, HP_MAX);

        //array 0
        this.damage.add(new DamageInfo(this, BITE_DAMAGE));

        this.loadAnimation("theAdventurerResources/images/monsters/ShoggothA/skeleton.atlas", "theAdventurerResources/images/monsters/ShoggothA/skeleton.json", 1.0F);
        TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        //this.state.addListener(new SlimeAnimListener());
    }

    public void takeTurn() {
        Iterator var1;
        AbstractMonster m;
        switch(this.nextMove) {
            //All monsters gain 10 block.
            case 1:
                //System.out.println("takeTurn called with case 1");
                var1 = AbstractDungeon.getMonsters().monsters.iterator();
                while(true) {
                    if (!var1.hasNext()) {
                        this.setMove((byte)2, Intent.BUFF);
                        break;
                    }
                    m = (AbstractMonster)var1.next();
                    if (!m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m, this, this.BLOCK_AMT));
                    }
                }
                break;
            //All monsters heal 5.
            case 2:
                //System.out.println("takeTurn called with case 2");
                var1 = AbstractDungeon.getMonsters().monsters.iterator();
                while(true) {
                    if (!var1.hasNext()) {
                        this.setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                        break;
                    }
                    m = (AbstractMonster)var1.next();
                    if (!m.isDying && !m.isEscaping) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(m, this, this.HEAL_AMT));
                    }
                }
                break;
            case 3:
                //System.out.println("takeTurn called with case 3");
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SMASH));
                this.setMove((byte)1, Intent.DEFEND);
        }
    }

    protected void getMove(int num) {
//        //18 - Elites have more challenging movesets and abilities. (NOT IMPLEMENTED)
        this.setMove((byte)1, Intent.DEFEND);
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("theStarKnight:Shoggoth_A");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
