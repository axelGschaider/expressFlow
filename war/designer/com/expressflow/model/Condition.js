Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Condition',{
    extend: 'com.expressflow.model.Scope',
    fields: [
             {name: 'expression', type: 'String'}
    ],
    getXml: function(){
        var _xml = Ext.String.format('<exclusiveGateway name="{0}" />', this.get('name'));
        return _xml;
    },
    getModel: function(){
    	var _image = this.get('image');
    	var _model = Ext.String.format('<exclusiveGateway name="{0}" x={1} y={2} />', this.get('name'), _image.attr('x'), _image.attr('y'));
    	return _model;
    }
}); 