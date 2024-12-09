package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theStarKnight.DefaultMod;
import theStarKnight.cards.FallingStar_SK;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class MeteorShower_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("MeteorShower_SKRelic");
//TODO Update image
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MeteorShower_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MeteorShower_SK_OL.png"));

    public MeteorShower_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    //At the start of combat, gain a meteor.
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new FallingStar_SK(), 1, false));
    }

    public AbstractRelic makeCopy() {
        return new MeteorShower_SKRelic();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}