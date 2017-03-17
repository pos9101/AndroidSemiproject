package app.com.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class StatusAdapter extends BaseAdapter{
    private String[] contentarr=new String[4];
    private String[] titlearr = {"ID","NAME","E-MAIL","TEL"};
    private Context myContext;

    private TextView tvtitle;
    private TextView tvcontent;

    public StatusAdapter(Context context,SignVO vo){
        myContext = context;
        contentarr[0]=vo.getId();
        contentarr[1]=vo.getName();
        contentarr[2]=vo.getEmail();
        contentarr[3]=vo.getTel();
    }
    @Override
    public int getCount() {
        return contentarr.length;
    }

    @Override
    public String getItem(int position) {
        return contentarr[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if(v==null) {
            v = ((LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.customlist_status, null);
            tvtitle = (TextView) v.findViewById(R.id.status_title);
            tvcontent = (TextView) v.findViewById(R.id.status_content);
        }

        String str =getItem(position);

        if(str!=null){
            tvtitle.setText(titlearr[position]);
            tvcontent.setText(contentarr[position]);
        }


        return v;
    }
}
