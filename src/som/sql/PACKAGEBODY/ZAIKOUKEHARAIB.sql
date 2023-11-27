CREATE OR REPLACE PACKAGE BODY AP21.ZAIKOUKEHARAI
IS
--■■■■■■■■■■■■■■■■■■■
--■       受注入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_ORDER(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_ROW_NO IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--受注データ検索
		cursor curHead(strNo varchar2) is
		select * from ORDER_HEAD where ORDER_NO =  strNo;
		rtHead curHead%rowtype;
		-- カーソル変数のオブジェクト定義
		type cur_get_type is ref cursor  ;
		-- カーソル変数定義
		curDetail cur_get_type;
		rtDetail ORDER_DETAIL%ROWTYPE;
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
		InOutDate date;
		addOpno number;
		strSql varchar(10000);
		strMes varchar(1000);
		strTmp varchar(1000);
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '受注番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 固定値
		FaultQty := 0; -- 不良数量
		-- 受注区分|1:在庫引当,2:受注生産,3:仕入直送品,4:例外出荷
		CASE rtHead.ORDER_DIVISION 
			WHEN 1 THEN
				addOpno := 99;
			WHEN 2 THEN
				addOpno := 101;
			WHEN 3 THEN
				addOpno := 101;
			WHEN 4 THEN
				addOpno := 99;
			ELSE
				OUT_PARA := '受注区分が不正です。';
				GOTO P_END;
		END CASE;
		IF LNG_FLG = 5 THEN
			IF addOpno = 99 THEN
				addOpno := 97;
			ELSE
			   	addOpno := 12;
			END IF;
		END IF;
		strSql := 'SELECT * FROM ORDER_DETAIL WHERE ORDER_NO = ''' || STR_CODE || ''' ';
		IF LNG_ROW_NO IS NULL THEN
			NULL;
		ELSE
			strSql := strSql || ' AND ROW_NO = ' || LNG_ROW_NO ;
		END IF;
		OPEN curDetail FOR strSql;
		OUT_PARA := '';
		strMes := '';
		LOOP
			PARA := '';
			FETCH curDetail INTO rtDetail;
			IF curDetail%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			CASE
				WHEN LNG_FLG = 1 THEN
					-- 登録時
					OpNo := addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  (NVL(rtDetail.ORDER_QTY,0) + NVL(rtDetail.MATSS,0))  ; -- 数量
					InOutDate := NVL(rtHead.SCHEDULED_SHIPPING_DATE,NVL(rtHead.SUGGESTED_DELIVERLIMIT,rtHead.ORDER_DATE));
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 9; -- 9:出荷
					IF addOpno = 99 THEN
						strMes := '';
						CHK_INVENTORY(rtDetail.ITEM_CD,Qty,false,strMes);
						IF strMes = '' or strMes is null THEN
							null;
						ELSE
							OUT_PARA := strMes;
							EXIT;
						END IF;
					END IF;
				WHEN LNG_FLG = 2 THEN
					OpNo := addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -(NVL(rtDetail.ORDER_QTY,0) + NVL(rtDetail.MATSS,0)); -- 数量
					InOutDate := NVL(rtHead.SCHEDULED_SHIPPING_DATE,NVL(rtHead.SUGGESTED_DELIVERLIMIT,rtHead.ORDER_DATE));
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 1; 
					FuncDivision := 89; -- 89:出荷取消
				WHEN LNG_FLG = 5 THEN
					IF rtDetail.SHIPPING_NO is null OR rtDetail.SHIPPING_NO = '' then
						NULL;
					ELSE
						ENTRY_SHIPPING(5,rtDetail.SHIPPING_NO,null,STR_LOGIN_USER,PARA);
						IF PARA = 'COMPLETE' THEN
							NULL;
						ELSE
							OUT_PARA := PARA;
							EXIT;
						END IF;
					END IF;
					OpNo := addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  (NVL(rtDetail.ORDER_QTY,0) + NVL(rtDetail.MATSS,0))  ; -- 数量
					InOutDate := NVL(rtHead.SCHEDULED_SHIPPING_DATE,NVL(rtHead.SUGGESTED_DELIVERLIMIT,rtHead.ORDER_DATE));
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 9; -- 89:出荷取消
				ELSE
					OUT_PARA := '処理選択が不正です。';
					EXIT;
			END CASE;			
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtDetail.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := null;
			PAK_ALLZAITABLE.LNG_COST := rtDetail.ORDER_UNITPRICE;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := null;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY :=null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := null;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO :=  rtDetail.ORDER_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := rtDetail.ROW_NO;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := rtDetail.ORDER_UNITPRICE;
			PAK_ALLZAITABLE.LNG_INOUTCOST := NVL(rtDetail.ORDER_UNITPRICE,0) * Qty;
			PAK_ALLZAITABLE.STR_REMARK := rtHead.ORDER_SUMMERY;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := null; --func_get_section_for_shipping(rtHead.VENDER_CD,'TS');
			PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
			--31-40
			PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
			PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
			PAK_ALLZAITABLE.STR_REASON := '';
			--PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
			--PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
			PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := null;
			PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
			PAK_ALLZAITABLE.STR_SLIPNO := rtDetail.SHIPPING_NO;
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
			PAK_ALLZAITABLE.STR_ORDERNO := rtDetail.ORDER_NO;
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
			PAK_ALLZAITABLE.STR_LASTINNO := null;
			PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
			PAK_ALLZAITABLE.LNG_HANPAQTY := null;
			PAK_ALLZAITABLE.STR_ALIAS_LOT := null;
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
			PAK_ALLZAITABLE.STR_RYCD := null;
			--パッケージ実行
			PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
			OUT_PARA := PARA;
			IF PARA = 'COMPLETE' THEN
				NULL;
			ELSE
				OUT_PARA := PARA || '　受注番号：' || rtDetail.ORDER_NO || '-' || rtDetail.ROW_NO;
				EXIT;
			END IF;
		END LOOP;
		CLOSE curDetail;
<<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			IF curDetail%ISOPEN THEN
				CLOSE curDetail;
			END IF;
			dbms_output.put_line('ENTRY_ORDER ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_ORDER ' || sqlcode || sqlerrm;
    END;
--■■■■■■■■■■■■■■■■■■■
--■       出荷入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_SHIPPING(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_ROW_NO IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--出荷データ検索
		cursor curHead(strNo varchar2) is
		select SHIPPING.*,NVL(ORDER_HEAD.ORDER_DIVISION,2) AS O_DIV from SHIPPING,ORDER_HEAD where SHIPPING.ORDER_NO = ORDER_HEAD.ORDER_NO(+)  AND SHIPPING_NO =  strNo;
		rtHead curHead%rowtype;
		-- カーソル変数のオブジェクト定義
		type cur_get_type is ref cursor  ;
		-- カーソル変数定義
		curDetail cur_get_type;
		rtDetail SHIPPING_DETAIL%ROWTYPE;
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
		LotTrace number;
		InOutDate date;
		addOpno number;
		strSql varchar(10000);
		strMes varchar(1000);
		strTmp varchar(1000);
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '出荷番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 固定値
		FaultQty := 0; -- 不良数量
		-- 受注区分|1:在庫引当,2:受注生産,3:仕入直送品,4:例外出荷
		CASE rtHead.O_DIV 
			WHEN 1 THEN
				addOpno := 98;
			WHEN 2 THEN
				addOpno := 1;
			WHEN 3 THEN
				addOpno := 1;
			WHEN 4 THEN
				addOpno := 98;
			ELSE
				OUT_PARA := '受注区分が不正です。';
				GOTO P_END;
		END CASE;
		strSql := 'SELECT * FROM SHIPPING_DETAIL WHERE SHIPPING_NO = ''' || STR_CODE || ''' ';
		IF LNG_ROW_NO IS NULL THEN
			NULL;
		ELSE
			strSql := strSql || ' AND SHIPPING_ROW_NO = ' || LNG_ROW_NO ;
		END IF;
		OPEN curDetail FOR strSql;
		OUT_PARA := '';
		strMes := '';
		LOOP
			PARA := '';
			FETCH curDetail INTO rtDetail;
			IF curDetail%NOTFOUND = TRUE THEN
				EXIT;
			END IF;

			CASE
				WHEN LNG_FLG = 1 THEN
					-- 登録時
					OpNo := addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtDetail.SHIPPING_INSTRUCTION ; -- 数量
					InOutDate := NVL(rtHead.SCHEDULED_SHIPPING_DATE,NVL(rtHead.SUGGESTED_DELIVERLIMIT,rtHead.SHIPPING_INSTRUCT_DATE));
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 9; -- 9:出荷
					LotTrace := 0;
				WHEN LNG_FLG = 2 THEN
					OpNo := addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtDetail.SHIPPING_INSTRUCTION,0) ; -- 数量
					InOutDate := NVL(rtHead.SCHEDULED_SHIPPING_DATE,NVL(rtHead.SUGGESTED_DELIVERLIMIT,rtHead.SHIPPING_INSTRUCT_DATE));
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 1; 
					FuncDivision := 89; -- 89:出荷取消
					LotTrace := 0;
				WHEN LNG_FLG = 3 THEN
					OpNo := 2;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtDetail.SHIPPING_RESULT_QUANTITY ; -- 数量
					InOutDate := rtDetail.SHIPPING_RESULT_DATE;
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 9; -- 89:出荷取消
					LotTrace := 0;
				WHEN LNG_FLG = 4 THEN
					OpNo := 2;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtDetail.SHIPPING_RESULT_QUANTITY,0); -- 数量
					InOutDate := rtDetail.SHIPPING_RESULT_DATE;
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 89; -- 89:出荷取消
					LotTrace := 1;
				WHEN LNG_FLG = 5 THEN
					OpNo := 12;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtDetail.SHIPPING_RESULT_QUANTITY,0); -- 数量
					InOutDate := rtDetail.SHIPPING_RESULT_DATE;
					InOutDivision := 4; -- 4:出荷払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 9; -- 89:出荷取消
					LotTrace := 0;
				ELSE
					OUT_PARA := '処理選択が不正です。';
					EXIT;
			END CASE;			
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtHead.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := rtDetail.LOCATION_CD;
			PAK_ALLZAITABLE.LNG_COST := null;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := null;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY :=null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := rtDetail.LOT_NO;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO :=  rtDetail.SHIPPING_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := rtDetail.SHIPPING_ROW_NO;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := null;
			PAK_ALLZAITABLE.LNG_INOUTCOST := null;
			PAK_ALLZAITABLE.STR_REMARK := null;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_for_shipping(rtHead.VENDER_CD,'TS');
			PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
			--31-40
			PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
			PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
			PAK_ALLZAITABLE.STR_REASON := '';
			--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
			--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
			PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := LotTrace;
			PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
			PAK_ALLZAITABLE.STR_SLIPNO := rtHead.ORDER_NO;
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := rtHead.ORDER_ROW_NO;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
			PAK_ALLZAITABLE.STR_ORDERNO := rtHead.ORDER_NO;
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
			PAK_ALLZAITABLE.STR_LASTINNO := null;
			PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
			PAK_ALLZAITABLE.LNG_HANPAQTY := null;
			PAK_ALLZAITABLE.STR_ALIAS_LOT := null;
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
			PAK_ALLZAITABLE.STR_RYCD := null;
			--パッケージ実行
			PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
			OUT_PARA := PARA;
			IF PARA = 'COMPLETE' THEN
				NULL;
			ELSE
				OUT_PARA := PARA || '　出荷番号：' || rtDetail.SHIPPING_NO || '-' || rtDetail.SHIPPING_ROW_NO;
				EXIT;
			END IF;
		END LOOP;
		CLOSE curDetail;
<<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			IF curDetail%ISOPEN THEN
				CLOSE curDetail;
			END IF;
			dbms_output.put_line('ENTRY_SHIPPING ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_SHIPPING ' || sqlcode || sqlerrm;
    END;
FUNCTION GET_PURCHASE_QTY(itemcd VARCHAR2,kg number,ko number) RETURN number IS
CT number;
typedivision number;
BEGIN
	SELECT COUNT(*) INTO CT FROM ITEM WHERE ITEM_CD = itemcd;
   	IF CT = 1 THEN
		SELECT type_division  INTO typedivision  FROM ITEM WHERE ITEM_CD = itemcd;
   	END IF;
	IF typedivision = 1 OR typedivision  = 3 THEN
		RETURN KG;
	END IF;
   	RETURN KO;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		RAISE;



END;

--■■■■■■■■■■■■■■■■■■■
--■       発注入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_PURCHASE(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
			OUT_PARA := '購買NOがありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
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
		PAK_ALLZAITABLE.STR_SECTIONCD := null; --func_get_section_for_shipping(rtGetPurchase.VENDER_CD,'SI');
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
		IF PARA = 'COMPLETE' THEN
			NULL;
		ELSE
			OUT_PARA := PARA || '　購買NO：' || rtGetPurchase.BUY_SUBCONTRACT_ORDER_NO;
		END IF;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curGetPurchase%ISOPEN THEN
				CLOSE curGetPurchase;
			END IF;
			dbms_output.put_line('ENTRY_PURCHASE ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_PURCHASE ' || sqlcode || sqlerrm;
		
    END;
    
--■■■■■■■■■■■■■■■■■■■
--■       受入入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE RECEIVE_PURCHASE(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--発注データ検索
		cursor curGetPurchase(strPurchaseNo varchar2) is
		select * from PURCHASE_SUBCONTRACT where PURCHASE_NO = strPurchaseNo;
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
		QTYKG number;
		QTYKO number;
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '購買NOがありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
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
				OpNo := 8;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty := GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,rtGetPurchase.ACCEPT_CONVERT_QUANTITY,rtGetPurchase.ACCEPT_QUANTITY); -- 数量
				InOutDivision := 3; -- 3:入荷受入
				InOutSourceDellFlg := 0; 
				FuncDivision := 1; -- 1:仕入
			WHEN LNG_FLG = 2 THEN
				-- 登録取消時
				OpNo := 8;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty := -GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,rtGetPurchase.ACCEPT_CONVERT_QUANTITY,rtGetPurchase.ACCEPT_QUANTITY); -- 数量
				InOutDivision := 3; -- 3:入荷受入
				InOutSourceDellFlg := 0; 
				FuncDivision := 81; -- 81:仕入取消
			WHEN LNG_FLG = 5 THEN
				OpNo := 11;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				QTYKG := NVL(rtGetPurchase.ORDER_CONVERT_QUANTITY,0) - NVL(rtGetPurchase.ACCEPT_CONVERT_QUANTITY,0);
				QTYKO := NVL(rtGetPurchase.ORDER_QUANTITY,0) - NVL(rtGetPurchase.ACCEPT_QUANTITY,0);
				Qty :=  GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,QTYKG,QTYKO); -- 数量
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
		PAK_ALLZAITABLE.DTE_INOUTDATE := rtGetPurchase.ACCEPT_DATE;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := NVL(rtGetPurchase.STOCK_LOCATION_CD,rtGetPurchase.HOUSING_LOCATION_CD);
		PAK_ALLZAITABLE.LNG_COST := rtGetPurchase.HOUSING_UNITPRICE;
		PAK_ALLZAITABLE.DTE_UKEDATE := rtGetPurchase.STOCKING_DATE;
		PAK_ALLZAITABLE.LNG_COSTFLG := rtGetPurchase.TMP_UNITPRICE_FLG;
		--11-20
		PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
		PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
		PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
		PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
		PAK_ALLZAITABLE.STR_LOTCODE := rtGetPurchase.LOT_NO;
		PAK_ALLZAITABLE.DTE_START_DATE := null;
		PAK_ALLZAITABLE.DTE_ISSUE_DATE := rtGetPurchase.ACCEPT_DATE;
		PAK_ALLZAITABLE.DTE_END_DATE := null;
		PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
		PAK_ALLZAITABLE.STR_ODERNO := rtGetPurchase.BUY_SUBCONTRACT_ORDER_NO;
		--21-30
		PAK_ALLZAITABLE.LNG_ODERLINENO := null;
		PAK_ALLZAITABLE.LNG_ODERLINENO := 0;
		PAK_ALLZAITABLE.LNG_INOUTPRICE := rtGetPurchase.HOUSING_UNITPRICE;
		PAK_ALLZAITABLE.LNG_INOUTCOST := rtGetPurchase.STOCKING_AMOUNT;
		PAK_ALLZAITABLE.STR_REMARK := rtGetPurchase.REMARK;
		PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
		PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
		PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_for_shipping(rtGetPurchase.VENDER_CD,'SI');
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
		PAK_ALLZAITABLE.STR_ALIAS_LOT := rtGetPurchase.SUPPLIER_LOTNO;
		OUT_PARA := '';
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := rtGetPurchase.ACCEPT_DATE;
		PAK_ALLZAITABLE.STR_RYCD := null;
		
		--パッケージ実行
		IF rtGetPurchase.ACCEPT_DATE is NULL and LNG_FLG = 2 THEN
			OUT_PARA := 'COMPLETE';
		ELSE
			PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
			--戻り値セット
			OUT_PARA := PARA;
			IF PARA = 'COMPLETE' THEN
				NULL;
			ELSE
				OUT_PARA := PARA || '　購買NO：' || rtGetPurchase.BUY_SUBCONTRACT_ORDER_NO;
			END IF;
		END IF;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curGetPurchase%ISOPEN THEN
				CLOSE curGetPurchase;
			END IF;
			dbms_output.put_line('RECEIVE_PURCHASE ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-RECEIVE_PURCHASE ' || sqlcode || sqlerrm;
		
    END;
--■■■■■■■■■■■■■■■■■■■
--■       受入完了
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE COMPLETE_PURCHASE(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--発注データ検索
		cursor curGetPurchase(strPurchaseNo varchar2) is
		select * from PURCHASE_SUBCONTRACT where BUY_SUBCONTRACT_ORDER_NO = strPurchaseNo;
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
		ct number;
		QTYKG number;
		QTYKO number;
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '発注番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		select count(*) into ct from PURCHASE_SUBCONTRACT where BUY_SUBCONTRACT_ORDER_NO = str_code;
		if ct <1 then
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
			
		OPEN curGetPurchase(STR_CODE);
		LOOP
			FETCH curGetPurchase INTO rtGetPurchase;
			IF curGetPurchase%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			-- 固定値
			FaultQty := 0; -- 不良数量
			OpNo := 11;
			InOutKbn := 0; -- 0:入庫
			ChkFlg := 1;  -- 1:在庫管理区分チェックをする
			CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
			QTYKG := NVL(rtGetPurchase.ORDER_CONVERT_QUANTITY,0) - NVL(rtGetPurchase.ACCEPT_CONVERT_QUANTITY,0);
			QTYKO := NVL(rtGetPurchase.ORDER_QUANTITY,0) - NVL(rtGetPurchase.ACCEPT_QUANTITY,0);
			Qty :=  GET_PURCHASE_QTY(rtGetPurchase.ITEM_CD,QTYKG,QTYKO); -- 数量
			InOutDivision := 3; -- 3:入荷受入
			InOutSourceDellFlg := 0; -- 0:入力
			FuncDivision := 1; -- 1:仕入
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtGetPurchase.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := rtGetPurchase.ACCEPT_DATE;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := NVL(rtGetPurchase.STOCK_LOCATION_CD,rtGetPurchase.HOUSING_LOCATION_CD);
			PAK_ALLZAITABLE.LNG_COST := rtGetPurchase.HOUSING_UNITPRICE;
			PAK_ALLZAITABLE.DTE_UKEDATE := rtGetPurchase.STOCKING_DATE;
			PAK_ALLZAITABLE.LNG_COSTFLG := rtGetPurchase.TMP_UNITPRICE_FLG;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := rtGetPurchase.LOT_NO;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := rtGetPurchase.ACCEPT_DATE;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO := rtGetPurchase.BUY_SUBCONTRACT_ORDER_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := 0;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := rtGetPurchase.HOUSING_UNITPRICE;
			PAK_ALLZAITABLE.LNG_INOUTCOST := rtGetPurchase.STOCKING_AMOUNT;
			PAK_ALLZAITABLE.STR_REMARK := rtGetPurchase.REMARK;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := null; --func_get_section_for_shipping(rtGetPurchase.VENDER_CD,'SI');
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
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
			PAK_ALLZAITABLE.STR_RYCD := null;

			OUT_PARA := '';

			--パッケージ実行
			PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
			--戻り値セット
			OUT_PARA := PARA;
			IF PARA = 'COMPLETE' THEN
				NULL;
			ELSE
				OUT_PARA := PARA || '　購買NO：' || rtGetPurchase.BUY_SUBCONTRACT_ORDER_NO;
				EXIT;
			END IF;
		END LOOP;
		CLOSE curGetPurchase;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curGetPurchase%ISOPEN THEN
				CLOSE curGetPurchase;
			END IF;
			dbms_output.put_line('COMPLETE_PURCHASE ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-COMPLETE_PURCHASE ' || sqlcode || sqlerrm;
		
    END;
--■■■■■■■■■■■■■■■■■■■
--■       包装・製造指図入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_DIRECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--包装・製造指図データ検索
		cursor curHead(lngDiv NUMBER,strNo varchar2) is
		select * from DIRECTION_HEADER where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo;
		rtHead curHead%rowtype;
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
		InOutDate date;
		Location VARCHAR2(20);
		addOpno number;
		hinKbn number;
	BEGIN 
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分がありません。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(LNG_DIV,STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		
		-- 固定値
		FaultQty := 0; -- 不良数量
		IF rtHead.DIRECTION_DIVISION = 1 THEN
			addOpno := 0;
			hinKbn := 3; --中間品
		else
			addOpno := 2;
			IF rtHead.DIRECTION_DIVISION = 2 THEN
				hinKbn := 0; --製品
			ELSE
				hinKbn := null ; --その他
			end if;				
		end if;
		-- 分割では入出庫リソースを作らない。
		IF NVL(rtHead.DIVIDE_FLAG,0) = 1 THEN
			OUT_PARA := 'COMPLETE';
			GOTO P_END;
		END IF;
			
		CASE
			WHEN LNG_FLG = 1 THEN
				-- 登録時
				OpNo := 103 + addOpno;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty :=  rtHead.PLANED_QTY; -- 数量
				InOutDate := rtHead.PLANED_EDATE; -- 入庫予定
				InOutDivision := 1; -- 1:製造受入
				InOutSourceDellFlg := 0; 
				FuncDivision := 4; -- 4:製造
			WHEN LNG_FLG = 2 THEN
				-- 登録取消時
				OpNo := 103 + addOpno;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty :=  -NVL(rtHead.PLANED_QTY,0); -- 数量
				InOutDate := rtHead.PLANED_EDATE; -- 入庫予定
				InOutDivision := 1; -- 1:製造受入
				InOutSourceDellFlg := 1; 
				FuncDivision := 84; -- 84:製造取消
			WHEN LNG_FLG = 3 THEN
				-- 確定時
				OpNo := 3;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty :=  rtHead.PLANED_QTY; -- 数量
				InOutDate := rtHead.PLANED_EDATE; -- 入庫予定
				InOutDivision := 1; -- 1:製造受入
				InOutSourceDellFlg := 1; 
				FuncDivision := 4; -- 4:製造
			WHEN LNG_FLG = 4 THEN
				-- 登録取消時
				OpNo := 3;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty :=  -NVL(rtHead.PLANED_QTY,0); -- 数量
				InOutDate := rtHead.PLANED_EDATE; -- 入庫予定
				InOutDivision := 1; -- 1:製造受入
				InOutSourceDellFlg := 1; 
				FuncDivision := 84; -- 84:製造取消
			ELSE
				OUT_PARA := '処理選択が不正です。';
				GOTO P_END;
		END CASE;			
		--更新引数セット1-10
		PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
		PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
		PAK_ALLZAITABLE.STR_ITEMCODE := rtHead.ITEM_CD;
		PAK_ALLZAITABLE.LNG_QTY := Qty;
		PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
		PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := null;
		PAK_ALLZAITABLE.LNG_COST := null;
		PAK_ALLZAITABLE.DTE_UKEDATE := null;
		PAK_ALLZAITABLE.LNG_COSTFLG := null;
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
		PAK_ALLZAITABLE.STR_ODERNO := rtHead.DIRECTION_NO;
		--21-30
		PAK_ALLZAITABLE.LNG_ODERLINENO := rtHead.DIRECTION_DIVISION;
		PAK_ALLZAITABLE.LNG_INOUTPRICE := null;
		PAK_ALLZAITABLE.LNG_INOUTCOST := null;
		PAK_ALLZAITABLE.STR_REMARK := rtHead.REMARK;
		PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
		PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
		PAK_ALLZAITABLE.STR_SECTIONCD := null; --func_get_section_from_item(rtHead.ITEM_CD,hinKbn,rtHead.DIRECTION_NO);
		PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
		--31-40
		PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
		PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
		PAK_ALLZAITABLE.STR_REASON :=null;
		--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
		--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
		PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := null;
		PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
		PAK_ALLZAITABLE.STR_SLIPNO := null;
		--41-46
		PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
		PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
		PAK_ALLZAITABLE.STR_ORDERNO := null;
		PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
		PAK_ALLZAITABLE.STR_LASTINNO := null;
		PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
		PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
		PAK_ALLZAITABLE.LNG_HANPAQTY := null;
		PAK_ALLZAITABLE.STR_ALIAS_LOT := null;
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := InOutDate; --rtHead.CERTIFICATION_DATE;
		PAK_ALLZAITABLE.STR_RYCD := null;

		OUT_PARA := '';

		--パッケージ実行
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		--戻り値セット
		OUT_PARA := PARA;
		IF PARA = 'COMPLETE' THEN
			NULL;
		ELSE
			OUT_PARA := PARA || '　指図番号：' || rtHead.DIRECTION_NO;
		END IF;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			dbms_output.put_line('ENTRY_DIRECTION ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_DIRECTION ' || sqlcode || sqlerrm;
		
    END;
--■■■■■■■■■■■■■■■■■■■
--■   レシピに従い製造指図を作成する
--■■■■■■■■■■■■■■■■■■■
    PROCEDURE CREATE_DIRECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
 		cursor curHead(lngDiv NUMBER,strNo varchar2) is
		select * from DIRECTION_HEADER where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo;
		rtHead curHead%rowtype;
		CT number;
	BEGIN
		IF LNG_FLG > 1 THEN
			OUT_PARA := 'COMPLETE';
			GOTO P_END;
		END IF;
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分がありません。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(LNG_DIV,STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		SELECT COUNT(*) INTO CT FROM DIRECTION_PROCEDURE WHERE DIRECTION_DIVISION = LNG_DIV
		AND DIRECTION_NO = STR_CODE;
		IF CT > 0 THEN
			OUT_PARA := 'COMPLETE';
			GOTO P_END;
		END IF;
		IF rtHead.DIRECTION_DIVISION = PD_PLAN_PACKAGE.pc_DirDivPack then
			--製造指図作成処理    詰め替え
			OUT_PARA := PD_PLAN_PACKAGE.FUN_CREATE_REPACK_DIRECTION
			(rtHead.DIRECTION_DIVISION,
			rtHead.DIRECTION_NO,
			rtHead.PRODUCTION_LINE,
			rtHead.ITEM_CD,
			rtHead.RECIPE_ID,
			rtHead.PLANED_QTY,
			rtHead.PLANED_SDATE,
			rtHead.PLANED_EDATE,
			rtHead.LOT_NO,
			null,
			rtHead.REMARK,
			rtHead.DIRECTION_STATUS,
			STR_LOGIN_USER);
	else
	  	--製造指図作成処理
		OUT_PARA := PD_PLAN_PACKAGE.FUN_PD_PLAN_CREATE_DIRECTION
			(rtHead.DIRECTION_DIVISION,
			rtHead.DIRECTION_NO,
			rtHead.PRODUCTION_LINE,
			rtHead.ITEM_CD,
			rtHead.RECIPE_ID,
			rtHead.PLANED_QTY,
			rtHead.PLANED_SDATE,
			rtHead.PLANED_EDATE,
			rtHead.LOT_NO,
			null,
			rtHead.REMARK,
			rtHead.DIRECTION_STATUS,
			STR_LOGIN_USER);
	end if;
				
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			dbms_output.put_line('CREATE_DIRECTION ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-CREATE_DIRECTION ' || sqlcode || sqlerrm;
		
    END;
		
    	
--■■■■■■■■■■■■■■■■■■■
--■         配合指図入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_FORMULA(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,LNG_STEP IN NUMBER,LNG_LINE IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--包装・製造指図データ検索
		cursor curHead(lngDiv NUMBER,strNo varchar2) is
		select * from DIRECTION_HEADER where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo;
		rtHead curHead%rowtype;
		-- カーソル変数のオブジェクト定義
		type cur_get_type is ref cursor  ;
		-- カーソル変数定義
		curFormula cur_get_type;
		rtFormula DIRECTION_FORMULA%ROWTYPE;
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
		InOutDate date;
		addOpno number;
		strSql varchar(10000);
		strMes varchar(1000);
		strTmp varchar(1000);
	BEGIN 
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分がありません。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(LNG_DIV,STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 分割では入出庫リソースを作らない。
		IF NVL(rtHead.DIVIDE_FLAG,0) = 1 THEN
			OUT_PARA := 'COMPLETE';
			GOTO P_END;
		END IF;
		-- 固定値
		FaultQty := 0; -- 不良数量
		IF rtHead.DIRECTION_DIVISION = 1 THEN
			addOpno := 0;
		else
			addOpno := 2;
		end if;
		strSql := 'SELECT * FROM DIRECTION_FORMULA WHERE LINE_TYPE=-1 AND DIRECTION_DIVISION = ' || LNG_DIV || ' AND DIRECTION_NO = ''' 
			|| STR_CODE || ''' ';
		IF LNG_STEP IS NULL THEN
			NULL;
		ELSE
			strSql := strSql || ' AND STEP_NO = ' || LNG_STEP ;
			IF LNG_LINE IS NULL OR LNG_LINE = 0 THEN
				NULL;
			ELSE
				strSql := strSql || ' AND LINE_NO = ' || LNG_LINE;
			END IF;
		END IF;
		OPEN curFormula FOR strSql;
		OUT_PARA := '';
		strMes := '';
		LOOP
			PARA := '';
			FETCH curFormula INTO rtFormula;
			IF curFormula%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			
			CASE
				WHEN LNG_FLG = 1 THEN
					-- 登録時
					OpNo := 104 + addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.QTY; -- 数量
					InOutDate := rtHead.PLANED_SDATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 4; -- 4:製造
				WHEN LNG_FLG = 2 THEN
					OpNo := 104 + addOpno;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.QTY,0); -- 数量
					InOutDate := rtHead.PLANED_SDATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
				WHEN LNG_FLG = 3 THEN
					-- 確定時
					OpNo := 4;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.QTY; -- 数量
					InOutDate := rtHead.PLANED_SDATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 4; -- 4:製造
					--LOCATION,LOTを検索する。
					strTmp := '';
					--CHK_INVENTORY(rtFormula.ITEM_CD,QTY,true,strTmp);
					IF strTmp is null Or strTmp = '' THEN
						NULL;
					ELSE
						IF strMes is Null or strMes = '' THEN
							strMes := strTmp;
						ELSE
							strMes := strMes || '<BR>' || strTmp;
						END IF;
					END IF;
				WHEN LNG_FLG = 4 THEN
					-- 登録取消時
					OpNo := 4;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.QTY,0); -- 数量
					InOutDate := rtHead.PLANED_SDATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
				ELSE
					OUT_PARA := '処理選択が不正です。';
					EXIT;
			END CASE;			
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtFormula.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := null;
			PAK_ALLZAITABLE.LNG_COST := rtFormula.COST;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := null;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY :=null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := null;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO := rtFormula.DIRECTION_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := rtFormula.STEP_NO*10000 + rtFormula.LINE_NO;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := rtFormula.COST;
			IF rtFormula.COST is NULL then
				PAK_ALLZAITABLE.LNG_INOUTCOST := null;
			else
				PAK_ALLZAITABLE.LNG_INOUTCOST := rtFormula.COST * Qty;
			end if;
			PAK_ALLZAITABLE.STR_REMARK := rtFormula.REMARK;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := null; --func_get_section_from_item(rtFormula.ITEM_CD,1,null);
			PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
			--31-40
			PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
			PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
			PAK_ALLZAITABLE.STR_REASON := '';
			--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
			--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
			PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := null;
			PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
			PAK_ALLZAITABLE.STR_SLIPNO := null;
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
			PAK_ALLZAITABLE.STR_ORDERNO := null;
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
			PAK_ALLZAITABLE.STR_LASTINNO := null;
			PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
			PAK_ALLZAITABLE.LNG_HANPAQTY := null;
			PAK_ALLZAITABLE.STR_ALIAS_LOT := null;
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
			PAK_ALLZAITABLE.STR_RYCD := null;

			--パッケージ実行
			PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
			IF PARA = 'COMPLETE' THEN
				NULL;
			ELSE
				PARA := PARA || '  (' || rtFormula.DIRECTION_NO || '/' || rtFormula.SEQ || '/' || rtFormula.LINE_NO || ')';
				IF OUT_PARA is null Or OUT_PARA = '' THEN
					OUT_PARA := PARA;
				ELSE
					OUT_PARA := OUT_PARA || '<BR>' || PARA;
				END IF;
			END IF;
		END LOOP;
		CLOSE curFormula;
		IF OUT_PARA is null Or OUT_PARA = '' THEN
			OUT_PARA := 'COMPLETE';
			IF LNG_FLG = 3 THEN
				IF strMes is null Or  strMes = '' THEN
					null;
				ELSE
					OUT_PARA := 'WARRNING:' || strMes ;
				END IF;
			END IF;
		END IF;
<<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			IF curFormula%ISOPEN THEN
				CLOSE curFormula;
			END IF;
			dbms_output.put_line('ENTRY_FORMULA ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_FORMULA ' || sqlcode || sqlerrm;
    END;
--■■■■■■■■■■■■■■■■■■■
--■         在庫チェック
--■■■■■■■■■■■■■■■■■■■
    PROCEDURE CHK_INVENTORY(STR_ITEM IN NVARCHAR2,QTY IN NUMBER,B_FLG IN boolean ,STR_MESSAGE OUT NVARCHAR2) IS
		cursor curInv(strItemCd varchar2) is
		select * from ITEM_INVENTORY where ITEM_CD = strItemCd ;
		rtInv curInv%rowtype;
		wkQty number;
		cursor curItem(strItemCd varchar2) is select ITEM_NAME FROM ITEM WHERE ITEM_CD = strItemCd ;
		rtItem curItem%rowtype;
		LNG_STOCK_DIVISION_LOCAL number;
	BEGIN
		STR_MESSAGE := '';
		wkQty := NVL(QTY,0);
		LNG_STOCK_DIVISION_LOCAL := FUN_GET_ITEM_STOCK_DIVISION(STR_ITEM);
		IF LNG_STOCK_DIVISION_LOCAL = 3 THEN
			wkQty := 0;
			GOTO P_END;
		END IF;
		OPEN curInv(STR_ITEM);
		FETCH curInv INTO rtInv;
		IF curInv%NOTFOUND = TRUE THEN
			NULL;
		ELSE
			wkQty := wkQty - (NVL(rtInv.INVENTORY_QTY,0) + NVL(rtInv.ASSIGN_QTY,0)+ NVL(rtInv.SALES_ASSIGN_QTY,0) 
				+ NVL(rtInv.INVALID_QTY,0) + NVL(rtInv.FAULT_QTY,0));
			IF B_FLG THEN
				wkQty := wkQty - NVL(rtInv.BACKORDER_QTY,0);
			END IF;
		END IF;
		CLOSE curInv;
		IF wkQty > 0.0 THEN
			OPEN curItem(STR_ITEM);
			FETCH curItem INTO rtItem;
			IF curItem%NOTFOUND = true THEN
				close curItem;
				GOTO P_END;
			END IF;
			CLOSE curItem;
			STR_MESSAGE := rtItem.ITEM_NAME || 'の在庫が不足しています。（' || TO_CHAR(-wkQty) || '）';
		ELSE
			wkQty := 0;
		END IF;
<<P_END>>
		null;
	EXCEPTION
		when others then
			IF curInv%ISOPEN THEN
				CLOSE curInv;
			END IF;
			IF curItem%ISOPEN THEN
				CLOSE curItem;
			END IF;
			dbms_output.put_line('CHK_INVENTORY ' || sqlcode || sqlerrm);
    END;
--■■■■■■■■■■■■■■■■■■■
--■         配合実績入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE RESULT_FORMULA(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,LNG_STEP IN NUMBER,LNG_LINE IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--包装・製造指図データ検索
		cursor curHead(lngDiv NUMBER,strNo varchar2) is
		select * from DIRECTION_HEADER where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo;
		rtHead curHead%rowtype;
		-- カーソル変数のオブジェクト定義
		type cur_get_type is ref cursor  ;
		-- カーソル変数定義
		curFormula cur_get_type;
		rtFormula DIRECTION_FORMULA%ROWTYPE;
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
		InOutDate date;
		addOpno number;
		strSql varchar(10000);
		strMes varchar(1000);
		strTmp varchar(1000);
		STEPNO number;
		LINENO number;
		ITEMCD nvarchar2(20);
		SEQNO number;
		CT number;
	BEGIN 
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分がありません。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(LNG_DIV,STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 固定値
		FaultQty := 0; -- 不良数量
		IF rtHead.DIRECTION_DIVISION = 1 THEN
			addOpno := 0;
		else
			addOpno := 2;
		end if;
		strSql := 'SELECT * FROM DIRECTION_FORMULA WHERE LINE_TYPE=-1 AND DIRECTION_DIVISION = ' || LNG_DIV || ' AND DIRECTION_NO = ''' 
			|| STR_CODE || ''' ';
		IF LNG_STEP IS NULL THEN
			NULL;
		ELSE
			strSql := strSql || ' AND STEP_NO = ' || LNG_STEP ;
			IF LNG_LINE IS NULL OR LNG_LINE = 0 THEN
				NULL;
			ELSE
				strSql := strSql || ' AND LINE_NO = ' || LNG_LINE;
			END IF;
		END IF;
		OPEN curFormula FOR strSql;
		OUT_PARA := '';
		strMes := '';
		LOOP
			PARA := '';
			FETCH curFormula INTO rtFormula;
			IF curFormula%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			STEPNO := rtFormula.STEP_NO;
			LINENO := rtFormula.LINE_NO;
			ITEMCD := rtFormula.ITEM_CD;
			SEQNO := rtFormula.SEQ;
			IF rtFormula.QTY = 0 THEN
				SELECT count(*) INTO CT FROM DIRECTION_FORMULA WHERE LINE_TYPE=-1 AND 
				DIRECTION_DIVISION =  LNG_DIV  AND DIRECTION_NO =  STR_CODE AND ITEM_CD = ITEMCD AND QTY > 0 AND SEQ < SEQNO 
				AND ROWNUM = 1 ORDER BY SEQ DESC;
				IF CT = 1 THEN
					SELECT STEP_NO,LINE_NO INTO STEPNO,LINENO FROM DIRECTION_FORMULA WHERE LINE_TYPE=-1 AND 
					DIRECTION_DIVISION =  LNG_DIV  AND DIRECTION_NO =  STR_CODE AND ITEM_CD = ITEMCD AND QTY > 0 AND SEQ < SEQNO 
					AND ROWNUM = 1 ORDER BY SEQ DESC;
				END IF;
			END IF;

			CASE
				WHEN LNG_FLG = 1 THEN
					-- 登録時
					OpNo := 6;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := NVL(rtHead.RESULT_SDATE,rtFormula.UPDATE_DATE) ; -- 製造開始
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 4; -- 4:製造
				WHEN LNG_FLG = 2 THEN
					OpNo := 6;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := NVL(rtHead.RESULT_SDATE,rtFormula.UPDATE_DATE) ; -- 製造開始
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
				WHEN LNG_FLG = 5 THEN
					-- 確定時
					OpNo := 12;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  NVL(rtFormula.QTY,0) - NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := rtHead.PLANED_SDATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 4; -- 4:製造
				ELSE
					CLOSE curFormula;
					OUT_PARA := '処理選択が不正です。';
					GOTO P_END;
			END CASE;			
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtFormula.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := rtFormula.LOCATION_CD;
			PAK_ALLZAITABLE.LNG_COST := rtFormula.COST;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := null;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := rtFormula.LOT_NO;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO := rtFormula.DIRECTION_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := STEPNO*10000 + LINENO;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := rtFormula.COST;
			IF rtFormula.COST is NULL then
				PAK_ALLZAITABLE.LNG_INOUTCOST := null;
			else
				PAK_ALLZAITABLE.LNG_INOUTCOST := NVL(rtFormula.COST,0) * Qty;
			end if;
			PAK_ALLZAITABLE.STR_REMARK := rtFormula.REMARK;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_from_item(rtFormula.ITEM_CD,1,rtFormula.LOT_NO);
			PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
			--31-40
			PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
			PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
			PAK_ALLZAITABLE.STR_REASON := '';
			--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
			--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
			PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := null;
			PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
			PAK_ALLZAITABLE.STR_SLIPNO := null;
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
			PAK_ALLZAITABLE.STR_ORDERNO := null;
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
			PAK_ALLZAITABLE.STR_LASTINNO := null;
			PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
			PAK_ALLZAITABLE.LNG_HANPAQTY := null;
			PAK_ALLZAITABLE.STR_ALIAS_LOT := rtFormula.MANUFACTURER_LOT_NO;
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
			PAK_ALLZAITABLE.STR_RYCD := null;
			IF  LNG_FLG = 5 THEN
				PAK_ALLZAITABLE.STR_LOTCODE := null;
				PAK_ALLZAITABLE.STR_LOCATIONCODE := null;
			END IF;
			--パッケージ実行
			IF Qty = 0 AND LNG_FLG = 2 THEN
				NULL;
			ELSE 
				PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
				IF PARA = 'COMPLETE' THEN
					NULL;
				ELSE
					PARA := PARA || '  (' || rtFormula.DIRECTION_NO || '/' || rtFormula.SEQ || '/' || rtFormula.LINE_NO || ')';
					IF OUT_PARA is null Or OUT_PARA = '' THEN
						OUT_PARA := PARA;
					ELSE
						OUT_PARA := OUT_PARA || '<BR>' || PARA;
					END IF;
				END IF;
			END IF;
		END LOOP;
		CLOSE curFormula;
		IF OUT_PARA is null Or OUT_PARA = '' THEN
			OUT_PARA := 'COMPLETE';
		END IF;
<<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			IF curFormula%ISOPEN THEN
				CLOSE curFormula;
			END IF;
			dbms_output.put_line('RESULT_FORMULA ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-RESULT_FORMULA ' || sqlcode || sqlerrm;
    END;
--■■■■■■■■■■■■■■■■■■■
--■         外注投入実績入力
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE RESULT_INJECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,LNG_STEP IN NUMBER,LNG_LINE IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		-- カーソル変数のオブジェクト定義
		type cur_get_type is ref cursor  ;
		-- カーソル変数定義
		curFormula cur_get_type;
		rtFormula PURCHASE_MATE_INJECTION%ROWTYPE;
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
		InOutDate date;
		addOpno number;
		strSql varchar(10000);
		strMes varchar(1000);
		strTmp varchar(1000);
		ct number;
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		-- 固定値
		FaultQty := 0; -- 不良数量
		strSql := 'SELECT * FROM PURCHASE_MATE_INJECTION WHERE SEQ > 0 AND  BUY_SUBCONTRACT_ORDER_NO =  ''' || STR_CODE || ''' ';
		IF LNG_DIV is NULL THEN
			NULL;
		ELSE
			strSql := strSql || ' AND RECIPE_ID = ' || LNG_DIV ;

			IF LNG_STEP IS NULL THEN
				NULL;
			ELSE
				strSql := strSql || ' AND STEP_NO = ' || LNG_STEP ;
				IF LNG_LINE IS NULL OR LNG_LINE = 0 THEN
					NULL;
				ELSE
					strSql := strSql || ' AND LINE_NO = ' || LNG_LINE;
				END IF;
			END IF;
		END IF;
		select count(*) into ct from PURCHASE_SUBCONTRACT where BUY_SUBCONTRACT_ORDER_NO =  STR_CODE ;
		IF CT = 0 THEN
			OUT_PARA := '受入日が未入力です。';
			GOTO P_END;
		else
			select max(ACCEPT_DATE) into InOutDate  from PURCHASE_SUBCONTRACT where  BUY_SUBCONTRACT_ORDER_NO =  STR_CODE ;
			if InOutDate is null then 
				OUT_PARA := '受入日が未入力です。';
				GOTO P_END;
			end if;
		end if;
		OPEN curFormula FOR strSql;
		OUT_PARA := '';
		strMes := '';
		LOOP
			PARA := '';
			FETCH curFormula INTO rtFormula;
			IF curFormula%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			CASE
				WHEN LNG_FLG = 1 THEN
					-- 登録時
					OpNo := 6;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.STOCKPD_QTY; -- 数量
					-- InOutDate := rtFormula.INPUT_DATE; -- 製造開始予定
					InOutDivision := 5; -- 5:支給
					InOutSourceDellFlg := 0; 
					FuncDivision := 5; -- 5:支給
				WHEN LNG_FLG = 2 THEN
					OpNo := 6;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					-- InOutDate := rtFormula.INPUT_DATE; -- 製造開始予定
					InOutDivision := 5; -- 5:支給
					InOutSourceDellFlg := 0; 
					FuncDivision := 85; -- 85:支給取消
				ELSE
					CLOSE curFormula;
					OUT_PARA := '処理選択が不正です。';
					GOTO P_END;
			END CASE;			
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtFormula.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := rtFormula.LOCATION_CD;
			PAK_ALLZAITABLE.LNG_COST := null;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := null;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := rtFormula.LOT_NO;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO :=  rtFormula.BUY_SUBCONTRACT_ORDER_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := rtFormula.STEP_NO*10000 + rtFormula.LINE_NO;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := null;
			PAK_ALLZAITABLE.LNG_INOUTCOST := null;
			PAK_ALLZAITABLE.STR_REMARK := null;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_from_item(rtFormula.ITEM_CD,1,rtFormula.LOT_NO);
			PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
			--31-40
			PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
			PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
			PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
			PAK_ALLZAITABLE.STR_REASON := '';
			--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
			--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
			PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := null;
			PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
			PAK_ALLZAITABLE.STR_SLIPNO := null;
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
			PAK_ALLZAITABLE.STR_ORDERNO := null;
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
			PAK_ALLZAITABLE.STR_LASTINNO := null;
			PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
			PAK_ALLZAITABLE.LNG_HANPAQTY := null;
			PAK_ALLZAITABLE.STR_ALIAS_LOT := rtFormula.MANUFACTURER_LOT_NO;
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := null;
			PAK_ALLZAITABLE.STR_RYCD := null;
			--パッケージ実行
			IF (InOutDate is NULL OR QTY is NULL OR QTY = 0) AND LNG_FLG = 2 THEN
				NULL;
			ELSE 
				PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
				IF PARA = 'COMPLETE' THEN
					NULL;
				ELSE
					PARA := PARA || '  (' || rtFormula.BUY_SUBCONTRACT_ORDER_NO || '/' || rtFormula.STEP_NO || '/' || rtFormula.LINE_NO || ')';
					IF OUT_PARA is null Or OUT_PARA = '' THEN
						OUT_PARA := PARA;
					ELSE
						OUT_PARA := OUT_PARA || '<BR>' || PARA;
					END IF;
				END IF;
			END IF;
		END LOOP;
		CLOSE curFormula;
		IF OUT_PARA is null Or OUT_PARA = '' THEN
			OUT_PARA := 'COMPLETE';
		END IF;
<<P_END>>
			null;
	EXCEPTION
		when others then
			IF curFormula%ISOPEN THEN
				CLOSE curFormula;
			END IF;
			dbms_output.put_line('RESULT_INJECTION ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-RESULT_INJECTION ' || sqlcode || sqlerrm;
	null;
    END;
--■■■■■■■■■■■■■■■■■■■
--■        包装・製造指図仕上入力 
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE RESULT_DIRECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--包装・製造指図データ検索
		cursor curHead(lngDiv NUMBER,strNo varchar2) is
		select * from DIRECTION_HEADER where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo;

		cursor curFormula(lngDiv NUMBER,strNo varchar2) is 
		select * from DIRECTION_FORMULA where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo AND LINE_TYPE > 0 ;
		rtHead curHead%rowtype;
		rtFormula curFormula%ROWTYPE;
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
		InOutDate date;
		Location VARCHAR2(20);
		LotNo VARCHAR2(20);
		addOpno number;
		IssuDate date;
		hinKbn number;
		-- カーソル変数定義
	BEGIN 
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分がありません。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(LNG_DIV,STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 固定値
		FaultQty := 0; -- 不良数量
		IF rtHead.DIRECTION_DIVISION = 1 THEN
			addOpno := 0;
			hinKbn := 3; -- 中間品
			IF LNG_FLG < 3 OR LNG_FLG = 5 THEN
				NULL;
			ELSE
				OUT_PARA := '処理選択が不正です。';
				GOTO P_END;
			END IF;
			Location := 'B7';
		else
			addOpno := 1;
			IF rtHead.DIRECTION_DIVISION = 2 THEN
				hinKbn := 0; -- 製品
			ELSE
				hinKbn := null; -- その他
			END IF;
		end if;
		OPEN  curFORMULA(LNG_DIV,STR_CODE);
		OUT_PARA := '該当データがありません。!!!';
		LOOP 
			FETCH curFORMULA INTO rtFORMULA;
			IF curFORMULA%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			--OUT_PARA := '';
			CASE
				WHEN LNG_FLG = 1 THEN
					-- 仕上時
					IF rtFormula.LINE_NO > 10000 THEN GOTO NEXT_STEP; END IF;
					OpNo := 41 + addOpno;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.STOCKPD_QTY; -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 0; 
					FuncDivision := 4; -- 4:製造
					IF rtHead.DIRECTION_DIVISION = 1 THEN
						Location := rtFormula.LOCATION_CD;
						LotNo := rtHead.DIRECTION_NO;
						IssuDate := rtHead.RESULT_EDATE;
					ELSE
						Location := null;
						LotNo := null;
						IssuDate := null;
					END IF;
				WHEN LNG_FLG = 2 THEN
					-- 仕上取消時
					IF rtFormula.LINE_NO > 10000 THEN GOTO NEXT_STEP; END IF;
					OpNo := 41 + addOpno;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
					IF rtHead.DIRECTION_DIVISION = 1 THEN
						Location := rtFormula.LOCATION_CD;
						LotNo := rtHead.DIRECTION_NO;
						IssuDate := rtHead.RESULT_EDATE;
					ELSE
						Location := null;
						LotNo := null;
						IssuDate := null;
					END IF;
				WHEN LNG_FLG = 3 THEN
					-- 検査待時
					IF rtFormula.LINE_NO < 10000 THEN GOTO NEXT_STEP; END IF;
					OpNo := 5;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.STOCKPD_QTY; -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 4; -- 4:製造
					Location := rtFormula.LOCATION_CD;
					LotNo := rtHead.DIRECTION_NO;
					IssuDate := null;
				WHEN LNG_FLG = 4 THEN
					-- 検査待取消時
					IF rtFormula.LINE_NO < 10000 THEN GOTO NEXT_STEP; END IF;
					OpNo := 5;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
					Location := rtFormula.LOCATION_CD;
					LotNo := rtHead.DIRECTION_NO;
					IssuDate := null;
				WHEN LNG_FLG = 5 THEN
					-- 完了時
					OpNo := 11;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.QTY; -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 4; -- 4:製造
					Location := null;
					LotNo := null;
					IssuDate := null;
				WHEN LNG_FLG = 6 THEN
					-- 製品検査時
					IF rtFormula.LINE_NO < 10000 THEN GOTO NEXT_STEP; END IF;
					OpNo := 51;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtFormula.STOCKPD_QTY; -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 4; -- 4:製造
					Location := rtFormula.LOCATION_CD;
					LotNo := rtHead.DIRECTION_NO;
					IssuDate := rtHead.RESULT_EDATE;
				WHEN LNG_FLG = 7 THEN
					-- 製品検査時
					IF rtFormula.LINE_NO < 10000 THEN GOTO NEXT_STEP; END IF;
					OpNo := 51;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
					Location := rtFormula.LOCATION_CD;
					LotNo := rtHead.DIRECTION_NO;
					IssuDate := rtHead.RESULT_EDATE;
				ELSE
					OUT_PARA := '処理選択が不正です。';
					EXIT;
			END CASE;			
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
			PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
			PAK_ALLZAITABLE.STR_ITEMCODE := rtFormula.ITEM_CD;
			PAK_ALLZAITABLE.LNG_QTY := Qty;
			PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
			PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := Location;
			PAK_ALLZAITABLE.LNG_COST := null;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := null;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := LotNo;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := IssuDate;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
			PAK_ALLZAITABLE.STR_ODERNO := rtHead.DIRECTION_NO;
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := rtHead.DIRECTION_DIVISION;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := null;
			PAK_ALLZAITABLE.LNG_INOUTCOST := null;
			PAK_ALLZAITABLE.STR_REMARK := rtHead.REMARK;
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
			PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_from_item(rtFormula.ITEM_CD,hinKbn,LotNo);
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
			PAK_ALLZAITABLE.STR_SLIPNO := null;
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
			PAK_ALLZAITABLE.STR_ORDERNO := null;
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
			PAK_ALLZAITABLE.STR_LASTINNO := null;
			PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
			PAK_ALLZAITABLE.LNG_HANPAQTY := null;
			IF rtHead.DIRECTION_DIVISION = 1 THEN
				PAK_ALLZAITABLE.STR_ALIAS_LOT := null;
			ELSE
				PAK_ALLZAITABLE.STR_ALIAS_LOT := rtFormula.LOT_NO;
			END IF;
			PAK_ALLZAITABLE.STR_RYCD := null;
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := InOutDate; --rtHead.CERTIFICATION_DATE;
			--パッケージ実行
			IF InOutDate is NULL AND (LNG_FLG = 2 OR LNG_FLG = 4 OR LNG_FLG = 7 ) THEN
				OUT_PARA := 'COMPLETE';
			ELSE 
				PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
				--戻り値セット
				OUT_PARA := PARA;
				IF PARA = 'COMPLETE' THEN
					NULL;
				ELSE
					OUT_PARA := PARA || ' 指図番号:' || rtHead.DIRECTION_NO ;
					EXIT; 
				END IF;
			END IF;
<<NEXT_STEP>>
			null;
		END LOOP;
		CLOSE curFormula;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			IF curFormula%ISOPEN THEN
				CLOSE curFormula;
			END IF;
			dbms_output.put_line('RESULT_DIRECTION ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-RESULT_DIRECTION ' || sqlcode || ' : ' ||  sqlerrm;
		
    END;
--■■■■■■■■■■■■■■■■■■■
--■        包装・合格入力 
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE RESULT_GRADE(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--包装・製造指図データ検索
		cursor curHead(lngDiv NUMBER,strNo varchar2) is
		select * from DIRECTION_HEADER where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo;

		cursor curFormula(lngDiv NUMBER,strNo varchar2) is 
		select * from DIRECTION_FORMULA where DIRECTION_DIVISION = lngDiv
		AND DIRECTION_NO = strNo AND LINE_TYPE > 0 ;

		cursor curLotInv(strItem varchar2,strlot varchar2) is
		select * from LOT_INVENTORY where ITEM_CD = strItem AND LOT_NO = strlot ;
		rtHead curHead%rowtype;
		rtFormula curFormula%ROWTYPE;
		rtLotInv curLotInv%ROWTYPE;
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
		InOutDate date;
		Location VARCHAR2(20);
		LotNo VARCHAR2(20);
		addOpno number;
		IssuDate date;
		hinKbn number;
		ct number;
		-- カーソル変数定義
	BEGIN 
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分がありません。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(LNG_DIV,STR_CODE);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 固定値
		FaultQty := 0; -- 不良数量
		IF rtHead.DIRECTION_DIVISION = 1 THEN
				OUT_PARA := '処理選択が不正です。';
				GOTO P_END;
		else
			addOpno := 1;
			IF rtHead.DIRECTION_DIVISION = 2 THEN
				hinKbn :=  0; -- 製品
			ELSE
				hinKbn := null;
			END IF;
		end if;
		OPEN  curFORMULA(LNG_DIV,STR_CODE);
		OUT_PARA := '該当データがありません。';
		LOOP 
			FETCH curFORMULA INTO rtFORMULA;
			IF curFORMULA%NOTFOUND = TRUE THEN
				EXIT;
			END IF;
			IF rtFormula.LINE_NO < 10000 THEN GOTO NEXT_STEP; END IF;
				
			OPEN curLotInv(rtFormula.ITEM_CD,rtHead.DIRECTION_NO);
			LOOP
				FETCH curLotInv INTO rtLotInv;
				IF curLotInv%NOTFOUND = TRUE THEN
					EXIT;
				END IF;
				OUT_PARA := '';
				CASE
				WHEN LNG_FLG = 6 THEN
					-- 製品検査時
					OpNo := 51;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  rtLotInv.INSPECTION_QTY; -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 4; -- 4:製造
					Location := rtLotInv.LOCATION_CD;
					LotNo := rtLotInv.LOT_NO;
					IssuDate := rtLotInv.ISSUE_DATE;
				WHEN LNG_FLG = 7 THEN
					-- 製品検査時
					OpNo := 51;
					InOutKbn := 0; -- 0:入庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtLotInv.INVENTORY_QTY,0); -- 数量
					InOutDate := NVL(rtHead.RESULT_EDATE,rtFormula.UPDATE_DATE); -- 入庫予定
					InOutDivision := 1; -- 1:製造受入
					InOutSourceDellFlg := 1; 
					FuncDivision := 84; -- 84:製造取消
					Location := rtLotInv.LOCATION_CD;
					LotNo := rtLotInv.LOT_NO;
					IssuDate := rtLotInv.ISSUE_DATE;
				ELSE
					OUT_PARA := '処理選択が不正です。';
					EXIT;
				END CASE;			
				--更新引数セット1-10
				PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
				PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
				PAK_ALLZAITABLE.STR_ITEMCODE := rtFormula.ITEM_CD;
				PAK_ALLZAITABLE.LNG_QTY := Qty;
				PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
				PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
				PAK_ALLZAITABLE.STR_LOCATIONCODE := Location;
				PAK_ALLZAITABLE.LNG_COST := null;
				PAK_ALLZAITABLE.DTE_UKEDATE := null;
				PAK_ALLZAITABLE.LNG_COSTFLG := null;
				--11-20
				PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
				PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
				PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
				PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
				PAK_ALLZAITABLE.STR_LOTCODE := LotNo;
				PAK_ALLZAITABLE.DTE_START_DATE := null;
				PAK_ALLZAITABLE.DTE_ISSUE_DATE := IssuDate;
				PAK_ALLZAITABLE.DTE_END_DATE := null;
				PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
				PAK_ALLZAITABLE.STR_ODERNO := rtHead.DIRECTION_NO;
				--21-30
				PAK_ALLZAITABLE.LNG_ODERLINENO := rtHead.DIRECTION_DIVISION;
				PAK_ALLZAITABLE.LNG_INOUTPRICE := null;
				PAK_ALLZAITABLE.LNG_INOUTCOST := null;
				PAK_ALLZAITABLE.STR_REMARK := rtHead.REMARK;
				PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
				PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
				PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_from_item(rtFormula.ITEM_CD,hinKbn,LotNo);
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
				PAK_ALLZAITABLE.STR_SLIPNO := null;
				--41-46
				PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
				PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
				PAK_ALLZAITABLE.STR_ORDERNO := null;
				PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
				PAK_ALLZAITABLE.STR_LASTINNO := null;
				PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
				PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
				PAK_ALLZAITABLE.LNG_HANPAQTY := null;
				PAK_ALLZAITABLE.STR_ALIAS_LOT := rtFormula.LOT_NO;
				PAK_ALLZAITABLE.DTE_LOTMAKEDATE := InOutDate; --rtHead.CERTIFICATION_DATE;
				PAK_ALLZAITABLE.STR_RYCD := null;
				--パッケージ実行
				IF InOutDate is NULL AND  LNG_FLG = 7  THEN
					NULL;
				ELSE 
					PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
					--戻り値セット
					OUT_PARA := PARA;
					IF PARA = 'COMPLETE' THEN
						NULL;
					ELSE
						OUT_PARA := PARA || ' 指図番号:' || rtHead.DIRECTION_NO ;
						EXIT;
					END IF;
				END IF;
			END LOOP;
			CLOSE curLotInv;
			IF PARA = 'COMPLETE' THEN
				NULL;
			ELSE
				EXIT;
			END IF;
<<NEXT_STEP>>
			null;
		END LOOP;
		CLOSE curFormula;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			IF curFormula%ISOPEN THEN
				CLOSE curFormula;
			END IF;
			dbms_output.put_line('RESULT_GRADE ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-RESULT_GRADE ' || sqlcode || sqlerrm;
		
    END;
--■■■■■■■■■■■■■■■■■■■
--■  返品入庫 
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE RETURN_DELIVERY(LNG_FLG IN NUMBER,SLIP_NO IN NVARCHAR2,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		--検索
		cursor curHead(strNo varchar2) is
		select * from SALES where SALES_NO = strNo;
		rtHead curHead%rowtype;
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
		InOutDate date;
		Location VARCHAR2(20);
		addOpno number;
	BEGIN 
		IF SLIP_NO is NULL or SLIP_NO = '' THEN 
			OUT_PARA := '売上番号がありません。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーがありません。';
			GOTO P_END;
		END IF;
		OPEN curHead(SLIP_NO);
		FETCH curHead INTO rtHead;
		IF curHead%NOTFOUND = TRUE THEN
			CLOSE curHead;
			OUT_PARA := '該当データがありません。';
			GOTO P_END;
		END IF;
		CLOSE curHead;
		-- 固定値
		FaultQty := 0; -- 不良数量
		CASE
			WHEN LNG_FLG = 1 THEN
				-- 返品時
				OpNo := 16;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty :=  rtHead.SALES_QUANTITY; -- 数量
				InOutDate := rtHead.SALES_DATE; -- 入庫予定
				InOutDivision := 4; 
				InOutSourceDellFlg := 0; 
				FuncDivision := 52; 
			WHEN LNG_FLG = 2 THEN
				-- 返品取消時
				OpNo := 16;
				InOutKbn := 0; -- 0:入庫
				ChkFlg := 1;  -- 1:在庫管理区分チェックをする
				CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
				Qty :=  -NVL(rtHead.SALES_QUANTITY,0); -- 数量
				InOutDate := rtHead.SALES_DATE; -- 入庫予定
				InOutDivision := 4; 
				InOutSourceDellFlg := 0; 
				FuncDivision := 92; 
			ELSE
				OUT_PARA := '処理選択が不正です。';
				GOTO P_END;
		END CASE;			
		--更新引数セット1-10
		PAK_ALLZAITABLE.LNG_OPNO :=OpNo;
		PAK_ALLZAITABLE.LNG_INOUTKBN := InOutKbn;
		PAK_ALLZAITABLE.STR_ITEMCODE := rtHead.ITEM_CD;
		PAK_ALLZAITABLE.LNG_QTY := Qty;
		PAK_ALLZAITABLE.LNG_FAULTQTY := FaultQty;
		PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := rtHead.HOUSING_LOCATION_CD;
		PAK_ALLZAITABLE.LNG_COST := rtHead.SALES_UNITPRICE;
		PAK_ALLZAITABLE.DTE_UKEDATE := InOutDate;
		PAK_ALLZAITABLE.LNG_COSTFLG := rtHead.TMP_UNITPRICE_FLG;
		--11-20
		PAK_ALLZAITABLE.LNG_CHKFLG := ChkFlg;
		PAK_ALLZAITABLE.LNG_CALCCOSTFLG := CalcCostFlg;
		PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
		PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
		PAK_ALLZAITABLE.STR_LOTCODE := rtHead.PACKAGE_DIRECTION_NO;
		PAK_ALLZAITABLE.DTE_START_DATE := null;
		PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
		PAK_ALLZAITABLE.DTE_END_DATE := null;
		PAK_ALLZAITABLE.LNG_INOUTDIVISION := InOutDivision;
		PAK_ALLZAITABLE.STR_ODERNO := rtHead.SALES_NO;
		--21-30
		PAK_ALLZAITABLE.LNG_ODERLINENO := 1;
		PAK_ALLZAITABLE.LNG_INOUTPRICE := rtHead.SALES_UNITPRICE;
		PAK_ALLZAITABLE.LNG_INOUTCOST := rtHead.SALES_AMOUNT;
		PAK_ALLZAITABLE.STR_REMARK := rtHead.REMARK;
		PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := InOutSourceDellFlg;
		PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
		PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_for_shipping(rtHead.VENDER_CD,'TS');
		PAK_ALLZAITABLE.STR_ACCOUNTCD := rtHead.DEBIT_TITLE_CD;
		PAK_ALLZAITABLE.STR_SUBACCOUNTCD := rtHead.DEBIT_SUB_TITLE_CD;
		PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
		--31-40
		PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
		PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
		PAK_ALLZAITABLE.STR_REASON := rtHead.RY_CD;
		--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := P_DTE_INOUTUPDATEDATE;
		--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := P_DTE_INVENTORYUPDATEDATE;
		PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := 0;
		PAK_ALLZAITABLE.LNG_FUNCDIVISION := FuncDivision;
		PAK_ALLZAITABLE.STR_SLIPNO := rtHead.SALES_SLIP_NO;
		--41-46
		PAK_ALLZAITABLE.LNG_SLIPLINENO := rtHead.SALES_SLIP_ROW_NO;
		PAK_ALLZAITABLE.LNG_ORDERDIVISION := rtHead.CATEGORY_DIVISION;
		PAK_ALLZAITABLE.STR_ORDERNO := rtHead.ORDER_NO || rtHead.ORDER_ROW_NO;
		PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
		PAK_ALLZAITABLE.STR_LASTINNO := null;
		PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
		PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
		PAK_ALLZAITABLE.LNG_HANPAQTY := null;
		PAK_ALLZAITABLE.STR_ALIAS_LOT := rtHead.PRODUCT_LOTNO;
		OUT_PARA := '';
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := InOutDate;
		PAK_ALLZAITABLE.STR_RYCD := rtHead.RY_CD;

		--パッケージ実行
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		--戻り値セット
		OUT_PARA := PARA;
 		IF PARA = 'COMPLETE' THEN
				NULL;
		ELSE
				OUT_PARA := PARA || '　売上番号：' || rtHead.SALES_NO;
		END IF;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			dbms_output.put_line('RETURN_DELIVERY ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-RETURN_DELIVERY ' || sqlcode || sqlerrm;
    END;
--■■■■■■■■■■■■■■■■■■■
--■  在庫入出庫入力 棚卸　 
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_INOUT(LNG_FLG IN NUMBER,ITEM_CODE IN NVARCHAR2,QTY IN NUMBER,INOUTDATE IN DATE,LOCATION IN NVARCHAR2,LOT IN NVARCHAR2,REMARK IN NVARCHAR2,REASON IN NVARCHAR2,REASONCD IN NVARCHAR2,LNG_FUNC IN NUMBER,LNG_INOUT IN NUMBER,STR_ALOT IN NVARCHAR2,LNG_COST IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		PARA VARCHAR2(255) := '';
		INOUTDIV NUMBER := 0;
	BEGIN 
		--更新引数セット1-10
		PAK_ALLZAITABLE.LNG_OPNO :=LNG_FLG;
		PAK_ALLZAITABLE.LNG_INOUTKBN := LNG_INOUT;
		PAK_ALLZAITABLE.STR_ITEMCODE := ITEM_CODE;
		PAK_ALLZAITABLE.LNG_QTY := NVL(QTY,0);
		PAK_ALLZAITABLE.LNG_FAULTQTY := 0;
		PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := Location;
		PAK_ALLZAITABLE.LNG_COST := LNG_COST;
		PAK_ALLZAITABLE.DTE_UKEDATE := null;
		PAK_ALLZAITABLE.LNG_COSTFLG := null;
		--11-20
		PAK_ALLZAITABLE.LNG_CHKFLG := 1;
		PAK_ALLZAITABLE.LNG_CALCCOSTFLG := 1;
		PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
		PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
		PAK_ALLZAITABLE.STR_LOTCODE := LOT;
		PAK_ALLZAITABLE.DTE_START_DATE := null;
		PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
		PAK_ALLZAITABLE.DTE_END_DATE := null;
		PAK_ALLZAITABLE.LNG_INOUTDIVISION := LNG_INOUT;
		PAK_ALLZAITABLE.STR_ODERNO := null;
		--21-30
		PAK_ALLZAITABLE.LNG_ODERLINENO := null;
		PAK_ALLZAITABLE.LNG_INOUTPRICE := LNG_COST;
		PAK_ALLZAITABLE.LNG_INOUTCOST := NVL(LNG_COST,0)*NVL(QTY,0);
		PAK_ALLZAITABLE.STR_REMARK := REMARK;
		PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := 0;
		PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
		PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_from_item(ITEM_CODE,null,LOT);
		PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
		--31-40
		PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
		PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
		PAK_ALLZAITABLE.STR_REASON := REASON;
		--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
		--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
		PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := 0;
		PAK_ALLZAITABLE.LNG_FUNCDIVISION :=LNG_FUNC;
		PAK_ALLZAITABLE.STR_SLIPNO := null;
		--41-46
		PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
		PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
		PAK_ALLZAITABLE.STR_ORDERNO := null;
		PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
		PAK_ALLZAITABLE.STR_LASTINNO := null;
		PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
		PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
		PAK_ALLZAITABLE.LNG_HANPAQTY :=null;
		PAK_ALLZAITABLE.STR_ALIAS_LOT := STR_ALOT;
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := InOutDate;
		PAK_ALLZAITABLE.STR_RYCD := REASONCD;
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		OUT_PARA := PARA;
 		IF PARA = 'COMPLETE' THEN
				NULL;
		ELSE
				OUT_PARA := PARA ;
		END IF;
	EXCEPTION
		when others then
			dbms_output.put_line('ENTRY_INOUT ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_INOUT ' || sqlcode || sqlerrm;
		
    	END;
--■■■■■■■■■■■■■■■■■■■
--■  預品売上計上 
--■■■■■■■■■■■■■■■■■■■
	PROCEDURE ENTRY_SALES(LNG_FLG IN NUMBER,ITEM_CODE IN NVARCHAR2,QTY IN NUMBER,INOUTDATE IN DATE,LOCATION IN NVARCHAR2,LOT IN NVARCHAR2,REMARK IN NVARCHAR2,REASON IN NVARCHAR2,REASONCD IN NVARCHAR2,LNG_FUNC IN NUMBER,LNG_INOUT IN NUMBER,STR_ALOT IN NVARCHAR2,LNG_COST IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
		PARA VARCHAR2(255) := '';
		INOUTDIV NUMBER := 0;
	BEGIN 
		--更新引数セット1-10
		PAK_ALLZAITABLE.LNG_OPNO :=17;
		PAK_ALLZAITABLE.LNG_INOUTKBN := LNG_INOUT;
		PAK_ALLZAITABLE.STR_ITEMCODE := ITEM_CODE;
		PAK_ALLZAITABLE.LNG_QTY := NVL(QTY,0);
		PAK_ALLZAITABLE.LNG_FAULTQTY := 0;
		PAK_ALLZAITABLE.DTE_INOUTDATE := InOutDate;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := Location;
		PAK_ALLZAITABLE.LNG_COST := LNG_COST;
		PAK_ALLZAITABLE.DTE_UKEDATE := null;
		PAK_ALLZAITABLE.LNG_COSTFLG := null;
		--11-20
		PAK_ALLZAITABLE.LNG_CHKFLG := 1;
		PAK_ALLZAITABLE.LNG_CALCCOSTFLG := 1;
		PAK_ALLZAITABLE.LNG_INVENTORYQTY := null;
		PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
		PAK_ALLZAITABLE.STR_LOTCODE := LOT;
		PAK_ALLZAITABLE.DTE_START_DATE := null;
		PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
		PAK_ALLZAITABLE.DTE_END_DATE := null;
		PAK_ALLZAITABLE.LNG_INOUTDIVISION := LNG_INOUT;
		PAK_ALLZAITABLE.STR_ODERNO := STR_ALOT;
		--21-30
		PAK_ALLZAITABLE.LNG_ODERLINENO := 1;
		PAK_ALLZAITABLE.LNG_INOUTPRICE := LNG_COST;
		PAK_ALLZAITABLE.LNG_INOUTCOST := NVL(LNG_COST,0)*NVL(QTY,0);
		PAK_ALLZAITABLE.STR_REMARK := NULL;
		PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := 0;
		PAK_ALLZAITABLE.STR_INOUTSOURCENO := null;
		PAK_ALLZAITABLE.STR_SECTIONCD := func_get_section_from_sales(STR_ALOT);
		PAK_ALLZAITABLE.STR_ACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_SUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_ITEMCATEGORY := null;
		--31-40
		PAK_ALLZAITABLE.STR_PARENTITEMCD := null;
		PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := null;
		PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := null;
		PAK_ALLZAITABLE.STR_REASON := REASON;
		--	PAK_ALLZAITABLE.DTE_INOUTUPDATEDATE := null;
		--	PAK_ALLZAITABLE.DTE_INVENTORYUPDATEDATE := null;
		PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := 0;
		PAK_ALLZAITABLE.LNG_FUNCDIVISION :=LNG_FUNC;
		PAK_ALLZAITABLE.STR_SLIPNO := null;
		--41-46
		PAK_ALLZAITABLE.LNG_SLIPLINENO := null;
		PAK_ALLZAITABLE.LNG_ORDERDIVISION := null;
		PAK_ALLZAITABLE.STR_ORDERNO := null;
		PAK_ALLZAITABLE.LNG_LASTINDIVISION := null;
		PAK_ALLZAITABLE.STR_LASTINNO := null;
		PAK_ALLZAITABLE.STR_INPUTNO := STR_LOGIN_USER;
		PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := null;
		PAK_ALLZAITABLE.LNG_HANPAQTY :=null;
		PAK_ALLZAITABLE.STR_ALIAS_LOT := NULL;
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := SYSDATE;
		PAK_ALLZAITABLE.LNG_AZUDIV := 0;
		PAK_ALLZAITABLE.STR_RYCD := REASONCD;
		
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		IF PARA = 'COMPLETE' THEN
			NULL;
		ELSE
			OUT_PARA := '移動元' || PARA;
			GOTO P_END;
		END IF;
		PAK_ALLZAITABLE.STR_LOCATIONCODE := REMARK;
		IF LNG_INOUT = 0 THEN
			PAK_ALLZAITABLE.LNG_INOUTKBN := 1;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := 1;
		ELSE
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := 0;
			PAK_ALLZAITABLE.LNG_INOUTKBN := 0;
		END IF;			
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		IF PARA = 'COMPLETE' THEN
			OUT_PARA := PARA;
		ELSE
			OUT_PARA := '移動先' || PARA;
			GOTO P_END;
		END IF;
<<P_END>>
		NULL;
	EXCEPTION
		when others then
			dbms_output.put_line('ENTRY_SALES ' || sqlcode || sqlerrm);
		OUT_PARA := 'ORA-ENTRY_SALES ' || sqlcode || sqlerrm;
		
    	END;

END;
/
