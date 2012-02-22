Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.SendEmail',{
    extend: 'com.expressflow.model.Element', 
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/sendEmail.PNG';
    	},
    	imageWidth: function(){
    		return 100; 
    	},
    	imageHeight: function(){
    		return 58;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.SendEmail');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    			case "from":
    				if(xml.childNodes[i].firstChild)
    					element.data.from = xml.childNodes[i].firstChild.nodeValue;
					break;
    			case "to":
    				if(xml.childNodes[i].firstChild)
    					element.data.to = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "respond_to":
    				if(xml.childNodes[i].firstChild)
    					element.data.respond_to = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "subject":
    				if(xml.childNodes[i].firstChild)
    					element.data.subject = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "body":
    				if(xml.childNodes[i].firstChild)
    					element.data.body = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			default:
    				break;
    			}
    		}
    		return element;
    	}
    },
    fields:[
            {name: 'from', type: 'String'},
            {name: 'to', type: 'String'},
            {name: 'respond_to', type: 'String'},
            {name: 'subject', type: 'String'},
            {name: 'body', type: 'String'},
            {name: 'nickname', type: 'String'}
    ],
    getXml: function(){
        var _xml = Ext.String.format('<sendEmail from="{0}" to="{1}" respond_to="{2}" subject="{3}" nickname="{4}">{5}</sendEmail>', this.get('from'), this.get('to'), this.get('respond_to'), this.get('subject'), this.get('nickname'), this.get('body'));
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
    	var _model = Ext.String.format('<sendEmail><from>{0}</from><to>{1}</to><respond_to>{2}</respond_to><subject>{3}</subject><nickname>{4}</nickname><body>{5}</body><x>{6}</x><y>{7}</y></sendEmail>', this.get('from'), this.get('to'), this.get('respond_to'), this.get('subject'), this.get('nickname'), this.get('body'), x, y);
    	return _model;
    }
});