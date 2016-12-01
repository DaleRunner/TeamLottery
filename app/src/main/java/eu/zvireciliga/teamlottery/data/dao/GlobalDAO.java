package eu.zvireciliga.teamlottery.data.dao;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import eu.zvireciliga.teamlottery.data.OnChangeListener;
import eu.zvireciliga.teamlottery.data.model.Audit;
import eu.zvireciliga.teamlottery.data.model.Player;
import eu.zvireciliga.teamlottery.data.model.Team;

@EBean(scope = EBean.Scope.Singleton)
public class GlobalDAO
{
    public interface OnTeamChangeListener extends OnChangeListener<Team>
    {}
    public interface OnAuditChangeListener extends OnChangeListener<Audit> {}

    private List<OnTeamChangeListener> teamListeners = new ArrayList<>();
    private List<OnAuditChangeListener> auditListeners = new ArrayList<>();

    private List<Team> teams = new ArrayList<>();
    private List<Audit> audits = new ArrayList<>();

    GlobalDAO()
    {
        for(String name : Arrays.asList("Sojky", "Hrdličky", "Drozdi", "Kukačky", "Jestřábi", "Sýkorky", "Kormoráni", "Rorýsi", "Skřivani", "Konipasi", "Volavky", "Sovy"))
        {
            teams.add(createTeam(name));
        }
    }

    private final Random randomizer = new Random();
    private Team createTeam(String name)
    {
        final Team team = new Team();
        team.setId(randomizer.nextLong());
        team.setName(name);
        return team;
    }

    public List<Team> getTeams(OnTeamChangeListener listener)
    {
        teamListeners.add(listener);
        return teams;
    }

    public List<Audit> getAudit(OnAuditChangeListener listener)
    {
        auditListeners.add(listener);
        return audits;
    }

    public void addPlayer(Team team, Player player)
    {
        team.getPlayers().add(player);

        final Audit audit = new Audit();
        audit.setTeam(team);
        audit.setPlayer(player);
        audits.add(audit);
        executeTeamListeners();
    }

    public void revertOperation(Audit audit)
    {
        try
        {
            audit.getTeam().getPlayers().remove(audit.getPlayer());
            audits.remove(audit);
        }
        finally
        {
            executeTeamListeners();
            executeAuditListeners();
        }
    }

    private void executeTeamListeners()
    {
        for(OnTeamChangeListener listener : teamListeners)
        {
            listener.onChange(teams);
        }
    }

    private void executeAuditListeners()
    {
        for(OnAuditChangeListener listener : auditListeners)
        {
            listener.onChange(audits);
        }
    }
}