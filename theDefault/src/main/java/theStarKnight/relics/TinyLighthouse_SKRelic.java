package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BurningBlood;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class TinyLighthouse_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("TinyLighthouse_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Lighthouse_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Lighthouse_SK_OL.png"));

    public TinyLighthouse_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }
        //First turn, gain 2 HP (BloodVial)
        if (this.counter == 1) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 2, 0.0F));
        }
        //Second turn, gain 1 Energy (Lantern)
        if (this.counter == 2) {
            this.flash();
            this.addToTop(new GainEnergyAction(1));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
        //Third turn, gain 5 Vigor (Akabeko)
        if (this.counter == 3) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 8), 8));
        }
        //Third turn, gain 10 Block (CaptainsWheel)
        if (this.counter == 4) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 10));
            this.counter = -1;
            this.grayscale = true;
        }

    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new TinyLighthouse_SKRelic();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
