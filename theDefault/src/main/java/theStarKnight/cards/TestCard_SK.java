package theStarKnight.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class TestCard_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(TestCard_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Blank.png");
    public static final String IMG2 = makeCardPath("TestOutline4.png");
    public static final String IMG3 = makeCardPath("TestOutline4_p.png");
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;
    //private static final int UPGRADED_COST = 2;

    private static final int AMOUNT = 6;
    private static final int UPGRADED_AMOUNT = 2;
    //private static final int DEBUFF = 1;
    //private static final int UPGRADED_DEBUFF = 1;

    public TestCard_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        //defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
        setPortraitTextures(IMG2, IMG3);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, this.magicNumber), this.magicNumber));

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //setPortraitTextures(IMG2, IMG3);
            //this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            //upgradeBaseCost(UPGRADED_COST);
            //this.loadCardImage(IMG2);
            initializeDescription();
        }
    }
}

