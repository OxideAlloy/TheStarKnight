package theStarKnight.powers;

        import basemod.interfaces.CloneablePowerInterface;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.common.*;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.cards.status.VoidCard;
        import com.megacrit.cardcrawl.cards.tempCards.Insight;
        import com.megacrit.cardcrawl.cards.tempCards.Smite;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.localization.PowerStrings;
        import com.megacrit.cardcrawl.powers.AbstractPower;
        import com.megacrit.cardcrawl.powers.PoisonPower;
        import theStarKnight.DefaultMod;
        import theStarKnight.util.TextureLoader;

public class VoidBornPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("VoidBornPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static int exhaustDamage;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/VoidBorn_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/VoidBorn_32.png");

    public VoidBornPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;

        exhaustDamage = 8;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    @Override
//    public void onCardDraw(AbstractCard card) {
//        //System.out.println("Card name is equal to: "+card.name);
//        if (card.cardID == "Void") {
//            this.flash();
//            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount*10, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
//        }
//
//    }

    @Override
    public void onExhaust(AbstractCard card) {
        //System.out.println("Card name is equal to: "+card.name);
        if (card.type == AbstractCard.CardType.STATUS) {
            this.flash();
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount*exhaustDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }

    }


    @Override
    public void atEndOfTurn(boolean playerTurn) {
        this.addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), this.amount, true, true));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount*exhaustDamage + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new VoidBornPower(owner, source, amount);
    }
}
