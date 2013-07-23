<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

	<xsl:template match="contact">
			<h1>
				<xsl:number value="position()"/>
				<xsl:text> </xsl:text>
				<xsl:value-of select="name"/>
			</h1>
	</xsl:template>

</xsl:stylesheet>
