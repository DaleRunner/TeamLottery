package eu.zvireciliga.teamlottery.gui.lottery;

import android.content.Context;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;

import eu.zvireciliga.teamlottery.R;

@EViewGroup(R.layout.fragment_lottery_team)
public class TeamPickerView extends LinearLayout
{
    public TeamPickerView(Context context)
    {
        super(context);
    }
}
