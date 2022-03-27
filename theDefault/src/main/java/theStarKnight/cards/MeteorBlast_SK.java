package theStarKnight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import java.util.Iterator;

import static theStarKnight.DefaultMod.makeCardPath;

// MeteorBlast_SK's name is Meteor Strike in card strings (same name as Defect card, different effect). //
public class MeteorBlast_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MeteorBlast_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MeteorStrike.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 5;
    private static final int UPGRADED_COST = 4;

    private static final int DAMAGE = 5;
    private static final int IMPACT = 35;

    public MeteorBlast_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        //baseMagicNumber = magicNumber = IMPACT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = IMPACT;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.defaultSecondMagicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(upgraded) {
            this.addToBot(new DamageAllEnemiesAction(p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        }
    }


    //// CODE to update card displayed damage calculations ////
    @Override
    public void applyPowers() {
        int aoeBase = this.baseDamage; //to reset after

        this.baseDamage = this.defaultBaseSecondMagicNumber; //Calculate damage for single target
        this.isMultiDamage = false;
        super.applyPowers();
        this.defaultSecondMagicNumber = this.damage; //store result in magic number variable
        this.isDefaultSecondMagicNumberModified = this.isDamageModified;

        this.baseDamage = aoeBase;
        this.isMultiDamage = true;
        super.applyPowers(); //calculate normal damage
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int aoeBase = this.baseDamage; //to reset after

        this.baseDamage = this.defaultBaseSecondMagicNumber; //Calculate damage for single target
        this.isMultiDamage = false;
        super.calculateCardDamage(m);
        this.defaultSecondMagicNumber = this.damage; //store result in magic number variable
        this.isDefaultSecondMagicNumberModified = this.isDamageModified;

        this.baseDamage = aoeBase;
        this.isMultiDamage = true;
        super.calculateCardDamage(m); //calculate normal damage
    }



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
