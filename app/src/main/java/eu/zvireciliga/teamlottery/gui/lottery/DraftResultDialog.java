package eu.zvireciliga.teamlottery.gui.lottery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.gui.GUIArguments;
import eu.zvireciliga.teamlottery.model.Player;
import eu.zvireciliga.teamlottery.model.Team;

@EFragment
public class DraftResultDialog extends DialogFragment
{
    DraftDialogListener listener;

    @FragmentArg(GUIArguments.ARG_TEAM)
    Team team;

    @FragmentArg(GUIArguments.ARG_PLAYER)
    Player player;

    public interface DraftDialogListener {
        void onDialogPositiveClick(DraftResultDialog dialog, Team team, Player player);
        void onDialogNegativeClick(DraftResultDialog dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
            .setTitle(R.string.dialog_confirm_team)
            .setMessage("Team: " + team.getName())
            .setPositiveButton(R.string.dialog_save, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                    listener.onDialogPositiveClick(DraftResultDialog.this, team, player);
                }
            })
            .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                    listener.onDialogNegativeClick(DraftResultDialog.this);
                }
            }).create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            listener = (DraftDialogListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " must implement DraftDialogListener");
        }
    }
}
