package theAdventurer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theAdventurer.DefaultMod;
import theAdventurer.util.TextureLoader;

import static theAdventurer.DefaultMod.makeRelicOutlinePath;
import static theAdventurer.DefaultMod.makeRelicPath;

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

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
