package com.asahikaseieng;

import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 * アプリケーション共通の定数.
 * @author t0011036
 */
public final class Constants {
	private Constants() {
	}

	/**
	 * プロパティーファイル.
	 */
	/** アプリケーションプロパティー. */
	public static final String APPLICATION_PROPERTIES = "application";

	/** システム設定プロパティー. */
	public static final String SYSTEM_PROPERTIES = "system";

	/** レポートプロパティー. */
	public static final String REPORT_PROPERTIES = "report";

	/** アプリケーションプロパティーのResourceBundle. */
	public static final ResourceBundle RB_APPLICATION_PROPERTIES = ResourceBundle
			.getBundle("application");

	/** システム設定プロパティーのResourceBundle. */
	public static final ResourceBundle RB_SYSTEM_PROPERTIES = ResourceBundle
			.getBundle("system");

	/** レポートプロパティーのResourceBundle. */
	public static final ResourceBundle RB_REPORT_PROPERTIES = ResourceBundle
			.getBundle("report");

	/**
	 * セッションに置くオブジェクトのキー.
	 */
	/** セッションキー：ログイン後、ユーザーオブジェクトを設定するキー. */
	public static final String LOGIN_KEY = "login.key";

	/** セッションキー：メニュー権限を設定するキー. */
	public static final String MENU_CONTEXT_AUTH = "menu.context.auth.key";

	/** セッションキー：メニューを格納するキー. */
	public static final String MENU_KEY = "menu.key";

	/** セッションキー：ガジェットを格納するキー. */
	public static final String GADGET_KEY = "gadget.key";

	/** リクエストキー：メニューIDを格納するキー. */
	public static final String MENU_PARAM = "menuId";

	/** セッションキー：EXCELダウンロードキー. */
	public static final String DOWNLOAD_FILE_KEY = "DOWNLOAD_FILE_KEY";

	/** セッションキー：PDFダウンロードキー. */
	public static final String DOWNLOAD_FILE_KEY_PDF = "DOWNLOAD_FILE_KEY_PDF";

	/** セッションキー：自社マスタ情報を格納するキー. */
	public static final String COMPANY_INFO_KEY = "company.key";

	/** セッションキー：ログイン失敗回数を格納するキー. */
	public static final String LOGIN_ERROR_COUNT_KEY = "login.error.count.key";

	/** セッションキー：メニュー階層(ロールマスタ用)を格納するキー. */
	public static final String MENU_SET_KEY = "roleDetailMenuForm";

	/**
	 * 
	 * Excel関連
	 * 
	 */
	/** セル形式(数値). */
	public static final String TYPE_NUMERIC = "NUMERIC";

	/** セル形式(文字列). */
	public static final String TYPE_STRING = "STRING";

	/** スペース(空白). */
	public static final String STRING_SPACE = " ";

	/**
	 * TEST用定数.
	 */
	/** TEST_PARAMETER_NODATA(対象データ無し). */
	public static final String TEST_PARAMETER_NODATA = "noData";

	/** TEST_PARAMETER_NODATA(閾値). */
	public static final String TEST_PARAMETER_THRESHOLD = "threshold";

	/** TEST_PARAMETER_DUPLICATE(一意制約). */
	public static final String TEST_PARAMETER_DUPLICATE = "duplication";

	/** TEST_PARAMETER_OPTIMISTIC(排他). */
	public static final String TEST_PARAMETER_OPTIMISTIC = "optimisticLock";

	/** 一意制約エラーコード. */
	public static final int TEST_DUPLICATE_EXCEPTION_CODE = 1;

	/**
	 * コンボボックス
	 */
	/** 選択されていない状態のvalue値. */
	public static final String UNSELECTED_VALUE = "";

	/** 選択されていない状態のlabel値. */
	public static final String UNSELECTED_LABEL = "";

	/**
	 * フラグ・コード.
	 */
	/** 共通フラグ：ON. */
	public static final String FLG_ON = "1";

	/** 共通フラグ：OFF. */
	public static final String FLG_OFF = "0";

	/**
	 * DuplicateRuntimeExceptionのインデックス番号.
	 */
	/** 対象外の初期値. */
	public static final int EXCEPTION_INIT_ROW = 0;

	/**
	 * メニュー.
	 */
	/** 種別：ファイル. */
	public static final String IS_FILE = "1";

	/** 制御用：メニューＩＤ接頭語. */
	public static final String MENU_ID_PREFIX = "menu";

	/** 制御用：メニューＩＤ接頭語. */
	public static final String MENU_ID_PREFIX2 = "m";

	/** 制御用：タブＩＤ接頭語. */
	public static final String TAB_ID_PREFIX = "t";

	/** 制御用：操作ＩＤ接頭語. */
	public static final String CTRL_ID_PREFIX = "c";

	/**
	 * メニュー権限.
	 */
	/** 権限：権限無し. */
	public static final String AUTHORITY_DISABLE = "0";

	/** 権限：読取. */
	public static final String AUTHORITY_READ = "1";

