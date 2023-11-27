CREATE OR REPLACE PACKAGE BODY      PD_PLAN_PACKAGE IS

--■■■■■■■■■■■■■■■■■■■
--■　生産計画メイン
--■　パラメータ:無し
--■■■■■■■■■■■■■■■■■■■
procedure PD_PLAN_MAIN IS
--宣言部
	boRet	boolean;	--戻り値

BEGIN
--プログラム開始
	--初期処理
	--PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogOk, 'PD_PLAN_MAIN', '生産計画作成実施 ' || g_daDate);

	--すべての計画を抹消
	DELETE FROM INOUT_SOURCE
	WHERE (INOUT_DIVISION= PD_PLAN_PACKAGE.pc_IoDivPlanIn OR INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivPlanOut);

	--生産計画登録判定
	boRet := FUN_PD_PLAN_JUD();
	if boRet=true then
		dbms_output.put_line('計画 作成終了');
		commit;
	end if;
EXCEPTION
	when others then
	dbms_output.put_line('PD_PLAN_MAIN' || sqlcode || sqlerrm);

END;

--■■■■■■■■■■■■■■■■■■■
--■　生産計画登録判定
--■　パラメータ:無し
--■■■■■■■■■■■■■■■■■■■
FUNCTION FUN_PD_PLAN_JUD RETURN boolean IS
--宣言部
	--******カーソル定義******
	--計画品目
	CURSOR curItem IS
	SELECT A1.ITEM_CD , A1.LLC
	FROM ITEM A1, PRODUCT_ATTRIBUTE_QUEUE A2
	WHERE
	A1.ITEM_CD = A2.ITEM_CD AND A1.PRODUCT_DIVISION <> 0 AND A1.PHANTOM_DIVISION = 0 AND
	--A2.STATUS = 3 AND ( A2.PLAN_DIVISION = 1 OR A2.PLAN_DIVISION = 2 )
	( A2.PLAN_DIVISION = 1 OR A2.PLAN_DIVISION = 2 )
	GROUP BY A1.ITEM_CD, A1.LLC
	ORDER BY A1.LLC ASC;

	--受払ソース取得（品目コード）
	CURSOR curInout(vItemCd VARCHAR2) IS
--20090107 edit st
	SELECT INOUT_DIVISION, ODER_NO, ODER_LINE_NO, sum(INOUT_QTY) as INOUT_QTY, INOUT_DATE, REFERENCE_NO, REFERENCE_LINE_NO
	FROM INOUT_SOURCE
	--WHERE ITEM_CD = vItemCd and INOUT_DATE>=trunc(sysdate) and (INOUT_DIVISION <> PD_PLAN_PACKAGE.pc_IoDivItemOut or (INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivItemOut and  REFERENCE_NO is null ))
	WHERE ITEM_CD = vItemCd and INOUT_DATE>=trunc(sysdate) and  REFERENCE_NO is null
	GROUP BY INOUT_DIVISION, ODER_NO, ODER_LINE_NO, INOUT_DATE, REFERENCE_NO, REFERENCE_LINE_NO
	ORDER BY INOUT_DATE ASC;

	--SELECT A.INOUT_DIVISION, A.ODER_NO, A.ODER_LINE_NO, sum(A.INOUT_QTY) as INOUT_QTY, A.INOUT_DATE, A.REFERENCE_NO, A.REFERENCE_LINE_NO
	--FROM INOUT_SOURCE A,PRODUCT_ATTRIBUTE_QUEUE B
	--WHERE A.ITEM_CD = vItemCd and B.ITEM_CD = vItemCd and A.INOUT_DATE>=trunc(sysdate) and  (A.REFERENCE_NO is null or B.PLAN_DIVISION = 1) and B.VERSION = (select max(C.VERSION) from PRODUCT_ATTRIBUTE_QUEUE C where C.ITEM_CD = vItemCd)
	--GROUP BY A.INOUT_DIVISION, A.ODER_NO, A.ODER_LINE_NO, A.INOUT_DATE, A.REFERENCE_NO, A.REFERENCE_LINE_NO
	--ORDER BY A.INOUT_DATE ASC;
--20090107 edit ed

	--受払ソース最終日付取得
	--CURSOR curInoutLast(vItemCd VARCHAR2) IS
	--SELECT max(INOUT_DATE) as LAST_DATE
	--FROM INOUT_SOURCE
	--WHERE ITEM_CD = vItemCd
	--group by ITEM_CD;

	--受払ソースリードタイム先数量取得
	CURSOR curInoutQty(vItemCd VARCHAR2, daDate date) IS
	SELECT sum(INOUT_QTY) as LT_INOUT_QTY
	FROM INOUT_SOURCE
	--WHERE ITEM_CD = vItemCd and INOUT_DATE>=trunc(sysdate) and INOUT_DATE <= daDate
	WHERE ITEM_CD = vItemCd and INOUT_DATE <= daDate --現在以前の在庫も参照するように修正20090129
	--and REFERENCE_NO is null --add 20090129
	group by ITEM_CD;

	--製造品扱い属性取得（品目コード）
	CURSOR curPdatt(vItemCd VARCHAR2) IS
	SELECT PLAN_DIVISION,PRODUCTION_LEAD_TIME,ORDER_POINT
	FROM PRODUCT_ATTRIBUTE_QUEUE
	WHERE
	ITEM_CD = vItemCd;

	--在庫数量取得
	CURSOR curInv(vItemCd VARCHAR2) IS
	--SELECT NVL(INVENTORY_QTY,0) as INVENTORY_QTY, NVL(BACKORDER_QTY) as BACKORDER_QTY, NVL(ASSIGN_QTY) as ASSIGN_QTY
	SELECT NVL(INVENTORY_QTY,0) as INVENTORY_QTY
	FROM ITEM_INVENTORY
	WHERE
	ITEM_CD = vItemCd;

	--オーダー情報取得（品目コード）
	CURSOR curOrder(vOrderNo VARCHAR2) IS
	SELECT ORDER_DIVISION
	FROM ORDER_HEAD
	WHERE ORDER_NO = vOrderNo ;

	--******ユーザ定義型******
	--******ローカル変数******
	rtItem 		curItem%rowtype;	--生産計画対象品目
	rtInout 	curInout%rowtype; 	--受払ソース
	rtPdatt 	curPdatt%rowtype; 	--製造品扱い属性
	rtInv		curInv%rowtype; 	--在庫数量
	--rtInoutLast	curInoutLast%rowtype;--受払ソース最終日
	rtInoutQty	curInoutQty%rowtype;--受払ソース数量
	rtOrder		curOrder%rowtype;	--オーダー情報
	nStockQty	NUMBER;				--在庫推移数量
	nStockLtQty	NUMBER;				--リードタイム先在庫推移数量
	daChkLttime date;				--リードタイム考慮開始日付
	daLttime 	date;				--リードタイム考慮開始日付
	daNow		date;				--現在日時
	nPlanQty	NUMBER;				--計画数量
	nPlanFlg	NUMBER;				--計画フラグ 初期処理:1 充填登録時:2 受注登録時:3
	boRet		boolean;			--戻り値
	vOrderNo	varchar2(20);
	nOrderLineNo NUMBER;
	iInitPlan	number;				--初期計画
	daBreak1st	date;				--初期発注点割れ日付
	daMakeDate	date;
	iBreakFlg	number;
	vtestItem	varchar2(20);
	nWork		number;
	nSucCnt		number;--成功数
	nFailCnt	number;--失敗数

BEGIN
--プログラム開始
	--計画品目のカーソルオープン
	OPEN curItem;
	nPlanFlg := 0;
	iInitPlan := 0;
	nSucCnt := 0;
	nFailCnt := 0;
	loop --品目ループ
		fetch curItem into rtItem;
		--終了判定
		if  curItem%NOTFOUND=true then
			exit;
		end if;

		--在庫数量取得
		OPEN curInv(rtItem.ITEM_CD);
		fetch curInv into rtInv;
		if curInv%notfound=true then
			rtInv.INVENTORY_QTY:=0;
		end if;
		close curInv;

		nStockQty := rtInv.INVENTORY_QTY;

		/* 製造品扱い属性カーソルオープン */
		open curPdatt(rtItem.ITEM_CD);
		fetch curPdatt into rtPdatt;
		close curPdatt;
		/* 受払ソース最終日付取得カーソルオープン */
		--open curInoutLast(rtItem.ITEM_CD);
		--fetch curInoutLast into rtInoutLast;
		--close curInoutLast;

		vtestItem:=rtItem.ITEM_CD;
		if (rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivFill or
				rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivOrder) then --and rtInv.INVENTORY_QTY != NULL then
			--//////初回生産処理//////
			if rtPdatt.PLAN_DIVISION= PD_PLAN_PACKAGE.pc_PlanDivFill then --生産計画区分=1（充填補充）
				--リードタイム先の日付（作成完了日付）
				select trunc(sysdate) into daNow from dual;
				daMakeDate := daNow + rtPdatt.PRODUCTION_LEAD_TIME;

				--リードタイム先の数量を取得
				open curInoutQty(rtItem.ITEM_CD, daMakeDate);
				fetch curInoutQty into rtInoutQty;

				if curInoutQty%notfound = true then
					nStockLtQty:=0;
				else
					nStockLtQty:=rtInoutQty.LT_INOUT_QTY;
				end if;
				close curInoutQty;
				nStockLtQty := rtInv.INVENTORY_QTY + nStockLtQty;
				--現在日付からリードタイム分先の在庫推移を先に見る
				if rtPdatt.ORDER_POINT > nStockLtQty then
					--リードタイム先も在庫割れ
					if nStockLtQty < 0 then
						--0以下
						nWork := rtPdatt.ORDER_POINT+(-1*nStockLtQty);
					else
						--在庫あり
						nWork := rtPdatt.ORDER_POINT-nStockLtQty;
					end if;
					--最終生産量
					nPlanQty := ceil(nWork );

					--生産計画登録処理実行
					boRet := FUN_PD_PLAN_REG( rtItem.ITEM_CD, nPlanQty, rtPdatt.PLAN_DIVISION,
								daMakeDate, NULL, NULL,rtPdatt.PRODUCTION_LEAD_TIME,null);

					if boRet = false then
						--生産計画登録失敗時は該当品目終了
						--DBへエラーログ登録「品目など」
						--close curInout;
						--exit;
						nFailCnt:=nFailCnt+1;
					else
						PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogOk, 'FUN_PD_PLAN_JUD',
						'生産計画登録成功（初回補充生産） 品目=' || rtItem.ITEM_CD || ' 数量=' || nPlanQty );
						nSucCnt:=nSucCnt+1;
					end if;
				end if;
			end if;

			--フラグ初期設定
			nPlanFlg := 1;
			iBreakFlg := 0;
			loop--受払ソースループ
				if nPlanFlg = 1 or nPlanFlg = 2 then
					--初期処理時or前回充填登録時
					--受払ソース取得(件数分)
					OPEN curInout(rtItem.ITEM_CD);
					fetch curInout into rtInout;
					nStockQty := rtInv.INVENTORY_QTY;
				else
					fetch curInout into rtInout;
				end if;

				--終了判定
				if  curInout%NOTFOUND=true then
					if curInout%isopen =true then
						close curInout;
					end if;
					exit;
				end if;

				--受払区分による在庫推移
				if rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivDirectIn or	--製造受入
					rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivItemIn or	--入荷受入
					rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivPlanIn or	--計画受入
					rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivDirectOut or--製造払出
					rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivItemOut or	--出荷払出
					rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivSupplyOut or--支給払出
					rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivPlanOut then--計画払出

					--受払数量加算減算
					nStockQty := nStockQty + rtInout.INOUT_QTY;
				end if;

				--フラグ初期化
				nPlanFlg :=0;

				--生産計画区分判定
				case rtPdatt.PLAN_DIVISION
				when PD_PLAN_PACKAGE.pc_PlanDivFill then --生産計画区分=1（充填補充）
					/* 在庫推移量と「発注点」を比較し、在庫推移量＜発注点であれば以下の処理を行う。*/
					IF rtPdatt.ORDER_POINT > nStockQty THEN
					--*******発注点を割り込んだ場合*******
						--第一割り込み日格納
						--daBreak1st := rtInout.INOUT_DATE;
						--リードタイム先の日付（作成完了日付）
						daMakeDate := rtInout.INOUT_DATE + rtPdatt.PRODUCTION_LEAD_TIME;

						--リードタイム先の数量を取得
						open curInoutQty(rtItem.ITEM_CD, daMakeDate);
						fetch curInoutQty into rtInoutQty;
						if curInoutQty%notfound = true then
							nStockLtQty:=0;
						else
							nStockLtQty:=rtInoutQty.LT_INOUT_QTY;
						end if;
						close curInoutQty;

						nStockLtQty := rtInv.INVENTORY_QTY + nStockLtQty;

						if rtPdatt.ORDER_POINT > nStockLtQty then
							--リードタイム先も在庫割れ
							nWork := rtPdatt.ORDER_POINT-nStockLtQty;

							--最終生産量
							nPlanQty := ceil(nWork );

							nPlanFlg := 2;
							daLttime := rtInout.INOUT_DATE ;--- 1;

							--製造に必要な日数を差し引く
--							daChkLttime:= rtInout.INOUT_DATE - rtPdatt.PRODUCTION_LEAD_TIME - 1;

							--リードタイム考慮日付-現在日付より過去かの判定
