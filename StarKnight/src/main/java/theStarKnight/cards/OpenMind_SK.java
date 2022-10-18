package theStarKnight.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.MadnessPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class OpenMind_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(OpenMind_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("OpenMind.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;

    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 2;

    public OpenMind_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        BaseMod.MAX_HAND_SIZE=BaseMod.MAX_HAND_SIZE+magicNumber;
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new MadnessPower(p, 0), 0)
        );
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