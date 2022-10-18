package theStarKnight.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;

import static theStarKnight.DefaultMod.makeCardPath;

//THIS CARD IS NOT ADDED TO THE CARD POOL
@AutoAdd.Ignore

public class StrangeMatter_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(StrangeMatter_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("StrangeMatter.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;
    private static final int AMOUNT = 5;

    public StrangeMatter_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToBot(new ExhaustAction(1, true, false, false));
    }

    public void triggerOnExhaust() {
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
    }
}
