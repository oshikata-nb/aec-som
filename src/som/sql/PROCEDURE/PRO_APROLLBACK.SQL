CREATE OR REPLACE PROCEDURE AP21.PRO_APROLLBACK
	(
		 P_STR_ORGANIZATION_CD	IN	NVARCHAR2
		,P_STR_PAYABLE_DATE		IN	NVARCHAR2
		,P_STR_INPUTOR_CD		IN	NVARCHAR2
		,P_NUM_RET				OUT	NUMBER
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_APROLLBACK
	処理内容	:	買掛ロールバック処理
	引数		:	P_STR_ORGANIZATION_CD	部署コード	NVARCHAR2型
	引数		:	P_STR_PAYABLE_DATE		買掛締め日	NVARCHAR2型
	引数		:	P_STR_INPUTOR_CD		登録者ＩＤ	NVARCHAR2型
	引数(戻値)	:	P_NUM_RET				実行結果(0:正常終了, 99:異常終了)	NUMBER型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.05	1.00	TOSCO       		creation
	2009.06.18								SOM殿向けカスタマイズ
*/----------------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_PayableTrgDiv0		CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_TARGET_DIVISION%TYPE		:= 0;	-- 買掛対象(未処理)
	c_PayableUpdDiv0		CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION%TYPE		:= 0;	-- 買掛更新フラグ(未処理)
	c_PayableUpdDiv1		CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION%TYPE		:= 1;	-- 買掛更新フラグ(更新済)
	daPayableDate			PAYABLE_HEADER.PAYABLE_DATE%TYPE;										-- 買掛締め日
BEGIN
	-- 文字型をDate型へキャスト
	daPayableDate := TO_DATE(P_STR_PAYABLE_DATE, 'YYYY/MM/DD');
	--------------------------------------------------------------------------------
	--仮締めデータ削除処理
	--------------------------------------------------------------------------------
	-------------------------------------------------
	--★仮締め買掛ヘッダー削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_PAYABLE_HEADER
	WHERE
		('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	;
	-------------------------------------------------
	--★仮締め買掛購買外注オーダー削除
	-------------------------------------------------
	DELETE FROM TMPPAY_PURCHASE_SUBCONTRACT
	WHERE
		('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	;
	-------------------------------------------------
	--★仮締め買掛入金トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_PAYABLE_CREDIT
	WHERE
		('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	;
	-------------------------------------------------
	--★仮締め買掛支払トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_PAYABLE_PAYMENT
	WHERE
		('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	;
	-------------------------------------------------
	--★仮締め買掛グループ間相殺トランザクション削除
	-------------------------------------------------
	DELETE FROM TMPPAY_OFFSET_GROUP_DATA
	WHERE
		('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	;
	--------------------------------------------------------------------------------
	--★買掛ヘッダー削除
	--------------------------------------------------------------------------------
	DELETE FROM PAYABLE_HEADER 
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		PAYABLE_DATE = daPayableDate;
	----------------------------------------------------------------------------------------------------------
	--各トランザクション更新処理
	----------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--★購買外注オーダーファイル更新
	--------------------------------------------------------------------------------
	UPDATE PURCHASE_SUBCONTRACT
		SET
			PAYABLE_TARGET_DIVISION		=	c_PayableTrgDiv0				-- 買掛対象(未処理)
		,	PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv0				-- 買掛更新フラグ(未処理)
		,	PAYABLE_NO					=	NULL							-- 買掛番号
		,	PAYABLE_UPDATE_DATE			=	NULL							-- 買掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv1				-- 買掛更新フラグ(更新済)
	AND		PAYABLE_UPDATE_DATE			=	daPayableDate					-- 買掛締め日
	;
	--------------------------------------------------------------------------------
	--★入金トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE CREDIT
		SET
			PAYABLE_TARGET_DIVISION		=	c_PayableTrgDiv0				-- 買掛対象(未処理)
		,	PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv0				-- 買掛更新フラグ(未処理)
		,	PAYABLE_NO					=	NULL							-- 買掛番号
		,	PAYABLE_UPDATE_DATE			=	NULL							-- 買掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv1				-- 買掛更新フラグ(更新済)
	AND		PAYABLE_UPDATE_DATE			=	daPayableDate					-- 買掛締め日
	;
	--------------------------------------------------------------------------------
	--★支払トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE PAYMENT
		SET
			PAYABLE_TARGET_DIVISION		=	c_PayableTrgDiv0				-- 買掛対象(未処理)
		,	PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv0				-- 買掛更新フラグ(未処理)
		,	PAYABLE_NO					=	NULL							-- 買掛番号
		,	PAYABLE_UPDATE_DATE			=	NULL							-- 買掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv1				-- 買掛更新フラグ(更新済)
	AND		PAYABLE_UPDATE_DATE			=	daPayableDate					-- 買掛締め日
	;
	--------------------------------------------------------------------------------
	--★グループ間相殺トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE OFFSET_GROUP_DATA
		SET
			PAYABLE_TARGET_DIVISION		=	c_PayableTrgDiv0				-- 買掛対象(未処理)
		,	PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv0				-- 買掛更新フラグ(未処理)
		,	PAYABLE_NO					=	NULL							-- 買掛番号
		,	PAYABLE_UPDATE_DATE			=	NULL							-- 買掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		PAYABLE_UPDATE_DIVISION		=	c_PayableUpdDiv1				-- 買掛更新フラグ(更新済)
	AND		PAYABLE_UPDATE_DATE			=	daPayableDate					-- 買掛締め日
	;
	--------------------------------------------------------------------------------
	--戻り値設定
	--------------------------------------------------------------------------------
	P_NUM_RET := 0;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		--ロールバック
		ROLLBACK;
		--戻り値設定
		P_NUM_RET := 99;
END PRO_APROLLBACK;
/