	/** 権限：書込. */
	public static final String AUTHORITY_WRITE = "2";

	/**
	 * ガジェット制御.
	 */
	/** 最初のレーン. */
	public static final BigDecimal FIRST_LANE = BigDecimal.ZERO;

	/** ２番目のレーン. */
	public static final BigDecimal SECOND_LANE = BigDecimal.ONE;

	/** 表示状態：通常. */
	public static final BigDecimal SLIDE_STATUS_MAX = BigDecimal.ZERO;

	/** 表示状態：最小化. */
	public static final BigDecimal SLIDE_STATUS_MIN = BigDecimal.ONE;

	/** 強制表示フラグ：強制表示なし. */
	public static final String FORCE_DISP_FLG_OFF = "0";

	/** 強制表示フラグ：強制表示あり. */
	public static final String FORCE_DISP_FLG_ON = "1";

	/**
	 * ログインチェック.
	 */
	/** ログイン失敗回数. */
	public static final int LOGIN_ERROR_COUNT_MAX = 5;

	/**
	 * メニュー区分.
	 */
	/** 区分：フォルダ. */
	public static final String KBN_DIRECTROY = "0";

	/** 区分：メニュー. */
	public static final String KBN_MENU = "1";

	/** 区分：タブ. */
	public static final String KBN_TAB = "2";

	/** 区分：操作. */
	public static final String KBN_CTRL = "3";

	/**
	 * 操作ID.
	 */
	/** 操作ID:閲覧 */
	public static final BigDecimal CTRL_ID_VIEW = new BigDecimal("0");

	/** 操作ID:承認 */
	public static final BigDecimal CTRL_ID_APPROVAL = new BigDecimal("1");

	/** 操作ID:承認依頼 */
	public static final BigDecimal CTRL_ID_APPROVAL_REQUEST = new BigDecimal(
			"2");

	/** 操作ID:否認 */
	public static final BigDecimal CTRL_ID_NEGATION = new BigDecimal("3");

	/** 操作ID:承認取消 */
	public static final BigDecimal CTRL_ID_APPROVAL_CANCEL = new BigDecimal("4");

	/** 操作ID:登録 */
	public static final BigDecimal CTRL_ID_UPDATE = new BigDecimal("5");

	/** 操作ID:削除 */
	public static final BigDecimal CTRL_ID_DELETE = new BigDecimal("6");
	
	/** 操作ID:確定 */
	public static final BigDecimal CTRL_ID_CONFIRM = new BigDecimal("7");

	/** 操作ID:確定取消 */
	public static final BigDecimal CTRL_ID_CONFIRM_CANCEL = new BigDecimal("8");


	/** 桁数チェックユーティリティのsession格納キー */
	public static final String CHECK_DIGIT_UTIL_KEY = "checkDigitUtil";

	/** 在庫数量換算のsession格納キー */
	public static final String CONV_INVENTORY_KEY = "convInventory";

	/** 休日チェックのsession格納キー */
	public static final String CHECK_HOLIDAY_KEY = "checkHoliday";

	/**
	 * メニューID
	 */
	/** 品目マスタ */
	public static final BigDecimal MENU_ID_ITEM = new BigDecimal("60");

	/** 品目基本 */
	public static final BigDecimal TAB_ID_ITEM_DETAIL = new BigDecimal("1");

	/** 品目在庫・単価 */
	public static final BigDecimal TAB_ID_ITEM_ATTRIBUTE = new BigDecimal("2");

	/** 品目成分 */
	public static final BigDecimal TAB_ID_ITEM_COMPONENT = new BigDecimal("3");

	/** 品目関連 */
	public static final BigDecimal TAB_ID_ITEM_RELATED = new BigDecimal("4");

	/** 品目技術 */
	public static final BigDecimal TAB_ID_ITEM_TECH = new BigDecimal("5");

	/** 品目その他 */
	public static final BigDecimal TAB_ID_ITEM_OTHER = new BigDecimal("6");

	/** 品目更新 */
	public static final BigDecimal TAB_ID_ITEM_HISTORY = new BigDecimal("7");

	/** 財務分類マスタ */
	public static final BigDecimal MENU_ID_FINANCIAL_CLASS = new BigDecimal(
			"61");

	/** 財務分類詳細 */
	public static final BigDecimal TAB_ID_FINANCIAL_CLASS_DETAIL = new BigDecimal(
			"1");

	/** 仕入先別単価マスタ */
	public static final BigDecimal MENU_ID_UNITPRICE = new BigDecimal("63");

	/** 仕入先別単価詳細 */
	public static final BigDecimal TAB_ID_UNITPRICE_DETAIL = new BigDecimal("1");

	/** 担当者マスタ */
	public static final BigDecimal MENU_ID_LOGIN = new BigDecimal("64");

	/** 担当者詳細 */
	public static final BigDecimal TAB_ID_LOGIN_DETAIL = new BigDecimal("1");

	/** ロケーションマスタ */
	public static final BigDecimal MENU_ID_LOCATION = new BigDecimal("65");

