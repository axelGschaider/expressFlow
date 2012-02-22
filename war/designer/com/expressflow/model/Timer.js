Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Timer',{
    extend: 'com.expressflow.model.Element',
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/timer.png';
    	},
    	imageWidth: function(){
    		return 52; 
    	},
    	imageHeight: function(){
    		return 51;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.Timer');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		if(xml.childNodes.length > 0){
				for(var pc = 0; pc < xml.childNodes.length; pc++){
					switch(xml.childNodes[pc].nodeName){
					case "trigger":
	    				if(xml.childNodes[pc].firstChild)
	    					element.data.trigger = xml.childNodes[pc].firstChild.nodeValue;
						break;
					case "processId":
	    				if(xml.childNodes[pc].firstChild)
	    					element.data.processId = xml.childNodes[pc].firstChild.nodeValue;
						break;
					}
				}
			}
    		return element;
    	}
    },
    fields: [
             {name: 'trigger', type: 'String'},
             {name: 'processId', type: 'String'},
             {name: 'xml', type: 'String'},
             {name: 'execXml', type: 'String'}
             ],
    getXml: function(){
        var _xml = Ext.String.format('<timer trigger="{0}" processId="{1}" />', this.get('trigger'), this.get('processId'));
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
    	var _model = Ext.String.format('<timer><trigger>{0}</trigger><processId>{1}</processId><x>{2}</x><y>{3}</y></timer>', this.get('trigger'), this.get('processId'), x, y);
    	return _model;
    }
}); 