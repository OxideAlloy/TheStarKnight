package theStarKnight.cards;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class TestCard2_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(TestCard2_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Blank.png");
    public static final String IMG2 = makeCardPath("TestOutline4.png");
    public static final String IMG3 = makeCardPath("TestOutline4_p.png");
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 0;
    //private static final int UPGRADED_COST = 2;

    private static final int AMOUNT = 6;
    private static final int UPGRADED_AMOUNT = 2;
    //private static final int DEBUFF = 1;
    //private static final int UPGRADED_DEBUFF = 1;

    public TestCard2_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        //defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
        setPortraitTextures(IMG2, IMG3);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, this.magicNumber), this.magicNumber));
        //this.addToBot(new TalkAction(true, "@RRrroohrrRGHHhhh!!@", 1.5F, 1.5F));
        //Testing things

        int cIndex = AbstractDungeon.player.hand.group.indexOf(this);
        if (cIndex>0) {
            /// Make a new action and roughly copy code from Mayham action to prevent errors
            AbstractCard c2 = AbstractDungeon.player.hand.group.get(cIndex - 1);
            this.addToBot(new NewQueueCardAction(c2, true, false, true));
        }
    }

    @Override
    public void hover() {
        int cIndex = AbstractDungeon.player.hand.group.indexOf(this);
        //.group.get(index + 1)
        //AbstractDungeon.player.hand().group.indexOf(this);
        //AbstractCard c = AbstractDungeon.player.hand.group.get(index + 1)
                if (cIndex>0) {
                    AbstractCard c2 = AbstractDungeon.player.hand.group.get(cIndex-1);
                    c2.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                    c2.target_x = this.current_x - 250;
                    c2.target_y = this.current_y;
                    c2.targetAngle = this.angle;
                    c2.targetDrawScale = 1.00F;
                }
    }

    @Override
    public void unhover() {
        AbstractCard c = AbstractDungeon.player.hand.group.get(2);
        c.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
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


// NOTES BELOW // NOTES BELOW // NOTES BELOW // NOTES BELOW // NOTES BELOW // NOTES BELOW // NOTES BELOW //

//From AbstractPlayer

//public void draw(int numCards) {
//    for(int i = 0; i < numCards; ++i) {
//        if (this.drawPile.isEmpty()) {
//            logger.info("ERROR: How did this happen? No cards in draw pile?? Player.java");
//        } else {
//            AbstractCard c = this.drawPile.getTopCard();
//            c.current_x = CardGroup.DRAW_PILE_X;
//            c.current_y = CardGroup.DRAW_PILE_Y;
//            c.setAngle(0.0F, true);
//            c.lighten(false);
//            c.drawScale = 0.12F;
//            c.targetDrawScale = 0.75F;
//            c.triggerWhenDrawn();
//            this.hand.addToHand(c);
//            this.drawPile.removeTopCard();
//
//            for(AbstractPower p : this.powers) {
//                p.onCardDraw(c);
//            }
//
//            for(AbstractRelic r : this.relics) {
//                r.onCardDraw(c);
//            }
//        }
//    }
//
//}