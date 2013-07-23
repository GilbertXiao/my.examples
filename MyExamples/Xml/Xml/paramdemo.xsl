<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
          	<xsl:apply-templates/>
	</xsl:template>
    
	<xsl:template match="phone-book">
        <html>
            <body>
            	<table align="center" border="1">
		            <xsl:apply-templates/>
	           </table> 
            </body>
        </html>
	</xsl:template>
	    
	<xsl:template match="contact">
		<tr>
			<td>
				<xsl:call-template name="displayNameWithSize">
					<xsl:with-param name="fontSize" select="1"/>
					<xsl:with-param name="name" select="./name"/>
				</xsl:call-template>
			</td>
			<td>
				<xsl:value-of select="phone-number"/>
			</td>
		</tr>	
	</xsl:template>
    
   <xsl:template name="displayNameWithSize">
       <xsl:param name="fontSize" select="5"/> 
       <xsl:param name="name"/>
       <font>
     		 <xsl:attribute name="size">
     		 	<xsl:value-of select="$fontSize"/>
     		 </xsl:attribute>
	       <xsl:value-of select="$name"/>
       </font>
	</xsl:template>

</xsl:stylesheet>