--							if to_date(daChkLttime, 'YYYY/MM/DD') - to_date(sysdate, 'YYYY/MM/DD') < 0 then
--								dbms_output.put_line('FUN_PD_PLAN_JUD リードタイム日付過去='
--								|| rtItem.ITEM_CD || 'リードタイム=' || rtPdatt.PRODUCTION_LEAD_TIME || '考慮日付=' || daLttime);
--								--過去の場合は受注日付で登録
--								--daLttime:= rtInout.INOUT_DATE-1;
--
--								--過去の場合は現在日付で登録
--								daLttime:= to_date(sysdate, 'YYYY/MM/DD');
--								nPlanFlg := 2;
--
--								PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogInf, 'FUN_PD_PLAN_JUD',
--								'リードタイム日付過去 品目=' || rtItem.ITEM_CD || ' 日付=' || daLttime );

--							else
								--現在以降の日付の場合は正常

								--if nStockLtQty < 0 then
								--0以下

								--else
									--在庫あり
								--	nWork := rtPdatt.ORDER_POINT-nStockLtQty;
								--end if;
								--最終生産量
								--nPlanQty := ceil(nWork / rtPdatt.ORDER_QTY) * rtPdatt.ORDER_QTY;
--								nPlanFlg := 2;
--								daLttime := daChkLttime;

--							end if;
						end if;
					END IF;
				when PD_PLAN_PACKAGE.pc_PlanDivOrder then --生産計画区分=2（受注生産）
					/* 受払区分=4（出荷払出）で取得した受注数量分の生産計画登録処理を行う。
					（受払ソーステーブルの受注数量、出荷予定日をパラメータへ指定）*/
					if rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivDirectOut or--製造払出
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivItemOut or	--出荷払出
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivSupplyOut or--支給払出
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivPlanOut then--計画払出

						--生産開始
						daLttime := rtInout.INOUT_DATE - rtPdatt.PRODUCTION_LEAD_TIME-1;--（仮）

						--20090108 add
						--vOrderNo := rtInout.ODER_NO;
						--nOrderLineNo := rtInout.ODER_LINE_NO;

						--リードタイム考慮日付-現在日付より過去かの判定
						if to_date(daLttime, 'YYYY/MM/DD') - to_date(sysdate, 'YYYY/MM/DD') < 0 then
							dbms_output.put_line('FUN_PD_PLAN_JUD リードタイム考慮日付＝過去（受注生産）');
							--過去の場合は受注日付で登録
							--daLttime:= rtInout.INOUT_DATE-1;

							--add start 20090106
							--計画数量格納
							nPlanQty := -1 * rtInout.INOUT_QTY;

							--生産計画フラグON
							nPlanFlg := 3;

							--過去の場合は現在日付で登録
							daLttime:=to_date(sysdate, 'YYYY/MM/DD');
							--add end 20090106
						else
							--計画数量格納
							nPlanQty := -1 * rtInout.INOUT_QTY;
							--生産計画フラグON
							nPlanFlg := 3;
							--完了日付
							daLttime:= rtInout.INOUT_DATE -1;
						end if;
					end if;
				else
					dbms_output.put_line('FUN_PD_PLAN_JUD 生産計画区分該当無し=' || rtItem.ITEM_CD || ' 区分=' || rtPdatt.PLAN_DIVISION);
					PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_JUD',
					'生産計画区分該当無し 品目=' || rtItem.ITEM_CD || ' 区分=' || rtPdatt.PLAN_DIVISION );
					nFailCnt:=nFailCnt+1;
				end case;

				--生産計画登録処理
				if nPlanFlg = 2 or nPlanFlg = 3 then
					--オーダー番号確定
					if rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivItemOut then--出荷払出
						--オーダ番号格納
						vOrderNo := rtInout.ODER_NO;
						nOrderLineNo := rtInout.ODER_LINE_NO;

					elsif rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivDirectOut or	--製造払出
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivDirectIn  or	--製造受入
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivSupplyOut or	--支給払出
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivPlanOut or		--計画払出
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivItemIn or		--入荷受入
						rtInout.INOUT_DIVISION = PD_PLAN_PACKAGE.pc_IoDivPlanIn then	--計画受入
						--リファレンス番号格納
						--vOrderNo := rtInout.REFERENCE_NO;
						--nOrderLineNo := rtInout.REFERENCE_LINE_NO;
						vOrderNo := rtInout.ODER_NO;
						nOrderLineNo := null;
						--nOrderLineNo := rtInout.ODER_LINE_NO;
					end if;

					--if ( rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivOrder and PD_PLAN_PACKAGE.pc_OrdDivPd = rtOrder.SUPPLIER_ORDER_DIVISION) then
					if rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivOrder then
						--オーダー明細取得2008/04/16
						OPEN curOrder(vOrderNo );
						fetch curOrder into rtOrder;
						close curOrder;
						--受注オーダーの受注区分が製品の物のみ生産計画対象
						--if PD_PLAN_PACKAGE.pc_OrdDivPd = rtOrder.SUPPLIER_ORDER_DIVISION then
							--**********生産計画登録処理実行(受注生産)**********
							boRet := FUN_PD_PLAN_REG_ORDERITEM( rtItem.ITEM_CD, nPlanQty, rtPdatt.PLAN_DIVISION,
										daLttime, vOrderNo, nOrderLineNo,rtPdatt.PRODUCTION_LEAD_TIME);
							if boRet = false then
								--生産計画登録失敗時は該当品目終了
								--DBへエラーログ登録「品目など」
								close curInout;
								nFailCnt:=nFailCnt+1;
								exit;
							else
								--成功ログ
								PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogOk, 'FUN_PD_PLAN_JUD',
								'生産計画登録成功（受注生産） 品目=' || rtItem.ITEM_CD || ' 数量=' || nPlanQty );
								nSucCnt:=nSucCnt+1;
							end if;
						--else
						--	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_JUD',
						--	'受注区分製品以外 品目=' || rtItem.ITEM_CD || ' 数量=' || nPlanQty );
						--	nFailCnt:=nFailCnt+1;
						--end if;

					elsif rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivFill then
						--**********生産計画登録処理実行**********
						boRet := FUN_PD_PLAN_REG( rtItem.ITEM_CD, nPlanQty, rtPdatt.PLAN_DIVISION,
									daLttime, vOrderNo, nOrderLineNo,rtPdatt.PRODUCTION_LEAD_TIME,null);

						if boRet = false then
							--生産計画登録失敗時は該当品目終了
							--DBへエラーログ登録「品目など」
							close curInout;
							nFailCnt:=nFailCnt+1;
							exit;
						else
							PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogOk, 'FUN_PD_PLAN_JUD',
							'生産計画登録成功（補充生産） 品目=' || rtItem.ITEM_CD || ' 数量=' || nPlanQty );
							nSucCnt:=nSucCnt+1;
						end if;
					end if;

					--終了判定
					if nPlanFlg <> 3 then
						close curInout;
					end if;
				end if;
			end loop;--受払ソースループ

		end if;
	end loop;--品目ループ
	CLOSE curItem;

--	PRO_PLAN_RECORD(g_daDate,nSucCnt,nFailCnt);

	return (true);

EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_JUD' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_JUD', sqlcode || sqlerrm );
	--PRO_PLAN_RECORD(g_daDate,null,null);
	return (false);
end;


--■■■■■■■■■■■■■■■■■■■
--■　処方ヘッダ選択
--■　パラメータ:IN-品目コード、必要数
--■　          :OUT-レシピインデックス、レシピコード、レシピバージョン、生産数、全生産数
--■　概要:品目コードから処方ヘッダ選択
--■■■■■■■■■■■■■■■■■■■
PROCEDURE PD_PLAN_RECIPE_SELECT
	(vItemCd IN VARCHAR2, nPlanQty IN NUMBER,
	nRcpIndex OUT NUMBER, vRcpCd OUT VARCHAR2, nRcpVer OUT NUMBER, nPdQty OUT NUMBER,nPdAllQty OUT NUMBER)
	IS
--宣言部
	--******カーソル定義******
	--処方ヘッダ取得（品目コード）
	/* 処方ヘッダテーブル「ステータス=１（試作用）＋2（一般使用用）、用途=3（production）、
	主要製品コード=品目コード、　有効開始日時＜（発注点を割った年月日−リードタイム〜発注点を割った年月日）＜有効終了日時
	、承認ステータス=3（承認済み）」 で抽出 */
	CURSOR curRcpHead(vItemCd VARCHAR2) IS
	SELECT RECIPE_ID, RECIPE_CD, RECIPE_VERSION, RECIPE_PRIORITY, MIN_QTY, MAX_QTY, STD_QTY,  PROCESS_LOSS, UNIT_QTY
	FROM RECIPE_HEADER
	WHERE
		RECIPE_PRIORITY = ( Select MAX(RECIPE_PRIORITY) From RECIPE_HEADER where ( RECIPE_STATUS=1 or RECIPE_STATUS=2 )
							and (RECIPE_USE=3 or RECIPE_USE=4) and ITEM_CD=vItemCd and APPROVAL_STATUS=5 and RECIPE_TYPE=3 )
		and ( RECIPE_STATUS=1 or RECIPE_STATUS=2 ) and (RECIPE_USE=3 or RECIPE_USE=4) and ITEM_CD=vItemCd
		and APPROVAL_STATUS=5 and RECIPE_TYPE=3 and PRODUCTION_LINE <> pc_RcpLineSample--サンプルは除外20090121
	order by RECIPE_VERSION desc;

	--処方ヘッダ件数取得（品目コード）
	CURSOR curRcpHeadCnt(vItemCd VARCHAR2) IS
	SELECT count(*) as CNT
	FROM RECIPE_HEADER
	WHERE
		RECIPE_PRIORITY = ( Select MAX(RECIPE_PRIORITY) From RECIPE_HEADER where ( RECIPE_STATUS=1 or RECIPE_STATUS=2 )
							and (RECIPE_USE=3 or RECIPE_USE=4) and ITEM_CD=vItemCd and APPROVAL_STATUS=5 and RECIPE_TYPE=3 )
		and ( RECIPE_STATUS=1 or RECIPE_STATUS=2 ) and (RECIPE_USE=3 or RECIPE_USE=4) and ITEM_CD = vItemCd
		and APPROVAL_STATUS=5 and RECIPE_TYPE=3
	order by RECIPE_VERSION desc;
	--処方フォーミュラ
	--CURSOR curRcpFm( nRcpId NUMBER ) IS
	--select LINE_TYPE, ITEM_CD, sum(QTY) as QTY, UNIT
	--from RECIPE_FORMULA
	--where RECIPE_ID = nRcpId and (LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMain or LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMat or LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeSub)
	--group by LINE_TYPE, ITEM_CD, UNIT;

	--******ローカル変数******
	rtRcpHead 		curRcpHead%rowtype;	--処方ヘッダ
	rtRcpHeadCnt 	curRcpHeadCnt%rowtype;	--処方ヘッダ件数
	--rtRcpFm			curRcpFm%rowtype;	--処方フォーミュラ
	nNeedQty		NUMBER;				--必要数
	nWork			NUMBER;				--ワーク
	nRcpCnt			NUMBER;				--取得件数
	boRet			boolean;			--戻り値
	nCnt			NUMBER;	--処方レシピ数カウンタ
begin
	--初期処理
	nRcpIndex := 0;

	--処方ヘッダ件数取得
	open curRcpHeadCnt(vItemCd);
	fetch curRcpHeadCnt into rtRcpHeadCnt;
	close curRcpHeadCnt;

	--処方ヘッダ取得
	open curRcpHead( vItemCd );
	nRcpCnt:=0;
	loop
		--処方ヘッダ取得
		fetch curRcpHead into rtRcpHead;

		if curRcpHead%notfound = true then
			if nRcpCnt=0 then
				/* 処方ヘッダ無し→エラーログ「該当処方ヘッダ無し：品目コード＝XXXX）、
				発注点を割った年月日、発注点を割った未来在庫量」*/
				--return (false);
				PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'PD_PLAN_RECIPE_SELECT',
				'処方ヘッダ無し 品目=' || vItemCd || ' 計画数量=' || nPlanQty);
				goto P_END;
			end if;
			exit;
		end if;
		nRcpCnt :=nRcpCnt+1;

		--*****************生産数確定*****************
		--計画数量（必要数）から生産数を算出
		nNeedQty := nPlanQty;
		--仮生産数 ＝ 必要数／（１−ロス率／１０００）
		--nWork := nNeedQty / ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );
		--nWork := nNeedQty / ( rtRcpHead.PROCESS_LOSS / 100 );
		nWork := nNeedQty;--収率計算は生産数確定時は行わない20090217

		--nPdAllQty:= nNeedQty / ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );

		--全生産数 ＝｛（仮生産数／単位生産量）小数点以下繰り上げ｝＊単位生産量
		nPdAllQty := ceil(nWork / rtRcpHead.UNIT_QTY) * rtRcpHead.UNIT_QTY;

		if rtRcpHead.MIN_QTY = rtRcpHead.MAX_QTY then
			--最小生産量＝最大生産量
			nPdQty := rtRcpHead.MAX_QTY;

		elsif rtRcpHead.MIN_QTY <= nPdAllQty and nPdAllQty <= rtRcpHead.MAX_QTY then
			--最小〜最大生産数範囲内
			nPdQty := nPdAllQty;

		--範囲外--
		elsif nPdAllQty < rtRcpHead.MIN_QTY then
			--標準発注数＜最小生産量
			nPdQty := rtRcpHead.MIN_QTY;

		elsif rtRcpHead.MAX_QTY < nPdAllQty then
			--標準発注数＜最大生産量
			nPdQty := rtRcpHead.MAX_QTY;
		end if;

		--*****************処方ヘッダ選定*****************
		--処方ヘッダ取得
		--「優先度」が最大を取得。(select文で完了)
		--「最小生産量＜生産数＜最大生産量」の条件判定
		if rtRcpHead.MIN_QTY <= nPdAllQty and nPdAllQty <= rtRcpHead.MAX_QTY then
			--確定
			nRcpIndex := rtRcpHead.RECIPE_ID;
			vRcpCd := rtRcpHead.RECIPE_CD;
			nRcpVer := rtRcpHead.RECIPE_VERSION;
			exit;
		elsif rtRcpHead.MIN_QTY = rtRcpHead.MAX_QTY and rtRcpHead.MAX_QTY < nPdAllQty then
			--「最小生産量＝最大生産量＜生産数」の条件で抽出する。
			--確定
			nRcpIndex := rtRcpHead.RECIPE_ID;
			vRcpCd := rtRcpHead.RECIPE_CD;
			nRcpVer := rtRcpHead.RECIPE_VERSION;
			exit;
			--値が大きい（最新の）バージョン抽出(select文で完了)
		elsif rtRcpHeadCnt.CNT=nRcpCnt then
			--最終件数
			nRcpIndex := rtRcpHead.RECIPE_ID;
			vRcpCd := rtRcpHead.RECIPE_CD;
			nRcpVer := rtRcpHead.RECIPE_VERSION;
			exit;
		end if;
	end loop;
	close curRcpHead;
