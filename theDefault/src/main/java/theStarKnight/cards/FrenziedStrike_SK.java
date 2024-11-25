package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.powers.StackingDrawReductionPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;


import static theStarKnight.DefaultMod.makeCardPath;

public class FrenziedStrike_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(FrenziedStrike_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("FrenziedStrike.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;
    //private static final int UPGRADED_COST = 0;

    private static final int AMOUNT = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 4;

    public FrenziedStrike_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
        this.tags.add(CardTags.STRIKE);

        //this.tags.add(CardTags.STRIKE);
//        this.cardsToPreview = new FrenziedStrike_SK();
//        //this.cardsToPreview.upgrade();
//        if (this.upgraded) {
//            this.cardsToPreview.upgrade();
//        }

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

        AbstractCard card = new FrenziedStrike_SK();
        if (this.upgraded) {
            card.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDrawPileAction(card, 1, true, true));

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            //upgradeBaseCost(UPGRADED_COST);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
