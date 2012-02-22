Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.End',{
    extend: 'com.expressflow.model.Element', 
    statics:{
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/end.png';
    	},
    	imageWidth: function(){
    		return 52; 
    	},
    	imageHeight: function(){
    		return 51;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.End');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		return element;
    	}
    },
    getXml: function(){
        var _xml = Ext.String.format('<endEvent />');
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
    	var _model = Ext.String.format('<endEvent><x>{1}</x><y>{2}</y></endEvent>', this.get('name'), x, y);
    	return _model;
    }
});