	/** ロケーション詳細 */
	public static final BigDecimal TAB_ID_LOCATION_DETAIL = new BigDecimal("1");

	/** 設備グループマスタ */
	public static final BigDecimal MENU_ID_RECIPE_RESOUCE_GROUP = new BigDecimal(
			"66");

	/** 設備グループ詳細 */
	public static final BigDecimal TAB_ID_RECIPE_RESOUCE_GROUP_DETAIL = new BigDecimal(
			"1");

	/** 納入先マスタ */
	public static final BigDecimal MENU_ID_DELIVERY = new BigDecimal("67");

	/** 納入先詳細 */
	public static final BigDecimal TAB_ID_DELIVERY_DETAIL = new BigDecimal("1");

	/** 工程マスタ */
	public static final BigDecimal MENU_ID_OPERATION = new BigDecimal("69");

	/** 工程詳細 */
	public static final BigDecimal TAB_ID_OPERATION_DETAIL = new BigDecimal("1");

	/** 設備マスタ */
	public static final BigDecimal MENU_ID_RECIPE_RESOUCE = new BigDecimal("70");

	/** 設備詳細 */
	public static final BigDecimal TAB_ID_RECIPE_RESOUCE_DETAIL = new BigDecimal(
			"1");

	/** 取引先マスタ */
	public static final BigDecimal MENU_ID_VENDER = new BigDecimal("71");

	/** 取引先詳細 */
	public static final BigDecimal TAB_ID_VENDER_DETAIL = new BigDecimal("1");

	/** 生産ラインマスタ */
	public static final BigDecimal MENU_ID_LINE = new BigDecimal("74");

	/** 生産ライン詳細 */
	public static final BigDecimal TAB_ID_LINE_DETAIL = new BigDecimal("1");

	/** 地区マスタ */
	public static final BigDecimal MENU_ID_AREA = new BigDecimal("76");

	/** 地区詳細 */
	public static final BigDecimal TAB_ID_AREA_DETAIL = new BigDecimal("1");

	/** 品目分類マスタ */
	public static final BigDecimal MENU_ID_ITEM_CATEGORY = new BigDecimal("77");

	/** 品目分類詳細 */
	public static final BigDecimal TAB_ID_ITEM_CATEGORY_DETAIL = new BigDecimal(
			"1");

	/** 勘定科目マスタ */
	public static final BigDecimal MENU_ID_ACCOUNTS = new BigDecimal("83");

	/** 勘定科目詳細 */
	public static final BigDecimal TAB_ID_ACCOUNTS_DETAIL = new BigDecimal("1");

	/** 相殺グループマスタ */
	public static final BigDecimal MENU_ID_OFFSET_GROUP = new BigDecimal("84");

	/** 相殺グループ詳細 */
	public static final BigDecimal TAB_ID_OFFSET_GROUP_DETAIL = new BigDecimal(
			"1");

	/** 運送会社マスタ */
	public static final BigDecimal MENU_ID_CARRY = new BigDecimal("86");

	/** 運送会社詳細 */
	public static final BigDecimal TAB_ID_CARRY_DETAIL = new BigDecimal("1");

	/** 成分マスタ */
	public static final BigDecimal MENU_ID_COMPONENT = new BigDecimal("88");

	/** 成分詳細 */
	public static final BigDecimal TAB_ID_COMPONENT_DETAIL = new BigDecimal("1");

	/** 備考マスタ */
	public static final BigDecimal MENU_ID_REMARK = new BigDecimal("90");

	/** 備考詳細 */
	public static final BigDecimal TAB_ID_REMARK_DETAIL = new BigDecimal("1");

	/** 帳合マスタ */
	public static final BigDecimal MENU_ID_BALANCE = new BigDecimal("91");

	/** 帳合詳細 */
	public static final BigDecimal TAB_ID_BALANCE_DETAIL = new BigDecimal("1");

	/** 販売条件マスタ */
	public static final BigDecimal MENU_ID_SALES_TERMS = new BigDecimal("92");

	/** 販売条件詳細 */
	public static final BigDecimal TAB_ID_SALES_TERMS_DETAIL = new BigDecimal(
			"1");

	/** 名称マスタ */
	public static final BigDecimal MENU_ID_NAMES = new BigDecimal("93");

	/** 名称詳細 */
	public static final BigDecimal TAB_ID_NAMES_DETAIL = new BigDecimal("1");

	/** 理由マスタ */
	public static final BigDecimal MENU_ID_REASON = new BigDecimal("95");

	/** 理由詳細 */
	public static final BigDecimal TAB_ID_REASON_DETAIL = new BigDecimal("1");
	
	/** 受注データ得意先連携マスタ */
	public static final BigDecimal MENU_ID_ORDERVENDERLINK = new BigDecimal("96");

	/** 原処方マスタ */
	public static final BigDecimal MENU_ID_GRECIPE = new BigDecimal("121");

	/** 原処方ヘッダー */
	public static final BigDecimal TAB_ID_GRECIPE_HEADER = new BigDecimal("1");

