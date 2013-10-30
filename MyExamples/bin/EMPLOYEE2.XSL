<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xml:space = "preserve">

	<xsl:template match="/">
		<html>
			<body>
				<CENTER>
					<TABLE BORDER="2">
						<TR BGCOLOR="cyan">
							<TH>Employee</TH>
							<TH>EmpID</TH>
							<TH>Designation</TH>
							<TH>Salary</TH>
							<TH>Department</TH>
						</TR>
						
					<xsl:for-each select="/EMPLOYEES/EMPLOYEE">
					<xsl:sort select = "EMP-SAL" order = "ascending"/> 
					<xsl:sort select = "EMP-NAME" order = "ascending"/> 
					
						<TR>
							<TD>
									<xsl:value-of select="EMP-NAME"/>
							</TD>

							<TD>
									<xsl:value-of select="@ID"/>
							</TD>
								
								<TD>
									<xsl:value-of select="EMP-DEG"/>
								</TD>
								
								<TD>
									<xsl:value-of select="EMP-SAL"/>
								</TD>
								
								<TD>
									<xsl:value-of select="EMP-DEPT"/>
								</TD>
							</TR>
						</xsl:for-each>
					</TABLE>
				</CENTER>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>