package eu.zvireciliga.teamlottery.gui.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.zvireciliga.teamlottery.gui.GUIArguments;
import eu.zvireciliga.teamlottery.model.Gender;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        final LotteryGridFragment fragment = new LotteryGridFragment_();
        final Bundle args = new Bundle();
        args.putInt(GUIArguments.ARG_GENDER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount()
    {
        return Gender.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return Gender.values()[position].toString();
    }
}
