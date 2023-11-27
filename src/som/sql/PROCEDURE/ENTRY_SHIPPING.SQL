CREATE OR REPLACE PROCEDURE ENTRY_SHIPPING(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_ROW_NO IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
			OUT_PARA := '出荷番号なし。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーなし。';
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
			PAK_ALLZAITABLE.STR_SECTIONCD := null;
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
		OUT_PARA := 'ENTRY_SHIPPING ' || sqlcode || sqlerrm;
    END;
/
