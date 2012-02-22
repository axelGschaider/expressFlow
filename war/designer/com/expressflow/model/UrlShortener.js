Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.UrlShortener',{
    extend: 'com.expressflow.model.Element', 
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/URLShortener.PNG';
    	},
    	imageWidth: function(){
    		return 104; 
    	},
    	imageHeight: function(){
    		return 61;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.UrlShortener');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    			case "longUrl":
					element.data.longUrl = xml.childNodes[i].firstChild.nodeValue;
					break;
    			case "shortUrl":
    				if(xml.childNodes[i].firstChild)
    					element.data.shortUrl = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			default:
    				break;
    			}
    		}
    		return element;
    	}
    },
    fields: [
             {name: 'id', type: 'int'},
             {name: 'longUrl', type: 'String'},
             {name: 'shortUrl', type: 'String'}
             ],
    getXml: function(){
        var _xml = Ext.String.format('<urlShortener><variable name="longUrl" type="string">{0}</variable><variable name="shortUrl" type="string">{1}</variable></urlShortener>', this.get('longUrl'), this.get('shortUrl'));
        return _xml;
    },
    getModel: function(){
    	var _image = this.get('image');
    	var x;
    	if(this.data.x == 0)
    		x = _image.attr('x');
    	else
    		x = this.data.x;
    	var y;
    	if(this.data.y == 0)
    		y = _image.attr('y');
    	else
    		y = this.data.y;
    	var _model = Ext.String.format('<urlShortener><variable><name>longUrl</name><type>String</type><value>{0}</value></variable><variable><name>shortUrl</name><type>String</type><value>{1}</value></variable><x>{2}</x><y>{3}</y></urlShortener>', this.get('longUrl'), this.get('shortUrl'), x, y);
    	return _model;
    }
});