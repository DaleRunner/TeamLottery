package eu.zvireciliga.teamlottery;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.gui.lottery.SectionsPagerAdapter;

@EActivity(R.layout.activity_lottery)
public class LotteryActivity extends AppCompatActivity
{
    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.container)
    ViewPager pager;

    @ViewById(R.id.tabs)
    TabLayout tabLayout;

    @AfterViews
    void initialize()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
