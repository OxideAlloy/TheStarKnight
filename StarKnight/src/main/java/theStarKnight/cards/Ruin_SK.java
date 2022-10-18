package theStarKnight.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.ElderFormPower;
import theStarKnight.powers.RuinPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class Ruin_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Ruin_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Ruin.png");
    public static final String IMG2 = makeCardPath("Ruin_full.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    private static final int AMOUNT = 1;

    public Ruin_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        this.tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ElderFormPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new RuinPower(p, p,1), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}