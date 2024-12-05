package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class MagneticField_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MagneticField_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MagneticField.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    //private static final int AMOUNT = 1;
    //private static final int UPGRADED_AMOUNT = 1;

    private static final int BLOCK = 0;

    // /STAT DECLARATION/

    public MagneticField_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseMagicNumber = magicNumber = AMOUNT;
        baseBlock = BLOCK;
        //this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        //this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
        //this.addToBot(new ApplyPowerAction(p, p, new TungstenPower(p, p, magicNumber), magicNumber));
        //this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
    }

    ///Eternal///
    public void triggerOnExhaust() {
        if(this.upgraded) {
            this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
        }
    }

    //// START PUlSAR DAMAGE UPDATE CODE ////
    public void atTurnStart() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseBlock = AbstractDungeon.player.exhaustPile.size();
    }
    public void applyPowers() {
        this.baseBlock = AbstractDungeon.player.exhaustPile.size();
        super.applyPowers();
        this.initializeDescription();
    }
    //// END PUlSAR DAMAGE UPDATE CODE ////

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //upgradeBaseCost(UPGRADED_COST);
            rawDescription = UPGRADE_DESCRIPTION;
            //this.isInnate = true;
            initializeDescription();
        }
    }
}
