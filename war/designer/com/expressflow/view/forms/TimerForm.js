Ext.namespace('com.expressflow.view.forms')
Ext.define('com.expressflow.view.forms.TimerForm', {
    extend: 'Ext.form.Panel',
    title: 'Timer',
    width: '100%',
    _model: 'Empty',
    setModel: function(model) {
        this._model = model;
    },
    items: [{
        xtype: 'hidden',
        name: 'modelIndex'
    },
    {
        xtype: 'datefield',
        anchor: '100%',
        fieldLabel: 'Date',
        format: 'm.d.Y',
        name: 'trigger_date'
    },
    {
        xtype: 'textfield',
        name: 'trigger_time',
        fieldLabel: 'Time (e.g. 15:38)'
    }],
    buttons: [{
        text: 'Save',
        handler: function(event) {
            var form = this.up('form').getForm();
            var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
            var model = elementsStore.data.items[form.findField('modelIndex').getValue()];
            var triggerDate = form.findField('trigger_date').getValue();
            var triggerTime = form.findField('trigger_time').getValue();
            var triggerDate = Ext.Date.format(triggerDate, 'Y-m-d');
            var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
            var process = processStore.data.items[0];
            
            var timer = Ext.create('com.expressflow.model.Timer');
            model.data.trigger = triggerDate + " " + triggerTime;
            model.data.processId = process.data.id;
            timer.data.image = com.expressflow.model.Timer.imageUrl();
            timer.data.x = model.data.x;
            timer.data.y = model.data.y;
            timer.data.processId = process.data.id;
            timer.data.trigger = triggerDate + " " + triggerTime;
            timer.data.id = 123;
            timer.data.xml = model.getModel();
            timer.data.execXml = model.getXml();

            timer.setProxy({
                type: 'rest',
                url: '/api/timermgmt'
            });
            
            timer.save({
            	success: function(m){
            		console.log("Timer saved.");
            	}
            });
        }
    }, {
        text: 'Reset',
        handler: function() {
            var form = this.up('form').getForm().reset();
        }
    }]
});