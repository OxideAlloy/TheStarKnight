package theStarKnight.cards;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class ConsumingDarkness_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(ConsumingDarkness_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("ConsumingDarkness.png");
    public static final String IMG2 = makeCardPath("Consuming_full.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 3;
    //private static final int UPGRADED_COST = 2;

    private static final int ENERGY = 2;

    private static final int AMOUNT = 4;
    private static final int UPGRADED_AMOUNT = 1;

    public ConsumingDarkness_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 20;
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = ENERGY;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
            this.addToBot(
                    new DamageCallbackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, dmg ->
                    {
                        if ((m.isDead || m.currentHealth <= 0) && !m.halfDead) {
                            this.addToBot(new GainEnergyAction(this.defaultSecondMagicNumber));
                            this.addToBot(new TalkAction(true, "@AHahaha@", 2.0F, 2.0F));
                        }
                    }));
    }

    //// START MADNESS CODE ////
    public void atTurnStart() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.baseDamage=this.baseDamage*this.magicNumber;
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.baseDamage=this.baseDamage*this.magicNumber;
    }
    public void applyPowers() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.baseDamage=this.baseDamage*this.magicNumber;
        super.applyPowers();
        this.initializeDescription();
    }
    //// END MADNESS CODE ////



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            //upgradeBaseCost(UPGRADED_COST);
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
//            //Madness update
//            this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
//            this.baseDamage=this.baseDamage*AMOUNT;
            this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}
