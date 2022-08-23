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
import org.apache.commons.codec.binary.StringUtils;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class MawHelm_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("MawHelm_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MawHelm2_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DarkHelm_SK_OL.png"));

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

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(DarkHelm_SKRelic.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (StringUtils.equals(AbstractDungeon.player.relics.get(i).relicId, DarkHelm_SKRelic.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(DarkHelm_SKRelic.ID);
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
