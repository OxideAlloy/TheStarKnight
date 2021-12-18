package theStarKnight.cards.attacks;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theStarKnight.DefaultMod;
import theStarKnight.cards.defaultExamples.AbstractDynamicCard;
import theStarKnight.characters.TheDefault;

import static theStarKnight.DefaultMod.makeCardPath;

public class Roar_SK extends AbstractDynamicCard {

    //See "CardTemplate" for original template

    public static final String ID = DefaultMod.makeID(Roar_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Roar.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    //private static final int DAMAGE = 10;
    //private static final int UPGRADE_PLUS_DMG = 3;

    private static final int AMOUNT = 1;
    private static final int UPGRADED_AMOUNT = 2;

    private static final int BUFF = 7;
    private static final int UPGRADED_BUFF = 10;

    public Roar_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = BUFF;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        this.addToBot(
                new ApplyPowerAction(p, p, new VigorPower(p, defaultSecondMagicNumber), defaultSecondMagicNumber));
//        AbstractDungeon.actionManager.addToBottom(
//                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(
                new TalkAction(true, "@RRrroohrrRGHHhhh!!@", 2.0F, 2.0F));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
            this.upgradeDefaultSecondMagicNumber(UPGRADED_BUFF);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
