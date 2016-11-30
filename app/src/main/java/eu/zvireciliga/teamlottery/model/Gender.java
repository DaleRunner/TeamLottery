package eu.zvireciliga.teamlottery.model;

public enum Gender
{
    MALE, FEMALE;

    public static Gender getValue(int position)
    {
        return values()[position];
    }
}
