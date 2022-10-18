package theStarKnight.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theStarKnight.cards.DarkTract_SK;

import java.lang.reflect.Field;


//See MasterDeckChangePatch in TheBeaked mod for original code referenced

public class AddToDeckPatch {
    public AddToDeckPatch() {
    }

//    @SpirePatch(
//            clz = CardGroup.class,
//            method = "removeCard",
//            paramtypez = {AbstractCard.class}
//    )
//    public static class onRemoveCard {
//        public onRemoveCard() {
//        }
//
//        public static void Prefix(CardGroup obj, AbstractCard c) {
//            if (c instanceof DarkTract_SK && obj.type == CardGroupType.MASTER_DECK) {
//                //((DarkTract_SK)c).onRemovedFromMasterDeck();
//            }
//        }
//    }

    @SpirePatch(
            clz = ShowCardAndObtainEffect.class,
            method = "update"
    )
    public static class ShowCardAndObtainEffectPatch {
        public ShowCardAndObtainEffectPatch() {
        }

        public static void Postfix(ShowCardAndObtainEffect obj) {
            if (obj.duration <= 0.0F) {
                try {
                    Field cardField = ShowCardAndObtainEffect.class.getDeclaredField("card");
                    cardField.setAccessible(true);
                    AbstractCard card = (AbstractCard)cardField.get(obj);
                    if (card instanceof DarkTract_SK) {
                        ((DarkTract_SK)card).onAddedToMasterDeck();
                    }
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

        }
    }

    @SpirePatch(
            clz = FastCardObtainEffect.class,
            method = "update"
    )
    public static class FastCardObtainEffectPatch {
        public FastCardObtainEffectPatch() {
        }

        public static void Postfix(FastCardObtainEffect obj) {
            if (obj.duration <= 0.0F) {
                try {
                    Field cardField = FastCardObtainEffect.class.getDeclaredField("card");
                    cardField.setAccessible(true);
                    AbstractCard card = (AbstractCard)cardField.get(obj);
                    if (card instanceof DarkTract_SK) {
                        ((DarkTract_SK)card).onAddedToMasterDeck();
                    }
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

        }
    }
}
