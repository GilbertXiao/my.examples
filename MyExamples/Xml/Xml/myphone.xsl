<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	
	<xsl:template match="/"	>
		<html>
			<body>
				<table align="center" border="1">
					<xsl:for-each select="/phone-book//contact">	
					<tr>
		
							<xsl:if test="position() mod 2 = 0">
								<xsl:attribute name="bgcolor">pink</xsl:attribute>
							</xsl:if>

							<xsl:if test="position() mod 2 != 0">
								<xsl:attribute name="bgcolor">yellow</xsl:attribute>
							</xsl:if>
		
						<td><h1>
										<xsl:number value="position()-1"/>
										<xsl:text>.</xsl:text>
									</h1>
						</td>
						
						<td><h1><xsl:value-of select="name"/></h1></td>							   <td><h1><xsl:value-of select="phone-number"/></h1></td>
  					   <td><h1><xsl:value-of select="@category"/></h1></td>						</tr>					
					</xsl:for-each>					
				</table>
			</body>
		</html>
	</xsl:template>
	
	
</xsl:stylesheet>
