package eu.zvireciliga.teamlottery;


import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.List;

import eu.zvireciliga.teamlottery.gui.settings.GeneralPreferenceFragment;
import eu.zvireciliga.teamlottery.gui.settings.NotificationPreferenceFragment;

@EActivity()
public class SettingsActivity extends AppCompatPreferenceActivity
{
    @AfterViews
    void initialize()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            if (!super.onMenuItemSelected(featureId, item))
            {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }
}
