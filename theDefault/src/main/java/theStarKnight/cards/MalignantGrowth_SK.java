package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.MalignantGrowthPower;
import theStarKnight.powers.SporePower;

import static theStarKnight.DefaultMod.makeCardPath;

public class MalignantGrowth_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MalignantGrowth_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MalignantGrowth.png");

//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;

    private static final int AMOUNT = 3;
    private static final int UPGRADED_AMOUNT = 1;

    public MalignantGrowth_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConstrictedPower(p, p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new MalignantGrowthPower(p, p, this.magicNumber), this.magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}