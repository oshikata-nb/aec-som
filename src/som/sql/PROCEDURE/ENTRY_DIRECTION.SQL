CREATE OR REPLACE PROCEDURE ENTRY_DIRECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
		else
			addOpno := 2;
		end if;
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
		PAK_ALLZAITABLE.STR_SECTIONCD := rtHead.PRODUCTION_LINE;
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
		PAK_ALLZAITABLE.DTE_LOTMAKEDATE := rtHead.CERTIFICATION_DATE;
		PAK_ALLZAITABLE.STR_RYCD := null;

		OUT_PARA := '';

		--パッケージ実行
		PAK_ALLZAITABLE.UPDATE_ZAI(PARA);
		--戻り値セット
		OUT_PARA := PARA;
 <<P_END>>
			null;
	EXCEPTION
		when others then
			IF curHead%ISOPEN THEN
				CLOSE curHead;
			END IF;
			dbms_output.put_line('ENTRY_DIRECTION ' || sqlcode || sqlerrm);
		OUT_PARA := 'ENTRY_DIRECTION ' || sqlcode || sqlerrm;
		
    END;
/