<<P_END>>
null;
EXCEPTION--エラー時
	--close curRcpHead;
	when others then
	dbms_output.put_line('PD_PLAN_RECIPE_SELECT' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'PD_PLAN_RECIPE_SELECT', sqlcode || sqlerrm );
end;


--■■■■■■■■■■■■■■■■■■■
--■　生産計画登録処理(受注生産品目)
--■　パラメータ:
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_REG_ORDERITEM
	(vItemCd VARCHAR2, nPlanQty NUMBER,nPlanDiv NUMBER, daPdSt DATE,
	vOrderNo VARCHAR2, nOrderLineNo NUMBER, nLdtime NUMBER)
	RETURN boolean IS

	--品目マスタ取得
	CURSOR curGetItem(vItemCd VARCHAR2) IS
	SELECT LLC
	FROM ITEM
	WHERE ITEM_CD=vItemCd;

	--製造品扱い属性取得（品目コード）
	CURSOR curPdatt(vItemCd VARCHAR2) IS
	SELECT PLAN_DIVISION,PRODUCTION_LEAD_TIME,ORDER_POINT
	FROM PRODUCT_ATTRIBUTE_QUEUE
	WHERE
	ITEM_CD = vItemCd;

	rtGetItem curGetItem%rowtype;
	nCnt number;
	nDirCnt number;
	nBefLlc number;
	daPlanStDate date;
	boRet boolean;
	nPlanSeq number;
	vPlanNoBase VARCHAR2(20);
	nAllPlanQty number;

	--*****構造体宣言*****
	TYPE tpLlcInfo IS RECORD (
		nLlcNo	NUMBER,
		nLlcNoCnt	NUMBER
	);

	--*****配列宣言*****
	TYPE t_table IS TABLE OF tpLlcInfo;
	tpLlc t_table:=t_table();
	nLlcMax number;
	nLLcCnt number;
	boFoundFlg boolean;
	ntestLLC number;
	nLlcArr number;
begin

	tpLlc.delete;

	--品目マスタ取得
	open curGetItem(vItemCd);
	fetch curGetItem into rtGetItem;

	if curGetItem%notfound = true then
		close curGetItem;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogNg, 'FUN_PD_PLAN_REG_ORDERITEM',
		'品目マスタ無し 品目=' || vItemCd);

		return false;
	end if;
	close curGetItem;

	--配列の初期化
	g_tpDirectionInfo :=tp_directiontable();
	g_tpDirectionInfo.EXTEND(1);--配列追加

	--代表品目詳細格納
	g_tpDirectionInfo(1).vItemCd := vItemCd;
	g_tpDirectionInfo(1).nLLC := rtGetItem.LLC;
	g_tpDirectionInfo(1).nQty := nPlanQty;
	g_tpDirectionInfo(1).nLeadTime := nLdtime;--リードタイム
	/*g_tpDirectionInfo(1).vLocation := '';--ロケーション
	g_tpDirectionInfo(1).nUse := '';		--使用用途
	g_tpDirectionInfo(1).nRcpIndex := '';	--レシピインデックス
	g_tpDirectionInfo(1).vRcpCd := '';			--レシピコード
	g_tpDirectionInfo(1).nRcpVer := '';		--レシピバージョン*/

	--受注生産品目取得
	if FUN_PD_PLAN_GET_ORDER_ITEM(g_tpDirectionInfo(1).vItemCd,nPlanQty) =false then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogNg, 'FUN_PD_PLAN_REG_ORDERITEM',
		'受注生産品目取得失敗 品目=' || g_tpDirectionInfo(1).vItemCd || ' 計画数量=' || nPlanQty);

		return (false);
	end if;
	nDirCnt := g_tpDirectionInfo.last;

	if nDirCnt is null then
		nDirCnt := 0;
	end if;

	--変数初期化
	nCnt:=0;
	--nLlcCnt:=1;
	nBefLlc := -1;
	daPlanStDate:=daPdSt;

	--製造指図配列添え字取得
	FOR nCnt IN 1..nDirCnt LOOP
		--LLCカウンタ加算判定

		nLlcMax := tpLlc.last;

		if nLlcMax is null then
			nLlcMax := 0;
		end if;

		boFoundFlg:=false;
		ntestLLC:=g_tpDirectionInfo(nCnt).nLLC;--test

		For nLlcCnt IN 1..nLlcMax LOOP
			if tpLlc(nLlcCnt).nLlcNo = g_tpDirectionInfo(nCnt).nLLC then
				tpLlc(nLlcCnt).nLlcNoCnt := tpLlc(nLlcCnt).nLlcNoCnt+1;
				boFoundFlg:=true;
				nLlcArr:=nLlcCnt;
				exit;
			end if;
		End Loop;

		if boFoundFlg = false then
			tpLlc.EXTEND(1);
			nLlcArr:=nLlcMax+1;
			tpLlc(nLlcArr).nLlcNo := g_tpDirectionInfo(nCnt).nLLC;
			tpLlc(nLlcArr).nLlcNoCnt := 1;

		end if;

		--if nBefLlc>0 and nBefLlc=g_tpDirectionInfo(nCnt).nLLC then
		--	nLlcCnt:=nLlcCnt+1;
		--else
		--	nLlcCnt:=1;
		--end if;

		--nBefLlc:=g_tpDirectionInfo(nCnt).nLLC;--add

		--開始日付終了日付
		if nCnt=1 then
			daPlanStDate:= daPlanStDate;
			--計画番号取得
			SELECT SEQ_PLAN.NEXTVAL INTO nPlanSeq FROM DUAL;
			--vPlanNo := 'PL' || to_char(daPdSt,'YYMMDDHHMM') || lpad(nPlanSeq,4,0);
		else
			daPlanStDate:= daPlanStDate - g_tpDirectionInfo(nCnt).nLeadTime - 1;
		end if;

		--add by tada 2008/12/25
		if daPlanStDate < trunc(sysdate) then
			daPlanStDate := trunc(sysdate);
		end if;

		vPlanNoBase := 'PL' || lpad(nPlanSeq,8,0) || '-' || lpad(g_tpDirectionInfo(nCnt).nLLC,2,0) || lpad(tpLlc(nLlcArr).nLlcNoCnt,2,0);
		--*********生産計画作成処理*********
		boRet := FUN_PD_PLAN_REG( g_tpDirectionInfo(nCnt).vItemCd, g_tpDirectionInfo(nCnt).nQty, nPlanDiv,
					daPlanStDate, vOrderNo, nOrderLineNo,g_tpDirectionInfo(nCnt).nLeadTime,vPlanNoBase);

		if boRet=false then
			return false;
		end if;

		--デバッグテスト
		dbms_output.put_line(g_tpDirectionInfo(nCnt).vItemCd || ' ' || g_tpDirectionInfo(nCnt).nLLC || ' ' ||
		g_tpDirectionInfo(nCnt).nLeadTime || ' ' || g_tpDirectionInfo(nCnt).nUse || ' ' || g_tpDirectionInfo(nCnt).nRcpIndex || ' ' ||
		g_tpDirectionInfo(nCnt).vRcpCd || ' ' || g_tpDirectionInfo(nCnt).nRcpVer || ' ' || g_tpDirectionInfo(nCnt).vLocation );
	end loop;

	return true;
