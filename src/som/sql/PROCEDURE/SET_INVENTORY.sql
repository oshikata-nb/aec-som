CREATE OR REPLACE PROCEDURE AP21.SET_INVENTORY(OUT_MSG OUT VARCHAR2)
IS
-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■  在庫再設定用　プロシージャ   2009/06/19 ETO
-- ■
-- ■　LOT_INVENTORYを正としてLOCATION_INVENTORY,ITEM_INVENTORYをセットします。　
-- ■　LOCATION_INVENTORYは全てのデータが更新されます。　　　　　　　　　　　　　
-- ■　ITEM_INVENTORYはINVENTORY_QTYだけが更新されます。
-- ■　ITEM_QUEUEにない、在庫不可なLOCATIONなど不正なLOT_INVENTORYは削除されます。
-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		cursor curLocation is
		select DISTINCT LEVEL,UPPER_LOCATION_CD from LOCATION START WITH UPPER_LOCATION_CD is null 
		CONNECT BY   UPPER_LOCATION_CD = PRIOR  LOCATION_CD  order by level desc;
		rtLocation curLocation%rowtype;
		cursor curLot is 
		select distinct LOCATION_CD,ITEM_CD from LOT_INVENTORY;
		rtLot curLot%rowtype;
		cursor curITEM is 
		select distinct ITEM_CD from LOT_INVENTORY;
		rtITEM curITEM%rowtype;
	CT number;
	ITEMCODE NVARCHAR2(40);
	QTY number;
	LOCATIONCODE NVARCHAR2(40);
BEGIN
	-- LOCATION_INVENTORYをクリア
	delete LOCATION_INVENTORY ;
	-- 不正なLOT_INVENTORYを削除
	OPEN curLot;
	LOOP
		FETCH curLot INTO rtLot;
		IF curLot%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
		ITEMCODE := rtLot.ITEM_CD;
		LOCATIONCODE := rtLot.LOCATION_CD;
		SELECT COUNT(*) INTO CT FROM ITEM_QUEUE WHERE ITEM_CD = ITEMCODE;
		IF CT = 0 THEN
			DELETE FROM LOT_INVENTORY WHERE ITEM_CD = ITEMCODE AND LOCATION_CD = LOCATIONCODE;
			GOTO NEXT_LOOP;
		END IF;
		SELECT COUNT(*) INTO CT FROM LOCATION WHERE AVAILABLE_FLG = 1 AND LOCATION_CD = LOCATIONCODE;
		IF CT = 0 THEN
			DELETE FROM LOT_INVENTORY WHERE ITEM_CD = ITEMCODE AND LOCATION_CD = LOCATIONCODE;
			GOTO NEXT_LOOP;
		END IF;
		SELECT COUNT(*) INTO CT FROM ITEM_INVENTORY WHERE ITEM_CD = ITEMCODE;
		IF CT = 0 THEN
			INSERT INTO ITEM_INVENTORY
			(
				ITEM_CD
				,PARENT_ITEM_CD
				,INPUT_DATE
				,INPUTOR_CD
				,UPDATE_DATE
				,UPDATOR_CD
			)
			VALUES
			(
				ITEMCODE
				,FUN_GET_ITEM_PARENT_ITEM_CD(ITEMCODE)
				,sysdate
				,'bat'
				,sysdate
				,'bat'
			);
		END IF;
