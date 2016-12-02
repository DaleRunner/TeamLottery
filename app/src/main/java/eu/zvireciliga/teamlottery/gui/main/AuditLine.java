package eu.zvireciliga.teamlottery.gui.main;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.zvireciliga.teamlottery.R;
import eu.zvireciliga.teamlottery.data.GlobalDAO;
import eu.zvireciliga.teamlottery.data.model.Audit;

@EViewGroup(R.layout.navigation_audit_item)
public class AuditLine extends LinearLayout
{
    @ViewById
    TextView timestamp;

    @ViewById
    TextView teamName;

    @ViewById
    TextView gender;

    @Bean
    GlobalDAO dao;

    private Audit audit;

    public AuditLine(Context context)
    {
        super(context);
    }

    public void bind(Audit audit)
    {
        this.audit = audit;
        timestamp.setText(DateUtils.formatDateTime(getContext(), audit.getTimestamp(), DateUtils.FORMAT_SHOW_TIME));
        teamName.setText(audit.getTeam().getName());
        gender.setText(audit.getPlayer().getGender().getString());
    }

    @Click(R.id.revertButton)
    void onRevertClick()
    {
        dao.revertOperation(audit);
    }
}
