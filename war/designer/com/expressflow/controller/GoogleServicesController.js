Ext.namespace('com.expressflow.controller');
Ext.define('com.expressflow.controller.GoogleServicesController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'#manageGoogleDocsGrid' : {
				itemdblclick: this.selectedGoogleDoc
			},
			'#manageGoogleCalendarGrid' : {
				itemdbclick: this.selectedGoogleCalendar
			}
		});
	},
	selectedGoogleDoc: function(dataview, record, item, index, e){
		var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
		var googleDoc = elementsStore.getAt(elementsStore.data.length - 1);
		googleDoc.data.link = record.data.link;
		googleDoc.data.email = record.data.modifiedByEmail;
	},
	selectedGoogleCalendar: function(dataview, record, item, index, e){
		var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
		var googleCalendar = elementsStore.getAt(elementsStore.data.length - 1);
	}
});