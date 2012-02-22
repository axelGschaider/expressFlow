Ext.application({
    name: 'mytasks',
    models: [
             'mytasks.model.Task'
             ],
    stores: [
             'mytasks.store.TaskStore'
             ],
    launch: function() {
    	Ext.Loader.setConfig({
            enabled: true
        });
    	
    	var overlayTb = Ext.create("Ext.Toolbar", {
    		id: 'overlayToolbar',
    		dock: 'top'
    	});
    	
    	var overlay = Ext.create("Ext.Panel",{
    		modal: true,
    		width: 260,
    		height: 220,
    		styleHtmlContent: true,
    		dockedItems: overlayTb,
    		scroll: 'vertical',
    		cls: 'htmlcontent'});
    	
    	Ext.create('Ext.Container', {
    	    fullscreen: true,
    	    layout: {
    	        type: 'vbox',
    	        pack: 'center'
    	    },
    	    items: [
    	        {
    	            xtype : 'toolbar',
    	            docked: 'top',
    	            title: 'My Tasks'
    	        },   	
    	        {
    	        	xtype: 'container',
    	        	layout: {
    	                type: 'vbox',
    	                pack: 'center'
    	            },
    	            defaults: {
    	                xtype: 'button',
    	                margin: '10 10 0 10'
    	            },
    	            items: [
    	                    {
    	                    xtype: 'list',
    	                    itemTpl: '<strong>{name}</strong></div>',
    	                    store: Ext.getStore('mytasks.store.TaskStore'),
    	                    grouped: false,
    	                    indexBar: true,
    	                    onItemDisclosure: {
    	            			handler: function(record, btn, index) {   
    	            				overlay.setCentered(true);   
    	            				var overlayTb = Ext.getCmp('overlayToolbar');
    	            				overlayTb.setTitle(record.get('name'));	  
    	            				overlay.setHtml(record.get('description'));
    	            				var startProcessBtn = new Ext.Button({
    	            					id: 'startBtn',
    	            					width: 220,
    	            					align: 'middle',
    	            					text: 'Start Task',
    	            					dock: 'bottom',
    	            					listeners: {'tap': function(){	
    	            						window.location = record.get('url');
    	            					}
    	            					}
    	            				});	  
    	            				if(overlay.items.items.length < 2)
    	            					overlay.add(startProcessBtn);
    	            				else{
    	            					overlay.removeAt(1);
    	            					overlay.add(startProcessBtn);
    	            				}
    	            				overlay.show();   
    	            			}
    	                    }
    	                    }
    	            ]
    	        }]
    	    });
    }
});