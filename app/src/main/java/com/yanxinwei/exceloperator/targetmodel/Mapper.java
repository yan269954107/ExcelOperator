package com.yanxinwei.exceloperator.targetmodel;

import android.text.TextUtils;

import com.generator.excelparser.ExcelUtils;
import com.generator.targetmodel.ParserTarget;
import com.yanxinwei.exceloperator.common.AppConstants;
import com.yanxinwei.exceloperator.common.Utils;
import com.yanxinwei.exceloperator.targetmodel.model.UnitInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanxinwei on 16/7/3.
 */
public class Mapper {

    private static final String FINANCIAL_TEST_REASON = "超出触及范围2米";
    private static final String MAJOR_EXTENSIONS_NUMBER = "000";
    private static final String FIRST_LABEL_NUMBER = "0001";

    public static ArrayList<HandVo> transformToVos(List<ParserTarget> hands) {
        ArrayList<HandVo> handVos = new ArrayList<>();
        ArrayList<HandVo> extensions = new ArrayList<>();
        String tag = null;
        HandVo majorHandVo = new HandVo();
        for (ParserTarget target : hands) {
            Hand hand = (Hand) target;
            if (!hand.get_Ad_biaoqian().equals(tag)) {
                //不是第一个
                if (tag != null) {
                    majorHandVo.setExtensions(extensions);
                    majorHandVo.setKuozhan(getExtension(majorHandVo, extensions));
                }
                tag = hand.get_Ad_biaoqian();
                majorHandVo = transformToVo(hand);
                handVos.add(majorHandVo);
                extensions = new ArrayList<>();
            }
            //标签号相等,为扩展
            else {
                HandVo handVo = transformToVo(hand);
                extensions.add(handVo);
            }
        }
        majorHandVo.setExtensions(extensions);
        majorHandVo.setKuozhan(getExtension(majorHandVo, extensions));
        return handVos;
    }

    private static String getExtension(HandVo major, List<HandVo> extensions) {
        if (extensions == null || extensions.size() == 0) return "";
        StringBuilder sb = new StringBuilder();
        String symbol = "init";
        int count = 1;
        int size = 0;
        for (HandVo handVo : extensions) {
            if (symbol.equals(AppConstants.EXTENSION_SYMBOL.get(handVo.getZujianzileixing()))
                    && handVo.getChicun() == size) {
                count++;
            } else {
                if (!symbol.equals("init")) {
                    sb.append(getSingleExtension(count, symbol, size, size == major.getChicun()));
                    sb.append("+");
                }
                count = 1;
                symbol = AppConstants.EXTENSION_SYMBOL.get(handVo.getZujianzileixing());
                size = handVo.getChicun();
            }
        }
        sb.append(getSingleExtension(count, symbol, size, size == major.getChicun()));
        return sb.toString();
    }

    private static String getSingleExtension(int count, String symbol, int size, boolean isEqual) {
        if (count == 1) {
            return getExtensionBySize(symbol, size, isEqual);
        } else {
            return getExtensionBySize(count + symbol, size, isEqual);
        }
    }

    private static String getExtensionBySize(String extension, int size, boolean isEqual) {
        if (isEqual) {
            return extension;
        } else {
            return extension + "(" + size + ")";
        }
    }

