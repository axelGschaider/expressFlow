Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.MobileFormForm', {
    extend: 'Ext.form.Panel',
    title: 'Mobile Form',
    width: '100%',
    height: '100%',
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
    getModel: function(){
    	return this._model;
    },
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    },
    {
        xtype: 'textfield',
        name: 'name',
        fieldLabel: 'Name',
        allowblank: false
    },
    {
    	xtype: 'fieldset',
    	title: 'Participants',
    	id: 'participantsFields',
    	items: [{
    		xtype: 'textfield',
    		name: 'participant',
    		fieldLabel: 'Participant',
    		allowblank: true
    	},{
    		xtype: 'button',
    		text: 'Invite',
    		handler: function(){
    			var f = this.up('form').getForm();
    			var participant = f.findField('participant').getValue();
    			console.log(participant +  " invited.");
    		}
    	}]
    },
    {
        xtype: 'fieldset',
        title: 'Form fields',
        id: 'formFields',
        items:[{
        			xtype: 'button',
        			text: 'Add Form Field',
        			handler: function(){
        				var f = this.up('form').getForm();
        				var form = Ext.getCmp('formFields');
        				var mobileForm = Ext.create('com.expressflow.view.forms.MobileFormContainer');
        				var modelIndex = f.findField('modelIndex').getValue();
        				var mobileF = mobileForm.getForm();
        				mobileF.findField('modelIndex').setValue(modelIndex);
        				form.add(mobileForm);
        				com.expressflow.view.forms.MobileFormForm.addForm(mobileForm);
        			}
        	},
        	{
        		xtype: 'button',
        		text: 'Remove Form',
        		handler: function(){
        			var form = Ext.getCmp('formFields');
        			var mForm = com.expressflow.view.forms.MobileFormForm.popForm();
        			if(mForm)
        				mForm.hide();
        		}
        	}]
    },
    {
    	xtype: 'button',
    	id: 'saveMhtButton',
    	text: 'Save',
    	handler: function() {
            var form = this.up('form').getForm();
            
            // Get the variablesStore
            var variablesStore = Ext.getStore('com.expressflow.store.VariablesStore');
            
            var mobileHumanTasksStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var modelIndex = form.findField('modelIndex').getValue();
            var model = mobileHumanTasksStore.data.items[modelIndex];
            model.set('name', form.findField('name').getValue());
            
            var modelXml = Ext.String.format('<mobileForm name="{0}">', model.data.name);
            var modelX = Ext.String.format('<mobileForm><name>{0}</name>', model.data.name);
            
            model.variables().each(function(variable){
            	variablesStore.add(variable);
    			modelXml += variable.getXml();
    			modelX += variable.getModel();
            });
            modelXml += "</mobileForm>";
            modelX += "</mobileForm>";
            
            model.data.xml = modelX;
            model.data.execXml = modelXml;
            
            // Reset the process variables on the DesignArea
            com.expressflow.designer.Designer.resetVariables();
            
            // Draw the variables on the DesignArea
            com.expressflow.designer.Designer.drawVariables();
            
            // Get the ProcessStore
            var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
            
            var process = processStore.getAt(0);
            
            // Get the MobileHumanTasksStore
            var mobileHumanTasksStore = Ext.getStore('com.expressflow.store.MobileFormsStore');
            mobileHumanTasksStore.removeAll();
            mobileHumanTasksStore.add(model);
            
            var mht = Ext.create('com.expressflow.model.MobileForm');
            mht.data.image = com.expressflow.model.MobileForm.imageUrl();
            mht.data.x = model.data.x;
            mht.data.y = model.data.y;
            mht.data.xml = model.data.xml;
            mht.data.execXml = model.data.execXml;
            mht.data.name = model.data.name;
            mht.data.id = 123;
            mht.data.creator = model.data.creator;
            mht.data.method = process.data.id;
            mht.variables = model.variables();
            
            mht.setProxy({
                type: 'rest',
                url: '/api/mobileform'
            });
            
            mht.save({
            	success: function(m){
            		Ext.message.msg("Advice 2", "Created Variables for Form <b>" + model.data.name + "</b><br/><br/>"
            				+ "Mobile Human Task generated. <a href='/mobileform/" + m.raw.mobileForm.id + "' target='_blank'>Explore</a><br />"
            				+ "Deploy your process to publish your mobile human task!<br/><br />"
            				+ "<b>Click to close</b>");
            		var button = Ext.getCmp('saveMhtButton');
            		button.disable();
            		
            		var mobileHumanTaskStore = Ext.getStore('com.expressflow.store.MobileFormsStore');
            		if(mobileHumanTaskStore.getAt(0)){
            			var mobile = mobileHumanTaskStore.getAt(0);
            			mobile.set("id", m.raw.mobileForm.id);
            		}
            	}
            });
    	}
    },
    {
    	xtype: 'button',
    	text: 'Reset',
    	handler: function(){
    		 var form = this.up('form').getForm().reset();
    	}
    }]
});