<<NEXT_LOOP>>
		null;
	END LOOP;
	CLOSE curLot;
	--最下位レベルのロケーション在庫をロット在庫からセットする
	INSERT INTO LOCATION_INVENTORY 
		SELECT LOCATION_CD
    			,ITEM_CD
			,FUN_GET_ITEM_PARENT_ITEM_CD(ITEM_CD)
    			,sum(INVENTORY_QTY)
    			,sum(BACKORDER_QTY)
    			,sum(ASSIGN_QTY)
    			,sum(SALES_ASSIGN_QTY)
    			,sum(FINISH_QTY)
    			,sum(INSPECTION_QTY)
    			,sum(INVALID_QTY)
    			,sum(FAULT_QTY)
    			,avg(INVENTORY_COST)
    			,max(LAST_IN_DATE)
    			,max(LAST_OUT_DATE)
    			,null
    			,0
    			,0
    			,0
    			,0
    			,0
    			,0
    			,0
    			,min(INPUT_DATE)
    			,'bat'
    			,sysdate
    			,'bat'
		FROM LOT_INVENTORY GROUP BY LOCATION_CD,ITEM_CD;

	--上位ロケーション在庫を下位ロケーション在庫を使ってセットする
	OPEN curLocation ;
	LOOP 
		FETCH curLocation INTO rtLocation;
		IF curLocation%NOTFOUND = TRUE THEN EXIT;END IF;
		LOCATIONCODE := rtLocation.UPPER_LOCATION_CD;
		IF LOCATIONCODE is NULL then
				null;
		ELSE
			INSERT INTO LOCATION_INVENTORY 
				SELECT LOCATIONCODE
    					,ITEM_CD
					,FUN_GET_ITEM_PARENT_ITEM_CD(ITEM_CD)
    					,sum(INVENTORY_QTY)
    					,sum(BACKORDER_QTY)
    					,sum(ASSIGN_QTY)
    					,sum(SALES_ASSIGN_QTY)
    					,sum(FINISH_QTY)
    					,sum(INSPECTION_QTY)
    					,sum(INVALID_QTY)
    					,sum(FAULT_QTY)
    					,avg(INVENTORY_COST)
    					,max(LAST_IN_DATE)
    					,max(LAST_OUT_DATE)
    					,null
    					,0
    					,0
    					,0
    					,0
    					,0
    					,0
    					,0
    					,min(INPUT_DATE)
    					,'bat'
    					,sysdate
    					,'bat'
				FROM LOCATION_INVENTORY WHERE LOCATION_CD IN (SELECT LOCATION_CD FROM LOCATION WHERE UPPER_LOCATION_CD = LOCATIONCODE ) GROUP BY ITEM_CD;
		END IF;
	END LOOP;
	CLOSE curLocation;
	--ITEM_INVENTORYの設定
	UPDATE ITEM_INVENTORY SET INVENTORY_QTY = (SELECT sum(INVENTORY_QTY) from LOT_INVENTORY WHERE LOT_INVENTORY.ITEM_CD = ITEM_INVENTORY.ITEM_CD)
				,UPDATE_DATE = sysdate
				,UPDATOR_CD = 'bat';
	--判定
	select count(*) INTO CT from
	(select sum(INVENTORY_QTY) as s1 from ITEM_INVENTORY) a,
	(select sum(INVENTORY_QTY) as s2 from LOT_INVENTORY ) b,
	(select sum(INVENTORY_QTY) as s3 from LOCATION_INVENTORY c,LOCATION d WHERE c.LOCATION_CD=d.LOCATION_CD and d.UPPER_LOCATION_CD is null) e,
	(select sum(INVENTORY_QTY) as s4 from LOCATION_INVENTORY f,LOCATION g WHERE f.LOCATION_CD=g.LOCATION_CD and g.AVAILABLE_FLG = 1) h
	where a.s1 = b.s2 and a.s1 = e.s3 and a.s1 = h.s4;
	IF CT = 0 THEN
		OUT_MSG := 'Not COMPLETE';
		COMMIT;
	ELSE
		OUT_MSG := 'COMPLETE';
		ROLLBACK;
	END IF;
	DBMS_OUTPUT.PUT_LINE(OUT_MSG);
			
EXCEPTION
		when others then
			IF curLot%ISOPEN THEN
				CLOSE curLot;
			END IF;
			IF curLocation%ISOPEN THEN
				CLOSE curLocation;
			END IF;
			ROLLBACK;
			OUT_MSG := 'ERROR ' || sqlcode || sqlerrm;
			dbms_output.put_line(OUT_MSG);
END;
/
