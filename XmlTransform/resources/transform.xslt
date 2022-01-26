<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" encoding="utf-8" />

	<xsl:param name="break" select="'&#xA;'" />
	<xsl:param name="quote" select="'&quot;'" />
	<xsl:param name="delim" select="','" />

	<xsl:template match="description">
		<xsl:for-each select="text">
			<xsl:value-of
				select="concat($delim,$quote,text(),$quote,$delim,$break)">
			</xsl:value-of>
		</xsl:for-each>
	</xsl:template>

	<!-- throw away all remaining text -->
	<xsl:template match="text()" />
</xsl:stylesheet>
