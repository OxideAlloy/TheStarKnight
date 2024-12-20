package theStarKnight.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;


public class StackingDrawReductionPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("StackingDrawReductionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    //TODO Update the texture icons to a black Draw Reduction Symbol
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/DrawLoss_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/DrawLoss_32.png");

    //private boolean justApplied = true;

    public StackingDrawReductionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.DEBUFF;
        isTurnBased = false;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    @Override
//    public void atEndOfRound() {
////        if (this.justApplied) {
////            this.justApplied = false;
////        } else {
//            AbstractDungeon.player.gameHandSize -= amount;
////        }
//    }

//    @Override
//    public void onInitialApplication() {
//        --AbstractDungeon.player.gameHandSize;
//    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        AbstractDungeon.player.gameHandSize -= amount;
    }


    @Override
    public void atStartOfTurnPostDraw() {
        //AbstractDungeon.player.gameHandSize += amount;
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onRemove() {
        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0]+amount;
    }
}