EXCEPTION--エラー時
	when others then
	dbms_output.put_line('FUN_PD_PLAN_REG_ORDERITEM' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_REG_ORDERITEM', sqlcode || sqlerrm );
	return false;
end;

--■■■■■■■■■■■■■■■■■■■
--■　生産計画登録処理
--■　パラメータ:品目コード、必要数、生産計画区分、生産終了日、受注番号、受注行番号、受注計画番号(受注生産品目のベース計画番号)
--■　概要:処方ヘッダ取得
--■■■■■■■■■■■■■■■■■■■
FUNCTION FUN_PD_PLAN_REG
	(vItemCd VARCHAR2, nPlanQty NUMBER,nPlanDiv NUMBER, daPdSt DATE,
	vOrderNo VARCHAR2, nOrderLineNo NUMBER, nLdtime NUMBER, vPlanNoBase VARCHAR2)
	RETURN boolean IS
--宣言部
	--******カーソル定義******
	--処方ヘッダ取得（品目コード）
	/* 処方ヘッダテーブル「ステータス=１（試作用）＋2（一般使用用）、用途=3（production）、
	主要製品コード=品目コード、　有効開始日時＜（発注点を割った年月日−リードタイム〜発注点を割った年月日）＜有効終了日時
	、承認ステータス=3（承認済み）」 で抽出 */
	CURSOR curRcpHead(nRcpIdx NUMBER) IS
	SELECT RECIPE_ID, RECIPE_VERSION, RECIPE_PRIORITY, MIN_QTY, MAX_QTY, STD_QTY,  PROCESS_LOSS, UNIT_QTY
	FROM RECIPE_HEADER
	WHERE RECIPE_ID = nRcpIdx;

	--処方フォーミュラ
	CURSOR curRcpFm( nRcpId NUMBER ) IS
	--select LINE_TYPE, ITEM_CD, sum(QTY) as QTY, UNIT, STEP_NO, LINE_NO
	select LINE_TYPE, ITEM_CD, QTY,  STEP_NO, LINE_NO
	from RECIPE_FORMULA
	--where RECIPE_ID = nRcpId and LINE_TYPE <> PD_PLAN_PACKAGE.pc_ItemTypeMain
	where RECIPE_ID = nRcpId and (LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMain or LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMat or LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeSub);
	--group by LINE_TYPE, ITEM_CD, UNIT;

	--品目情報
	CURSOR curItemInfo( vItemCd VARCHAR2 ) IS
	select DEFAULT_LOCATION from ITEM where ITEM_CD = vItemCd;


	--製造品扱い属性取得（品目コード）
	CURSOR curPdatt(vItemCd VARCHAR2) IS
	SELECT PLAN_DIVISION,PRODUCTION_LEAD_TIME,ORDER_POINT
	FROM PRODUCT_ATTRIBUTE_QUEUE
	WHERE
	ITEM_CD = vItemCd;

	--******ローカル変数******
	rtRcpHead 		curRcpHead%rowtype;	--処方ヘッダ
	rtRcpHeadSub	curRcpHead%rowtype;	--処方ヘッダ子品目用（フォーミュラ）
	rtRcpFm			curRcpFm%rowtype;	--処方フォーミュラ
	rtItemInfo		curItemInfo%rowtype;	--品目情報
	rtPdatt			curPdatt%rowtype;
	nNeedQty		NUMBER;				--必要数
	nPdQty			NUMBER;				--生産数
	vPlanNo			VARCHAR2(255);		--計画番号
	--vPlanNoBase		VARCHAR2(255);		--計画番号ベース(受注生産品目のベース計画番号)
	nPlanLineNo		NUMBER;				--計画行番号(工程No*10000+行No)
	vLocationCd		VARCHAR2(255);		--基準保管場所
	nSelCnt			NUMBER;				--取得件数
	nPlanSeq		NUMBER;				--シーケンス番号（計画）
	nRegFlg			NUMBER;				--登録フラグ
	nIoDiv			NUMBER;				--受払区分
	daIoDate		date;				--受払予定日
	boRet			boolean;			--戻り値
	nRcpIndex		NUMBER;				--レシピインデックス
	vRcpCd			VARCHAR2(255);		--レシピコード
	nRcpVer			NUMBER;				--レシピバージョン
	nPdAllQty		NUMBER;				--全生産数
	nPdPlanQty		NUMBER;				--計画生産数
	boLast			boolean;			--生産数最終フラグ
	nInoutQty		NUMBER;				--受払数量
	nMakePlanCnt	NUMBER;				--生産計画作成カウンタ
	vIoOrderNo		VARCHAR2(255);
	nIoOrderLineNo	NUMBER;

begin
	--処方ヘッダ選択処理
	PD_PLAN_RECIPE_SELECT( vItemCd, nPlanQty, nRcpIndex, vRcpCd, nRcpVer, nPdQty, nPdAllQty );

	--処方ヘッダ取得
	open curRcpHead(nRcpIndex);
	fetch curRcpHead into rtRcpHead;

	if curRcpHead%notfound = true then
		dbms_output.put_line('FUN_PD_PLAN_REG レシピインデックス取得不可=' || vItemCd || ' 計画数量=' || nPlanQty);
		close curRcpHead;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogNg, 'FUN_PD_PLAN_REG',
		'処方ヘッダ無し 品目=' || vItemCd || ' 計画数量=' || nPlanQty);
		return (false);
	end if;
	close curRcpHead;

	--初期化
	vPlanNo:=null;
	--vPlanNoBase:=null;
	nMakePlanCnt := 0;

	boLast := false;
	if nRcpIndex!=0 then
	loop
		--*********親品目登録*********
		if nPdQty >= nPdAllQty then
			--全生産数が生産数を割ったとき
			nPdPlanQty := nPdAllQty;
			boLast :=true;
		else
			nPdPlanQty := nPdQty;
			--全生産数減算
			nPdAllQty := nPdAllQty - nPdQty;
		end if;

		--生産計画回数加算
		nMakePlanCnt:=nMakePlanCnt+1;

		--必要数量
		--nNeedQty := nPdPlanQty * ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );

		--計画番号算出＝“PL” + 製造開始予定日（YYMMDDHHMM） + シーケンス番号（0001〜9999）
		if nPlanDiv = PD_PLAN_PACKAGE.pc_PlanDivOrder then
			--受注生産品目
			--if vPlanNoBase is null then
			--	SELECT SEQ_PLAN.NEXTVAL INTO nPlanSeq FROM DUAL;
			--	vPlanNoBase := 'PL' || lpad(nPlanSeq,8,0);
			--end if;
			--vPlanNo := vPlanNoBase || '-' || lpad(nLlc,2,0) || lpad(nLlcCnt,2,0) || lpad(nMakePlanCnt,2,0);
				vPlanNo := vPlanNoBase || lpad(nMakePlanCnt,2,0);

		elsif nPlanDiv = PD_PLAN_PACKAGE.pc_PlanDivFill then
			--補充生産
			SELECT SEQ_PLAN.NEXTVAL INTO nPlanSeq FROM DUAL;
			--vPlanNo := 'PL' || to_char(daPdSt,'YYMMDDHHMM') || lpad(nPlanSeq,4,0);
			vPlanNo := 'PL' || lpad(nPlanSeq,8,0);
		end if;

		--品目マスタより「基準保管場所取得」
		/*select DEFAULT_LOCATION into vLocationCd from ITEM where ITEM_CD = vItemCd;
		--受払ソース登録処理
		boRet := FUN_PD_PLAN_INOUTREG( PD_PLAN_PACKAGE.pc_IoDivPlanIn , vPlanNo, NULL, vItemCd, vLocationCd,
						 NULL, nNeedQty, daPdSt, PD_PLAN_PACKAGE.pc_ItemTypeMain, vOrderNo, nOrderLineNo,NULL);

		if boRet=false then
			--登録エラー
			return (false);
		end if;*/

		--*********親+子品目登録*********
		--初期処理
		nRegFlg := 0;

		--処方フォーミュラ件数取得
		select count(*) into nSelCnt
		from RECIPE_FORMULA
		where RECIPE_ID = rtRcpHead.RECIPE_ID;

		--データ無し
		if nSelCnt=0 then
			/* 0件→エラーとし、ログテーブルへ
			「エラー内容＝（処方フォーミュラ無し：品目コード＝XXXX、レシピインデックス＝XXXX）、
			上記で取得した予定日、生産数」*/
			PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogNg, 'FUN_PD_PLAN_REG',
			'処方フォーミュラ無し 品目=' || vItemCd || ' 計画数量=' || nPlanQty);
			return (false);
		end if;

		--１件以上ヒットした場合「品目コード」ごとに数量を合計する。
		--払出予定数算出
		open curRcpFm(rtRcpHead.RECIPE_ID);

		loop
			--処方フォーミュラカーソルオープン「行No、品目コード、数量、単位」を取得
			fetch curRcpFm into rtRcpFm;
			if curRcpFm%notfound = true then
				close curRcpFm;
				exit;
			end if;

			/*
			--子品目処方ヘッダ選択処理
			PD_PLAN_RECIPE_SELECT(rtRcpFm.ITEM_CD , nPlanQty ,nRcpIndex , nPdQty ,nPdAllQty );

			--処方ヘッダ取得
			open curRcpHead(nRcpIndex);
			fetch curRcpHead into rtRcpHead;

			if curRcpHead%notfound = true then
				close curRcpHead;
				return (false);
			end if;
			close curRcpHead;*/

			--払出予定数＝「上記で取得した生産数」＊｛「数量／標準生産量」／１００｝（配合率％）
			--nPdPlanQty := nPdPlanQty * ( rtRcpFm.QTY / rtRcpHead.STD_QTY );
			nNeedQty := nPdPlanQty * ( rtRcpFm.QTY / rtRcpHead.STD_QTY );

			if  rtRcpFm.LINE_TYPE= PD_PLAN_PACKAGE.pc_ItemTypeMat then	--[品目タイプ＝-1:原材料]
				nIoDiv := PD_PLAN_PACKAGE.pc_IoDivPlanOut;				--受払区分＝7（計画払出）
				daIoDate := daPdSt - nLdtime;--製造開始予定日

				--nInoutQty := -1 * nNeedQty;
				nInoutQty := -1 * nNeedQty / ( rtRcpHead.PROCESS_LOSS / 100 );--収率計算を行う20090217

			elsif rtRcpFm.LINE_TYPE= PD_PLAN_PACKAGE.pc_ItemTypeSub or
				  rtRcpFm.LINE_TYPE= PD_PLAN_PACKAGE.pc_ItemTypeMain then--[品目タイプ＝0:主要製品,1:副生品]
				nIoDiv := PD_PLAN_PACKAGE.pc_IoDivPlanIn;	--受払区分＝6（計画受入）
				daIoDate := daPdSt;--製造終了予定日
				nInoutQty := nNeedQty;

			else
				dbms_output.put_line('FUN_PD_PLAN_REG 品目タイプ計画対象外=' || rtRcpFm.ITEM_CD ||
						' 品目タイプ1=' || rtRcpFm.LINE_TYPE || sqlcode || sqlerrm);
				PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogNg, 'FUN_PD_PLAN_REG',
				'品目タイプ計画対象外 品目=' || rtRcpFm.ITEM_CD || ' 品目タイプ=' || rtRcpFm.LINE_TYPE);
				return (false);
			end if;

			/* 製造品扱い属性カーソルオープン */
			open curPdatt(rtRcpFm.ITEM_CD);
			fetch curPdatt into rtPdatt;
			close curPdatt;

			--補充生産品については受け払いソースリファレンス番号にオーダー番号を入れない
			--if rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivFill then
			if vPlanNoBase is null or rtPdatt.PLAN_DIVISION = PD_PLAN_PACKAGE.pc_PlanDivFill then
				vIoOrderNo := null;
				nIoOrderLineNo := null;
			else
				vIoOrderNo := vOrderNo;
				nIoOrderLineNo := nOrderLineNo;

			end if;

			--add by tada 2008/12/25
			if daIoDate < trunc(sysdate) then
				daIoDate := trunc(sysdate);
			end if;

			--「品目タイプ＝-1:原材料 or 0:主要製品,1:副生品」の品目は以下の算出を行う。
			if rtRcpFm.LINE_TYPE=PD_PLAN_PACKAGE.pc_ItemTypeMat or
				rtRcpFm.LINE_TYPE=PD_PLAN_PACKAGE.pc_ItemTypeSub or
				rtRcpFm.LINE_TYPE= PD_PLAN_PACKAGE.pc_ItemTypeMain then
				--計画番号算出＝“PL” + 製造開始予定日（YYMMDDHHMM） + シーケンス番号（0001〜9999）
				--SELECT SEQ_PLAN.NEXTVAL INTO nPlanSeq FROM DUAL;
				--vPlanNo := 'PL' || to_char(daPdSt,'YYMMDDHHMM') || lpad(nPlanSeq,4,0);
				nPlanLineNo := rtRcpFm.STEP_NO * 10000 + rtRcpFm.LINE_NO;--ステップ番号、行番号
				nRegFlg := 1;
			end if;

		end loop;

		--終了判定
		if boLast=true then
			exit;
		end if;

	end loop;
	end if;

	return (true);

EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_REG' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_REG', sqlcode || sqlerrm );
	return (false);
end;




--■■■■■■■■■■■■■■■■■■■
--■　生産計画確定処理
--■　パラメータ:計画番号,作業者,作業開始日時,作業時間
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_DECISION
	(vPlanNo varchar2,vDirNo varchar2, vWorker varchar2, daStDate date,daEdDate date,
	nQty number, nWorkTime NUMBER, vOdrNo varchar2,nOdrLineNo NUMBER, vInputorCd varchar2 )
	RETURN NUMBER IS

	--******カーソル定義******
	--受払ソース取得（計画番号）
	CURSOR curInout( vPlanNo VARCHAR2 ) IS
	SELECT *
	FROM INOUT_SOURCE
	WHERE ODER_NO = vPlanNo and ITEM_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMain
	ORDER BY INOUT_DATE asc;
	--処方ヘッダ取得
	CURSOR curRcpHead( nRcpIndex NUMBER,vRcpCd VARCHAR2, nRcpVer NUMBER) IS
	SELECT *
	FROM RECIPE_HEADER
	WHERE RECIPE_ID=nRcpIndex and RECIPE_CD=vRcpCd and RECIPE_VERSION=nRcpVer ;

	--******ローカル変数******
	rtInout curInout%rowtype;		--受払ソース
	rtRcpHead curRcpHead%rowtype;	--処方ヘッダ
	boRet boolean;
	--vDirNo varchar2(20);
	nRcpIndex NUMBER;	--レシピインデックス
	vRcpCd VARCHAR2(20);
	nRcpVer NUMBER;
	nPdDirQty NUMBER;
	nPdQty NUMBER;		--ダミー
	nPdAllQty NUMBER;	--ダミー
	vLotNo VARCHAR2(20);
	nDirDiv NUMBER;
begin
	--指図番号
	if vDirNo is null then
		return -10;
	end if;

	--受払ソース取得
	open curInout(vPlanNo);

	fetch curInout into rtInout;

	if curInout%notfound = true then
		close curInout;
		return -2;
	end if;

	--*********処方ヘッダ取得*********
	--処方ヘッダ選択処理
	--PD_PLAN_RECIPE_SELECT(rtInout.ITEM_CD , rtInout.INOUT_QTY ,nRcpIndex ,vRcpCd, nRcpVer, nPdQty ,nPdAllQty );
	PD_PLAN_RECIPE_SELECT(rtInout.ITEM_CD , nQty ,nRcpIndex ,vRcpCd, nRcpVer, nPdQty ,nPdAllQty );

	--処方ヘッダ取得
	open curRcpHead(nRcpIndex, vRcpCd, nRcpVer);
	fetch curRcpHead into rtRcpHead;

	if curRcpHead%notfound = true then
		dbms_output.put_line('FUN_PD_PLAN_DECISION レシピインデックス取得不可=' || rtInout.ITEM_CD || ' 計画数量=' || rtInout.INOUT_QTY);

		close curRcpHead;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DECISION',
		'処方ヘッダ無し 品目=' || rtInout.ITEM_CD || ' 計画数量=' || rtInout.INOUT_QTY);
		return -3;
	end if;
	close curRcpHead;

	--ロット番号
	--vLotNo := FUN_PD_PLAN_GET_LOTNO(daStDate);
	vLotNo:=null;
	--if vLotNo=NULL then
		--ロット番号が取得できないときエラーログ出力
	--	return -4;
	--end if;

	--*********製造指図受払ソース登録処理*********
	--製造指図番号取得(外部から取得するように修正)
	/*SELECT SEQ_DIRECTION.NEXTVAL INTO vDirNo FROM DUAL;
	vDirNo := 'SS' || lpad(vDirNo,8,0);
	*/
	case rtRcpHead.RECIPE_USE
	when PD_PLAN_PACKAGE.pc_RcpUsePd then
		nDirDiv := PD_PLAN_PACKAGE.pc_DirDivBat;
	when PD_PLAN_PACKAGE.pc_RcpUseFill then
		nDirDiv := PD_PLAN_PACKAGE.pc_DirDivFill;
	else
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DECISION',
		'使用用途該当なし 品目=' || rtInout.ITEM_CD || ' 計画数量=' || rtInout.INOUT_QTY);
		return -5;
	end case;

	--PD_PLAN_PACKAGE.pc_DirDivPack	--詰替・貼替指図は無し

	--受払ソース登録処理(必要数量を指定)製造指図登録時に行うためコメント2008/04/16
--	boRet := FUN_PD_PLAN_INOUTREG( PD_PLAN_PACKAGE.pc_IoDivDirectIn, vDirNo,
--		nDirDiv, rtInout.ITEM_CD, rtInout.LOCATION_CD, NULL,
--		rtInout.INOUT_QTY, daStDate, rtInout.ITEM_TYPE,
--		rtInout.REFERENCE_NO , rtInout.REFERENCE_LINE_NO );

--	if boRet =false then
--		return -6;
--	end if;

	--生産量再計算
	--nPdDirQty := rtInout.INOUT_QTY / ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );
	nPdDirQty := nQty;

	--*********製造指図登録処理*********
	boRet := FUN_PD_PLAN_DIRECTION_SET
		(nDirDiv, vDirNo, rtRcpHead.PRODUCTION_LINE, rtInout.ITEM_CD,
		nRcpIndex,  nPdDirQty, daStDate, daEdDate,
		vLotNo, rtInout.LOCATION_CD, NULL,
		PD_PLAN_PACKAGE.pc_DirStsOk, 0,vWorker, nWorkTime, vInputorCd );

	if boRet=false then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DECISION',
		'製造指図登録処理失敗 品目=' || rtInout.ITEM_CD || ' 指図数量=' || nPdDirQty);
		return -7;
	end if;

	--*********受注受け払いソース更新*********
	if vOdrNo is not null then
		update INOUT_SOURCE
		set
			REFERENCE_NO=substr(vDirNo,1,10),
			--REFERENCE_LINE_NO
			UPDATE_DATE = sysdate,	--更新日
			UPDATOR_CD = vInputorCd	--更新者
		where ODER_NO=vOdrNo and ODER_LINE_NO=nOdrLineNo;

	end if;

	--受払ソース削除処理
	DELETE FROM INOUT_SOURCE
