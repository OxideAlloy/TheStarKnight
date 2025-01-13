package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class ThreeEyedStatue_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("ThreeEyedStatue_SKRelic");

    //TODO Update images
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ThreeEyedStatue_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ThreeEyedStatue_SK_OL.png"));

    public ThreeEyedStatue_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.CLINK);
    }

    public void atTurnStart() {
        if(!this.grayscale) {
            this.beginLongPulse();
            //this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BlurPower(AbstractDungeon.player, 1), 1));
        }
    }

    public void onPlayerEndTurn() {
        if (this.grayscale) {
            //turn start, if grayscale, set to color
            this.grayscale = false;
        } else {
            //if color, skip enemy turn, set to grayscale
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new SkipEnemiesTurnAction());
            this.grayscale = true;
            this.stopPulse();
        }
    }

    public void onVictory() {
        this.grayscale = true;
        AbstractDungeon.player.gameHandSize+=2;
    }

    public void atBattleStartPreDraw() {
        this.grayscale = false;
        AbstractDungeon.player.gameHandSize-=2;
    }

    public void onEquip() {
        this.grayscale = true;
        --AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }


    public AbstractRelic makeCopy() {
        return new ThreeEyedStatue_SKRelic();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}