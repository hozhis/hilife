<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/layer-mobile/css/layer.mobile-1.7.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/life/index.css"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript">
	define('global',{
		context : '${rc.contextPath}',
		base_url : '${rc.contextPath}',
		locale : 'zh_CN',
		static_url : '${contextPath}',
		token : '${token}'
	})
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
</head>
<body>
<div class="main-wrapper" data-role="none">
	<div class="header">
		<div class="left"></div>
		<div class="right"><span class="fui-shopping_cart"></span></div>
		<div class="title"><label>生活</label></div>
	</div>
	<div class="content">
		<div class="content-search" data-role="none">
			<input class="search" type="text" placeholder="" id="searchbox" name="searchbox"  data-role="none">
        	<button class="search-btn" type="submit" data-role="none"><span class="fui-search"></span></button>
		</div>
		<!-- Bootstrap 轮播（Carousel）插件 -->
		<div id="carousel-hilife" class="carousel slide" data-ride="carousel" data-interval="4000">
			<ol class="carousel-indicators"></ol>
			<div class="carousel-inner" role="listbox"></div>
		</div>
		<div class="content-body">
			<div class="gap"></div>
			<div class="products">
				<div id="" class="product-title">精品推荐<a target="_top" href="${contextPath}/web/goods/index?onsaleType=1&token=${token}">更多<span class="fui-keyboard_arrow_right"></span></a></div>
				<div class="product-item">
					<ul>
						<#list promotes as p>
							<#if p.promoteTypeId == 1>
							<li class="item-d"><a href="${contextPath}/web/goods/detail/${p.productId}?token=${token}" target="_top"><div><img src="${p.productDto.image}"></div><span>${p.productDto.productName}</span></a></li>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="products">
				<div id="" class="product-title">最新上市<a target="_top" href="${contextPath}/web/goods/index?onsaleType=3&token=${token}">更多<span class="fui-keyboard_arrow_right"></span></a></div>
				<div class="product-item">
					<ul>
						<#list promotes as p>
							<#if p.promoteTypeId == 3>
							<li class="item-d"><a href="${contextPath}/web/goods/detail/${p.productId}?token=${token}" target="_top"><div><img src="${p.productDto.image}"></div><span>${p.productDto.productName}</span></a></li>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="products">
				<div id="" class="product-title">热销好评<a target="_top" href="${contextPath}/web/goods/index?onsaleType=2&token=${token}">更多<span class="fui-keyboard_arrow_right"></span></a></div>
				<div class="product-item">
					<ul>
						<#list promotes as p>
							<#if p.promoteTypeId == 2>
							<li class="item-d"><a href="${contextPath}/web/goods/detail/${p.productId}?token=${token}" target="_top"><div><img src="${p.productDto.image}"></div><span>${p.productDto.productName}</span></a></li>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="categories">
				<div class="category-title"><span class="fui-list-columned"></span>所有分类</div>
				<div class="category">
					<ul>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=2&token=${token}"><span class="fui-cherry"></span>水果</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=3&token=${token}"><span class="fui-eggplant"></span>蔬菜</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=4&token=${token}"><span class="fui-drink"></span>饮品&nbsp;&&nbsp;酒</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=5&token=${token}"><span class="fui-rice"></span>米面粗粮</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=6&token=${token}"><span class="fui-oil"></span>食用油</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=7&token=${token}"><span class="fui-meat"></span>鱼肉类</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=8&token=${token}"><span class="fui-jiangyou"></span>调味品</a></li>
						<li><a target="_top" href="${contextPath}/web/goods/index?typeId=9&token=${token}"><span class="fui-food"></span>休闲食品</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="gap2"></div>
	<div class="footer">
		<ul>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/product/index?token=${token}"><span class="home3"></span>首页</a></div></li>
			<li><div class="f-child"><a target="_top" class="active" href="${contextPath}/web/life/index?token=${token}"><span class="local_cafe"></span>生活</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/order/index?token=${token}"><span class="dns"></span>订单</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/me/index?token=${token}"><span class="person"></span>我</a></div></li>
		</ul>
		<div class="clear"></div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/life/index.js" type="text/javascript"></script>
</html>
