package theAdventurer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theAdventurer.DefaultMod;
import theAdventurer.util.TextureLoader;

import static theAdventurer.DefaultMod.makeRelicOutlinePath;
import static theAdventurer.DefaultMod.makeRelicPath;

public class Telescope_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("Telescope_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Telescope_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Telescope_SK_OL.png"));

    public Telescope_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
    }

    //At the start of combat, scry 6.
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ScryAction(6));
    }

    public AbstractRelic makeCopy() {
        return new Telescope_SKRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}