	/** 原処方工程 */
	public static final BigDecimal TAB_ID_GRECIPE_PROCEDURE = new BigDecimal(
			"2");

	/** 原処方配合 */
	public static final BigDecimal TAB_ID_GRECIPE_FORMULA = new BigDecimal("3");

	/** 原処方検査 */
	public static final BigDecimal TAB_ID_GRECIPE_INSPECTION = new BigDecimal(
			"4");

	/** 原処方仕上 */
	public static final BigDecimal TAB_ID_GRECIPE_FINISH = new BigDecimal("5");

	/** 原処方詳細 */
	public static final BigDecimal TAB_ID_GRECIPE_DETAIL = new BigDecimal("6");

	/** 原処方その他 */
	public static final BigDecimal TAB_ID_GRECIPE_OTHER = new BigDecimal("7");

	/** 基本処方マスタ */
	public static final BigDecimal MENU_ID_MRECIPE = new BigDecimal("122");

	/** 基本処方ヘッダー */
	public static final BigDecimal TAB_ID_MRECIPE_HEADER = new BigDecimal("1");

	/** 基本処方工程 */
	public static final BigDecimal TAB_ID_MRECIPE_PROCEDURE = new BigDecimal(
			"2");

	/** 基本処方配合 */
	public static final BigDecimal TAB_ID_MRECIPE_FORMULA = new BigDecimal("3");

	/** 基本処方検査 */
	public static final BigDecimal TAB_ID_MRECIPE_INSPECTION = new BigDecimal(
			"4");

	/** 基本処方仕上 */
	public static final BigDecimal TAB_ID_MRECIPE_FINISH = new BigDecimal("5");

	/** 基本処方Asprova */
	public static final BigDecimal TAB_ID_MRECIPE_ASPROVA = new BigDecimal("6");

	/** 基本処方詳細 */
	public static final BigDecimal TAB_ID_MRECIPE_DETAIL = new BigDecimal("7");

	/** 基本処方その他 */
	public static final BigDecimal TAB_ID_MRECIPE_OTHER = new BigDecimal("8");

	/** 受注 */
	public static final BigDecimal MENU_ID_ORDER = new BigDecimal("141");

	/** 受注詳細 */
	public static final BigDecimal TAB_ID_ORDER_DETAIL = new BigDecimal("1");
	
	/** 受注取込 */
	public static final BigDecimal MENU_ID_ORDERIMPORT = new BigDecimal("142");

	/** 受注取込詳細 */
	public static final BigDecimal TAB_ID_ORDERIMPORT_DETAIL = new BigDecimal("1");
	
	/** 出荷指図 */
	public static final BigDecimal MENU_ID_SHIPPING = new BigDecimal("161");

	/** 出荷指図詳細 */
	public static final BigDecimal TAB_ID_SHIPPING_DETAIL = new BigDecimal("1");

	/** 出荷実績 */
	public static final BigDecimal MENU_ID_SHIPPING_RESULT = new BigDecimal(
			"162");

	/** 出荷実績詳細 */
	public static final BigDecimal TAB_ID_SHIPPING_RESULT_DETAIL = new BigDecimal(
			"1");

	/** 生産計画入力 */
	public static final BigDecimal MENU_ID_PRODUCTION = new BigDecimal("181");

	/** 生産計画入力詳細 */
	public static final BigDecimal TAB_ID_PRODUCTION_DETAIL = new BigDecimal(
			"1");

	/** 月間生産計画表 */
	public static final BigDecimal MENU_ID_PRODUCTION_PLAN = new BigDecimal(
			"182");

	/** 月間生産計画表詳細 */
	public static final BigDecimal TAB_ID_PRODUCTION_PLAN_DETAIL = new BigDecimal(
			"1");

	/** 在庫入庫入力 */
	public static final BigDecimal MENU_ID_INVENTORY = new BigDecimal("201");

	/** 在庫入庫入力詳細 */
	public static final BigDecimal TAB_ID_INVENTORY_DETAIL = new BigDecimal("1");

	/** 在庫出庫入力 */
	public static final BigDecimal MENU_ID_INVENTORY_SHIPPING_OUT = new BigDecimal(
			"202");

	/** 在庫出庫入力詳細 */
	public static final BigDecimal TAB_ID_INVENTORY_SHIPPING_OUT_DETAIL = new BigDecimal(
			"1");

	/** 在庫移動入力 */
	public static final BigDecimal MENU_ID_INVENTORY_MOVE = new BigDecimal(
			"203");

	/** 在庫移動入力詳細 */
	public static final BigDecimal TAB_ID_INVENTORY_MOVE_DETAIL = new BigDecimal(
			"1");

	/** 見積/単価マスタ */
	public static final BigDecimal MENU_ID_ESTIMATE = new BigDecimal("221");

	/** 見積/単価詳細 */
	public static final BigDecimal TAB_ID_ESTIMATE_DETAIL = new BigDecimal("1");

	/** 発注 */
	public static final BigDecimal MENU_ID_PURCHASE = new BigDecimal("241");

