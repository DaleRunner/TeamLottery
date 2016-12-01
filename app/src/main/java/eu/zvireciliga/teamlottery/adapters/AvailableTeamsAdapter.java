package eu.zvireciliga.teamlottery.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import eu.zvireciliga.teamlottery.TeamSettings;
import eu.zvireciliga.teamlottery.dao.TeamDAO;
import eu.zvireciliga.teamlottery.gui.lottery.TeamPickerView;
import eu.zvireciliga.teamlottery.gui.lottery.TeamPickerView_;
import eu.zvireciliga.teamlottery.model.Gender;
import eu.zvireciliga.teamlottery.model.Team;

import static eu.zvireciliga.teamlottery.model.Gender.FEMALE;
import static eu.zvireciliga.teamlottery.model.Gender.MALE;

@EBean
public class AvailableTeamsAdapter extends BaseAdapter
{
    private final long seed = System.nanoTime();
    private final List<Team> teams = new ArrayList<>();
    private final List<Team> availableTeams = new ArrayList<>();
    private Gender gender;

    @RootContext
    Context context;

    @Bean
    TeamDAO dao;

    @AfterInject
    void initAdapter()
    {
        teams.addAll(dao.getTeams(new TeamDAO.OnTeamChangeListener()
        {
            @Override
            public void onChange(List<Team> newTeams)
            {
                teams.clear();
                teams.addAll(newTeams);
                reload();
            }
        }));
        reload();
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
        reload();
    }

    private void reload()
    {
        availableTeams.clear();
        for(Team team : teams)
        {
            if(team.getPlayers().size() < TeamSettings.MAX_PLAYERS
                    && (gender == null
                        || (MALE.equals(gender) && team.getMales().size() < TeamSettings.MAX_MALES))
                        || (FEMALE.equals(gender) && team.getFemales().size() < TeamSettings.MAX_FEMALES))
            {
                availableTeams.add(team);
            }
        }
        Collections.shuffle(availableTeams, new Random(seed));
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return availableTeams.size();
    }

    @Override
    public Team getItem(int position)
    {
        return availableTeams.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return availableTeams.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return (convertView == null) ? TeamPickerView_.build(context) : (TeamPickerView) convertView;
    }
}
