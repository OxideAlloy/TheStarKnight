package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class DarkTract_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(DarkTract_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("DarkTract.png");

//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;

    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;
    private static final int AMOUNT2 = 2;


    // /STAT DECLARATION/

    public DarkTract_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = AMOUNT2;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new DiscardAction(p, p, defaultSecondMagicNumber, false));
    }

//// NOTE: Must add the card to AddToDeckPatch for this to work ////

    public void onAddedToMasterDeck() {
        //System.out.println("***onAddedToMasterDeck called***");
        AbstractDungeon.player.increaseMaxHp(2, true);
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
