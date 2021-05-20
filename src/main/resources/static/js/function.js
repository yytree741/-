var isIE = !!document.all;
//Selector
function bindSelector()
{
	for(var i=0; i<arguments.length; i++) {
		document.getElementById(arguments[i]).onmouseover = function(){
			this.getElementsByTagName("ul")[0].style.display = "block";
		}
		document.getElementById(arguments[i]).onmouseout = function(){
			this.getElementsByTagName("ul")[0].style.display = "none";
		}
	}
}

function getStyle(el, name)
{
	return isIE ? el.currentStyle[name] : window.getComputedStyle(el, null)[name];
}

function getStreet(){
    $.getJSON("city/json/list",{"province.id":$('#province_id').val()},function (data) {
        var html = '';
        for(var i=0;i<data.length;i++){
            html += '<option value="';
            html += data[i].id;
            html += '">';
            html += data[i].name
            html += '</option>';
        }
        $('#city_id').html(html);
    });
}

$(function () {
    $.getJSON("toys/json/list",null,function (data) {
        var html = '';
        for(var i=0;i<data.length;i++){
            html += '<option value="';
            html += data[i].id;
            html += '">';
            html += data[i].name
            html += '</option>';
        }
        $('#toys_id').html(html);
    });
    $.getJSON("district/json/list",null,function (data) {
        var html = '';
        for(var i=0;i<data.length;i++){
            html += '<option value="';
            html += data[i].id;
            html += '">';
            html += data[i].name
            html += '</option>';
        }
        $('#province_id').html(html);
        getStreet();
    });
    $('#province_id').change(getStreet);
});