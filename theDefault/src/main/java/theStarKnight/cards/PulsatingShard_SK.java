package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class PulsatingShard_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(PulsatingShard_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("PulsatingShard.png");
    public static final String IMG2 = makeCardPath("Pulsating_full.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = -2;

    private static final int AMOUNT = 1;
//    private static final int UPGRADED_AMOUNT = 2;

    // /STAT DECLARATION/

    //////////////////////////////////////////////////////////////
    //The power "ShardEcho" causes this cards effect to trigger.//
    //////////////////////////////////////////////////////////////

    public PulsatingShard_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            this.isInnate = true;
            this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}
