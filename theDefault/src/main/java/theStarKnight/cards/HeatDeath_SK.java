package theStarKnight.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class HeatDeath_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(HeatDeath_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("HeatDeath.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int AMOUNT = 99;

    public HeatDeath_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.magicNumber = this.baseMagicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, (this.magicNumber), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
   }

   // NEED TO FIGURE OUT HOW TO UPDATE THE MAGIC NUMBER WHEN PLAYER HAND SIZE CHANGES --- look into hooks//

//    @Override
//    public void update() {
//        this.magicNumber = this.baseMagicNumber = (BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size());
//    }



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
