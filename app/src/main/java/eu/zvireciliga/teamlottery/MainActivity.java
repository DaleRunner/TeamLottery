package eu.zvireciliga.teamlottery;

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
    ListView teamList;

    @ViewById
    ProgressBar progressBar;

    @Bean
    TeamsAdapter teamsAdapter;

    @Bean
    TeamDAO dao;

    @AfterViews
    void initialize()
    {
        teamList.setAdapter(teamsAdapter);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final List<Team> teams = dao.getTeams(new TeamDAO.OnChangeListener()
        {
            @Override
            public void onChange(List<Team> teams)
            {
                calculateProgress(teams);
            }
        });
        calculateProgress(teams);
    }

    private void calculateProgress(List<Team> teams)
    {
        int maxPlayers = teams.size() * 6;
        int currentPlayers = 0;
        for(Team team : teams)
        {
            currentPlayers += team.getPlayers().size();
        }
        progressBar.setProgress((int) (currentPlayers * (maxPlayers / 100.0f)));
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
