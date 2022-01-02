package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class StrangeMatter_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(StrangeMatter_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("StrangeMatter.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;

    private static final int AMOUNT = 2;

    // /STAT DECLARATION/

    public StrangeMatter_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        //this.cardsToPreview = new StrangeMatter_SK();
    }

    // Actions the card should do.
    @Override
    public void triggerWhenDrawn() {
        this.addToBot(new ExhaustAction(1, true, false, false));
        AbstractCard card = new StrangeMatter_SK();
        this.addToBot(new MakeTempCardInHandAction(card, 1));
    }

    public void triggerOnExhaust() {
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
    }
}
