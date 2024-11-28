package theStarKnight.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.CommonPower;
import theStarKnight.powers.IchorPower;
import theStarKnight.powers.MadnessPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class OpenMind_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(OpenMind_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("OpenMind.png");

//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 1;

    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;

    public OpenMind_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        BaseMod.MAX_HAND_SIZE=BaseMod.MAX_HAND_SIZE+magicNumber;
        AbstractDungeon.actionManager.addToBottom(
                //this is just to force an update of MadnessPower
                new ApplyPowerAction(p, p, new MadnessPower(p, 0), 0)
        );

                //new ApplyPowerAction(p, p, new MadnessPower(p, 2), 2)

        System.out.println("Hand size is currently = "+BaseMod.MAX_HAND_SIZE);
        System.out.println("MadnessPower should display = "+(BaseMod.MAX_HAND_SIZE-AbstractDungeon.player.hand.size()+1));
    }




    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}