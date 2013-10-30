<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

	<xsl:template match = "/">
		<html>
			<body>
				<h2>
				<xsl:for-each select="/phone-book/contact">
					<xsl:apply-templates select = "name"/>
					<xsl:text>--</xsl:text>
					<xsl:apply-templates select = "phone-number"/>
				</xsl:for-each>
				</h2>
			</body>
		</html>
	</xsl:template>	

	<xsl:template match="name">
		<font color = "red">
			<xsl:value-of select="."></xsl:value-of>
		</font>
	</xsl:template>

	<xsl:template match="name">
		<font color = "red">
			<xsl:value-of select="."></xsl:value-of>
		</font>
	</xsl:template>
	
	<xsl:template match="phone-number">
		<font color = "blue">
				<xsl:value-of select="."></xsl:value-of><br/>
		</font>		
	</xsl:template>
	
</xsl:stylesheet>