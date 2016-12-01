package eu.zvireciliga.teamlottery.data.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.zvireciliga.teamlottery.data.dao.GlobalDAO;
import eu.zvireciliga.teamlottery.gui.main.AuditLine;
import eu.zvireciliga.teamlottery.gui.main.AuditLine_;
import eu.zvireciliga.teamlottery.data.model.Audit;

@EBean
public class AuditAdapter extends BaseAdapter
{
    private final List<Audit> audits = new ArrayList<>();

    @RootContext
    Context context;

    @Bean
    GlobalDAO dao;

    @AfterInject
    void initAdapter()
    {
        audits.addAll(dao.getAudit(new GlobalDAO.OnAuditChangeListener()
        {
            @Override
            public void onChange(List<Audit> newTeams)
            {
                audits.clear();
                audits.addAll(newTeams);
                notifyDataSetChanged();
                Collections.reverse(audits);
            }
        }));
        Collections.reverse(audits);
    }

    @Override
    public int getCount()
    {
        return audits.size();
    }

    @Override
    public Audit getItem(int position)
    {
        return audits.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return audits.get(position).getId();
    }

    @Override
    public AuditLine getView(int position, View convertView, ViewGroup parent)
    {
        final AuditLine auditView = (convertView == null) ? AuditLine_.build(context) : (AuditLine) convertView;
        auditView.bind(getItem(position));
        return auditView;
    }
}
