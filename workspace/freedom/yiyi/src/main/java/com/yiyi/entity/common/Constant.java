package com.yiyi.entity.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 常量
 * 
 * @author wangxin
 * @Date 2015-03-04
 */
public class Constant {

    /******************** xss攻击防注入参数 begin ************************/
    // 出错跳转的目的地
    public final static String XSS_ERROR_PATH = "/WEB-INF/pages/error.jsp";
    // 不进行拦截的url
    public final static String XSS_EXCLUDE_PATHS = "";
    // 需要拦截的JS字符关键字
    public final static String XSS_SAFELESS =
        "<script, </script, <iframe, </iframe, <frame, </frame, set-cookie, %3cscript, %3c/script, %3ciframe, %3c/iframe, %3cframe, %3c/frame, src=\"javascript:, <body, </body, %3cbody, %3c/body, <, >, </, />, %3c, %3e, %3c/, /%3e";
    /******************** xss攻击防注入参数 end ************************/
    // 保存用户信息存入session中的key值
    public final static String LOGIN_USER = "login_user";

    /**成功     */
    public final static String SUCCESS = "0";
    /**
     * 失败
     */
    public final static String FAIL_DEF1 = "1";
    /**
     * 失败扩展
     */
    public final static String FAIL_DEF2 = "2";
    /**
     * 失败扩展
     */
    public final static String FAIL_DEF3 = "3";
    /**
     * 失败扩展
     */
    public final static String FAIL_DEF4 = "4";

    /**
     * 商家基本信息session code
     */
    public final static String BUSI_SESSION_CODE = "BusiSessionCode";

    // 默认分页时
    /** 默认每页显示的记录数 10 */
    public final static int defaultPageSize = 10;

    /** 导出模板对应的key */
    public final static String property_template = "export.property";
    /** 导出模板对应的itgGrant key */
    public final static String property_itgGrant = "export.itgGrant";
    /** 导出模板对应的itgRe key */
    public final static String property_itgRe = "export.itgRe";

    /** 默认当前页 1 */
    public final static int defaultCurrentPage = 1;
    /** 导出模板对应的cat export.cat_edit */
    public final static String cat_edit_template = "export.cat_edit";
    /** 导出模板对应的cat export.cat_check */
    public final static String cat_check_template = "export.cat_check";
    /** 导出模板对应的cut export.ippcut */
    public final static String ippcut_template = "export.ippcut";
    /** 导出模板对应的brand export.ippbrands */
    public final static String ippBrands_template = "export.ippbrands";

    /** 导出模板对应的sovtuuser export.sovtuuser */
    public final static String sovtuuser_template = "export.sovtuuser";
    /** 导出模板对应的ficut export.ficut */
   public final static String ficut_template = "export.ficut";
   /** 导出模板对应的cutacoo export.cutacoo */
   public final static String cutacoo_template = "export.cutacoo";
   /** 导出模板对应的region export.region */ 
   public static final String region_template = "export.region";
   /** 导出模板对应的busiVerify export.busiVerify */ 
   public static final String busiVerify_template = "export.busiVerify";
   /** 导出模板对应的accountManage export.accountManage */ 
   public static final String accountManage_template = "export.accountManage";
   /** 导出模板对应的fiBonus export.fiBonus */ 
   public static final String fiBonus_template = "export.fiBonus";
    

    /** 导出模板对应的brandcat export.ippbrandcat */
    public final static String ippBrandCat_template = "export.ippbrandcat";
    /** 导出模板对应的brandaud export.ippbrandaud */
    public final static String ippBrandAud_template = "export.ippbrandaud";
    

    /** 商家审核状态 */
    public static Map<String, String> VERIFY_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    
    /** IPP审核状态 */
    public static Map<String, String> IVERIFY_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    
    /** 有效01 */
    public final static String STATUS_VALID = "01";
    /** 无效02 */
    public final static String STATUS_INVALID = "02";
    
    
    
    
    /**
     * 数据未删除
     */
    public final static String DELETE_STATUS_0="0";
    
    /**
     * 数据删除
     */
    public final static String DELETE_STATUS_1="1";
    
