package theStarKnight.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.IchorPower;

import java.util.Iterator;

import static theStarKnight.DefaultMod.makeCardPath;

public class IchorSpray_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(IchorSpray_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("IchorSpray.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 2;

    // /STAT DECLARATION/

    public IchorSpray_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            this.flash();
//            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();
//
//            while(var3.hasNext()) {
//                AbstractMonster monster = (AbstractMonster)var3.next();
//                if (!monster.isDead && !monster.isDying) {
//                    AbstractDungeon.actionManager.addToBottom(
//                            new ApplyPowerAction(m, p, new IchorPower(m, p, this.magicNumber), this.magicNumber));
//                    AbstractDungeon.actionManager.addToBottom(
//                            new ApplyPowerAction(monster, p, new WeakPower(monster, 1, false), 1));
//                    AbstractDungeon.actionManager.addToBottom(
//                            new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber));
//
//                }
//            }
//        }
//
//    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster)var3.next();
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, 1, false), 1));
                    this.addToBot(new ApplyPowerAction(monster, p, new IchorPower(monster, p, this.magicNumber), this.magicNumber));
                }
            }
        }

    }



    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
