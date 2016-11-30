package eu.zvireciliga.teamlottery.gui.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.zvireciliga.teamlottery.model.Gender;

import static android.text.style.TtsSpan.ARG_GENDER;

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
        args.putInt(ARG_GENDER, position);
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
