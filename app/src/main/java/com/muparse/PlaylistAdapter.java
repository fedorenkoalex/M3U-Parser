package com.muparse;

/**
 * Created by fedor on 28.11.2016.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ItemHolder> {
    private List<M3UItem> mItem = new ArrayList<M3UItem>();
    private Context mContext;
    private LayoutInflater mInflater;

    public PlaylistAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(mContext);
    }


    public class ItemHolder extends RecyclerView.ViewHolder {


        TextView name;
        TextView url;

        public ItemHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_name);
            url = (TextView) view.findViewById(R.id.item_url);

        }

        public void update(final M3UItem item) {
            name.setText(item.getItemName());
            url.setText(item.getItemUrl());
        }
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View sView = mInflater.inflate(R.layout.item_playlist, parent, false);
        return new ItemHolder(sView);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final M3UItem item = mItem.get(position);
        if (item != null) {
            holder.update(item);
        }
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }


    public void update(List<M3UItem> _list) {
        this.mItem = _list;
        notifyDataSetChanged();
    }
}
