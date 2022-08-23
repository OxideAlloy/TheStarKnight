package theStarKnight.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ShoggothB extends AbstractMonster {
    public static final String ID = "Shoggoth_B";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    //public static final String[] MOVES;
    //public static final String[] DIALOG;

    public static final int HP_MIN = 26;
    public static final int HP_MAX = 30;

    private static final byte CHOMP_DAMAGE = 12;

    private int STR_AMOUNT = 1;

    //Top Jaw of Shoggoth
    public ShoggothB(float x, float y) {
        super(NAME, ID, 32, 0.0F, 260.0F, 130.0F, 100.0F, (String)null, x, y);

        this.type = EnemyType.ELITE;

        this.setHp(HP_MIN, HP_MAX);

        //array 0
        this.damage.add(new DamageInfo(this, CHOMP_DAMAGE));


        this.loadAnimation("theStarKnightResources/images/monsters/ShoggothB/skeleton.atlas", "theStarKnightResources/images/monsters/ShoggothB/skeleton.json", 1.0F);
        //this.loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
        TrackEntry e = this.state.setAnimation(0, "Idle", true);
        //TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener(new SlimeAnimListener());
    }

    public void takeTurn() {
        switch(this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 2, true), 2));
                this.setMove((byte)2, Intent.DEBUFF);
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_SLIME_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Slimed(), 1));
                this.setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SMASH));;
                this.setMove((byte)1, Intent.DEBUFF);
        }

    }

    protected void getMove(int num) {
        this.setMove((byte)1, Intent.DEBUFF);
    }

    static {
        //monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Shoggoth Head");
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("theStarKnight:Shoggoth_B");
        NAME = monsterStrings.NAME;
        //MOVES = monsterStrings.MOVES;
        //DIALOG = monsterStrings.DIALOG;
    }

//    static {
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
//        NAME = monsterStrings.NAME;
//    }
}