	/** 発注詳細 */
	public static final BigDecimal TAB_ID_PURCHASE_DETAIL = new BigDecimal("1");

	/** 納期回答 */
	public static final BigDecimal MENU_ID_PURCHASE_DELIVERY = new BigDecimal(
			"243");

	/** 納期回答詳細 */
	public static final BigDecimal TAB_ID_PURCHASE_DELIVERY_DETAIL = new BigDecimal(
			"1");

	/** 入荷 */
	public static final BigDecimal MENU_ID_ARRIVAL = new BigDecimal("244");

	/** 入荷詳細 */
	public static final BigDecimal TAB_ID_ARRIVAL_DETAIL = new BigDecimal("1");

	/** 受入・仕入 */
	public static final BigDecimal MENU_ID_ACCEPT = new BigDecimal("245");

	/** 受入・仕入詳細 */
	public static final BigDecimal TAB_ID_ACCEPT_DETAIL = new BigDecimal("1");

	/** 仕入 */
	public static final BigDecimal MENU_ID_BUYING = new BigDecimal("246");

	/** 仕入詳細 */
	public static final BigDecimal TAB_ID_BUYING_DETAIL = new BigDecimal("1");

	/** 仕入承認 */
	public static final BigDecimal MENU_ID_BUYING_APPROVAL = new BigDecimal(
			"247");

	/** 仕入承認詳細 */
	public static final BigDecimal TAB_ID_BUYING_APPROVAL_DETAIL = new BigDecimal(
			"1");

	/** 外注原材料投入実績 */
	public static final BigDecimal MENU_ID_MATERIAL_RINPUT = new BigDecimal(
			"248");

	/** 外注原材料投入実績詳細 */
	public static final BigDecimal TAB_ID_MATERIAL_RINPUT_DETAIL = new BigDecimal(
			"1");

	/** 製造指図 */
	public static final BigDecimal MENU_ID_DIRECTION = new BigDecimal("261");

	/** 製造指図ヘッダー */
	public static final BigDecimal TAB_ID_DIRECTION_HEADER = new BigDecimal("1");

	/** 製造指図工程 */
	public static final BigDecimal TAB_ID_DIRECTION_PROCEDURE = new BigDecimal(
			"2");

	/** 製造指図配合 */
	public static final BigDecimal TAB_ID_DIRECTION_FORMULA = new BigDecimal(
			"3");

	/** 製造指図検査 */
	public static final BigDecimal TAB_ID_DIRECTION_INSPECTION = new BigDecimal(
			"4");

	/** 製造指図仕上 */
	public static final BigDecimal TAB_ID_DIRECTION_FINISH = new BigDecimal("5");

	/** 製造実績 */
	public static final BigDecimal MENU_ID_RDIRECTION = new BigDecimal("262");

	/** 製造実績ヘッダー */
	public static final BigDecimal TAB_ID_RDIRECTION_HEADER = new BigDecimal(
			"1");

	/** 製造実績工程 */
	public static final BigDecimal TAB_ID_RDIRECTION_PROCEDURE = new BigDecimal(
			"2");

	/** 製造実績配合 */
	public static final BigDecimal TAB_ID_RDIRECTION_FORMULA = new BigDecimal(
			"3");

	/** 製造実績検査 */
	public static final BigDecimal TAB_ID_RDIRECTION_INSPECTION = new BigDecimal(
			"4");

	/** 製造実績仕上 */
	public static final BigDecimal TAB_ID_RDIRECTION_FINISH = new BigDecimal(
			"5");

	/** 製造実績検索 20221006 */
	public static final BigDecimal TAB_ID_RDIRECTION_SEARCH = new BigDecimal(
			"6");

	/** 中間品検査完了入力 */
	public static final BigDecimal MENU_ID_MID_INSPECT_COMP = new BigDecimal(
			"264");

	/** 中間品検査完了入力詳細 */
	public static final BigDecimal TAB_ID_MID_INSPECT_COMP_DETAIL = new BigDecimal(
			"1");

	/** 調合タンク底弁インターロック解除/再設定 */
	public static final BigDecimal MENU_ID_TANK_LOCK = new BigDecimal("265");

	/** 調合タンク底弁インターロック解除/再設定詳細 */
	public static final BigDecimal TAB_ID_TANK_LOCK_DETAIL = new BigDecimal("1");

	/** ステータス変更 */
	public static final BigDecimal MENU_ID_DIRECTION_STATUS_CHANGE = new BigDecimal(
			"266");

	/** ステータス変更詳細 */
	public static final BigDecimal TAB_ID_DIRECTION_STATUS_CHANGE_DETAIL = new BigDecimal(
			"1");

	/** 製品別原材料消費量リスト変更 */
	public static final BigDecimal MENU_ID_DIRECTION_MATERIAL_USED = new BigDecimal(
			"267");

	/** 製品別原材料消費量リスト変更詳細 */
	public static final BigDecimal TAB_ID_DIRECTION_MATERIAL_USED_DETAIL = new BigDecimal(
			"1");

