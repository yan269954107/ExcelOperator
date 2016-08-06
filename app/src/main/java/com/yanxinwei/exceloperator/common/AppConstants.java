package com.yanxinwei.exceloperator.common;

import android.os.Environment;

import com.yanxinwei.exceloperator.targetmodel.model.UnitInfo;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * Created by yanxinwei on 16/6/1.
 */
public interface AppConstants {

    String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    String TARGETS_PATH = SD_PATH.concat("/Targets");

    String[] ORIENTATION = {"东", "西", "南", "北", "东南", "西南", "东北", "西北", "顶部", "底部", "上方", "下方"};

    String[] YES_OR_NO_VO = {"是", "否"};
    String[] YES_OR_NO = {"是", ""};

    String[] SUB_UNIT_FAMEN = {"闸阀", "针阀", "球阀", "蝶阀", "截止阀", "控制阀"};
    String[] SUB_UNIT_FALA = {"法兰"};
    String[] SUB_UNIT_LIANJIEJIAN = {"丝扣连接", "活接", "管帽", "丝堵", "空冷丝堵"};
    String[] SUB_UNIT_KAIKOUGUANXIAN = {"开口管线"};
    String[] SUB_UNIT_BENG = {"离心泵", "往复泵", "螺杆泵", "真空泵"};
    String[] SUB_UNIT_JIAOBANQI = {"搅拌器"};
    String[] SUB_UNIT_YASUOJI = {"离心式压缩机", "往复式压缩机", "螺杆式压缩机"};
    String[] SUB_UNIT_XIEYAZHUANGZHI = {"安全阀"};
    String[] SUB_UNIT_QUYANGLIANJIEXITONG = {"取样连接系统"};

    String[] UNIT_TYPES = {"阀门", "法兰", "连接件", "开口管线", "泵", "搅拌器", "压缩机", "泄压装置", "取样连接系统"};

    LinkedHashMap<String, String[]> UNIT = new LinkedHashMap<String, String[]>() {{
        put("阀门", SUB_UNIT_FAMEN);
        put("法兰", SUB_UNIT_FALA);
        put("连接件", SUB_UNIT_LIANJIEJIAN);
        put("开口管线", SUB_UNIT_KAIKOUGUANXIAN);
        put("泵", SUB_UNIT_BENG);
        put("搅拌器", SUB_UNIT_JIAOBANQI);
        put("压缩机", SUB_UNIT_YASUOJI);
        put("泄压装置", SUB_UNIT_XIEYAZHUANGZHI);
        put("取样连接系统", SUB_UNIT_QUYANGLIANJIEXITONG);
    }};

    HashMap<String, String> EXTENSION_SYMBOL = new HashMap<String, String>() {{
        put("法兰", "F");
        put("丝扣连接", "S");
        put("活接", "U");
        put("管帽", "CAP");
        put("丝堵", "CP");
        put("开口管线", "OEL");
        put("空冷丝堵", "KP");
        put("多级离心泵", "CF");
        put("闸阀", "GV");
        put("针阀", "NV");
        put("球阀", "BV");
        put("截止阀", "GLV");
    }};

    HashMap<String, UnitInfo> UNIT_TYPE_MATCH_SYMBOL = new HashMap<String, UnitInfo>() {{
        put("F", new UnitInfo("法兰", "法兰"));
        put("S", new UnitInfo("连接件", "丝扣连接"));
        put("U", new UnitInfo("连接件", "活接"));
        put("CAP", new UnitInfo("连接件", "管帽"));
        put("CP", new UnitInfo("连接件", "丝堵"));
        put("OEL", new UnitInfo("开口管线", "开口管线"));
        put("KP", new UnitInfo("连接件", "空冷丝堵"));
        put("CF", new UnitInfo("泵", "多级离心泵"));
        put("GV", new UnitInfo("阀门", "闸阀"));
        put("NV", new UnitInfo("阀门", "针阀"));
        put("BV", new UnitInfo("阀门", "球阀"));
        put("GLV", new UnitInfo("阀门", "截止阀"));
    }};

    String[] STR_EXTENSION_SYMBOL = {"F","S","U","CAP","CP","OEL","KP","CF","GV","NV","BV","GLV"};
    String[] STR_EXTENSION_FULL = {"法兰 F","丝扣连接 S","活接 U","管帽 CAP","丝堵 CP","开口管线 OEL",
            "空冷丝堵 KP","多级离心泵CF","闸阀GV","针阀 NV","球阀 BV","截止阀 GLV"};

