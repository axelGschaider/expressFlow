Ext.ns('com.expressflow.dd');
Ext.define('com.expressflow.dd.DragElementHelper', {
	extend: 'Ext.util.Observable',
    statics: {
        getDDel: function(src) {
            var el;
            switch(src) {
                // Events
                case com.expressflow.model.Start.imageUrl(): 
                    el = Ext.create('com.expressflow.model.Start');
                    break;
                case com.expressflow.model.End.imageUrl():
                    el = Ext.create('com.expressflow.model.End');
                    break;
                case com.expressflow.model.Timer.imageUrl():
                	el = Ext.create('com.expressflow.model.Timer');
                	break;
                // Basic Elements
                case com.expressflow.model.Annotate.imageUrl():
                    el = Ext.create('com.expressflow.model.Annotate');
                    break;
                case com.expressflow.model.Assign.imageUrl():
                	el = Ext.create('com.expressflow.model.Assign');
                	break;
                case com.expressflow.model.MobileForm.imageUrl():
                    el = Ext.create('com.expressflow.model.MobileForm');
                    break;
                case com.expressflow.model.SendEmail.imageUrl():
                    el = Ext.create('com.expressflow.model.SendEmail');
                    break;
                case com.expressflow.model.SendSMS.imageUrl():
                    el = Ext.create('com.expressflow.model.SendSMS');
                    break;
                // Google Services Elements
                case com.expressflow.model.GoogleDocs.imageUrl():
                	el = Ext.create('com.expressflow.model.GoogleDocs');
                    break;
                case com.expressflow.model.GoogleCalendar.imageUrl():
                	el = Ext.create('com.expressflow.model.GoogleCalendar');
                	break;
                // Google App Engine Services Elements
                case com.expressflow.model.UrlShortener.imageUrl():
                	el = Ext.create('com.expressflow.model.UrlShortener');
                	break;
                // Structured Elements
                case 'https://s3.amazonaws.com/expressflow/assets/condition.png':
                	el = Ext.create('com.expressflow.model.Condition');
                	break;
                default:
                    console.log('[DragElementHelper.getDDel] Unknown element!');
            }
            return el;
        },
        dropTargetDispatcher: function(model){
            switch(model.modelName){
                case 'com.expressflow.model.MobileForm':
                    this.dispatchMobileHTForm(model);
                    break;
                case 'com.expressflow.model.GoogleDocs':
                	this.dispatchGoogleDocsForm(model);
                	break;
                case 'com.expressflow.model.GoogleCalendar':
                	this.dispatchGoogleCalendarForm(model);
                	break;
                case 'com.expressflow.model.Start':
                	var eventPanel = Ext.getCmp('eventsPanel');
                	// Add the end icon later, to avoid bad flow design ;-)
                	if(!Ext.getCmp('endEvent')){
                    	var end = Ext.create('com.expressflow.view.End', {
                    		id: 'endEvent',
                    		src: 'https://s3.amazonaws.com/expressflow/assets/end.png'
                    	});
                    	eventPanel.add(end);
                    }
                	// as the user models start triggered flows, let's remove the timer event
                	if(Ext.getCmp('timerIcon')){
                		var timerIcon = Ext.getCmp('timerIcon');
                		eventPanel.remove(timerIcon);
                	}
                	
                	break;
                case 'com.expressflow.model.Timer':
                	var eventPanel = Ext.getCmp('eventsPanel');
                	if(!Ext.getCmp('endEvent')){
                    	var end = Ext.create('com.expressflow.view.End', {
                    		id: 'endEvent',
                    		src: 'https://s3.amazonaws.com/expressflow/assets/end.png'
                    	});
                    	var eventPanel = Ext.getCmp('eventsPanel');
                    	eventPanel.add(end);
                    }
                	// as the user models start triggered flows, let's remove the timer event
                	if(Ext.getCmp('startIcon')){
                		var startIcon = Ext.getCmp('startIcon');
                		eventPanel.remove(startIcon);
                	}
                	break;
                default:
                	break;
            }
        },
        dispatchMobileHTForm: function(model){
            
        },
        dispatchGoogleDocsForm: function(model){
        	Ext.create('Ext.data.Store', {
    			storeId : 'manageDocsStore',
    			autoLoad: true,
    			fields : [ 'title', 'lastViewed', 'lastUpdated', 'modifiedByEmail', 'link' ],
    			proxy : {
    				type : 'rest',
    				url: '/api/googledocs/',
    				reader : {
    					type : 'json',
    					root : 'googleDocList'
    				}
    			}
    		});
        	
        	var window = Ext.create('Ext.Window', {
    			title : 'Manage Your Google Documents',
    			width : 600,
    			height : 400,
    			constrain : true,
    			maximizable : true,
    			layout : 'fit',
    			items : [ Ext.create('Ext.grid.Panel', {
    				id: 'manageGoogleDocsGrid',
    				title : 'My Google Docs',
    				store : Ext.data.StoreManager.lookup('manageDocsStore'),
    				columns : [ {
    					header : 'Title',
    					dataIndex : 'title'
    				},  
    				{
    					header : 'Last Viewed',
    					dataIndex : 'lastViewed'
    				},
    				{
    					header : 'Last Updated',
    					dataIndex : 'lastUpdated'
    				},
    				{
    					header: 'Modified By',
    					dataIndex: 'modifiedByEmail'
    				}]
    			}) ]
    		});
    		window.show();
        },
        dispatchGoogleCalendarForm: function(model){
        	Ext.create('Ext.data.Store', {
    			storeId : 'manageCalendarStore',
    			autoLoad: true,
    			fields : [ 'title', 'edited', 'updated', 'published', 'link' ],
    			proxy : {
    				type : 'rest',
    				url: '/api/googlecalendar/',
    				reader : {
    					type : 'json',
    					root : 'googleCalendarList'
    				}
    			}
    		});
        	
        	var window = Ext.create('Ext.Window', {
    			title : 'Manage Your Google Calendar',
    			width : 600,
    			height : 400,
    			constrain : true,
    			maximizable : true,
    			layout : 'fit',
    			items : [ Ext.create('Ext.grid.Panel', {
    				id: 'manageGoogleCalendarGrid',
    				title : 'My Google Calendars',
    				store : Ext.data.StoreManager.lookup('manageCalendarStore'),
    				columns : [ {
    					header : 'Title',
    					dataIndex : 'title'
    				},  
    				{
    					header : 'Last Edited',
    					dataIndex : 'edited'
    				},
    				{
    					header : 'Last Updated',
    					dataIndex : 'updated'
    				},
    				{
    					header: 'Last Published',
    					dataIndex: 'published'
    				}]
    			}) ]
    		});
    		window.show();
        }
    }
});