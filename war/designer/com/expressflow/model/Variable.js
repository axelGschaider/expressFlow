Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Variable',{
    extend: 'com.expressflow.model.Element', 
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/variable.png';
    	},
    	imageWidth: function(){
    		return 102; 
    	},
    	imageHeight: function(){
    		return 60;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.Variable');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		for(var i=0; i < xml.childNodes.length; i++){
    			switch(xml.childNodes[i].nodeName){
    				case "type":
    					element.data.type = xml.childNodes[i].firstChild.nodeValue;
    					break;
    				case "value":
    					element.data.value = xml.childNodes[i].firstChild.nodeValue;
    				default:
    					console.log("[Variable] Unrecognized element");
    			}
    		}
    		return element;
    	}
    },
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'String'},
        {name: 'type', type: 'String'},
        {name: 'value', type: 'String'}
        ],
    getXml: function(){
        var _xml = Ext.String.format('<variable name="{0}" type="{1}">{2}</variable>', this.get('name'), this.get('type'), this.get('value'));
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
    	var _model = Ext.String.format('<variable><name>{0}</name><type>{1}</type><value>{2}</value><x>{3}</x><y>{4}</y></variable>', this.get('name'),  this.get('type'), this.get('value'), x, y);
    	return _model;
    }
});