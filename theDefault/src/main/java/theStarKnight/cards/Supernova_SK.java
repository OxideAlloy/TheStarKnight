package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import java.util.Iterator;

import static theStarKnight.DefaultMod.makeCardPath;

// Currently non-functional

public class Supernova_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Supernova_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Supernova.png");
    //TODO Need to update to "Supernova_SK.png"

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    //private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 8;

//    private static final int AMOUNT = 2;
//    private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/

    public Supernova_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        //baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        //this.addToBot(new ExhaustAction(this.magicNumber, false, false, false));
        //new EscapePlanAction(this.block)
        //this.addToBot(new DrawCardAction(1, new EscapePlanAction(this.block)));
    }

    //public DrawCardAction(AbstractCreature source, int amount, boolean endTurnDraw) {}
    //public ExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {}

//    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new DrawCardAction(1, new EscapePlanAction(this.block)));
//    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADED_COST);
            //this.upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
