package theStarKnight.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class ShieldOfFear_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(ShieldOfFear_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("DarkImpulse.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;

    public ShieldOfFear_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 5;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.magicNumber = this.baseMagicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, (this.block)));
    }

    ///Eternal///
    public void triggerOnExhaust() {
        if(this.upgraded) {
            this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
        }
    }

    //// START MADNESS CODE ////
    public void atTurnStart() {
        this.baseBlock = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseBlock = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
    }
    public void applyPowers() {
        this.baseBlock = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
        super.applyPowers();
        this.initializeDescription();
    }
    //// END MADNESS CODE ////

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}