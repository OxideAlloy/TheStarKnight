package theStarKnight.powers;

        import basemod.interfaces.CloneablePowerInterface;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        import com.megacrit.cardcrawl.actions.common.GainBlockAction;
        import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
        import com.megacrit.cardcrawl.actions.utility.UseCardAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.localization.PowerStrings;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import com.megacrit.cardcrawl.powers.AbstractPower;
        import com.megacrit.cardcrawl.powers.PoisonPower;
        import theStarKnight.DefaultMod;
        import theStarKnight.util.TextureLoader;

        import java.util.Iterator;

public class IchorFloodPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("IchorFloodPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/IchorFlood_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/IchorFlood_32.png");

    public IchorFloodPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        //this.source = source;
        type = PowerType.BUFF;
        isTurnBased = true;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    public void atStartOfTurnPostDraw() {
//        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            this.flash();
//            Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
//
//            while(var1.hasNext()) {
//                AbstractMonster m = (AbstractMonster)var1.next();
//                if (!m.isDead && !m.isDying) {
//                    this.addToBot(new ApplyPowerAction(m, this.owner, new IchorPower(m, this.owner, this.amount), this.amount));
//                }
//            }
//        }
//
//    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && card.type == AbstractCard.CardType.SKILL) {
            this.flash();
            Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var1.hasNext()) {
                AbstractMonster m = (AbstractMonster)var1.next();
                if (!m.isDead && !m.isDying) {
                    this.addToBot(new ApplyPowerAction(m, this.owner, new IchorPower(m, this.owner, this.amount), this.amount));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new IchorFloodPower(owner, amount);
    }
}
