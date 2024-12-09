package theStarKnight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class CocoonShard_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(CocoonShard_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("CacconShard.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;

    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/

    //////////////////////////////////////////////////////////////
    //The power "ShardEcho" causes this cards effect to trigger.//
    //////////////////////////////////////////////////////////////

    public CocoonShard_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new HealAction(p, p, 2));
    }

//    @Override
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        return false;
//    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
