package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.IchorPower;


import static theStarKnight.DefaultMod.makeCardPath;

public class Ignition_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Ignition_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Ignition.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int AMOUNT = 0;

    public Ignition_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (m.hasPower(IchorPower.POWER_ID)) {
            this.magicNumber = (m.getPower(IchorPower.POWER_ID).amount);
            this.addToBot(new LoseHPAction(m, p, this.magicNumber));
        } else {
            this.addToBot(
                    new TalkAction(true, "There is no @Ichor@ to ignite!", 2.0F, 2.0F));
            }
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
