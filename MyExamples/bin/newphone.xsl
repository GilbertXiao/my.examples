<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="phone-book">
		<html>
			<body>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="contact">
		<xsl:choose>
			<xsl:when test="@category='friend'">
				<xsl:call-template name="displayName">
					<xsl:with-param name="contactName" select="name"/>
					<xsl:with-param name="fontSize" select="4"/>
				</xsl:call-template>
			</xsl:when>			
			<xsl:otherwise>
				<xsl:call-template name="displayName">
					<xsl:with-param name="contactName" select="name"/>
				</xsl:call-template>
			</xsl:otherwise>			
		</xsl:choose>
		
		<br/>			
	</xsl:template>

	<xsl:template name="displayName">
		<xsl:param name="contactName"/>
		<xsl:param name="fontSize" select="2"/>
		
		<font>
			<xsl:attribute name="size">
				<xsl:value-of select="$fontSize"/>
			</xsl:attribute>
			<xsl:value-of select="$contactName"/>
		</font>		
	</xsl:template>

</xsl:stylesheet>