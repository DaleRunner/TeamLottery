package eu.zvireciliga.teamlottery.data.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import eu.zvireciliga.teamlottery.data.dao.GlobalDAO;
import eu.zvireciliga.teamlottery.gui.main.TeamOverview;
import eu.zvireciliga.teamlottery.gui.main.TeamOverview_;
import eu.zvireciliga.teamlottery.data.model.Team;

@EBean
public class TeamsAdapter extends BaseAdapter
{
    private final List<Team> teams = new ArrayList<>();

    @RootContext
    Context context;

    @Bean
    GlobalDAO dao;

    @AfterInject
    void initAdapter()
    {
        teams.addAll(dao.getTeams(new GlobalDAO.OnTeamChangeListener()
        {
            @Override
            public void onChange(List<Team> newTeams)
            {
                teams.clear();
                teams.addAll(newTeams);
                notifyDataSetChanged();
            }
        }));
    }

    @Override
    public int getCount()
    {
        return teams.size();
    }

    @Override
    public Team getItem(int position)
    {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return teams.get(position).getId();
    }

    @Override
    public TeamOverview getView(int position, View convertView, ViewGroup parent)
    {
        final TeamOverview teamView = (convertView == null) ? TeamOverview_.build(context) : (TeamOverview) convertView;
        teamView.bind(getItem(position));
        return teamView;
    }
}