--	WHERE INOUT_DIVISION= PD_PLAN_PACKAGE.pc_IoDivPlanIn and ODER_NO = vPlanNo;
	WHERE ODER_NO = vPlanNo;

	--commit;
	return 0;
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_DECISION' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DECISION', sqlcode || sqlerrm );
	return -1;
end;

--■■■■■■■■■■■■■■■■■■■
--■　ロット番号取得
--■　パラメータ:
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_GET_LOTNO( daDate date )
	RETURN VARCHAR2 IS
	vLot VARCHAR2(20);

begin
	vLot := to_char(daDate,'YYYYMMDD');
	return vLot;
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_GET_LOTNO' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_GET_LOTNO', sqlcode || sqlerrm );
	return NULL;
end;


--■■■■■■■■■■■■■■■■■■■
--■　製造指図入力登録処理SOM版
--■　パラメータ:指図区分、指図番号、生産ラインコード、品目コード、
--■	基本処方コード、必要数量、製造開始日時、製造終了日時、ロット番号、ロケーションコード、摘要、
--■	指図ステータス
--■　備考：Web画面から呼び出し
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_CREATE_DIRECTION
	(nDirDiv NUMBER
	, vDirNo VARCHAR2
	, vPdLineCd VARCHAR2
	, vItemCd VARCHAR2
	,vRcpIdxCd VARCHAR2
	, nNeedQty NUMBER
	, daPdStart date
	, daPdEnd date
	,vLot VARCHAR2
	, vLocationCd VARCHAR2
	, vTeki VARCHAR2
	,nDirSts NUMBER
	, vInputorCd VARCHAR2
	) RETURN VARCHAR2 IS

--宣言部
	--******カーソル定義******
	--処方ヘッダ取得（レシピインデックス）
	CURSOR curRcpHead( nRcpIndex NUMBER) IS
	SELECT *
	FROM RECIPE_HEADER
	WHERE RECIPE_ID=nRcpIndex ;

	--*****変数宣言*****
	rtRcpHead 		curRcpHead%rowtype;	--処方ヘッダ
	nWork 			NUMBER;				--ワーク
	nWork2 			NUMBER;				--ワーク
	nPdAllQty		NUMBER;				--全生産数
	nPdDirQty		NUMBER;				--生産数（１回の指図分）
	nNeedDirQty		NUMBER;				--必要数（１回の指図分）
	nGetRcpIndex	NUMBER;				--レシピインデックス
	boRet			boolean;			--戻り値
	--nParDiv			NUMBER;
begin
	--レシピインデックス、レシピコード、レシピバージョン」編集取得
	if vRcpIdxCd = NULL then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_CREATE_DIRECTION',
		'処方文字列編集失敗1 品目=' || vItemCd || ' 処方=' || vRcpIdxCd );
		return '-1';
	end if;

	--処方ヘッダテーブル取得
	open curRcpHead( vRcpIdxCd );

	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogInf, 'FUN_PD_PLAN_CREATE_DIRECTION',
	'指図作成 番号=' || vDirNo  );--test

	fetch curRcpHead into rtRcpHead;
	if curRcpHead%notfound = true then
		--ログテーブルへ「エラー内容＝（該当処方ヘッダ無し：品目コード＝XXXX）、
		close curRcpHead;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_CREATE_DIRECTION',
		'処方ヘッダ無し 品目=' || vItemCd || ' 処方=' || vRcpIdxCd );
		return '-2';
	end if;
	close curRcpHead;


	--*****************生産数確定*****************
	--仮生産数 ＝ 必要数／（１−ロス率／１０００）
	--nWork := nNeedQty / ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );
	--nWork := nNeedQty / ( rtRcpHead.PROCESS_LOSS / 100 );
	nWork := nNeedQty;--収率計算は生産数確定時は行わない20090217

	--全生産数 ＝｛（仮生産数／単位生産量）小数点以下繰り上げ｝＊単位生産量
	IF NVL(rtRcpHead.UNIT_QTY,0) = 0 THEN
		nPdAllQty := nWork;
	ELSE
		nPdAllQty := ceil(nWork / rtRcpHead.UNIT_QTY) * rtRcpHead.UNIT_QTY;
	END IF;

	--必要数
	--nNeedDirQty := nPdDirQty * ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );

	--製造指図登録処理(生産量を指定)
	boret := FUN_PD_PLAN_DIRECTION_SET( nDirDiv , vDirno , vPdLineCd , vItemCd , vRcpIdxCd ,
		nNeedQty , daPdStart, daPdEnd , vLot , vLocationCd , vTeki , nDirSts ,
		nDirSts ,NULL,NULL, vInputorCd);

	if boRet=false then
		--エラー
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_CREATE_DIRECTION',
		'製造指図登録処理失敗 品目=' || vItemCd || ' 指図数=' || nPdDirQty );
		return '-4';
	end if;

	return vDirno;
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_CREATE_DIRECTION' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_CREATE_DIRECTION', sqlcode || sqlerrm );
	return '-6';
end;


--■■■■■■■■■■■■■■■■■■■
--■　製造指図入力登録処理（詰め替え張り替え）SOM版
--■　パラメータ:指図区分、指図番号、生産ラインコード、品目コード、
--■	基本処方コード、必要数量、製造開始日時、製造終了日時、ロット番号、ロケーションコード、摘要、
--■	指図ステータス
--■　備考：Web画面から呼び出し
--■■■■■■■■■■■■■■■■■■■
function FUN_CREATE_REPACK_DIRECTION
	(nDirDiv NUMBER
	, vDirNo VARCHAR2
	, vPdLineCd VARCHAR2
	, vItemCd VARCHAR2
	,vRcpIdxCd VARCHAR2
	, nNeedQty NUMBER
	, daPdStart date
	, daPdEnd date
	,vLot VARCHAR2
	, vLocationCd VARCHAR2
	, vTeki VARCHAR2
	,nDirSts NUMBER
	, vInputorCd VARCHAR2
	) RETURN VARCHAR2 IS

--宣言部
	--******カーソル定義******

	--*****変数宣言*****
	boRet			boolean;			--戻り値
begin


	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogInf, 'FUN_PD_PLAN_CREATE_DIRECTION',
	'指図作成 番号=' || vDirNo );--test

	--*********製造指図受払ソース登録処理*********
	boRet := FUN_DIRECTION_REPACK_SET( nDirDiv , vDirNO 
		, vPdLineCd , vItemCd , vRcpIdxCd
		,nNeedQty , daPdStart, daPdEnd , vLot , vLocationCd , vTeki , nDirSts ,
		nDirSts ,NULL,NULL, vInputorCd);

	return vDirNo;
EXCEPTION
	when others then
	dbms_output.put_line('FUN_CREATE_REPACK_DIRECTION' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_CREATE_REPACK_DIRECTION', sqlcode || sqlerrm );
	return '-6';
end;



--■■■■■■■■■■■■■■■■■■■
--■　製造指図登録処理(SOM版)
--■　パラメータ:指図区分、指図番号、親指図区分、親指図番号、受注番号、受注行番号、生産ラインコード、
--■	品目コード、基本処方コード、生産数量、製造開始日時、製造終了日時、ロット番号、ロケーションコード、
--■	摘要、指図ステータス、工程ステータス、入力者
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_DIRECTION_SET
	(nDirDiv NUMBER
	, vDirNo VARCHAR2
	, vPdLine VARCHAR2
	, vItemCd VARCHAR2
	,nGetRcpIndex NUMBER
	, nPdQty NUMBER
	, daPdStart date
	, daPdEnd date
	,vLot VARCHAR2
	, vLocationCd VARCHAR2
	, vTeki VARCHAR2
	,nDirSts NUMBER
	,nProcSts NUMBER
	,vWorker varchar2
	, nWorkTime NUMBER
	, vInputorCd VARCHAR2
	) RETURN BOOLEAN IS

	--******カーソル定義******
	--処方ヘッダ取得（レシピインデックス）
	CURSOR curRcpHead( nRcpIndex NUMBER) IS
	SELECT *
	FROM RECIPE_HEADER
	WHERE RECIPE_ID=nRcpIndex ;

	--処方プロシージャ取得（レシピインデックス）
	CURSOR curRcpPr( nRcpId NUMBER ) IS
	select *
	from RECIPE_PROCEDURE
	where RECIPE_ID = nRcpId
	order by STEP_NO asc;

	--処方フォーミュラ（レシピインデックス,ステップNO）
	CURSOR curRcpFm( nRcpId NUMBER, nStepNo NUMBER ) IS
	select *
	from RECIPE_FORMULA
	where RECIPE_ID = nRcpId and STEP_NO = nStepNo 
	order by LINE_NO asc;


	CURSOR curGetRcpFmQty( nRcpId NUMBER, nStepNo NUMBER ) IS
	select LINE_TYPE, nvl(sum(QTY),0) as SUMQTY
	from RECIPE_FORMULA
	where RECIPE_ID = nRcpId and STEP_NO = nStepNo
	group by LINE_TYPE;


	--処方検査（レシピインデックス,ステップNO）
	CURSOR curRcpIns( nRcpId NUMBER, nStepNo NUMBER ) IS
	select *
	from RECIPE_INSPECTION
	where RECIPE_ID = nRcpId and STEP_NO = nStepNo
	order by LINE_NO asc;

	--品目マスタ
	CURSOR curItemInfo( vItemCd VARCHAR2) IS
	select LOT_DIVISION, DEFAULT_LOCATION
	from ITEM
	where ITEM_CD = vItemCd;

	--ロット在庫
	CURSOR curLotInv( vItemCd VARCHAR2, vLocation VARCHAR2 ) IS
	select LOT_NO,INVENTORY_QTY
	from LOT_INVENTORY
	where ITEM_CD = vItemCd and LOCATION_CD = vLocation;

	--*****変数宣言*****
	rtRcpHead 		curRcpHead%rowtype;	--処方ヘッダ
	rtRcpPr			curRcpPr%rowtype;	--処方プロシージャ
	rtRcpFm			curRcpFm%rowtype;	--処方フォーミュラ
	rtGetRcpFmQty	curGetRcpFmQty%rowtype;--工程内原料合計
	rtRcpIns		curRcpIns%rowtype;	--処方検査
	rtItemInfo		curItemInfo%rowtype;--品目マスタ
	rtLotInv		curLotInv%rowtype;	--ロット在庫
	rtOdrPdItemCnt	g_curOdrPdItemCnt%rowtype;	--
	nWork 			NUMBER;				--ワーク
	nWork2 			NUMBER;				--ワーク
	nLotQty			NUMBER;				--ロット在庫数
	nSelCnt			NUMBER;				--抽出数
	nOutQty			NUMBER;				--払出数量
	vLotNoFm		varchar2(20);		--フォーミュラロット番号
	boRet			boolean;			--戻り値
	nIoDiv			NUMBER;				--受払区分
	nNeedDirQty		NUMBER;				--必要数（１回の指図分）
	nInoutQty		NUMBER;				--受払数量
	daIoDate		date;				--受払日時
	vInoutOrder		varchar2(20);		--受払ソースオーダ番号
	vInoutOrderLine	varchar2(20);		--受払ソースオーダ行番号
	nFmQty			NUMBER;				--フォーミュラ数量
	iStepNo			NUMBER;				--STEP_NO(工程2桁＋工程分割連番2桁)
	iLineNo			NUMBER;				--LINE_NO(製品フラグ1桁＋ライン番号2桁＋ロット連番1桁)
	iStepNoCnt		NUMBER;				--工程分割連番
	iStepQty		NUMBER;				--1工程数量加算減算
	iStepBaseQty	NUMBER;				--1工程数量
	iStepDivQty		NUMBER;				--工程分割数量
	iStepDivMax		NUMBER;				--工程分割数
	boLast			boolean;			--最終フラグ
	vPickNo 		varchar2(20);		--ピッキング番号
	nPickLineNo		NUMBER;				--ピッキング行番号
	nMainSubSumQty	NUMBER;
	nMatSumQty		NUMBER;
	nFmSumQty		NUMBER;

	--nCnt			NUMBER;
begin
	/* 処方ヘッダテーブル取得 */
	open curRcpHead( nGetRcpIndex );

	fetch curRcpHead into rtRcpHead;
	if curRcpHead%notfound = true then
		--ログテーブルへ「エラー内容＝（該当処方ヘッダ無し：品目コード＝XXXX）、
		close curRcpHead;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET',
		'処方ヘッダ無し 品目=' || vItemCd );

		return (false);
	end if;
	close curRcpHead;

	--受注生産品件数取得
	open g_curOdrPdItemCnt(vItemCd);
	fetch g_curOdrPdItemCnt into rtOdrPdItemCnt;
	close g_curOdrPdItemCnt;

	if rtOdrPdItemCnt.CNT>0 then
		--受注品は生産量チェック無し
		null;
	else
		--生産量チェック
		if rtRcpHead.MIN_QTY <= nPdQty and nPdQty <= rtRcpHead.MAX_QTY then
			null;
		else--エラー
			PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET',
			'生産量エラー 品目=' || vItemCd || ' 生産数=' || nPdQty);
			return (false);
		end if;
	end if;

	--必要数算出
	--nNeedDirQty := nPdQty * ( 1 - rtRcpHead.PROCESS_LOSS / 1000 );
	--nNeedDirQty := nPdQty * ( rtRcpHead.PROCESS_LOSS / 100 );
	nNeedDirQty := nPdQty;--収率計算戻しは必要数算出時は行わない20090217

	--**********製造指図ヘッダーテーブル登録**********
	boRet := FUN_PD_PLAN_DIRECTION_HDREG
				(nDirDiv, 	--指図区分
				vDirNo, 	--指図番号
				nDirSts,	--指図ステータス
				nGetRcpIndex, --レシピインデックス
				rtRcpHead.RECIPE_CD,
				rtRcpHead.RECIPE_VERSION,
				vPdLine , 	--生産ライン
				vItemCd, 	--品目コード
				nNeedDirQty,--生産量
				vLot, 	--ロット番号
				vLocationCd,--入庫ロケーション
				daPdStart, 	--製造開始日時
				daPdEnd, 	--製造開終了時
				vTeki,		--摘要
				vInputorCd,	--入力者
				rtRcpHead);

	if boRet = false then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET',
		'製造指図ヘッダー登録処理失敗 品目=' || vItemCd || ' 指図数=' || nNeedDirQty );
		return (false);
	end if;

	--**********製造指図プロシージャテーブル登録**********
	--処方プロシージャテーブル取得
	open curRcpPr(rtRcpHead.RECIPE_ID);

	loop--処方プロシージャLoop
		fetch curRcpPr into rtRcpPr;

		if curRcpPr%notfound = true then
			close curRcpPr;
			exit;
		end if;

		--製造指図プロシージャ分割処理

		--フォーミュラ数量取得


		--工程分割カウンタ
		iStepNoCnt := 1;

		--終了フラグ初期化
		boLast:=false;

		--工程分割回数、

			iStepNo := rtRcpPr.STEP_NO * 100 + iStepNoCnt;

			--製造指図プロシージャ登録
			boRet := FUN_PD_PLAN_DIRECTION_PRREG
				(nDirDiv, vDirNo, iStepNo, nProcSts,
				vWorker, daPdStart,daPdEnd, nWorkTime, vInputorCd, rtRcpPr);

			if boRet = false then
				close curRcpPr;
				PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET',
				'製造指図プロシージャ登録処理失敗 品目=' || vItemCd || ' 指図数=' || nNeedDirQty );
				return (false);
			end if;

			--**********製造指図フォーミュラテーブル登録**********
			/* 処方フォーミュラテーブル取得*/
			open curRcpFm( rtRcpHead.RECIPE_ID, rtRcpPr.STEP_NO );

			loop--処方フォーミュラLoop
				fetch curRcpFm into rtRcpFm;

				if curRcpFm%notfound = true then
					close curRcpFm;
					exit;
				end if;

				vLotNoFm := NULL;

				--品目タイプから払出区分を確定
				if  rtRcpFm.LINE_TYPE= PD_PLAN_PACKAGE.pc_ItemTypeMat then	--品目タイプ＝-1:原材料
					nIoDiv := PD_PLAN_PACKAGE.pc_IoDivDirectOut;			--受払区分＝2製造払出
					daIoDate := daPdStart;									--製造開始予定日
					--nFmQty := nPdQty * ( rtRcpFm.QTY / rtRcpHead.STD_QTY );									--フォーミュラ数量(原材料は生産量から計算される。)
					nFmQty := nPdQty  * rtRcpFm.QTY / rtRcpHead.STD_QTY ;	--フォーミュラ数量(原材料は生産量から計算される。[工程分割])
					nFmQty := nFmQty / ( rtRcpHead.PROCESS_LOSS / 100 );	--フォーミュラ数量(原材料は収率計算実施)20090217
					--nInoutQty := -1 * nFmQty;	--引き当て(工程分割されたフォーミュラ数量を受け払いソース数量とする)

					iLineNo := rtRcpFm.LINE_NO * 10 + 1;

				else 
					nIoDiv := PD_PLAN_PACKAGE.pc_IoDivDirectIn;	--受払区分＝1製造受入
					daIoDate := daPdEnd;	--製造終了予定日
					--nFmQty := nNeedDirQty * ( rtRcpFm.QTY / rtRcpHead.STD_QTY );									--フォーミュラ数量(製品は必要量から計算される)
					nFmQty := nNeedDirQty  * rtRcpFm.QTY / rtRcpHead.STD_QTY ;	--フォーミュラ数量(製品は必要量から計算される[工程分割])

					--nInoutQty := nFmQty;		--引き当て(工程分割されたフォーミュラ数量を受け払いソース数量とする)
					iLineNo := rtRcpFm.LINE_NO;
				end if;

				--製造指図フォーミュラ登録呼び出し
				boRet := FUN_PD_PLAN_DIRECTION_FMREG
					(nDirDiv, vDirNo, iStepNo, iLineNo, vLocationCd, NULL, nFmQty, vInputorCd, rtRcpFm); --test

				if boRet = false then
					PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET',
					'製造指図フォーミュラ登録処理失敗 品目=' || vItemCd || ' 指図数=' || nNeedDirQty );
					return (false);
				end if;

			end loop;	--処方フォーミュラLoopend

			--**********製造指図検査テーブル登録**********
			/* 処方検査テーブル取得*/
			open curRcpIns( rtRcpHead.RECIPE_ID, rtRcpPr.STEP_NO );

			loop--処方検査Loop
				fetch curRcpIns into rtRcpIns;

				if curRcpIns%notfound = true then
					close curRcpIns;
					exit;
				end if;

				--製造指図検査登録呼び出し
				boRet := FUN_PD_PLAN_DIRECTION_INSREG(nDirDiv, vDirNo, iStepNo, rtRcpIns.LINE_NO, vInputorCd, rtRcpIns);

				if boRet = false then
					PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET',
					'製造指図検査登録失敗 製造オーダー=' || vDirNo );
					return (false);
				end if;
			end loop;--処方検査Loopend

			--終了判定
			--if boLast=true or iStepQty <= 0.0001*iStepDivMax or iStepDivMax=iStepNoCnt then
			if boLast=true then
				exit;
			end if;

			iStepNoCnt := iStepNoCnt + 1;

	end loop;--処方プロシージャLoopend


	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_DIRECTION_SET' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_SET', sqlcode || sqlerrm );
	return (false);
