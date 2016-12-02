package eu.zvireciliga.teamlottery.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.zvireciliga.teamlottery.data.model.Audit;
import eu.zvireciliga.teamlottery.data.model.Gender;
import eu.zvireciliga.teamlottery.data.model.Player;
import eu.zvireciliga.teamlottery.data.model.Team;

@EBean(scope = EBean.Scope.Singleton)
public class GlobalDAO
{
    private static final Type TEAM_TYPE = new TypeToken<List<Team>>() {}.getType();
    private static final Type AUDIT_TYPE = new TypeToken<List<Audit>>() {}.getType();

    public interface OnTeamChangeListener extends OnChangeListener<Team> {}
    public interface OnAuditChangeListener extends OnChangeListener<Audit> {}

    private List<OnTeamChangeListener> teamListeners = new ArrayList<>();
    private List<OnAuditChangeListener> auditListeners = new ArrayList<>();

    @Pref
    GlobalPreferences_ preferences;

    private final List<Team> teams = new ArrayList<>();
    private final List<Audit> audits = new ArrayList<>();

    @AfterInject
    void initialize()
    {
        final List<Team> storedTeams = new Gson().fromJson(preferences.teams().get(), TEAM_TYPE);
        if(storedTeams != null)
        {
            teams.addAll(storedTeams);
        }

        final List<Audit> storedAudits = new Gson().fromJson(preferences.audits().get(), AUDIT_TYPE);
        if(storedAudits != null)
        {
            audits.addAll(storedAudits);
        }

        watchTeams(new OnTeamChangeListener()
        {
            @Override
            public void onChange(List<Team> items)
            {
                preferences.teams().put(new Gson().toJson(items));
            }
        });
        watchAudit(new OnAuditChangeListener()
        {
            @Override
            public void onChange(List<Audit> items)
            {
                preferences.audits().put(new Gson().toJson(items));
            }
        });
    }

    public void initializeDefaultTeams()
    {
        for(String name : Arrays.asList("Sojky", "Hrdličky", "Drozdi", "Kukačky", "Jestřábi", "Sýkorky", "Kormoráni", "Rorýsi", "Skřivani", "Konipasi", "Volavky", "Sovy"))
        {
            final Team team = new Team();
            team.setName(name);
            teams.add(team);
        }
        executeTeamListeners();
    }

    public void watchTeams(OnTeamChangeListener listener)
    {
        teamListeners.add(listener);
        listener.onChange(teams);
    }

    public void watchAudit(OnAuditChangeListener listener)
    {
        auditListeners.add(listener);
        listener.onChange(audits);
    }

    public void addPlayer(Team team, Player player)
    {
        try
        {
            team.getPlayers().add(player);

            final Audit audit = new Audit();
            audit.setTeam(team);
            audit.setPlayer(player);
            audits.add(audit);
        }
        finally
        {
            executeTeamListeners();
            executeAuditListeners();
        }
    }

    public void revertOperation(Audit audit)
    {
        try
        {
            for(Team team : teams)
            {
                if(team.equals(audit.getTeam()))
                {
                    for (Player player : team.getPlayers())
                    {
                        if(player.equals(audit.getPlayer()))
                        {
                            team.getPlayers().remove(player);
                            break;
                        }
                    }
                    break;
                }
            }
            audits.remove(audit);
        }
        finally
        {
            executeTeamListeners();
            executeAuditListeners();
        }
    }

    public int getMaxTeamPlayers(Gender gender)
    {
        int max = 0;
        max += (Gender.MALE.equals(gender) || gender == null) ? preferences.maxMales().getOr(4) : 0;
        max += (Gender.FEMALE.equals(gender) || gender == null) ? preferences.maxFemales().getOr(2) : 0;
        return max;
    }

    public int getMaxPlayers()
    {
        return getMaxTeamPlayers(null) * teams.size();
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