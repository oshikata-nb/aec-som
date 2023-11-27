CREATE OR REPLACE PACKAGE      PD_PLAN_PACKAGE
IS
--■■■■■■■■■■■■■■■■■■■
--■　生産計画パッケージ
--■　パラメータ:
--■■■■■■■■■■■■■■■■■■■
	--*****カーソル宣言*****
	--処方ヘッダ
	CURSOR g_curRcpHead IS SELECT * FROM RECIPE_HEADER;
	--処方プロシージャ
	CURSOR g_curRcpPr IS select * from RECIPE_PROCEDURE;
	--処方フォーミュラ
	CURSOR g_curRcpFm IS select * from RECIPE_FORMULA;
	--処方検査
	CURSOR g_curRcpIns IS select * from RECIPE_INSPECTION;

	--受注生産品目件数取得
	CURSOR g_curOdrPdItemCnt(vItemCd VARCHAR2) IS
	SELECT distinct count(*) as CNT
	FROM ITEM A1, PRODUCT_ATTRIBUTE_QUEUE A2
	WHERE
	A1.ITEM_CD = A2.ITEM_CD AND A1.PRODUCT_DIVISION <> 0 AND A1.PHANTOM_DIVISION = 0 AND
	--A2.STATUS = 3 AND A2.PLAN_DIVISION = 2 AND A1.ITEM_CD = vItemCd;
	A2.PLAN_DIVISION = 2 AND A1.ITEM_CD = vItemCd;

	--*****プロシージャ宣言*****
	--生産計画メイン処理
	PROCEDURE PD_PLAN_MAIN;

	--処方ヘッダ選択処理
	PROCEDURE PD_PLAN_RECIPE_SELECT
		(vItemCd IN VARCHAR2, nPlanQty IN NUMBER,
		nRcpIndex OUT NUMBER, vRcpCd OUT VARCHAR2, nRcpVer OUT NUMBER, nPdQty OUT NUMBER,nPdAllQty OUT NUMBER);

	--製造指図更新
	PROCEDURE PD_PLAN_DIRECTION_UPDATE
		(nDirDiv IN NUMBER, vDirNo IN varchar2,vStepLine IN varchar2, daPdStart IN date, daPdEnd IN date,
		vWorker IN varchar2, nTime IN NUMBER, vInputorCd IN VARCHAR2, nRet OUT number);

	--*****関数宣言*****
	--生産計画登録判定処理
	FUNCTION FUN_PD_PLAN_JUD RETURN boolean;

	--生産計画登録処理
	FUNCTION FUN_PD_PLAN_REG
		(vItemCd VARCHAR2, nPlanQty NUMBER,nPlanDiv NUMBER, daPdSt DATE,
		vOrderNo VARCHAR2, nOrderLineNo NUMBER,nLdtime NUMBER,vPlanNoBase VARCHAR2)
		RETURN boolean;

	--生産計画登録処理(受注生産品目)
	FUNCTION FUN_PD_PLAN_REG_ORDERITEM
		(vItemCd VARCHAR2, nPlanQty NUMBER,nPlanDiv NUMBER, daPdSt DATE,
		vOrderNo VARCHAR2, nOrderLineNo NUMBER,nLdtime NUMBER)
		RETURN boolean;

	--ロット番号取得処理
	FUNCTION FUN_PD_PLAN_GET_LOTNO( daDate date ) RETURN VARCHAR2;

	--生産計画確定処理
	FUNCTION FUN_PD_PLAN_DECISION(vPlanNo varchar2,vDirNo varchar2, vWorker varchar2,
		daStDate date,daEdDate date, nQty NUMBER, nWorkTime NUMBER,vOdrNo varchar2,
		nOdrLineNo NUMBER, vInputorCd varchar2 )
		RETURN NUMBER;

	--製造指図作成処理
	function FUN_PD_PLAN_CREATE_DIRECTION
		(nDirDiv NUMBER, vDirNo VARCHAR2, vPdLineCd VARCHAR2, vItemCd VARCHAR2,
		vRcpIdxCd VARCHAR2, nNeedQty NUMBER, daPdStart date, daPdEnd date,
		vLot VARCHAR2, vLocationCd VARCHAR2, vTeki VARCHAR2,
		nDirSts NUMBER, vInputorCd VARCHAR2)
		RETURN VARCHAR2;

	--製造指図作成処理
	function FUN_CREATE_REPACK_DIRECTION
		(nDirDiv NUMBER, vDirNo VARCHAR2,  vPdLineCd VARCHAR2, vItemCd VARCHAR2,
		vRcpIdxCd VARCHAR2, nNeedQty NUMBER, daPdStart date, daPdEnd date,
		vLot VARCHAR2, vLocationCd VARCHAR2, vTeki VARCHAR2,
		nDirSts NUMBER, vInputorCd VARCHAR2)
		RETURN VARCHAR2;

	--製造指図登録処理
	FUNCTION FUN_PD_PLAN_DIRECTION_SET
	(nDirDiv NUMBER	, vDirNo VARCHAR2	, vPdLine VARCHAR2	, vItemCd VARCHAR2
	,nGetRcpIndex NUMBER	, nPdQty NUMBER	, daPdStart date	, daPdEnd date
	,vLot VARCHAR2	, vLocationCd VARCHAR2	, vTeki VARCHAR2	,nDirSts NUMBER
	,nProcSts NUMBER	,vWorker varchar2	, nWorkTime NUMBER	, vInputorCd VARCHAR2)
	RETURN boolean;

	--製造指図登録処理(詰め替え張替え) 20090202
	FUNCTION FUN_DIRECTION_REPACK_SET
	(nDirDiv NUMBER	, vDirNo VARCHAR2	, vPdLine VARCHAR2	, vItemCd VARCHAR2	,nGetRcpIndex VARCHAR2
	, nPdQty NUMBER	, daPdStart date	, daPdEnd date	,vLot VARCHAR2	, vLocationCd VARCHAR2	, vTeki VARCHAR2
	,nDirSts NUMBER	,nProcSts NUMBER	,vWorker varchar2	, nWorkTime NUMBER	, vInputorCd VARCHAR2
	) RETURN BOOLEAN ;

	--製造指図ヘッダ登録
	FUNCTION FUN_PD_PLAN_DIRECTION_HDREG
	(nDirDiv	NUMBER, 	--指図区分
	vDirNo		VARCHAR2, 	--指図番号
	nDirSts		NUMBER,		--指図ステータス
	nRcpIdx		NUMBER, 	--レシピインデックス
	vRcpCd		VARCHAR2, 	--レシピコード
	nRcpVer		NUMBER, 	--レシピバージョン
	vPdLine		varchar2 , 	--生産ライン
	vItemCd		VARCHAR2, 	--品目コード
	nPdQty		NUMBER, 	--生産量
	vLotNo		VARCHAR2, 	--ロット番号
	vLocationCd VARCHAR2,	--入庫ロケーション
	daPdStart	date, 		--製造開始日時
	daPdEnd		date, 		--製造開終了時
	vTeki		VARCHAR2,	--摘要
	vInputorCd	VARCHAR2,	--入力者
	rtRcpHead	g_curRcpHead%rowtype)
		RETURN boolean;

	--製造指図プロシージャ登録
	FUNCTION FUN_PD_PLAN_DIRECTION_PRREG
		(nDirDiv NUMBER, vDirNo VARCHAR2, nStepNo NUMBER, nProcSts NUMBER,
		vWorker varchar2, daStDate date, daEdDate date,nWorkTime NUMBER,vInputorCd VARCHAR2,
		rtRcpPr g_curRcpPr%rowtype)
		RETURN boolean;

	--製造指図フォーミュラ登録
	FUNCTION FUN_PD_PLAN_DIRECTION_FMREG
		(nDirDiv NUMBER, vDirNo VARCHAR2, nStepNo NUMBER, nLineNo NUMBER,
		vLocationCd VARCHAR2, vLotNo VARCHAR2, nFmQty NUMBER, vInputorCd VARCHAR2, rtRcpFm g_curRcpFm%rowtype)
		RETURN boolean;

	--製造指図検査登録
	FUNCTION FUN_PD_PLAN_DIRECTION_INSREG
		(nDirDiv NUMBER, vDirNo VARCHAR2, nStepNo NUMBER, nLineNo NUMBER, vInputorCd VARCHAR2,
		rtRcpIns g_curRcpIns%rowtype)
		RETURN boolean;

	--受注生産品目取得
	FUNCTION FUN_PD_PLAN_GET_ORDER_ITEM(vMainItemCd VARCHAR2,nNeedQty number) RETURN boolean;

	--ピッキング登録
	function FUN_PD_PLAN_PICKING_REG
		(vPickNo varchar2,vPickLineNo NUMBER, nDirDiv NUMBER, vDirNo VARCHAR2, vItemCd VARCHAR2,nQty NUMBER,
		daPdStart date,vPdLine VARCHAR2,vLocation VARCHAR2, vStepGr VARCHAR2,vInputorCd VARCHAR2)
		RETURN boolean;

	--*****ユーザ定義型宣言*****
	--受注生産品 枝番製造指図品目情報
	TYPE tpDirectionItemInfo IS RECORD (
		vItemCd varchar2(255),		--品目コード
		nLLC	NUMBER,				--LLC
		nQty	NUMBER,				--数量
		nLeadTime	NUMBER,			--リードタイム
		nUse		NUMBER,			--使用用途
		nRcpIndex	NUMBER,			--レシピインデックス
		vRcpCd		varchar2(20),	--レシピコード
		nRcpVer		NUMBER,			--レシピバージョン
		vLocation	varchar2(10),	--ロケーション
		vPdLine 	varchar2(10),	--生産ライン
		nLoss		NUMBER,
		nUnitQty	NUMBER,
		--nAllPdQty	NUMBER,
		nPdQty		NUMBER,
		nPlanDiv	NUMBER
	);

	--*****変数宣言*****
	--受注生産品 枝番製造指図品目情報
	TYPE tp_directiontable IS TABLE OF tpDirectionItemInfo;
	g_tpDirectionInfo tp_directiontable;

	g_daDate date := sysdate;--処理日付

	--*****定数宣言*****
	--受払ソース：受払区分
	pc_IoDivDirectIn 	constant NUMBER:=1;--製造受入
	pc_IoDivDirectOut 	constant NUMBER:=2;--製造払出
	pc_IoDivItemIn 		constant NUMBER:=3;--入荷受入
	pc_IoDivItemOut 	constant NUMBER:=4;--出荷払出
	pc_IoDivSupplyOut 	constant NUMBER:=5;--支給払出
	pc_IoDivPlanIn 		constant NUMBER:=6;--計画受入
	pc_IoDivPlanOut 	constant NUMBER:=7;--計画払出

	--生産計画区分
	pc_PlanDivFill 		constant NUMBER:=1;--充填補充
	pc_PlanDivOrder		constant NUMBER:=2;--受注生産

	--品目タイプ
	pc_ItemTypeMat		constant NUMBER:=-1;--原材料
	pc_ItemTypeHalf		constant NUMBER:=1;--中間品
	pc_ItemTypeReCyc	constant NUMBER:=2;--回収品
	pc_ItemTypeMain		constant NUMBER:=3;--主要製造品
	pc_ItemTypeSub		constant NUMBER:=4;--複製品
	pc_ItemTypeWaste	constant NUMBER:=5;--廃棄品

	--処方ヘッダ用途
	pc_RcpUsePlan		constant NUMBER:=1;--Planning
	pc_RcpUseCost		constant NUMBER:=2;--Costing
	pc_RcpUsePd			constant NUMBER:=3;--production-バッチ
	pc_RcpUseFill		constant NUMBER:=4;--充填
	--pc_RcpUsePack		constant NUMBER:=5;--詰替・貼替

	--処方ヘッダ生産ライン
	pc_RcpLine2			constant NUMBER:=2;--
	pc_RcpLineSample	constant NUMBER:=4;--サンプル

	--製造指図区分
	pc_DirDivBat		constant NUMBER:=1;--バッチ指図
	pc_DirDivFill		constant NUMBER:=2;--充填・包装指図
	pc_DirDivPack		constant NUMBER:=3;--詰替・貼替指図

	--製造指図ステータス
	pc_DirStsNone		constant NUMBER:=0;--未確定
	pc_DirStsOk			constant NUMBER:=1;--確定
	pc_DirStsComp		constant NUMBER:=2;--完了
	pc_DirStsClose		constant NUMBER:=3;--クローズ

	--ロット管理区分
	pc_LotCtrlOn		constant NUMBER:=1;--ロット管理する
	pc_LotCtrlOff		constant NUMBER:=0;--ロット管理しない

	--受注区分
	pc_OrdDivPd			constant NUMBER:=1;--製品
	pc_OrdDivSampleS	constant NUMBER:=2;--サンプル(有償)
	pc_OrdDivSampleF	constant NUMBER:=3;--サンプル(無償)
	pc_OrdDivTrial		constant NUMBER:=4;--トライアル
	pc_OrdDivChg		constant NUMBER:=5;--預かり品

	pc_LogOk			constant NUMBER:=0;--表示メッセージ（成功）
	pc_LogNg			constant NUMBER:=1;--表示メッセージ（失敗）
	pc_LogInf			constant NUMBER:=2;--情報ログ
	pc_LogErr			constant NUMBER:=9;--エラーログ

	--工程パターン
	pc_Ope2				constant VARCHAR2(20):='FP999';--工程２
	pc_OpeEtc			constant VARCHAR2(20):='FL999';--工程その他

END;
/
