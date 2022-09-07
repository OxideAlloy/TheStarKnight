package theStarKnight.powers;

import basemod.BaseMod;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

public class MadnessPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("MadnessPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/Madness_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/Madness_32.png");

    public MadnessPower(AbstractCreature owner, int amount) {
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

//Currently applied at start of battle with DarkHelm_SKRelic

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.amount = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }

//    @Override
//    public void onAfterCardPlayed(AbstractCard usedCard) {
//        this.amount = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
//    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        this.amount = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.amount = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }

    @Override
    public void onDrawOrDiscard() {
        this.amount = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }

    //Set hand size back to 10 at end of battle
    @Override
    public void onVictory() { BaseMod.MAX_HAND_SIZE=10;}

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MadnessPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public MadnessPower makeCopy() {
        return new MadnessPower(AbstractDungeon.player, this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
