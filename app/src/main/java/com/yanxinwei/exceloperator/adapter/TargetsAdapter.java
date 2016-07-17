package com.yanxinwei.exceloperator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanxinwei.exceloperator.R;
import com.yanxinwei.exceloperator.targetmodel.HandVo;

import java.util.ArrayList;

/**
 * Created by yanxinwei on 16/7/1.
 */
public class TargetsAdapter extends BaseAdapter{

    private ArrayList<HandVo> mHandVos;
    private Context mContext;

    public TargetsAdapter(Context context, ArrayList<HandVo> handVos) {
        mHandVos = handVos;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mHandVos.size();
    }

    @Override
    public Object getItem(int position) {
        return mHandVos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_targets, parent, false);
            holder = new ViewHolder();
            holder.imgState = (ImageView) convertView.findViewById(R.id.img_state);
            holder.txtExpandNumber = (TextView) convertView.findViewById(R.id.txt_expand_number);
            holder.txtLabelNumber = (TextView) convertView.findViewById(R.id.txt_label_number);
            holder.txtOrientation = (TextView) convertView.findViewById(R.id.txt_orientation);
            holder.txtPicNumber = (TextView) convertView.findViewById(R.id.txt_pic_number);
            holder.txtPosition1 = (TextView) convertView.findViewById(R.id.txt_position_1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HandVo handVo = mHandVos.get(position);
        holder.txtLabelNumber.setText(handVo.getBiaoqian());
        holder.txtExpandNumber.setText(handVo.getKuozhanhao());
        holder.txtPosition1.setText(handVo.getWeizhi1());
        holder.txtPicNumber.setText(handVo.getTuhao());
        holder.txtOrientation.setText(handVo.getFangxiang());
        return convertView;
    }

    static class ViewHolder{
        TextView txtLabelNumber;
        TextView txtExpandNumber;
        TextView txtPicNumber;
        TextView txtPosition1;
        TextView txtOrientation;
        ImageView imgState;
    }
}
