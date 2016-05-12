require(['global'],function(global){
	require.config({
		baseUrl : global.static_url + '/assets/js/1.0',
		paths : {
			// the left side is the module ID, the right side is the path to
			// the jQuery file, relative to baseUrl. Also, the path should NOT include
			// the '.js' file extension. This example is using jQuery located at
			// vendor/jquery/jquery-1.11.0.js, relative to the HTML page.
			'jquery' : '../../vendor/jquery/jquery-1.11.0',
			'jquery.mobile' : '../../vendor/jquery-mobile/js/jquery.mobile-1.4.5',
			'bootstrap' : '../../vendor/bootstrap/js/bootstrap',
			'requirejs/cs' : '../../vendor/requirejs/plugins/cs',
			'requirejs/domready' : '../../vendor/requirejs/plugins/domready',
			'requirejs/i18n' : '../../vendor/requirejs/plugins/i18n',
			'requirejs/text' : '../../vendor/requirejs/plugins/text',
			'pnotify' : '../../vendor/layer-mobile/layer.mobile-1.7',
			'ajaxfileupload':'../../vendor/fileupload/ajaxfileupload',
			'ajaxfileupload.multi':'../../vendor/fileupload/ajaxfileupload.multi',
			'fileupload':'../../vendor/fileupload/jquery.fileupload',
			'iframe':'../../vendor/fileupload/jquery.iframe'
		},
		// Remember: only use shim config for non-AMD scripts,
		// scripts that do not already call define(). The shim
		// config will not work correctly if used on AMD scripts.
		// in particular, the exports and init config will not
		// be triggered, and the deps config will be confusing
		// for those cases.
		shim : {
			'jquery.mobile' : {
				// This script dependency should be loaded before loading
				// jquery mobile modual
				deps : ['jquery']
			},
			'bootstrap' : {
				// This script dependency should be loaded before loading
				// jquery mobile modual
				deps : ['jquery']
			},
			'ajaxfileupload':{
            	deps : [ 'jquery' ]
            },
            'ajaxfileupload.multi':{
            	deps : [ 'jquery','ajaxfileupload' ],
            },
            'fileupload':{
            	deps : [ 'jquery' ]
            }
		}
	});
});