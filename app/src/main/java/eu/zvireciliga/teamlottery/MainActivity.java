package eu.zvireciliga.teamlottery;

import android.support.design.widget.FloatingActionButton;
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

import eu.zvireciliga.teamlottery.adapters.AuditAdapter;
import eu.zvireciliga.teamlottery.adapters.TeamsAdapter;
import eu.zvireciliga.teamlottery.dao.TeamDAO;
import eu.zvireciliga.teamlottery.model.Audit;
import eu.zvireciliga.teamlottery.model.Team;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.options)
public class MainActivity extends AppCompatActivity
{
    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById
    FloatingActionButton fab;

    @ViewById
    ListView teamList;

    @ViewById(R.id.nav_view_list)
    ListView navigationViewItems;

    @ViewById
    ProgressBar progressBar;

    @Bean
    TeamsAdapter teamsAdapter;

    @Bean
    AuditAdapter auditAdapter;

    @Bean
    TeamDAO dao;

    private final List<Team> teams = new ArrayList<>();

    @AfterViews
    void initialize()
    {
        teamList.setAdapter(teamsAdapter);
        navigationViewItems.setAdapter(auditAdapter);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        teams.addAll(dao.getTeams(new TeamDAO.OnTeamChangeListener()
        {
            @Override
            public void onChange(List<Team> items)
            {
                teams.clear();
                teams.addAll(items);
                calculateProgress();
            }
        }));
        calculateProgress();
        progressBar.setMax(teams.size() * 6);

        dao.getAudit(new TeamDAO.OnAuditChangeListener()
        {
            @Override
            public void onChange(List<Audit> items)
            {
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void calculateProgress()
    {
        int males = 0, females = 0;
        for(Team team : teams)
        {
            males += team.getMales().size();
            females += team.getFemales().size();
        }
        progressBar.setProgress(males);
        progressBar.setSecondaryProgress(males + females);

        fab.setEnabled((males + females) < (teams.size() * 6));
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
