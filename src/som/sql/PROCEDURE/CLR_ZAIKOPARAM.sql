CREATE OR REPLACE PROCEDURE      CLR_ZAIKOPARAM
IS
BEGIN
			--更新引数セット1-10
			PAK_ALLZAITABLE.LNG_OPNO :=0;
			PAK_ALLZAITABLE.LNG_INOUTKBN := 0;
			PAK_ALLZAITABLE.STR_ITEMCODE := '';
			PAK_ALLZAITABLE.LNG_QTY := 0;
			PAK_ALLZAITABLE.LNG_FAULTQTY := 0;
			PAK_ALLZAITABLE.DTE_INOUTDATE := null;
			PAK_ALLZAITABLE.STR_LOCATIONCODE := null;
			PAK_ALLZAITABLE.LNG_COST := 0;
			PAK_ALLZAITABLE.DTE_UKEDATE := null;
			PAK_ALLZAITABLE.LNG_COSTFLG := 0;
			--11-20
			PAK_ALLZAITABLE.LNG_CHKFLG := 0;
			PAK_ALLZAITABLE.LNG_CALCCOSTFLG := 0;
			PAK_ALLZAITABLE.LNG_INVENTORYQTY :=null;
			PAK_ALLZAITABLE.LNG_INVENTORYCOST := null;
			PAK_ALLZAITABLE.STR_LOTCODE := null;
			PAK_ALLZAITABLE.DTE_START_DATE := null;
			PAK_ALLZAITABLE.DTE_ISSUE_DATE := null;
			PAK_ALLZAITABLE.DTE_END_DATE := null;
			PAK_ALLZAITABLE.LNG_INOUTDIVISION := 0;
			PAK_ALLZAITABLE.STR_ODERNO :=  '';
			--21-30
			PAK_ALLZAITABLE.LNG_ODERLINENO := 0;
			PAK_ALLZAITABLE.LNG_INOUTPRICE := 0;
			PAK_ALLZAITABLE.LNG_INOUTCOST := 0;
			PAK_ALLZAITABLE.STR_REMARK := '';
			PAK_ALLZAITABLE.LNG_INOUTSOURCEDELFLG := 0;
			PAK_ALLZAITABLE.STR_INOUTSOURCENO := '';
			PAK_ALLZAITABLE.STR_SECTIONCD := '';
			PAK_ALLZAITABLE.STR_ACCOUNTCD := '';
			PAK_ALLZAITABLE.STR_SUBACCOUNTCD := '';
			PAK_ALLZAITABLE.STR_ITEMCATEGORY := '';
			--31-40
			PAK_ALLZAITABLE.STR_PARENTITEMCD := '';
			PAK_ALLZAITABLE.STR_PARENTACCOUNTCD := '';
			PAK_ALLZAITABLE.STR_PARENTSUBACCOUNTCD := '';
			PAK_ALLZAITABLE.STR_PARENTITEMCATEGORY := '';
			PAK_ALLZAITABLE.STR_REASON := '';
			PAK_ALLZAITABLE.LNG_LOTTRACEDELFLG := 0;
			PAK_ALLZAITABLE.LNG_FUNCDIVISION := 0;
			PAK_ALLZAITABLE.STR_SLIPNO := '';
			--41-46
			PAK_ALLZAITABLE.LNG_SLIPLINENO := 0;
			PAK_ALLZAITABLE.LNG_ORDERDIVISION := 0;
			PAK_ALLZAITABLE.STR_ORDERNO := '';
			PAK_ALLZAITABLE.LNG_LASTINDIVISION := 0;
			PAK_ALLZAITABLE.STR_LASTINNO := '';
			PAK_ALLZAITABLE.STR_INPUTNO := '';
			PAK_ALLZAITABLE.STR_HANPA_ITEMCODE := '';
			PAK_ALLZAITABLE.LNG_HANPAQTY := 0;
END;
/
