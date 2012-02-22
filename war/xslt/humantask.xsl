<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
 <html xmlns="http://www.w3.org/1999/xhtml">
 <head>
 <title>expressFlow - Engine Web Form</title>
 <link href="../css/HumanTask.css" rel="Stylesheet" type="text/css" />
 <style> 
	  body {margin: 1em;}
 </style> 
 </head>
 <body>
<form action="" method="post" class="horizontal"> 
    <fieldset> 
      <legend>expressFlow Human Task</legend> 
		<xsl:for-each select="//form">
			<div class="field"> 
				<label for="{//form/@name}"><xsl:value-of select="@name" /></label>

				<input type="text" name="{//form/@name}" value="{//form/@value}" />
            </div>
		</xsl:for-each>
		<div class="buttons"> 
        	<input type="submit" class="button" value="Execute" /> 
        	<input type="reset" class="button" value="Reset" /> 
      	</div> 
</fieldset>
</form>
<script type="text/javascript"><!--
google_ad_client = "ca-pub-4320468257595773";
/* expressFlow_Content */
google_ad_slot = "7279056496";
google_ad_width = 468;
google_ad_height = 60;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</body>
</html>
</xsl:template>
</xsl:stylesheet>