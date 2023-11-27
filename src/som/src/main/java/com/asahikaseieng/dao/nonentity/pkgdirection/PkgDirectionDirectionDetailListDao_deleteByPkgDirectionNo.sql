/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/

/**
 * 包装指図－製造指図明細－包装指図番号で一括削除SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionDirectionDetailList
 * packageName=pkgdirection
 * methodName=deleteByPkgDirectionNo
 *
 */
DELETE 
FROM
	DIRECTION_DETAIL
WHERE
	DIRECTION_DETAIL.PKG_DIRECTION_NO = /*pkgDirectionNo*/
