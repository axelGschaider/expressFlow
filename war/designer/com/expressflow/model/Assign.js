Ext.namespace('com.expressflow.model');

Ext.define('com.expressflow.model.Assign',{
    extend: 'com.expressflow.model.Element', 
    fields: [
             {name: 'xml', type: 'String'},
             {name: 'execXml', type: 'String'},
             {name: 'fromvariable', type: 'String'},
             {name: 'fromexpr', type: 'String'},
             {name: 'literal', type: 'String'},
             {name: 'toexpr', type: 'String'},
             {name: 'outputvariable', type: 'String'}
    ],
    statics: {
    	imageUrl: function(){
    		return 'https://s3.amazonaws.com/expressflow/assets/copy.png';
    	},
    	imageWidth: function(){
    		return 100; 
    	},
    	imageHeight: function(){
    		return 60;
    	},
    	fromXML: function(xml){
    		var element = Ext.create('com.expressflow.model.Assign');
    		element = com.expressflow.parser.Parser.parseBase(element, xml);
    		if(xml.childNodes.length > 0){
				for(var pc = 0; pc < xml.childNodes.length; pc++){
					switch(xml.childNodes[pc].nodeName){
					case "fromvariable":
	    				if(xml.childNodes[i].firstChild)
	    					element.data.fromvariable = xml.childNodes[i].firstChild.nodeValue;
						break;
					case "fromexpr":
	    				if(xml.childNodes[i].firstChild)
	    					element.data.fromexpr = xml.childNodes[i].firstChild.nodeValue;
	    				break;
					case "literal":
	    				if(xml.childNodes[i].firstChild)
	    					element.data.literal = xml.childNodes[i].firstChild.nodeValue;
						break;
					case "toexpr":
	    				if(xml.childNodes[i].firstChild)
	    					element.data.toexpr = xml.childNodes[i].firstChild.nodeValue;
						break;
					case "outputvariable":
	    				if(xml.childNodes[i].firstChild)
	    					element.data.outputvariable = xml.childNodes[i].firstChild.nodeValue;
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
        var _xml = Ext.String.format('<assign ');
        if(this.get('fromvariable'))
        	_xml += Ext.String.format(' fromvariable={0}', this.get('fromvariable')); 
        if(this.get('fromexpr'))
        	_xml += Ext.String.format(' fromexpr={0}', this.get('fromexpr')); 
        if(this.get('literal'))
        	_xml += Ext.String.format(' literal={0}', this.get('literal')); 
        if(this.get('toexpr'))
        	_xml += Ext.String.format(' toexpr={0}', this.get('toexpr')); 
        if(this.get('outputvariable'))
        	_xml += Ext.String.format(' outputvariable={0}', this.get('outputvariable')); 
        _xml += "></assign>";
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
    	var _model = Ext.String.format('<assign>');
    	 if(this.get('fromvariable'))
         	_model += Ext.String.format('<fromvariable>{0}</fromvariable>', this.get('fromvariable')); 
         if(this.get('fromexpr'))
         	_xml += Ext.String.format('<fromexpr>{0}</fromexpr>', this.get('fromexpr')); 
         if(this.get('literal'))
         	_xml += Ext.String.format('<literal>{0}</literal>', this.get('literal')); 
         if(this.get('toexpr'))
         	_xml += Ext.String.format('<toexpr>{0}</toexpr>', this.get('toexpr')); 
         if(this.get('outputvariable'))
         	_xml += Ext.String.format('<outputvariable>{0}</outputvariable>', this.get('outputvariable')); 
    	_model += Ext.String.format('<x>{0}</x><y>{1}</y>', x, y);
    	_model += "</assign>";
    	return _model;
    }
});