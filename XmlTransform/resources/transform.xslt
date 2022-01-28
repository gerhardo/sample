<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" encoding="utf-8" />

	<!-- Parameter values may be overwritten by transformer call -->
	<xsl:param name="break" select="'&#xA;'" />
	<xsl:param name="quote" select="'&quot;'" />
	<xsl:param name="delim" select="';'" />

	<!-- Build output with headline -->
	<xsl:template match="/text_data">
		<xsl:text>lo_number,id,kdn,info,version,publish_date,text,lang_code</xsl:text>
		<xsl:value-of select="$break" />
		<xsl:for-each select="child::*/version">
			<xsl:apply-templates select="description" />
		</xsl:for-each>
	</xsl:template>

	<!-- handle text data -->
	<xsl:template match="description">
		<xsl:variable name="lo_number" select="../../lo_number" />
		<xsl:variable name="id" select="../../id" />
		<xsl:variable name="kdn" select="../../kdn" />
		<xsl:variable name="info" select="../../info" />
		<xsl:for-each select="text">
			<xsl:value-of
				select="concat($lo_number,$delim,$id,$delim,$kdn,$delim,$quote,$info,$quote,$delim,../../@label,$delim,../../@publish_date,$delim,$quote,text(),$quote,$delim,@lang_code,$break)">
			</xsl:value-of>
		</xsl:for-each>
	</xsl:template>


	<!-- throw away all remaining text like whitespaces -->
	<xsl:template match="text()" />
</xsl:stylesheet>
