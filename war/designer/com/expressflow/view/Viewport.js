Ext.ns('com.expressflow.view');
Ext.define('com.expressflow.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: ['com.expressflow.model.Start', 'com.expressflow.model.End'],
    layout: 'border',
    items: [{
        region: 'north',
        xtype: 'toolbar',
        items: [{
            text: 'Main',
            menu: {
                xtype: 'menu',
                id: 'mainMenu',
                items: [{
                    text: 'New',
                    menu: {
                        xtype: 'menu',
                        id: 'newMenu',
                        items: [{
                            text: 'Process',
                            id: 'newProcessItem'
                        }]
                    }
                }, {
                    xtype: 'menuseparator',
                    id: 'mainMenuSeparator'
                }, {
                    xtype: 'menuitem',
                    text: 'XML Source',
                    listeners: {
                        click: function() {
                            var xmlWindow = Ext.create('Ext.Window', {
                                title: 'Business Process XML Source Test',
                                width: 600,
                                height: 400,
                                constrain: true,
                                maximizable: true,
                                layout: 'fit',
                                items: [{
                                    html: com.expressflow.designer.Designer.getElementsXml(),
                                    height: 100,
                                    margin: '0 0 5 0'
                                }]
                            });
                            xmlWindow.show();
                        }
                    }
                }, {
                    xtype: 'menuitem',
                    text: 'Close Designer',
                    listeners: {
                    	click: function(){
                    		 Ext.MessageBox.show({
                    	           title:'Close expressFlow Designer?',
                    	           msg: 'You are closing the designer that has unsaved changes. <br />Would you like to save your changes?',
                    	           buttons: Ext.MessageBox.YESNOCANCEL,
                    	           fn: function(btn){
                    	        	   if(btn == "yes" ){
                    	        		   window.location = "http://expressflow.com";
                    	        	   }
                    	           },
                    	           icon: Ext.MessageBox.QUESTION
                    	       });
                    	}
                    }
                }]
            }
        },
        {
        	xtype: 'textfield',
        	id: 'processNameTxt',
        	listeners: {
        		specialkey: function(field, e){
        			 if ( e.getKey() == e.RETURN || e.getKey() == e.ENTER ) {
        				 var pNameTxT = Ext.getCmp('processNameTxt');
        				 Ext.getCmp('designerArea1').setTitle(pNameTxT.getValue());
        			 }
        		}
        	}
        },
        {
            xtype: 'button',
            id: 'deployProcessItem',
            text: 'Deploy Process'
        },
        {
            xtype: 'button',
            id: 'manageProcessesItem',
            text: 'Manage Processes'
        }
        ]
    }, {
        region: 'west',
        collapsible: true,
        split: true,
        title: 'Toolbox',
        xtype: 'panel',
        layout: 'accordion',
        id: 'toolbox-panel',
        width: 220,
        items: [{
            xtype: 'panel',
            title: 'Events',
            id: 'eventsPanel',
            layout: {
                type: 'table',
                columns: 2
            },
            items: [
            Ext.create('com.expressflow.view.Start', {
                id: 'startIcon',
                tooltip: 'Start',
                src: 'https://s3.amazonaws.com/expressflow/assets/start.png'
            }),
            {
            	id: 'timerIcon',
            	xtype: 'image',
            	src: 'https://s3.amazonaws.com/expressflow/assets/timer.png'
            }],
            renderTo: Ext.get('eventsPanel')
        }, {
            xtype: 'panel',
            title: 'Basic Activities',
            id: 'basicActivitiesPanel',
            layout: {
                type: 'table',
                columns: 2
            },
            items: [
            {
                id: 'assignIcon',
                xtype: 'image',
                src: 'https://s3.amazonaws.com/expressflow/assets/copy.png'
            }, {
                id: 'mobileFormIcon',
                xtype: 'image',
                src: 'https://s3.amazonaws.com/expressflow/assets/mobileHT.PNG'
            },
            {
                id: 'sendEmailIcon',
                xtype: 'image',
                src: 'https://s3.amazonaws.com/expressflow/assets/sendEmail.PNG'
            }, {
                id: 'sendSMSIcon',
                xtype: 'image',
                src: 'https://s3.amazonaws.com/expressflow/assets/sendSMS.png'
            }
            ]
        },
        {
            xtype: 'panel',
            title: 'Google Documents',
            id: 'googleActivitiesPanel',
            layout: {
                type: 'table',
                columns: 2
            },
            items: [
            {
            	id: 'googleDocsIcon',
            	xtype: 'image',
            	src: 'https://s3.amazonaws.com/expressflow/assets/GoogleDocs.PNG'
            },
            {
            	id: 'googleTasksIcon',
            	xtype: 'image',
            	src: 'https://s3.amazonaws.com/expressflow/assets/gcalendar.PNG'
            }
            ]
        },
        {
        	xtype: 'panel',
        	title: 'Google Services',
        	id: 'gaeActivitiesPanel',
        	layout: {
        		type: 'table',
        		columns: 2
        	},
        	items: [
        	        {
        	        	id: 'gaeUrlShortenerIcon',
        	        	xtype: 'image',
        	        	src: 'https://s3.amazonaws.com/expressflow/assets/URLShortener.PNG'
        	}]
        }
        ]
    }, {
        region: 'south',
        title: 'Engine Console',
        collapsible: true,
        split: true,
        height: 50,
        minHeight: 50,
        items:[
            Ext.create('Ext.ux.StatusBar', {
    		id: 'processStatus'
		})
        ]
    }, {
        id: 'properties',
        region: 'east',
        title: 'Properties',
        collapsible: true,
        split: true,
        width: 250,
        autoscroll: true,
        items: [
        {
            xtype: 'panel',
            title: 'My Profile'
        }]
    }, 
    {
        id: 'designerTab',
        region: 'center',
        xtype: 'tabpanel',
        // TabPanel itself has no title
        activeTab: 0,
        // First tab active by default
        items:
        {
            id: 'designerArea1',
            xtype: 'panel',
            title: 'Process 1',
            layout: 'vbox',
            closable: false,
            constructor: function(config){
            	this.addEvents('saveProcess');
            }
       } 
    }]
});