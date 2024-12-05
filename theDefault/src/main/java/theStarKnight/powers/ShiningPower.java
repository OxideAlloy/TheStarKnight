package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

public class ShiningPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("ShiningPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean playerTurn = true;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/Atlas_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/Atlas_32.png");

    public ShiningPower(final AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play ("POWER_METALLICIZE", 0.05F);
    }

//    @Override
//    public void atStartOfTurn() {
//        this.playerTurn = true;
//        //this.addToBot(new TalkAction(true, "start turn", 1.5F, 1.5F));
//    }
//    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
//        this.playerTurn = false;
//        this.addToBot(new TalkAction(true, "atEndOfTurnPreEndTurnCards", 1.5F, 1.5F));
//    }
//    public void atEndOfTurn(boolean isPlayer) {
//        this.addToBot(new TalkAction(true, "atEndOfTurn", 1.5F, 1.5F));
//    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            this.addToBot(new TalkAction(true, "damaged on my turn", 1.5F, 1.5F));
            this.flash();
            return 0;
        } else {
            this.addToBot(new TalkAction(true, "damaged NOT on my turn", 1.5F, 1.5F));
            return damageAmount;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new ShiningPower(owner);
    }
}
