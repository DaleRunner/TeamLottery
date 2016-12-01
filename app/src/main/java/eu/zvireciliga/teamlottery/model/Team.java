package eu.zvireciliga.teamlottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team implements Serializable
{
    private long id;
    private String name;
    private List<Player> players = new ArrayList<>();

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
        return players;
    }

    public List<Player> getMales()
    {
        final List<Player> males = new ArrayList<>();
        for(Player player : players)
        {
            if(Gender.MALE.equals(player.getGender()))
            {
                males.add(player);
            }
        }
        return Collections.unmodifiableList(males);
    }

    public List<Player> getFemales()
    {
        final List<Player> females = new ArrayList<>();
        for(Player player : players)
        {
            if(Gender.FEMALE.equals(player.getGender()))
            {
                females.add(player);
            }
        }
        return Collections.unmodifiableList(females);
    }
}