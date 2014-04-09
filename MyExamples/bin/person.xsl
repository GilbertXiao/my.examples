<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

	<xsl:template match="/">
		<xsl:element name="delegates">
				<xsl:for-each select="/Delegates/Name">
					<xsl:element name="person">
							<xsl:attribute name="age">
								<xsl:value-of select="Age"/>
							</xsl:attribute>		
							<xsl:value-of select="FirstName"/>
							<xsl:text> </xsl:text>
							<xsl:value-of select="LastName"/>
					</xsl:element>
				</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
</xsl:stylesheet>