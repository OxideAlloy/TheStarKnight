package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class MagneticField_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MagneticField_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MagneticField.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int AMOUNT = 2;
    //private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/

    public MagneticField_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //this.upgradeMagicNumber(UPGRADED_AMOUNT);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