    String[] MEDIUM_STATES = {"轻液", "重液", "气体/蒸汽"};

    String[] P_BENG = {"本体", "本体导淋", "本体放空", "进口阀", "进口过滤器", "进口导淋", "进口支路阀", "出口阀", "出口回流线阀", "出口导淋", "出口压力表", "出口压力表阀", "出口止回阀", "出口支路阀", "进出口副线", "自冲洗线", "平衡线", "压力表阀", "排污线视镜", "封液罐进口阀", "封液罐出口阀", "封液罐出口温度表", "封液罐下导淋", "封液罐补液泵球阀", "封液罐补液泵本体", "封液罐填料阀", "封液罐出口压力表", "封液罐液位计", "封液罐放空线闸阀", "封液罐放空止回阀"};
    String[] P_BENG_FA = {"本体导淋", "本体放空", "进口阀", "进口导淋", "进口支路阀", "出口阀", "出口回流线阀", "出口导淋", "出口压力表阀", "出口支路阀", "进出口副线", "压力表阀", "封液罐进口阀", "封液罐出口阀", "封液罐下导淋", "封液罐补液泵球阀", "封液罐填料阀", "封液罐放空线闸阀"};

    String[] D_V_GUAN = {"进口阀", "进口法兰", "出口阀", "出口法兰", "人孔", "放空", "热电偶", "温度表", "压力表", "液位计上控制", "液位计上调节", "液位计上放空", "液位计本体", "液位计下控制", "液位计下调节", "液位计下导淋", "液位变送器上控制", "液位变送器上导淋", "液位变送器下控制", "液位变送器下导淋", "放空法兰", "引压阀", "切水阀", "取样阀", "排污阀", "预留口", "安全阀后手阀", "安全阀", "安全阀前手阀", "安全阀副线阀"};
    String[] D_V_GUAN_FA = {"进口阀", "出口阀", "放空", "液位计上控制", "液位计上调节", "液位计上放空", "液位计下控制", "液位计下调节", "液位计下导淋", "液位变送器上控制", "液位变送器上导淋", "液位变送器下控制", "液位变送器下导淋", "引压阀", "切水阀", "取样阀", "排污阀", "安全阀后手阀", "安全阀前手阀", "安全阀副线阀"};

    String[] E_HUANREQI = {"封头法兰", "管程进口法兰", "管程出口法兰", "管程进口阀", "管程出口阀", "管程出口导淋", "壳程进口法兰", "壳程出口法兰", "壳程进口阀", "壳程进口导淋", "壳程出口阀", "壳程出口副线", "壳程出口导淋", "出口温度表", "出料线热电偶", "预留口", "副线阀", "连接法兰", "丝堵", "进出口跨线", "吹扫阀"};
    String[] E_HUANREQI_FA = {"管程进口阀", "管程出口阀", "管程出口导淋", "壳程进口阀", "壳程进口导淋", "壳程出口阀", "壳程出口副线", "壳程出口导淋", "副线阀", "进出口跨线", "吹扫阀"};

    String[] C_T_R = {"人孔", "热电偶", "温度表", "进口法兰", "出口法兰", "出口热电偶", "进口阀", "出口阀", "出口副线阀", "出口支路阀", "压力表", "放空", "吹扫阀", "液位计上控制", "液位计上调节", "液位计上放空", "液位计本体", "液位计下控制", "液位计下调节", "液位计下导淋", "液位变送器", "液位变送器上控制", "液位变送器下控制", "安全阀后手阀", "安全阀", "安全阀前手阀", "安全阀副线阀", "隔离液阀"};
    String[] C_T_R_FA = {"进口阀", "出口阀", "出口副线阀", "出口支路阀", "放空", "吹扫阀", "液位计上控制", "液位计上调节", "液位计上放空", "液位计下控制", "液位计下调节", "液位计下导淋", "液位变送器上控制", "液位变送器下控制", "安全阀后手阀", "安全阀前手阀", "安全阀副线阀", "隔离液阀"};

    String[] LV_FT_FV_PV_PT = {"前手阀", "本体", "后手阀", "副线阀", "导淋", "三阀组", "引压法兰", "引压阀", "引压缓冲罐", "放空阀", "隔离液控制阀", "隔离液止回阀", "控制阀", "采样阀", "活接"};
    String[] LV_FT_FV_PV_PT_FA = {"前手阀", "后手阀", "副线阀", "导淋", "三阀组", "引压阀", "放空阀", "隔离液控制阀", "控制阀", "采样阀"};

