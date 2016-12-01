package eu.zvireciliga.teamlottery.data.model;

import java.io.Serializable;

public class Player implements Serializable
{
    private long id;
    private Gender gender;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
}
