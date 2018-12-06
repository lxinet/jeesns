/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
    config.language = 'zh-cn';
    config.height = 300;
    config.toolbarCanCollapse = true;
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbar = [
        { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike' ] },
        { name: 'links', items: [ 'Link', 'Unlink' ] },
        { name: 'insert', items: [ 'Image', 'Flash', 'Table' ] },
        { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
        { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'JustifyLeft', 'JustifyCenter', '-', 'Blockquote', 'CodeSnippet' ] },
        { name: 'styles', items: [ 'Format','FontSize'] },
        { name: 'tools', items: [ 'Source', 'Maximize' ] }
    ];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
    config.uiColor = '#ffffff';
    config.resize_dir = 'vertical';
    config.enterMode = 2;
    config.defaultLanguage = 'zh-cn';
    config.skin = 'moono-lisa';
    // //添加插件，多个插件用逗号隔开
    config.extraPlugins = 'codesnippet,colorbutton,font';
    config.allowedContent= false;

    config.codeSnippet_languages = {
        apache       : 'Apache',
        bash         : 'Bash',
        cs           : 'C#',
        cpp          : 'C++',
        css          : 'CSS',
        coffeescript : 'CoffeeScript',
        delphi       : 'Delphi',
        diff         : 'Diff',
        go           : 'Go',
        groovy       : 'Groovy',
        html         : 'HTML',
        http         : 'HTTP',
        ini          : 'Ini',
        json         : 'JSON',
        java         : 'Java',
        javascript   : 'JavaScript',
        lua          : 'lua',
        makefile     : 'Makefile',
        markdown     : 'Markdown',
        nginx        : 'Nginx',
        objectivec   : 'Objective-C',
        php          : 'PHP',
        perl         : 'Perl',
        python       : 'Python',
        ruby         : 'Ruby',
        scala        : 'Scala',
        scss         : 'SCSS',
        sql          : 'SQL',
        swift        : 'Swift',
        vbscript     : 'VBScript',
        xml          : 'XML'
    };

    config.image_previewText = "JEESNS是一款基于JAVA企业级平台研发的社交管理系统，依托企业级JAVA的高效、安全、稳定等优势，开创国内JAVA版开源SNS先河，JEESNS可以用来搭建门户、论坛、社区、微博、知识付费平台等。";
    // 图片上传配置
    config.filebrowserUploadUrl = basePath + '/ckeditorUpload/uploadImage';
    config.filebrowserImageUploadUrl = basePath + '/ckeditorUpload/uploadImage';
    config.filebrowserFlashUploadUrl = basePath + '/ckeditorUpload/uploadImage';
};
