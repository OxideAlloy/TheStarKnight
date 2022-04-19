package theStarKnight.powers;

        import basemod.interfaces.CloneablePowerInterface;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.animations.VFXAction;
        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
        import com.megacrit.cardcrawl.actions.common.LoseHPAction;
        import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
        import com.megacrit.cardcrawl.actions.utility.SFXAction;
        import com.megacrit.cardcrawl.actions.utility.UseCardAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.localization.PowerStrings;
        import com.megacrit.cardcrawl.powers.AbstractPower;
        import com.megacrit.cardcrawl.powers.PoisonPower;
        import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
        import theStarKnight.DefaultMod;
        import theStarKnight.util.TextureLoader;

public class AlignmentPower extends AbstractPower implements CloneablePowerInterface {
    //public AbstractCreature source;


    public static final String POWER_ID = DefaultMod.makeID("AlignmentPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/Alignment_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/Alignment_32.png");

    public AlignmentPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
//        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.cost > 1) {
            card.setCostForTurn(card.costForTurn - this.amount);
            //this may cause status/curse cards to become playable?
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AlignmentPower(owner, amount);
    }
}
