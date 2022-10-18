package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import static theStarKnight.DefaultMod.makeCardPath;

public class MeteorBlast_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(MeteorBlast_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("MeteorStrike.png");
    public static final String IMG2 = makeCardPath("Meteor_full.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 5;
    private static final int UPGRADED_COST = 4;

    private static final int DAMAGE = 35;
    private static final int IMPACT = 5;

    public MeteorBlast_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = IMPACT;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DamageAllEnemiesAction(p, this.defaultBaseSecondMagicNumber, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
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

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}
