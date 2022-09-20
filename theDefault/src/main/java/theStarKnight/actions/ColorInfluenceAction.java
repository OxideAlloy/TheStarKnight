package theStarKnight.actions;

        import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.helpers.CardLibrary;
        import com.megacrit.cardcrawl.screens.CardRewardScreen;
        import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
        import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
        import java.util.ArrayList;

public class ColorInfluenceAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private int cardChoices = 1;
    private boolean isRed;

    public ColorInfluenceAction(int num, boolean redColor) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.isRed = redColor;
        this.cardChoices = num;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(this.generateCardChoices(), CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
//                    if (this.upgraded) {
//                        disCard.setCostForTurn(0);
//                    }

                    disCard.current_x = -1000.0F * Settings.xScale;
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList derp = new ArrayList();

        while (derp.size() != this.cardChoices) {
            //int roll = AbstractDungeon.cardRandomRng.random(99);
            AbstractCard.CardRarity cardRarity;
//            if (roll < 55) {
//                cardRarity = AbstractCard.CardRarity.COMMON;
//            } else if (roll < 85) {
//                cardRarity = AbstractCard.CardRarity.UNCOMMON;
//            } else {
//                cardRarity = AbstractCard.CardRarity.RARE;
//            }
            cardRarity = AbstractCard.CardRarity.COMMON;

            if (this.isRed) {
                    //if isRed then pulls a random skill and rejects all non-red cards
                AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.SKILL, cardRarity);
                if (tmp.color != AbstractCard.CardColor.RED) {
                    continue;
                }
                derp.add(tmp.makeCopy());
            } else {
                //if isRed is false then pulls a random attack and reject all non-blue cards
                AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity);
                if (tmp.color != AbstractCard.CardColor.BLUE) {
                    continue;
                }
                derp.add(tmp.makeCopy());
            }
        }
        return derp;
    }
}





//        ArrayList derp = new ArrayList();
//
//        ArrayList redList = new ArrayList();
//
//
//        private ArrayList<AbstractCard> generateCardChoices() {
//            ArrayList derp = new ArrayList();
//
//            while(derp.size() != 4) {
//                int roll = AbstractDungeon.cardRandomRng.random(99);
//                CardRarity cardRarity;
//                if (roll < 65) {
//                    cardRarity = CardRarity.COMMON;
//                } else {
//                    cardRarity = CardRarity.UNCOMMON;
//                }
//
//                for(AbstractCard.CardColor color : AbstractCard.CardColor.values()) {
//                    if (color == AbstractCard.CardColor.CURSE || color == AbstractCard.CardColor.COLORLESS || color == TheAncient.Enums.COLOR_ANCIENT_YELLOW){
//                        continue;
//                    }
//                    AbstractCard tmp = CardLibrary.getAnyColorCard(cardRarity);
//                    derp.add(tmp.makeCopy());
//                }
//            }
//            return derp;
//        }
//
//
//
//        //ArrayList dope = new ArrayList();
//
//        //AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON);
//
//
////        while(derp.size() != this.cardChoices) {
////            AbstractCard tmp = new Havoc();
////            int roll = AbstractDungeon.cardRandomRng.random(2);
////            switch(roll) {
////                case 1:
////                    tmp = new Bash();
////                    break;
////                case 2:
////                    tmp = new Anger();
////                    break;
////                case 3:
////                    tmp = new BodySlam();
////                    break;
////            }
//            //derp.add(tmp.makeCopy());
//
//
////            Iterator var6 = derp.iterator();
////            AbstractCard c = (AbstractCard)var6.next();
////            derp.add(tmp.makeCopy());
////
////            boolean dupe = false;
////            int roll = AbstractDungeon.cardRandomRng.random(99);
////            AbstractCard.CardRarity cardRarity;
////            if (roll < 60) {
////                cardRarity = AbstractCard.CardRarity.COMMON;
////            } else if (roll < 95) {
////                cardRarity = AbstractCard.CardRarity.UNCOMMON;
////            } else {
////                cardRarity = AbstractCard.CardRarity.RARE;
////            }
////
////            AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.SKILL, cardRarity);
////            Iterator var6 = derp.iterator();
////
////            while(var6.hasNext()) {
////                AbstractCard c = (AbstractCard)var6.next();
////                if (c.cardID.equals(tmp.cardID)) {
////                    dupe = true;
////                    break;
////                }
////            }
////
////            if (!dupe) {
////                derp.add(tmp.makeCopy());
////            }
////        }
//
//        return derp;
//    }




//}
