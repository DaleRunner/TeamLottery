package eu.zvireciliga.teamlottery.model;

import java.util.ArrayList;
import java.util.List;

public class Team
{
    private long id;
    private String name;
    private List<Player> males = new ArrayList<>();
    private List<Player> females = new ArrayList<>();

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Player> getPlayers()
    {
        final List<Player> players = new ArrayList<>();
        players.addAll(getMales());
        players.addAll(getFemales());
        return players;
    }

    public List<Player> getMales()
    {
        return males;
    }

    public void setMales(List<Player> males)
    {
        this.males = males;
    }

    public List<Player> getFemales()
    {
        return females;
    }

    public void setFemales(List<Player> females)
    {
        this.females = females;
    }
}