package theStarKnight.patches.monsters;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class DonuPatch
{
    @SpirePatch(clz=Donu.class,method=SpirePatch.CONSTRUCTOR)
    public static class DonusBigNaturals
    {
        @SpireInstrumentPatch
        public static ExprEditor Instrument()
        {
            return new ExprEditor()
            {
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getMethodName().equals("loadAnimation"))
                    {
                        m.replace("loadAnimation(\"theStarKnightResources/images/monsters/Donu/skeleton.atlas\", \"theStarKnightResources/images/monsters/Donu/skeleton.json\", 1.0F);");

                    }
                }
            };
        }
    }
}