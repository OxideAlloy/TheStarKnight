package theStarKnight.cards.attacks;

        import basemod.BaseMod;
        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.common.DamageAction;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.characters.AbstractPlayer;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import theStarKnight.DefaultMod;
        import theStarKnight.cards.defaultExamples.AbstractDynamicCard;
        import theStarKnight.characters.TheDefault;

        import static theStarKnight.DefaultMod.makeCardPath;

public class KnifeGame_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(KnifeGame_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("KnivesOut.png");
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int TIMES = 2;
    private static final int UPGRADED_TIMES = 3;

    public KnifeGame_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = TIMES;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        // Sets 1st magic number equal to Madness
        this.magicNumber = this.baseMagicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        //Deals Madness a number of times equal to TIMES (2nd magic number)
        for (int i = 0; i < defaultSecondMagicNumber; i++) {
            this.addToBot(
                    new DamageAction(m, new DamageInfo(p, this.magicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeDefaultSecondMagicNumber(UPGRADED_TIMES);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
