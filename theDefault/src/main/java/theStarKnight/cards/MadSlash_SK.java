package theStarKnight.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
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

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(MadSlash_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MadSlash.png");
    //TODO Need to update to "MadSlash_SK.png"

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;

    //private static final int AMOUNT = 99;

    public MadSlash_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseMagicNumber = magicNumber = AMOUNT;
        this.baseDamage = 5;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    //// START MADNESS CODE ////
    public void atTurnStart() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        if(upgraded){this.baseDamage=this.baseDamage*2;}
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        if(upgraded){this.baseDamage=this.baseDamage*2;}
    }
    public void applyPowers() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        super.applyPowers();
        this.initializeDescription();
        if(upgraded){this.baseDamage=this.baseDamage*2;}
    }
    //// END MADNESS CODE ////



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