end;


--■■■■■■■■■■■■■■■■■■■
--■　製造指図登録処理(SOM版)
--■　パラメータ:指図区分、指図番号、親指図区分、親指図番号、受注番号、受注行番号、生産ラインコード、
--■	品目コード、基本処方コード、生産数量、製造開始日時、製造終了日時、ロット番号、ロケーションコード、
--■	摘要、指図ステータス、工程ステータス、入力者
--■■■■■■■■■■■■■■■■■■■
function FUN_DIRECTION_REPACK_SET
	(nDirDiv NUMBER
	, vDirNo VARCHAR2
	, vPdLine VARCHAR2
	, vItemCd VARCHAR2
	,nGetRcpIndex VARCHAR2
	, nPdQty NUMBER
	, daPdStart date
	, daPdEnd date
	,vLot VARCHAR2
	, vLocationCd VARCHAR2
	, vTeki VARCHAR2
	,nDirSts NUMBER
	,nProcSts NUMBER
	,vWorker varchar2
	, nWorkTime NUMBER
	, vInputorCd VARCHAR2
	) RETURN BOOLEAN IS

	--******カーソル定義******
	--処方ヘッダ取得（レシピインデックス）
	CURSOR curRcpHead( nRcpIndex NUMBER) IS
	SELECT *
	FROM RECIPE_HEADER
	WHERE RECIPE_ID=nRcpIndex  ;

	--処方プロシージャ取得（レシピインデックス）
	CURSOR curRcpPr( nRcpId NUMBER ) IS
	select *
	from RECIPE_PROCEDURE
	where RECIPE_ID = nRcpId
	order by STEP_NO asc;

	--処方フォーミュラ（レシピインデックス,ステップNO）
	CURSOR curRcpFm( nRcpId NUMBER, nStepNo NUMBER ) IS
	select *
	from RECIPE_FORMULA
	where RECIPE_ID = nRcpId and STEP_NO = nStepNo and (LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMain or LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMat or LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeSub)
	order by LINE_NO asc;



	--処方検査（レシピインデックス,ステップNO）
	CURSOR curRcpIns( nRcpId NUMBER, nStepNo NUMBER ) IS
	select *
	from RECIPE_INSPECTION
	where RECIPE_ID = nRcpId and STEP_NO = nStepNo
	order by LINE_NO asc;


	--*****変数宣言*****
	rtRcpHead 		curRcpHead%rowtype;	--処方ヘッダ
	rtRcpPr			curRcpPr%rowtype;	--処方プロシージャ
	rtRcpFm			curRcpFm%rowtype;	--処方フォーミュラ
	rtRcpIns		curRcpIns%rowtype;	--処方検査
	nLotQty			NUMBER;				--ロット在庫数
	boRet			boolean;			--戻り値
	nIoDiv			NUMBER;				--受払区分
	nNeedDirQty		NUMBER;				--必要数（１回の指図分）
	--nInoutQty		NUMBER;				--受払数量
	--daIoDate		date;				--受払日時
	vInoutOrder		varchar2(20);		--受払ソースオーダ番号
	vInoutOrderLine	varchar2(20);		--受払ソースオーダ行番号
	iStepNo			NUMBER;				--STEP_NO(工程2桁＋工程分割連番2桁)
	iLineNo			NUMBER;				--LINE_NO(製品フラグ1桁＋ライン番号2桁＋ロット連番1桁)

	boPrExistFlg	boolean;			--処方プロシージャ存在フラグ
	--nCnt			NUMBER;
begin
	/* 処方ヘッダテーブル取得 */
	open curRcpHead( nGetRcpIndex );

	fetch curRcpHead into rtRcpHead;
	if curRcpHead%notfound = true then
		--ログテーブルへ「エラー内容＝（該当処方ヘッダ無し：品目コード＝XXXX）、
		--発注点を割った年月日、発注点を割った未来在庫量」を登録
		close curRcpHead;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET',
		'処方ヘッダ無し 品目=' || vItemCd );

		return (false);
	end if;
	close curRcpHead;

	--必要数算出
	nNeedDirQty := nPdQty;-- * ( rtRcpHead.PROCESS_LOSS / 100 );

	--**********製造指図ヘッダーテーブル登録**********
	boRet := FUN_PD_PLAN_DIRECTION_HDREG
				(nDirDiv, 	--指図区分
				vDirNo, 	--指図番号
				nDirSts,	--指図ステータス
				nGetRcpIndex, --レシピインデックス
				rtRcpHead.RECIPE_CD,
				rtRcpHead.RECIPE_VERSION,
				vPdLine , 	--生産ライン
				vItemCd, 	--品目コード
				vLot, 	--ロット番号
				vLocationCd,--入庫ロケーション
				nNeedDirQty,--生産量
				daPdStart, 	--製造開始日時
				daPdEnd, 	--製造開終了時
				vTeki,		--摘要
				vInputorCd,	--入力者
				rtRcpHead);

	if boRet = false then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET',
		'製造指図ヘッダー登録処理失敗 品目=' || vItemCd || ' 指図数=' || nNeedDirQty );
		return (false);
	end if;

	--**********製造指図プロシージャテーブル登録**********
	--処方プロシージャテーブル取得
	open curRcpPr(rtRcpHead.RECIPE_ID);

	boPrExistFlg := false;

	loop--処方プロシージャLoop
		fetch curRcpPr into rtRcpPr;

		if curRcpPr%notfound = true then
			close curRcpPr;
			exit;
		end if;

		--処方プロシージャ存在
		boPrExistFlg := true;

		iStepNo := rtRcpPr.STEP_NO * 100 + 1;

		--製造指図プロシージャ登録
		boRet := FUN_PD_PLAN_DIRECTION_PRREG
			(nDirDiv, vDirNo, iStepNo, nProcSts,
			vWorker, daPdStart,daPdEnd, nWorkTime, vInputorCd, rtRcpPr);

		if boRet = false then
			close curRcpPr;
			PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET',
			'製造指図プロシージャ登録処理失敗 品目=' || vItemCd || ' 指図数=' || nNeedDirQty );
			return (false);
		end if;

		--**********製造指図検査テーブル登録**********
		/* 処方検査テーブル取得*/
		open curRcpIns( rtRcpHead.RECIPE_ID, rtRcpPr.STEP_NO );

		loop--処方検査Loop
			fetch curRcpIns into rtRcpIns;

			if curRcpIns%notfound = true then
				close curRcpIns;
				exit;
			end if;

			--製造指図検査登録呼び出し
			boRet := FUN_PD_PLAN_DIRECTION_INSREG(nDirDiv, vDirNo, iStepNo, rtRcpIns.LINE_NO, vInputorCd, rtRcpIns);

			if boRet = false then
				PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET',
				'製造指図検査登録失敗 製造オーダー=' || vDirNo );
				return (false);
			end if;
		end loop;--処方検査Loopend
	end loop;--処方プロシージャLoopend

	if boPrExistFlg = false then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET',
		'処方プロシージャ無し 製造オーダー=' || vDirNo );
		return (false);
	end if;

	--フォーミュラ登録

	vInoutOrder:=lpad(nDirDiv,2,0) || vDirNo;

	iLineNo := iStepNo*10000 + 1001;


	rtRcpFm.SEQ:=1;--SEQ--投入順
	rtRcpFm.ITEM_CD:=vItemCd;	--品目コード
	rtRcpFm.LINE_TYPE:=PD_PLAN_PACKAGE.pc_ItemTypeMain;	--品目タイプ
	rtRcpFm.COST:=0;--rtItemAttInfo.COST;		--原価
	rtRcpFm.COST_UNIT:=null;	--原価単位

	--製造指図フォーミュラ登録呼び出し
	boRet := FUN_PD_PLAN_DIRECTION_FMREG
		(nDirDiv, vDirNo, iStepNo, 1001, vLocationCd, vLot, nNeedDirQty, vInputorCd, rtRcpFm); --test

	if boRet = false then
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET',
		'受払ソース登録処理失敗 品目=' || vItemCd || ' 指図数=' || nNeedDirQty );

		return (false);
	end if;


	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_DIRECTION_REPACK_SET' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_DIRECTION_REPACK_SET', sqlcode || sqlerrm );
	return (false);
