package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

public class IchorShieldPower extends AbstractPower implements CloneablePowerInterface {
    //public AbstractCreature source;
    //private static final Logger logger = LogManager.getLogger(IchorShieldPower.class.getName());


    public static final String POWER_ID = DefaultMod.makeID("IchorShieldPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/IchorShield_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/IchorShield_32.png");

    public IchorShieldPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        //this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        //System.out.println("onAttack called within IchorShieldPower");
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            this.flash();
            this.addToTop(new ApplyPowerAction(info.owner, this.owner, new IchorPower(info.owner, this.owner, this.amount), this.amount));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        //System.out.println("atStartOfTurn called within IchorShieldPower");
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new IchorShieldPower(owner, amount);
    }

}
