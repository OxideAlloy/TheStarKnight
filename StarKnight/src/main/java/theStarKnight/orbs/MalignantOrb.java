package theStarKnight.orbs;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.math.MathUtils;
        import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.animations.VFXAction;
        import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
        import com.megacrit.cardcrawl.actions.common.DrawCardAction;
        import com.megacrit.cardcrawl.actions.utility.SFXAction;
        import com.megacrit.cardcrawl.cards.DamageInfo;
        import com.megacrit.cardcrawl.cards.purple.Protect;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.helpers.ImageMaster;
        import com.megacrit.cardcrawl.localization.OrbStrings;
        import com.megacrit.cardcrawl.orbs.AbstractOrb;
        import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
        import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
        import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

        import basemod.abstracts.CustomOrb;
        import theStarKnight.DefaultMod;

        import static theStarKnight.DefaultMod.makeOrbPath;

public class MalignantOrb extends CustomOrb {

    // Standard ID/Description
    public static final String ORB_ID = DefaultMod.makeID("MalignantOrb");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int PASSIVE_AMOUNT = 1;
    private static final int EVOKE_AMOUNT = 3;

    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    private boolean hFlip1 = MathUtils.randomBoolean();
    private boolean hFlip2 = MathUtils.randomBoolean();

    private Texture img1;
    private Texture img2;
    private Texture img3;

    public MalignantOrb() {
        // The passive/evoke description we pass in here, specifically, don't matter
        // You can ctrl+click on CustomOrb from the `extends CustomOrb` above.
        // You'll see below we override CustomOrb's updateDescription function with our own, and also, that's where the passiveDescription and evokeDescription
        // parameters are used. If your orb doesn't use any numbers/doesn't change e.g "Evoke: shuffle your draw pile."
        // then you don't need to override the update description method and can just pass in the parameters here.
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[0], DESCRIPTIONS[2], makeOrbPath("MalignantOrb.png"));

        updateDescription();

        this.img1 = ImageMaster.loadImage(makeOrbPath("MalignantOrb1.png"));
        this.img2 = ImageMaster.loadImage(makeOrbPath("MalignantOrb2.png"));
        this.img3 = ImageMaster.loadImage(makeOrbPath("MalignantOrb3.png"));

        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + evokeAmount + DESCRIPTIONS[1];
    }

//    @Override
//    public void applyFocus() {
//        this.passiveAmount = this.basePassiveAmount;
//        this.evokeAmount = this.baseEvokeAmount;
//    }

    @Override
    public void onEvoke() { // 1.On Orb Evoke

        AbstractDungeon.actionManager.addToBottom(
                new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, this.evokeAmount));

        AbstractDungeon.actionManager.addToBottom(new SFXAction("SLIME_BLINK_1")); // 3.And play a Jingle Sound.
        // For a list of sound effects you can use, look under com.megacrit.cardcrawl.audio.SoundMaster - you can see the list of keys you can use there. As far as previewing what they sound like, open desktop-1.0.jar with something like 7-Zip and go to audio. Reference the file names provided. (Thanks fiiiiilth)
        //AbstractDungeon.actionManager.addToBottom(new SFXAction("SLIME_SPLIT"));
    }

    @Override
    public void onEndOfTurn() {// 1.At the end of your turn.
        AbstractDungeon.actionManager.addToBottom(// 2.This orb will have a flare effect
                new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), 0.1f));
        AbstractDungeon.actionManager.addToBottom(
                new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, this.passiveAmount));
    }

    @Override
    public void updateAnimation() {// You can totally leave this as is.
        // If you want to create a whole new orb effect - take a look at conspire's Water Orb. It includes a custom sound, too!
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            //AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    // Render the orb.
    @Override
    public void render(SpriteBatch sb) {
        //sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
        //sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
        //sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
        //sb.setBlendFunction(770, 1);
        //sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, -angle, 0, 0, 96, 96, false, false);
        //sb.setBlendFunction(770, 771);
        //renderText(sb);
        //hb.render(sb);

        sb.setColor(this.c);
        sb.draw(img1, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 8.0F, 0, 0, 96, 96, this.hFlip1, false);
        sb.draw(img2, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F - this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 7.0F, 0, 0, 96, 96, this.hFlip1, false);
        sb.draw(img3, this.cX - 48.0F - this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 2.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip2, false);
        this.renderText(sb);
        this.hb.render(sb);

    }


    @Override
    public void triggerEvokeAnimation() { // The evoke animation of this orb is the dark-orb activation effect.
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }

    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        CardCrawlGame.sound.play("SLIME_SPLIT", 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new MalignantOrb();
    }
}
