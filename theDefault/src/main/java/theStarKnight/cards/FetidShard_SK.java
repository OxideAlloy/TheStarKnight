package theStarKnight.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class FetidShard_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(FetidShard_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("FetidShard.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;

//    private static final int DAMAGE = 3;
//    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int AMOUNT = 3;
    private static final int UPGRADED_AMOUNT = 2;

    public FetidShard_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.

    //////////////////////////////////////////////////////////////
    //The power "ShardEcho" causes this cards effect to trigger.//
    //////////////////////////////////////////////////////////////
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

//    @Override
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        return false;
//    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
