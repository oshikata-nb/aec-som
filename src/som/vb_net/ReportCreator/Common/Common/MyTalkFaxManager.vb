Option Explicit On
Option Strict On

Imports log4net
Imports System.Configuration
Imports System.Reflection

''' <summary>
''' まいとーくFAX用処理クラス
''' </summary>
''' <remarks></remarks>
Public Class MyTalkFaxManager

#Region "プロパティ"

    ''' <summary>
    ''' バージョン
    ''' V200固定
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Version As String = "V200"

    ''' <summary>
    ''' 文書ファイル名
    ''' </summary>
    ''' <remarks></remarks>
    Public Property FileName As String = String.Empty

    ''' <summary>
    ''' FAX番号
    ''' </summary>
    ''' <remarks></remarks>
    Public Property FaxNo As String = String.Empty

    ''' <summary>
    ''' 指名
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Name As String = String.Empty

    ''' <summary>
    ''' 敬称
    ''' </summary>
    ''' <remarks></remarks>
    Public Property HonorificTitle As String = String.Empty

    ''' <summary>
    ''' 会社名
    ''' </summary>
    ''' <remarks></remarks>
    Public Property CompanyName As String = String.Empty

    ''' <summary>
    ''' 所属
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Belong As String = String.Empty

    ''' <summary>
    ''' 役職
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Position As String = String.Empty

    ''' <summary>
    ''' 電話番号
    ''' </summary>
    ''' <remarks></remarks>
    Public Property PhoneNo As String = String.Empty

    ''' <summary>
    ''' Fコード
    ''' </summary>
    ''' <remarks></remarks>
    Public Property FCode As String = String.Empty

    ''' <summary>
    ''' メールアドレス
    ''' </summary>
    ''' <remarks></remarks>
    Public Property MailAddress As String = String.Empty

    ''' <summary>
    ''' インターネットFAXフラグ
    ''' 0:FAX送信、1:インターネットFAX送信
    ''' </summary>
    ''' <remarks></remarks>
    Public Property InternetFaxFlag As String = "0"

    ''' <summary>
    ''' インターネットFAXメールの件名
    ''' </summary>
    ''' <remarks></remarks>
    Public Property InternetFaxTitle As String = String.Empty

    ''' <summary>
    ''' インターネットFAXメールの本文
    ''' </summary>
    ''' <remarks></remarks>
    Public Property InternetFaxBody As String = String.Empty

    ''' <summary>
    ''' 送付状
    ''' </summary>
    ''' <remarks></remarks>
    Public Property TransmittalLetter As String = String.Empty

    ''' <summary>
    ''' 送付メモ
    ''' </summary>
    ''' <remarks></remarks>
    Public Property TransmittalMemo As String = String.Empty

    ''' <summary>
    ''' 通信ポート
    ''' </summary>
    ''' <remarks></remarks>
    Public Property ComPort As String = String.Empty

    ''' <summary>
    ''' 任意項目
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Remarks As String = String.Empty

#End Region

#Region "処理"
    ''' <summary>
    ''' FAX送信ファイルの保存先
    ''' </summary>
    ''' <param name="fileName"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function GetOutputFilePath(ByVal fileName As String) As String
        Dim tmp As String = IO.Path.Combine(ConfigurationManager.AppSettings("FaxFileDir"), IO.Path.GetFileName(fileName))
        Dim entryAsm As Assembly = Assembly.GetEntryAssembly()
        Return tmp.Replace("MyReportApp", entryAsm.GetName.Name)
    End Function

    ''' <summary>
    ''' FAX用CSVの保存先
    ''' </summary>
    ''' <param name="fileName"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function GetOutputCsvPath(ByVal fileName As String) As String
        Dim tmp As String = IO.Path.Combine(ConfigurationManager.AppSettings("FaxCsvDir"), IO.Path.GetFileName(fileName) + ".csv")
        Dim entryAsm As Assembly = Assembly.GetEntryAssembly()
        Return tmp.Replace("MyReportApp", entryAsm.GetName.Name)
    End Function

    ''' <summary>
    ''' CSV出力
    ''' </summary>
    ''' <param name="csvPath">CSV出力先PATH</param>
    ''' <remarks></remarks>
    Public Sub OutputCsv(ByVal csvPath As String)

        'CSVファイルに書き込むときに使うEncoding 
        Dim enc As System.Text.Encoding = System.Text.Encoding.GetEncoding("Shift_JIS")

        '書き込むファイルを開く
        Using sr As New System.IO.StreamWriter(csvPath, False, enc)

            'フィールドを書き込む
            sr.Write(createField(Version))
            sr.Write(createField(FileName))
            sr.Write(createField(FaxNo))
            sr.Write(createField(Name))
            sr.Write(createField(HonorificTitle))
            sr.Write(createField(CompanyName))
            sr.Write(createField(Belong))
            sr.Write(createField(Position))
            sr.Write(createField(PhoneNo))
            sr.Write(createField(FCode))
            sr.Write(createField(MailAddress))
            sr.Write(createField(InternetFaxFlag))
            sr.Write(createField(InternetFaxTitle))
            sr.Write(createField(InternetFaxBody))
            sr.Write(createField(TransmittalLetter))
            sr.Write(createField(TransmittalMemo))
            sr.Write(createField(ComPort))
            sr.Write(createField(Remarks, False))     'カンマなし

        End Using

    End Sub

    ''' <summary>
    ''' "で囲む。カンマを付ける
    ''' </summary>
    ''' <param name="text"></param>
    ''' <param name="needComma"></param>
    ''' <param name="needDblQuot"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function createField(ByVal text As String, Optional ByVal needComma As Boolean = True, Optional ByVal needDblQuot As Boolean = False) As String
        Dim field As String = String.Empty

        If needDblQuot Then
            field = """" + text + """"
        Else
            field = text
        End If

        If needComma Then
            field = field + ","
        End If

        Return field
    End Function
#End Region

End Class
