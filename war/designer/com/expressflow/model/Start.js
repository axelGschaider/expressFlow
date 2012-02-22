Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Start',{
    extend: 'com.expressflow.model.Element',
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/start.png';
    	},
    	imageWidth: function(){
    		return 52; 
    	},
    	imageHeight: function(){
    		return 52;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.Start');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		return element;
    	}
    },
    getXml: function(){
        var _xml = Ext.String.format('<startEvent name="{0}" />', this.get('name'));
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
    	var _model = Ext.String.format('<startEvent><name>{0}</name><x>{1}</x><y>{2}</y></startEvent>', this.get('name'), x, y);
    	return _model;
    }
}); 