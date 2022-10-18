package theStarKnight.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

//THIS CARD IS NOT ADDED TO THE CARD POOL
@AutoAdd.Ignore

public class Pulsar_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Pulsar_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Pulsar.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;
    private static final int DAMAGE = 99;

    public Pulsar_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    //// START PUlSAR DAMAGE UPDATE CODE ////
    public void atTurnStart() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
    }
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size();
        super.applyPowers();
        this.initializeDescription();
    }
    //// END PUlSAR DAMAGE UPDATE CODE ////

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
