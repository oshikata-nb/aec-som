CREATE OR REPLACE PROCEDURE RESULT_INJECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,LNG_STEP IN NUMBER,LNG_LINE IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
	BEGIN 
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号なし。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーなし。';
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
					InOutDate := rtFormula.INPUT_DATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 4; -- 4:製造
				WHEN LNG_FLG = 2 THEN
					OpNo := 6;
					InOutKbn := 1; -- 1:出庫
					ChkFlg := 1;  -- 1:在庫管理区分チェックをする
					CalcCostFlg := 1; --  1:在庫評価単価を再計算しない
					Qty :=  -NVL(rtFormula.STOCKPD_QTY,0); -- 数量
					InOutDate := rtFormula.INPUT_DATE; -- 製造開始予定
					InOutDivision := 2; -- 2:製造払出
					InOutSourceDellFlg := 0; 
					FuncDivision := 84; -- 84:製造取消
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
			IF (InOutDate is NULL OR QTY is NULL OR QTY = 0) AND LNG_FLG = 2 THEN
				NULL;
			ELSE 
				PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
				IF PARA = 'COMPLETE' THEN
					NULL;
				ELSE
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
		OUT_PARA := 'RESULT_INJECTION ' || sqlcode || sqlerrm;
	null;
    END;
/
