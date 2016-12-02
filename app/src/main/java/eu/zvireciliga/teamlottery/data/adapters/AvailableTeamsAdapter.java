package eu.zvireciliga.teamlottery.data.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import eu.zvireciliga.teamlottery.data.GlobalDAO;
import eu.zvireciliga.teamlottery.data.model.Gender;
import eu.zvireciliga.teamlottery.data.model.Team;
import eu.zvireciliga.teamlottery.gui.lottery.TeamPickerView;
import eu.zvireciliga.teamlottery.gui.lottery.TeamPickerView_;

import static eu.zvireciliga.teamlottery.data.model.Gender.FEMALE;
import static eu.zvireciliga.teamlottery.data.model.Gender.MALE;

@EBean
public class AvailableTeamsAdapter extends BaseAdapter
{
    private static final Random random = new SecureRandom(new SecureRandom().generateSeed(20));
    private final List<Team> teams = new ArrayList<>();
    private final List<Team> availableTeams = new ArrayList<>();
    private Gender gender;

    @RootContext
    Context context;

    @Bean
    GlobalDAO dao;

    @AfterInject
    void initAdapter()
    {
        dao.watchTeams(new GlobalDAO.OnTeamChangeListener()
        {
            @Override
            public void onChange(List<Team> newTeams)
            {
                teams.clear();
                teams.addAll(newTeams);
                reload();
            }
        });
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
            if(team.getPlayers().size() < dao.getMaxPlayers()
                    && (gender == null
                        || (MALE.equals(gender) && team.getMales().size() < dao.getMaxTeamPlayers(Gender.MALE)))
                        || (FEMALE.equals(gender) && team.getFemales().size() < dao.getMaxTeamPlayers(Gender.FEMALE)))
            {
                availableTeams.add(team);
            }
        }
        Collections.shuffle(availableTeams, random);
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
