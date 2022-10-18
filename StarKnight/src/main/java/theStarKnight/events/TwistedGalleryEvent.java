package theStarKnight.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theStarKnight.DefaultMod;
import theStarKnight.cards.Nihilism_SK;

import static theStarKnight.DefaultMod.makeEventPath;

public class TwistedGalleryEvent extends AbstractImageEvent {


    public static final String ID = DefaultMod.makeID("TwistedGalleryEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("TwistedGallery_E_SK.png");

    private int screenNum = 0;

    private int healthgain = 10;
    private int stealAmt = 150;

    public TwistedGalleryEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);

        // The first dialogue options available to us.
        imageEventText.setDialogOption(OPTIONS[0]); // [Contemplate] Heal 10 HP.
        imageEventText.setDialogOption(OPTIONS[1], new Nihilism_SK()); // [Steal] Gain 100 gold.
        //imageEventText.setDialogOption(OPTIONS[2]); // Leave

    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0:
                        AbstractDungeon.player.heal(healthgain);

                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();

                        screenNum = 1;
                        break; // Onto screen 1 we go.
                    case 1: // If you press button the second button
                        imageEventText.loadImage("theStarKnightResources/images/events/TwistedGallery_2_E_SK.png"); // Change the shown image

                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.stealAmt));
                        AbstractDungeon.player.gainGold(this.stealAmt);

                        AbstractCard c = new Nihilism_SK().makeCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();

                        screenNum = 1;
                        break; // Onto screen 1 we go.
                }
                break;
            case 1: // Welcome to screenNum = 1;
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }

}
