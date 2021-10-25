function getXMLHttpRequest() {
	try {
		return new XMLHttpRequest;
	} catch (e) {
		// TODO handle the exception
		try {
			return new ActiveXObject("Msxml2.XMLHTTOP");
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTOP");

			} catch (e) {
				alert("啊--我不能认识你要找的浏览器");
				throw e;
			}
		}
	}
}

// ��װajax

/*
 * ���� method get/post; �����ַ url �Ƿ��첽 ansy true; ���� params ���� ���� type �ɹ�����
 * success ����һ��������
 */
function ajax(opt) {

	var http = getXMLHttpRequest();

	if (!opt.method) {
		opt.method = "GET";
	}

	if (!opt.ansy) {
		opt.ansy = true;
	}

	http.open(opt.method, opt.url, opt.ansy);
	if (opt.method == "POST") {
		http.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

	}

	http.send(opt.params);
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			var content;
			if (!opt.type) {
				content = http.responseText;
			} else if (opt.type == "text") {
				content = http.responseText;

			} else if (opt.type == "XML") {
				content = http.responseXML;
			} else if (opt.type == "json") {
				var t = http.responseText;
				content = eval("(" + t + ")");
			   
			}
                
			opt.success(content);

		}

	}

}