	/** 包装指図 */
	public static final BigDecimal MENU_ID_PKGDIRECTION = new BigDecimal("271");

	/** 包装指図ヘッダー */
	public static final BigDecimal TAB_ID_PKGDIRECTION_HEADER = new BigDecimal(
			"1");

	/** 包装指図工程 */
	public static final BigDecimal TAB_ID_PKGDIRECTION_PROCEDURE = new BigDecimal(
			"2");

	/** 包装指図配合 */
	public static final BigDecimal TAB_ID_PKGDIRECTION_FORMULA = new BigDecimal(
			"3");

	/** 包装指図検査 */
	public static final BigDecimal TAB_ID_PKGDIRECTION_INSPECTION = new BigDecimal(
			"4");

	/** 包装指図仕上 */
	public static final BigDecimal TAB_ID_PKGDIRECTION_FINISH = new BigDecimal(
			"5");

	/** 包装実績 */
	public static final BigDecimal MENU_ID_PKGRDIRECTION = new BigDecimal("272");

	/** 包装実績ヘッダー */
	public static final BigDecimal TAB_ID_PKGRDIRECTION_HEADER = new BigDecimal(
			"1");

	/** 包装実績工程 */
	public static final BigDecimal TAB_ID_PKGRDIRECTION_PROCEDURE = new BigDecimal(
			"2");

	/** 包装実績配合 */
	public static final BigDecimal TAB_ID_PKGRDIRECTION_FORMULA = new BigDecimal(
			"3");

	/** 包装実績検査 */
	public static final BigDecimal TAB_ID_PKGRDIRECTION_INSPECTION = new BigDecimal(
			"4");

	/** 包装実績仕上 */
	public static final BigDecimal TAB_ID_PKGRDIRECTION_FINISH = new BigDecimal(
			"5");

	/** 検査待ち在庫計上 */
	public static final BigDecimal MENU_ID_STOCK_BOOKING = new BigDecimal("273");

	/** 検査待ち在庫計上詳細 */
	public static final BigDecimal TAB_ID_STOCK_BOOKING_DETAIL = new BigDecimal(
			"1");

	/** 製品検査完了入力 */
	public static final BigDecimal MENU_ID_PRODUCT_INSPECT_COMP = new BigDecimal(
			"274");

	/** 製品検査完了入力詳細 */
	public static final BigDecimal TAB_ID_PRODUCT_INSPECT_COMP_DETAIL = new BigDecimal(
			"1");

	/** ロット別棚卸準備処理 */
	public static final BigDecimal MENU_ID_INQUIRY_PREPARATION = new BigDecimal(
			"361");

	/** ロット別棚卸準備処理詳細 */
	public static final BigDecimal TAB_ID_INQUIRY_PREPARATION_DETAIL = new BigDecimal(
			"1");

	/** ロット別棚卸準備キャンセル処理 */
	public static final BigDecimal MENU_ID_INQUIRY_PREPARATION_CANCEL = new BigDecimal(
			"362");

	/** ロット別棚卸準備キャンセル処理詳細 */
	public static final BigDecimal TAB_ID_INQUIRY_PREPARATION_CANCEL_DETAIL = new BigDecimal(
			"1");

	/** 棚卸入力 */
	public static final BigDecimal MENU_ID_INQUIRY_INPUT = new BigDecimal("363");

	/** 棚卸入力 */
	public static final BigDecimal TAB_ID_INQUIRY_INPUT_DETAIL = new BigDecimal(
			"1");

	/** ロット別棚卸更新処理 */
	public static final BigDecimal MENU_ID_INQUIRY_UPDATE = new BigDecimal(
			"364");

	/** ロット別棚卸更新処理 */
	public static final BigDecimal TAB_ID_INQUIRY_UPDATE_DETAIL = new BigDecimal(
			"1");

	/** 月次受払更新処理 */
	public static final BigDecimal MENU_ID_INOUT_MONTHLY_UPDATE = new BigDecimal(
			"382");

	/** 月次受払更新処理詳細 */
	public static final BigDecimal TAB_ID_INOUT_MONTHLY_UPDATE_DETAIL = new BigDecimal(
			"1");

	/** 月次受払ロールバック処理 */
	public static final BigDecimal MENU_ID_INOUT_MONTHLY_CANCEL = new BigDecimal(
			"383");

	/** 月次受払ロールバック処理詳細 */
	public static final BigDecimal TAB_ID_INOUT_MONTHLY_CANCEL_DETAIL = new BigDecimal(
			"1");

	/** 原材料別入出庫一覧表処理 */
	public static final BigDecimal MENU_ID_INOUT_MATERIAL = new BigDecimal(
			"384");

	/** 原材料別入出庫一覧表詳細 */
	public static final BigDecimal TAB_ID_INOUT_MATERIAL_DETAIL = new BigDecimal(
			"1");

	/** 受払月報処理 */
	public static final BigDecimal MENU_ID_INOUT_MONTHLY_REPORT = new BigDecimal(
			"385");

