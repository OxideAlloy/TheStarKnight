package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.IchorPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class Bile_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Bile_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Bile.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;

    private static final int AMOUNT = 4;
    private static final int UPGRADED_AMOUNT = 2;

    private static final int DEBUFF = 2;
    private static final int UPGRADED_DEBUFF = 1;


    // /STAT DECLARATION/

    public Bile_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new IchorPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.defaultSecondMagicNumber, false), this.defaultSecondMagicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            initializeDescription();
        }
    }
}
