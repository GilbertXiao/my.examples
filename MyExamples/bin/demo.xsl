<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="phone-book">
			<table border="1" align="center">
						<xsl:apply-templates/>
			</table>
	</xsl:template>

	<xsl:template  match="person">
			<tr>
						<td>
							<xsl:number value="position()"/>
						</td>
						<td>

						<xsl:call-template name="mycolor">
							<xsl:with-param name="clr" select="red"/>
						</xsl:call-template>
							
							<xsl:value-of select="name"/>
						</td>
						<td>
							<xsl:value-of select="phone-number"/>
						</td>
						<td>
							<xsl:value-of select="@category"/>
						</td>
					</tr>
	</xsl:template>
	
	<xsl:template name="mycolor">
		<xsl:param name="clr" select="BLUE"/>
		My Name is 
	</xsl:template>
	
</xsl:stylesheet>