    /**
     * 数据有效
     */
    public final static String EFFECTIVE_TRUE = "0";
    /**
     * 数据无效
     */
    public final static String EFFECTIVE_FALSE = "1";
    
    
    /**
     * 撤销
     */
    public final static String ILLEGALSTATUS_1 = "1";
    /**
     * 退审
     */
    public final static String ILLEGALSTATUS_2 ="2";
    
    
    /** 状态  01 有效   02无效 */
    public static Map<String, String> STATUS_MAP = new LinkedHashMap<String, String>();// 状态描述信息
    
    /** * 收益设置类型---商品销售*/
    public final static String BONUS_RULL_TYPE = "01";
    /*** 收益设置类型---商品上架*/
    public final static String BONUS_TYPE_SAL = "02";
    /** 收益设置类型  01 商品销售  02商品上架 */
    public static Map<String, String> FI_SAL_MAP = new LinkedHashMap<String, String>();// 收益设置类型描述信息
    
    /*** 收益账户类型为E卡通 */
    public final static String PAY_METHOD_EID = "01";
    /*** 收益账户类型为银行卡*/
    public final static String PAY_METHOD_KID = "02";
    /** 收益账户设置类型  01 银行卡  02 E卡通 */
    public static Map<String, String> PAY_EK_MAP = new LinkedHashMap<String, String>();// 收益设置类型描述信息
    
    /** 编辑中 00 */
    public final static String VERIFY_EDIT = "00";
    /** 待审核 01 */
    public final static String VERIFY_WAIT = "01";
    /** 审核通过 02 */
    public final static String VERIFY_PASS = "02";
    /** 审核不通过 03 */
    public final static String VERIFY_NOPASS = "03";

    /** 区域等级 */
    /** 大区 1 */
    public static final String REGION_BIGAREA = "1";
    /** 省 2 */
    public static final String REGION_PROV = "2";
    /** 市 3 */
    public static final String REGION_CITY = "3";
    /** 区县 4 */
    public static final String REGION_DIS = "4";
    /** 区域等级Map */
    public static Map<String, String> REGION_MAP = new LinkedHashMap<String, String>();//区域等级信息

    
    /** 类目审核类型 */
    /** 审核分类0 */
    public final static String CAT_CHECK_TYPE_CAT = "0";
    /** 审核扣点1 */
    public final static String CAT_CHECK_TYPE_POINT = "1";
    /** 类目等级 */
    /** 一级1 */
    public final static String CAT_LEV_F = "1";
    /** 二级2 */
    public final static String CAT_LEV_S = "2";
    /** 三级3 */
    public final static String CAT_LEV_T = "3";

    /** 删除标识 */
    /** 已删除 1 */
    public final static int IS_DEL_D = 1;
    /** 未删除 0 */
    public final static int IS_DEL_U = 0;
    /** 商品类型dictClsCode 045 */
    //public final static String DICT_CLS_ID_GOODSTYPE = "045";
    /** 商品类型GOODSTYPE_MAP 045 */
    public static Map<String, String> GOODSTYPE_MAP = new LinkedHashMap<String, String>();
    /** 商品类型 :实物商品  01*/
    public final static String GOODSTYPE_GOODS = "01";
    /** 商品类型 :虚拟票券  02*/
    public final static String GOODSTYPE_VIRTUAL = "02";
    /** 商品类型 :充值缴费 03*/
    public final static String GOODSTYPE_RECHARGE = "03";
    
    /** 商品属性类型  PROPERTYTYPE_MAP 045 */
    public static Map<String, String> PROPERTYTYPE_MAP = new LinkedHashMap<String, String>();
    /** 商品类型 :基本属性  0*/
    public final static String PROPERTY_BASE = "0";
    /** 商品类型 :系列属性  1*/
    public final static String PROPERTY_SERIES = "1";

    
    
    
    /** 数据字典积分收取类型（商家） */
    public final static String DICT_CLS_CODE_153 = "153";
    /** 数据字典积分收取类型（主站） */
    public final static String DICT_CLS_CODE_113 = "113";
    /** 数据字典积分发放类型（主站） */
    public final static String DICT_CLS_CODE_116 = "116";
    /** 数据字典积分收取类型（商家） */
    public final static String DICT_CLS_CODE_152 = "152";
    /** 数据字典积分发放说明（商家）下单送积分 */
    public final static String DICT_CLS_ITGTYPE_00 = "00";
    /** 数据字典积分收取说明（商家）订单百分比 */
    public final static String DICT_CLS_ITGTYPE_103 = "103";
    /** 积分状态 */
    public static Map<String, String> ITG_STATUS_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    /** 初始化 01 */
    public final static String ITG_STATUS_INITI = "01";
    /** 待运行 02 */
    public final static String ITG_STATUS_WAIT = "02";
    /** 运行中 03 */
    public final static String ITG_STATUS_RUNNING = "03";
    /** 运行完成 04 */
    public final static String ITG_STATUS_FINNISH = "04";
    /** 作废 05 */
    public final static String ITG_STATUS_INVALID = "05";
    /** 积分来源 */
    public static Map<String, String> ORG_TYPE_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    /** 主站 02 */
    public final static String ORG_TYPE_IPP = "02";
    /** 商家 03 */
    public final static String ORG_TYPE_MERCHANT = "03";

