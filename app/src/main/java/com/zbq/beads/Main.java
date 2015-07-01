package com.zbq.beads;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Created by a123 on 15-7-1.
 */
public class Main extends BaseView {

    ListView cycle_list;

    public Main(Activity context, int layoutId) {
        super(context, layoutId);
    }

    public Main(Activity context) {
        super(context, R.layout.main);
        cycle_list = (ListView)view.findViewById(R.id.cycle_list);
    }

    @Override
    public void OnViewShow() {

    }

    @Override
    public void OnViewHide() {

    }

    class CycleAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            return convertView;
        }
    }

    final class ViewHolder{
        CheckBox checkBox;
    }
}
