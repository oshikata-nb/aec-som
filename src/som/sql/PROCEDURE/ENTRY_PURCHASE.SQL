CREATE OR REPLACE PROCEDURE ENTRY_PURCHASE(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--発注データ検索
		cursor curGetPurchase(strPurchaseNo varchar2) is
		select * from PURCHASE_SUBCONTRACT where   PURCHASE_NO = strPurchaseNo;
		rtGetPurchase curGetPurchase%rowtype;
		OpNo number;
		InOutKbn number;
		FaultQty number;
		ChkFlg number;
		CalcCostFlg number;
		Qty number;
		InOutDivision number;
		FuncDivision number;
		PARA VARCHAR2(255);
		InOutSourceDellFlg number;
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '購買NOなし。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーなし。';
			GOTO P_END;
		END IF;
		OPEN curGetPurchase(STR_CODE);
		FETCH curGetPurchase INTO rtGetPurchase;
		IF curGetPurchase%NOTFOUND = TRUE THEN
			CLOSE curGetPurchase;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curGetPurchase;
		-- 固定値
		FaultQty := 0; -- 不良数量
		CASE
			WHEN LNG_FLG = 1 THEN
				-- 登録時
				OpNo := 107;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty := GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,rtGetPurchase.ORDER_CONVERT_QUANTITY,rtGetPurchase.ORDER_QUANTITY); -- 数量
				InOutDivision := 3; -- 3:入荷受入
				InOutSourceDellFlg := 0; -- 0:入力
				FuncDivision := 1; -- 1:仕入
			WHEN LNG_FLG = 2 THEN
				-- 登録取消時
				OpNo := 107;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty := -GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,rtGetPurchase.ORDER_CONVERT_QUANTITY,rtGetPurchase.ORDER_QUANTITY); -- 数量
				InOutDivision := 3; -- 3:入荷受入
				InOutSourceDellFlg := 1; --  1:削除
				FuncDivision := 1; -- 1:仕入
			WHEN LNG_FLG = 3 THEN
				-- 確定時
				OpNo := 7;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty := GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,rtGetPurchase.ORDER_CONVERT_QUANTITY,rtGetPurchase.ORDER_QUANTITY); -- 数量
				InOutDivision := 3; -- 3:入荷受入
				InOutSourceDellFlg := 0; -- 0:入力
				FuncDivision := 1; -- 1:仕入
			WHEN LNG_FLG = 4 THEN
				-- 確定取消時
				OpNo := 7;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty := -GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,rtGetPurchase.ORDER_CONVERT_QUANTITY,rtGetPurchase.ORDER_QUANTITY); -- 数量
				InOutDivision := 3; -- 3:入荷受入
				InOutSourceDellFlg := 0; -- 0:入力
				FuncDivision := 1; -- 1:仕入
			ELSE
				OUT_PARA := '処理選択が不正です。';
				GOTO P_END;
		END CASE;			
		--更新引数セット1-10
		PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
		PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
		PAK_ALLZAITABLE.STR_ITEMCODE := rtGetPurchase.ITEM_CD;
		PAK_ALLZAITABLE.LNG_QTY := Qty;
		PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
		PAK_ALLZAITABLE.DTE_INOUTDATE := rtGetPurchase.SUGGESTED_DELIVERLIMIT_DATE;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := null;
		PAK_ALLZAITABLE.LNG_COST := rtGetPurchase.ORDER_UNITPRICE;
		PAK_ALLZAITABLE.DTE_UKEDATE := rtGetPurchase.STOCKING_DATE;
		PAK_ALLZAITABLE.LNG_COSTFLG := rtGetPurchase.TMP_UNITPRICE_FLG;
		--11-20
		PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
		PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
		PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
		PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
		PAK_ALLZAITABLE.STR_LOTCODE := null;
		PAK_ALLZAITABLE.DTE_START_DATE := null;
		PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
		PAK_ALLZAITABLE.DTE_END_DATE := null;
		PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
		PAK_ALLZAITABLE.STR_ODERNO := rtGetPurchase.BUY_SUBCONTRACT_ORDER_NO;
		--21-30
		PAK_ALLZAITABLE.LNG_ODERLINENO := 0;
		PAK_ALLZAITABLE.LNG_INOUTPRICE := rtGetPurchase.ORDER_UNITPRICE;
		PAK_ALLZAITABLE.LNG_INOUTCOST := rtGetPurchase.SUPPLIER_ORD_AMOUNT;
		PAK_ALLZAITABLE.STR_REMARK := rtGetPurchase.REMARK;
		PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
		PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
		PAK_ALLZAITABLE.STR_SECTIONCD := rtGetPurchase.CHARGE_ORGANIZATION_CD;
		PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
		--31-40
		PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
		PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
		PAK_ALLZAITABLE.STR_REASON := null;
		--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
		--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
		PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := null;
		PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
		PAK_ALLZAITABLE.STR_SLIPNO := rtGetPurchase.SLIP_NO;
		--41-46
		PAK_ALLZAITABLE.LNG_SLIPLINENO := rtGetPurchase.ROW_NO;
		PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
		PAK_ALLZAITABLE.STR_ORDERNO := null;
		PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
		PAK_ALLZAITABLE.STR_LASTINNO := null;
		PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
		PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
		PAK_ALLZAITABLE.LNG_HANPAQTY := null;
		PAK_ALLZAITABLE.STR_ALIAS_LOT := null;
		OUT_PARA := '';
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
		PAK_ALLZAITABLE.STR_RYCD := null;

		--パッケージ実行
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		--戻り値セット
		OUT_PARA := PARA;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curGetPurchase%ISOPEN THEN
				CLOSE curGetPurchase;
			END IF;
			dbms_output.put_line('ENTRY_PURCHASE ' || sqlcode || sqlerrm);
		OUT_PARA := 'ENTRY_PURCHASE ' || sqlcode || sqlerrm;
		
    END;
/