end;


--■■■■■■■■■■■■■■■■■■■
--■　受注生産品目取得
--■　パラメータ:
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_GET_ORDER_ITEM
	(vMainItemCd 	IN VARCHAR2,nNeedQty number)
	RETURN boolean IS --戻り値

	--処方ヘッダ取得
	CURSOR curRcpHead(nRcpIdx NUMBER) IS
	SELECT RECIPE_ID, RECIPE_VERSION, RECIPE_PRIORITY, MIN_QTY, MAX_QTY, STD_QTY, RECIPE_USE, PROCESS_LOSS, UNIT_QTY,PRODUCTION_LINE
	FROM RECIPE_HEADER
	WHERE RECIPE_ID = nRcpIdx ;

	--処方フォーミュラ＆製造品扱い属性、品目マスタ取得
	CURSOR curRcpFm( nRcpId NUMBER ,vItemCd VARCHAR2) IS
	select A3.ITEM_CD, sum(A3.QTY) as SUMQTY,A1.LLC, A2.PRODUCTION_LEAD_TIME, A1.DEFAULT_LOCATION
	from ITEM A1, PRODUCT_ATTRIBUTE_QUEUE A2, RECIPE_FORMULA A3
	where A3.RECIPE_ID = nRcpId and ( A3.LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMat or A3.LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMain or A3.LINE_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeSub) and A3.ITEM_CD <> vItemCd and
	A1.ITEM_CD = A2.ITEM_CD AND A1.ITEM_CD = A3.ITEM_CD AND A1.PRODUCT_DIVISION <> 0 AND A1.PHANTOM_DIVISION = 0 AND
	--A2.STATUS = 3 AND ( A2.PLAN_DIVISION = 2)
	( A2.PLAN_DIVISION = 2) -- and A2.VERSION = (select max(A4.VERSION) from PRODUCT_ATTRIBUTE_QUEUE A4 where A4.ITEM_CD = vItemCd)
	GROUP BY A3.ITEM_CD, A3.QTY, A1.LLC,A2.PRODUCTION_LEAD_TIME, A1.DEFAULT_LOCATION
	ORDER BY A1.LLC ASC;


	/*
	戻り値
	-1:
	-2:LLC設定品目ではない
	-3:処方ヘッダ取得不可
	*/

	--******ローカル変数******
	rtItemCnt 	g_curOdrPdItemCnt%rowtype;	--対象品目
	rtRcpHead	curRcpHead%rowtype;	--処方ヘッダ
	rtRcpFm		curRcpFm%rowtype;	--処方フォーミュラ
	nRcpIndex	number;				--レシピインデックス
	vRcpCd		VARCHAR2(255);		--レシピコード
	nRcpVer		NUMBER;				--レシピバージョン
	nPdQty		number;				--生産数ダミー
	nPdAllQty	number;				--総生産数ダミー
	nOrQty		number;
	boRet		boolean;
	nMainLlc	number;
	nSubLlc		number;
	nDirCnt		number;
	nCnt		number;
	nExistFlg	number;

BEGIN

	--対象品目確認
	open g_curOdrPdItemCnt(vMainItemCd);
	fetch g_curOdrPdItemCnt into rtItemCnt;

	if rtItemCnt.CNT<1 then

		dbms_output.put_line('FUN_PD_PLAN_GET_ORDER_ITEM 対象品目なし=' || vMainItemCd);
		--無ければ終了
		close g_curOdrPdItemCnt;
		--OUT_PARA:=-2;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogInf, 'FUN_PD_PLAN_GET_ORDER_ITEM',
		'対象品目無し 品目=' || vMainItemCd );
		return(true);
	end if;
	close g_curOdrPdItemCnt;


	--レシピインデックス取得
	PD_PLAN_PACKAGE.PD_PLAN_RECIPE_SELECT(vMainItemCd, nOrQty,
										nRcpIndex, vRcpCd, nRcpVer, nPdQty,nPdAllQty);

	--処方ヘッダ取得
	open curRcpHead(nRcpIndex);
	fetch curRcpHead into rtRcpHead;

	if curRcpHead%notfound = true then
		dbms_output.put_line('FUN_PD_PLAN_GET_ORDER_ITEM レシピインデックス取得不可=' || vMainItemCd || ' 発注数量=' || nOrQty);
		close curRcpHead;
		--OUT_PARA:=-4;
		PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_GET_ORDER_ITEM',
		'処方ヘッダ無し 品目=' || vMainItemCd || ' 数量=' || nOrQty);

		goto P_END;
	end if;
	close curRcpHead;

	--処方情報格納
	nDirCnt := g_tpDirectionInfo.last;

	if nDirCnt is null then
		nDirCnt := 0;
	end if;

	nExistFlg:=0;
	nCnt:=0;

	--製造指図配列添え字取得
	FOR nCnt IN 1..nDirCnt LOOP
		if g_tpDirectionInfo(nCnt).vItemCd = vMainItemCd and g_tpDirectionInfo(nDirCnt).nRcpIndex is null then
			--既に配列内に品目が存在
			g_tpDirectionInfo(nDirCnt).nUse := rtRcpHead.RECIPE_USE;--使用用途
			g_tpDirectionInfo(nDirCnt).nLoss := rtRcpHead.PROCESS_LOSS;
			g_tpDirectionInfo(nDirCnt).nUnitQty := rtRcpHead.UNIT_QTY;
			--if g_tpDirectionInfo(nDirCnt).vPdLine is null then
				g_tpDirectionInfo(nDirCnt).vPdLine := rtRcpHead.PRODUCTION_LINE;--生産ライン
			--end if;
			g_tpDirectionInfo(nDirCnt).nRcpIndex := nRcpIndex;	--レシピインデックス
			g_tpDirectionInfo(nDirCnt).vRcpCd := vRcpCd;		--レシピコード
			g_tpDirectionInfo(nDirCnt).nRcpVer := nRcpVer;		--レシピバージョン
			exit;
		end if;
	end loop;


	--フォーミュラカーソル
	open curRcpFm(nRcpIndex,vMainItemCd);
	loop
		fetch curRcpFm into rtRcpFm;
		if curRcpFm%notfound = true then
			close curRcpFm;
			exit;
		end if;

		nDirCnt := g_tpDirectionInfo.last;

		if nDirCnt is null then
			nDirCnt := 0;
		end if;

		nExistFlg:=0;
		nCnt:=0;
		--製造指図配列添え字取得
		FOR nCnt IN 1..nDirCnt LOOP
			if g_tpDirectionInfo(nCnt).vItemCd = rtRcpFm.ITEM_CD then
				--既に配列内に品目が存在
				nDirCnt:=nCnt;
				nExistFlg:=1;

			end if;
		end loop;

		--存在確認
		if nExistFlg <> 1 then
			--配列内に品目無し
			--配列拡張
			g_tpDirectionInfo.EXTEND(1);

			nDirCnt := g_tpDirectionInfo.last;

			--品目コード、LLC格納
			g_tpDirectionInfo(nDirCnt).vItemCd := rtRcpFm.ITEM_CD;
			g_tpDirectionInfo(nDirCnt).nLLC := rtRcpFm.LLC;
			g_tpDirectionInfo(nDirCnt).nQty:=0;
			g_tpDirectionInfo(nDirCnt).nLeadTime := rtRcpFm.PRODUCTION_LEAD_TIME;--リードタイム
			g_tpDirectionInfo(nDirCnt).vLocation := rtRcpFm.DEFAULT_LOCATION;--ロケーション

		end if;

		--製造指図品目加算
		--g_tpDirectionInfo(nDirCnt).nQty := g_tpDirectionInfo(nDirCnt).nQty + rtRcpFm.SUMQTY;
		g_tpDirectionInfo(nDirCnt).nQty := g_tpDirectionInfo(nDirCnt).nQty + (nNeedQty * (rtRcpFm.SUMQTY / rtRcpHead.STD_QTY));

		--再帰製造指図品目検索
		if FUN_PD_PLAN_GET_ORDER_ITEM(rtRcpFm.ITEM_CD,g_tpDirectionInfo(nDirCnt).nQty)=false then
			dbms_output.put_line('FUN_PD_PLAN_GET_ORDER_ITEM' || '再帰製造指図品目検索失敗');
			PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_GET_ORDER_ITEM',
			'再帰製造指図品目検索失敗 品目=' || rtRcpFm.ITEM_CD);
			return(false);
		end if;

	end loop;

	return(true);
<<P_END>>
	return(false);
EXCEPTION--エラー時
	when others then
	dbms_output.put_line('FUN_PD_PLAN_GET_ORDER_ITEM' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_GET_ORDER_ITEM', sqlcode || sqlerrm );
	return(false);
end;

--■■■■■■■■■■■■■■■■■■■
--■　製造指図ヘッダ登録
--■　パラメータ:
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_DIRECTION_HDREG
	(nDirDiv	NUMBER, 	--指図区分
	vDirNo		VARCHAR2, 	--指図番号
	nDirSts		NUMBER,		--指図ステータス
	nRcpIdx		NUMBER, 	--レシピインデックス
	vRcpCd		VARCHAR2, 	--レシピコード
	nRcpVer		NUMBER, 	--レシピバージョン
	vPdLine		varchar2 , 	--生産ライン
	vItemCd		VARCHAR2, 	--品目コード
	nPdQty		NUMBER, 	--生産量
	vLotNo		VARCHAR2, 	--ロット番号
	vLocationCd VARCHAR2,	--入庫ロケーション
	daPdStart	date, 		--製造開始日時
	daPdEnd		date, 		--製造開終了時
	vTeki		VARCHAR2,	--摘要
	vInputorCd	VARCHAR2,	--入力者
	rtRcpHead	g_curRcpHead%rowtype)
	RETURN boolean IS

	nSelCnt	NUMBER;
begin
	--製造指図ヘッダ件数取得
	select count(*) into nSelCnt
	from DIRECTION_HEADER
	where DIRECTION_DIVISION = nDirDiv and DIRECTION_NO = vDirNo ;

	if nSelCnt=0 then
		--製造指図ヘッダ新規登録
		insert into
		DIRECTION_HEADER
		values
			(nDirDiv,	--指図区分
			vDirNo,		--指図番号
			sysdate,	--指図日時
			null,           --ＡＳＰオーダー番号@
			nDirSts,	--指図ステータス
			nRcpIdx,	--レシピインデックス
			vRcpCd,		--レシピコード
			nRcpVer,	--レシピバージョン
			vPdLine,	--生産ライン
			null,           --調合タンク番号@
			null,           --ホールドタンク番号@
			null,           --予備溶解タンク番号@
			null,           --充填タンク番号@
			null,           --包装ライン@
			null,           --現工程@
			vItemCd,	--品目コード
			nPdQty,		--予定生産量
			NULL,		--実績生産量
			null,           --分納フラグ@
			null,           --次回予定数量@
			null,           --物流入庫実績@
			rtRcpHead.PROCESS_LOSS,--ロス率
			vLotNo,		--ロット番号
			daPdStart,	--開始予定日時
			daPdEnd,	--終了予定日時
			NULL,		--開始実績日時
			NULL,		--終了実績日時
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--指図書発行フラグ
			NULL,		--指図書発行日
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--検査判定フラグ日
			NULL,		--検査合格日
			vTeki,		--摘要
			rtRcpHead.DELETE_FLAG,--削除フラグ
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--検査承認者
			rtRcpHead.APP_DATE,--確定承認日
			NULL,		--検査承認日
			NULL,		--製造完了承認者
			NULL,		--製造完了承認日
			sysdate,	--登録日
			vInputorCd,	--登録者ID
			sysdate,	--更新日時
			vInputorCd	--更新者ID
			);
	else
		--製造指図ヘッダ更新
		update DIRECTION_HEADER
		set DIRECTION_DIVISION =nDirDiv,	--指図区分
			DIRECTION_NO =vDirNo,			--指図番号
			DIRECTION_DATE =sysdate,		--指図日時
			DIRECTION_STATUS =nDirSts,		--指図ステータス
			RECIPE_ID =nRcpIdx,				--レシピインデックス
			RECIPE_CD =vRcpCd,				--レシピコード
			RECIPE_VERSION =nRcpVer,		--レシピバージョン
			PRODUCTION_LINE = vPdLine,		--生産ライン
			ITEM_CD = vItemCd,				--主要製品コード
			PLANED_QTY =nPdQty,				--予定生産量
			RESULT_QTY =NULL,				--実績生産量
			PEOCESS_LOSS =rtRcpHead.PROCESS_LOSS,--ロス率
			LOT_NO = vLotNo,				--ロット番号
			PLANED_SDATE =daPdStart,		--開始予定日時
			PLANED_EDATE =daPdEnd,			--終了予定日時
			RESULT_SDATE =NULL,				--開始実績日時
			RESULT_EDATE =NULL,				--終了実績日時
			STAMP_FLAG =NULL,				--指図書発行フラグ
			STAMP_DATE =NULL,				--指図書発行日
			CERTIFICATION_DATE=NULL,		--検査合格日
			REMARK = vTeki,					--摘要
			DELETE_FLAG =rtRcpHead.DELETE_FLAG,--削除フラグ
			--APPROVALDATE =rtRcpHead.APPROVALDATE,--承認日
			--APPROVEDBY = rtRcpHead.APPROVEDBY,--承認者
			--INPUT_DATE = sysdate,--登録日
			--INPUTOR_CD = vInputorCd,--登録者ＩＤ
			UPDATE_DATE = sysdate,			--更新日時
			UPDATOR_CD = vInputorCd			--更新者ＩＤ
		where DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo ;
	end if;
	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_DIRECTION_HDREG' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_HDREG', sqlcode || sqlerrm );
	return (false);
