CREATE OR REPLACE PROCEDURE RESULT_DIRECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
		-- カーソル変数定義
	BEGIN 
		IF LNG_DIV is NULL THEN
			OUT_PARA := '指図区分なし。';
			GOTO P_END;
		END IF;
		IF STR_CODE is NULL or STR_CODE = '' THEN 
			OUT_PARA := '指図番号なし。';
			GOTO P_END;
		END IF;
		IF STR_LOGIN_USER is NULL or STR_LOGIN_USER = '' THEN 
			OUT_PARA := 'ログインユーザーなし。';
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
			IF LNG_FLG < 3 OR LNG_FLG = 5 THEN
				NULL;
			ELSE
				OUT_PARA := '処理選択が不正です。';
				GOTO P_END;
			END IF;
			Location := 'B7';
		else
			addOpno := 1;
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
					InOutDate := rtHead.RESULT_EDATE; -- 入庫予定
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
					InOutDate := rtHead.RESULT_EDATE; -- 入庫予定
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
					InOutDate := rtHead.RESULT_EDATE; -- 入庫予定
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
					InOutDate := rtHead.RESULT_EDATE; -- 入庫予定
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
					InOutDate := rtHead.PLANED_EDATE; -- 入庫予定
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
					InOutDate := rtHead.CERTIFICATION_DATE; -- 入庫予定
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
					InOutDate := rtHead.CERTIFICATION_DATE; -- 入庫予定
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
			PAK_ALLZAITABLE.STR_SECTIONCD := rtHead.PRODUCTION_LINE;
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
			PAK_ALLZAITABLE.DTE_LOTMAKEDATE := rtHead.CERTIFICATION_DATE;
			--パッケージ実行
			IF InOutDate is NULL AND (LNG_FLG = 2 OR LNG_FLG = 4 OR LNG_FLG = 7 ) THEN
				NULL;
			ELSE 
				PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
				--戻り値セット
				OUT_PARA := PARA;
				IF PARA = 'COMPLETE' THEN
					NULL;
				ELSE
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
		OUT_PARA := 'RESULT_DIRECTION ' || sqlcode || sqlerrm;
		
    END;
/
