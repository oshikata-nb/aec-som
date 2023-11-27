/*
 * ガジェット設定削除用SQL
 * キー：担当コード
 *
 * entityName=GadgetConfig
 * packageName=dao.entity.gadgetconfig
 * methodName=deleteByTantoCd
 *
 */
delete from GADGET_CONFIG
where TANTO_CD = /*tantoCd*/'test000001' 