end;

--■■■■■■■■■■■■■■■■■■■
--■　製造指図プロシージャ登録
--■　パラメータ:指図区分,指図番号,ステップNO(1〜）,工程ステータス
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_DIRECTION_PRREG
	(nDirDiv NUMBER, vDirNo VARCHAR2, nStepNo NUMBER, nProcSts NUMBER,
	vWorker varchar2, daStDate date,daEdDate date,nWorkTime NUMBER,vInputorCd VARCHAR2,
	rtRcpPr g_curRcpPr%rowtype)
	RETURN boolean IS
	--*****カーソル宣言*****
	--*****変数宣言*****
	nSelCnt NUMBER;
	vSetWorker varchar2(20);

begin
	if vWorker is NULL then
		vSetWorker:='';
	else
		vSetWorker:=vWorker;
	end if;
	--製造指図プロシージャ件数取得
	select count(*) into nSelCnt
	from DIRECTION_PROCEDURE
	where DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo and STEP_NO=nStepNo;

	if nSelCnt = 0 then
		--製造指図プロシージャ新規登録
		insert into
		DIRECTION_PROCEDURE
		values
			(nDirDiv,			--指図区分
			vDirNo,				--指図番号
			nStepNo,			--ステップNO
			rtRcpPr.SEQ,		--SEQ??
			nProcSts,			--工程ステータス
			rtRcpPr.OPERATION_CD,--工程コード
			rtRcpPr.CONDITION,	--条件
			rtRcpPr.REMARK,--コメント
			rtRcpPr.NOTES,	--注釈
			null,	--リードタイム
			daStDate,			--開始日時
			daEdDate, 			--rtRcpPr.END_DATE,	--終了日時
			NULL,				--開始実績日時
			NULL,				--終了実績日時
			null,--パラメータ１
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,--パラメータ１０
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			NULL,		--@
			sysdate,			--登録日
			vInputorCd,			--登録者コード
			sysdate,			--更新日
			vInputorCd );		--更新者コード
	else
		--製造指図プロシージャ更新
		update DIRECTION_PROCEDURE
		set DIRECTION_DIVISION =nDirDiv,		--指図区分
			DIRECTION_NO =vDirNo,				--指図番号
			STEP_NO =nStepNo,					--ステップNo.
			SEQ=rtRcpPr.SEQ,							--SEQ??
			OPERATION_CD =rtRcpPr.OPERATION_CD,	--工程コード
			CONDITION =rtRcpPr.CONDITION,		--条件
			REMARK =rtRcpPr.REMARK,	--コメント
			NOTES =rtRcpPr.NOTES,		--注釈
			START_DATE =daStDate,				--開始日時
			END_DATE =daEdDate,					--終了日時
			RESULT_SDATE=NULL,					--開始実績日時
			RESULT_EDATE=NULL,					--終了実績日時
		--	INPUT_DATE = sysdate,				--登録日
		--	INPUTOR_CD = vInputorCd,			--登録者コード
			UPDATE_DATE = sysdate,				--更新日
			UPDATOR_CD = vInputorCd 			--更新者コード>>>>綴り
		where DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo and STEP_NO=nStepNo;

	end if;
	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_DIRECTION_PRREG' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_PRREG', sqlcode || sqlerrm );
	return (false);
end;


--■■■■■■■■■■■■■■■■■■■
--■　製造指図フォーミュラ登録
--■　パラメータ:指図区分,指図番号,ステップNO.,行番,
--■　　　　　　 基準保管場所（品目マスタ）,ロット番号（品目マスタ）
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_DIRECTION_FMREG
	(nDirDiv NUMBER, vDirNo VARCHAR2, nStepNo NUMBER, nLineNo NUMBER,
	vLocationCd VARCHAR2,vLotNo VARCHAR2, nFmQty NUMBER, vInputorCd VARCHAR2,rtRcpFm g_curRcpFm%rowtype)
	RETURN boolean IS
	--******変数定義******
	nSelCnt NUMBER;
begin
	--「指図区分、指図番号、ステップNO.、行NO.(連番)」で検索し件数を取得
	select count(*) into nSelCnt
	from DIRECTION_FORMULA
	where DIRECTION_DIVISION = nDirDiv and DIRECTION_NO = vDirNo and
		STEP_NO=nStepNo and LINE_NO=nLineNo;

	--件数が0件の場合は新規登録とし、１件の場合は更新処理を行う。
	if nSelCnt = 0 then
		--製造指図フォーミュラ新規登録
		insert into
		DIRECTION_FORMULA
		values
			(nDirDiv,			--指図区分
			vDirNo,				--指図番号
			nStepNo,			--ステップNO.
			nLineNo,			--行NO.
			rtRcpFm.SEQ,--SEQ,			--投入順
			rtRcpFm.LINE_TYPE,	--品目タイプ
			rtRcpFm.ITEM_CD,	--品目コード
			null,                   --@
			null,                   --@
			null,                   --@
			nFmQty,				--数量
			null,                   --@
			NULL,				--実績数量
			null,                   --@
			null,                   --@
			null,                   --@
			null,                   --@
			rtRcpFm.COST,		--原価
			rtRcpFm.REMARK,--条件
			rtRcpFm.NOTES,--コメント
			vLocationCd,		--ロケーション
			null,                   --@
			null,                   --@
			vLotNo,				--ロット番号
			null,                   --@
			null,                   --@
			null,                   --@
			null,                   --@
			sysdate,			--登録日
			vInputorCd,			--登録者コード
			sysdate,			--更新日
			vInputorCd );		--更新者コード
	else
		--製造指図フォーミュラ更新
		update DIRECTION_FORMULA
		set DIRECTION_DIVISION =nDirDiv,--指図区分
			DIRECTION_NO =vDirNo,		--指図番号
			STEP_NO =nStepNo,			--ステップNO.
			LINE_NO =nLineNo,			--行NO.
			SEQ =rtRcpFm.SEQ,			--投入順
			LINE_TYPE =rtRcpFm.LINE_TYPE,--品目タイプ
			ITEM_CD =rtRcpFm.ITEM_CD,	--品目コード
			QTY =nFmQty,				--数量
			RESULT_QTY=NULL,			--実績数量
			COST =rtRcpFm.COST,			--原価
			STEP_CONDITION =rtRcpFm.NOTES,--条件
			REMARK =rtRcpFm.REMARK,--コメント
			LOCATION_CD =vLocationCd,	--ロケーション
			LOT_NO =vLotNo,				--ロット番号
	--		INPUT_DATE = sysdate,		--登録日
	--		INPUTOR_CD =vInputorCd,		--更新者
			UPDATE_DATE = sysdate,		--更新日
			UPDATOR_CD=vInputorCd		--更新者コード
		where  DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo and STEP_NO =nStepNo and LINE_NO =nLineNo;

	end if;
	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_DIRECTION_FMREG' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_FMREG', sqlcode || sqlerrm );
	return (false);
end;

--■■■■■■■■■■■■■■■■■■■
--■　製造指図検査登録
--■　パラメータ:指図区分,指図番号,ステップNO.,行番,
--■　　　　　　 処方検査
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_DIRECTION_INSREG
	(nDirDiv NUMBER, vDirNo VARCHAR2, nStepNo NUMBER, nLineNo NUMBER, vInputorCd VARCHAR2,
	rtRcpIns g_curRcpIns%rowtype)
	RETURN boolean IS
	--******変数定義******
	nSelCnt NUMBER;
begin
	--「指図区分、指図番号、ステップNO.、行NO.(連番)」で検索し件数を取得
	select count(*) into nSelCnt
	from DIRECTION_INSPECTION
	where DIRECTION_DIVISION = nDirDiv and DIRECTION_NO = vDirNo and
		STEP_NO=nStepNo and LINE_NO=nLineNo;

	--件数が0件の場合は新規登録とし、１件の場合は更新処理を行う。
	if nSelCnt = 0 then
		--製造指図検査新規登録
		insert into
		DIRECTION_INSPECTION
		values
			(nDirDiv,				--指図区分
			vDirNo,					--指図番号
			nStepNo,				--ステップNO.
			nLineNo,				--行NO.
			rtRcpIns.SEQ,			--検査順
			rtRcpIns.INSPECTION_CD ,--検査(規格値)コード
			rtRcpIns.DIVISION ,		--区分
			rtRcpIns.VALUE_TYPE ,	--値１タイプ|1:数値,2:文字列
			rtRcpIns.VALUE1 ,		--値１
			NULL,					--実績値１
			rtRcpIns.VALUE2 ,		--値２
			NULL,					--実績値２
			rtRcpIns.CONDITION ,	--条件
			rtRcpIns.NOTES ,	--コメント
			rtRcpIns.REMARK ,	--備考
			sysdate,				--登録日
			vInputorCd ,			--登録者
			sysdate,				--更新日
			vInputorCd);			--更新者
	else
		--製造指図検査更新
		update DIRECTION_INSPECTION
		set
			DIRECTION_DIVISION =nDirDiv,	--指図区分
			DIRECTION_NO =vDirNo,			--指図番号
			STEP_NO =nStepNo,				--ステップNO.
			LINE_NO =nLineNo,				--行NO.
			SEQ =rtRcpIns.SEQ,				--検査順
			INSPECTION_CD =rtRcpIns.INSPECTION_CD ,	--検査(規格値)コード
			DIVISION =rtRcpIns.DIVISION ,	--区分
			VALUE_TYPE =rtRcpIns.VALUE_TYPE ,	--値１タイプ|1:数値,2:文字列
			VALUE1 =rtRcpIns.VALUE1 ,		--値１
			RESULT_VALUE1=NULL,				--実績値１
			VALUE2 =rtRcpIns.VALUE2 ,		--値２
			RESULT_VALUE2=NULL,				--実績値２
			CONDITION =rtRcpIns.CONDITION ,	--条件
			NOTES =rtRcpIns.NOTES ,--コメント
			REMARK =rtRcpIns.REMARK ,	--備考
		--	INPUT_DATE=sysdate,				--登録日
		--	INPUTOR_CD =vInputorCd ,		--登録者
			UPDATE_DATE=sysdate,			--更新日
			UPDATOR_CD =vInputorCd			--更新者
		where  DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo and STEP_NO =nStepNo and LINE_NO =nLineNo;

	end if;
	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_DIRECTION_INSREG' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_DIRECTION_INSREG', sqlcode || sqlerrm );
	return (false);
end;


--■■■■■■■■■■■■■■■■■■■
--■　製造指図更新処理
--■　パラメータ:指図区分、指図番号、製造開始日時、製造終了日時、作業者、作業時間
--■　概要：製造指図プロシージャ内の作業者、作業開始日時、作業時間を更新します
--■■■■■■■■■■■■■■■■■■■
PROCEDURE PD_PLAN_DIRECTION_UPDATE
	(nDirDiv IN NUMBER, vDirNo IN varchar2,vStepLine IN varchar2, daPdStart IN date, daPdEnd IN date,
	vWorker IN varchar2, nTime IN NUMBER, vInputorCd IN VARCHAR2, nRet OUT number)
	IS
	vIoStr varchar2(20);
begin
	vIoStr :=lpad(nDirDiv,2,0) || vDirNo;
	--受払ソース更新(主要品)
	update INOUT_SOURCE
	set INOUT_DATE=daPdEnd,	--受払日時＝終了日時
		UPDATE_DATE=sysdate,
		UPDATOR_CD=vInputorCd
	where ODER_NO=vIoStr and ODER_LINE_NO = vStepLine and ITEM_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMain ;

	--受払ソース更新(原料)
	update INOUT_SOURCE
	set INOUT_DATE=daPdStart,	--受払日時＝開始日時
		UPDATE_DATE=sysdate,
		UPDATOR_CD=vInputorCd
	where ODER_NO=vIoStr and ITEM_TYPE = PD_PLAN_PACKAGE.pc_ItemTypeMat ;

	--製造指図プロシージャ更新
	update DIRECTION_PROCEDURE
	set
		UPDATE_DATE = sysdate,	--更新日
		UPDATOR_CD = vInputorCd	--更新者
	where DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo;

	--製造指図ヘッダ更新
	update DIRECTION_HEADER
	set
		PLANED_SDATE = daPdStart,	--開始日時
		PLANED_EDATE = daPdEnd,		--終了日時
		UPDATE_DATE = sysdate,		--更新日
		UPDATOR_CD = vInputorCd		--更新者
	where DIRECTION_DIVISION=nDirDiv and DIRECTION_NO=vDirNo;

	--commit;
	nRet := 0;
EXCEPTION
	when others then
	dbms_output.put_line('PD_PLAN_DIRECTION_UPDATE' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'PD_PLAN_DIRECTION_UPDATE', sqlcode || sqlerrm );
	nRet := -1;
end;


--■■■■■■■■■■■■■■■■■■■
--■　ピッキング登録
--■　パラメータ:
--■■■■■■■■■■■■■■■■■■■
function FUN_PD_PLAN_PICKING_REG
	(vPickNo varchar2,vPickLineNo NUMBER, nDirDiv NUMBER, vDirNo VARCHAR2, vItemCd VARCHAR2,nQty NUMBER,
	daPdStart date,vPdLine VARCHAR2,vLocation VARCHAR2, vStepGr VARCHAR2,vInputorCd VARCHAR2)
	RETURN boolean IS

begin
	return (true);
EXCEPTION
	when others then
	dbms_output.put_line('FUN_PD_PLAN_PICKING_REG' || sqlcode || sqlerrm);
	PRO_PLAN_LOG(g_daDate, PD_PLAN_PACKAGE.pc_LogErr, 'FUN_PD_PLAN_PICKING_REG', sqlcode || sqlerrm );
	return (false);
end;



END;
/
