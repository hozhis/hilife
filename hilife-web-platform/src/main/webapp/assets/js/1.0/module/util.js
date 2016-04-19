define(
		[ 'jquery' ],
		function($) {
			
			$.fn.extend({          
				gridJSON: function(opts) {
					if(opts.mtype == "POST"||opts.mtype == "post"||opts.datatype == 'local'){
						var _opts = $.extend({
							serializeGridData: function (data) {
						          delete data._search;
						          delete data.sord;					
						          return JSON.stringify(data);
						    },
						    datatype: "json",
							ajaxGridOptions :{
								  contentType : "application/json; charset=utf-8"
							},
						    jsonReader: {
						        root : "list",  
						        page : "currentPage",  
						        total : "totalPages",  
						        records : "totalRecord",  
						        repeatitems : false
						    },
						    prmNames: {sort: null,nd:null,page:'currentPage',rows:'pageSize'}
						},opts);
						$(this).jqGrid(_opts);
					}
					else if(opts.mtype == "GET"||opts.mtype == "get"){
						var _opts = $.extend({
						    datatype: "json",
							ajaxGridOptions :{
								  contentType : "application/json; charset=utf-8"
							},
						    jsonReader: {
						        root : "list",  
						        page : "currentPage",  
						        total : "totalPages",  
						        records : "totalRecord",  
						        repeatitems : false
						    }
						},opts);
						$(this).jqGrid(_opts);
						
					}
					          
			      }       
			});
			
			F2J = {
					FORM_PATTERN : /.+\[\d*\]+$/,
					generate : function(obj, name, value) {
						if(name&&name.length){
						if (obj[name] === undefined) {
							obj[name] = value;
						} else {
							if (!obj[name].push) {
								obj[name] = [ obj[name] ];
							}
							obj[name].push(value);
						}
					}
						return obj;
					},
					translate : function(arrs) {
						var obj = {};
						$.each(arrs, function() {
							if (this.name.indexOf('.') != -1) {
								var params = this.name.split('.'), //
								innerobj = obj;
								for ( var i = 0; i < params.length - 1; i++) {
									var index = -1, //
									name = params[i], //
									tobj;
									if (F2J.FORM_PATTERN.test(name)) {
										index = name.substring(name.indexOf('[') + 1, name.indexOf(']'));
										name = name.substring(0, name.indexOf('['));
									}
									tobj = innerobj[name];
									// 未定义，则初始化
									if (tobj === undefined) {
										if (-1 === index) {
											innerobj[name] = {};
										} else {
											innerobj[name] = [];
										}
									}
									if (-1 === index) {
										innerobj = innerobj[name];
									} else {
										if (!innerobj[name][index]) {
											innerobj[name][index] = {};
										}
										innerobj = innerobj[name][index];
									}
								}
								F2J.generate(innerobj, params[params.length - 1], this.value);
							} else {
								F2J.generate(obj, this.name, this.value);
							}
						});
						return obj;
					}
				};
			var J2F = {
				parse : function parse($els, data) {
					var dataItem, $el;
					for ( var n in data) {
						if (!data.hasOwnProperty(n) || (dataItem = data[n]) == undefined)
							continue;
						$el = $els.filter("[name='"  + n + "']");
						if ($el.length > 0 && !$.isPlainObject(dataItem)) {
							if ($el.is("input[type='text']") || $el.is("input[type='hidden']") || $el.is("textarea")) {
								$el.val(dataItem);
							} else if ($el.is("input[type='radio']")) {
								$el.filter("[value='" + dataItem + "']").prop("checked", true);
							} else if ($el.is("input[type='checkbox']")) {
								if ($.isArray(dataItem)) {
									$.each(dataItem, function() {
										$el.filter("[value='" + this + "']").prop("checked", true);
									});
								} else {
									$el.prop("checked", dataItem);
								}
							} else if ($el.is("select")) {
								$el.val(dataItem);
								$el.select ? $el.select("value", dataItem) : $el.find("[value='" + dataItem + "']").prop("checked");
							} else if ($el.not("input,textarea,select")) {
								typeof dataItem === "string" && !/<(?!script)\w+\s+[^\n\f\r]*>/.test(dataItem) && (dataItem = dataItem.replace(/</g,"&lt;").replace(/>/g,"&gt;"));
								$el.html(dataItem);
							}
							$els = $els.not($el);
						} else {
							if ($.isPlainObject(dataItem)) {
								parse($els, dataItem, prefix + n + ".");
							}
						}
					}
				}
			};
			
			
			var Util = function() {
			};
			
			Util.prototype = {

				stringify_aoData : function(aoData) {
					var o = {};
					var modifiers = [ 'mDataProp_', 'sSearch_', 'iSortCol_',
							'bSortable_', 'bRegex_', 'bSearchable_',
							'sSortDir_' ];
					$
							.each(
									aoData,
									function(idx, obj) {
										if (obj.name) {
											for (var i = 0; i < modifiers.length; i++) {
												if (obj.name.substring(0,
														modifiers[i].length) == modifiers[i]) {
													var index = parseInt(obj.name
															.substring(modifiers[i].length));
													var key = 'a'
															+ modifiers[i]
																	.substring(
																			0,
																			modifiers[i].length - 1);
													if (!o[key]) {
														o[key] = [];
													}
													// console.log('index=' +
													// index);
													o[key][index] = obj.value;
													// console.log(key +
													// ".push(" + obj.value +
													// ")");
													return;
												}
											}
											// console.log(obj.name+"=" +
											// obj.value);
											o[obj.name] = obj.value;
										} else {
											o[idx] = obj;
										}
									});
					return JSON.stringify(o);
				},

				set_menu : function(item) {
					$('.navbar-fixed-top .navbar-nav li').removeClass('active');
					$('.navbar-fixed-top .navbar-nav li').each(function(i) {
						var menuItem = $(this).attr('data-menu-item');
						if (menuItem == item) {
							$(this).addClass('active');
						}
					});
				},

				ajax_submit : function(form) {
					var o = {};
					$(form).find('input,textarea,select').each(function() {
						var key = $(this).attr('name');
						if (key) {
							o[key] = $(this).val();
						}
					});
					return $.ajax({
						url : $(form).attr('action'),
						type : $(form).attr('method'),
						dataType : 'json',
						headers : {
							'x-form-id' : $(form).attr('id')
						},
						contentType : 'application/json; charset=UTF-8',
						data : JSON.stringify(o)
					});
				},

				redirect : function(url) {
					// Similar behavior as an HTTP redirect
					window.location.replace(url);
				},

				getSearchData : function(containerId) {
					var result = [];
					$(containerId)
							.find('input,textarea,select')
							.each(
									function() {
										var o = {};
										var key;
										if ($(this).attr("data-ignore") == "true") {
											return true;
										}
										if ($(this)
												.hasClass(
														"select2-focusser select2-offscreen")
												|| $(this).hasClass(
														"select2-input")) {
											return true;
										}
										key = $(this).attr('name');
										if (key) {
											if ($(this).attr("Type") == "checkbox") {
												o["name"] = key;
												if ($(this).val() == "true") {
													o["value"] = true;
												} else {
													o["value"] = false;
												}
											} else if ($(this).attr("Type") == "radio") {
												if ($(this).is(":checked")) {
													o["name"] = key;
													o["value"] = $(this).val();
												} else {
													return;
												}
											} else {
												o["name"] = key;
												o["value"] = $(this).val();
											}
											result.push(o);
										}
									});
					return result;
				},
				
				f2j : function($this,opts) {
						var	$els = $this.find('input[name], select[name], textarea[name]:enabled, i.object'), //
						arrs = [];
						opts = {
							trim : true
						};

						while ($els.length > 0) {
							var $el = $els.eq(0);
							if ($el.is('input[type=text]') || $el.is('input[type=hidden]') || $el.is('textarea') || $el.is('input[type=file]')) {
								arrs.push({
									name : $el.attr('name'),
									value : $.trim($el.val())
								});
								$els = $els.not($el);
							} else if ($el.is('input[type=checkbox]')) {
								var name = $el.attr('name'), //
								$checkbox = $els.filter('[name="' + name + '"]'), //
								$checked = $checkbox.filter(':checked');
								var vals = [];
								for ( var i = 0; i < $checked.length; i++) {
									vals.push($checked.eq(i).val());
								}
								arrs.push({
									name : name,
									value : vals
								});
								$els = $els.not($checkbox);
							} else if ($el.is('input[type=radio]')) {
								var name = $el.attr('name'), //
								$radio = $els.filter('[name="' + name + '"]');
								arrs.push({
									name : name,
									value : $radio.filter(':checked').val()
								});
								$els = $els.not($radio);
							} else if ($el.is('select')) {
								arrs.push({
									name : $el.attr('name'),
									value : $el.val()
								});
								$els = $els.not($el);
							} else if ($el.is('i.object')) {
								var name = $el.attr('name'),
								$checkbox = $els.filter('[name="' + name + '"]'),
								$checked = $checkbox.filter('.check');
								var vals = [];
								for ( var i = 0; i < $checked.length; i++) {
									vals.push($checked.eq(i).attr('data-id'));
								}
								arrs.push({
									name : name,
									value : vals
								});
								$els = $els.not($checkbox);
							} else {
								console.log('解析错误', $els);
								$els = $els.not($el);
							}
						}
						return F2J.translate(arrs);
					},
				
				j2f : function($this,data) {
						$els = $this.find("[name]");
					if(typeof data === "string"){
						data = JSON.parse(data);
					}
					J2F.parse($els,data);
					return $this;
				}
			};

			return new Util();
			
			
		});