package eu.zvireciliga.teamlottery.gui.lottery;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.gui.GUIArguments;
import eu.zvireciliga.teamlottery.model.Gender;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
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
        final Gender gender = Gender.values()[position];
        int resource = Gender.MALE.equals(gender) ? R.string.player_male : R.string.player_female;
        return context.getString(resource);
    }
}
