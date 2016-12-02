package eu.zvireciliga.teamlottery.gui.settings;


import android.preference.PreferenceActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.PreferenceScreen;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.R;

@EActivity(R.layout.activity_settings)
@PreferenceScreen(R.xml.preferences)
public class SettingsActivity extends PreferenceActivity
{
    @ViewById
    Toolbar toolbar;

    @AfterViews
    void initialize()
    {
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
}
