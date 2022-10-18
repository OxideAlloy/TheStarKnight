package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.ElderFormPower;
import theStarKnight.powers.RuinPower;
import theStarKnight.powers.SingularityPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class Singularity_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Singularity_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Singularity.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;
    private static final int DEBUFF = 1;

    public Singularity_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SingularityPower(p, p, magicNumber), magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new RuinPower(p, p,defaultSecondMagicNumber), defaultSecondMagicNumber));
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