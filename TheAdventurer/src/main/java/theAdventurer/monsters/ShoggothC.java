package theAdventurer.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ShoggothC extends AbstractMonster {
    public static final String ID = "Shoggoth_C";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    public static final int HP_MIN = 16;
    public static final int HP_MAX = 20;

    private static final byte CLAWS_DAMAGE = 2;
    private static final byte SLAM_DAMAGE = 5;

    private int STR_AMOUNT = 1;
    private static final int BLOCK_AMT = 5;

    //Smaller blob Shoggoth
    public ShoggothC(float x, float y) {
        super(NAME, "Shoggoth_C", HP_MAX, 0.0F, -0.0F, 100.0F, 100.0F, (String)null, x, y);

        this.type = EnemyType.ELITE;

        this.setHp(HP_MIN, HP_MAX);

        //array 0
        this.damage.add(new DamageInfo(this, CLAWS_DAMAGE));
        //array 1
        this.damage.add(new DamageInfo(this, SLAM_DAMAGE));


        this.loadAnimation("theAdventurerResources/images/monsters/ShoggothC/skeleton.atlas", "theAdventurerResources/images/monsters/ShoggothC/skeleton.json", 1.0F);
        TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener(new SlimeAnimListener());
    }

    public void takeTurn() {
        switch(this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_VERTICAL, true));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_DIAGONAL));
                this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.BLUNT_HEAVY));;
                this.setMove((byte)3, Intent.DEFEND_BUFF);
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, this.BLOCK_AMT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, this.STR_AMOUNT), this.STR_AMOUNT));
                this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
        }

    }

    protected void getMove(int num) {

//    if (AbstractDungeon.aiRng.randomBoolean()) {
//        this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
//        } else {
//            this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
//        }

        this.setMove((byte) 1, Intent.ATTACK, ((DamageInfo) this.damage.get(0)).base, 2, true);
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("theStarKnight:Shoggoth_C");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
