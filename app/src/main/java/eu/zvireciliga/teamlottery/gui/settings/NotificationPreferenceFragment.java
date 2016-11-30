package eu.zvireciliga.teamlottery.gui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.SettingsActivity_;

public class NotificationPreferenceFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_notification);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            startActivity(new Intent(getActivity(), SettingsActivity_.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
