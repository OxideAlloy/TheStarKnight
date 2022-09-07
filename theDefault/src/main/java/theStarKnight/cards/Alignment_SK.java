package theStarKnight.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.CorruptionPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.AdaptivePower;
import theStarKnight.powers.AlignmentPower;
import theStarKnight.powers.CommonPower;

import java.util.Iterator;

import static theStarKnight.DefaultMod.makeCardPath;

public class Alignment_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Alignment_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Alignment.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 3;

    private static final int AMOUNT = 1;
//    private static final int UPGRADED_AMOUNT = 1;

    public Alignment_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        this.isEthereal = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE, p.hb.cX, p.hb.cY), 0.33F));
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.BLACK, p.hb.cX, p.hb.cY), 0.50F));

        this.addToBot(new ApplyPowerAction(p, p, new AlignmentPower(p, this.magicNumber)));

        //OMG why is corruption coded like it is?  (╯°□°)╯︵ ┻━┻
//        int theSize = AbstractDungeon.player.hand.size();
//        this.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, theSize, false));

    }



    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            this.isEthereal = false;
            initializeDescription();
        }
    }
}