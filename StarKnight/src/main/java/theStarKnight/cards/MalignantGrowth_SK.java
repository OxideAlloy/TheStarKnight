package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.orbs.MalignantOrb;

import static theStarKnight.DefaultMod.makeCardPath;

public class MalignantGrowth_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MalignantGrowth_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MalignantGrowth.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;
    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;

    public MalignantGrowth_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMaxOrbAction(this.magicNumber));
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new ChannelAction(new MalignantOrb()));
        }
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