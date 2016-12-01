package eu.zvireciliga.teamlottery.data;

import java.util.List;

public interface OnChangeListener<T>
{
    void onChange(List<T> items);
}
