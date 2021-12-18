package theStarKnight.cards;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class CleavingBlow_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(CleavingBlow_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Cleave.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 4;

    private static final int AMOUNT = 2;

    public CleavingBlow_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(m.hasPower("Minion")) {
            this.addToBot(
                    new DamageCallbackAction(m, new DamageInfo(p, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, dmg ->
                    {
                        if ((m.isDead || m.currentHealth <= 0) && !m.halfDead) {
                            this.addToBot(new GainEnergyAction(this.magicNumber));
                            this.addToBot(new TalkAction(true, "@AHahaha@", 2.0F, 2.0F));
                        }
                    }));
        } else {
            this.addToBot(
                    new DamageCallbackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, dmg ->
                    {
                        if ((m.isDead || m.currentHealth <= 0) && !m.halfDead) {
                            this.addToBot(new GainEnergyAction(this.magicNumber));
                            this.addToBot(new TalkAction(true, "@AHahaha@", 2.0F, 2.0F));
                        }
                    }));
        }
    }

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