	/** 受払月報詳細 */
	public static final BigDecimal TAB_ID_INOUT_MONTHLY_REPORT_DETAIL = new BigDecimal(
			"1");

	/** 売上・返品 */
	public static final BigDecimal MENU_ID_SALES = new BigDecimal("421");

	/** 売上・返品詳細 */
	public static final BigDecimal TAB_ID_SALES_DETAIL = new BigDecimal("1");

	/** 得意先管理月報 */
	public static final BigDecimal MENU_ID_MONTHLY_VENDER = new BigDecimal(
			"423");

	/** 得意先管理月報 */
	public static final BigDecimal TAB_ID_SALES_MONTHLY_VENDER = new BigDecimal(
			"1");

	/** 請求更新処理 */
	public static final BigDecimal MENU_ID_CLAIM_UPDATE = new BigDecimal("471");

	/** 請求更新処理詳細 */
	public static final BigDecimal TAB_ID_CLAIM_UPDATE_DETAIL = new BigDecimal(
			"1");

	/** 請求更新ロールバック処理 */
	public static final BigDecimal MENU_ID_CLAIM_ROLLBACK = new BigDecimal(
			"472");

	/** 請求更新ロールバック処理詳細 */
	public static final BigDecimal TAB_ID_CLAIM_ROLLBACK_DETAIL = new BigDecimal(
			"1");

	/** 入金 */
	public static final BigDecimal MENU_ID_DEPOSIT = new BigDecimal("482");

	/** 入金詳細 */
	public static final BigDecimal TAB_ID_DEPOSIT_DETAIL = new BigDecimal("1");

	/** 入金消込 */
	public static final BigDecimal MENU_ID_ERASER = new BigDecimal("483");

	/** 売掛更新処理 */
	public static final BigDecimal MENU_ID_AR_UPDATE = new BigDecimal("491");

	/** 売掛更新処理詳細 */
	public static final BigDecimal TAB_ID_AR_UPDATE_DETAIL = new BigDecimal("1");

	/** 売掛更新ロールバック処理 */
	public static final BigDecimal MENU_ID_AR_ROLLBACK = new BigDecimal("492");

	/** 売掛更新ロールバック処理詳細 */
	public static final BigDecimal TAB_ID_AR_ROLLBACK_DETAIL = new BigDecimal(
			"1");

	/** 売掛元帳 */
	public static final BigDecimal MENU_ID_AR_LEDGER = new BigDecimal("494");

	/** 売掛元帳詳細 */
	public static final BigDecimal TAB_ID_AR_LEDGER_DETAIL = new BigDecimal("1");

	/** 入金消込詳細 */
	public static final BigDecimal TAB_ID_ERASER_DETAIL = new BigDecimal("1");

	/** 債務会計送信 */
	public static final BigDecimal MENU_ID_AP_TRANS = new BigDecimal("496");

	/** 債務会計送信詳細 */
	public static final BigDecimal TAB_ID_AP_TRANS_DETAIL = new BigDecimal("1");

	/** 支払 */
	public static final BigDecimal MENU_ID_PAYMENT = new BigDecimal("502");

	/** 支払詳細 */
	public static final BigDecimal TAB_ID_PAYMENT_DETAIL = new BigDecimal("1");

	/** 支払更新処理 */
	public static final BigDecimal MENU_ID_PAYMENT_UPDATE = new BigDecimal(
			"511");

	/** 支払更新処理詳細 */
	public static final BigDecimal TAB_ID_PAYMENT_UPDATE_DETAIL = new BigDecimal(
			"1");

	/** 支払更新ロールバック処理 */
	public static final BigDecimal MENU_ID_PAYMENT_ROLLBACK = new BigDecimal(
			"512");

	/** 支払更新ロールバック処理詳細 */
	public static final BigDecimal TAB_ID_PAYMENT_ROLLBACK_DETAIL = new BigDecimal(
			"1");

	/** FBデータ作成 */
	public static final BigDecimal MENU_ID_FBDATA_MAKING = new BigDecimal("514");

	/** FBデータ作成詳細 */
	public static final BigDecimal TAB_ID_FBDATA_MAKING_DETAIL = new BigDecimal(
			"1");

	/** グループ間相殺 */
	public static final BigDecimal MENU_ID_OFFSET = new BigDecimal("516");

	/** グループ間相殺詳細 */
	public static final BigDecimal TAB_ID_OFFSET_DETAIL = new BigDecimal("1");

	/** 買掛更新処理 */
	public static final BigDecimal MENU_ID_AP_UPDATE = new BigDecimal("521");

	/** 買掛更新処理詳細 */
	public static final BigDecimal TAB_ID_AP_UPDATE_DETAIL = new BigDecimal("1");

	/** 買掛更新ロールバック処理 */
	public static final BigDecimal MENU_ID_AP_ROLLBACK = new BigDecimal("522");

	/** 買掛更新ロールバック処理詳細 */
	public static final BigDecimal TAB_ID_AP_ROLLBACK_DETAIL = new BigDecimal(
			"1");

