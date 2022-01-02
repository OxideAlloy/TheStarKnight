package theStarKnight.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class DarkHeart_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(DarkHeart_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("DarkHeart.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int TIMES = 1;
    private static final int UPGRADED_TIMES = 1;

    public DarkHeart_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = TIMES;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.magicNumber = this.baseMagicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());

        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, (this.magicNumber*defaultSecondMagicNumber)));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, (this.magicNumber*defaultSecondMagicNumber), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        //System.out.println("gameHandSize = "+BaseMod.MAX_HAND_SIZE);
        //System.out.println("hand.size = "+AbstractDungeon.player.hand.size());
        //System.out.println("magic number = "+this.magicNumber);
        //System.out.println("second magic number = "+defaultSecondMagicNumber);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeDefaultSecondMagicNumber(UPGRADED_TIMES);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
