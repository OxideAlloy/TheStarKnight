package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import java.util.Iterator;

import static theStarKnight.DefaultMod.makeCardPath;

public class Supernova_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Supernova_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Supernova.png");
    public static final String IMG2 = makeCardPath("Nova_full.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;
    //private static final int UPGRADED_COST = 4;

    private static final int AMOUNT = 5;
    private static final int UPGRADED_AMOUNT = 3;


    // /STAT DECLARATION/

    public Supernova_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        //this.isEthereal = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
    }

//    public void triggerOnExhaust() {
//        this.updateCost(-1);
//        this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
//    }

//    @Override
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        return false;
//    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADED_COST);
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}
