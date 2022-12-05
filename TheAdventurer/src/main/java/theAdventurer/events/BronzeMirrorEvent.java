package theAdventurer.events;

        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.events.AbstractEvent;
        import com.megacrit.cardcrawl.events.AbstractImageEvent;
        import com.megacrit.cardcrawl.helpers.ScreenShake;
        import com.megacrit.cardcrawl.localization.EventStrings;
        import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
        import theAdventurer.DefaultMod;

        import static theAdventurer.DefaultMod.makeEventPath;

public class BronzeMirrorEvent extends AbstractImageEvent {


    public static final String ID = DefaultMod.makeID("BronzeMirrorEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("BronzeMirror_E_SK.png");

    private int hpAmt = 8;
    private int damage = 16;
    private int screenNum = 0;

    public BronzeMirrorEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);

        // The first dialogue options available to us.
        imageEventText.setDialogOption(OPTIONS[0]); // [Reflect]
        imageEventText.setDialogOption(OPTIONS[1]); // [Remove your helm]
        imageEventText.setDialogOption(OPTIONS[2]); // [Turn away]

    }

    public void onEnterRoom() {
        CardCrawlGame.music.playTempBGM("SHRINE");
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
            logMetricObtainCard("Duplicator", "Copied", c);
            c.inBottleFlame = false;
            c.inBottleLightning = false;
            c.inBottleTornado = false;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0:
                        imageEventText.loadImage("theAdventurerResources/images/events/BronzeMirror_E_3_SK.png"); // Change the shown image
                        this.use();

                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);

                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();

                        screenNum = 1;
                        break; // Onto screen 1 we go.
                    case 1: // If you press button the second button

                        imageEventText.loadImage("theAdventurerResources/images/events/BronzeMirror_E_2_SK.png"); // Change the shown image

                        CardCrawlGame.sound.play("EVENT_VAMP_BITE");

//                        Object r;
//                        //if (AbstractDungeon.player.hasRelic("DarkHelm_SKRelic")) {
//                        r = new MawHelm_SKRelic();
//                        //AbstractDungeon.player.loseRelic("DarkHelm_SKRelic");
//                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, (AbstractRelic)r);


                        AbstractDungeon.player.maxHealth += this.hpAmt;
                        AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)null, this.damage));
                        if (AbstractDungeon.player.maxHealth < 1) {
                            AbstractDungeon.player.maxHealth = 1;
                        }

                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();

                        screenNum = 1;
                        break; // Onto screen 1 we go.
                    case 2:
                        //this.use();
                        imageEventText.loadImage("theAdventurerResources/images/events/BronzeMirror_E_3_SK.png"); // Change the shown image

                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
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

    public void use() {
        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[2], false, false, false, false);
    }

    public void logMetric(String result) {
        AbstractEvent.logMetric("Duplicator", result);
    }

//    public void update() { // We need the update() when we use grid screens (such as, in this case, the screen for selecting a card to remove)
//        super.update(); // Do everything the original update()
//        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) { // Once the grid screen isn't empty (we selected a card for removal)
//            AbstractCard c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0); // Get the card
//            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2))); // Create the card removal effect
//            AbstractDungeon.player.masterDeck.removeCard(c); // Remove it from the deck
//            AbstractDungeon.gridSelectScreen.selectedCards.clear(); // Or you can .remove(c) instead of clear,
//            // if you want to continue using the other selected cards for something
//        }
//
//    }


}
