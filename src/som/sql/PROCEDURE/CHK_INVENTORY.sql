CREATE OR REPLACE PROCEDURE CHK_INVENTORY(STR_ITEM IN NVARCHAR2,QTY IN NUMBER,B_FLG IN boolean ,STR_MESSAGE OUT NVARCHAR2) IS
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
/
