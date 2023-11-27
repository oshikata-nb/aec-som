CREATE OR REPLACE PROCEDURE      ENTRY_ORDER(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_ROW_NO IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
			OUT_PARA := '受注番号なし。';
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
			PAK_ALLZAITABLE.STR_SECTIONCD := rtHead.ORGANIZATION_CD;
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
		OUT_PARA := 'ENTRY_ORDER ' || sqlcode || sqlerrm;
    END;
/
