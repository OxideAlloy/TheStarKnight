package theStarKnight.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.SkewerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import theStarKnight.DefaultMod;
import theStarKnight.actions.SolarFlareAction;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class SolarFlare_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(SolarFlare_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("SolarFlare.png");
    public static final String IMG2 = makeCardPath("Flare_full.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = -1;
    private static final int DAMAGE = 6;
    private static final int AMOUNT = 6;
    private static boolean ISUPGRADED = false;

    public SolarFlare_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseMagicNumber = magicNumber = AMOUNT;
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SolarFlareAction(p, m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));

        if (m != null && EnergyPanel.totalCount>3) {
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.GOLDENROD.cpy())));
            this.addToBot(new WaitAction(0.4F));
            this.addToBot(new VFXAction(new FireBurstParticleEffect(m.hb.cX, m.hb.cY)));
        }
//
//        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
//        EnergyPanel.totalCount=0;
//        if (ISUPGRADED && this.energyOnUse>1) {
//                this.addToBot(new GainEnergyAction((int) Math.floor(this.energyOnUse / 2)));
//        };
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            this.baseDamage = (EnergyPanel.totalCount*AMOUNT+2);
        } else {
            this.baseDamage = (EnergyPanel.totalCount*AMOUNT);
        }

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            ISUPGRADED = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}
