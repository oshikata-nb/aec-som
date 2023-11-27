''' <summary>
''' データベース処理基底クラス
''' </summary>
''' <remarks></remarks>
Public MustInherit Class AbstractDbManager
    Implements IDisposable

    ''' <summary>
    ''' コネクション
    ''' </summary>
    ''' <remarks></remarks>
    Protected _dbConnection As IDbConnection = Nothing
    ''' <summary>
    ''' コマンド
    ''' </summary>
    ''' <remarks></remarks>
    Protected _dbCommand As IDbCommand = Nothing
    ''' <summary>
    ''' アダプタ
    ''' </summary>
    ''' <remarks></remarks>
    Protected _dbDataAdapter As IDbDataAdapter = Nothing
    ''' <summary>
    ''' トランザクション
    ''' </summary>
    ''' <remarks></remarks>
    Protected _dbTransaction As IDbTransaction = Nothing

    Public LastSQL As String

    ''' <summary>
    ''' データベースに接続する
    ''' </summary>
    ''' <remarks></remarks>
    Protected MustOverride Sub Open()

    ''' <summary>
    ''' トランザクション開始
    ''' </summary>
    ''' <remarks></remarks>
    Public MustOverride Sub BeginTran()

    ''' <summary>
    ''' コミット
    ''' </summary>
    ''' <remarks></remarks>
    Public MustOverride Sub Commit()

    ''' <summary>
    ''' ロールバック
    ''' </summary>
    ''' <remarks></remarks>
    Public MustOverride Sub RollBack()

    ''' <summary>
    ''' 終了処理
    ''' </summary>
    ''' <remarks></remarks>
    Public MustOverride Sub Dispose() Implements IDisposable.Dispose

    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public MustOverride Function GetDataSet(ByVal wSql As String) As DataSet

    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <param name="tableName"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public MustOverride Function GetDataSet(ByVal wSql As String, ByVal tableName As String) As DataSet

    ''' <summary>
    ''' SQL実行（SELECT以外）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public MustOverride Function ExecuteSQL(ByVal wSql As String) As Integer

    ''' <summary>
    ''' 部分文字列取得SQL文生成
    ''' </summary>
    ''' <param name="str">部分文字列抽出対象</param>
    ''' <param name="startPos">部分文字列抽出開始位置</param>
    ''' <param name="length">部分文字列の長さ</param>
    ''' <returns>部分文字列取得SQL</returns>
    ''' <remarks></remarks>
    Public MustOverride Function MakeSQLSubString(ByVal str As String, ByVal startPos As Integer, ByVal length As Integer) As String

    ''' <summary>
    ''' NULL置換SQL文生成
    ''' </summary>
    ''' <param name="str">NULL判定文字列</param>
    ''' <param name="replaceStr">置換文字列</param>
    ''' <returns>NULL置換関数文字列(SQL)</returns>
    ''' <remarks></remarks>
    Public MustOverride Function MakeSQLConvertNULL(ByVal str As String, ByVal replaceStr As String) As String

    ''' <summary>
    ''' 文字列選択置換SQL文生成
    ''' </summary>
    ''' <param name="str">置換対象文字列リスト</param>
    ''' <param name="replaceStr">置換文字列リスト</param>
    ''' <param name="elseStr">例外文字列リスト</param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public MustOverride Function MakeSQLReplace(ByVal str As String, ByVal pattern As String(), ByVal replaceStr As String(), ByVal elseStr As String) As String

    ''' <summary>
    ''' 文字列日付関数取得SQL文生成
    ''' </summary>
    ''' <param name="dateTime">変換したい日付</param>
    ''' <returns>変換後日時文字列</returns>
    ''' <remarks></remarks>
    Public MustOverride Function ConvertSQLDateTime(ByVal dateTime As Date) As String

    ''' <summary>
    ''' Dual句SQL文取得
    ''' </summary>
    ''' <returns>Dual句</returns>
    ''' <remarks></remarks>
    Public MustOverride Function GetDualSQL() As String

End Class