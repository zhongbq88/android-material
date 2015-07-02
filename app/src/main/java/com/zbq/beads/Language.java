package com.zbq.beads;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by a123 on 15-7-1.
 */
public class Language extends BaseView{

    ListView language_list;
    String[] language_array;
    LanguageAdapter languageAdapter;
    int select = 0;

    public Language(Activity context, int layoutId) {
        super(context, layoutId);
    }

    public Language(Activity context) {
        super(context, R.layout.language);
        language_list = (ListView)view.findViewById(R.id.language_list);
        language_array = context.getResources().getStringArray(R.array.language_array);
        languageAdapter = new LanguageAdapter();
        language_list.setAdapter(languageAdapter);
    }

    @Override
    public void OnViewShow() {

    }

    @Override
    public void OnViewHide() {

    }

    class LanguageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return language_array.length;
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
                convertView = inflater.inflate(R.layout.language_item, null);
                viewHolder = new ViewHolder();
                viewHolder.languagetv = (TextView) convertView.findViewById(R.id.languagetv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.languagetv.setText(language_array[position]);
            if(select==position){
                viewHolder.languagetv.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.signup_choose,0);
            }else{
                viewHolder.languagetv.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            }
            return convertView;
        }
    }

    final class ViewHolder{
        TextView languagetv;
    }
}