	/** 買掛元帳 */
	public static final BigDecimal MENU_ID_AP_LEDGER = new BigDecimal("524");

	/** 買掛元帳詳細 */
	public static final BigDecimal TAB_ID_AP_LEDGER_DETAIL = new BigDecimal("1");

	/** 債権会計送信 */
	public static final BigDecimal MENU_ID_AR_TRANS = new BigDecimal("525");

	/** 債権会計送信詳細 */
	public static final BigDecimal TAB_ID_AR_TRANS_DETAIL = new BigDecimal("1");

	/** 日報入力 */
	public static final BigDecimal MENU_ID_DAILY_REPORT = new BigDecimal("601");

	/** 日報入力詳細 */
	public static final BigDecimal TAB_ID_DAILY_REPORT_DETAIL = new BigDecimal(
			"1");

	/** 原価計算データ送信 */
	public static final BigDecimal MENU_ID_COST_ACCOUNTING = new BigDecimal(
			"701");

	/** 原価計算データ送信詳細 */
	public static final BigDecimal TAB_ID_COST_ACCOUNTING_DETAIL = new BigDecimal(
			"1");

	/** 自社マスタ */
	public static final BigDecimal MENU_ID_COMPANY = new BigDecimal("901");

	/** 自社詳細 */
	public static final BigDecimal TAB_ID_COMPANY_DETAIL = new BigDecimal("1");

	/** カレンダーマスタ */
	public static final BigDecimal MENU_ID_CAL = new BigDecimal("902");

	/** カレンダー詳細 */
	public static final BigDecimal TAB_ID_CAL_DETAIL = new BigDecimal("1");

	/** 会計部門マスタ */
	public static final BigDecimal MENU_ID_BUMON = new BigDecimal("903");

	/** 会計部門詳細 */
	public static final BigDecimal TAB_ID_BUMON_DETAIL = new BigDecimal("1");

	/** 銀行マスタ */
	public static final BigDecimal MENU_ID_BANK = new BigDecimal("904");

	/** 銀行詳細 */
	public static final BigDecimal TAB_ID_BANK_DETAIL = new BigDecimal("1");

	/** 部署マスタ */
	public static final BigDecimal MENU_ID_ORGANIZATION = new BigDecimal("911");

	/** 部署詳細 */
	public static final BigDecimal TAB_ID_ORGANIZATION_DETAIL = new BigDecimal(
			"1");

	/** 所属マスタ */
	public static final BigDecimal MENU_ID_BELONG = new BigDecimal("912");

	/** 所属詳細 */
	public static final BigDecimal TAB_ID_BELONG_DETAIL = new BigDecimal("1");

	/** 役職マスタ */
	public static final BigDecimal MENU_ID_POST = new BigDecimal("913");

	/** 役職詳細 */
	public static final BigDecimal TAB_ID_POST_DETAIL = new BigDecimal("1");

	/** ロールマスタ */
	public static final BigDecimal MENU_ID_ROLE = new BigDecimal("914");

	/** ロール詳細 */
	public static final BigDecimal TAB_ID_ROLE_DETAIL = new BigDecimal("1");

	/** 所属ロール組合せマスタ */
	public static final BigDecimal MENU_ID_BELONG_ROLE = new BigDecimal("915");

	/** 所属ロール組合せ詳細 */
	public static final BigDecimal TAB_ID_BELONG_ROLE_DETAIL = new BigDecimal(
			"1");
	
	/** 販売条件・見積単価　コピー作成・削除 */
	public static final BigDecimal MENU_ID_SALESTERMS_AND_ESTIMATE = new BigDecimal("916");

	/** 販売条件・見積単価　コピー作成・削除詳細 */
	public static final BigDecimal TAB_ID_SALESTERMS_AND_ESTIMATE_DETAIL = new BigDecimal(
			"1");
	

	/** ロット管理しない場合のロット番号 */
	public static final String LOTNO_WITHOUT_LOT = "999999";

	/** AUTOCOMPLETE の表示行上限 */
	public static final String AUTOCOMPLETTE_ROW_LIMIT = "50";

	/** エラーログ出力最大サイズ */
	public static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** 小数点以下の桁数 割り算の循環小数エラー回避用 */
	public static final int SYOSU_KETA = 50;
	
	//20190819追記
	/** コンボボックス：すべてのコード. */
	public static final String COMB_ALL_CD = "0";

	public static final String VENDER_DIVISION_TS = "TS";
	
	public static final String VENDER_DIVISION_SI = "SI";
	
	public static final String COMPANY_CD = "000001";

	public static final String SALES_CATEGORY = "SALES";
	
	public static final String STOCKING_CATEGORY = "STOCKING";
	
	//20201022追記
	/** 桁数チェック用*/
	public static final String TANKA = "TANKA";
	
	public static final String URTANKA = "URTANKA";
	
	public static final String URKINGAKU = "URKINGAKU";
	
	public static final String KG = "1";
	
	public static final String PIECE = "8";
	
	public static final String SONOTA = "SONOTA";
}

