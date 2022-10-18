package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class DyingSpark_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("DyingSpark_SKRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DyingSpark_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DyingSpark_SK_OL.png"));

    public DyingSpark_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL );
    }

    //On shuffle increase gain 1 strength
    public void onShuffle() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public AbstractRelic makeCopy() {
        return new DyingSpark_SKRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
