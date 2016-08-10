package com.yanxinwei.exceloperator.targetmodel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * Created by yanxinwei on 16/7/3.
 */
public class HandVo implements Serializable, Cloneable{

    private String weizhi1 = "";
    private String weizhi3 = "";
    private String biaoqian = "";
    private String kuozhanhao = "";
    private String tuhao = "";
    private String zhuyaocankaowu = "";
    private String fangxiang = "";
    private double juli = -1;
    private String danwei1 = "";
    private double louceng;
    private double gaodu = -1;
    private String danwei2 = "";
    private String fujiamiaoshu = "";
    private String zujianleixing = "";
    private String zujianzileixing = "";
    private int chicun;
    private String jiezhizhuangtai = "";
    private int chanpinliu;
    private String tianjiariqi = "";
    private String nanyichuji = "";
    private String nanyujiance = "";
    private String nanyujianceyuanyin = "";
    private String zujianbeijueyuan = "";
    private String kuozhan = "";
    private int konglengsiduRow = -1;

    private ArrayList<HandVo> mExtensions;
    private int mRow;
    private String mPath;
    private int mIndex;

    public String getWeizhi1() {
        return weizhi1;
    }

    public void setWeizhi1(String weizhi1) {
        this.weizhi1 = weizhi1;
    }

    public String getWeizhi3() {
        return weizhi3;
    }

    public void setWeizhi3(String weizhi3) {
        this.weizhi3 = weizhi3;
    }

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }

    public String getKuozhanhao() {
        return kuozhanhao;
    }

    public void setKuozhanhao(String kuozhanhao) {
        this.kuozhanhao = kuozhanhao;
    }

    public String getTuhao() {
        return tuhao;
    }

    public void setTuhao(String tuhao) {
        this.tuhao = tuhao;
    }

    public String getZhuyaocankaowu() {
        return zhuyaocankaowu;
    }

    public void setZhuyaocankaowu(String zhuyaocankaowu) {
        this.zhuyaocankaowu = zhuyaocankaowu;
    }

    public String getFangxiang() {
        return fangxiang;
    }

    public void setFangxiang(String fangxiang) {
        this.fangxiang = fangxiang;
    }

    public double getJuli() {
        return juli;
    }

    public void setJuli(double juli) {
        this.juli = juli;
    }

    public double getLouceng() {
        return louceng;
    }

    public void setLouceng(double louceng) {
        this.louceng = louceng;
    }

    public double getGaodu() {
        return gaodu;
    }

    public void setGaodu(double gaodu) {
        this.gaodu = gaodu;
    }

    public String getFujiamiaoshu() {
        return fujiamiaoshu;
    }

    public void setFujiamiaoshu(String fujiamiaoshu) {
        this.fujiamiaoshu = fujiamiaoshu;
    }

    public String getZujianleixing() {
        return zujianleixing;
    }

    public void setZujianleixing(String zujianleixing) {
        this.zujianleixing = zujianleixing;
    }

    public String getZujianzileixing() {
        return zujianzileixing;
    }

    public void setZujianzileixing(String zujianzileixing) {
        this.zujianzileixing = zujianzileixing;
    }

    public int getChicun() {
        return chicun;
    }

    public void setChicun(int chicun) {
        this.chicun = chicun;
    }

    public String getJiezhizhuangtai() {
        return jiezhizhuangtai;
    }

    public void setJiezhizhuangtai(String jiezhizhuangtai) {
        this.jiezhizhuangtai = jiezhizhuangtai;
    }

    public int getChanpinliu() {
        return chanpinliu;
    }

    public void setChanpinliu(int chanpinliu) {
        this.chanpinliu = chanpinliu;
    }

    public String getTianjiariqi() {
        return tianjiariqi;
    }

    public void setTianjiariqi(String tianjiariqi) {
        this.tianjiariqi = tianjiariqi;
    }

    public String getNanyichuji() {
        return nanyichuji;
    }

    public void setNanyichuji(String nanyichuji) {
        this.nanyichuji = nanyichuji;
    }

    public String getNanyujiance() {
        return nanyujiance;
    }

    public void setNanyujiance(String nanyujiance) {
        this.nanyujiance = nanyujiance;
    }

    public String getNanyujianceyuanyin() {
        return nanyujianceyuanyin;
    }

    public void setNanyujianceyuanyin(String nanyujianceyuanyin) {
        this.nanyujianceyuanyin = nanyujianceyuanyin;
    }

    public String getZujianbeijueyuan() {
        return zujianbeijueyuan;
    }

    public void setZujianbeijueyuan(String zujianbeijueyuan) {
        this.zujianbeijueyuan = zujianbeijueyuan;
    }

    public String getKuozhan() {
        return kuozhan;
    }

    public void setKuozhan(String kuozhan) {
        this.kuozhan = kuozhan;
    }

    public ArrayList<HandVo> getExtensions() {
        return mExtensions;
    }

    public void setExtensions(ArrayList<HandVo> extensions) {
        mExtensions = extensions;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        mRow = row;
    }

    public void addRow(int add) {
        mRow += add;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        this.mPath = path;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public String getDanwei1() {
        return danwei1;
    }

    public void setDanwei1(String danwei1) {
        this.danwei1 = danwei1;
    }

    public String getDanwei2() {
        return danwei2;
    }

    public void setDanwei2(String danwei2) {
        this.danwei2 = danwei2;
    }

    public int getKonglengsiduRow() {
        return konglengsiduRow;
    }

    public void setKonglengsiduRow(int konglengsiduRow) {
        this.konglengsiduRow = konglengsiduRow;
    }

    @Override
    public HandVo clone() {
        try {
            HandVo handVo = (HandVo) super.clone();
            handVo.setExtensions(null);
            return handVo;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
