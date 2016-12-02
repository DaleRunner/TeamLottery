package eu.zvireciliga.teamlottery.gui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import org.androidannotations.annotations.EFragment;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.data.model.Gender;

@EFragment
public class GenderPickerDialog extends DialogFragment
{
    GenderPickerDialogListener listener;

    public interface GenderPickerDialogListener
    {
        void onDialogClick(GenderPickerDialog dialog, Gender gender);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.dialog_gender)
            .setNegativeButton(R.string.player_male, new GenderClickListener(Gender.MALE))
            .setPositiveButton(R.string.player_female, new GenderClickListener(Gender.FEMALE))
            .setNeutralButton(R.string.dialog_cancel, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    GenderPickerDialog.this.dismiss();
                }
            })
            .create();
    }

    private class GenderClickListener implements DialogInterface.OnClickListener
    {
        private final Gender gender;

        GenderClickListener(Gender gender)
        {
            this.gender = gender;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            listener.onDialogClick(GenderPickerDialog.this, gender);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            listener = (GenderPickerDialogListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " must implement GenderPickerDialogListener");
        }
    }
}
