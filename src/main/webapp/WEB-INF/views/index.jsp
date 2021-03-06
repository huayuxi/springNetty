<%@ page import="com.air.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String wsPath = request.getServerName();
  String id = StringUtils.getMD5(request.getSession().getId());

%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>车森林</title>
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link rel="stylesheet" href="<%=basePath %>css/sm.css">
  <link rel="stylesheet" href="<%=basePath %>/css/sm-extend.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/global.css?170122002">
</head>

<body>
<div class="page-group">
  <div class="page" id="device-list">
    <header class="bar bar-nav">
      <a class="icon icon-code pull-right" href="#device-add" ></a>
      <h1 class="title">设备列表</h1>
    </header>
    <div class="content" id="devices">
    </div>
    <nav class="bar bar-tab">
      <a class="tab-item external active" href="#" menu-data="device-list">
        <span class="icon icon-home"></span>
        <span class="tab-label">设备列表</span>
      </a>
      <a class="tab-item external" href="#" menu-data="device-his">
        <span class="icon icon-me"></span>
        <span class="tab-label">历史记录</span>
        <span class="badge">2</span>
      </a>
      <a class="tab-item external" href="#" menu-data="device-set">
        <span class="icon icon-star"></span>
        <span class="tab-label">常用设置</span>
      </a>
    </nav>
  </div>
  <div class="page" id="device-info">
    <header class="bar bar-nav">
      <a class="icon icon-left pull-left pull-left back" href="#device-list"></a>
      <h1 class="title" id="device-info-title"></h1>
    </header>
    <div class="content d-info" id="device-info-uid">
      <div class="row d-bg">
        <span class="a-text">优</span>
        <span class="m-text">手动运行</span>
        <span class="s-text">非常棒的空气！</span>
      </div>
      <div class="row d-status">
        <div class="col-33">
          <span>甲醛：100</span>
        </div>
        <div class="col-33">
          <span>PM2.5：100</span>
        </div>
        <div class="col-33">
          <span>温度：70</span>
        </div>
        <div class="col-33">
        </div>
      </div>
      <div class="row d-status">
        <div class="col-33">
          <span>湿度：80</span>
        </div>
        <div class="col-33">
          <span>电量：100</span>
        </div>
        <div class="col-33">
        </div>
      </div>
      <div class="row d-btns">
        <div class="col-25">
          <span class="icon" id="openFan">开</span>
        </div>
        <div class="col-25">
          <span class="icon" id="closeFan">关</span>
        </div>
        <div class="col-25">
          <span class="icon">调速</span>
        </div>
        <div class="col-25">
          <span class="icon">离子</span>
        </div>
      </div>

    </div>
    <nav class="bar bar-tab">
      <a class="tab-item external active" href="#" menu-data="device-list">
        <span class="icon icon-home"></span>
        <span class="tab-label">设备列表</span>
      </a>
      <a class="tab-item external" href="#" menu-data="device-his">
        <span class="icon icon-me"></span>
        <span class="tab-label">历史记录</span>
        <span class="badge">2</span>
      </a>
      <a class="tab-item external" href="#" menu-data="device-set">
        <span class="icon icon-star"></span>
        <span class="tab-label">常用设置</span>
      </a>
    </nav>
  </div>
  <div class="page" id="device-his">
    <header class="bar bar-nav">
      <h1 class="title">历史记录</h1>
    </header>
    <nav class="bar bar-tab">
      <a class="tab-item external " href="#" menu-data="device-list">
        <span class="icon icon-home"></span>
        <span class="tab-label">设备列表</span>
      </a>
      <a class="tab-item external active" href="#" menu-data="device-his">
        <span class="icon icon-me"></span>
        <span class="tab-label">历史记录</span>
        <span class="badge">2</span>
      </a>
      <a class="tab-item external" href="#" menu-data="device-set">
        <span class="icon icon-star"></span>
        <span class="tab-label">常用设置</span>
      </a>
    </nav>
  </div>
  <div class="page" id="device-add">
    <header class="bar bar-nav">
      <a class="icon icon-left pull-left pull-left back" href="#device-list"></a>
      <h1 class="title">设备新增</h1>
    </header>
    <div class="content">
      <form id="add-form">
        <div class="list-block">
          <ul>
            <!-- Text inputs -->
            <li>
              <div class="item-content">
                <div class="item-media"><i class="icon icon-form-name"></i></div>
                <div class="item-inner">
                  <div class="item-title label">设备名称</div>
                  <div class="item-input">
                    <input type="text" placeholder="设备名称" name="device_name">
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div class="item-content">
                <div class="item-media"><i class="icon icon-form-email"></i></div>
                <div class="item-inner">
                  <div class="item-title label">设备UID</div>
                  <div class="item-input">
                    <input type="text" placeholder="设备UID" name="device_uid">
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="content-block">
          <div class="row">
            <div class="col-50"><a href="#device-list" class="button button-big button-fill button-danger">取消</a></div>
            <div class="col-50"><a href="#" class="button button-big button-fill button-success">提交</a></div>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="page" id="device-set">
    <header class="bar bar-nav">
      <h1 class="title">常用设置</h1>
    </header>
    <nav class="bar bar-tab">
      <a class="tab-item external " href="#" menu-data="device-list">
        <span class="icon icon-home"></span>
        <span class="tab-label">设备列表</span>
      </a>
      <a class="tab-item external" href="#" menu-data="device-his">
        <span class="icon icon-me"></span>
        <span class="tab-label">历史记录</span>
        <span class="badge">2</span>
      </a>
      <a class="tab-item external active" href="#" menu-data="device-set">
        <span class="icon icon-star"></span>
        <span class="tab-label">常用设置</span>
      </a>
    </nav>
  </div>
</div>
<script type='text/javascript' src='<%=basePath %>/js/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='<%=basePath %>/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='<%=basePath %>/js/sm-extend.min.js' charset='utf-8'></script>
<script type="text/javascript" src="<%=basePath %>/js/index.js?170123003"></script>
</body>

</html>

