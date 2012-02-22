Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.SendSMS',{
    extend: 'com.expressflow.model.Element', 
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/sendSMS.png';
    	},
    	imageWidth: function(){
    		return 101; 
    	},
    	imageHeight: function(){
    		return 60;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.SendSMS');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    			case "to":
    				if(xml.childNodes[i].firstChild)
    					element.data.to = xml.childNodes[i].firstChild.nodeValue;
					break;
    			case "message":
    				if(xml.childNodes[i].firstChild)
    					element.data.message = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			default:
    				break;
    			}
    		}
    		return element;
    	}
    },
    fields:[
            {name: 'to', type: 'String'},
    ],
    getXml: function(){
        var _xml = Ext.String.format('<sendSMS to="{0}">{1}</sendSMS>', this.get('to'), this.get('message'));
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
    	var _model = Ext.String.format('<sendSMS><to>{0}</to><message>{1}</message><x>{2}</x><y>{3}</y></sendSMS>', this.get('to'), this.get('message'), x, y);
    	return _model;
    }
});