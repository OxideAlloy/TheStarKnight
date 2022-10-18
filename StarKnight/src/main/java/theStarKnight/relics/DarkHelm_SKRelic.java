package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theStarKnight.DefaultMod;
import theStarKnight.powers.MadnessPower;
import theStarKnight.util.TextureLoader;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class DarkHelm_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("DarkHelm_SKRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DarkHelm_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DarkHelm_SK_OL.png"));

    public DarkHelm_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    //On victory increase max HP by one
    public void onVictory() {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        this.addToTop(new RelicAboveCreatureAction(p, this));
        p.increaseMaxHp(1, true);
    }

    public void atBattleStartPreDraw() {
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MadnessPower(AbstractDungeon.player, 1), 1));
    }

    public AbstractRelic makeCopy() {
        return new DarkHelm_SKRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
