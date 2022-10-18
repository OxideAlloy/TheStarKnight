package theStarKnight.cards;

        import com.megacrit.cardcrawl.characters.AbstractPlayer;
        import com.megacrit.cardcrawl.monsters.AbstractMonster;
        import theStarKnight.DefaultMod;

        import static theStarKnight.DefaultMod.makeCardPath;


public class Nihilism_SK extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Nihilism_SK.class.getSimpleName());
    public static final String IMG = makeCardPath("Nihilism.png");

    private static final CardRarity RARITY = CardRarity.CURSE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = CardColor.CURSE;

    private static final int COST = -2;

    public Nihilism_SK() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upgrade() {
    }
}
