package theStarKnight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class Samsara_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Samsara_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Ouroboros.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int AMOUNT = 9;
    private static final int UPGRADED_AMOUNT = 5;
    private static final int DEBUFF = 2;
    //private static final int UPGRADED_DEBUFF = 1;


    // /STAT DECLARATION/

    public Samsara_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new VFXAction(new HemokinesisEffect(p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY), 0.5F));

        //AbstractDungeon.player.increaseMaxHp(-1, false);
        this.addToBot(new LoseHPAction(p, p, 2));

        this.addToBot(new AddTemporaryHPAction(p, p, this.magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
