package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.ConstrictedPower;
//import com.megacrit.cardcrawl.powers.StrengthPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.IchorPower;


import static theStarKnight.DefaultMod.makeCardPath;


public class Corrupt_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Corrupt_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Corrupt.png");
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int AMOUNT = 3;
    private static final int UPGRADED_AMOUNT = 1;
    //private static final int DEBUFF = 1;
    //private static final int UPGRADED_DEBUFF = 1;

    public Corrupt_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
       //defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //System.out.println("Magic Number = "+magicNumber );
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new IchorPower(m, p, magicNumber), magicNumber));
    }

//    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(
//              new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
//    }

//    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractDungeon.actionManager.addToBottom(
//                new ApplyPowerAction(m, p, new SlowPower(m, this.magicNumber - 1), this.magicNumber - 1));
//    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
