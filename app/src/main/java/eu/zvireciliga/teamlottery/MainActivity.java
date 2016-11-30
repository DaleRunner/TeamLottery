package eu.zvireciliga.teamlottery;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import eu.zvireciliga.teamlottery.adapters.TeamsAdapter;
import eu.zvireciliga.teamlottery.dao.TeamDAO;
import eu.zvireciliga.teamlottery.model.Team;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.options)
public class MainActivity extends AppCompatActivity
{
    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @ViewById
    FloatingActionButton fab;

    @ViewById
    ListView teamList;

    @ViewById
    ProgressBar progressBar;

    @Bean
    TeamsAdapter teamsAdapter;

    @Bean
    TeamDAO dao;

    private final List<Team> teams = new ArrayList<>();

    @AfterViews
    void initialize()
    {
        teamList.setAdapter(teamsAdapter);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        teams.addAll(dao.getTeams(new TeamDAO.OnChangeListener()
        {
            @Override
            public void onChange(List<Team> newTeams)
            {
                teams.clear();
                teams.addAll(newTeams);
                calculateProgress();
            }
        }));
        calculateProgress();
        progressBar.setMax(teams.size() * 6);
    }

    private void calculateProgress()
    {
        int currentPlayers = 0;
        for(Team team : teams)
        {
            currentPlayers += team.getPlayers().size();
        }
        progressBar.setProgress(currentPlayers);
        fab.setEnabled(currentPlayers < (teams.size() * 6));
        if(!fab.isEnabled())
        {
            fab.hide();
        }
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    @OptionsItem(R.id.action_settings)
    void settingsMenuItem()
    {
        SettingsActivity_.intent(MainActivity.this).start();
    }

    @Click(R.id.fab)
    void onFabClick()
    {
        LotteryActivity_.intent(MainActivity.this).start();
    }
}
