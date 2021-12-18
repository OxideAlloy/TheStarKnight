package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.powers.StackingDrawReductionPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;


import static theStarKnight.DefaultMod.makeCardPath;

public class FrenziedStrike_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(FrenziedStrike_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("FrenziedStrike.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int AMOUNT = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 10;

    public FrenziedStrike_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractDungeon.actionManager.addToBottom(
//                new TalkAction(true, "@DARKNESS@", 2.0F, 2.0F));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        AbstractDungeon.actionManager.addToBottom(
//                new ApplyPowerAction(p, p, new DrawReductionPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StackingDrawReductionPower(p, p, this.magicNumber), this.magicNumber));
        //System.out.println("StackingDrawReduction called via FrenziedStrike, magic number = "+this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDrawPileAction(new FrenziedStrike_SK(), 1, true, true));



    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }

}
