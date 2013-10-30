<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

	<xsl:template match="/">
		<html>
			<body>
				<table align="center" border="1">
			  	<xsl:for-each 
		  				select="phone-book/contact[@category = 'friend']">  
			  	
						<xsl:sort select="name" order="descending"/>							<tr>
							<td><h2>
								<xsl:number value="position()" format="1"/>
							</h2></td>
							<td>
								<h2><xsl:value-of select="name"/></h2>
							</td>
							<td>
								<h2><xsl:value-of select="phone-number"/></h2>
							</td>
							<td>
								<h2><xsl:value-of select="@category"/></h2>
							</td>
						</tr>
					</xsl:for-each>
				</table>			
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>