package eu.zvireciliga.teamlottery.gui.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.TeamSettings;
import eu.zvireciliga.teamlottery.data.model.Team;

@EViewGroup(R.layout.activity_main_team)
public class TeamOverview extends LinearLayout
{
    @ViewById
    TextView teamName;

    @ViewById
    TextView details;

    public TeamOverview(Context context)
    {
        super(context);
    }

    public void bind(Team team)
    {
        teamName.setText(team.getName());
        details.setText(team.getMales().size() + "/" + team.getFemales().size() + "/" + team.getPlayers().size());

        int color = R.color.teamEmpty;
        if(team.getPlayers().size() >= TeamSettings.MAX_PLAYERS)
        {
            color = R.color.teamFull;
        }
        else if (!team.getPlayers().isEmpty())
        {
            color = R.color.teamPartial;
        }
        this.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }
}
