Ext.application({
    name: 'com.expressflow',
    autoCreateViewport: true,
    appFolder: 'com.expressflow',
    controllers: [
        'com.expressflow.controller.DesignerController',
        'com.expressflow.controller.ManageProcessesController',
        'com.expressflow.controller.GoogleServicesController',
        'com.expressflow.controller.DeployProcessController'
    ],
    models: [
        'com.expressflow.model.Advice',
        'com.expressflow.model.Assign',
        'com.expressflow.model.CloudStorage',
        'com.expressflow.model.CreateFile',
        'com.expressflow.model.Decrypt',
        'com.expressflow.model.Encrypt',
        'com.expressflow.model.MobileForm', 
        'com.expressflow.model.SendSMS',
        'com.expressflow.model.MakeCall',
        'com.expressflow.model.Start',
        'com.expressflow.model.Process',
        'com.expressflow.model.UrlShortener',
        'com.expressflow.model.User',
        'com.expressflow.model.xml.XProcess'
    ],
    stores: [
        'com.expressflow.store.AdvicesStore',
        'com.expressflow.store.ElementsStore',
        'com.expressflow.store.MobileFormsStore' ,
        'com.expressflow.store.ProcessStore',
        'com.expressflow.store.UserStore',
        'com.expressflow.store.VariablesStore'
    ],
    launch: function() {
        Ext.BLANK_IMAGE_URL = 'https://s3.amazonaws.com/expressflow/extjs/s.gif';
        Ext.Loader.setConfig({
            enabled: true
        });
        Ext.QuickTips.init();
        // Drag and Drop Zones
        var startDragZone, eventDragZone, basicActivitiesDragZone, googleServicesDragZone, gaeServicesDragZone, structuredActivitiesDragZone, designerDropZone;
        eventDragZone = Ext.create('com.expressflow.dd.xFDragZone', 'eventsPanel');
        basicActivitiesDragZone = Ext.create('com.expressflow.dd.xFDragZone', 'basicActivitiesPanel');
        googleServicesDragZone = Ext.create('com.expressflow.dd.xFDragZone', 'googleActivitiesPanel');
        gaeServicesDragZone = Ext.create('com.expressflow.dd.xFDragZone', 'gaeActivitiesPanel');
        designerDropZone = Ext.create('com.expressflow.dd.xFDropTarget', 'designerArea1');
		// structuredActivitiesDragZone = Ext.create('com.expressflow.dd.xFDragZone', 'structuredActivitiesPanel');
		
		// Clean up all stores
		var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
		processStore.removeAll();
		var elementsStore = Ext.getStore('com.expressflow.store.ElementsStore');
		elementsStore.removeAll();
		var variablesStore = Ext.getStore('com.expressflow.store.VariablesStore');
		variablesStore.removeAll();
		
		// Init the user
        com.expressflow.model.User.setProxy({
        	type: 'rest',
        	url: '/api/user/init',
        	root: 'user'
        });
        com.expressflow.model.User.load(0,{
        	success: function(u){
        		if(u.raw.user.googleServicesActivated > 0){
        			var toolboxPanel = Ext.getCmp('toolbox-panel');
        			var googleServicesPanel = Ext.getCmp('googleActivitiesPanel');
        			toolboxPanel.remove(googleServicesPanel);
        		}
        		var userStore = Ext.getStore('com.expressflow.store.UserStore');
        		var user = Ext.create('com.expressflow.model.User');
        		user.data.nickname = u.raw.user.nickname;
        		user.data.email = u.raw.user.email;
        		user.data.appId = u.raw.user.appId;
        		userStore.insert(0, user);
        	}
        });
		
        // Init the process model
        com.expressflow.model.Process.setProxy({
            type: 'rest',
            url: '/api/process/init'
        });
        
        com.expressflow.model.Process.load(0,{
        	success: function(p){
        		console.log("Process created.");
        		var processStore = Ext.getStore('com.expressflow.store.ProcessStore');
        		p.data.id = p.raw.process.id;
        		p.data.accessDateView = p.raw.process.accessDateView;
        		p.data.creationDateView = p.raw.process.creationDateView;
        		p.data.creator = p.raw.process.creator;
        		p.data.name = "Process 1";
        		Ext.getCmp('processNameTxt').setValue("Process 1");
        		processStore.insert(0, p);	
        		
        		var adviceStore = Ext.getStore('com.expressflow.store.AdvicesStore');
        		var advice = adviceStore.data.items[0];
        		var title = advice.data.title;
        		var message = advice.data.message;
        		Ext.message.msg(title, message);
        		// Ext.message.msg("Advice 1", "Remember: Each process begins with a Start:<br /><img src='https://s3.amazonaws.com/expressflow/assets/start.png' /><br />"
        		//		+"And ends with an End ;-)<img src='https://s3.amazonaws.com/expressflow/assets/end.png' /> <br /><b>Click to close</b>");
        	}
        });
    }
});