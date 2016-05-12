<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/goods/index.css"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript">
	define('global',{
		context : '${rc.contextPath}',
		base_url : '${rc.contextPath}',
		locale : 'zh_CN',
		static_url : '${contextPath}',
		token : '${token}',
		product : '${product}'
	})
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
</head>
<body>
	<div class="main-wrapper">
		<div class="header">
			<div class="left" style="width:30px;"><span class="fui-arrow-left2"></span></div>
			<div class="right" style="width:30px;"></div>
			<div class="title" style="max-width:90%;padding: 0 10px;"><#if searchStr??><input id="search" type="text" value="${searchStr}"><span class="icon-search"></span></#if>
					<#if onsaleType == 1><label>精品推荐</label>
					<#elseif onsaleType == 2><label>热销好评</label>
					<#elseif onsaleType == 3><label>新品上市</label>
					<#else><label>${productType}</label></#if></div>
		</div>
		<div class="content">
			<div class="content-title">
				<ul>
					<li id="all" class="active" data-id="productId">全部</li>
					<li id="price" data-id="price">价格<span class="fui-triangle-up-small"></span><span class="fui-triangle-down-small"></span></li>
					<li id="saleAmount" data-id="saleAmount">销量<span class="fui-triangle-up-small"></span><span class="fui-triangle-down-small"></span></li>
					<li id="reviews" data-id="reviews">人气<span class="fui-triangle-up-small"></span><span class="fui-triangle-down-small"></span></li>
				</ul>
			</div>
			<div class="content-body">
				<div class="gap"></div>
				<ul class="product-ul">
					<#list product as p>
						<#if onsaleType??>
							<li data-id="${p.productId}">
								<a href="${contextPath}/web/goods/detail/${p.productId}?token=${token}" target="_top">
									<div class="product-item">
										<div class="item-image"><img src="${p.productDto.image}"></div>
										<div class="item-desc"><span>￥${p.productDto.price/100}<label>已售：${p.productDto.saleAmount}</label></span><span>${p.productDto.productName}</span></div>
									</div>
								</a>
							</li>
						<#else>
							<li data-id="${p.productId}">
								<a href="${contextPath}/web/goods/detail/${p.productId}?token=${token}" target="_top">
									<div class="product-item">
										<div class="item-image"><img src="${p.image}"></div>
										<div class="item-desc"><span>￥${p.price/100}<label>已售：${p.saleAmount}</label></span><span>${p.productName}</span></div>
									</div>
								</a>
							</li>
						</#if>
					</#list>
				</ul>
				<div class="progress hide"><span class="fui-spin5 animate-spin"></span></div>
			</div>
		</div>
		<div class="hide">
			<input id="onsaleType" value="${onsaleType}">
			<input id="typeId" value="${typeId}">
			<input id="searchStr" value="${searchStr}">
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/goods/index.js" type="text/javascript"></script>
</html>