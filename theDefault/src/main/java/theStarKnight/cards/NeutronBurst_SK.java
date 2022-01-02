package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class NeutronBurst_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(NeutronBurst_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("NeutronBurst.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int AMOUNT = 3;

    // /STAT DECLARATION/

    public NeutronBurst_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        this.cardsToPreview = new StrangeMatter_SK();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        //AbstractCard card = new StrangeMatter_SK();
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 1, true, true, false));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
