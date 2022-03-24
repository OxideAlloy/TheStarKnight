package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

public class MalignantGrowthPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("MalignantGrowthPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/StrangeColour_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/StrangeColour_32.png");

    public MalignantGrowthPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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

    @Override
    public void atEndOfTurn(boolean playerTurn) {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(this.owner, this.owner, this.amount));
        this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

//    @Override
//    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
//        this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
//        return damage;
//    }

//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
//            this.flash();
//            this.addToBot(new LoseHPAction(target, this.owner, this.amount));
//        }
//
//    }

//    @Override
//    public int onAttacked(DamageInfo info, int damageAmount) {
//        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
//            this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
//            this.flash();
//        }
//
//        return damageAmount;
//    }

//    @Override
//    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
//        if (damage > 0 && type == DamageInfo.DamageType.NORMAL) {
//            this.addToBot(new LoseHPAction(owner, owner, this.amount));
//        }
//        return damage;
//    }

//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
//    }


    //figure out how to damage owner of power
//    @Override
//    public int onAttacked(DamageInfo info, int damageAmount) {
//
//        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
//            this.flash();
//            damageAmount = damageAmount + this.amount;
//        }
//
//        return damageAmount;
//    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new MalignantGrowthPower(owner, source, amount);
    }
}
