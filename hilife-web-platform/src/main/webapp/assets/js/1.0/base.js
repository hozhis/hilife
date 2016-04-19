require(['global'], function(global){
	require.config({  
		baseUrl: global.static_url + '/assets/js/1.0',
		paths : {
			// the left side is the module ID, the right side is the path to
			// the jQuery file, relative to baseUrl. Also, the path should NOT include
			// the '.js' file extension. This example is using jQuery located at
			// vendor/jquery/jquery-1.11.0.js, relative to the HTML page.
			'jquery' : '../../vendor/jquery/jquery-1.11.0',
			'bootstrap' : '../../vendor/bootstrap/js/bootstrap',
			'jquery.ui' : '../../vendor/jquery-ui/js/jquery-ui.min',
			'jquery.validate' : '../../vendor/jquery-validation/jquery.validate',
			'jquery.spinner' : '../../vendor/jquery.spinner/jquery.spinner',
			'jquery.pnotify' : '../../vendor/pnotify/jquery.pnotify',
			'jquery-bridget/jquery.bridget': '../../vendor/masonry/masonry.pkgd',
			'holder' : '../../vendor/holder/holder-2.3.1',
			'imagesloaded' : '../../vendor/imagesloaded/imagesloaded.pkgd',
			'jquery.infinitescroll' : '../../vendor/infinite-scroll/jquery.infinitescroll',
			'masonry' : '../../vendor/masonry/masonry.pkgd',
			'modernizr' : '../../vendor/modernizr/modernizr-2.7.1',
			'requirejs/text' : '../../vendor/requirejs/plugins/text',
			'requirejs/i18n' : '../../vendor/requirejs/plugins/i18n',
			'requirejs/domready' : '../../vendor/requirejs/plugins/domReady',
			'requirejs/cs' : '../../vendor/requirejs/plugins/cs',
			'datatables' : '../../vendor/datatables/js/jquery.dataTables',
			'columnfilter': '../../vendor/datatables/js/ColumnFilter',
			'fixedcolumns':'../../vendor/dataTables.fixedColumns/js/dataTables.fixedColumns',
			'ajaxfileupload':'../../vendor/fileupload/ajaxfileupload',
			'ajaxfileupload.multi':'../../vendor/fileupload/ajaxfileupload.multi',
			'jquery.migrate':'../../vendor/jquery.migrate/jquery.migrate',
			'jquery.jqgrid':'../../vendor/jqgrid/jquery.jqGrid',
			'jquery.jqgrid/i18n':'../../vendor/jqgrid/i18n/grid.locale-en',
			'bootstrap.datatimepicker':'../../vendor/bootstrap-datatimepicker/js/bootstrap-datetimepicker.min',
			'fileupload':'../../vendor/fileupload/jquery.fileupload',
			'iframe':'../../vendor/fileupload/jquery.iframe',
			'jquery.md5':'../../vendor/jquery-md5/jquery.md5',
			'jquery.cookie':'../../vendor/jquery-cookie/jquery.cookie',
			'jquery.easyui':'../../vendor/jquery-easyui/jquery.easyui.min',
			'editor':'../../vendor/editor/jHtmlArea-0.8.min',
			'slide':'../../vendor/slide/slider',
			'jquery.min':'../../vendor/jquery-easyui/jquery.min',
			'jquery.combotree':'../../vendor/jquery-easyui/plugins/jquery.combotree'
		},
		// Remember: only use shim config for non-AMD scripts,
		// scripts that do not already call define(). The shim
		// config will not work correctly if used on AMD scripts,
		// in particular, the exports and init config will not
		// be triggered, and the deps config will be confusing
		// for those cases.
		shim : {
			'modernizr': {
				// Once loaded, use the global 'Holder' as the
				// module value.
				exports: 'Modernizr'
			},
			'holder': {
				// Once loaded, use the global 'Holder' as the
				// module value.
				exports: 'Holder'
			},
			'bootstrap': {
				// These script dependencies should be loaded before loading
				// bootstrap modual
				deps : [ 'jquery' ]
			},
			'bootstrap.datatimepicker':{
				// These script dependencies should be loaded before loading
				// datatimepicker modual
				deps : [ 'jquery','bootstrap' ]
			},
			'jquery.ui': {
				deps : [ 'jquery' ]
			},
			'jquery.validate': {
				deps : [ 'jquery' ]
			},
			'jquery.spinner' : {
				deps : [ 'jquery' ]
			},
			'jquery.pnotify' : {
				deps : [ 'jquery' ]
			},
			'jquery.infinitescroll' : {
				deps : [ 'jquery' ]
			},
			'plugins': {
				deps : [ 'jquery.pnotify' ]
			},
			'datatables': {
				// These script dependencies should be loaded before loading
				// bootstrap modual
				deps : [ 'jquery' ]
			},
			'jquery.jqgrid': {
				// These script dependencies should be loaded before loading
				// bootstrap modual
				deps : [ 'jquery' ]
			},
			'jquery.jqgrid/i18n':{
				deps : [ 'jquery' ]
			},
			'jquery.migrate':{
	            deps : ['jquery']
	        },
			'fixedcolumns':{
                deps : ['jquery','datatables', 'jquery.migrate']
            },
	        'columnfilter':{
                deps : ['jquery','datatables']
            },
            'ajaxfileupload':{
            	deps : [ 'jquery' ]
            },
            'ajaxfileupload.multi':{
            	deps : [ 'jquery','ajaxfileupload' ],
            },
            'fileupload':{
            	deps : [ 'jquery' ]
            },
            'jquery.md5': {
				deps : [ 'jquery' ]
			},
			'jquery.easyui':{
				deps : [ 'jquery.min' ],
			},
			'jquery.combotree':{
				deps : [ 'jquery.min' ,'jquery.easyui' ],
			},
			'editor': {
				deps : [ 'jquery' ]
			},
			'slide': {
				deps : [ 'jquery' ]
			}
			
		}
	});
});
