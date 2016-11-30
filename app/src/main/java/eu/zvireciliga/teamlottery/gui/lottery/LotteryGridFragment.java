package eu.zvireciliga.teamlottery.gui.lottery;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.adapters.AvailableTeamsAdapter;
import eu.zvireciliga.teamlottery.dao.TeamDAO;
import eu.zvireciliga.teamlottery.model.Gender;
import eu.zvireciliga.teamlottery.model.Team;

import static android.text.style.TtsSpan.ARG_GENDER;

@EFragment(R.layout.fragment_lottery)
public class LotteryGridFragment extends Fragment
{
    @Bean
    TeamDAO dao;

    @Bean
    AvailableTeamsAdapter adapter;

    @ViewById
    GridView gridView;

    @FragmentArg(ARG_GENDER)
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
                final String text = "You picked team: " + team.getName();
                Toast.makeText(LotteryGridFragment.this.getActivity(), text, Toast.LENGTH_SHORT).show();
                dao.addPlayer(team, Gender.getValue(gender));
            }
        });
    }
}
