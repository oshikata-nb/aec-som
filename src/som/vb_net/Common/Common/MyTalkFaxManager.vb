Option Explicit On
Option Strict On

Imports log4net
Imports System.Configuration
Imports System.Reflection

''' <summary>
''' �܂��Ɓ[��FAX�p�����N���X
''' </summary>
''' <remarks></remarks>
Public Class MyTalkFaxManager

#Region "�v���p�e�B"

    ''' <summary>
    ''' �o�[�W����
    ''' V200�Œ�
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Version As String = "V200"

    ''' <summary>
    ''' �����t�@�C����
    ''' </summary>
    ''' <remarks></remarks>
    Public Property FileName As String = String.Empty

    ''' <summary>
    ''' FAX�ԍ�
    ''' </summary>
    ''' <remarks></remarks>
    Public Property FaxNo As String = String.Empty

    ''' <summary>
    ''' �w��
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Name As String = String.Empty

    ''' <summary>
    ''' �h��
    ''' </summary>
    ''' <remarks></remarks>
    Public Property HonorificTitle As String = String.Empty

    ''' <summary>
    ''' ��Ж�
    ''' </summary>
    ''' <remarks></remarks>
    Public Property CompanyName As String = String.Empty

    ''' <summary>
    ''' ����
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Belong As String = String.Empty

    ''' <summary>
    ''' ��E
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Position As String = String.Empty

    ''' <summary>
    ''' �d�b�ԍ�
    ''' </summary>
    ''' <remarks></remarks>
    Public Property PhoneNo As String = String.Empty

    ''' <summary>
    ''' F�R�[�h
    ''' </summary>
    ''' <remarks></remarks>
    Public Property FCode As String = String.Empty

    ''' <summary>
    ''' ���[���A�h���X
    ''' </summary>
    ''' <remarks></remarks>
    Public Property MailAddress As String = String.Empty

    ''' <summary>
    ''' �C���^�[�l�b�gFAX�t���O
    ''' 0:FAX���M�A1:�C���^�[�l�b�gFAX���M
    ''' </summary>
    ''' <remarks></remarks>
    Public Property InternetFaxFlag As String = "0"

    ''' <summary>
    ''' �C���^�[�l�b�gFAX���[���̌���
    ''' </summary>
    ''' <remarks></remarks>
    Public Property InternetFaxTitle As String = String.Empty

    ''' <summary>
    ''' �C���^�[�l�b�gFAX���[���̖{��
    ''' </summary>
    ''' <remarks></remarks>
    Public Property InternetFaxBody As String = String.Empty

    ''' <summary>
    ''' ���t��
    ''' </summary>
    ''' <remarks></remarks>
    Public Property TransmittalLetter As String = String.Empty

    ''' <summary>
    ''' ���t����
    ''' </summary>
    ''' <remarks></remarks>
    Public Property TransmittalMemo As String = String.Empty

    ''' <summary>
    ''' �ʐM�|�[�g
    ''' </summary>
    ''' <remarks></remarks>
    Public Property ComPort As String = String.Empty

    ''' <summary>
    ''' �C�Ӎ���
    ''' </summary>
    ''' <remarks></remarks>
    Public Property Remarks As String = String.Empty

#End Region

#Region "����"
    ''' <summary>
    ''' FAX���M�t�@�C���̕ۑ���
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
    ''' FAX�pCSV�̕ۑ���
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
    ''' CSV�o��
    ''' </summary>
    ''' <param name="csvPath">CSV�o�͐�PATH</param>
    ''' <remarks></remarks>
    Public Sub OutputCsv(ByVal csvPath As String)

        'CSV�t�@�C���ɏ������ނƂ��Ɏg��Encoding 
        Dim enc As System.Text.Encoding = System.Text.Encoding.GetEncoding("Shift_JIS")

        '�������ރt�@�C�����J��
        Using sr As New System.IO.StreamWriter(csvPath, False, enc)

            '�t�B�[���h����������
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
            sr.Write(createField(Remarks, False))     '�J���}�Ȃ�

        End Using

    End Sub

    ''' <summary>
    ''' "�ň͂ށB�J���}��t����
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
