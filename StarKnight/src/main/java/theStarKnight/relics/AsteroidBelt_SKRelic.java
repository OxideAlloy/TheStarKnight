package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theStarKnight.DefaultMod;
import theStarKnight.cards.Comet_SK;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class AsteroidBelt_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("AsteroidBelt_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AsteroidBelt_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AsteroidBelt_SK_OL.png"));

    public AsteroidBelt_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    //Add a comet to your hand when you take damage
    public void wasHPLost(int damageAmount) {
        if (damageAmount > 0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new MakeTempCardInHandAction(new Comet_SK(), 1, false));
        }
    }

    public AbstractRelic makeCopy() {
        return new AsteroidBelt_SKRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}