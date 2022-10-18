package theStarKnight.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.abstracts.CustomPotion;
import theStarKnight.powers.IchorPower;

public class IchorPotion extends CustomPotion {

    public static final String POTION_ID = theStarKnight.DefaultMod.makeID("IchorPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public IchorPotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.SMOKE);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = true;
        this.targetRequired = true;
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        //target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new IchorPower(target, AbstractDungeon.player, potency), potency));
        }
    }

//    public void use(AbstractCreature target) {
//        DamageInfo info = new DamageInfo(AbstractDungeon.player, this.potency, DamageInfo.DamageType.THORNS);
//        info.applyEnemyPowersOnly(target);
//        this.addToBot(new DamageAction(target, info, AbstractGameAction.AttackEffect.FIRE));
//    }

    @Override
    public AbstractPotion makeCopy() {
        return new IchorPotion();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 4;
    }

    public void upgradePotion()
    {
        potency += 2;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}
