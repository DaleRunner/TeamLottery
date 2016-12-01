package eu.zvireciliga.teamlottery.dao;

import java.util.List;

public interface OnChangeListener<T>
{
    void onChange(List<T> items);
}
