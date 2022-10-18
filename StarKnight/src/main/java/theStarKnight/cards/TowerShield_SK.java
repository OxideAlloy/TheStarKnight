package theStarKnight.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStarKnight.DefaultMod;
import theStarKnight.characters.TheDefault;
import theStarKnight.powers.WeakNextTurnPower;

import static theStarKnight.DefaultMod.makeCardPath;

public class TowerShield_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(TowerShield_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("TowerShield.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOUR_SK;

    private static final int COST = 2;
    private static final int BLOCK = 15;
    private static final int UPGRADE_PLUS_BLOCK = 5;

    public TowerShield_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        this.selfRetain = true;
    }

    public void onRetained() {
        this.addToBot(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new WeakNextTurnPower(AbstractDungeon.player,AbstractDungeon.player, 1), 1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
