package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import java.util.Iterator;

public class RuinPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("RuinPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/Ruin_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/Ruin_32.png");

    public RuinPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    @Override
//    public void onGainedBlock(float blockAmount) {
//        if (blockAmount > 0.0F) {
//            this.flash();
//            blockAmount = 0;
//            //return blockAmount;
//        }
//
//    }

    @Override
    public float modifyBlock(float blockAmount) {
        return 0;
    }


    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        System.out.println("onAttack hook called!");
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {

            int unblocked=damageAmount-target.currentBlock;
            System.out.println("You dealt this much unblocked damage "+unblocked);

            if(unblocked > 0) {
                System.out.println("If statement called!");
                this.flash();
                this.addToBot(new ApplyPowerAction(target, this.owner, new StrengthPower(target, -unblocked), -unblocked));
                if (target != null && !target.hasPower("Artifact")) {
                    this.addToBot(new ApplyPowerAction(target, this.owner, new GainStrengthPower(target, unblocked), unblocked));
                }
            }
        }
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        if (target.lastDamageTaken > 0) {
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new StrengthPower(m, -defaultSecondMagicNumber), -defaultSecondMagicNumber));
//        }
//        //return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
//    }

//    public void update() {
//        if (this.duration == 0.5F) {
//            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
//        }
//
//        this.tickDuration();
//        if (this.isDone) {
//            this.target.damage(this.info);
//            if (this.target.lastDamageTaken > 0) {
//                this.addToTop(new HealAction(this.source, this.source, this.target.lastDamageTaken));
//                this.addToTop(new WaitAction(0.1F));
//            }
//
//            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
//                AbstractDungeon.actionManager.clearPostCombatActions();
//            }
//        }
//
//    }




    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new VoidBornPower(owner, source, amount);
    }
}
