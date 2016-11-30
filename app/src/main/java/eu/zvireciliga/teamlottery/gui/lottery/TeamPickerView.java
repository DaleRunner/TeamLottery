package eu.zvireciliga.teamlottery.gui.lottery;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.model.Team;

@EViewGroup(R.layout.fragment_lottery_team)
public class TeamPickerView extends LinearLayout
{
    @ViewById
    TextView debug;

    public TeamPickerView(Context context)
    {
        super(context);
    }

    public void bind(Team team)
    {
        debug.setText(team.getName() + "\n" + team.getMales().size() + "/" + team.getFemales().size() + "/" + team.getPlayers().size());
    }
}
