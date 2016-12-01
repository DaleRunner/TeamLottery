package eu.zvireciliga.teamlottery.gui.lottery;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.zvireciliga.teamlottery.data.model.Gender;
import eu.zvireciliga.teamlottery.gui.GUIArguments;

class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private final Context context;
    private final Gender gender;

    SectionsPagerAdapter(FragmentManager fm, Context context, Gender gender)
    {
        super(fm);
        this.context = context;
        this.gender = gender;
    }

    @Override
    public Fragment getItem(int position)
    {
        final LotteryGridFragment fragment = new LotteryGridFragment_();
        final Bundle args = new Bundle();
        args.putSerializable(GUIArguments.ARG_GENDER, gender);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount()
    {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return context.getString(gender.getString());
    }
}
