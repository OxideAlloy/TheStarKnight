package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import theStarKnight.DefaultMod;
import theStarKnight.cards.DefaultRareAttack;
import theStarKnight.util.TextureLoader;

public class IchorPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("Ichor");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/placeholder_power32.png");

    public IchorPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.DEBUFF;
        isTurnBased = false;
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    @Override
//    public void atStartOfTurn() { // At the start of your turn
//        AbstractCard playCard = new DefaultRareAttack(); // Declare Card - the DefaultRareAttack card. We will name it 'playCard'.
//        AbstractMonster targetMonster = AbstractDungeon.getRandomMonster(); // Declare Target - Random Monster. We will name the monster 'targetMonster'.
//
//        playCard.freeToPlayOnce = true; //Self Explanatory
//
//        if (playCard.type != AbstractCard.CardType.POWER) {
//            playCard.purgeOnUse = true;
//        }
//
//        // Remove completely on use (Not Exhaust). A note - you don't need the '{}' in this if statement,
//        // as it's just 1 line directly under. You can remove them, if you want. In fact, you can even put it all on 1 line:
//        //  if (playCard.type != AbstractCard.CardType.POWER) playCard.purgeOnUse = true; - works identically
//
//        AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(playCard, targetMonster)); // Play the card on the target.
//    }

//    @Override
//    public void playApplyPowerSfx() {
//        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
//    }

//    @Override
//    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
//        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new SlowPower(this.owner, 1), 1));
//    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * (1.0F + (float)this.amount * 0.1F) : damage;

//        AbstractPower p = this.target.getPower("Poison");
//        if (p != null) {
//            --p.amount;
//            if (p.amount == 0) {
//                this.target.powers.remove(p);
//            } else {
//                p.updateDescription();
//            }
//        }
    }




    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new IchorPower(owner, source, amount);
    }
}
