package theStarKnight.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class MadSlash_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MadSlash_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MadSlash.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    //public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;

    private static final int TIMES = 5;

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 1;

    //private static final int DEBUFF = -3;
    //private static final int UPGRADED_DEBUFF = 2;

    public MadSlash_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = TIMES;
        this.baseDamage = DAMAGE;
        //defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.magicNumber>0) {
            for(int i = 0; i < this.magicNumber; ++i) {
                this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }else {
            this.addToBot(new TalkAction(true, "@I'm not mad enough!@", 1.5F, 1.5F));
        }
    }

    //// EPHEMERAL ////
    public void triggerOnCardPlayed(AbstractCard c) {
        this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    //// START MADNESS CODE ////
    public void atTurnStart() {
        baseMagicNumber = this.magicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        //this.initializeDescription();
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        baseMagicNumber = this.magicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        //this.initializeDescription();
    }
    public void applyPowers() {
        baseMagicNumber = this.magicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        super.applyPowers();
        this.initializeDescription();
    }
    //// END MADNESS CODE ////



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //rawDescription = UPGRADE_DESCRIPTION;
            //this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
