package theStarKnight.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class DarkHeart_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(DarkHeart_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("DarkHeart.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;

    //private static final int TIMES = 0;
    //private static final int UPGRADED_TIMES = 4;

    public DarkHeart_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseMagicNumber = magicNumber = TIMES;
        this.baseDamage = 5;
        this.baseBlock = 5;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if(this.upgraded){
            this.addToBot(new SFXAction("ATTACK_HEAVY"));
            this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, (this.damage), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, (this.block)));
        //System.out.println("gameHandSize = "+BaseMod.MAX_HAND_SIZE);
        //System.out.println("gameHandSize = "+BaseMod.MAX_HAND_SIZE);
        //System.out.println("hand.size = "+AbstractDungeon.player.hand.size());
        //System.out.println("magic number = "+this.magicNumber);
        //System.out.println("second magic number = "+defaultSecondMagicNumber);
    }


    //// START MADNESS CODE ////
    public void atTurnStart() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.baseBlock = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
            this.baseDamage=this.baseDamage+magicNumber;
            this.baseBlock=this.baseBlock+magicNumber;
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.baseBlock = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
            this.baseDamage=this.baseDamage+magicNumber;
            this.baseBlock=this.baseBlock+magicNumber;
    }
    public void applyPowers() {
        this.baseDamage = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        this.baseBlock = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
            this.baseDamage=this.baseDamage+magicNumber;
            this.baseBlock=this.baseBlock+magicNumber;
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
            //this.upgradeMagicNumber(UPGRADED_TIMES);
            upgradeDamage(9);
            initializeDescription();
        }
    }
}
