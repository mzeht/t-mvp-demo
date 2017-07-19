package com.wpmac.tmvpdemo.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;


public class BasePreference extends Preference {
    private static BasePreference mBasePreference;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    //数据库名称
    private String SP_NAME = "BAIMI";
    //
    //员工编号
    public static final String EMPLOYEE_NO = "EMPLOYEE_NO";
    //网点编号
    public static final String POINT_NO = "POINT_NO";
    //是否登录
    public static final String IS_LOGINED = "IS_LOGINED";
    //默认登录名称
    public static final String DEFALUT_NAME = "DEFALUT_NAME";
    //服务器地址
    public static final String DEFALUT_SERVER = "DEFALUT_SERVER";
    //默认分页大小
    public static final String DEFALUT_PAGE_SIZE = "DEFALUT_PAGE_SIZE";
    //默认操作模式是否是手机
    public static final String IS_MOBILE = "IS_MOBILE";

    //"area":"江干区","address":"杭州电子科技大学下沙校区","province":"浙江省","city":"杭州市","pointNo":"20161101141426539844989653"
    public static final String PROVINCE = "PROVINCE";
    public static final String CITY = "CITY";
    public static final String ADDRESS = "ADDRESS";
    public static final String AREA = "AREA";

    public void saveProvice(String s){
        editor.putString(PROVINCE, s).commit();

    }

    public String getPROVINCE() {
        return sp.getString(PROVINCE, "");
    }

    public void saveCity(String s){
        editor.putString(CITY, s).commit();
    }

    public String getCITY() {
        return sp.getString(CITY, "");
    }

    public void saveAddress(String s){
        editor.putString(ADDRESS, s).commit();

    }

    public String getADDRESS() {
        return sp.getString(ADDRESS,"");
    }


    public void saveArea(String s){
        editor.putString(AREA, s).commit();
    }

    public String getAREA() {
        return sp.getString(AREA,"");
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static BasePreference getInstance() {
        return mBasePreference;
    }

    /**
     * 构造函数
     *
     * @param context
     */
    private BasePreference(Context context) {
        super(context);
        mBasePreference = this;
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * 单例初始化
     *
     * @param paramContext
     */
    public static void initialize(Context paramContext) {

        if (mBasePreference == null) {
            mBasePreference = new BasePreference(paramContext);
        }

    }


    public boolean isMobile() {
        return sp.getBoolean(IS_MOBILE, true);
    }

    public void saveIsMobile(boolean flag) {
        editor.putBoolean(IS_MOBILE, flag).commit();
    }

    /**
     * 获取分页大小
     *
     * @return
     */
    public int getPageSize() {
        return sp.getInt(DEFALUT_PAGE_SIZE, 10);
    }

    /**
     * 保存EMPLOYEE_NO
     *
     * @param
     */
    public void saveEmployeeNo(String s) {
        editor.putString(EMPLOYEE_NO, s).commit();

    }

    /**
     * 获取EMPLOYEE_NO
     *
     * @return
     */
    public String getEmployeeNo() {
        return sp.getString(EMPLOYEE_NO, "");
    }

    /**
     * 保存POINT_NO
     *
     * @param
     */
    public void savePointNo(String s) {
        editor.putString(POINT_NO, s);
        editor.commit();
    }

    /**
     * 获取POINT_NO
     *
     * @return
     */
    public String getPointNo() {
        return sp.getString(POINT_NO, "");
    }

    /**
     * IS_LOGINED
     *
     * @param
     */
    public void saveIsLogined(boolean s) {
        editor.putBoolean(IS_LOGINED, s);
        editor.commit();
    }

    /**
     * 获取POINT_NO
     *
     * @return
     */
    public boolean getIsLogined() {
        return sp.getBoolean(IS_LOGINED, false);
    }

    /**
     * 默认登录名称
     *
     * @param
     */
    public void saveLoginName(String s) {
        editor.putString(DEFALUT_NAME, s);
        editor.commit();
    }

    /**
     * 获取登录名称
     *
     * @return
     */
    public String getLoginName() {
        return sp.getString(DEFALUT_NAME, "");
    }

    /**
     * 默认登录名称
     *
     * @param
     */
    public void saveServerAddress(String s) {
        editor.putString(DEFALUT_SERVER, s);
        editor.commit();
    }

    /**
     * 获取登录名称
     *
     * @return
     */
    public String getServerAddress() {
        return sp.getString(DEFALUT_SERVER, "");
    }




}
