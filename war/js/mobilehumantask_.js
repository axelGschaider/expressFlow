Ext.setup({
    icon: 'icon.png',
    tabletStartupScreen: 'tablet_startup.png',
    phoneStartupScreen: 'phone_startup.png',
    glossOnIcon: false,
    onReady: function() {

        var form;
        
        Ext.regModel('User', {
            fields: [
                {name: 'name',     type: 'string'},
                {name: 'password', type: 'password'},
                {name: 'email',    type: 'string'},
                {name: 'url',      type: 'string'},
                {name: 'rank',     type: 'string'},
                {name: 'enable',   type: 'boolean'},
                {name: 'cool',     type: 'boolean'},
                {name: 'color',    type: 'string'},
                {name: 'team',     type: 'string'},
                {name: 'secret',   type: 'boolean'}
            ]
        });
        
         Ext.regModel('Ranks', {
            fields: [
                {name: 'rank',     type: 'string'},
                {name: 'title',    type: 'string'}
            ]
         });
        
        var ranksStore = new Ext.data.JsonStore({
           data : [
                { rank : 'master',  title : 'Master'},
                { rank : 'padawan', title : 'Student'},
                { rank : 'teacher', title : 'Instructor'},
                { rank : 'aid',     title : 'Assistant'}
           ],
           model : 'Ranks',
           autoLoad : true,
           autoDestroy : true
        });
        
        var formBase = {
            scroll: 'vertical',
            url   : 'postUser.php',
            standardSubmit : false,
            items: [
                {
                    xtype: 'fieldset',
                    title: 'Personal Info',
                    instructions: 'Please enter the information above.',
                    defaults: {
                        required: true,
                        labelAlign: 'left',
                        labelWidth: '40%'
                    },
                    items: [
                    {
                        xtype: 'textfield',
                        name : 'name',
                        label: 'Name',
                        useClearIcon: true,
                        autoCapitalize : false
                    }, {
                        xtype: 'passwordfield',
                        name : 'password',
                        label: 'Password',
                        useClearIcon: false
                    }, {
                        xtype: 'textfield',
                        name : 'disabled',
                        label: 'Disabled',
                        disabled: true,
                        useClearIcon: true
                    }, {
                        xtype: 'emailfield',
                        name : 'email',
                        label: 'Email',
                        placeHolder: 'you@sencha.com',
                        useClearIcon: true
                    }, {
                        xtype: 'urlfield',
                        name : 'url',
                        label: 'Url',
                        placeHolder: 'http://sencha.com',
                        useClearIcon: true
                    }, {
                        xtype: 'checkboxfield',
                        name : 'cool',
                        label: 'Cool',
                        value: true
                    }, {
                        xtype: 'spinnerfield',
                        name : 'spinner',
                        label: 'Spinner'
                    }, {
                        xtype: 'selectfield',
                        name : 'rank',
                        label: 'Rank',
                        valueField : 'rank',
                        displayField : 'title',
                        store : ranksStore
                    }, {
                        xtype: 'hiddenfield',
                        name : 'secret',
                        value: 'false'
                    }, {
                        xtype : 'textareafield',
                        name  : 'bio',
                        label : 'Bio',
                        maxLength : 60,
                        maxRows : 10
                    }]
                }
            ],
            listeners : {
                submit : function(form, result){
                    console.log('success', Ext.toArray(arguments));
                },
                exception : function(form, result){
                    console.log('failure', Ext.toArray(arguments));
                }
            },
        
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    items: [
                        {
                            text: 'Load Model',
                            ui: 'round',
                            handler: function() {
                                formBase.user = Ext.ModelMgr.create({
                                    'name'    : 'Martin',
                                    'password': 'secret',
                                    'email'   : 'martin@expressflow.com',
                                    'url'     : 'http://expressflow.com',
                                    'single_slider': 20,
                                    'enable'  : 1,
                                    'cool'    : true,
                                    'team'    : 'redteam',
                                    'color'   : 'blue',
                                    'rank'    : 'padawan',
                                    'secret'  : true,
                                    'bio'     : 'Learned the hard way !'
                                }, 'User');
        
                                form.loadModel(formBase.user);
                            }
                        },
                        {xtype: 'spacer'},
                        {
                            text: 'Reset',
                            handler: function() {
                                form.reset();
                            }
                        },
                        {
                            text: 'Save',
                            ui: 'confirm',
                            handler: function() {
                                if(formBase.user){
                                    form.updateRecord(formBase.user, true);
                                }
                                form.submit({
                                    waitMsg : {message:'Submitting', cls : 'demos-loading'}
                                });
                            }
                        }
                    ]
                }
            ]
        };
        
        if (Ext.is.Phone) {
            formBase.fullscreen = true;
        } else {
            Ext.apply(formBase, {
                autoRender: true,
                floating: true,
                modal: true,
                centered: true,
                hideOnMaskTap: false,
                height: 385,
                width: 480
            });
        }
        
        form = new Ext.form.FormPanel(formBase);
        form.show();
    }
});