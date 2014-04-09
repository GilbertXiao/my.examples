<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="phone-book">
			<table border="1" align="center" bgcolor="pink">
						<xsl:apply-templates/>
			</table>
	</xsl:template>

</xsl:stylesheet>
