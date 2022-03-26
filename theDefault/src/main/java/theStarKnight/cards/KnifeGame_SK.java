package theStarKnight.cards;

        import basemod.BaseMod;
        import com.badlogic.gdx.math.MathUtils;
        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.common.DamageAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.characters.AbstractPlayer;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
        import theStarKnight.DefaultMod;
        import theStarKnight.characters.TheDefault;

        import static theStarKnight.DefaultMod.makeCardPath;
        import static theStarKnight.cards.DarkHeart_SK.UPGRADE_DESCRIPTION;

public class KnifeGame_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(KnifeGame_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("KnivesOut.png");
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int TIMES = 2;
    private static final int UPGRADED_TIMES = 1;


    public KnifeGame_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = TIMES;
        this.baseDamage = 5;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(
                    new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    //// START MADNESS CODE ////
    public void atTurnStart() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) { this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size()); }
    public void applyPowers() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        super.applyPowers();
        this.initializeDescription();
    }
    //// END MADNESS CODE ////



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_TIMES);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
