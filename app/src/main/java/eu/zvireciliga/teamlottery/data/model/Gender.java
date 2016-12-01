package eu.zvireciliga.teamlottery.data.model;

import eu.zvireciliga.teamlottery.R;

public enum Gender
{
    MALE(R.string.player_male), FEMALE(R.string.player_female);

    private final int resource;

    Gender(int resource)
    {
        this.resource = resource;
    }

    public int getString()
    {
        return resource;
    }
}
