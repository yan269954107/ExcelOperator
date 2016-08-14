package com.yanxinwei.exceloperator.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yanxinwei.exceloperator.R;
import com.yanxinwei.exceloperator.targetmodel.HandVo;

import java.util.ArrayList;

/**
 * Created by yanxinwei on 16/7/1.
 */
public class TargetsAdapter extends BaseAdapter {

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
            holder.txtLabelNumber = (TextView) convertView.findViewById(R.id.txt_label_number);
            holder.txtArea = (TextView) convertView.findViewById(R.id.txt_area);
            holder.txtFlagNumber = (TextView) convertView.findViewById(R.id.txt_flag_number);
            holder.txtPicNumber = (TextView) convertView.findViewById(R.id.txt_pic_number);
            holder.txtMainReference = (TextView) convertView.findViewById(R.id.txt_main_reference);
            holder.txtOrientation = (TextView) convertView.findViewById(R.id.txt_orientation);
            holder.txtDistance = (TextView) convertView.findViewById(R.id.txt_distance);
            holder.txtFloor = (TextView) convertView.findViewById(R.id.txt_floor);
            holder.txtHeight = (TextView) convertView.findViewById(R.id.txt_height);
            holder.txtUnitType = (TextView) convertView.findViewById(R.id.txt_unit_type);
            holder.txtUnitSubType = (TextView) convertView.findViewById(R.id.txt_unit_sub_type);
            holder.txtExtra = (TextView) convertView.findViewById(R.id.txt_extra);
            holder.txtHigh = (TextView) convertView.findViewById(R.id.txt_high);
            holder.txtUnreachable = (TextView) convertView.findViewById(R.id.txt_unreachable);
            holder.txtPreserve = (TextView) convertView.findViewById(R.id.txt_preserve);
            holder.txtExpand = (TextView) convertView.findViewById(R.id.txt_expand);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HandVo handVo = mHandVos.get(position);
        holder.txtLabelNumber.setText(handVo.getBiaoqian());
        holder.txtArea.setText(handVo.getWeizhi3());
        holder.txtFlagNumber.setText(handVo.getChanpinliu() + "");
        holder.txtPicNumber.setText(handVo.getTuhao());
        holder.txtMainReference.setText(handVo.getZhuyaocankaowu());
        holder.txtOrientation.setText(handVo.getFangxiang());
        holder.txtDistance.setText(handVo.getJuli() + "");
        holder.txtFloor.setText(handVo.getLouceng() + "");
        holder.txtHeight.setText(handVo.getGaodu() + "");
        holder.txtUnitType.setText(handVo.getZujianleixing());
        holder.txtUnitSubType.setText(handVo.getZujianzileixing());
        holder.txtExtra.setText(handVo.getFujiamiaoshu());
        holder.txtExpand.setText(handVo.getKuozhan());
        if (getYesOrNo(handVo.getNanyichuji())) {
            holder.txtHigh.setText("高空");
        }
        if (getYesOrNo(handVo.getNanyujiance())) {
            holder.txtUnreachable.setText("不可达");
        }
        if (getYesOrNo(handVo.getZujianbeijueyuan())) {
            holder.txtPreserve.setText("保温");
        }
        return convertView;
    }

    private String getDescription(String str) {
        if (TextUtils.isEmpty(str)) {
            return "否";
        } else {
            return str;
        }
    }

    private boolean getYesOrNo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    static class ViewHolder {
        TextView txtLabelNumber;
        TextView txtArea;
        TextView txtFlagNumber;
        TextView txtPicNumber;
        TextView txtMainReference;
        TextView txtOrientation;
        TextView txtDistance;
        TextView txtFloor;
        TextView txtHeight;
        TextView txtUnitType;
        TextView txtUnitSubType;
        TextView txtExtra;
        TextView txtHigh;
        TextView txtUnreachable;
        TextView txtPreserve;
        TextView txtExpand;
    }
}
