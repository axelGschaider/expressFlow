var riaStats_protocol = (("https:" == document.location.protocol) ? "https://" : "http://");

var riaStats_siteID = "d7c9e690792312c38d340af232cd768d1dbcc6e0";

if(riaStats_protocol == "http://" && document.cookie.indexOf('riastats_lock') == -1)

document.write(unescape("%3Cscript src='" + riaStats_protocol + "riastats.com/browserPluginStat.js' type='text/javascript'%3E%3C/script%3E"));