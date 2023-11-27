CREATE OR REPLACE PROCEDURE CREATE_DIRECTION(LNG_FLG IN NUMBER,STR_CODE IN NVARCHAR2,LNG_DIV IN NUMBER,STR_LOGIN_USER IN NVARCHAR2,OUT_PARA OUT NVARCHAR2 ) IS
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
		OUT_PARA := 'CREATE_DIRECTION ' || sqlcode || sqlerrm;
		
    END;
/
