package theStarKnight.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theStarKnight.DefaultMod;
import theStarKnight.cards.*;
import theStarKnight.util.TextureLoader;

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
    //private AbstractCard card = null;

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
        //this.card = card;
    }

    public void atStartOfTurn() {
        //HANDLES START OF TURN SHARD/MEMORY EFFECTS
        //AbstractDungeon.actionManager.addToBottom(new ShardEchoAction());
        int yshift = 0;
        int shiftAmt = 50;
        int xpos = Settings.WIDTH-150;
        int ypos = Settings.HEIGHT-200;

        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();
        while(var1.hasNext()) {
            AbstractCard card = (AbstractCard) var1.next();

            //OblivionShard deals damage to a random monster
            if (card.cardID.equals(OblivionShard_SK.ID)) {

                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(),xpos,ypos-yshift));

                AbstractMonster mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                card.calculateCardDamage(mon);
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mon, new DamageInfo(null, card.magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

                yshift+=shiftAmt;
            }

            //FetidShard deals damage to all monsters
            if (card.cardID.equals(FetidShard_SK.ID)) {

                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(),xpos,ypos-yshift));

                this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(card.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

                yshift+=shiftAmt;
            }

            //PulsatingShard gains player 1 energy.
            if (card.cardID.equals(PulsatingShard_SK.ID)) {
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(),xpos,ypos-yshift));
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FAST));

                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
//                if (!card.upgraded) {
//                    //this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));
//                    //this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IchorPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
//                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player, card.magicNumber, false), card.magicNumber));
//                }
                yshift+=shiftAmt;
            }

            //CacconShard grants 2 temporary HP (or 3 if upgraded).
            if (card.cardID.equals(CocoonShard_SK.ID)) {
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(),xpos,ypos-yshift));
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FAST));

                //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MalignantGrowthPower(AbstractDungeon.player, AbstractDungeon.player, card.magicNumber), card.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, card.magicNumber));
                yshift+=shiftAmt;
            }

            //ColdShard grants 5 block (or 8 block if upgraded).
            if (card.cardID.equals(ColdShard_SK.ID)) {
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(),xpos,ypos-yshift));
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FAST));

                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, card.magicNumber));
                yshift+=shiftAmt;
            }

//            if (AbstractDungeon.player.limbo.contains(c)) {
//                AbstractDungeon.player.limbo.removeCard(c);
//            }
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
