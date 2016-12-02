package eu.zvireciliga.teamlottery.data;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface GlobalPreferences
{
    @DefaultInt(4)
    int maxMales();

    @DefaultInt(2)
    int maxFemales();

    @DefaultBoolean(false)
    boolean debug();

    String teams();

    String audits();
}
