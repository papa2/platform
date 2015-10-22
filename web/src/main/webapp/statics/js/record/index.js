// 百度地图API功能
function loadJScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=57GsXSutGfXcANaX3GfW1FqZ&callback=init";
	document.body.appendChild(script);
}

function init() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js";
	document.body.appendChild(script);

	var data_info = JSON.parse(parkList);

	// 百度地图API功能
	var map = new BMap.Map("allmap");

	map.centerAndZoom(new BMap.Point(data_info[0].lon, data_info[0].lat), 15);

	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
				// 靠左上角位置
				anchor : BMAP_ANCHOR_TOP_LEFT,
				// LARGE类型
				type : BMAP_NAVIGATION_CONTROL_LARGE,
				// 启用显示定位
				enableGeolocation : true
			});
	map.addControl(navigationControl);

	// 添加定位控件
	var geolocationControl = new BMap.GeolocationControl();
	geolocationControl.addEventListener("locationError", function(e) {
				// 定位失败事件
				alert(e.message);
			});
	map.addControl(geolocationControl);

	for (var i = 0; i < data_info.length; i++) {
		var marker = new BMap.Marker(new BMap.Point(data_info[i].lon,
				data_info[i].lat)); // 创建标注
		var content = "<div><strong>地址：" + data_info[i].address
				+ "</strong><div style='margin: 3px 0 3px 0;'>空余车位："
				+ "<a href='javascript:detail(" + data_info[i].parkCode
				+ ");'>" + (600 - data_info[i].occupy)
				+ "</a></div><div>可预约车位：2</div></div>";
		map.addOverlay(marker); // 将标注添加到地图中
		addClickHandler(data_info[i].parkName, content, marker);
	}

	function addClickHandler(title, content, marker) {
		marker.addEventListener("click", function(e) {
					openInfo(title, content, marker, e)
				});
	}

	function openInfo(title, content, marker, e) {
		// 创建检索信息窗口对象
		var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
					title : title, // 标题
					enableAutoPan : true, // 自动平移
					searchTypes : []
				});

		searchInfoWindow.open(marker);
	}
}

window.onload = loadJScript; // 异步加载地图

function detail(parkCode) {
	loading();
	top.location.href = appUrl + "/record/detail.htm?parkCode=" + parkCode;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
