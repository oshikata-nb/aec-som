CREATE OR REPLACE PROCEDURE AP21.PRO_PAYMENTROLLBACK
	(
		 P_STR_ORGANIZATION_CD	IN	NVARCHAR2
		,P_STR_VENDER_CD		IN	NVARCHAR2
		,P_STR_PAYABLE_DATE		IN	NVARCHAR2
		,P_STR_INPUTOR_CD		IN	NVARCHAR2
		,P_NUM_RET				OUT	NUMBER
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_PAYMENTROLLBACK
	処理内容	:	支払更新ロールバック処理
	引数		:	P_STR_ORGANIZATION_CD	部署コード		NVARCHAR2型
	引数		:	P_STR_VENDER_CD			支払先コード	NVARCHAR2型
	引数		:	P_STR_PAYABLE_DATE		支払締め日		NVARCHAR2型
	引数		:	P_STR_INPUTOR_CD		登録者ＩＤ		NVARCHAR2型
	引数(戻値)	:	P_NUM_RET				実行結果(0:正常終了, 99:異常終了)	NUMBER型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.04	1.00	TOSCO       		creation
	2009.06.18								SOM殿向けカスタマイズ
*/----------------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_PaymentTrgDiv0	CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_TARGET_DIVISION%TYPE		:= 0;	-- 支払対象(未処理)
	c_PaymentUpdDiv0	CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION%TYPE		:= 0;	-- 支払更新フラグ(未処理)
	c_PaymentUpdDiv1	CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION%TYPE		:= 1;	-- 支払更新フラグ(更新済)
	daPayableDate		PAYMENT_HEADER.PAYABLE_DATE%TYPE;										-- 支払締め日
BEGIN
	-- 文字型をDate型へキャスト
	daPayableDate := TO_DATE(P_STR_PAYABLE_DATE, 'YYYY/MM/DD');
	--------------------------------------------------------------------------------
	--★支払ヘッダー削除
	--------------------------------------------------------------------------------
	DELETE FROM PAYMENT_HEADER 
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR SUPPLIER_CD = P_STR_VENDER_CD)
	AND		PAYABLE_DATE = daPayableDate;
	----------------------------------------------------------------------------------------------------------
	--各トランザクション更新処理
	----------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--★購買外注オーダーファイル更新
	--------------------------------------------------------------------------------
	UPDATE PURCHASE_SUBCONTRACT
		SET
			PAYMENT_TARGET_DIVISION		=	c_PaymentTrgDiv0			-- 支払対象(未処理)
		,	PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv0			-- 支払更新フラグ(未処理)
		,	PAYMENT_NO					=	NULL						-- 支払番号
		,	PAYMENT_UPDATE_DATE			=	NULL						-- 支払締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
			 (VENDER_CD = P_STR_VENDER_CD AND PAYEE_CD IS NULL) OR
			 PAYEE_CD = P_STR_VENDER_CD
			)
	AND		PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv1			-- 支払更新フラグ(更新済)
	AND		PAYMENT_UPDATE_DATE		    =   daPayableDate				-- 支払締め日
	;
	--------------------------------------------------------------------------------
	--★入金トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE CREDIT
		SET
			PAYMENT_TARGET_DIVISION		=	c_PaymentTrgDiv0			-- 支払対象(未処理)
		,	PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv0			-- 支払更新フラグ(未処理)
		,	PAYMENT_NO					=	NULL						-- 支払番号
		,	PAYMENT_UPDATE_DATE			=	NULL						-- 支払締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
	AND		PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv1			-- 支払更新フラグ(更新済)
	AND		PAYMENT_UPDATE_DATE		    =   daPayableDate				-- 支払締め日
	;
	--------------------------------------------------------------------------------
	--★支払トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE PAYMENT
		SET
			PAYMENT_TARGET_DIVISION		=	c_PaymentTrgDiv0			-- 支払対象(未処理)
		,	PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv0			-- 支払更新フラグ(未処理)
		,	PAYMENT_NO					=	NULL						-- 支払番号
		,	PAYMENT_UPDATE_DATE			=	NULL						-- 支払締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR SUPPLIER_CD = P_STR_VENDER_CD)
	AND		PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv1			-- 支払更新フラグ(更新済)
	AND		PAYMENT_UPDATE_DATE		    =   daPayableDate				-- 支払締め日
	;
	--------------------------------------------------------------------------------
	--★グループ間相殺トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE OFFSET_GROUP_DATA
		SET
			PAYMENT_TARGET_DIVISION		=	c_PaymentTrgDiv0			-- 支払対象(未処理)
		,	PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv0			-- 支払更新フラグ(未処理)
		,	PAYMENT_NO					=	NULL						-- 支払番号
		,	PAYMENT_UPDATE_DATE			=	NULL						-- 支払締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
	AND		PAYMENT_UPDATE_DIVISION		=	c_PaymentUpdDiv1			-- 支払更新フラグ(更新済)
	AND		PAYMENT_UPDATE_DATE		    =   daPayableDate				-- 支払締め日
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
END PRO_PAYMENTROLLBACK;
/
