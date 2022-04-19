package theStarKnight.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BurningBlood;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theStarKnight.DefaultMod;
import theStarKnight.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static theStarKnight.DefaultMod.makeRelicOutlinePath;
import static theStarKnight.DefaultMod.makeRelicPath;

public class TinyLighthouse_SKRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("TinyLighthouse_SKRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Lighthouse_SK.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Lighthouse_SK_OL.png"));

    public TinyLighthouse_SKRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

//    private boolean cardsReceived = true;
//    private boolean cardsSelected = true;
//
//    //On Equip and update grants a common relic and potion slot
//    public void onEquip() {
//        this.cardsReceived = false;
//        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//        //AbstractCard bellCurse = new CurseOfTheBell();
//        //UnlockTracker.markCardAsSeen(bellCurse.cardID);
//        //group.addToBottom(bellCurse.makeCopy());
//        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
//        //CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
//
//        this.cardsSelected = false;
//        if (AbstractDungeon.isScreenUp) {
//            AbstractDungeon.dynamicBanner.hide();
//            AbstractDungeon.overlayMenu.cancelButton.hide();
//            AbstractDungeon.previousScreen = AbstractDungeon.screen;
//        }
//
//        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
//        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//        Iterator var2 = AbstractDungeon.player.masterDeck.getPurgeableCards().group.iterator();
//
//        while(var2.hasNext()) {
//            AbstractCard card = (AbstractCard)var2.next();
//            tmp.addToTop(card);
//        }
//
//        if (tmp.group.isEmpty()) {
//            this.cardsSelected = true;
//        } else {
//            if (tmp.group.size() <= 2) {
//                this.deleteCards(tmp.group);
//            } else {
//                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 2, this.DESCRIPTIONS[1], false, false, false, true);
//            }
//
//        }
//    }
//
//    public void update() {
//
//        AbstractPlayer var10000 = AbstractDungeon.player;
//        var10000.potionSlots += 1;
//        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
//
//        super.update();
//
//        if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
//            AbstractDungeon.combatRewardScreen.open();
//            AbstractDungeon.combatRewardScreen.rewards.clear();
//            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.COMMON)));
////            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.UNCOMMON)));
////            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.RARE)));
//            AbstractDungeon.combatRewardScreen.positionRewards();
//            //AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[2]);
//            this.cardsReceived = true;
//            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
//
//            if (this.hb.hovered && InputHelper.justClickedLeft) {
//                this.flash();
//            }
//
//        }
//
//        if (!this.cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
//            this.deleteCards(AbstractDungeon.gridSelectScreen.selectedCards);
//        }
//
//
//    }
//
//    public void deleteCards(ArrayList<AbstractCard> group) {
//        this.cardsSelected = true;
//        float displayCount = 0.0F;
//        Iterator i = group.iterator();
//
//        while(i.hasNext()) {
//            AbstractCard card = (AbstractCard)i.next();
//            card.untip();
//            card.unhover();
//            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (float) Settings.WIDTH / 3.0F + displayCount, (float)Settings.HEIGHT / 2.0F));
//            displayCount += (float)Settings.WIDTH / 6.0F;
//            AbstractDungeon.player.masterDeck.removeCard(card);
//        }
//
//        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
//        AbstractDungeon.gridSelectScreen.selectedCards.clear();
//    }
//
//
//
//
//    //Increases number of cards in reward screen by 1
//    public int changeNumberOfCardsInReward(int numberOfCards) {
//        return numberOfCards + 1;
//    }



    public AbstractRelic makeCopy() {
        return new TinyLighthouse_SKRelic();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
