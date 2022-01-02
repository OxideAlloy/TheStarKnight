package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStarKnight.DefaultMod;
import theStarKnight.cards.OblivionShard_SK;
import theStarKnight.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

public class ShardEcho extends AbstractPower implements CloneablePowerInterface, InvisiblePower {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("ShardEcho");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    //private ArrayList<AbstractMonster> targets = new ArrayList();

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theStarKnightResources/images/powers/DrawLoss_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theStarKnightResources/images/powers/DrawLoss_32.png");

    public ShardEcho(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.loadRegion("beat");
        this.updateDescription();
    }

    public void atStartOfTurn() {
        //System.out.println("ShardEcho Called at start of turn.");

        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();

            if (c.cardID.equals(OblivionShard_SK.ID)) {
                AbstractMonster mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                c.calculateCardDamage(mon);
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mon, new DamageInfo(AbstractDungeon.player, c.damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }

    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ShardEcho(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
    }

    public ShardEcho makeCopy() {
        return new ShardEcho(this.owner, this.source, this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
