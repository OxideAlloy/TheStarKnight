package theStarKnight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class Stomp_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Stomp_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Stomp.png");
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int AMOUNT = 1;

    public Stomp_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
//        this.addToBot(
//                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        System.out.println("Monster current health = "+m.currentHealth );

        this.addToBot(
                new DamageCallbackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, dmg ->
                {
                    //System.out.println("Monster current health = "+m.currentHealth );
                    if ((m.isDead || m.currentHealth <= 0) && !m.halfDead)
                    {
                        this.addToBot(new GainEnergyAction(this.magicNumber));
                        this.addToBot(new DrawCardAction(p, this.magicNumber));
                        this.addToBot(new TalkAction(true, "@AHahaha@", 2.0F, 2.0F));
                        //System.out.println("Monster current health = "+m.currentHealth );
                    }
                }));
    }


//        update(); {
//            System.out.println("Monster current health = "+m.currentHealth );
//            System.out.println("Player current health = "+p.currentHealth );
//            if ((m.isDying || m.currentHealth <= 0) && !m.halfDead) {
//                this.addToBot(new GainEnergyAction(this.magicNumber));
//                this.addToBot(new DrawCardAction(p, this.magicNumber));
//                this.addToBot(new TalkAction(true, "@#pHehehe@", 2.0F, 2.0F));
//            }
//        }

//        if ((m.isDying || m.currentHealth <= 0) && !m.halfDead) {
//            this.addToBot(new GainEnergyAction(this.magicNumber));
//            this.addToBot(new DrawCardAction(p, this.magicNumber));
//            this.addToBot(new TalkAction(true, "@#pHehehe@", 2.0F, 2.0F));
//        }



//    public void DamageCallbackAction(AbstractCreature m) {
//        System.out.println("Monster current health = "+m.currentHealth );
////        this.addToBot(new GainEnergyAction(this.magicNumber));
////        this.addToBot(new DrawCardAction(p, this.magicNumber));
////        this.addToBot(new TalkAction(true, "@#pHehehe@", 2.0F, 2.0F));
//    }

//    @Override
//    public void update() {
//        super.update();
//        System.out.println("Monster current health = "+m.currentHealth );
//        System.out.println("Player current health = "+p.currentHealth );
//    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
