package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class Comet_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Comet_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Comet.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;

    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 1;

    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/

    public Comet_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = AMOUNT;
        this.exhaust = true;
        this.selfRetain = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    public void onRetained() {
        this.upgradeBlock(this.magicNumber);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
