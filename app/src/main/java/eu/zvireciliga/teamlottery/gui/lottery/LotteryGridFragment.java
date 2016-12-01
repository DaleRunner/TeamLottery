package eu.zvireciliga.teamlottery.gui.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.adapters.AvailableTeamsAdapter;
import eu.zvireciliga.teamlottery.gui.GUIArguments;
import eu.zvireciliga.teamlottery.model.Gender;
import eu.zvireciliga.teamlottery.model.Player;
import eu.zvireciliga.teamlottery.model.Team;

@EFragment(R.layout.fragment_lottery)
public class LotteryGridFragment extends Fragment
{
    @Bean
    AvailableTeamsAdapter adapter;

    @ViewById
    GridView gridView;

    @FragmentArg(GUIArguments.ARG_GENDER)
    int gender;

    @AfterViews
    void initialize()
    {
        adapter.setGender(Gender.getValue(gender));
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                final Team team = adapter.getItem(position);
                final Player player = new Player();
                player.setGender(Gender.getValue(gender));

                final DraftResultDialog dialog = new DraftResultDialog_();

                final Bundle args = new Bundle();
                args.putSerializable(GUIArguments.ARG_TEAM, team);
                args.putSerializable(GUIArguments.ARG_PLAYER, player);
                dialog.setArguments(args);

                dialog.show(getFragmentManager(), "DraftResultDialog");
            }
        });
    }
}