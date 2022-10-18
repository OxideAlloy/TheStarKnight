package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class BurningSky_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(BurningSky_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("BurningSky2.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    public BurningSky_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new FallingStar_SK();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.cardsToPreview.upgrade();
        }
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 2, true, true, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
