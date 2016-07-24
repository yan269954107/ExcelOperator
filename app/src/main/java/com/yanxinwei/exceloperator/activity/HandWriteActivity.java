package com.yanxinwei.exceloperator.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.generator.excelparser.ExcelParser;
import com.generator.excelparser.Result;
import com.generator.targetmodel.ParserTarget;
import com.yanxinwei.exceloperator.MyApplication;
import com.yanxinwei.exceloperator.R;
import com.yanxinwei.exceloperator.common.AppConstants;
import com.yanxinwei.exceloperator.common.DialogUtils;
import com.yanxinwei.exceloperator.common.MyTextWatcher;
import com.yanxinwei.exceloperator.common.T;
import com.yanxinwei.exceloperator.common.Utils;
import com.yanxinwei.exceloperator.targetmodel.Extension;
import com.yanxinwei.exceloperator.targetmodel.Hand;
import com.yanxinwei.exceloperator.targetmodel.HandVo;
import com.yanxinwei.exceloperator.targetmodel.Mapper;
import com.yanxinwei.exceloperator.widget.ParamsBlock;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandWriteActivity extends BaseActivity implements View.OnClickListener {

    public static final int REQUEST_EXTENSION = 10001;

    public static final String EXTENSION_LIST = "extension_list";

    private static final String UNIT_VALVE = "阀门";

    public static final double HIGH_THRESHOLD = 2.5;
    public static final double UNREACHABLE_THRESHOLD = 4.5;

    @Bind(R.id.pb_equipment)
    ParamsBlock mPbEquipment;
    @Bind(R.id.pb_area)
    ParamsBlock mPbArea;
    @Bind(R.id.pb_tag_number)
    ParamsBlock mPbTagNumber;
    @Bind(R.id.pb_extension_number)
    ParamsBlock mPbExtensionNumber;
    @Bind(R.id.pb_flag_number)
    ParamsBlock mPbFlagNumber;
    @Bind(R.id.pb_pic_number)
    ParamsBlock mPbPicNumber;
    @Bind(R.id.pb_main_reference)
    ParamsBlock mPbMainReference;
    @Bind(R.id.pb_orientation)
    ParamsBlock mPbOrientation;
    @Bind(R.id.pb_distance)
    ParamsBlock mPbDistance;
    @Bind(R.id.pb_floor)
    ParamsBlock mPbFloor;
    @Bind(R.id.pb_height)
    ParamsBlock mPbHeight;
    @Bind(R.id.pb_unit_type)
    ParamsBlock mPbUnitType;
    @Bind(R.id.pb_unit_sub_type)
    ParamsBlock mPbUnitSubType;
    @Bind(R.id.pb_size)
    ParamsBlock mPbSize;
    @Bind(R.id.pb_extra_des)
    ParamsBlock mPbExtraDes;
    @Bind(R.id.pb_medium_states)
    ParamsBlock mPbMediumStates;
    @Bind(R.id.pb_high)
    ParamsBlock mPbHigh;
    @Bind(R.id.pb_not_arrive)
    ParamsBlock mPbNotArrive;
    @Bind(R.id.pb_preserve)
    ParamsBlock mPbPreserve;
    @Bind(R.id.pb_extension)
    ParamsBlock mPbExtension;

    private HandVo mHandVo;
    private int mPosition;

    private List<HandVo> mOldExtensions;
    private boolean isModifyExtensions = false;
    private boolean isNew;
    private boolean isSaved = false;

    public static void startActivity(Context context, HandVo handVo, int position, boolean isNew) {
        Intent intent = new Intent(context, HandWriteActivity.class);
        intent.putExtra("target_handVo", handVo);
        intent.putExtra("position", position);
        intent.putExtra("isNew", isNew);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_write);
        ButterKnife.bind(this);

        mHandVo = (HandVo) getIntent().getSerializableExtra("target_handVo");
        mPosition = getIntent().getIntExtra("position", -1);
        isNew = getIntent().getBooleanExtra("isNew", false);

        if (mPosition == -1) {
            T.showShort(this, "数据异常,请退出后重试");
        }

        mOldExtensions = mHandVo.getExtensions();
        bindValue();

    }

    private void bindValue() {
        mPbEquipment.setValue(mHandVo.getWeizhi1());
        mPbArea.setValue(mHandVo.getWeizhi3());
        mPbTagNumber.setValue(mHandVo.getBiaoqian());
        mPbExtensionNumber.setValue(mHandVo.getKuozhanhao());
        mPbFlagNumber.setValue(mHandVo.getChanpinliu() + "");
        mPbPicNumber.setValue(mHandVo.getTuhao());
        mPbMainReference.setValue(mHandVo.getZhuyaocankaowu());
        mPbOrientation.setValue(mHandVo.getFangxiang());
        mPbDistance.setValue(mHandVo.getJuli() + "");
        mPbFloor.setValue(mHandVo.getLouceng() + "");
        mPbHeight.setValue(mHandVo.getGaodu() + "");
        mPbUnitType.setValue(mHandVo.getZujianleixing());
        mPbUnitSubType.setValue(mHandVo.getZujianzileixing());
        mPbSize.setValue(mHandVo.getChicun() + "");
        String[] extraDes = Utils.getExtraDes(mHandVo.getFujiamiaoshu());
        mPbExtraDes.setValue(extraDes[1]);
        mPbExtraDes.setRelativeValue(extraDes[0]);
        mPbMediumStates.setValue(mHandVo.getJiezhizhuangtai());
        mPbHigh.setValue(mHandVo.getNanyichuji());
        mPbNotArrive.setValue(mHandVo.getNanyujiance());
        mPbPreserve.setValue(mHandVo.getZujianbeijueyuan());
        mPbExtension.setValue(mHandVo.getKuozhan());

        mPbExtension.setOnClickListener(this);
        mPbOrientation.setOnClickListener(this);
        mPbPreserve.setOnClickListener(this);
        mPbUnitType.setOnClickListener(this);
        mPbUnitSubType.setOnClickListener(this);
        mPbMediumStates.setOnClickListener(this);
        mPbNotArrive.setOnClickListener(this);
        mPbExtraDes.setOnClickListener(this);
        mPbHigh.setOnClickListener(this);
        mPbSize.setOnClickListener(this);


        mPbHeight.setTextWatcher(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String data = s.toString();
                if (!TextUtils.isEmpty(data)) {
                    Double height = Double.parseDouble(data);
                    if (height >= HIGH_THRESHOLD && height < UNREACHABLE_THRESHOLD) {
                        mPbHigh.setValue("是");
                        mPbNotArrive.setValue("");
                    } else if (height >= UNREACHABLE_THRESHOLD) {
                        mPbHigh.setValue("");
                        mPbNotArrive.setValue("是");
                    } else {
                        mPbHigh.setValue("");
                        mPbNotArrive.setValue("");
                    }
                }
            }
        });

        mPbArea.setTextWatcher(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String data = s.toString();
                if (!TextUtils.isEmpty(data)) {
                    mPbExtraDes.setRelativeValue(data);
                }
            }
        });
    }


    @OnClick({R.id.btn_pre, R.id.btn_save, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pre:
                goPre();
                break;
            case R.id.btn_save:
                save();
                break;
            case R.id.btn_next:
                goNext();
                break;
            case R.id.pb_extension:
                navigateToExtension();
                break;
            case R.id.pb_orientation:
                selectOrientation();
                break;
            case R.id.pb_preserve:
            case R.id.pb_not_arrive:
            case R.id.pb_high:
                selectYesOrNo(view);
                break;
            case R.id.pb_unit_type:
                selectUnitType();
                break;
            case R.id.pb_unit_sub_type:
                selectUnitSubType();
                break;
            case R.id.pb_medium_states:
                selectMediumStates();
                break;
            case R.id.pb_extra_des:
                selectExtraDes();
                break;
            case R.id.pb_size:
                selectSize();
                break;
        }
    }

    private void selectSize() {
        DialogUtils.showDialogListAndInput(AppConstants.ALL_SIZE, "请选择尺寸", this,
                InputType.TYPE_CLASS_NUMBER, new DialogUtils.DialogListOnItem() {
                    @Override
                    public void onItem(String data) {
                        mPbSize.setValue(data);
                    }
                });
    }

    private void selectExtraDes() {
        String prefix = Utils.getExtraPrefix(mPbArea.getValue());
        if (UNIT_VALVE.equals(mPbUnitType.getValue())) {
            prefix = prefix + "f";
        }
        String[] data = AppConstants.EXTRA_DES.get(prefix);
        if (null == data) {
            data = new String[0];
        }
        DialogUtils.showDialogListAndInput(data, "请选择附加描述", this, InputType.TYPE_CLASS_TEXT, new DialogUtils.DialogListOnItem() {
            @Override
            public void onItem(String data) {
                mPbExtraDes.setValue(data);
            }
        });
    }

    private void goNext() {
        if (checkIsModified()) {
            T.showShort(this, "记录已修改, 请保存后再离开");
            return;
        }
        ArrayList<HandVo> handVos = MyApplication.getInstance().getHandVos();
        int exitSize = handVos.size();
        int nextPosition = mPosition + 1;
        //还有已存在的记录,直接获取打开
        if (nextPosition < exitSize) {
            HandVo nextHandVo = handVos.get(nextPosition);
            HandWriteActivity.startActivity(this, nextHandVo, nextPosition, false);
            finish();
        } else {
            //已经没有存在的记录了,需要新建
            HandVo newNextHandVo = Mapper.getNewNextHandVo(mHandVo);
            handVos.add(newNextHandVo);
            HandWriteActivity.startActivity(this, newNextHandVo, nextPosition, true);
            finish();
        }
    }

    private void goPre() {
        if (checkIsModified()) {
            T.showShort(this, "记录已修改, 请保存后再离开");
            return;
        }
        if (mPosition == 0) {
            T.showShort(this, "已经是第一个了");
            return;
        }
        int prePosition = mPosition - 1;
        HandVo preHandVo = MyApplication.getInstance().getHandVos().get(prePosition);
        HandWriteActivity.startActivity(this, preHandVo, prePosition, false);
        finish();
    }

    private boolean checkIsModified() {
        if (isNew) {
            return true;
        }

        if (isSaved) {
            return false;
        }

        if (!mPbEquipment.getValue().equals(mHandVo.getWeizhi1())) {
            return true;
        }
        if (!mPbArea.getValue().equals(mHandVo.getWeizhi3())) {
            return true;
        }
        if (!mPbTagNumber.getValue().equals(mHandVo.getBiaoqian())) {
            return true;
        }
        if (!mPbExtensionNumber.getValue().equals(mHandVo.getKuozhanhao())) {
            return true;
        }
        if (!mPbFlagNumber.getValue().equals(mHandVo.getChanpinliu() + "")) {
            return true;
        }
        if (!mPbPicNumber.getValue().equals(mHandVo.getTuhao())) {
            return true;
        }
        if (!mPbMainReference.getValue().equals(mHandVo.getZhuyaocankaowu())) {
            return true;
        }
        if (!mPbOrientation.getValue().equals(mHandVo.getFangxiang())) {
            return true;
        }
        if (!mPbDistance.getValue().equals(mHandVo.getJuli() + "")) {
            return true;
        }
        if (!mPbFloor.getValue().equals(mHandVo.getLouceng() + "")) {
            return true;
        }
        if (!mPbHeight.getValue().equals(mHandVo.getGaodu() + "")) {
            return true;
        }
        if (!mPbUnitType.getValue().equals(mHandVo.getZujianleixing())) {
            return true;
        }
        if (!mPbUnitSubType.getValue().equals(mHandVo.getZujianzileixing())) {
            return true;
        }
        if (!mPbSize.getValue().equals(mHandVo.getChicun() + "")) {
            return true;
        }
        if (!mPbMediumStates.getValue().equals(mHandVo.getJiezhizhuangtai())) {
            return true;
        }
        if (!mPbHigh.getValue().equals(mHandVo.getNanyichuji())) {
            return true;
        }
        if (!mPbNotArrive.getValue().equals(mHandVo.getNanyujiance())) {
            return true;
        }
        if (!mPbPreserve.getValue().equals(mHandVo.getZujianbeijueyuan())) {
            return true;
        }
        if (!mPbExtension.getValue().equals(mHandVo.getKuozhan())) {
            return true;
        }
        return false;
    }

    private void selectMediumStates() {
        DialogUtils.showDialogList(AppConstants.MEDIUM_STATES, "请选择介质状态", this, new DialogUtils.DialogListOnItem() {
            @Override
            public void onItem(String data) {
                mPbMediumStates.setValue(data);
            }
        });
    }

    private void selectUnitSubType() {
        String unitType = mPbUnitType.getValue();
        if (TextUtils.isEmpty(unitType)) {
            T.showShort(this, "清先选择组件类型");
            return;
        }
        String[] subTypes = AppConstants.UNIT.get(unitType);
        DialogUtils.showDialogList(subTypes, "请选择组件子类型", this, new DialogUtils.DialogListOnItem() {
            @Override
            public void onItem(String data) {
                mPbUnitSubType.setValue(data);
            }
        });
    }

    private void selectUnitType() {
        DialogUtils.showDialogList(AppConstants.UNIT_TYPES, "请选择组件类型", this, new DialogUtils.DialogListOnItem() {
            @Override
            public void onItem(String data) {
                mPbUnitType.setValue(data);
                mPbUnitSubType.setValue("");
            }
        });
    }

    private void selectYesOrNo(View view) {
        final ParamsBlock pb = (ParamsBlock) view;
        DialogUtils.showDialogList(AppConstants.YES_OR_NO, AppConstants.YES_OR_NO_VO, "请选择", this, new DialogUtils.DialogListOnItem() {
            @Override
            public void onItem(String data) {
                pb.setValue(data);
            }
        });
    }

    private void selectOrientation() {
        DialogUtils.showDialogList(AppConstants.ORIENTATION, "请选择方向", this, new DialogUtils.DialogListOnItem() {
            @Override
            public void onItem(String data) {
                mPbOrientation.setValue(data);
            }
        });
    }

    private void navigateToExtension() {
        if (TextUtils.isEmpty(mPbSize.getValue())) {
            T.showShort(this, "请先填写尺寸");
            return;
        }
        int size = Integer.parseInt(mPbSize.getValue());
        mHandVo.setChicun(size);
        ExtensionActivity.startActivity(this, mHandVo.getKuozhan(), mHandVo.getChicun(), REQUEST_EXTENSION);
    }

    private void save() {
        if (TextUtils.isEmpty(mPbUnitSubType.getValue())) {
            T.showShort(this, "组件子类型不能为空,请选择");
            return;
        }
        showDialog("正在保存任务");
        fillData();
        if (mOldExtensions.size() > 0) {
            HandVo startExtension = mOldExtensions.get(0);
            HandVo endExtension = mOldExtensions.get(mOldExtensions.size() - 1);
            final ExcelParser excelParser = ExcelParser.getInstance();
            excelParser.removeRow(startExtension.getPath(), startExtension.getIndex(), startExtension.getRow(), endExtension.getRow(),
                    new Result() {
                        @Override
                        public void onSucceed() {
//                                T.showShort(HandWriteActivity.this, "保存数据成功");
                            List<HandVo> newExtensions = mHandVo.getExtensions();
                            int difference = newExtensions.size() - mOldExtensions.size();
                            //更新之后的所有行
                            if (difference != 0) {
                                MyApplication.getInstance().shiftRow(mPosition + 1, difference);
                            }
                            insertNewExtension(excelParser, newExtensions);
                        }

                        @Override
                        public void onError(int code, Exception e) {
                            hideDialog();
                            T.showShort(HandWriteActivity.this, "保存数据异常,请退出后重试");
                        }
                    });

        }
        //原先的扩展个数为0
        else {
            List<HandVo> newExtensions = mHandVo.getExtensions();
            int difference = newExtensions.size();
            //更新之后的所有行
            if (difference != 0) {
                MyApplication.getInstance().shiftRow(mPosition + 1, difference);
            }
            insertNewExtension(ExcelParser.getInstance(), newExtensions);
        }
    }

    private void fillData() {
        mHandVo.setWeizhi1(mPbEquipment.getValue());
        mHandVo.setWeizhi3(mPbArea.getValue());
        mHandVo.setChanpinliu(Integer.parseInt(mPbFlagNumber.getValue()));
        mHandVo.setTuhao(mPbPicNumber.getValue());
        mHandVo.setZhuyaocankaowu(mPbMainReference.getValue());
        mHandVo.setFangxiang(mPbOrientation.getValue());
        mHandVo.setJuli(Double.parseDouble(mPbDistance.getValue()));
        mHandVo.setLouceng(Integer.parseInt(mPbFloor.getValue()));
        mHandVo.setGaodu(Double.parseDouble(mPbHeight.getValue()));
        mHandVo.setZujianleixing(mPbUnitType.getValue());
        mHandVo.setZujianzileixing(mPbUnitSubType.getValue());
        mHandVo.setChicun(Integer.parseInt(mPbSize.getValue()));
        mHandVo.setFujiamiaoshu(mPbExtraDes.getRelativeValue() + mPbExtraDes.getValue());
        mHandVo.setJiezhizhuangtai(mPbMediumStates.getValue());
        mHandVo.setNanyichuji(mPbHigh.getValue());
        mHandVo.setNanyujiance(mPbNotArrive.getValue());
        mHandVo.setZujianbeijueyuan(mPbPreserve.getValue());

        for (HandVo handVo : mHandVo.getExtensions()) {
            Mapper.transformToExtensions(handVo, mHandVo);
        }
    }

    private void insertNewExtension(final ExcelParser excelParser, List<HandVo> newExtensions) {
        List<ParserTarget> hands = Mapper.transformToModelList(newExtensions);
        excelParser.insertRow(hands, new Result() {
            @Override
            public void onSucceed() {
                saveMajor(excelParser);
            }

            @Override
            public void onError(int code, Exception e) {
                hideDialog();
                T.showShort(HandWriteActivity.this, "保存数据异常,请退出后重试");
            }
        });
    }

    private void saveMajor(ExcelParser excelParser) {
        Hand hand = Mapper.transformToModel(mHandVo);
        excelParser.saveToFile(hand, new Result() {
            @Override
            public void onSucceed() {
                isNew = false;
                isSaved = true;
                hideDialog();
                T.showShort(HandWriteActivity.this, "保存数据成功");
            }

            @Override
            public void onError(int code, Exception e) {
                hideDialog();
                T.showShort(HandWriteActivity.this, "保存数据异常,请退出后重试");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_EXTENSION:
                    ArrayList<Extension> extensions = (ArrayList<Extension>) data.getSerializableExtra(EXTENSION_LIST);
                    processExtensions(extensions);
                    isModifyExtensions = true;
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (checkIsModified()) {
                T.showShort(this, "记录已修改, 请保存后再离开");
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void processExtensions(ArrayList<Extension> extensions) {
        String extensionPrompt = Mapper.transformFromExtension(extensions, mHandVo);
        mPbExtension.setValue(extensionPrompt);
    }
}
