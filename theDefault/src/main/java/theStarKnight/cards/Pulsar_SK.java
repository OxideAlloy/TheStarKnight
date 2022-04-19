package theStarKnight.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class Pulsar_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(Pulsar_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Pulsar.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;
    //private static final int UPGRADED_COST = 0;

    private static final int DAMAGE = 99;
    //private static final int DAMAGE = AbstractDungeon.player.exhaustPile.group.size();

    public Pulsar_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //damage = p.exhaustPile.size();
        //this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));

        if (!this.upgraded) {
            // if not upgraded - exhaust a RANDOM card.
            this.addToBot(new ExhaustAction(1, true, false, false));
        } else {
            // if not upgraded - exhaust a CHOSEN card.
            this.addToBot(new ExhaustAction(1, false, false, false));
        }
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


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
