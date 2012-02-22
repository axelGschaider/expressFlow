Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.MobileForm',{
    extend: 'com.expressflow.model.Element', 
    associations: [
                   {type: 'hasMany', model: 'com.expressflow.model.Variable',    name: 'variables'},
                   {type: 'hasMany', model: 'com.expressflow.model.User', name: 'users'}
    ],
    fields: [
             {name: 'xml', type: 'String'},
             {name: 'execXml', type: 'String'},
             {name: 'method', type: 'String'},
             {name: 'creator', type: 'com.expressflow.model.User'}
    ],
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/mobileHT.PNG';
    	},
    	imageWidth: function(){
    		return 100; 
    	},
    	imageHeight: function(){
    		return 60;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.MobileForm');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		if(xml.childNodes.length > 0){
				for(var pc = 0; pc < xml.childNodes.length; pc++){
					switch(xml.childNodes[pc].nodeName){
					case "variable":
						var variable = com.expressflow.model.Variable.fromXML(xml.childNodes[pc]);
						element.variables().add(variable);
						break;
					case "creator":
						var user = com.expressflow.model.User.fromXML(xml.childNodes[pc]);
						element.users().add(user);
						break;
					}
				}
			}
    		element.data.image = this.imageUrl();
    		return element;
    	}
    },
    proxy:{
        type: 'rest',
        url: '/api/mobileform',
        reader: {
        	type: 'json',
        	root: 'entity'
        },
        writer: {
        	type: 'json',
        	root: 'entity'
        }
    },
    getXml: function(){
        var _xml = Ext.String.format('<mobileForm name="{0}">', this.get('name'));
        this.variables().each(function(variable){
			_xml += variable.getXml();
        });
        this.users().each(function(user){
        	_xml += user.getXml();
        });
        _xml += "</mobileForm>";
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
    	var _model = Ext.String.format('<mobileForm><name>{0}</name><x>{1}</x><y>{2}</y>', this.get('name'), x, y);
    	this.variables().each(function(variable){
			_model += variable.getModel();
        });
    	this.users().each(function(user){
    		_model += user.getModel();
    	});
    	_model += "</mobileForm>";
    	return _model;
    }
});