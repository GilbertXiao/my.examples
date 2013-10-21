 <xsl:template match="/">
        <HTML>
            <HEAD>
                <TITLE>Fitness Center</TITLE>
            </HEAD>
            <BODY>
                <xsl:call-template name="displayNameWithFont">
                    <xsl:with-param name="fontFace" select="'Impact'"/>
                    <xsl:with-param name="name" 
                            select="/FitnessCenter/Member[1]/Name"/>
                </xsl:call-template>
                <BR/>
                ...
            </BODY>
        </HTML>
    </xsl:template>

    <xsl:template name="displayNameWithFont">
        <xsl:param name="fontFace" select="'Braggadocio'"/> <!-- default font -->
        <xsl:param name="name"/>
        <FONT face="{$fontFace}">
            <xsl:value-of select="$name"/>
        </FONT>
    </xsl:template>


   <xsl:template match="/">
        <HTML>
            <HEAD>
                <TITLE>Fitness Center</TITLE>
            </HEAD>
            <BODY>
                16 / 2 = 
                <xsl:variable name="result">
                    <xsl:call-template name="NumDiv2">
                         <xsl:with-param name="N" select="16"/>
                    </xsl:call-template>
                </xsl:variable>
                <xsl:value-of select="$result"/>
            </BODY>
        </HTML>
    </xsl:template>

    <xsl:template name="NumDiv2">
        <xsl:param name="N"/>
        <xsl:value-of select="$N div 2"/>
    </xsl:template>
