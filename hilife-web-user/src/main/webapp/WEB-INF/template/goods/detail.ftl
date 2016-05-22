<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/goods/detail.css"/>
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
	<div class="main-wrapper">
		<div class="header">
			<div class="left"><span class="fui-arrow-left2"></span></div>
			<div class="right"><span class="fui-shopping_cart"></span><span class="fui-home3"></span></div>
			<div class="title"><label>商品详情</label></div>
		</div>
		<div class="content">
			<div class="product-img">
				<img src="${product.image}">
			</div>
			<div class="product-info">
				<h1>${product.productName}</h1>
				<h2>由 好生活平台 发货并提供售后服务</h2>
				<div class="price">
					<div class="price-left">零售价</div>
					<div class="price-middle">￥<strong>${(product.price/100)?string('0.00')}</strong></div>
					<div class="price-right">已售：${product.saleAmount}</div>
				</div>
				<div class=""></div>
			</div>
			<div class="gap"></div>
			<div class="product-amount">
				<label>商品数量：</label>
				<button data-role="none" id="minus">-</button><input data-role="none" value="1" type="tel"><button id="plus" data-role="none">+</button>
			</div>
			<div class="product-rest">
				<label>商品库存：</label><label id="rest-num">${product.mount}</label>
			</div>
			<div class="product-option">
				<button>立即购买</button>
				<button>加入购物车</button>
			</div>
			<div class="gap"></div>
			<div class="product-desc">
				<ul id="p-tap">
					<li class="active">详情</li>
					<li>评价</li>
				</ul>
				<div id="page1" class="" style="height:auto;">${product.introduction}</div>
				<div id="page2" class="hide" style="">
					<ul id="tap-list">
						<li class="on">全部</li>
					</ul>
					<ul>
						<li>
							<div class="userinfo"><span>马天宇</span></div>
							<div class="evalcon">之前购买过，现在是回购，宝贝非常好，无添加无荧光剂无漂白，电视上说过国内牌子大多有荧光剂，只是多少的问题，所以现在都会购买信得过的外国牌子用了。我曾长期居住在国外，北欧对食品安全的控制非常严格，甚至比北美更严格，而这种严谨是有延伸性的，所以我还是挺信任这个牌子的。这次购买体验非常棒，商家还送了小礼物，很实用。客服香香很帮忙，人很好哦，我问很多问题都有耐心回答，谢啦。</div>
							<ul id="evalpiclist" class="evalpiclist">
								<li><img src="${contextPath}/assets/img/lemon.jpg"></li>
								<li><img src="${contextPath}/assets/img/peach.jpg"></li>
							</ul>
							<div class="otherinfo"><span>2016-03-01</span></div>
						</li>
						<li>
							<div class="userinfo"><span>杨洋</span></div>
							<div class="evalcon">宝贝收到了，很好，很喜欢，而且之前就用过这个牌子，效果很不错，吸水效果特别好，比国内的一些牌子好多了，强烈推荐大家可以试试，女人的必备！，值得购买。 客服小文服务态度很好 值得表扬。</div>
							<ul id="evalpiclist" class="evalpiclist">
								<li><img src="${contextPath}/assets/img/kiwi.jpg"></li>
								<li><img src="http://www.maigusoft.com/image/fish.jpg"></li>
							</ul>
							<div class="otherinfo"><span>2016-03-01</span></div>
						</li>
					</ul>
					<div class="pagination"></div>
				</div>
			</div>
			<div class="gap2"></div><div class="gap2"></div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/goods/detail.js" type="text/javascript"></script>
</html>