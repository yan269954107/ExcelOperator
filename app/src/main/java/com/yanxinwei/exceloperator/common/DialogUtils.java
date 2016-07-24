package com.yanxinwei.exceloperator.common;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yanxinwei.exceloperator.R;

/**
 * Created by yanxinwei on 16/7/11.
 */
public class DialogUtils {

    public interface DialogListOnItem {
        void onItem(String data);
    }

    public static void showDialogList(final String[] datas, String title,
                                      Context context, final DialogListOnItem listener) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View convertView = LayoutInflater.from(context).inflate(R.layout.dialog_list_view, null);
        dialogBuilder.setView(convertView);
        dialogBuilder.setTitle(title);
        ListView lv = (ListView) convertView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, datas);
        lv.setAdapter(adapter);
        final AlertDialog dialog = dialogBuilder.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dialog != null) {
                    dialog.dismiss();
                    String data = datas[position];
                    listener.onItem(data);
                }
            }
        });
    }

    public static void showDialogList(final String[] datas, String[] dataVos, String title,
                                      Context context, final DialogListOnItem listener) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View convertView = LayoutInflater.from(context).inflate(R.layout.dialog_list_view, null);
        dialogBuilder.setView(convertView);
        dialogBuilder.setTitle(title);
        ListView lv = (ListView) convertView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,
                dataVos);
        lv.setAdapter(adapter);
        final AlertDialog dialog = dialogBuilder.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dialog != null) {
                    dialog.dismiss();
                    String data = datas[position];
                    listener.onItem(data);
                }
            }
        });
    }

    public static void showDialogListAndInput(final String[] datas, String title, final Context context,
                                              int inputType, final DialogListOnItem listener) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View convertView = LayoutInflater.from(context).inflate(R.layout.dialog_list_input_view, null);
        dialogBuilder.setView(convertView);
        dialogBuilder.setTitle(title);
        ListView lv = (ListView) convertView.findViewById(R.id.listView);
        TextView txtEmpty = (TextView) convertView.findViewById(R.id.txt_empty);
        lv.setEmptyView(txtEmpty);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, datas);
        lv.setAdapter(adapter);
        final AlertDialog dialog = dialogBuilder.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dialog != null) {
                    dialog.dismiss();
                    String data = datas[position];
                    listener.onItem(data);
                }
            }
        });
        Button btnConfirm = (Button) convertView.findViewById(R.id.btn_confirm);
        final EditText edtContent = (EditText) convertView.findViewById(R.id.edt_content);
        edtContent.setInputType(inputType);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtContent.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    T.showShort(context, "手写内容不能为空");
                } else {
                    if (dialog != null) {
                        dialog.dismiss();
                        listener.onItem(content);
                    }
                }
            }
        });
    }

    public static void showInputDialog(final Context context, String title, String inputContent,
                                       final DialogListOnItem listener) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View convertView = LayoutInflater.from(context).inflate(R.layout.dialog_input_view, null);
        dialogBuilder.setView(convertView);
        dialogBuilder.setTitle(title);

        final EditText editInput = (EditText) convertView.findViewById(R.id.edt_input);
        editInput.setHint(title);
        editInput.setText(inputContent);
        final AlertDialog dialog = dialogBuilder.show();
        Button btnConfirm = (Button) convertView.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editInput.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    T.showShort(context, "手写内容不能为空");
                } else {
                    if (dialog != null) {
                        dialog.dismiss();
                        listener.onItem(content);
                    }
                }
            }
        });
    }
}