    public static HandVo transformToVo(Hand hand) {
        HandVo handVo = new HandVo();
        handVo.setBiaoqian(hand.get_Ad_biaoqian());
        handVo.setChanpinliu(hand.get_Aw_chanpinliu());
        handVo.setChicun(hand.get_Au_chicun());
        handVo.setExtensions(null);
        handVo.setFangxiang(hand.get_Ah_fangxiang());
        handVo.setFujiamiaoshu(hand.get_Ar_fujiamiaoshu());
        handVo.setGaodu(hand.get_Ap_gaodu());
        handVo.setJiezhizhuangtai(hand.get_Av_jiezhizhuangtai());
        handVo.setJuli(hand.get_Ai_juli());
        handVo.setKuozhanhao(hand.get_Ae_kuozhanhao());
        handVo.setLouceng(hand.get_Ao_louceng());
        handVo.setNanyichuji(hand.get_Bd_nanyichuji());
        handVo.setNanyujiance(hand.get_Be_nanyujiance());
        handVo.setTuhao(hand.get_Af_tuhao());
        handVo.setWeizhi1(hand.get_Aa_weizhi1());
        handVo.setWeizhi3(hand.get_Ac_weizhi3());
        handVo.setZhuyaocankaowu(hand.get_Ag_zhuyaocankaowu());
        handVo.setZujianbeijueyuan(hand.get_Bo_zujianbeijueyuan());
        handVo.setZujianleixing(hand.get_As_zujianleixing());
        handVo.setZujianzileixing(hand.get_At_zujianzileixing());
        handVo.setRow(hand.getRow());
        handVo.setPath(hand.getPath());
        handVo.setDanwei1(hand.get_Aj_danwei());
        handVo.setDanwei2(hand.get_Aq_danwei());
        return handVo;
    }

    public static Hand transformToModel(HandVo handVo) {
        Hand hand = new Hand();
        hand.setAd_biaoqian(handVo.getBiaoqian());
        hand.setAw_chanpinliu(handVo.getChanpinliu());
        hand.setAu_chicun(handVo.getChicun());
        hand.setAh_fangxiang(handVo.getFangxiang());
        hand.setAr_fujiamiaoshu(handVo.getFujiamiaoshu());
        hand.setAp_gaodu(handVo.getGaodu());
        hand.setAv_jiezhizhuangtai(handVo.getJiezhizhuangtai());
        hand.setAi_juli(handVo.getJuli());
        hand.setAe_kuozhanhao(handVo.getKuozhanhao());
        hand.setAo_louceng(handVo.getLouceng());
        hand.setBd_nanyichuji(handVo.getNanyichuji());
        hand.setBe_nanyujiance(handVo.getNanyujiance());
        if (!TextUtils.isEmpty(handVo.getNanyujiance()) && "是".equals(handVo.getNanyujiance())) {
            hand.setBf_nanyujianceyuanyin(FINANCIAL_TEST_REASON);
        }
        hand.setAf_tuhao(handVo.getTuhao());
        hand.setAa_weizhi1(handVo.getWeizhi1());
        hand.setAc_weizhi3(handVo.getWeizhi3());
        hand.setAg_zhuyaocankaowu(handVo.getZhuyaocankaowu());
        hand.setBo_zujianbeijueyuan(handVo.getZujianbeijueyuan());
        hand.setAs_zujianleixing(handVo.getZujianleixing());
        hand.setAt_zujianzileixing(handVo.getZujianzileixing());
        hand.setRow(handVo.getRow());
        hand.setPath(handVo.getPath());
        hand.setAj_danwei(handVo.getDanwei1());
        hand.setAq_danwei(handVo.getDanwei2());
        hand.setBc_tianjiariqi(ExcelUtils.date2.format(new Date()));
        return hand;
    }

    public static List<ParserTarget> transformToModelList(List<HandVo> handVos) {
        List<ParserTarget> hands = new ArrayList<>();
        for (HandVo handVo : handVos) {
            Hand hand = transformToModel(handVo);
            hands.add(hand);
        }
        return hands;
    }

    public static Extension parseExtension(String strExtension, int majorSize) {
        int countIndex = -1;
        int symbolEndIndex = -1;
        int length = strExtension.length();
        for (int i = 0; i < length; i++) {
            if (strExtension.charAt(i) >= 'A' && strExtension.charAt(i) <= 'Z' && countIndex == -1) {
                countIndex = i;
            }
            if (strExtension.charAt(i) == '(' && symbolEndIndex == -1) {
                symbolEndIndex = i;
                break;
            }
        }
        if (symbolEndIndex == -1) {
            symbolEndIndex = length;
        }
        Extension extension = new Extension();
        if (countIndex == 0) {
            extension.setCount(1);
        } else {
            extension.setCount(Integer.parseInt(strExtension.substring(0, countIndex)));
        }
        extension.setSymbol(strExtension.substring(countIndex, symbolEndIndex));
        if (symbolEndIndex < length) {
            extension.setSize(Integer.parseInt(strExtension.substring(symbolEndIndex + 1, length - 1)));
        } else {
            extension.setSize(majorSize);
        }
        return extension;
    }

