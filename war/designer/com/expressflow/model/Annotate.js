Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Annotate',{
    extend: 'com.expressflow.model.Element', 
    statics:{
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/annotate.png';
    	},
    	imageWidth: function(){
    		return 102; 
    	},
    	imageHeight: function(){
    		return 60;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.Annotate');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		element.data.image = this.imageUrl();
    		return element;
    	}
    },
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'String'},
        {name: 'variable', type: 'String'},
        {name: 'expr', type: 'String'}
        ],
    getXml: function(){
        var _xml = Ext.String.format('<annotate name="{0}" variable="{1}" expr="{2}" />', this.get('name'), this.get('variable'), this.get('expr'));
        return _xml;
    },
    getModel: function(){
    	var _image = this.get('image');
    	var _model = Ext.String.format('<annotate><name>{0}</name><variable>{3}</variable><expr>{4}</expr><x>{1}</x><y>{2}</y></annotate>', this.get('name'), _image.attr('x'), _image.attr('y'), this.get('variable'), this.get('expr'));
    	return _model;
    }
});