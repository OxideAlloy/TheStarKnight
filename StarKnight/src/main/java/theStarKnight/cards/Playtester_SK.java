package theStarKnight.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;

import static theStarKnight.DefaultMod.makeCardPath;

//THIS CARD IS NOT ADDED TO THE CARD POOL
@AutoAdd.Ignore

public class Playtester_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Playtester_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    public Playtester_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApotheosisAction());
        this.addToBot(new GainEnergyAction(5));
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
