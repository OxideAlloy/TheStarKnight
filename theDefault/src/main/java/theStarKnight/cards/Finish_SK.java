package theStarKnight.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class Finish_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(Finish_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Finish.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 1;
    //private static final int UPGRADE_PLUS_DMG = 1;

    private static final int DISPLAY = 0;

    public Finish_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DISPLAY;
        this.baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //calculate half missing health (rounded down)
        //this.magicNumber = this.baseMagicNumber = ((m.maxHealth-m.currentHealth)/2);

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        //THIS WILL NEED TO BE AN ACTION TO WORK//
//        if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
//            AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);
//            if (this.target instanceof Deca) {
//                this.addToBot(new TalkAction(true, "@Diced!@", 1.5F, 1.5F));
//            }
//        }

        //System.out.println("monster currentHealth = "+m.currentHealth);
        //System.out.println("monster maxHealth = "+m.maxHealth);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        //this.magicNumber = this.baseMagicNumber = ((mo.maxHealth-mo.currentHealth)/2);
        this.baseDamage = ((mo.maxHealth-mo.currentHealth)/2);
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
