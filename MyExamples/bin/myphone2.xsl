<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	
	<xsl:template match="/"	>
		<html>
			<body>
				<table align="center" border="1">
					<xsl:for-each select="/phone-book/contact">	
					<tr>

						<xsl:choose>
							<xsl:when test="position() = 1">
								<xsl:attribute name="bgcolor">red</xsl:attribute>
							</xsl:when>

							<xsl:when test="position() = 2">
								<xsl:attribute name="bgcolor">green</xsl:attribute>
							</xsl:when>
							
							<xsl:otherwise>
								<xsl:attribute name="bgcolor">blue</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose> 				
		
						<td><h1>
										<xsl:number value="position()"/>
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