    /*** 收益类型---银行类型*/
    public final static String BANK_TYPE = "021";
    
    /*** 验票渠道---手动验票*/
    public final static String MANUAL_TICKET ="01";
    /*** 验票渠道---轻松购*/
    public final static String EASY_PURCHASE ="02";
    /*** 验票渠道*/
    public static Map<String, String> TICKET_PURCHASE_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    
    /** 商家类型 ---百货类商家*/
    public static final String BU_ORG_TYPE_BAIHUO = "01";
    /** 商家类型 ---购物中心*/
    public static final String BU_ORG_TYPE_SHOPCENTER = "02";
    /** 商家类型 ---普通商家*/
    public static final String BU_ORG_TYPE_COMMON = "03";
    /** 商家类型Map */
    public static Map<String, String> BU_ORG_TYPE_MAP = new LinkedHashMap<String, String>();
    
    /** 商家收益设置数据的生效状态     未生效*/
    public static final String FI_BONUS_STATUS_WAIT = "01";
    /** 商家收益设置数据的生效状态     生效*/
    public static final String FI_BONUS_STATUS_FAC = "02";
    /** 商家收益设置数据的生效状态     失效*/
    public static final String FI_BONUS_STATUS_NOFAC = "03";
    /** 商家收益设置Map */
    public static Map<String, String> FI_BONUS_STATUS_MAP = new LinkedHashMap<String, String>();
    
    /** 支付类型  银行支行*/
    public static final String PAY_TYPE_NAME = "01";
    /** 支付类型  银行支行Map */
    public static Map<String, String> PAY_TYPE_NAME_MAP = new LinkedHashMap<String, String>();
    
    
    /** 商品属性 状态 */
    public static Map<String, String> PROPERTY_STATUS_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    /** 商品属性 状态0 无效 */
    public static final String PROPERTY_STATUS_INVALID = "0";
    /** 商品属性 状态1 有效 */
    public static final String PROPERTY_STATUS_VALID = "1";
    
    /** 商品属性 提交审核 or保存  标识  save */
    public static final String PROPERTY_MARK_SAVE = "save";
    /** 商品属性 提交审核 or保存  标识    check */
    public static final String PROPERTY_MARK_CHECK = "check";
    
    /** 商品属性 是否编辑  标识  edit */
    public static final String PROPERTY_STATUS_MARK_EDIT = "edit";
    /** 商品属性   是否编辑  标识    unedit */
    public static final String PROPERTY_STATUS_MARK_UNEDIT = "unedit";
    /** 商品属性   基本属性 标识    base */
    public static final String PROPERTY_TYPE_MARK_BASE = "base";
    /** 商品属性   系列属性 标识    series */
    public static final String PROPERTY_TYPE_MARK_SERIES = "series";
    
    /**商品类目 审核状态**/
    public static Map<String, String> CAT_CHECKSTATUS_MAP = new LinkedHashMap<String, String>();// 审核描述信息
    /** 商品类目 审核状态  待审核  01 */
    public static final String CAT_CHECKSTATUS_WAIT = "01";
    /** 商品类目 审核状态  审核通过  02 */
    public static final String CAT_CHECKSTATUS_PASS = "02";
    /** 商品类目 审核状态  审核不通过  03 */
    public static final String CAT_CHECKSTATUS_UNPASS = "03";
    
    
    
