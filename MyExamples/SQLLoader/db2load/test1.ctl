UNRECOVERABLE
LOAD DATA
INFILE 'INPUT_FILE'
APPEND
INTO TABLE JOHN.TABLE1
TRAILING NULLCOLS
(
BC_OFF_BCBANK POSITION(2:4) CHAR   ,
BC_OFF_SEGMENT_TYPE POSITION(5:12) CHAR "rtrim(:BC_OFF_SEGMENT_TYPE,' ')"    ,
BC_OFF_NUM POSITION(13:18) CHAR DEFAULTIF BC_OFF_NUM= ' ?????',
BC_OFF_SQ_EFF_DATE POSITION(19:26) CHAR DEFAULTIF BC_OFF_SQ_EFF_DATE=" ???????",
BC_OFF_SQ_ENT_DATE POSITION(27:40) CHAR DEFAULTIF BC_OFF_SQ_ENT_DATE = ' ?????????????',
BC_OFF_DELETE POSITION(41:41) CHAR,
BC_OFF_EFF_DATE POSITION(42:53) CHAR DEFAULTIF BC_OFF_EFF_DATE =' ???????????',
BC_OFF_INITIALS POSITION(54:56) CHAR "rtrim(:BC_OFF_INITIALS,' ')",
BC_OFF_NAME POSITION(57:76) CHAR "rtrim(:BC_OFF_NAME,' ')",
BC_OFF_C_L_SECTION POSITION(77:78) CHAR,
BC_OFF_PHONE_NR POSITION(79:90) CHAR DEFAULTIF BC_OFF_PHONE_NR=' ???????????',
BC_OFF_SC_LND_LMT POSITION(91:98) CHAR DEFAULTIF BC_OFF_SC_LND_LMT = ' ???????',
BC_OFF_UNSC_LND_LMT POSITION(99:106) CHAR DEFAULTIF BC_OFF_UNSC_LND_LMT = ' ???????',
BC_OFF_NEWCOL POSITION(107:107) CHAR NULLIF (BC_OFF_NEWCOL=BLANKS),
PRCS_DTE CONSTANT "PROCESSDATE",
PRCS_YR_MTH_NBR CONSTANT "PROCYRMTH"
)
 
