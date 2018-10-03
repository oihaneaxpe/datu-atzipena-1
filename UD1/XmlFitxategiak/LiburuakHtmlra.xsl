<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Liburuak</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
		 <th>ISBN</th>	
        <th>Izenburua</th>
        <th>Egilea</th>
      </tr>
      <xsl:for-each select="liburutegi/liburu">
			<tr>
			 <td><xsl:value-of select="@isbn"/></td>
          <td><xsl:value-of select="izenburua"/></td>
          <td><xsl:value-of select="egilea"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet> 