    String[] F = {"火嘴瓦斯线", "火嘴长明灯", "燃烧器瓦斯线", "燃烧器长明灯", "长明灯控制阀", "瓦斯线控制阀", "进口阀", "进口法兰", "出口阀", "出口法兰"};
    String[] F_FA = {"长明灯控制阀", "瓦斯线控制阀", "进口阀", "出口阀"};

    String[] EC_A = {"进口阀", "进口法兰", "出口阀", "出口导淋", "出口法兰", "空冷丝堵", "丝堵"};
    String[] EC_A_FA = {"进口阀", "出口阀", "出口导淋"};

    String[] PSV = {"后手阀", "本体", "前手阀", "副线阀"};
    String[] PSV_FA = {"后手阀", "前手阀", "副线阀"};

    HashMap<String, String[]> EXTRA_DES = new HashMap<String, String[]>(){{
        put("p", P_BENG);
        put("pf", P_BENG_FA);
        put("d", D_V_GUAN);
        put("df", D_V_GUAN_FA);
        put("v", D_V_GUAN);
        put("vf", D_V_GUAN_FA);
        put("e", E_HUANREQI);
        put("ef", E_HUANREQI_FA);
        put("c", C_T_R);
        put("t", C_T_R);
        put("r", C_T_R);
        put("cf", C_T_R_FA);
        put("tf", C_T_R_FA);
        put("rf", C_T_R_FA);
        put("lv", LV_FT_FV_PV_PT);
        put("ft", LV_FT_FV_PV_PT);
        put("fv", LV_FT_FV_PV_PT);
        put("pv", LV_FT_FV_PV_PT);
        put("pt", LV_FT_FV_PV_PT);
        put("lvf", LV_FT_FV_PV_PT_FA);
        put("ftf", LV_FT_FV_PV_PT_FA);
        put("fvf", LV_FT_FV_PV_PT_FA);
        put("pvf", LV_FT_FV_PV_PT_FA);
        put("ptf", LV_FT_FV_PV_PT_FA);
        put("f", F);
        put("ff", F_FA);
        put("ec", EC_A);
        put("a", EC_A);
        put("ecf", EC_A_FA);
        put("af", EC_A_FA);
        put("psv", PSV);
        put("psvf", PSV_FA);
        //大写
        put("P", P_BENG);
        put("Pf", P_BENG_FA);
        put("D", D_V_GUAN);
        put("Df", D_V_GUAN_FA);
        put("V", D_V_GUAN);
        put("Vf", D_V_GUAN_FA);
        put("E", E_HUANREQI);
        put("Ef", E_HUANREQI_FA);
        put("C", C_T_R);
        put("T", C_T_R);
        put("R", C_T_R);
        put("Cf", C_T_R_FA);
        put("Tf", C_T_R_FA);
        put("Rf", C_T_R_FA);
        put("Lv", LV_FT_FV_PV_PT);
        put("Ft", LV_FT_FV_PV_PT);
        put("Fv", LV_FT_FV_PV_PT);
        put("Pv", LV_FT_FV_PV_PT);
        put("Pt", LV_FT_FV_PV_PT);
        put("LVf", LV_FT_FV_PV_PT_FA);
        put("FTf", LV_FT_FV_PV_PT_FA);
        put("FVf", LV_FT_FV_PV_PT_FA);
        put("PVf", LV_FT_FV_PV_PT_FA);
        put("PTf", LV_FT_FV_PV_PT_FA);
        put("F", F);
        put("Ff", F_FA);
        put("Ec", EC_A);
        put("A", EC_A);
        put("ECf", EC_A_FA);
        put("Af", EC_A_FA);
        put("PSV", PSV);
        put("PSVf", PSV_FA);
    }};

    String[] ALL_SIZE = {"0", "10", "15", "20", "25", "40", "50", "80", "100", "150", "200", "250",
            "300", "350", "400", "500", "600", "800", "1000"};

    String SHAREPRE_USER_NAME = "userName";

    String[] EXTENSION_COUNT = {"1", "2", "3", "4"};

    String[] FLOORS = {"1", "1.5", "2", "2.5", "3", "3.5", "4"};
}
