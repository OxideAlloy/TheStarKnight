package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.WeepingWoundPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class WeepingWound_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(WeepingWound_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("WeepingWound.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;

    public WeepingWound_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        this.cardsToPreview = new Wound();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new WeepingWoundPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 3, true, true, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}