package theStarKnight.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theStarKnight.DefaultMod;
import theStarKnight.cards.*;
import theStarKnight.powers.ShardEcho;
import theStarKnight.relics.*;

import java.util.ArrayList;
import java.util.List;

import static theStarKnight.DefaultMod.*;
import static theStarKnight.characters.TheDefault.Enums.COLOUR_SK;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in DefaultMod-character-Strings.json in the resources

public class TheDefault extends CustomPlayer {

//    public static final String DISCIPLE_SKELETON_ATLAS = "chrono_images/char/Disciple.atlas";
//    public static final String DISCIPLE_SKELETON_JSON = "chrono_images/char/Disciple.json";

    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());
    public static final float[] layerSpeeds;

    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("images/scenes/greenBg.jpg");
    }

    public List<CutscenePanel> getCutscenePanels() {
        //noinspection rawtypes
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel("theStarKnightResources/images/scenes/endingPanel1.png"));
        panels.add(new CutscenePanel("theStarKnightResources/images/scenes/endingPanel2.png"));
        panels.add(new CutscenePanel("theStarKnightResources/images/scenes/endingPanel3.png"));
        return panels;
    }




    // =============== CHARACTER ENUMERATORS =================
    // These are enums for your Characters color (both general color and for the card library) as well as
    // an enum for the name of the player class - IRONCLAD, THE_SILENT, DEFECT, YOUR_CLASS ...
    // These are all necessary for creating a character. If you want to find out where and how exactly they are used
    // in the basegame (for fun and education) Ctrl+click on the PlayerClass, CardColor and/or LibraryType below and go down the
    // Ctrl+click rabbit hole

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_DEFAULT;
        @SpireEnum(name = "COLOUR_OUT_OF_SPACE_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor COLOUR_SK;
        @SpireEnum(name = "COLOUR_OUT_OF_SPACE_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("DefaultCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "theStarKnightResources/images/char/defaultCharacter/orb/layer1.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer2.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer3.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer4.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer5.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer6.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer1d.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer2d.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer3d.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer4d.png",
            "theStarKnightResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public TheDefault(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "theStarKnightResources/images/char/defaultCharacter/orb/vfx.png", layerSpeeds,
                new SpineAnimation(
                        "theStarKnightResources/images/char/defaultCharacter/skeleton.atlas","theStarKnightResources/images/char/defaultCharacter/skeleton.json",1f));


        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_DEFAULT_SHOULDER_2, // campfire pose
                THE_DEFAULT_SHOULDER_1, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================  

        //NOTE smaller scale = larger character
        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                0.9f);
        //TODO Update "newAnimation" if the animation is redone
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    @Override
    public void renderPlayerImage(SpriteBatch sb)
    {
        sr.setPremultipliedAlpha(false);
        super.renderPlayerImage(sb);
        sr.setPremultipliedAlpha(true);
    }


    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        for (int i = 0; i < 4; i++) {
            retVal.add(Strike_SK.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend_SK.ID);
        }
        retVal.add(DarkHeart_SK.ID);
        retVal.add(Corrupt_SK.ID);

        //retVal.add(RedShift_SK.ID);
        //retVal.add(FetidShard_SK.ID);
        //retVal.add(Playtester_SK.ID);
        //retVal.add(Stomp_SK.ID);
        //retVal.add(FrenziedStrike_SK.ID);
        //retVal.add(DeathGrip_SK.ID);
        //retVal.add(DiffractionSpikes_SK.ID);
        //retVal.add(Roar_SK.ID);
        //retVal.add(SolarFlare_SK.ID);
        //retVal.add(KnifeGame_SK.ID);
        //retVal.add(Samsara_SK.ID);
        //retVal.add(Finish_SK.ID);
        //retVal.add(CleavingBlow_SK.ID);
        //retVal.add(VoidStrike_SK.ID);
        //retVal.add(OrbitalStrike_SK.ID);
        //retVal.add(MeteorBlast_SK.ID);
        //retVal.add(HeavyMetal_SK.ID);
        //retVal.add(HammerTime_SK.ID);
        //retVal.add(Helios_SK.ID);
        //retVal.add(Hemorrhage_SK.ID);
        //retVal.add(WoodenSword_SK.ID);
        //retVal.add(OblivionShard_SK.ID);
        //retVal.add(Gnaw_SK.ID);
        //retVal.add(GhostArmor_SK.ID);
        //retVal.add(NeutronBurst_SK.ID);
        //retVal.add(StrangeMatter_SK.ID);
        //retVal.add(IchorShield_SK.ID);
        //retVal.add(BurningSky_SK.ID);
        //retVal.add(GatherMass_SK.ID);
        //retVal.add(Bile_SK.ID);
        //retVal.add(DarkTract_SK.ID);
        //retVal.add(LeadShield_SK.ID);
        //retVal.add(PulsatingShard_SK.ID);
        //retVal.add(CacconShard_SK.ID);
        //retVal.add(ColdShard_SK.ID);
        //retVal.add(Impurity_SK.ID);
        //retVal.add(Glare_SK.ID);
        //retVal.add(EventHorizon_SK.ID);
        //retVal.add(HeatDeath_SK.ID);
        //retVal.add(Ignition_SK.ID);
        //retVal.add(DarkImpulse_SK.ID);
        //retVal.add(MadSlash_SK.ID);
        //retVal.add(RecklessStrike_SK.ID);
        //retVal.add(Vengance_SK.ID);
        //retVal.add(TowerShield_SK.ID);
        //retVal.add(TidalLock_SK.ID);
        //retVal.add(Comet_SK.ID);
        //retVal.add(Supernova_SK.ID);
        //retVal.add(IchorSpray_SK.ID);
        //retVal.add(MagneticField_SK.ID);
        //retVal.add(Radiate_SK.ID);
        //retVal.add(Terror_SK.ID);
        //retVal.add(Riposte_SK.ID);
        //retVal.add(Supermassive_SK.ID);
        //retVal.add(UnstableBlock_SK.ID);
        //retVal.add(Radiant_SK.ID);
        //retVal.add(BleedingEdge_SK.ID);
        //retVal.add(Atlas_SK.ID);
        //retVal.add(AccretionDisk_SK.ID);
        //retVal.add(Pulsar_SK.ID);
        //retVal.add(IchorFlood_SK.ID);
        //retVal.add(Singularity_SK.ID);
        //retVal.add(Instinct_SK.ID);
        //retVal.add(OpenMind_SK.ID);
        //retVal.add(Adaptive_SK.ID);
        //retVal.add(Alignment_SK.ID);
        //retVal.add(MalignantGrowth_SK.ID);
        //retVal.add(VoidBorn_SK.ID);
        //retVal.add(VoidBorn_SK.ID);
        //retVal.add(StarForge_SK.ID);
        //retVal.add(WeepingWound_SK.ID);
        //retVal.add(Ruin_SK.ID);





        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        //TODO Add some Relics
        retVal.add(DarkHelm_SKRelic.ID);
        //retVal.add(PlaceholderRelic.ID);
        //retVal.add(PlaceholderRelic2.ID);
        //retVal.add(DefaultClickableRelic.ID);

        // Mark relics as seen - makes it visible in the compendium immediately
        // If you don't have this it won't be visible in the compendium until you see them in game
        UnlockTracker.markRelicAsSeen(DarkHelm_SKRelic.ID);
        //UnlockTracker.markRelicAsSeen(PlaceholderRelic.ID);
        //UnlockTracker.markRelicAsSeen(PlaceholderRelic2.ID);
        //UnlockTracker.markRelicAsSeen(DefaultClickableRelic.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOUR_SK;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return theStarKnight.DefaultMod.COLOUR_OUT_OF_SPACE;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new TowerShield_SK();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new TheDefault(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return theStarKnight.DefaultMod.COLOUR_OUT_OF_SPACE;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return theStarKnight.DefaultMod.COLOUR_OUT_OF_SPACE;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }


    //////////////////////////
    //BEGIN NON-DEFAULT CODE//
    //////////////////////////

    //Apply ShardEcho power
    public void applyPreCombatLogic() {
        super.applyPreCombatLogic();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ShardEcho(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
        //Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();
    }


    static {
        layerSpeeds = new float[]{-20.0F, 20.0F, -40.0F, 40.0F, 0.0F};
    }


}
