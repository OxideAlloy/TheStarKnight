package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BlackBlood;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class MawHelm_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("MawHelm_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MawHelm_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MawHelm_SK_OL.png"));

    public MawHelm_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY );
    }

    //On victory increase max HP by two
    public void onVictory() {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        this.addToTop(new RelicAboveCreatureAction(p, this));
        p.increaseMaxHp(2, true);
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("DarkHelm_SKRelic");
    }

    public AbstractRelic makeCopy() {
        return new MawHelm_SKRelic();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
