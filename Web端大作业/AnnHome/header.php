<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link href="<?php bloginfo('template_url'); ?>/style.css" rel="stylesheet" />
		<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
		<title>
			<?php
			if (is_home()) {
				bloginfo('name');
				echo " - ";
				bloginfo('description');
			} elseif (is_category()) {
				single_cat_title();
				echo " - ";
				bloginfo('name');
			} elseif (is_single() || is_page()) {
				single_post_title();
			} elseif (is_search()) {
				echo "搜索结果";
				echo " - ";
				bloginfo('name');
			} elseif (is_404()) {
				echo '页面未找到!';
			} else {
				wp_title('', true);
			}
 ?>
		</title>
		<link rel="alternate" type="application/rss+xml" title="RSS 2.0 - 所有文章" href="<?php echo get_bloginfo('rss2_url'); ?>" />
		<link rel="alternate" type="application/rss+xml" title="RSS 2.0 - 所有评论" href="<?php bloginfo('comments_rss2_url'); ?>" />
		<?php wp_head(); ?>
	</head>
	<?php flush(); ?>
	<body>
		<header class="navbar navbar-default navbar-fixed-top">
			<!--inverse-->
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<?php echo get_option('home'); ?>"><?php bloginfo('name'); ?></a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<!--导航条-->
					<ul class="nav navbar-nav">
						<ul class="nav navbar-nav nav-pills">
							<li role="presentation" <?php
							if (is_home()) { echo 'class="active"';
							}
 ?>><a href="<?php echo get_option('home'); ?>/">首页</a></li>
							<?php wp_list_categories('depth=1&title_li=0&sort_column=menu_order'); ?>
								<!--<?php
$args=array(
'orderby' => 'name',
'order' => 'ASC',
'child_of'=>10
);
$categories=get_categories($args);
echo ' <li class="dropdown" role="presentation">';
foreach($categories as $category) {
echo ' <lu class="dropdown-menu">';
echo ' <a href="' . get_category_link( $category->term_id ) . '" title="' . $category->name . '" ' . '>' . $category->name.' </a>';
echo ' </li>';
}
?>-->
							<?php wp_list_pages('depth=1&title_li=0&sort_column=menu_order'); ?>
						</ul>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">分享<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Tencent</a></li>
								<li><a href="#">Sina</a></li>
								<li><a href="#">Wechat</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">Github</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</header>