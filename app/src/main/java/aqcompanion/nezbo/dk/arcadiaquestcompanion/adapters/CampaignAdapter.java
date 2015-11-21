package aqcompanion.nezbo.dk.arcadiaquestcompanion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import aqcompanion.nezbo.dk.arcadiaquestcompanion.R;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Campaign;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Player;

/**
 * Created by Emil on 15-11-2015.
 */
public class CampaignAdapter extends BaseAdapter {

    private final List<Campaign> campaigns;
    private final Context context;

    public CampaignAdapter(List<Campaign> campaigns, Context c) {
        this.context = c;
        this.campaigns = campaigns;
    }
    
    @Override
    public int getCount() {
        return campaigns.size();
    }

    @Override
    public Object getItem(int position) {
        return campaigns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return campaigns.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Campaign campaign = campaigns.get(position);
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.campaign_item, null);
        }

        // Actually set stuff
        TextView title = (TextView) v.findViewById(R.id.tvCampaignTitle);
        TextView details = (TextView) v.findViewById(R.id.tvCampaignDetails);

        title.setText(campaign.getTitle());
        details.setText(makeDetails(campaign));

        if(campaign.isCompleted()) {
            title.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
        }

        return v;
    }

    private String makeDetails(Campaign campaign) {
        StringBuilder builder = new StringBuilder();
        if(!campaign.getPlayers().isEmpty()) {
            for(Player p : campaign.getPlayers()) {
                builder.append(p.getName());
                builder.append(", ");
            }

            builder.delete(builder.length()-3, builder.length()-1);
        }

        return builder.toString();
    }
}
