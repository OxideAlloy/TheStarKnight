package theStarKnight.powers;

        import basemod.BaseMod;
        import basemod.interfaces.CloneablePowerInterface;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        import com.megacrit.cardcrawl.actions.common.GainBlockAction;
        import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
        import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
        import com.megacrit.cardcrawl.characters.AbstractPlayer;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.localization.PowerStrings;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import com.megacrit.cardcrawl.powers.AbstractPower;
        import com.megacrit.cardcrawl.powers.ConstrictedPower;
        import theStarKnight.DefaultMod;
        import theStarKnight.util.TextureLoader;

        import java.util.Iterator;

public class InstinctPower extends AbstractPower implements CloneablePowerInterface {
    //public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("InstinctPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/Instinct_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/Instinct_32.png");

    private int Madness = 1;

    public InstinctPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.Madness = 1;
       //his.source = source;
        type = PowerType.BUFF;
        isTurnBased = true;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    @Override
//    public void atStartOfTurn() {
//        this.addToBot(new LoseEnergyAction(this.amount));
//        this.flash();
//    }

//    @Override
//    public void onEnergyRecharge() {
//        this.flash();
//        this.addToBot(new LoseEnergyAction(this.amount));
//        //this.updateDescription();
//    }

    @Override
    public void atEndOfTurn(boolean playerTurn) {
        this.flash();
        this.Madness = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount*Madness));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new InstinctPower(owner, amount);
    }
}
