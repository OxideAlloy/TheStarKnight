package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class Hypnos_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("Hypnos_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Hypnos_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Hypnos_SK_OL.png"));

    public Hypnos_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.SOLID);
    }

    //When you enter a rest sites, upgrade a random card.
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof RestRoom) {

            ArrayList<AbstractCard> upgradableCards = new ArrayList();
            Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();
            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
                if (c.canUpgrade()) {
                    upgradableCards.add(c);
                }
            }
            Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
            if (!upgradableCards.isEmpty()) {
                this.flash();
                ((AbstractCard)upgradableCards.get(0)).upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard)upgradableCards.get(0));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards.get(0)).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new Hypnos_SKRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