    public static String transformFromExtension(ArrayList<Extension> extensions, HandVo major) {
        ArrayList<HandVo> extensionList = new ArrayList<>();
        int index = 0;
        for (Extension extension : extensions) {
            int count = extension.getCount();
            for (int i = 0; i < count; i++) {
                HandVo handVo = major.clone();
                handVo.setKuozhan("");
                handVo.setChicun(extension.getSize());
                UnitInfo unitInfo = AppConstants.UNIT_TYPE_MATCH_SYMBOL.get(extension.getSymbol());
                handVo.setZujianleixing(unitInfo.getUnitType());
                handVo.setZujianzileixing(unitInfo.getUnitSubType());
                index++;
                handVo.addRow(index);
                handVo.setKuozhanhao(getExtensionNumber(index));
                extensionList.add(handVo);
            }
        }
        major.setExtensions(extensionList);
        String extensionPrompt = getExtension(major, extensionList);
        major.setKuozhan(extensionPrompt);
        return extensionPrompt;
    }

    public static void transformToExtensions(HandVo extension, HandVo major) {
        extension.setBiaoqian(major.getBiaoqian());
        extension.setChanpinliu(major.getChanpinliu());
        extension.setFangxiang(major.getFangxiang());
        extension.setFujiamiaoshu(major.getFujiamiaoshu());
        extension.setGaodu(major.getGaodu());
        extension.setJiezhizhuangtai(major.getJiezhizhuangtai());
        extension.setJuli(major.getJuli());
        extension.setLouceng(major.getLouceng());
        extension.setNanyichuji(major.getNanyichuji());
        extension.setNanyujiance(major.getNanyujiance());
        extension.setTuhao(major.getTuhao());
        extension.setWeizhi1(major.getWeizhi1());
        extension.setWeizhi3(major.getWeizhi3());
        extension.setZhuyaocankaowu(major.getZhuyaocankaowu());
        extension.setZujianbeijueyuan(major.getZujianbeijueyuan());
    }

    public static HandVo getNewNextHandVo(HandVo template) {
        HandVo handVo = new HandVo();
        handVo.setWeizhi1(template.getWeizhi1());
        handVo.setWeizhi3(template.getWeizhi3());
        handVo.setChanpinliu(template.getChanpinliu());
        handVo.setTuhao(template.getTuhao());
        handVo.setZhuyaocankaowu(template.getZhuyaocankaowu());
        handVo.setFangxiang(template.getFangxiang());
        handVo.setLouceng(template.getLouceng());
        handVo.setJiezhizhuangtai(template.getJiezhizhuangtai());
        handVo.setPath(template.getPath());
        int nextRow;
        List<HandVo> extensions = template.getExtensions();
        if (null == extensions) {
            nextRow = template.getRow() + 1;
        } else {
            nextRow = template.getRow() + extensions.size() + 1;
        }
        handVo.setRow(nextRow);
        handVo.setIndex(template.getIndex());
        handVo.setExtensions(new ArrayList<HandVo>());
        handVo.setKuozhanhao(MAJOR_EXTENSIONS_NUMBER);
        handVo.setBiaoqian(Utils.getLabelNumber(template.getBiaoqian()));
        handVo.setFujiamiaoshu(template.getFujiamiaoshu());
        return handVo;
    }

    private static String getExtensionNumber(int index) {
        if (index < 10) {
            return "00" + index;
        } else if (index < 100) {
            return "0" + index;
        } else {
            return "" + index;
        }
    }

    public static HandVo getFirstHandVo(String path) {
        HandVo handVo = new HandVo();
        handVo.setPath(path);
        handVo.setRow(1);
        handVo.setIndex(0);
        handVo.setExtensions(new ArrayList<HandVo>());
        handVo.setKuozhanhao(MAJOR_EXTENSIONS_NUMBER);
        handVo.setBiaoqian(FIRST_LABEL_NUMBER);
        return handVo;
    }
}
