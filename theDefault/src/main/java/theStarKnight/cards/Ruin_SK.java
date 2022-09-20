package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.CommonPower;
import theStarKnight.powers.RuinPower;
import theStarKnight.powers.WeepingWoundPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class Ruin_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Ruin_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Ruin.png");

//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;
    //private static final int UPGRADED_COST = 1;

    private static final int AMOUNT = 1;

    private static final int DEBUFF = 4;
    private static final int UPGRADED_DEBUFF = -2;

    public Ruin_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new RuinPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -defaultSecondMagicNumber), -defaultSecondMagicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //this.upgradeMagicNumber(UPGRADED_AMOUNT);
            this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            //upgradeBaseCost(UPGRADED_COST);
            //rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}