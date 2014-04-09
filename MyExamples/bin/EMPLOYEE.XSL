<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
	<xsl:template match="/">
		<HTML>
			<TITLE>Using XML stylesheets</TITLE>
			<BODY>
				<CENTER>
					<TABLE BORDER="2">
						<TR BGCOLOR="cyan">
							<TD>Employee</TD>
							<TD>EmpID</TD>
							<TD>Designation</TD>
							<TD>Salary</TD>
							<TD>Department</TD>
						</TR>
						<xsl:for-each select="EMPLOYEES/EMPLOYEE" 
													 order-by="+EMP-SAL;EMP-NAME">
							<TR>
								<TD>
									<xsl:value-of select="EMP-NAME"/>
								</TD>
								<TD>
							<!--using xsl:if element-->
								<xsl:if test="@ID[.='HROO1']">
									<IMG SRC="cert.gif" BORDER="1"></IMG>
								</xsl:if>
									
		<xsl:if test="@ID[.='SEOO1' or .='SEOO2' or .='SEO1O']">
			<img src="comp.gif" border="1"></img>
		</xsl:if>
		
	<xsl:if test="@ID[.!='SEO1O' and .!='HROO1' and .!='SEOO1' 
	  and .!='SEOO2' ]">
										<b>no</b>
									</xsl:if>

	<!-- using xsl:choose,xsl:when and xsl:otherwise elements-
									<xsl:choose>
				<xsl:when test="@ID[.='HROO1']">
				<IMG  SRC="cert.gif" BORDER="1"></IMG>
										</xsl:when>
				<xsl:when test="@ID[.='SEOO1']">
				<IMG  SRC="comp.gif" BORDER="1"></IMG>
										</xsl:when>
				<xsl:when test="@ID[.='SEOO2']">
				<IMG  SRC="comp.gif" BORDER="1"></IMG>
										</xsl:when>
				<xsl:when test="@ID[.='SEO1O']">
				<IMG  SRC="comp.gif" BORDER="1"></IMG>
										</xsl:when>
									<xsl:otherwise>
											<b>no</b>
									</xsl:otherwise>
									</xsl:choose>		-->							
								</TD>
								<TD>
					<xsl:value-of select="EMP-DEG"/>
								</TD>
								<TD>
					<xsl:value-of select="EMP-SAL"/>
								</TD>
				<TD>	<xsl:value-of select="EMP-DEPT"/> </TD>
							</TR>
						</xsl:for-each>
					</TABLE>
				</CENTER>
			</BODY>
		</HTML>
	</xsl:template>
</xsl:stylesheet>