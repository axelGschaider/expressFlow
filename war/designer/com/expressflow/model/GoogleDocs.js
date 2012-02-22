Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.GoogleDocs',{
    extend: 'com.expressflow.model.Element', 
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/GoogleDocs.PNG';
    	},
    	imageWidth: function(){
    		return 100; 
    	},
    	imageHeight: function(){
    		return 60;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.GoogleDocs');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    			case "link":
					element.data.link = xml.childNodes[i].firstChild.nodeValue;
					break;
    			case "action":
    				element.data.action = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "email":
    				element.data.email = xml.childNodes[i].firstChild.nodeValue;
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
             {name: 'link', type: 'String'},
             {name: 'action', type: 'String'},
             {name: 'email', type: 'String'}
             ],
    getXml: function(){
        var _xml = Ext.String.format('<gDoc link="{0}" action="{1}" email="{2}" />', this.get('link'), this.get('action'), this.get('email'));
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
    	var _model = Ext.String.format('<gDoc><link>{0}</link><action>{1}</action><email>{2}</email><x>{3}</x><y>{4}</y></gDoc>', this.get('link'), this.get('action'), this.get('email'), x, y);
    	return _model;
    }
});