package eu.zvireciliga.teamlottery.dao;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import eu.zvireciliga.teamlottery.model.Gender;
import eu.zvireciliga.teamlottery.model.Player;
import eu.zvireciliga.teamlottery.model.Team;

@EBean(scope = EBean.Scope.Singleton)
public class TeamDAO
{
    public interface OnChangeListener {
        void onChange(List<Team> teams);
    }
    private List<OnChangeListener> listeners = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<String> names = Arrays.asList("Sojky", "Hrdličky", "Drozdi", "Kukačky", "Jestřábi", "Sýkorky", "Kormoráni", "Rorýsi", "Skřivani", "Konipasi", "Volavky", "Sovy");

    TeamDAO()
    {
        for(String name : names)
        {
            teams.add(createTeam(name));
        }
        teams.get(0).getFemales().add(new Player());
        teams.get(0).getFemales().add(new Player());
    }

    private final Random randomizer = new Random();
    private Team createTeam(String name)
    {
        final Team team = new Team();
        team.setId(randomizer.nextLong());
        team.setName(name);
        return team;
    }

    public List<Team> getTeams(OnChangeListener listener)
    {
        listeners.add(listener);
        return teams;
    }

    public void addPlayer(Team team, Gender gender)
    {
        final Player player = new Player();
        player.setMan(Gender.MALE.equals(gender));
        switch (gender) {
            case MALE:
                team.getMales().add(player);
                break;
            case FEMALE:
                team.getFemales().add(player);
                break;
        }
        executeListeners();
    }

    private void executeListeners()
    {
        for(OnChangeListener listener : listeners)
        {
            listener.onChange(teams);
        }
    }
}
