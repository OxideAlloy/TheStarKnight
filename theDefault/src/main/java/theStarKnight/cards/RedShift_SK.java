package theStarKnight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import theStarKnight.DefaultMod;
import theStarKnight.actions.ColorInfluenceAction;
import theStarKnight.characters.TheDefault;

import static java.awt.Color.red;
import static theStarKnight.DefaultMod.makeCardPath;

public class RedShift_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(RedShift_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("RedShift.png");

//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    //public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
//    //public static final String DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;

    private static final int CARD_CHOICE = 3;
    private static final int CARD_CHOICE_UPGRADE = 2;

    // /STAT DECLARATION/

    public RedShift_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = CARD_CHOICE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ColorInfluenceAction(this.magicNumber, true));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(CARD_CHOICE_UPGRADE);
            initializeDescription();
        }
    }
}
