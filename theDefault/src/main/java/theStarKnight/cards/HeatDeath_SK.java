package theStarKnight.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class HeatDeath_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(HeatDeath_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("HeatDeath.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    //private static final int UPGRADED_COST = 0;
    private static final int AMOUNT = 1;
    private static final int DAMAGE = 0;

    public HeatDeath_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, (this.damage), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ExhaustAction(1, false, false, false));
    }

   ///Eternal///
    public void triggerOnExhaust() {
        if(this.upgraded) {
            this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
        }
    }


    //// START PUlSAR DAMAGE UPDATE CODE ////
    public void atTurnStart() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
    }
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
        super.applyPowers();
        this.initializeDescription();
    }
    //// END PUlSAR DAMAGE UPDATE CODE ////

    //// START MADNESS CODE ////
//    public void atTurnStart() {
//        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
//        this.baseDamage=this.baseDamage*this.magicNumber;
//    }
//    public void triggerOnOtherCardPlayed(AbstractCard c) {
//        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
//        this.baseDamage=this.baseDamage*this.magicNumber;
//    }
//    public void applyPowers() {
//        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
//        this.baseDamage=this.baseDamage*this.magicNumber;
//        super.applyPowers();
//        this.initializeDescription();
//    }
    //// END MADNESS CODE ////



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADED_COST);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
