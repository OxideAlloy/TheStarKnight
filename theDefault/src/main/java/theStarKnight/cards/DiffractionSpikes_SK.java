package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.RipostePower;

import static theStarKnight.DefaultMod.makeCardPath;

public class DiffractionSpikes_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(DiffractionSpikes_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Diffraction.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    private static final int AMOUNT = 5;
    private static final int UPGRADED_AMOUNT = 3;

    // /STAT DECLARATION/

    public DiffractionSpikes_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RipostePower(this.magicNumber), this.magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeBaseCost(UPGRADED_COST);
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
