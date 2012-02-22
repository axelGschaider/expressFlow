Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.UrlShortenerForm', {
    extend: 'Ext.form.Panel',
    title: 'Url Shortener Element',
    width: '100%',
    statics:{
    	_forms: new Array(),
    	addForm: function(form){
    		this._forms.push(form);
    	},
		getForm: function(index){
			return this._forms[index];
		},
		getFormsLength: function(){
			return this._forms.length;
		},
		popForm: function(){
			if(this._forms.length > 0){
				return this._forms.pop();
			}
		}
    },
    _model: 'Empty',
    forms: new Array(),
    setModel: function(model) {
        this._model = model;
    },
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    },
    {
        xtype: 'textfield',
        name: 'longUrl',
        fieldLabel: 'URL',
        allowblank: false
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var variablesStore = Ext.getStore('com.expressflow.store.VariablesStore');
            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
            model.set('longUrl', form.findField('longUrl').getValue());
            model.set('shortUrl', 'empty');
            
            // Set Process Variables
            var shortUrlVar = Ext.create('com.expressflow.model.Variable',{
            	name: 'shortUrl',
            	type: 'string',
            	value: 'empty'
            });
            variablesStore.add(shortUrlVar);
            
            var longUrlVar = Ext.create('com.expressflow.model.Variable',{
            	name: 'longUrl',
            	type: 'string',
            	value: form.findField('longUrl').getValue()
            });
			variablesStore.add(longUrlVar);
			
			// Reset the process variables on the DesignArea ...
            com.expressflow.designer.Designer.resetVariables();
			
            // and draw the updated variables 
			com.expressflow.designer.Designer.drawVariables();
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});