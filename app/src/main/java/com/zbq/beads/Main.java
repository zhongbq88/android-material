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
    String[] cycle_array;
    CycleAdapter cycleAdapter;

    public Main(Activity context, int layoutId) {
        super(context, layoutId);
    }

    public Main(Activity context) {
        super(context, R.layout.main);
        cycle_list = (ListView)view.findViewById(R.id.cycle_list);
        cycle_array = context.getResources().getStringArray(R.array.cycle_array);
        cycleAdapter = new CycleAdapter();
        cycle_list.setAdapter(cycleAdapter);
    }

    @Override
    public void OnViewShow() {
        cycleAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnViewHide() {

    }

    class CycleAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cycle_array.length;
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
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.checkBox.setText(cycle_array[position]);
            return convertView;
        }
    }

    final class ViewHolder{
        CheckBox checkBox;
    }
}
