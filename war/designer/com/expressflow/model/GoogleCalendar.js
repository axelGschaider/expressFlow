Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.GoogleCalendar',{
    extend: 'com.expressflow.model.Element', 
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/gcalendar.PNG';
    	},
    	imageWidth: function(){
    		return 100; 
    	},
    	imageHeight: function(){
    		return 59;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.GoogleCalendar');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    			case "title":
					element.data.title = xml.childNodes[i].firstChild.nodeValue;
					break;
    			case "content":
    				element.data.content = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "startTime":
    				element.data.startTime = xml.childNodes[i].firstChild.nodeValue;
    				break;
    			case "endTime":
    				element.data.endTime = xml.childNodes[i].firstChild.nodeValue;
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
             {name: 'title', type: 'String'},
             {name: 'content', type: 'String'},
             {name: 'startTime', type: 'String'},
             {name: 'endTime', type: 'String'}
             ],
    getXml: function(){
        var _xml = Ext.String.format('<gCalendar title="{0}" content="{1}" startTime="{2}" endTime="{3}" />', this.get('title'), this.get('content'), this.get('startTime'), this.get('endTime'));
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
    	var _model = Ext.String.format('<gCalendar><title>{0}</title><content>{1}</content><startTime>{2}</startTime><endTime>{3}</endTime><x>{4}</x><y>{5}</y></gCalendar>', this.get('title'), this.get('content'), this.get('startTime'), this.get('endTime'), x, y);
    	return _model;
    }
});