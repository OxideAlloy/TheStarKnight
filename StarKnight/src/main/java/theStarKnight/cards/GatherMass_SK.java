package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.TempStrNextTurn;

import static theStarKnight.DefaultMod.makeCardPath;

public class GatherMass_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(GatherMass_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("GatherMass.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;

    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    private static final int STR_AMOUNT = 4;
    private static final int UPGRADED_STR_AMOUNT = 1;

    private static final int ENERGY_AMOUNT = 1;
    private static final int UPGRADED_ENERGY_AMOUNT = 1;

    public GatherMass_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = STR_AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = ENERGY_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        this.addToBot(new ApplyPowerAction(p, p, new TempStrNextTurn(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, this.defaultSecondMagicNumber), this.defaultSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADED_STR_AMOUNT);
            this.upgradeDefaultSecondMagicNumber(UPGRADED_ENERGY_AMOUNT);
            initializeDescription();
        }
    }
}
