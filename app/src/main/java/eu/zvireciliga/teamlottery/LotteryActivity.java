package eu.zvireciliga.teamlottery;

import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.dao.TeamDAO;
import eu.zvireciliga.teamlottery.gui.lottery.DraftResultDialog;
import eu.zvireciliga.teamlottery.gui.lottery.SectionsPagerAdapter;
import eu.zvireciliga.teamlottery.model.Player;
import eu.zvireciliga.teamlottery.model.Team;

@EActivity(R.layout.activity_lottery)
public class LotteryActivity extends AppCompatActivity implements DraftResultDialog.DraftDialogListener
{
    @Bean
    TeamDAO dao;

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
        pager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onDialogPositiveClick(DraftResultDialog dialog, Team team, Player player)
    {
        dao.addPlayer(team, player);
        dialog.dismiss();
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onDialogNegativeClick(DraftResultDialog dialog)
    {
        dialog.dismiss();
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onBackPressed()
    {
        NavUtils.navigateUpFromSameTask(this);
    }
}