    // 初始化
    static {
    	//区域等级Map设置
    	REGION_MAP.put(REGION_BIGAREA, "大区");
    	REGION_MAP.put(REGION_PROV, "省");
    	REGION_MAP.put(REGION_CITY, "市");
    	REGION_MAP.put(REGION_DIS, "区县");
    	
    	//商家收益设置数据生效状态
    	FI_BONUS_STATUS_MAP.put(FI_BONUS_STATUS_WAIT, "未生效");
    	FI_BONUS_STATUS_MAP.put(FI_BONUS_STATUS_FAC, "生效");
    	FI_BONUS_STATUS_MAP.put(FI_BONUS_STATUS_NOFAC, "失效");
    	
    	//商家类型
    	BU_ORG_TYPE_MAP.put(BU_ORG_TYPE_BAIHUO, "百货类商家");
    	BU_ORG_TYPE_MAP.put(BU_ORG_TYPE_SHOPCENTER, "购物中心");
    	BU_ORG_TYPE_MAP.put(BU_ORG_TYPE_COMMON, "普通商家");
        
        // 商家审核状态
        VERIFY_MAP.put(VERIFY_EDIT, "编辑中");
        VERIFY_MAP.put(VERIFY_WAIT, "待审核");
        VERIFY_MAP.put(VERIFY_PASS, "审核通过");
        VERIFY_MAP.put(VERIFY_NOPASS, "审核不通过");
        
        // IPP审核状态        
        IVERIFY_MAP.put(VERIFY_WAIT, "待审核");
        IVERIFY_MAP.put(VERIFY_PASS, "审核通过");
        IVERIFY_MAP.put(VERIFY_NOPASS, "审核不通过");

        
        /**商品类型map**/
        GOODSTYPE_MAP.put(GOODSTYPE_GOODS, "实物商品");
        GOODSTYPE_MAP.put(GOODSTYPE_VIRTUAL, "虚拟票券");
        GOODSTYPE_MAP.put(GOODSTYPE_RECHARGE, "缴费充值");
 
        // 积分状态
        ITG_STATUS_MAP.put(ITG_STATUS_INITI, "初始化");
        ITG_STATUS_MAP.put(ITG_STATUS_WAIT, "待运行");
        ITG_STATUS_MAP.put(ITG_STATUS_RUNNING, "运行中");
        ITG_STATUS_MAP.put(ITG_STATUS_FINNISH, "运行完成");
        ITG_STATUS_MAP.put(ITG_STATUS_INVALID, "作废");
        // 积分来源
        ORG_TYPE_MAP.put(ORG_TYPE_IPP, "主站");
        ORG_TYPE_MAP.put(ORG_TYPE_MERCHANT, "商家");

        //状态
        STATUS_MAP.put(STATUS_VALID, "有效");
        STATUS_MAP.put(STATUS_INVALID, "无效");
        //收益设置类型
        FI_SAL_MAP.put(BONUS_RULL_TYPE, "商品销售");
        FI_SAL_MAP.put(BONUS_TYPE_SAL, "商品上架");
       //收益账户设置类型 
        PAY_EK_MAP.put(PAY_METHOD_KID, "银行卡");
        PAY_EK_MAP.put(PAY_METHOD_EID, "E卡通");
        //验票渠道
        TICKET_PURCHASE_MAP.put(MANUAL_TICKET, "手动验票");
        TICKET_PURCHASE_MAP.put(EASY_PURCHASE, "轻松购");
        /**商品属性map**/
        PROPERTYTYPE_MAP.put(PROPERTY_BASE, "基本属性");
        PROPERTYTYPE_MAP.put(PROPERTY_SERIES, "系列属性");
        //支付类型   银行支付
        PAY_TYPE_NAME_MAP.put(PAY_TYPE_NAME, "银行支付");
        
        //商品属性map
        PROPERTY_STATUS_MAP.put(PROPERTY_STATUS_INVALID, "无效");
        PROPERTY_STATUS_MAP.put(PROPERTY_STATUS_VALID, "有效");
        
        //商品类目审核状态map
        CAT_CHECKSTATUS_MAP.put(CAT_CHECKSTATUS_WAIT, "待审核");
        CAT_CHECKSTATUS_MAP.put(CAT_CHECKSTATUS_PASS, "审核通过");
        CAT_CHECKSTATUS_MAP.put(CAT_CHECKSTATUS_UNPASS, "审核不通过");
        
    }

}
