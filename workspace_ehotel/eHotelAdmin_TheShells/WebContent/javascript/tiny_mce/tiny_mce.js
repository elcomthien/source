
(function (d) {
	var a = /^\s*|\s*$/g, e, c = "B".replace(/A(.)|B/, "$1") === "$1";
	var b = {majorVersion:"3", minorVersion:"4b2", releaseDate:"2011-01-13", _init:function () {
		var s = this, q = document, o = navigator, g = o.userAgent, m, f, l, k, j, r;
		s.isOpera = d.opera && opera.buildNumber;
		s.isWebKit = /WebKit/.test(g);
		s.isIE = !s.isWebKit && !s.isOpera && (/MSIE/gi).test(g) && (/Explorer/gi).test(o.appName);
		s.isIE6 = s.isIE && /MSIE [56]/.test(g);
		s.isGecko = !s.isWebKit && /Gecko/.test(g);
		s.isMac = g.indexOf("Mac") != -1;
		s.isAir = /adobeair/i.test(g);
		s.isIDevice = /(iPad|iPhone)/.test(g);
		if (d.tinyMCEPreInit) {
			s.suffix = tinyMCEPreInit.suffix;
			s.baseURL = tinyMCEPreInit.base;
			s.query = tinyMCEPreInit.query;
			return;
		}
		s.suffix = "";
		f = q.getElementsByTagName("base");
		for (m = 0; m < f.length; m++) {
			if (r = f[m].href) {
				if (/^https?:\/\/[^\/]+$/.test(r)) {
					r += "/";
				}
				k = r ? r.match(/.*\//)[0] : "";
			}
		}
		function h(p) {
			if (p.src && /tiny_mce(|_gzip|_jquery|_prototype|_full)(_dev|_src)?.js/.test(p.src)) {
				if (/_(src|dev)\.js/g.test(p.src)) {
					s.suffix = "_src";
				}
				if ((j = p.src.indexOf("?")) != -1) {
					s.query = p.src.substring(j + 1);
				}
				s.baseURL = p.src.substring(0, p.src.lastIndexOf("/"));
				if (k && s.baseURL.indexOf("://") == -1 && s.baseURL.indexOf("/") !== 0) {
					s.baseURL = k + s.baseURL;
				}
				return s.baseURL;
			}
			return null;
		}
		f = q.getElementsByTagName("script");
		for (m = 0; m < f.length; m++) {
			if (h(f[m])) {
				return;
			}
		}
		l = q.getElementsByTagName("head")[0];
		if (l) {
			f = l.getElementsByTagName("script");
			for (m = 0; m < f.length; m++) {
				if (h(f[m])) {
					return;
				}
			}
		}
		return;
	}, is:function (g, f) {
		if (!f) {
			return g !== e;
		}
		if (f == "array" && (g.hasOwnProperty && g instanceof Array)) {
			return true;
		}
		return typeof (g) == f;
	}, makeMap:function (f, j, h) {
		var g;
		f = f || [];
		j = j || ",";
		if (typeof (f) == "string") {
			f = f.split(j);
		}
		h = h || {};
		g = f.length;
		while (g--) {
			h[f[g]] = {};
		}
		return h;
	}, each:function (j, f, h) {
		var k, g;
		if (!j) {
			return 0;
		}
		h = h || j;
		if (j.length !== e) {
			for (k = 0, g = j.length; k < g; k++) {
				if (f.call(h, j[k], k, j) === false) {
					return 0;
				}
			}
		} else {
			for (k in j) {
				if (j.hasOwnProperty(k)) {
					if (f.call(h, j[k], k, j) === false) {
						return 0;
					}
				}
			}
		}
		return 1;
	}, map:function (g, h) {
		var j = [];
		b.each(g, function (f) {
			j.push(h(f));
		});
		return j;
	}, grep:function (g, h) {
		var j = [];
		b.each(g, function (f) {
			if (!h || h(f)) {
				j.push(f);
			}
		});
		return j;
	}, inArray:function (g, h) {
		var j, f;
		if (g) {
			for (j = 0, f = g.length; j < f; j++) {
				if (g[j] === h) {
					return j;
				}
			}
		}
		return -1;
	}, extend:function (k, j) {
		var h, g, f = arguments;
		for (h = 1, g = f.length; h < g; h++) {
			j = f[h];
			b.each(j, function (l, m) {
				if (l !== e) {
					k[m] = l;
				}
			});
		}
		return k;
	}, trim:function (f) {
		return (f ? "" + f : "").replace(a, "");
	}, create:function (q, f, k) {
		var o = this, g, j, l, m, h, n = 0;
		q = /^((static) )?([\w.]+)(:([\w.]+))?/.exec(q);
		l = q[3].match(/(^|\.)(\w+)$/i)[2];
		j = o.createNS(q[3].replace(/\.\w+$/, ""), k);
		if (j[l]) {
			return;
		}
		if (q[2] == "static") {
			j[l] = f;
			if (this.onCreate) {
				this.onCreate(q[2], q[3], j[l]);
			}
			return;
		}
		if (!f[l]) {
			f[l] = function () {
			};
			n = 1;
		}
		j[l] = f[l];
		o.extend(j[l].prototype, f);
		if (q[5]) {
			g = o.resolve(q[5]).prototype;
			m = q[5].match(/\.(\w+)$/i)[1];
			h = j[l];
			if (n) {
				j[l] = function () {
					return g[m].apply(this, arguments);
				};
			} else {
				j[l] = function () {
					this.parent = g[m];
					return h.apply(this, arguments);
				};
			}
			j[l].prototype[l] = j[l];
			o.each(g, function (p, r) {
				j[l].prototype[r] = g[r];
			});
			o.each(f, function (p, r) {
				if (g[r]) {
					j[l].prototype[r] = function () {
						this.parent = g[r];
						return p.apply(this, arguments);
					};
				} else {
					if (r != l) {
						j[l].prototype[r] = p;
					}
				}
			});
		}
		o.each(f["static"], function (p, r) {
			j[l][r] = p;
		});
		if (this.onCreate) {
			this.onCreate(q[2], q[3], j[l].prototype);
		}
	}, walk:function (j, h, k, g) {
		g = g || this;
		if (j) {
			if (k) {
				j = j[k];
			}
			b.each(j, function (l, f) {
				if (h.call(g, l, f, k) === false) {
					return false;
				}
				b.walk(l, h, k, g);
			});
		}
	}, createNS:function (j, h) {
		var g, f;
		h = h || d;
		j = j.split(".");
		for (g = 0; g < j.length; g++) {
			f = j[g];
			if (!h[f]) {
				h[f] = {};
			}
			h = h[f];
		}
		return h;
	}, resolve:function (j, h) {
		var g, f;
		h = h || d;
		j = j.split(".");
		for (g = 0, f = j.length; g < f; g++) {
			h = h[j[g]];
			if (!h) {
				break;
			}
		}
		return h;
	}, addUnload:function (k, j) {
		var h = this;
		k = {func:k, scope:j || this};
		if (!h.unloads) {
			function g() {
				var f = h.unloads, m, p;
				if (f) {
					for (p in f) {
						m = f[p];
						if (m && m.func) {
							m.func.call(m.scope, 1);
						}
					}
					if (d.detachEvent) {
						d.detachEvent("onbeforeunload", l);
						d.detachEvent("onunload", g);
					} else {
						if (d.removeEventListener) {
							d.removeEventListener("unload", g, false);
						}
					}
					h.unloads = m = f = w = g = 0;
					if (d.CollectGarbage) {
						CollectGarbage();
					}
				}
			}
			function l() {
				var m = document;
				if (m.readyState == "interactive") {
					function f() {
						m.detachEvent("onstop", f);
						if (g) {
							g();
						}
						m = 0;
					}
					if (m) {
						m.attachEvent("onstop", f);
					}
					d.setTimeout(function () {
						if (m) {
							m.detachEvent("onstop", f);
						}
					}, 0);
				}
			}
			if (d.attachEvent) {
				d.attachEvent("onunload", g);
				d.attachEvent("onbeforeunload", l);
			} else {
				if (d.addEventListener) {
					d.addEventListener("unload", g, false);
				}
			}
			h.unloads = [k];
		} else {
			h.unloads.push(k);
		}
		return k;
	}, removeUnload:function (j) {
		var g = this.unloads, h = null;
		b.each(g, function (k, f) {
			if (k && k.func == j) {
				g.splice(f, 1);
				h = j;
				return false;
			}
		});
		return h;
	}, explode:function (f, g) {
		return f ? b.map(f.split(g || ","), b.trim) : f;
	}, _addVer:function (g) {
		var f;
		if (!this.query) {
			return g;
		}
		f = (g.indexOf("?") == -1 ? "?" : "&") + this.query;
		if (g.indexOf("#") == -1) {
			return g + f;
		}
		return g.replace("#", f + "#");
	}, _replace:function (h, f, g) {
		if (c) {
			return g.replace(h, function () {
				var l = f, j = arguments, k;
				for (k = 0; k < j.length - 2; k++) {
					if (j[k] === e) {
						l = l.replace(new RegExp("\\$" + k, "g"), "");
					} else {
						l = l.replace(new RegExp("\\$" + k, "g"), j[k]);
					}
				}
				return l;
			});
		}
		return g.replace(h, f);
	}};
	b._init();
	d.tinymce = d.tinyMCE = b;
})(window);
tinymce.create("tinymce.util.Dispatcher", {scope:null, listeners:null, Dispatcher:function (a) {
	this.scope = a || this;
	this.listeners = [];
}, add:function (a, b) {
	this.listeners.push({cb:a, scope:b || this.scope});
	return a;
}, addToTop:function (a, b) {
	this.listeners.unshift({cb:a, scope:b || this.scope});
	return a;
}, remove:function (a) {
	var b = this.listeners, c = null;
	tinymce.each(b, function (e, d) {
		if (a == e.cb) {
			c = a;
			b.splice(d, 1);
			return false;
		}
	});
	return c;
}, dispatch:function () {
	var f, d = arguments, e, b = this.listeners, g;
	for (e = 0; e < b.length; e++) {
		g = b[e];
		f = g.cb.apply(g.scope, d);
		if (f === false) {
			break;
		}
	}
	return f;
}});
(function () {
	var a = tinymce.each;
	tinymce.create("tinymce.util.URI", {URI:function (e, g) {
		var f = this, h, d, c;
		e = tinymce.trim(e);
		g = f.settings = g || {};
		if (/^(mailto|tel|news|javascript|about|data):/i.test(e) || /^\s*#/.test(e)) {
			f.source = e;
			return;
		}
		if (e.indexOf("/") === 0 && e.indexOf("//") !== 0) {
			e = (g.base_uri ? g.base_uri.protocol || "http" : "http") + "://mce_host" + e;
		}
		if (!/^\w*:?\/\//.test(e)) {
			e = (g.base_uri.protocol || "http") + "://mce_host" + f.toAbsPath(g.base_uri.path, e);
		}
		e = e.replace(/@@/g, "(mce_at)");
		e = /^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*):?([^:@]*))?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/.exec(e);
		a(["source", "protocol", "authority", "userInfo", "user", "password", "host", "port", "relative", "path", "directory", "file", "query", "anchor"], function (b, j) {
			var k = e[j];
			if (k) {
				k = k.replace(/\(mce_at\)/g, "@@");
			}
			f[b] = k;
		});
		if (c = g.base_uri) {
			if (!f.protocol) {
				f.protocol = c.protocol;
			}
			if (!f.userInfo) {
				f.userInfo = c.userInfo;
			}
			if (!f.port && f.host == "mce_host") {
				f.port = c.port;
			}
			if (!f.host || f.host == "mce_host") {
				f.host = c.host;
			}
			f.source = "";
		}
	}, setPath:function (c) {
		var b = this;
		c = /^(.*?)\/?(\w+)?$/.exec(c);
		b.path = c[0];
		b.directory = c[1];
		b.file = c[2];
		b.source = "";
		b.getURI();
	}, toRelative:function (b) {
		var c = this, d;
		if (b === "./") {
			return b;
		}
		b = new tinymce.util.URI(b, {base_uri:c});
		if ((b.host != "mce_host" && c.host != b.host && b.host) || c.port != b.port || c.protocol != b.protocol) {
			return b.getURI();
		}
		d = c.toRelPath(c.path, b.path);
		if (b.query) {
			d += "?" + b.query;
		}
		if (b.anchor) {
			d += "#" + b.anchor;
		}
		return d;
	}, toAbsolute:function (b, c) {
		var b = new tinymce.util.URI(b, {base_uri:this});
		return b.getURI(this.host == b.host && this.protocol == b.protocol ? c : 0);
	}, toRelPath:function (g, h) {
		var c, f = 0, d = "", e, b;
		g = g.substring(0, g.lastIndexOf("/"));
		g = g.split("/");
		c = h.split("/");
		if (g.length >= c.length) {
			for (e = 0, b = g.length; e < b; e++) {
				if (e >= c.length || g[e] != c[e]) {
					f = e + 1;
					break;
				}
			}
		}
		if (g.length < c.length) {
			for (e = 0, b = c.length; e < b; e++) {
				if (e >= g.length || g[e] != c[e]) {
					f = e + 1;
					break;
				}
			}
		}
		if (f == 1) {
			return h;
		}
		for (e = 0, b = g.length - (f - 1); e < b; e++) {
			d += "../";
		}
		for (e = f - 1, b = c.length; e < b; e++) {
			if (e != f - 1) {
				d += "/" + c[e];
			} else {
				d += c[e];
			}
		}
		return d;
	}, toAbsPath:function (e, f) {
		var c, b = 0, h = [], d, g;
		d = /\/$/.test(f) ? "/" : "";
		e = e.split("/");
		f = f.split("/");
		a(e, function (j) {
			if (j) {
				h.push(j);
			}
		});
		e = h;
		for (c = f.length - 1, h = []; c >= 0; c--) {
			if (f[c].length == 0 || f[c] == ".") {
				continue;
			}
			if (f[c] == "..") {
				b++;
				continue;
			}
			if (b > 0) {
				b--;
				continue;
			}
			h.push(f[c]);
		}
		c = e.length - b;
		if (c <= 0) {
			g = h.reverse().join("/");
		} else {
			g = e.slice(0, c).join("/") + "/" + h.reverse().join("/");
		}
		if (g.indexOf("/") !== 0) {
			g = "/" + g;
		}
		if (d && g.lastIndexOf("/") !== g.length - 1) {
			g += d;
		}
		return g;
	}, getURI:function (d) {
		var c, b = this;
		if (!b.source || d) {
			c = "";
			if (!d) {
				if (b.protocol) {
					c += b.protocol + "://";
				}
				if (b.userInfo) {
					c += b.userInfo + "@";
				}
				if (b.host) {
					c += b.host;
				}
				if (b.port) {
					c += ":" + b.port;
				}
			}
			if (b.path) {
				c += b.path;
			}
			if (b.query) {
				c += "?" + b.query;
			}
			if (b.anchor) {
				c += "#" + b.anchor;
			}
			b.source = c;
		}
		return b.source;
	}});
})();
(function () {
	var a = tinymce.each;
	tinymce.create("static tinymce.util.Cookie", {getHash:function (d) {
		var b = this.get(d), c;
		if (b) {
			a(b.split("&"), function (e) {
				e = e.split("=");
				c = c || {};
				c[unescape(e[0])] = unescape(e[1]);
			});
		}
		return c;
	}, setHash:function (k, b, g, f, j, c) {
		var h = "";
		a(b, function (e, d) {
			h += (!h ? "" : "&") + escape(d) + "=" + escape(e);
		});
		this.set(k, h, g, f, j, c);
	}, get:function (j) {
		var h = document.cookie, g, f = j + "=", d;
		if (!h) {
			return;
		}
		d = h.indexOf("; " + f);
		if (d == -1) {
			d = h.indexOf(f);
			if (d != 0) {
				return null;
			}
		} else {
			d += 2;
		}
		g = h.indexOf(";", d);
		if (g == -1) {
			g = h.length;
		}
		return unescape(h.substring(d + f.length, g));
	}, set:function (j, b, g, f, h, c) {
		document.cookie = j + "=" + escape(b) + ((g) ? "; expires=" + g.toGMTString() : "") + ((f) ? "; path=" + escape(f) : "") + ((h) ? "; domain=" + h : "") + ((c) ? "; secure" : "");
	}, remove:function (e, b) {
		var c = new Date();
		c.setTime(c.getTime() - 1000);
		this.set(e, "", c, b, c);
	}});
})();
(function () {
	function serialize(o, quote) {
		var i, v, t;
		quote = quote || "\"";
		if (o == null) {
			return "null";
		}
		t = typeof o;
		if (t == "string") {
			v = "\bb\tt\nn\ff\rr\"\"''\\\\";
			return quote + o.replace(/([\u0080-\uFFFF\x00-\x1f\"])/g, function (a, b) {
				i = v.indexOf(b);
				if (i + 1) {
					return "\\" + v.charAt(i + 1);
				}
				a = b.charCodeAt().toString(16);
				return "\\u" + "0000".substring(a.length) + a;
			}) + quote;
		}
		if (t == "object") {
			if (o.hasOwnProperty && o instanceof Array) {
				for (i = 0, v = "["; i < o.length; i++) {
					v += (i > 0 ? "," : "") + serialize(o[i], quote);
				}
				return v + "]";
			}
			v = "{";
			for (i in o) {
				v += typeof o[i] != "function" ? (v.length > 1 ? "," + quote : quote) + i + quote + ":" + serialize(o[i], quote) : "";
			}
			return v + "}";
		}
		return "" + o;
	}
	tinymce.util.JSON = {serialize:serialize, parse:function (s) {
		try {
			return eval("(" + s + ")");
		}
		catch (ex) {
		}
	}};
})();
tinymce.create("static tinymce.util.XHR", {send:function (g) {
	var a, e, b = window, h = 0;
	g.scope = g.scope || this;
	g.success_scope = g.success_scope || g.scope;
	g.error_scope = g.error_scope || g.scope;
	g.async = g.async === false ? false : true;
	g.data = g.data || "";
	function d(j) {
		a = 0;
		try {
			a = new ActiveXObject(j);
		}
		catch (c) {
		}
		return a;
	}
	a = b.XMLHttpRequest ? new XMLHttpRequest() : d("Microsoft.XMLHTTP") || d("Msxml2.XMLHTTP");
	if (a) {
		if (a.overrideMimeType) {
			a.overrideMimeType(g.content_type);
		}
		a.open(g.type || (g.data ? "POST" : "GET"), g.url, g.async);
		if (g.content_type) {
			a.setRequestHeader("Content-Type", g.content_type);
		}
		a.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		a.send(g.data);
		function f() {
			if (!g.async || a.readyState == 4 || h++ > 10000) {
				if (g.success && h < 10000 && a.status == 200) {
					g.success.call(g.success_scope, "" + a.responseText, a, g);
				} else {
					if (g.error) {
						g.error.call(g.error_scope, h > 10000 ? "TIMED_OUT" : "GENERAL", a, g);
					}
				}
				a = null;
			} else {
				b.setTimeout(f, 10);
			}
		}
		if (!g.async) {
			return f();
		}
		e = b.setTimeout(f, 10);
	}
}});
(function () {
	var c = tinymce.extend, b = tinymce.util.JSON, a = tinymce.util.XHR;
	tinymce.create("tinymce.util.JSONRequest", {JSONRequest:function (d) {
		this.settings = c({}, d);
		this.count = 0;
	}, send:function (f) {
		var e = f.error, d = f.success;
		f = c(this.settings, f);
		f.success = function (h, g) {
			h = b.parse(h);
			if (typeof (h) == "undefined") {
				h = {error:"JSON Parse error."};
			}
			if (h.error) {
				e.call(f.error_scope || f.scope, h.error, g);
			} else {
				d.call(f.success_scope || f.scope, h.result);
			}
		};
		f.error = function (h, g) {
			if (e) {
				e.call(f.error_scope || f.scope, h, g);
			}
		};
		f.data = b.serialize({id:f.id || "c" + (this.count++), method:f.method, params:f.params});
		f.content_type = "application/json";
		a.send(f);
	}, "static":{sendRPC:function (d) {
		return new tinymce.util.JSONRequest().send(d);
	}}});
}());
(function (j) {
	var a, g, d, k = /[&\"\u007E-\uFFFF]/g, b = /[<>&\u007E-\uFFFF]/g, f = /[<>&\"\']/g, c = /&(#)?([\w]+);/g;
	g = {"\"":"&quot;", "'":"&#39;", "<":"&lt;", ">":"&gt;", "&":"&amp;"};
	d = {"&lt;":"<", "&gt;":">", "&amp;":"&", "&quot;":"\"", "&apos;":"'"};
	function h(l) {
		var m;
		m = document.createElement("div");
		m.innerHTML = l;
		return m.textContent || m.innerText || l;
	}
	function e(m, p) {
		var n, o, l, q = {};
		if (m) {
			m = m.split(",");
			p = p || 10;
			for (n = 0; n < m.length; n += 2) {
				o = String.fromCharCode(parseInt(m[n], p));
				if (!g[o]) {
					l = "&" + m[n + 1] + ";";
					q[o] = l;
					q[l] = o;
				}
			}
			return q;
		}
	}
	a = e("50,nbsp,51,iexcl,52,cent,53,pound,54,curren,55,yen,56,brvbar,57,sect,58,uml,59,copy,5a,ordf,5b,laquo,5c,not,5d,shy,5e,reg,5f,macr,5g,deg,5h,plusmn,5i,sup2,5j,sup3,5k,acute,5l,micro,5m,para,5n,middot,5o,cedil,5p,sup1,5q,ordm,5r,raquo,5s,frac14,5t,frac12,5u,frac34,5v,iquest,60,Agrave,61,Aacute,62,Acirc,63,Atilde,64,Auml,65,Aring,66,AElig,67,Ccedil,68,Egrave,69,Eacute,6a,Ecirc,6b,Euml,6c,Igrave,6d,Iacute,6e,Icirc,6f,Iuml,6g,ETH,6h,Ntilde,6i,Ograve,6j,Oacute,6k,Ocirc,6l,Otilde,6m,Ouml,6n,times,6o,Oslash,6p,Ugrave,6q,Uacute,6r,Ucirc,6s,Uuml,6t,Yacute,6u,THORN,6v,szlig,70,agrave,71,aacute,72,acirc,73,atilde,74,auml,75,aring,76,aelig,77,ccedil,78,egrave,79,eacute,7a,ecirc,7b,euml,7c,igrave,7d,iacute,7e,icirc,7f,iuml,7g,eth,7h,ntilde,7i,ograve,7j,oacute,7k,ocirc,7l,otilde,7m,ouml,7n,divide,7o,oslash,7p,ugrave,7q,uacute,7r,ucirc,7s,uuml,7t,yacute,7u,thorn,7v,yuml,ci,fnof,sh,Alpha,si,Beta,sj,Gamma,sk,Delta,sl,Epsilon,sm,Zeta,sn,Eta,so,Theta,sp,Iota,sq,Kappa,sr,Lambda,ss,Mu,st,Nu,su,Xi,sv,Omicron,t0,Pi,t1,Rho,t3,Sigma,t4,Tau,t5,Upsilon,t6,Phi,t7,Chi,t8,Psi,t9,Omega,th,alpha,ti,beta,tj,gamma,tk,delta,tl,epsilon,tm,zeta,tn,eta,to,theta,tp,iota,tq,kappa,tr,lambda,ts,mu,tt,nu,tu,xi,tv,omicron,u0,pi,u1,rho,u2,sigmaf,u3,sigma,u4,tau,u5,upsilon,u6,phi,u7,chi,u8,psi,u9,omega,uh,thetasym,ui,upsih,um,piv,812,bull,816,hellip,81i,prime,81j,Prime,81u,oline,824,frasl,88o,weierp,88h,image,88s,real,892,trade,89l,alefsym,8cg,larr,8ch,uarr,8ci,rarr,8cj,darr,8ck,harr,8dl,crarr,8eg,lArr,8eh,uArr,8ei,rArr,8ej,dArr,8ek,hArr,8g0,forall,8g2,part,8g3,exist,8g5,empty,8g7,nabla,8g8,isin,8g9,notin,8gb,ni,8gf,prod,8gh,sum,8gi,minus,8gn,lowast,8gq,radic,8gt,prop,8gu,infin,8h0,ang,8h7,and,8h8,or,8h9,cap,8ha,cup,8hb,int,8hk,there4,8hs,sim,8i5,cong,8i8,asymp,8j0,ne,8j1,equiv,8j4,le,8j5,ge,8k2,sub,8k3,sup,8k4,nsub,8k6,sube,8k7,supe,8kl,oplus,8kn,otimes,8l5,perp,8m5,sdot,8o8,lceil,8o9,rceil,8oa,lfloor,8ob,rfloor,8p9,lang,8pa,rang,9ea,loz,9j0,spades,9j3,clubs,9j5,hearts,9j6,diams,ai,OElig,aj,oelig,b0,Scaron,b1,scaron,bo,Yuml,m6,circ,ms,tilde,802,ensp,803,emsp,809,thinsp,80c,zwnj,80d,zwj,80e,lrm,80f,rlm,80j,ndash,80k,mdash,80o,lsquo,80p,rsquo,80q,sbquo,80s,ldquo,80t,rdquo,80u,bdquo,810,dagger,811,Dagger,81g,permil,81p,lsaquo,81q,rsaquo,85c,euro", 32);
	j.html = j.html || {};
	j.html.Entities = {encodeRaw:function (m, l) {
		return m.replace(l ? k : b, function (n) {
			return g[n] || n;
		});
	}, encodeAllRaw:function (l) {
		return ("" + l).replace(f, function (m) {
			return g[m] || m;
		});
	}, encodeNumeric:function (m, l) {
		return m.replace(l ? k : b, function (n) {
			return g[n] || "&#" + n.charCodeAt(0) + ";";
		});
	}, encodeNamed:function (n, l, m) {
		m = m || a;
		return n.replace(l ? k : b, function (o) {
			return g[o] || m[o] || o;
		});
	}, getEncodeFunc:function (l, o) {
		var p = j.html.Entities;
		o = e(o) || a;
		function m(r, q) {
			return r.replace(q ? k : b, function (s) {
				return g[s] || o[s] || "&#" + s.charCodeAt(0) + ";" || s;
			});
		}
		function n(r, q) {
			return p.encodeNamed(r, q, o);
		}
		l = j.makeMap(l.replace(/\+/g, ","));
		if (l.named && l.numeric) {
			return m;
		}
		if (l.named) {
			if (o) {
				return n;
			}
			return p.encodeNamed;
		}
		if (l.numeric) {
			return p.encodeNumeric;
		}
		return p.encodeRaw;
	}, decode:function (l) {
		return l.replace(c, function (n, m, o) {
			if (m) {
				return String.fromCharCode(o);
			}
			return d[n] || a[n] || h(n);
		});
	}};
})(tinymce);
tinymce.html.Styles = function (d, f) {
	var k = /rgb\s*\(\s*([0-9]+)\s*,\s*([0-9]+)\s*,\s*([0-9]+)\s*\)/gi, h = /(?:url(?:(?:\(\s*\"([^\"]+)\"\s*\))|(?:\(\s*\'([^\']+)\'\s*\))|(?:\(\s*([^)\s]+)\s*\))))|(?:\'([^\']+)\')|(?:\"([^\"]+)\")/gi, b = /\s*([^:]+):\s*([^;]+);?/g, l = /\s+$/, m = /rgb/, e, g, a = {}, j;
	d = d || {};
	j = "\\\" \\' \\; \\: ; : _".split(" ");
	for (g = 0; g < j.length; g++) {
		a[j[g]] = "_" + g;
		a["_" + g] = j[g];
	}
	function c(o, s, q, n) {
		function p(r) {
			r = parseInt(r).toString(16);
			return r.length > 1 ? r : "0" + r;
		}
		return "#" + p(s) + p(q) + p(n);
	}
	return {toHex:function (n) {
		return n.replace(k, c);
	}, parse:function (s) {
		var z = {}, q, o, x, r, v = d.url_converter, y = d.url_converter_scope || this;
		function p(D, G) {
			var F, C, B, E;
			F = z[D + "-top" + G];
			if (!F) {
				return;
			}
			C = z[D + "-right" + G];
			if (F != C) {
				return;
			}
			B = z[D + "-bottom" + G];
			if (C != B) {
				return;
			}
			E = z[D + "-left" + G];
			if (B != E) {
				return;
			}
			z[D + G] = E;
			delete z[D + "-top" + G];
			delete z[D + "-right" + G];
			delete z[D + "-bottom" + G];
			delete z[D + "-left" + G];
		}
		function u(C) {
			var D = z[C], B;
			if (!D || D.indexOf(" ") < 0) {
				return;
			}
			D = D.split(" ");
			B = D.length;
			while (B--) {
				if (D[B] !== D[0]) {
					return false;
				}
			}
			z[C] = D[0];
			return true;
		}
		function A(D, C, B, E) {
			if (!u(C)) {
				return;
			}
			if (!u(B)) {
				return;
			}
			if (!u(E)) {
				return;
			}
			z[D] = z[C] + " " + z[B] + " " + z[E];
			delete z[C];
			delete z[B];
			delete z[E];
		}
		function t(B) {
			r = true;
			return a[B];
		}
		function n(C, B) {
			if (r) {
				C = C.replace(/_[0-9]/g, function (D) {
					return a[D];
				});
			}
			if (!B) {
				C = C.replace(/\\([\'\";:])/g, "$1");
			}
			return C;
		}
		if (s) {
			s = s.replace(/\\[\"\';:_]/g, t).replace(/\"[^\"]+\"|\'[^\']+\'/g, function (B) {
				return B.replace(/[;:]/g, t);
			});
			while (q = b.exec(s)) {
				o = q[1].replace(l, "").toLowerCase();
				x = q[2].replace(l, "");
				if (o && x.length > 0) {
					if (o === "font-weight" && x === "700") {
						x = "bold";
					} else {
						if (o === "color" || o === "background-color") {
							x = x.toLowerCase();
						}
					}
					x = x.replace(k, c);
					x = x.replace(h, function (C, B, F, E, G, D) {
						G = G || D;
						if (G) {
							G = n(G);
							return "'" + G.replace(/\'/g, "\\'") + "'";
						}
						B = n(B || F || E);
						if (v) {
							B = v.call(y, B, "style");
						}
						return "url('" + B.replace(/\'/g, "\\'") + "')";
					});
					z[o] = r ? n(x, true) : x;
				}
				b.lastIndex = q.index + q[0].length;
			}
			p("border", "");
			p("border", "-width");
			p("border", "-color");
			p("border", "-style");
			p("padding", "");
			p("margin", "");
			A("border", "border-width", "border-style", "border-color");
			if (z.border === "medium none") {
				delete z.border;
			}
		}
		return z;
	}, serialize:function (q, r) {
		var p = "", o;
		function n(t) {
			var x, u, s, t, v;
			x = f.styles[t];
			if (x) {
				for (u = 0, s = x.length; u < s; u++) {
					t = x[u];
					v = q[t];
					if (v !== e) {
						p += (p.length > 0 ? " " : "") + t + ": " + v + ";";
					}
				}
			}
		}
		if (r && f && f.styles) {
			n("*");
			n(o);
		} else {
			for (o in q) {
				p += (p.length > 0 ? " " : "") + o + ": " + q[o] + ";";
			}
		}
		return p;
	}};
};
(function (k) {
	var e = {}, g, j, a, c, d = k.makeMap, h = k.each;
	function f(m, l) {
		return m.split(l || ",");
	}
	function b(p, o) {
		var m, n = {};
		function l(q) {
			return q.replace(/[A-Z]+/g, function (r) {
				return l(p[r]);
			});
		}
		for (m in p) {
			if (p.hasOwnProperty(m)) {
				p[m] = l(p[m]);
			}
		}
		l(o).replace(/#/g, "#text").replace(/(\w+)\[([^\]]+)\]\[([^\]]*)\]/g, function (t, r, q, s) {
			q = f(q, "|");
			n[r] = {attributes:d(q), attributesOrder:q, children:d(s, "|", {"#comment":{}})};
		});
		return n;
	}
	j = "h1,h2,h3,h4,h5,h6,hr,p,div,address,pre,form,table,tbody,thead,tfoot,th,tr,td,li,ol,ul,caption,blockquote,center,dl,dt,dd,dir,fieldset,noscript,menu,isindex,samp,header,footer,article,section,hgroup";
	j = d(j, ",", d(j.toUpperCase()));
	e = b({Z:"H|K|N|O|P", Y:"X|form|R|Q", ZG:"E|span|width|align|char|charoff|valign", X:"p|T|div|U|W|isindex|fieldset|table", ZF:"E|align|char|charoff|valign", W:"pre|hr|blockquote|address|center|noframes", ZE:"abbr|axis|headers|scope|rowspan|colspan|align|char|charoff|valign|nowrap|bgcolor|width|height", ZD:"[E][S]", U:"ul|ol|dl|menu|dir", ZC:"p|Y|div|U|W|table|br|span|bdo|object|applet|img|map|K|N|Q", T:"h1|h2|h3|h4|h5|h6", ZB:"X|S|Q", S:"R|P", ZA:"a|G|J|M|O|P", R:"a|H|K|N|O", Q:"noscript|P", P:"ins|del|script", O:"input|select|textarea|label|button", N:"M|L", M:"i|b|dfn|code|q|samp|kbd|var|cite|abbr|acronym", L:"sub|sup", K:"J|I", J:"tt|i|b|u|s|strike", I:"big|small|font|basefont", H:"G|F", G:"br|span|bdo", F:"object|applet|img|map|iframe", E:"A|B|C", D:"accesskey|tabindex|onfocus|onblur", C:"onclick|ondblclick|onmousedown|onmouseup|onmouseover|onmousemove|onmouseout|onkeypress|onkeydown|onkeyup", B:"lang|xml:lang|dir", A:"id|class|style|title"}, "script[id|charset|type|language|src|defer|xml:space][]style[B|id|type|media|title|xml:space][]object[E|declare|classid|codebase|data|type|codetype|archive|standby|height|width|usemap|name|tabindex|align|border|hspace|vspace][#|param|Y]param[id|name|value|valuetype|type][]p[E|align][#|S]a[E|D|charset|type|name|href|hreflang|rel|rev|shape|coords|target][#|Z]br[A|clear][]span[E][#|S]bdo[A|C|B][#|S]applet[A|codebase|archive|code|object|alt|name|width|height|align|hspace|vspace][#|param|Y]h1[E|align][#|S]img[E|src|alt|name|longdesc|height|width|usemap|ismap|align|border|hspace|vspace][]map[B|C|A|name][X|form|Q|area]h2[E|align][#|S]iframe[A|longdesc|name|src|frameborder|marginwidth|marginheight|scrolling|align|height|width][#|Y]h3[E|align][#|S]tt[E][#|S]i[E][#|S]b[E][#|S]u[E][#|S]s[E][#|S]strike[E][#|S]big[E][#|S]small[E][#|S]font[A|B|size|color|face][#|S]basefont[id|size|color|face][]i[E][#|S]b[E][#|S]dfn[E][#|S]code[E][#|S]q[E|cite][#|S]samp[E][#|S]kbd[E][#|S]var[E][#|S]cite[E][#|S]abbr[E][#|S]acronym[E][#|S]sub[E][#|S]sup[E][#|S]input[E|D|type|name|value|checked|disabled|readonly|size|maxlength|src|alt|usemap|onselect|onchange|accept|align][]select[E|name|size|multiple|disabled|tabindex|onfocus|onblur|onchange][optgroup|option]optgroup[E|disabled|label][option]option[E|selected|disabled|label|value][]textarea[E|D|name|rows|cols|disabled|readonly|onselect|onchange][]label[E|for|accesskey|onfocus|onblur][#|S]button[E|D|name|value|type|disabled][#|p|T|div|U|W|table|G|object|applet|img|map|K|N|Q]h4[E|align][#|S]ins[E|cite|datetime][#|Y]h5[E|align][#|S]del[E|cite|datetime][#|Y]h6[E|align][#|S]div[E|align][#|Y]ul[E|type|compact][li]li[E|type|value][#|Y]ol[E|type|compact|start][li]dl[E|compact][dt|dd]dt[E][#|S]dd[E][#|Y]menu[E|compact][li]dir[E|compact][li]pre[E|width|xml:space][#|ZA]hr[E|align|noshade|size|width][]blockquote[E|cite][#|Y]address[E][#|S|p]center[E][#|Y]noframes[E][#|Y]isindex[A|B|prompt][]fieldset[E][#|legend|Y]legend[E|accesskey|align][#|S]table[E|summary|width|border|frame|rules|cellspacing|cellpadding|align|bgcolor][caption|col|colgroup|thead|tfoot|tbody|tr]caption[E|align][#|S]col[ZG][]colgroup[ZG][col]thead[ZF][tr]tr[ZF|bgcolor][th|td]th[E|ZE][#|Y]form[E|action|method|name|enctype|onsubmit|onreset|accept|accept-charset|target][#|X|R|Q]noscript[E][#|Y]td[E|ZE][#|Y]tfoot[ZF][tr]tbody[ZF][tr]area[E|D|shape|coords|href|nohref|alt|target][]base[id|href|target][]body[E|onload|onunload|background|bgcolor|text|link|vlink|alink][#|Y]");
	g = d("checked,compact,declare,defer,disabled,ismap,multiple,nohref,noresize,noshade,nowrap,readonly,selected,preload,autoplay,loop,controls");
	a = d("area,base,basefont,br,col,frame,hr,img,input,isindex,link,meta,param,embed,source");
	c = d("pre,script,style");
	k.html.Schema = function (o) {
		var v = this, l = {}, m = {}, t = [], n;
		o = o || {};
		if (o.verify_html === false) {
			o.valid_elements = "*[*]";
		}
		if (o.valid_styles) {
			n = {};
			h(o.valid_styles, function (y, x) {
				n[x] = k.explode(y);
			});
		}
		function u(x) {
			return new RegExp("^" + x.replace(/([?+*])/g, ".$1") + "$");
		}
		function q(E) {
			var D, z, S, O, T, y, B, N, Q, J, R, V, H, C, P, x, L, A, U, W, I, M, G = /^([#+-])?([^\[\/]+)(?:\/([^\[]+))?(?:\[([^\]]+)\])?$/, K = /^([!\-])?(\w+::\w+|[^=:<]+)?(?:([=:<])(.*))?$/, F = /[*?+]/;
			if (E) {
				E = f(E);
				if (l["@"]) {
					L = l["@"].attributes;
					A = l["@"].attributesOrder;
				}
				for (D = 0, z = E.length; D < z; D++) {
					y = G.exec(E[D]);
					if (y) {
						P = y[1];
						J = y[2];
						x = y[3];
						Q = y[4];
						H = {};
						C = [];
						B = {attributes:H, attributesOrder:C};
						if (P === "#") {
							B.paddEmpty = true;
						}
						if (P === "-") {
							B.removeEmpty = true;
						}
						if (L) {
							for (W in L) {
								H[W] = L[W];
							}
							C.push.apply(C, A);
						}
						if (Q) {
							Q = f(Q, "|");
							for (S = 0, O = Q.length; S < O; S++) {
								y = K.exec(Q[S]);
								if (y) {
									N = {};
									V = y[1];
									R = y[2].replace(/::/g, ":");
									P = y[3];
									M = y[4];
									if (V === "!") {
										B.attributesRequired = B.attributesRequired || [];
										B.attributesRequired.push(R);
										N.required = true;
									}
									if (V === "-") {
										delete H[R];
										C.splice(k.inArray(C, R), 1);
										continue;
									}
									if (P) {
										if (P === "=") {
											B.attributesDefault = B.attributesDefault || [];
											B.attributesDefault.push({name:R, value:M});
											N.defaultValue = M;
										}
										if (P === ":") {
											B.attributesForced = B.attributesForced || [];
											B.attributesForced.push({name:R, value:M});
											N.forcedValue = M;
										}
										if (P === "<") {
											N.validValues = d(M, "?");
										}
									}
									if (F.test(R)) {
										B.attributePatterns = B.attributePatterns || [];
										N.pattern = u(R);
										B.attributePatterns.push(N);
									} else {
										if (!H[R]) {
											C.push(R);
										}
										H[R] = N;
									}
								}
							}
						}
						if (!L && J == "@") {
							L = H;
							A = C;
						}
						if (x) {
							B.outputName = J;
							l[x] = B;
						}
						if (F.test(J)) {
							B.pattern = u(J);
							t.push(B);
						} else {
							l[J] = B;
						}
					}
				}
			}
		}
		function s(x) {
			l = {};
			t = [];
			q(x);
			h(e, function (z, y) {
				m[y] = z.children;
			});
		}
		function p(y) {
			var x = /^(~)?(.+)$/;
			if (y) {
				h(f(y), function (B) {
					var A = x.exec(B), C = A[1] === "~" ? "span" : "div", z = A[2];
					m[z] = m[C];
					h(m, function (D, E) {
						if (D[C]) {
							D[z] = D[C];
						}
					});
				});
			}
		}
		function r(y) {
			var x = /^([+\-]?)(\w+)\[([^\]]+)\]$/;
			if (y) {
				h(f(y), function (C) {
					var B = x.exec(C), z, A;
					if (B) {
						A = B[1];
						if (A) {
							z = m[B[2]];
						} else {
							z = m[B[2]] = {"#comment":{}};
						}
						z = m[B[2]];
						h(f(B[3], "|"), function (D) {
							if (A === "-") {
								delete z[D];
							} else {
								z[D] = {};
							}
						});
					}
				});
			}
		}
		if (!o.valid_elements) {
			h(e, function (y, x) {
				l[x] = {attributes:y.attributes, attributesOrder:y.attributesOrder};
				m[x] = y.children;
			});
			h(f("b/b,i/i"), function (x) {
				x = f(x, "/");
				l[x[1]].outputName = x[0];
			});
			l.img.attributesDefault = [{name:"alt", value:""}];
			h(f("ol,ul,li,sub,sup,blockquote,tr,div,span,font,a,table,tbody"), function (x) {
				l[x].removeEmpty = true;
			});
			h(f("p,h1,h2,h3,h4,h5,h6,th,td,pre,div,address,caption"), function (x) {
				l[x].paddEmpty = true;
			});
		} else {
			s(o.valid_elements);
		}
		p(o.custom_elements);
		r(o.valid_children);
		q(o.extended_valid_elements);
		r("+ol[ul|ol],+ul[ul|ol]");
		if (o.invalid_elements) {
			k.each(k.explode(o.invalid_elements), function (x) {
				if (l[x]) {
					delete l[x];
				}
			});
		}
		v.children = m;
		v.styles = n;
		v.getBoolAttrs = function () {
			return g;
		};
		v.getBlockElements = function () {
			return j;
		};
		v.getEmptyElements = function () {
			return a;
		};
		v.getWhiteSpaceElements = function () {
			return c;
		};
		v.isValidChild = function (x, z) {
			var y = m[x];
			return !!(y && y[z]);
		};
		v.getElementRule = function (x) {
			var z = l[x], y;
			if (z) {
				return z;
			}
			y = t.length;
			while (y--) {
				z = t[y];
				if (z.pattern.test(x)) {
					return z;
				}
			}
		};
		v.addValidElements = q;
		v.setValidElements = s;
		v.addCustomElements = p;
		v.addValidChildren = r;
	};
	k.html.Schema.boolAttrMap = g;
	k.html.Schema.blockElementsMap = j;
})(tinymce);
(function (a) {
	a.html.SaxParser = function (c, e) {
		var b = this, d = function () {
		};
		c = c || {};
		b.schema = e = e || new a.html.Schema();
		a.each("comment cdata text start end pi doctype".split(" "), function (f) {
			if (f) {
				b[f] = c[f] || d;
			}
		});
		b.parse = function (r) {
			var A = this, f, n = 0, E, j, m = [], B, l, I, u, L, q, k, x, z, G, s, D, p, H, o, F, K, J, C, h, g, v, t = 0, y = a.html.Entities.decode;
			C = new RegExp("<(?:(?:!--([\\w\\W]*?)-->)|(?:!\\[CDATA\\[([\\w\\W]*?)\\]\\]>)|(?:!DOCTYPE([\\w\\W]*?)>)|(?:\\?([^\\s\\/<>]+) ?([\\w\\W]*?)[?/]>)|(?:\\/([^>]+)>)|(?:([^\\s\\/<>]+)\\s*((?:[^\"'>]+(?:(?:\"[^\"]*\")|(?:'[^']*')|[^>]+))*)>))", "g");
			h = /([\w:\-]+)(?:\s*=\s*(?:(?:\"((?:\\.|[^\"])*)\")|(?:\'((?:\\.|[^\'])*)\')|([^>\s]+)))?/g;
			g = {script:/<\/script[^>]*>/gi, style:/<\/style[^>]*>/gi, noscript:/<\/noscript[^>]*>/gi};
			q = e.getEmptyElements();
			k = e.getBoolAttrs();
			z = c.validate;
			while (f = C.exec(r)) {
				if (n < f.index) {
					A.text(y(r.substr(n, f.index - n)));
				}
				if (E = f[6]) {
					E = E.toLowerCase();
					l = m.length;
					while (l--) {
						if (m[l].name === E) {
							break;
						}
					}
					if (l >= 0) {
						for (I = m.length - 1; I >= l; I--) {
							E = m[I];
							if (E.valid) {
								A.end(E.name);
							}
						}
						m.length = l;
					}
				} else {
					if (E = f[7]) {
						E = E.toLowerCase();
						x = E in q;
						if (!z || (G = e.getElementRule(E))) {
							s = true;
							if (z) {
								H = G.attributes;
								o = G.attributePatterns;
							}
							if (p = f[8]) {
								B = [];
								B.map = {};
								p.replace(h, function (N, M, R, Q, P) {
									var S, O;
									M = M.toLowerCase();
									R = M in k ? M : y(R || Q || P || "");
									if (z && M.indexOf("data-") !== 0) {
										S = H[M];
										if (!S && o) {
											O = o.length;
											while (O--) {
												S = o[O];
												if (S.pattern.test(M)) {
													break;
												}
											}
											if (O === -1) {
												S = null;
											}
										}
										if (!S) {
											return;
										}
										if (S.validValues && !(R in S.validValues)) {
											return;
										}
									}
									B.map[M] = R;
									B.push({name:M, value:R});
								});
							} else {
								B = [];
								B.map = {};
							}
							if (z) {
								F = G.attributesRequired;
								K = G.attributesDefault;
								J = G.attributesForced;
								if (J) {
									I = J.length;
									while (I--) {
										D = J[I];
										L = D.name;
										v = D.value;
										if (v === "{$uid}") {
											v = "mce_" + t++;
										}
										B.map[L] = v;
										B.push({name:L, value:v});
									}
								}
								if (K) {
									I = K.length;
									while (I--) {
										D = K[I];
										L = D.name;
										if (!(L in B.map)) {
											v = D.value;
											if (v === "{$uid}") {
												v = "mce_" + t++;
											}
											B.map[L] = v;
											B.push({name:L, value:v});
										}
									}
								}
								if (F) {
									I = F.length;
									while (I--) {
										if (F[I] in B.map) {
											break;
										}
									}
									if (I === -1) {
										s = false;
									}
								}
							}
							if (s) {
								A.start(E, B, x);
							}
						} else {
							s = false;
						}
						if (j = g[E]) {
							j.lastIndex = n = f.index + f[0].length;
							if (f = j.exec(r)) {
								if (s) {
									u = r.substr(n, f.index - n);
								}
								n = f.index + f[0].length;
							} else {
								u = r.substr(n);
								n = r.length;
							}
							if (s && u.length > 0) {
								A.text(u, true);
							}
							if (s) {
								A.end(E);
							}
							C.lastIndex = n;
							continue;
						}
						if (!x) {
							if (!p || p.indexOf("/") != p.length - 1) {
								m.push({name:E, valid:s});
							} else {
								if (s) {
									A.end(E);
								}
							}
						}
					} else {
						if (E = f[1]) {
							A.comment(E);
						} else {
							if (E = f[2]) {
								A.cdata(E);
							} else {
								if (E = f[3]) {
									A.doctype(E);
								} else {
									if (E = f[4]) {
										A.pi(E, f[5]);
									}
								}
							}
						}
					}
				}
				n = f.index + f[0].length;
			}
			if (n < r.length) {
				A.text(y(r.substr(n)));
			}
			for (I = m.length - 1; I >= 0; I--) {
				E = m[I];
				if (E.valid) {
					A.end(E.name);
				}
			}
		};
	};
})(tinymce);
(function (d) {
	var c = /^[ \t\r\n]*$/, e = {"#text":3, "#comment":8, "#cdata":4, "#pi":7, "#doctype":10, "#document-fragment":11};
	function a(l, m, k) {
		var j, h, f = k ? "lastChild" : "firstChild", g = k ? "prev" : "next";
		if (l[f]) {
			return l[f];
		}
		if (l !== m) {
			j = l[g];
			if (j) {
				return j;
			}
			for (h = l.parent; h && h !== m; h = h.parent) {
				j = h[g];
				if (j) {
					return j;
				}
			}
		}
	}
	function b(f, g) {
		this.name = f;
		this.type = g;
		if (g === 1) {
			this.attributes = [];
			this.attributes.map = {};
		}
	}
	d.extend(b.prototype, {replace:function (g) {
		var f = this;
		if (g.parent) {
			g.remove();
		}
		f.insert(g, f);
		f.remove();
		return f;
	}, attr:function (h, l) {
		var f = this, g, j, k;
		if (typeof h !== "string") {
			for (j in h) {
				f.attr(j, h[j]);
			}
			return f;
		}
		if (g = f.attributes) {
			if (l !== k) {
				if (l === null) {
					if (h in g.map) {
						delete g.map[h];
						j = g.length;
						while (j--) {
							if (g[j].name === h) {
								g = g.splice(j, 1);
								return f;
							}
						}
					}
					return f;
				}
				if (h in g.map) {
					j = g.length;
					while (j--) {
						if (g[j].name === h) {
							g[j].value = l;
							break;
						}
					}
				} else {
					g.push({name:h, value:l});
				}
				g.map[h] = l;
				return f;
			} else {
				return g.map[h];
			}
		}
	}, clone:function () {
		var g = this, n = new b(g.name, g.type), h, f, m, j, k;
		if (m = g.attributes) {
			k = [];
			k.map = {};
			for (h = 0, f = m.length; h < f; h++) {
				j = m[h];
				if (j.name !== "id") {
					k[k.length] = {name:j.name, value:j.value};
					k.map[j.name] = j.value;
				}
			}
			n.attributes = k;
		}
		n.value = g.value;
		n.empty = g.empty;
		return n;
	}, wrap:function (g) {
		var f = this;
		f.parent.insert(g, f);
		g.append(f);
		return f;
	}, unwrap:function () {
		var f = this, h, g;
		for (h = f.firstChild; h; ) {
			g = h.next;
			f.insert(h, f, true);
			h = g;
		}
		f.remove();
	}, remove:function () {
		var f = this, h = f.parent, g = f.next, j = f.prev;
		if (h) {
			if (h.firstChild === f) {
				h.firstChild = g;
				if (g) {
					g.prev = null;
				}
			} else {
				j.next = g;
			}
			if (h.lastChild === f) {
				h.lastChild = j;
				if (j) {
					j.next = null;
				}
			} else {
				g.prev = j;
			}
			f.parent = f.next = f.prev = null;
		}
		return f;
	}, append:function (h) {
		var f = this, g;
		if (h.parent) {
			h.remove();
		}
		g = f.lastChild;
		if (g) {
			g.next = h;
			h.prev = g;
			f.lastChild = h;
		} else {
			f.lastChild = f.firstChild = h;
		}
		h.parent = f;
		return h;
	}, insert:function (h, f, j) {
		var g;
		if (h.parent) {
			h.remove();
		}
		g = f.parent || this;
		if (j) {
			if (f === g.firstChild) {
				g.firstChild = h;
			} else {
				f.prev.next = h;
			}
			h.prev = f.prev;
			h.next = f;
			f.prev = h;
		} else {
			if (f === g.lastChild) {
				g.lastChild = h;
			} else {
				f.next.prev = h;
			}
			h.next = f.next;
			h.prev = f;
			f.next = h;
		}
		h.parent = g;
		return h;
	}, getAll:function (g) {
		var f = this, h, j = [];
		for (h = f.firstChild; h; h = a(h, f)) {
			if (h.name === g) {
				j.push(h);
			}
		}
		return j;
	}, empty:function () {
		var g = this, f, h, j;
		if (g.firstChild) {
			f = [];
			for (j = g.firstChild; j; j = a(j, g)) {
				f.push(j);
			}
			h = f.length;
			while (h--) {
				j = f[h];
				j.parent = j.firstChild = j.lastChild = j.next = j.prev = null;
			}
		}
		g.firstChild = g.lastChild = null;
		return g;
	}, isEmpty:function (j) {
		var f = this, h = f, g;
		do {
			if (h.type === 1) {
				if (j[h.name]) {
					return false;
				}
				g = h.attributes.length;
				while (g--) {
					if (h.attributes[g].name.indexOf("data-") === 0) {
						return false;
					}
				}
			}
			if ((h.type === 3 && !c.test(h.value))) {
				return false;
			}
		} while (h = a(h, f));
		return true;
	}});
	d.extend(b, {create:function (g, f) {
		var j, h;
		j = new b(g, e[g] || 1);
		if (f) {
			for (h in f) {
				j.attr(h, f[h]);
			}
		}
		return j;
	}});
	d.html.Node = b;
})(tinymce);
(function (b) {
	var a = b.html.Node;
	b.html.DomParser = function (f, g) {
		var e = this, d = {}, c = [];
		f = f || {};
		f.root_name = f.root_name || "body";
		e.schema = g = g || new b.html.Schema();
		function h(j) {
			var l, m, s, r, u, k, n, p, q = g.getEmptyElements(), o, t;
			o = b.makeMap("tr,td,th,tbody,thead,tfoot,table");
			for (l = 0; l < j.length; l++) {
				m = j[l];
				if (!m.parent) {
					continue;
				}
				r = [m];
				for (s = m.parent; s && !g.isValidChild(s.name, m.name) && !o[s.name]; s = s.parent) {
					r.push(s);
				}
				if (s && r.length > 1) {
					r.reverse();
					u = k = r[0].clone();
					for (i = 0; i < r.length - 1; i++) {
						if (g.isValidChild(k.name, r[i].name)) {
							n = r[i].clone();
							k.append(n);
						} else {
							n = k;
						}
						for (childNode = r[i].firstChild; childNode && childNode != r[i + 1]; childNode = childNode.next) {
							n.append(childNode);
						}
						k = n;
					}
					if (!u.isEmpty(q)) {
						s.insert(u, r[0], true);
						s.insert(m, u);
					} else {
						s.insert(m, r[0], true);
					}
					if (r[0].isEmpty(q)) {
						r[0].empty().remove();
					}
				} else {
					if (m.parent) {
						if (m.name === "li") {
							t = m.prev;
							if (t && (t.name === "ul" || t.name === "ul")) {
								t.append(m);
								continue;
							}
							t = m.next;
							if (t && (t.name === "ul" || t.name === "ul")) {
								t.insert(m, t.firstChild, true);
								continue;
							}
							m.wrap(new a("ul", 1));
							continue;
						}
						if (g.isValidChild(m.parent.name, "div") && g.isValidChild("div", m.name)) {
							m.wrap(new a("div", 1));
						} else {
							if (m.name === "style" || m.name === "script") {
								m.empty().remove();
							} else {
								m.unwrap();
							}
						}
					}
				}
			}
		}
		e.addNodeFilter = function (j, k) {
			b.each(b.explode(j), function (l) {
				var m = d[l];
				if (!m) {
					d[l] = m = [];
				}
				m.push(k);
			});
		};
		e.addAttributeFilter = function (j, k) {
			b.each(b.explode(j), function (l) {
				var m;
				for (m = 0; m < c.length; m++) {
					if (c[m].name === l) {
						c[m].callbacks.push(k);
						return;
					}
				}
				c.push({name:l, callbacks:[k]});
			});
		};
		e.parse = function (u, m) {
			var n, D, y, x, k = {}, p = {}, A, z, v, r, C, G, o, B, F = [], t, j, s, q;
			m = m || {};
			o = b.extend(b.makeMap("script,style,head,title,meta,param"), g.getBlockElements());
			q = g.children;
			s = g.getWhiteSpaceElements();
			B = /^[ \t\r\n]+/;
			t = /[ \t\r\n]+$/;
			j = /[ \t\r\n]+/g;
			function E(l, H) {
				var I = new a(l, H), J;
				if (l in d) {
					J = k[l];
					if (J) {
						J.push(I);
					} else {
						k[l] = [I];
					}
				}
				return I;
			}
			n = new b.html.SaxParser({validate:f.validate, cdata:function (l) {
				y.append(E("#cdata", 4)).value = l;
			}, text:function (I, l) {
				var H;
				if (!s[y.name]) {
					I = I.replace(j, " ");
					if (y.lastChild && o[y.lastChild.name]) {
						I = I.replace(B, "");
					}
				}
				if (I.length !== 0) {
					H = E("#text", 3);
					H.raw = !!l;
					y.append(H).value = I;
				}
			}, comment:function (l) {
				y.append(E("#comment", 8)).value = l;
			}, pi:function (l, H) {
				y.append(E(l, 7)).value = H;
			}, doctype:function (l) {
				y.append(E("#doctype", 10)).value = l;
			}, start:function (l, P, I) {
				var N, K, J, H, L, Q, O, M;
				J = g.getElementRule(l);
				if (J) {
					N = E(J.outputName || l, 1);
					N.attributes = P;
					N.shortEnded = I;
					y.append(N);
					M = q[y.name];
					if (M && q[N.name] && !M[N.name]) {
						F.push(N);
					}
					K = c.length;
					while (K--) {
						L = c[K].name;
						if (L in P.map) {
							C = p[L];
							if (C) {
								C.push(N);
							} else {
								p[L] = [N];
							}
						}
					}
					if (o[l]) {
						for (H = N.prev; H && H.type === 3; ) {
							Q = H.value.replace(t, "");
							if (Q.length > 0) {
								H.value = Q;
								H = H.prev;
							} else {
								O = H.prev;
								H.remove();
								H = O;
							}
						}
					}
					if (!I) {
						y = N;
					}
				}
			}, end:function (l) {
				var L, I, K, H, J;
				I = g.getElementRule(l);
				if (I) {
					if (o[l]) {
						if (!s[y.name]) {
							for (L = y.firstChild; L && L.type === 3; ) {
								K = L.value.replace(B, "");
								if (K.length > 0) {
									L.value = K;
									L = L.next;
								} else {
									H = L.next;
									L.remove();
									L = H;
								}
							}
							for (L = y.lastChild; L && L.type === 3; ) {
								K = L.value.replace(t, "");
								if (K.length > 0) {
									L.value = K;
									L = L.prev;
								} else {
									H = L.prev;
									L.remove();
									L = H;
								}
							}
						}
						L = y.prev;
						if (L && L.type === 3) {
							K = L.value.replace(B, "");
							if (K.length > 0) {
								L.value = K;
							} else {
								L.remove();
							}
						}
					}
					if (I.removeEmpty || I.paddEmpty) {
						if (y.isEmpty(g.getEmptyElements())) {
							if (I.paddEmpty) {
								y.empty().append(new a("#text", "3")).value = "\xa0";
							} else {
								if (!y.attributes.map.name) {
									J = y.parent;
									y.empty().remove();
									y = J;
								}
								return;
							}
						}
					}
					y = y.parent;
				}
			}}, g);
			D = y = new a(f.root_name, 11);
			n.parse(u);
			h(F);
			for (G in k) {
				C = d[G];
				x = k[G];
				v = x.length;
				while (v--) {
					if (!x[v].parent) {
						x.splice(v, 1);
					}
				}
				for (A = 0, z = C.length; A < z; A++) {
					C[A](x, G, m);
				}
			}
			for (A = 0, z = c.length; A < z; A++) {
				C = c[A];
				if (C.name in p) {
					x = p[C.name];
					v = x.length;
					while (v--) {
						if (!x[v].parent) {
							x.splice(v, 1);
						}
					}
					for (v = 0, r = C.callbacks.length; v < r; v++) {
						C.callbacks[v](x, C.name, m);
					}
				}
			}
			return D;
		};
	};
})(tinymce);
tinymce.html.Writer = function (e) {
	var c = [], a, b, d, f;
	e = e || {};
	a = e.indent;
	b = tinymce.makeMap(e.indent_before || "");
	d = tinymce.makeMap(e.indent_after || "");
	f = tinymce.html.Entities.getEncodeFunc(e.entity_encoding || "raw", e.entities);
	return {start:function (k, j, o) {
		var m, h, g, n;
		if (a && b[k] && c.length > 0) {
			n = c[c.length - 1];
			if (n.length > 0 && n !== "\n") {
				c.push("\n");
			}
		}
		c.push("<", k);
		if (j) {
			for (m = 0, h = j.length; m < h; m++) {
				g = j[m];
				c.push(" ", g.name, "=\"", f(g.value, true), "\"");
			}
		}
		if (!o) {
			c[c.length] = ">";
		} else {
			c[c.length] = " />";
		}
	}, end:function (g) {
		var h;
		c.push("</", g, ">");
		if (a && d[g] && c.length > 0) {
			h = c[c.length - 1];
			if (h.length > 0 && h !== "\n") {
				c.push("\n");
			}
		}
	}, text:function (h, g) {
		if (h.length > 0) {
			c[c.length] = g ? h : f(h);
		}
	}, cdata:function (g) {
		c.push("<![CDATA[", g, "]]>");
	}, comment:function (g) {
		c.push("<!--", g, "-->");
	}, pi:function (g, h) {
		if (h) {
			c.push("<?", g, " ", h, "?>");
		} else {
			c.push("<?", g, "?>");
		}
	}, doctype:function (g) {
		c.push("<!DOCTYPE", g, ">");
	}, reset:function () {
		c.length = 0;
	}, getContent:function () {
		return c.join("").replace(/\n$/, "");
	}};
};
(function (a) {
	a.html.Serializer = function (c, d) {
		var b = this, e = new a.html.Writer(c);
		c = c || {};
		c.validate = "validate" in c ? c.validate : true;
		b.schema = d = d || new a.html.Schema();
		b.writer = e;
		b.serialize = function (h) {
			var g, j;
			j = c.validate;
			g = {3:function (l, k) {
				e.text(l.value, l.raw);
			}, 8:function (k) {
				e.comment(k.value);
			}, 7:function (k) {
				e.pi(k.name, k.value);
			}, 10:function (k) {
				e.doctype(k.value);
			}, 4:function (k) {
				e.cdata(k.value);
			}, 11:function (k) {
				if ((k = k.firstChild)) {
					do {
						f(k);
					} while (k = k.next);
				}
			}};
			e.reset();
			function f(m) {
				var u = g[m.type], k, p, t, s, q, v, o, n, r;
				if (!u) {
					k = m.name;
					p = m.shortEnded;
					t = m.attributes;
					if (j && t && t.length > 1) {
						v = [];
						v.map = {};
						r = d.getElementRule(m.name);
						for (o = 0, n = r.attributesOrder.length; o < n; o++) {
							s = r.attributesOrder[o];
							if (s in t.map) {
								q = t.map[s];
								v.map[s] = q;
								v.push({name:s, value:q});
							}
						}
						for (o = 0, n = t.length; o < n; o++) {
							s = t[o].name;
							if (!(s in v.map)) {
								q = t.map[s];
								v.map[s] = q;
								v.push({name:s, value:q});
							}
						}
						t = v;
					}
					e.start(m.name, t, p);
					if (!p) {
						if ((m = m.firstChild)) {
							do {
								f(m);
							} while (m = m.next);
						}
						e.end(k);
					}
				} else {
					u(m);
				}
			}
			if (h.type == 1 && !c.inner) {
				f(h);
			} else {
				g[11](h);
			}
			return e.getContent();
		};
	};
})(tinymce);
(function (d) {
	var f = d.each, c = d.is, e = d.isWebKit, a = d.isIE, h = d.html.Entities, b = /^([a-z0-9],?)+$/i, g = d.html.Schema.blockElementsMap;
	d.create("tinymce.dom.DOMUtils", {doc:null, root:null, files:null, pixelStyles:/^(top|left|bottom|right|width|height|borderWidth)$/, props:{"for":"htmlFor", "class":"className", className:"className", checked:"checked", disabled:"disabled", maxlength:"maxLength", readonly:"readOnly", selected:"selected", value:"value", id:"id", name:"name", type:"type"}, DOMUtils:function (n, l) {
		var k = this, j;
		k.doc = n;
		k.win = window;
		k.files = {};
		k.cssFlicker = false;
		k.counter = 0;
		k.stdMode = !d.isIE || n.documentMode >= 8;
		k.boxModel = !d.isIE || n.compatMode == "CSS1Compat" || k.stdMode;
		k.hasOuterHTML = "outerHTML" in n.createElement("a");
		k.settings = l = d.extend({keep_values:false, hex_colors:1}, l);
		k.styles = new d.html.Styles({url_converter:l.url_converter, url_converter_scope:l.url_converter_scope}, l.schema);
		if (d.isIE6) {
			try {
				n.execCommand("BackgroundImageCache", false, true);
			}
			catch (m) {
				k.cssFlicker = true;
			}
		}
		if (a) {
			("abbr article aside audio canvas details figcaption figure footer header hgroup mark menu meter nav output progress section summary time video").replace(/\w+/g, function (o) {
				n.createElement(o);
			});
		}
		d.addUnload(k.destroy, k);
	}, getRoot:function () {
		var j = this, k = j.settings;
		return (k && j.get(k.root_element)) || j.doc.body;
	}, getViewPort:function (k) {
		var l, j;
		k = !k ? this.win : k;
		l = k.document;
		j = this.boxModel ? l.documentElement : l.body;
		return {x:k.pageXOffset || j.scrollLeft, y:k.pageYOffset || j.scrollTop, w:k.innerWidth || j.clientWidth, h:k.innerHeight || j.clientHeight};
	}, getRect:function (m) {
		var l, j = this, k;
		m = j.get(m);
		l = j.getPos(m);
		k = j.getSize(m);
		return {x:l.x, y:l.y, w:k.w, h:k.h};
	}, getSize:function (m) {
		var k = this, j, l;
		m = k.get(m);
		j = k.getStyle(m, "width");
		l = k.getStyle(m, "height");
		if (j.indexOf("px") === -1) {
			j = 0;
		}
		if (l.indexOf("px") === -1) {
			l = 0;
		}
		return {w:parseInt(j) || m.offsetWidth || m.clientWidth, h:parseInt(l) || m.offsetHeight || m.clientHeight};
	}, getParent:function (l, k, j) {
		return this.getParents(l, k, j, false);
	}, getParents:function (u, p, l, s) {
		var k = this, j, m = k.settings, q = [];
		u = k.get(u);
		s = s === undefined;
		if (m.strict_root) {
			l = l || k.getRoot();
		}
		if (c(p, "string")) {
			j = p;
			if (p === "*") {
				p = function (o) {
					return o.nodeType == 1;
				};
			} else {
				p = function (o) {
					return k.is(o, j);
				};
			}
		}
		while (u) {
			if (u == l || !u.nodeType || u.nodeType === 9) {
				break;
			}
			if (!p || p(u)) {
				if (s) {
					q.push(u);
				} else {
					return u;
				}
			}
			u = u.parentNode;
		}
		return s ? q : null;
	}, get:function (j) {
		var k;
		if (j && this.doc && typeof (j) == "string") {
			k = j;
			j = this.doc.getElementById(j);
			if (j && j.id !== k) {
				return this.doc.getElementsByName(k)[1];
			}
		}
		return j;
	}, getNext:function (k, j) {
		return this._findSib(k, j, "nextSibling");
	}, getPrev:function (k, j) {
		return this._findSib(k, j, "previousSibling");
	}, select:function (l, k) {
		var j = this;
		return d.dom.Sizzle(l, j.get(k) || j.get(j.settings.root_element) || j.doc, []);
	}, is:function (l, j) {
		var k;
		if (l.length === undefined) {
			if (j === "*") {
				return l.nodeType == 1;
			}
			if (b.test(j)) {
				j = j.toLowerCase().split(/,/);
				l = l.nodeName.toLowerCase();
				for (k = j.length - 1; k >= 0; k--) {
					if (j[k] == l) {
						return true;
					}
				}
				return false;
			}
		}
		return d.dom.Sizzle.matches(j, l.nodeType ? [l] : l).length > 0;
	}, add:function (m, q, j, l, o) {
		var k = this;
		return this.run(m, function (s) {
			var r, n;
			r = c(q, "string") ? k.doc.createElement(q) : q;
			k.setAttribs(r, j);
			if (l) {
				if (l.nodeType) {
					r.appendChild(l);
				} else {
					k.setHTML(r, l);
				}
			}
			return !o ? s.appendChild(r) : r;
		});
	}, create:function (l, j, k) {
		return this.add(this.doc.createElement(l), l, j, k, 1);
	}, createHTML:function (r, j, p) {
		var q = "", m = this, l;
		q += "<" + r;
		for (l in j) {
			if (j.hasOwnProperty(l)) {
				q += " " + l + "=\"" + m.encode(j[l]) + "\"";
			}
		}
		if (typeof (p) != "undefined") {
			return q + ">" + p + "</" + r + ">";
		}
		return q + " />";
	}, remove:function (j, k) {
		return this.run(j, function (m) {
			var l, n;
			l = m.parentNode;
			if (!l) {
				return null;
			}
			if (k) {
				while (n = m.firstChild) {
					if (!d.isIE || n.nodeType !== 3 || n.nodeValue) {
						l.insertBefore(n, m);
					} else {
						m.removeChild(n);
					}
				}
			}
			return l.removeChild(m);
		});
	}, setStyle:function (m, j, k) {
		var l = this;
		return l.run(m, function (p) {
			var o, n;
			o = p.style;
			j = j.replace(/-(\D)/g, function (r, q) {
				return q.toUpperCase();
			});
			if (l.pixelStyles.test(j) && (d.is(k, "number") || /^[\-0-9\.]+$/.test(k))) {
				k += "px";
			}
			switch (j) {
			  case "opacity":
				if (a) {
					o.filter = k === "" ? "" : "alpha(opacity=" + (k * 100) + ")";
					if (!m.currentStyle || !m.currentStyle.hasLayout) {
						o.display = "inline-block";
					}
				}
				o[j] = o["-moz-opacity"] = o["-khtml-opacity"] = k || "";
				break;
			  case "float":
				a ? o.styleFloat = k : o.cssFloat = k;
				break;
			  default:
				o[j] = k || "";
			}
			if (l.settings.update_styles) {
				l.setAttrib(p, "data-mce-style");
			}
		});
	}, getStyle:function (m, j, l) {
		m = this.get(m);
		if (!m) {
			return false;
		}
		if (this.doc.defaultView && l) {
			j = j.replace(/[A-Z]/g, function (n) {
				return "-" + n;
			});
			try {
				return this.doc.defaultView.getComputedStyle(m, null).getPropertyValue(j);
			}
			catch (k) {
				return null;
			}
		}
		j = j.replace(/-(\D)/g, function (o, n) {
			return n.toUpperCase();
		});
		if (j == "float") {
			j = a ? "styleFloat" : "cssFloat";
		}
		if (m.currentStyle && l) {
			return m.currentStyle[j];
		}
		return m.style[j];
	}, setStyles:function (m, n) {
		var k = this, l = k.settings, j;
		j = l.update_styles;
		l.update_styles = 0;
		f(n, function (o, p) {
			k.setStyle(m, p, o);
		});
		l.update_styles = j;
		if (l.update_styles) {
			k.setAttrib(m, l.cssText);
		}
	}, removeAllAttribs:function (j) {
		return this.run(j, function (m) {
			var k = m.attributes;
			for (var l = k.length - 1; l >= 0; l--) {
				m.removeAttributeNode(k.item(l));
			}
		});
	}, setAttrib:function (l, m, j) {
		var k = this;
		if (!l || !m) {
			return;
		}
		if (k.settings.strict) {
			m = m.toLowerCase();
		}
		return this.run(l, function (o) {
			var n = k.settings;
			switch (m) {
			  case "style":
				if (!c(j, "string")) {
					f(j, function (p, q) {
						k.setStyle(o, q, p);
					});
					return;
				}
				if (n.keep_values) {
					if (j && !k._isRes(j)) {
						o.setAttribute("data-mce-style", j, 2);
					} else {
						o.removeAttribute("data-mce-style", 2);
					}
				}
				o.style.cssText = j;
				break;
			  case "class":
				o.className = j || "";
				break;
			  case "src":
			  case "href":
				if (n.keep_values) {
					if (n.url_converter) {
						j = n.url_converter.call(n.url_converter_scope || k, j, m, o);
					}
					k.setAttrib(o, "data-mce-" + m, j, 2);
				}
				break;
			  case "shape":
				o.setAttribute("data-mce-style", j);
				break;
			}
			if (c(j) && j !== null && j.length !== 0) {
				o.setAttribute(m, "" + j, 2);
			} else {
				o.removeAttribute(m, 2);
			}
		});
	}, setAttribs:function (k, l) {
		var j = this;
		return this.run(k, function (m) {
			f(l, function (o, p) {
				j.setAttrib(m, p, o);
			});
		});
	}, getAttrib:function (m, o, l) {
		var j, k = this;
		m = k.get(m);
		if (!m || m.nodeType !== 1) {
			return false;
		}
		if (!c(l)) {
			l = "";
		}
		if (/^(src|href|style|coords|shape)$/.test(o)) {
			j = m.getAttribute("data-mce-" + o);
			if (j) {
				return j;
			}
		}
		if (a && k.props[o]) {
			j = m[k.props[o]];
			j = j && j.nodeValue ? j.nodeValue : j;
		}
		if (!j) {
			j = m.getAttribute(o, 2);
		}
		if (/^(checked|compact|declare|defer|disabled|ismap|multiple|nohref|noshade|nowrap|readonly|selected)$/.test(o)) {
			if (m[k.props[o]] === true && j === "") {
				return o;
			}
			return j ? o : "";
		}
		if (m.nodeName === "FORM" && m.getAttributeNode(o)) {
			return m.getAttributeNode(o).nodeValue;
		}
		if (o === "style") {
			j = j || m.style.cssText;
			if (j) {
				j = k.serializeStyle(k.parseStyle(j), m.nodeName);
				if (k.settings.keep_values && !k._isRes(j)) {
					m.setAttribute("data-mce-style", j);
				}
			}
		}
		if (e && o === "class" && j) {
			j = j.replace(/(apple|webkit)\-[a-z\-]+/gi, "");
		}
		if (a) {
			switch (o) {
			  case "rowspan":
			  case "colspan":
				if (j === 1) {
					j = "";
				}
				break;
			  case "size":
				if (j === "+0" || j === 20 || j === 0) {
					j = "";
				}
				break;
			  case "width":
			  case "height":
			  case "vspace":
			  case "checked":
			  case "disabled":
			  case "readonly":
				if (j === 0) {
					j = "";
				}
				break;
			  case "hspace":
				if (j === -1) {
					j = "";
				}
				break;
			  case "maxlength":
			  case "tabindex":
				if (j === 32768 || j === 2147483647 || j === "32768") {
					j = "";
				}
				break;
			  case "multiple":
			  case "compact":
			  case "noshade":
			  case "nowrap":
				if (j === 65535) {
					return o;
				}
				return l;
			  case "shape":
				j = j.toLowerCase();
				break;
			  default:
				if (o.indexOf("on") === 0 && j) {
					j = d._replace(/^function\s+\w+\(\)\s+\{\s+(.*)\s+\}$/, "$1", "" + j);
				}
			}
		}
		return (j !== undefined && j !== null && j !== "") ? "" + j : l;
	}, getPos:function (s, m) {
		var k = this, j = 0, q = 0, o, p = k.doc, l;
		s = k.get(s);
		m = m || p.body;
		if (s) {
			if (a && !k.stdMode) {
				s = s.getBoundingClientRect();
				o = k.boxModel ? p.documentElement : p.body;
				j = k.getStyle(k.select("html")[0], "borderWidth");
				j = (j == "medium" || k.boxModel && !k.isIE6) && 2 || j;
				return {x:s.left + o.scrollLeft - j, y:s.top + o.scrollTop - j};
			}
			l = s;
			while (l && l != m && l.nodeType) {
				j += l.offsetLeft || 0;
				q += l.offsetTop || 0;
				l = l.offsetParent;
			}
			l = s.parentNode;
			while (l && l != m && l.nodeType) {
				j -= l.scrollLeft || 0;
				q -= l.scrollTop || 0;
				l = l.parentNode;
			}
		}
		return {x:j, y:q};
	}, parseStyle:function (j) {
		return this.styles.parse(j);
	}, serializeStyle:function (k, j) {
		return this.styles.serialize(k, j);
	}, loadCSS:function (j) {
		var l = this, m = l.doc, k;
		if (!j) {
			j = "";
		}
		k = l.select("head")[0];
		f(j.split(","), function (n) {
			var o;
			if (l.files[n]) {
				return;
			}
			l.files[n] = true;
			o = l.create("link", {rel:"stylesheet", href:d._addVer(n)});
			if (a && m.documentMode && m.recalc) {
				o.onload = function () {
					if (m.recalc) {
						m.recalc();
					}
					o.onload = null;
				};
			}
			k.appendChild(o);
		});
	}, addClass:function (j, k) {
		return this.run(j, function (l) {
			var m;
			if (!k) {
				return 0;
			}
			if (this.hasClass(l, k)) {
				return l.className;
			}
			m = this.removeClass(l, k);
			return l.className = (m != "" ? (m + " ") : "") + k;
		});
	}, removeClass:function (l, m) {
		var j = this, k;
		return j.run(l, function (o) {
			var n;
			if (j.hasClass(o, m)) {
				if (!k) {
					k = new RegExp("(^|\\s+)" + m + "(\\s+|$)", "g");
				}
				n = o.className.replace(k, " ");
				n = d.trim(n != " " ? n : "");
				o.className = n;
				if (!n) {
					o.removeAttribute("class");
					o.removeAttribute("className");
				}
				return n;
			}
			return o.className;
		});
	}, hasClass:function (k, j) {
		k = this.get(k);
		if (!k || !j) {
			return false;
		}
		return (" " + k.className + " ").indexOf(" " + j + " ") !== -1;
	}, show:function (j) {
		return this.setStyle(j, "display", "block");
	}, hide:function (j) {
		return this.setStyle(j, "display", "none");
	}, isHidden:function (j) {
		j = this.get(j);
		return !j || j.style.display == "none" || this.getStyle(j, "display") == "none";
	}, uniqueId:function (j) {
		return (!j ? "mce_" : j) + (this.counter++);
	}, setHTML:function (l, k) {
		var j = this;
		return j.run(l, function (n) {
			if (a) {
				while (n.firstChild) {
					n.removeChild(n.firstChild);
				}
				try {
					n.innerHTML = "<br />" + k;
					n.removeChild(n.firstChild);
				}
				catch (m) {
					n = j.create("div");
					n.innerHTML = "<br />" + k;
					f(n.childNodes, function (p, o) {
						if (o) {
							n.appendChild(p);
						}
					});
				}
			} else {
				n.innerHTML = k;
			}
			return k;
		});
	}, getOuterHTML:function (l) {
		var k, j = this;
		l = j.get(l);
		if (!l) {
			return null;
		}
		if (l.nodeType === 1 && j.hasOuterHTML) {
			return l.outerHTML;
		}
		k = (l.ownerDocument || j.doc).createElement("body");
		k.appendChild(l.cloneNode(true));
		return k.innerHTML;
	}, setOuterHTML:function (m, k, n) {
		var j = this;
		function l(p, o, r) {
			var s, q;
			q = r.createElement("body");
			q.innerHTML = o;
			s = q.lastChild;
			while (s) {
				j.insertAfter(s.cloneNode(true), p);
				s = s.previousSibling;
			}
			j.remove(p);
		}
		return this.run(m, function (p) {
			p = j.get(p);
			if (p.nodeType == 1) {
				n = n || p.ownerDocument || j.doc;
				if (a) {
					try {
						if (a && p.nodeType == 1) {
							p.outerHTML = k;
						} else {
							l(p, k, n);
						}
					}
					catch (o) {
						l(p, k, n);
					}
				} else {
					l(p, k, n);
				}
			}
		});
	}, decode:h.decode, encode:h.encodeAllRaw, insertAfter:function (j, k) {
		k = this.get(k);
		return this.run(j, function (m) {
			var l, n;
			l = k.parentNode;
			n = k.nextSibling;
			if (n) {
				l.insertBefore(m, n);
			} else {
				l.appendChild(m);
			}
			return m;
		});
	}, isBlock:function (k) {
		var j = k.nodeType;
		if (j) {
			return !!(j === 1 && g[k.nodeName]);
		}
		return !!g[k];
	}, replace:function (p, m, j) {
		var l = this;
		if (c(m, "array")) {
			p = p.cloneNode(true);
		}
		return l.run(m, function (k) {
			if (j) {
				f(d.grep(k.childNodes), function (n) {
					p.appendChild(n);
				});
			}
			return k.parentNode.replaceChild(p, k);
		});
	}, rename:function (m, j) {
		var l = this, k;
		if (m.nodeName != j.toUpperCase()) {
			k = l.create(j);
			f(l.getAttribs(m), function (n) {
				l.setAttrib(k, n.nodeName, l.getAttrib(m, n.nodeName));
			});
			l.replace(k, m, 1);
		}
		return k || m;
	}, findCommonAncestor:function (l, j) {
		var m = l, k;
		while (m) {
			k = j;
			while (k && m != k) {
				k = k.parentNode;
			}
			if (m == k) {
				break;
			}
			m = m.parentNode;
		}
		if (!m && l.ownerDocument) {
			return l.ownerDocument.documentElement;
		}
		return m;
	}, toHex:function (j) {
		var l = /^\s*rgb\s*?\(\s*?([0-9]+)\s*?,\s*?([0-9]+)\s*?,\s*?([0-9]+)\s*?\)\s*$/i.exec(j);
		function k(m) {
			m = parseInt(m).toString(16);
			return m.length > 1 ? m : "0" + m;
		}
		if (l) {
			j = "#" + k(l[1]) + k(l[2]) + k(l[3]);
			return j;
		}
		return j;
	}, getClasses:function () {
		var n = this, j = [], m, o = {}, p = n.settings.class_filter, l;
		if (n.classes) {
			return n.classes;
		}
		function q(r) {
			f(r.imports, function (s) {
				q(s);
			});
			f(r.cssRules || r.rules, function (s) {
				switch (s.type || 1) {
				  case 1:
					if (s.selectorText) {
						f(s.selectorText.split(","), function (t) {
							t = t.replace(/^\s*|\s*$|^\s\./g, "");
							if (/\.mce/.test(t) || !/\.[\w\-]+$/.test(t)) {
								return;
							}
							l = t;
							t = d._replace(/.*\.([a-z0-9_\-]+).*/i, "$1", t);
							if (p && !(t = p(t, l))) {
								return;
							}
							if (!o[t]) {
								j.push({"class":t});
								o[t] = 1;
							}
						});
					}
					break;
				  case 3:
					q(s.styleSheet);
					break;
				}
			});
		}
		try {
			f(n.doc.styleSheets, q);
		}
		catch (k) {
		}
		if (j.length > 0) {
			n.classes = j;
		}
		return j;
	}, run:function (m, l, k) {
		var j = this, n;
		if (j.doc && typeof (m) === "string") {
			m = j.get(m);
		}
		if (!m) {
			return false;
		}
		k = k || this;
		if (!m.nodeType && (m.length || m.length === 0)) {
			n = [];
			f(m, function (p, o) {
				if (p) {
					if (typeof (p) == "string") {
						p = j.doc.getElementById(p);
					}
					n.push(l.call(k, p, o));
				}
			});
			return n;
		}
		return l.call(k, m);
	}, getAttribs:function (k) {
		var j;
		k = this.get(k);
		if (!k) {
			return [];
		}
		if (a) {
			j = [];
			if (k.nodeName == "OBJECT") {
				return k.attributes;
			}
			if (k.nodeName === "OPTION" && this.getAttrib(k, "selected")) {
				j.push({specified:1, nodeName:"selected"});
			}
			k.cloneNode(false).outerHTML.replace(/<\/?[\w:\-]+ ?|=[\"][^\"]+\"|=\'[^\']+\'|=[\w\-]+|>/gi, "").replace(/[\w:\-]+/gi, function (l) {
				j.push({specified:1, nodeName:l});
			});
			return j;
		}
		return k.attributes;
	}, destroy:function (k) {
		var j = this;
		if (j.events) {
			j.events.destroy();
		}
		j.win = j.doc = j.root = j.events = null;
		if (!k) {
			d.removeUnload(j.destroy);
		}
	}, createRng:function () {
		var j = this.doc;
		return j.createRange ? j.createRange() : new d.dom.Range(this);
	}, nodeIndex:function (n, o) {
		var j = 0, l, m, k;
		if (n) {
			for (l = n.nodeType, n = n.previousSibling, m = n; n; n = n.previousSibling) {
				k = n.nodeType;
				if (o && k == 3) {
					if (k == l || !n.nodeValue.length) {
						continue;
					}
				}
				j++;
				l = k;
			}
		}
		return j;
	}, split:function (n, m, q) {
		var s = this, j = s.createRng(), o, l, p;
		function k(u) {
			var t, r = u.childNodes;
			if (u.nodeType == 1 && u.getAttribute("data-mce-type") == "bookmark") {
				return;
			}
			for (t = r.length - 1; t >= 0; t--) {
				k(r[t]);
			}
			if (u.nodeType != 9) {
				if (u.nodeType == 3 && u.nodeValue.length > 0) {
					if (!s.isBlock(u.parentNode) || d.trim(u.nodeValue).length > 0) {
						return;
					}
				}
				if (u.nodeType == 1) {
					r = u.childNodes;
					if (r.length == 1 && r[0] && r[0].nodeType == 1 && r[0].getAttribute("data-mce-type") == "bookmark") {
						u.parentNode.insertBefore(r[0], u);
					}
					if (r.length || /^(br|hr|input|img)$/i.test(u.nodeName)) {
						return;
					}
				}
				s.remove(u);
			}
			return u;
		}
		if (n && m) {
			j.setStart(n.parentNode, s.nodeIndex(n));
			j.setEnd(m.parentNode, s.nodeIndex(m));
			o = j.extractContents();
			j = s.createRng();
			j.setStart(m.parentNode, s.nodeIndex(m) + 1);
			j.setEnd(n.parentNode, s.nodeIndex(n) + 1);
			l = j.extractContents();
			p = n.parentNode;
			p.insertBefore(k(o), n);
			if (q) {
				p.replaceChild(q, m);
			} else {
				p.insertBefore(m, n);
			}
			p.insertBefore(k(l), n);
			s.remove(n);
			return q || m;
		}
	}, bind:function (n, j, m, l) {
		var k = this;
		if (!k.events) {
			k.events = new d.dom.EventUtils();
		}
		return k.events.add(n, j, m, l || this);
	}, unbind:function (m, j, l) {
		var k = this;
		if (!k.events) {
			k.events = new d.dom.EventUtils();
		}
		return k.events.remove(m, j, l);
	}, _findSib:function (m, j, k) {
		var l = this, n = j;
		if (m) {
			if (c(n, "string")) {
				n = function (o) {
					return l.is(o, j);
				};
			}
			for (m = m[k]; m; m = m[k]) {
				if (n(m)) {
					return m;
				}
			}
		}
		return null;
	}, _isRes:function (j) {
		return /^(top|left|bottom|right|width|height)/i.test(j) || /;\s*(top|left|bottom|right|width|height)/i.test(j);
	}});
	d.DOM = new d.dom.DOMUtils(document, {process_html:0});
})(tinymce);
(function (a) {
	function b(c) {
		var O = this, e = c.doc, T = 0, F = 1, k = 2, E = true, S = false, V = "startOffset", h = "startContainer", Q = "endContainer", A = "endOffset", l = tinymce.extend, o = c.nodeIndex;
		l(O, {startContainer:e, startOffset:0, endContainer:e, endOffset:0, collapsed:E, commonAncestorContainer:e, START_TO_START:0, START_TO_END:1, END_TO_END:2, END_TO_START:3, setStart:r, setEnd:u, setStartBefore:g, setStartAfter:J, setEndBefore:K, setEndAfter:v, collapse:B, selectNode:y, selectNodeContents:G, compareBoundaryPoints:x, deleteContents:q, extractContents:I, cloneContents:d, insertNode:D, surroundContents:N, cloneRange:L});
		function r(W, t) {
			C(E, W, t);
		}
		function u(W, t) {
			C(S, W, t);
		}
		function g(t) {
			r(t.parentNode, o(t));
		}
		function J(t) {
			r(t.parentNode, o(t) + 1);
		}
		function K(t) {
			u(t.parentNode, o(t));
		}
		function v(t) {
			u(t.parentNode, o(t) + 1);
		}
		function B(t) {
			if (t) {
				O[Q] = O[h];
				O[A] = O[V];
			} else {
				O[h] = O[Q];
				O[V] = O[A];
			}
			O.collapsed = E;
		}
		function y(t) {
			g(t);
			v(t);
		}
		function G(t) {
			r(t, 0);
			u(t, t.nodeType === 1 ? t.childNodes.length : t.nodeValue.length);
		}
		function x(Z, t) {
			var ac = O[h], X = O[V], ab = O[Q], W = O[A], aa = t.startContainer, ae = t.startOffset, Y = t.endContainer, ad = t.endOffset;
			if (Z === 0) {
				return H(ac, X, aa, ae);
			}
			if (Z === 1) {
				return H(ab, W, aa, ae);
			}
			if (Z === 2) {
				return H(ab, W, Y, ad);
			}
			if (Z === 3) {
				return H(ac, X, Y, ad);
			}
		}
		function q() {
			n(k);
		}
		function I() {
			return n(T);
		}
		function d() {
			return n(F);
		}
		function D(Z) {
			var W = this[h], t = this[V], Y, X;
			if ((W.nodeType === 3 || W.nodeType === 4) && W.nodeValue) {
				if (!t) {
					W.parentNode.insertBefore(Z, W);
				} else {
					if (t >= W.nodeValue.length) {
						c.insertAfter(Z, W);
					} else {
						Y = W.splitText(t);
						W.parentNode.insertBefore(Z, Y);
					}
				}
			} else {
				if (W.childNodes.length > 0) {
					X = W.childNodes[t];
				}
				if (X) {
					W.insertBefore(Z, X);
				} else {
					W.appendChild(Z);
				}
			}
		}
		function N(W) {
			var t = O.extractContents();
			O.insertNode(W);
			W.appendChild(t);
			O.selectNode(W);
		}
		function L() {
			return l(new b(c), {startContainer:O[h], startOffset:O[V], endContainer:O[Q], endOffset:O[A], collapsed:O.collapsed, commonAncestorContainer:O.commonAncestorContainer});
		}
		function P(t, W) {
			var X;
			if (t.nodeType == 3) {
				return t;
			}
			if (W < 0) {
				return t;
			}
			X = t.firstChild;
			while (X && W > 0) {
				--W;
				X = X.nextSibling;
			}
			if (X) {
				return X;
			}
			return t;
		}
		function m() {
			return (O[h] == O[Q] && O[V] == O[A]);
		}
		function H(Y, aa, W, Z) {
			var ab, X, t, ac, ae, ad;
			if (Y == W) {
				if (aa == Z) {
					return 0;
				}
				if (aa < Z) {
					return -1;
				}
				return 1;
			}
			ab = W;
			while (ab && ab.parentNode != Y) {
				ab = ab.parentNode;
			}
			if (ab) {
				X = 0;
				t = Y.firstChild;
				while (t != ab && X < aa) {
					X++;
					t = t.nextSibling;
				}
				if (aa <= X) {
					return -1;
				}
				return 1;
			}
			ab = Y;
			while (ab && ab.parentNode != W) {
				ab = ab.parentNode;
			}
			if (ab) {
				X = 0;
				t = W.firstChild;
				while (t != ab && X < Z) {
					X++;
					t = t.nextSibling;
				}
				if (X < Z) {
					return -1;
				}
				return 1;
			}
			ac = c.findCommonAncestor(Y, W);
			ae = Y;
			while (ae && ae.parentNode != ac) {
				ae = ae.parentNode;
			}
			if (!ae) {
				ae = ac;
			}
			ad = W;
			while (ad && ad.parentNode != ac) {
				ad = ad.parentNode;
			}
			if (!ad) {
				ad = ac;
			}
			if (ae == ad) {
				return 0;
			}
			t = ac.firstChild;
			while (t) {
				if (t == ae) {
					return -1;
				}
				if (t == ad) {
					return 1;
				}
				t = t.nextSibling;
			}
		}
		function C(W, Z, Y) {
			var t, X;
			if (W) {
				O[h] = Z;
				O[V] = Y;
			} else {
				O[Q] = Z;
				O[A] = Y;
			}
			t = O[Q];
			while (t.parentNode) {
				t = t.parentNode;
			}
			X = O[h];
			while (X.parentNode) {
				X = X.parentNode;
			}
			if (X == t) {
				if (H(O[h], O[V], O[Q], O[A]) > 0) {
					O.collapse(W);
				}
			} else {
				O.collapse(W);
			}
			O.collapsed = m();
			O.commonAncestorContainer = c.findCommonAncestor(O[h], O[Q]);
		}
		function n(ac) {
			var ab, Y = 0, ae = 0, W, aa, X, Z, t, ad;
			if (O[h] == O[Q]) {
				return f(ac);
			}
			for (ab = O[Q], W = ab.parentNode; W; ab = W, W = W.parentNode) {
				if (W == O[h]) {
					return s(ab, ac);
				}
				++Y;
			}
			for (ab = O[h], W = ab.parentNode; W; ab = W, W = W.parentNode) {
				if (W == O[Q]) {
					return U(ab, ac);
				}
				++ae;
			}
			aa = ae - Y;
			X = O[h];
			while (aa > 0) {
				X = X.parentNode;
				aa--;
			}
			Z = O[Q];
			while (aa < 0) {
				Z = Z.parentNode;
				aa++;
			}
			for (t = X.parentNode, ad = Z.parentNode; t != ad; t = t.parentNode, ad = ad.parentNode) {
				X = t;
				Z = ad;
			}
			return p(X, Z, ac);
		}
		function f(aa) {
			var ac, Z, Y, ab, t, X, W;
			if (aa != k) {
				ac = e.createDocumentFragment();
			}
			if (O[V] == O[A]) {
				return ac;
			}
			if (O[h].nodeType == 3) {
				Z = O[h].nodeValue;
				Y = Z.substring(O[V], O[A]);
				if (aa != F) {
					O[h].deleteData(O[V], O[A] - O[V]);
					O.collapse(E);
				}
				if (aa == k) {
					return;
				}
				ac.appendChild(e.createTextNode(Y));
				return ac;
			}
			ab = P(O[h], O[V]);
			t = O[A] - O[V];
			while (t > 0) {
				X = ab.nextSibling;
				W = z(ab, aa);
				if (ac) {
					ac.appendChild(W);
				}
				--t;
				ab = X;
			}
			if (aa != F) {
				O.collapse(E);
			}
			return ac;
		}
		function s(ac, Z) {
			var ab, aa, W, t, Y, X;
			if (Z != k) {
				ab = e.createDocumentFragment();
			}
			aa = j(ac, Z);
			if (ab) {
				ab.appendChild(aa);
			}
			W = o(ac);
			t = W - O[V];
			if (t <= 0) {
				if (Z != F) {
					O.setEndBefore(ac);
					O.collapse(S);
				}
				return ab;
			}
			aa = ac.previousSibling;
			while (t > 0) {
				Y = aa.previousSibling;
				X = z(aa, Z);
				if (ab) {
					ab.insertBefore(X, ab.firstChild);
				}
				--t;
				aa = Y;
			}
			if (Z != F) {
				O.setEndBefore(ac);
				O.collapse(S);
			}
			return ab;
		}
		function U(aa, Z) {
			var ac, W, ab, t, Y, X;
			if (Z != k) {
				ac = e.createDocumentFragment();
			}
			ab = R(aa, Z);
			if (ac) {
				ac.appendChild(ab);
			}
			W = o(aa);
			++W;
			t = O[A] - W;
			ab = aa.nextSibling;
			while (t > 0) {
				Y = ab.nextSibling;
				X = z(ab, Z);
				if (ac) {
					ac.appendChild(X);
				}
				--t;
				ab = Y;
			}
			if (Z != F) {
				O.setStartAfter(aa);
				O.collapse(E);
			}
			return ac;
		}
		function p(aa, t, ad) {
			var X, af, Z, ab, ac, W, ae, Y;
			if (ad != k) {
				af = e.createDocumentFragment();
			}
			X = R(aa, ad);
			if (af) {
				af.appendChild(X);
			}
			Z = aa.parentNode;
			ab = o(aa);
			ac = o(t);
			++ab;
			W = ac - ab;
			ae = aa.nextSibling;
			while (W > 0) {
				Y = ae.nextSibling;
				X = z(ae, ad);
				if (af) {
					af.appendChild(X);
				}
				ae = Y;
				--W;
			}
			X = j(t, ad);
			if (af) {
				af.appendChild(X);
			}
			if (ad != F) {
				O.setStartAfter(aa);
				O.collapse(E);
			}
			return af;
		}
		function j(ab, ac) {
			var X = P(O[Q], O[A] - 1), ad, aa, Z, t, W, Y = X != O[Q];
			if (X == ab) {
				return M(X, Y, S, ac);
			}
			ad = X.parentNode;
			aa = M(ad, S, S, ac);
			while (ad) {
				while (X) {
					Z = X.previousSibling;
					t = M(X, Y, S, ac);
					if (ac != k) {
						aa.insertBefore(t, aa.firstChild);
					}
					Y = E;
					X = Z;
				}
				if (ad == ab) {
					return aa;
				}
				X = ad.previousSibling;
				ad = ad.parentNode;
				W = M(ad, S, S, ac);
				if (ac != k) {
					W.appendChild(aa);
				}
				aa = W;
			}
		}
		function R(ab, ac) {
			var Y = P(O[h], O[V]), Z = Y != O[h], ad, aa, X, t, W;
			if (Y == ab) {
				return M(Y, Z, E, ac);
			}
			ad = Y.parentNode;
			aa = M(ad, S, E, ac);
			while (ad) {
				while (Y) {
					X = Y.nextSibling;
					t = M(Y, Z, E, ac);
					if (ac != k) {
						aa.appendChild(t);
					}
					Z = E;
					Y = X;
				}
				if (ad == ab) {
					return aa;
				}
				Y = ad.nextSibling;
				ad = ad.parentNode;
				W = M(ad, S, E, ac);
				if (ac != k) {
					W.appendChild(aa);
				}
				aa = W;
			}
		}
		function M(t, Z, ac, ad) {
			var Y, X, aa, W, ab;
			if (Z) {
				return z(t, ad);
			}
			if (t.nodeType == 3) {
				Y = t.nodeValue;
				if (ac) {
					W = O[V];
					X = Y.substring(W);
					aa = Y.substring(0, W);
				} else {
					W = O[A];
					X = Y.substring(0, W);
					aa = Y.substring(W);
				}
				if (ad != F) {
					t.nodeValue = aa;
				}
				if (ad == k) {
					return;
				}
				ab = t.cloneNode(S);
				ab.nodeValue = X;
				return ab;
			}
			if (ad == k) {
				return;
			}
			return t.cloneNode(S);
		}
		function z(W, t) {
			if (t != k) {
				return t == F ? W.cloneNode(E) : W;
			}
			W.parentNode.removeChild(W);
		}
	}
	a.Range = b;
})(tinymce.dom);
(function () {
	function a(g) {
		var j = this, k = "\ufeff", e, h, d = g.dom, c = true, f = false;
		function b() {
			var o = g.getRng(), l = d.createRng(), n, p;
			n = o.item ? o.item(0) : o.parentElement();
			if (n.ownerDocument != d.doc) {
				return l;
			}
			p = g.isCollapsed();
			if (o.item || !n.hasChildNodes()) {
				if (p) {
					l.setStart(n, 0);
					l.setEnd(n, 0);
				} else {
					l.setStart(n.parentNode, d.nodeIndex(n));
					l.setEnd(l.startContainer, l.startOffset + 1);
				}
				return l;
			}
			function m(t) {
				var v, r, u, q, B = 0, y, z, A, s, x;
				s = o.duplicate();
				s.collapse(t);
				v = d.create("a");
				A = s.parentElement();
				if (!A.hasChildNodes()) {
					l[t ? "setStart" : "setEnd"](A, 0);
					return;
				}
				A.appendChild(v);
				s.moveToElementText(v);
				x = o.compareEndPoints(t ? "StartToStart" : "EndToEnd", s);
				if (x > 0) {
					l[t ? "setStartAfter" : "setEndAfter"](A);
					d.remove(v);
					return;
				}
				q = tinymce.grep(A.childNodes);
				y = q.length - 1;
				while (B <= y) {
					z = Math.floor((B + y) / 2);
					A.insertBefore(v, q[z]);
					s.moveToElementText(v);
					x = o.compareEndPoints(t ? "StartToStart" : "EndToEnd", s);
					if (x > 0) {
						B = z + 1;
					} else {
						if (x < 0) {
							y = z - 1;
						} else {
							found = true;
							break;
						}
					}
				}
				r = x > 0 || z == 0 ? v.nextSibling : v.previousSibling;
				if (r.nodeType == 1) {
					d.remove(v);
					u = d.nodeIndex(r);
					r = r.parentNode;
					if (!t || z > 0) {
						u++;
					}
				} else {
					if (x > 0 || z == 0) {
						s.setEndPoint(t ? "StartToStart" : "EndToEnd", o);
						u = s.text.length;
					} else {
						s.setEndPoint(t ? "StartToStart" : "EndToEnd", o);
						u = r.nodeValue.length - s.text.length;
					}
					d.remove(v);
				}
				l[t ? "setStart" : "setEnd"](r, u);
			}
			m(true);
			if (!p) {
				m();
			}
			return l;
		}
		this.addRange = function (l) {
			var q, o, n, s, v, t, u = g.dom.doc, p = u.body;
			function m(C) {
				var y, B, x, A, z;
				x = d.create("a");
				y = C ? n : v;
				B = C ? s : t;
				A = q.duplicate();
				if (y == u) {
					y = p;
					B = 0;
				}
				if (y.nodeType == 3) {
					y.parentNode.insertBefore(x, y);
					A.moveToElementText(x);
					A.moveStart("character", B);
					d.remove(x);
					q.setEndPoint(C ? "StartToStart" : "EndToEnd", A);
				} else {
					z = y.childNodes;
					if (z.length) {
						if (B >= z.length) {
							d.insertAfter(x, z[z.length - 1]);
						} else {
							y.insertBefore(x, z[B]);
						}
						A.moveToElementText(x);
					} else {
						x = u.createTextNode(k);
						y.appendChild(x);
						A.moveToElementText(x.parentNode);
						A.collapse(c);
					}
					q.setEndPoint(C ? "StartToStart" : "EndToEnd", A);
					d.remove(x);
				}
			}
			this.destroy();
			n = l.startContainer;
			s = l.startOffset;
			v = l.endContainer;
			t = l.endOffset;
			q = p.createTextRange();
			if (n == v && n.nodeType == 1 && s == t - 1) {
				if (s == t - 1) {
					try {
						o = p.createControlRange();
						o.addElement(n.childNodes[s]);
						o.select();
						return;
					}
					catch (r) {
					}
				}
			}
			m(true);
			m();
			q.select();
		};
		this.getRangeAt = function () {
			if (!e || !tinymce.dom.RangeUtils.compareRanges(h, g.getRng())) {
				e = b();
				h = g.getRng();
			}
			try {
				e.startContainer.nextSibling;
			}
			catch (l) {
				e = b();
				h = null;
			}
			return e;
		};
		this.destroy = function () {
			h = e = null;
		};
	}
	tinymce.dom.TridentSelection = a;
})();
(function () {
	var q = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g, k = 0, d = Object.prototype.toString, p = false, j = true;
	[0, 0].sort(function () {
		j = false;
		return 0;
	});
	var b = function (x, e, A, B) {
		A = A || [];
		e = e || document;
		var D = e;
		if (e.nodeType !== 1 && e.nodeType !== 9) {
			return [];
		}
		if (!x || typeof x !== "string") {
			return A;
		}
		var y = [], t, F, I, s, v = true, u = b.isXML(e), C = x, E, H, G, z;
		do {
			q.exec("");
			t = q.exec(C);
			if (t) {
				C = t[3];
				y.push(t[1]);
				if (t[2]) {
					s = t[3];
					break;
				}
			}
		} while (t);
		if (y.length > 1 && l.exec(x)) {
			if (y.length === 2 && f.relative[y[0]]) {
				F = h(y[0] + y[1], e);
			} else {
				F = f.relative[y[0]] ? [e] : b(y.shift(), e);
				while (y.length) {
					x = y.shift();
					if (f.relative[x]) {
						x += y.shift();
					}
					F = h(x, F);
				}
			}
		} else {
			if (!B && y.length > 1 && e.nodeType === 9 && !u && f.match.ID.test(y[0]) && !f.match.ID.test(y[y.length - 1])) {
				E = b.find(y.shift(), e, u);
				e = E.expr ? b.filter(E.expr, E.set)[0] : E.set[0];
			}
			if (e) {
				E = B ? {expr:y.pop(), set:a(B)} : b.find(y.pop(), y.length === 1 && (y[0] === "~" || y[0] === "+") && e.parentNode ? e.parentNode : e, u);
				F = E.expr ? b.filter(E.expr, E.set) : E.set;
				if (y.length > 0) {
					I = a(F);
				} else {
					v = false;
				}
				while (y.length) {
					H = y.pop();
					G = H;
					if (!f.relative[H]) {
						H = "";
					} else {
						G = y.pop();
					}
					if (G == null) {
						G = e;
					}
					f.relative[H](I, G, u);
				}
			} else {
				I = y = [];
			}
		}
		if (!I) {
			I = F;
		}
		if (!I) {
			b.error(H || x);
		}
		if (d.call(I) === "[object Array]") {
			if (!v) {
				A.push.apply(A, I);
			} else {
				if (e && e.nodeType === 1) {
					for (z = 0; I[z] != null; z++) {
						if (I[z] && (I[z] === true || I[z].nodeType === 1 && b.contains(e, I[z]))) {
							A.push(F[z]);
						}
					}
				} else {
					for (z = 0; I[z] != null; z++) {
						if (I[z] && I[z].nodeType === 1) {
							A.push(F[z]);
						}
					}
				}
			}
		} else {
			a(I, A);
		}
		if (s) {
			b(s, D, A, B);
			b.uniqueSort(A);
		}
		return A;
	};
	b.uniqueSort = function (s) {
		if (c) {
			p = j;
			s.sort(c);
			if (p) {
				for (var e = 1; e < s.length; e++) {
					if (s[e] === s[e - 1]) {
						s.splice(e--, 1);
					}
				}
			}
		}
		return s;
	};
	b.matches = function (e, s) {
		return b(e, null, null, s);
	};
	b.find = function (z, e, A) {
		var y;
		if (!z) {
			return [];
		}
		for (var u = 0, t = f.order.length; u < t; u++) {
			var x = f.order[u], v;
			if ((v = f.leftMatch[x].exec(z))) {
				var s = v[1];
				v.splice(1, 1);
				if (s.substr(s.length - 1) !== "\\") {
					v[1] = (v[1] || "").replace(/\\/g, "");
					y = f.find[x](v, e, A);
					if (y != null) {
						z = z.replace(f.match[x], "");
						break;
					}
				}
			}
		}
		if (!y) {
			y = e.getElementsByTagName("*");
		}
		return {set:y, expr:z};
	};
	b.filter = function (D, C, G, v) {
		var t = D, I = [], A = C, y, e, z = C && C[0] && b.isXML(C[0]);
		while (D && C.length) {
			for (var B in f.filter) {
				if ((y = f.leftMatch[B].exec(D)) != null && y[2]) {
					var s = f.filter[B], H, F, u = y[1];
					e = false;
					y.splice(1, 1);
					if (u.substr(u.length - 1) === "\\") {
						continue;
					}
					if (A === I) {
						I = [];
					}
					if (f.preFilter[B]) {
						y = f.preFilter[B](y, A, G, I, v, z);
						if (!y) {
							e = H = true;
						} else {
							if (y === true) {
								continue;
							}
						}
					}
					if (y) {
						for (var x = 0; (F = A[x]) != null; x++) {
							if (F) {
								H = s(F, y, x, A);
								var E = v ^ !!H;
								if (G && H != null) {
									if (E) {
										e = true;
									} else {
										A[x] = false;
									}
								} else {
									if (E) {
										I.push(F);
										e = true;
									}
								}
							}
						}
					}
					if (H !== undefined) {
						if (!G) {
							A = I;
						}
						D = D.replace(f.match[B], "");
						if (!e) {
							return [];
						}
						break;
					}
				}
			}
			if (D === t) {
				if (e == null) {
					b.error(D);
				} else {
					break;
				}
			}
			t = D;
		}
		return A;
	};
	b.error = function (e) {
		throw "Syntax error, unrecognized expression: " + e;
	};
	var f = b.selectors = {order:["ID", "NAME", "TAG"], match:{ID:/#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/, CLASS:/\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/, NAME:/\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/, ATTR:/\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/, TAG:/^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/, CHILD:/:(only|nth|last|first)-child(?:\((even|odd|[\dn+\-]*)\))?/, POS:/:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/, PSEUDO:/:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/}, leftMatch:{}, attrMap:{"class":"className", "for":"htmlFor"}, attrHandle:{href:function (e) {
		return e.getAttribute("href");
	}}, relative:{"+":function (y, s) {
		var u = typeof s === "string", x = u && !/\W/.test(s), z = u && !x;
		if (x) {
			s = s.toLowerCase();
		}
		for (var t = 0, e = y.length, v; t < e; t++) {
			if ((v = y[t])) {
				while ((v = v.previousSibling) && v.nodeType !== 1) {
				}
				y[t] = z || v && v.nodeName.toLowerCase() === s ? v || false : v === s;
			}
		}
		if (z) {
			b.filter(s, y, true);
		}
	}, ">":function (y, s) {
		var v = typeof s === "string", x, t = 0, e = y.length;
		if (v && !/\W/.test(s)) {
			s = s.toLowerCase();
			for (; t < e; t++) {
				x = y[t];
				if (x) {
					var u = x.parentNode;
					y[t] = u.nodeName.toLowerCase() === s ? u : false;
				}
			}
		} else {
			for (; t < e; t++) {
				x = y[t];
				if (x) {
					y[t] = v ? x.parentNode : x.parentNode === s;
				}
			}
			if (v) {
				b.filter(s, y, true);
			}
		}
	}, "":function (u, s, x) {
		var t = k++, e = r, v;
		if (typeof s === "string" && !/\W/.test(s)) {
			s = s.toLowerCase();
			v = s;
			e = o;
		}
		e("parentNode", s, t, u, v, x);
	}, "~":function (u, s, x) {
		var t = k++, e = r, v;
		if (typeof s === "string" && !/\W/.test(s)) {
			s = s.toLowerCase();
			v = s;
			e = o;
		}
		e("previousSibling", s, t, u, v, x);
	}}, find:{ID:function (s, t, u) {
		if (typeof t.getElementById !== "undefined" && !u) {
			var e = t.getElementById(s[1]);
			return e ? [e] : [];
		}
	}, NAME:function (t, x) {
		if (typeof x.getElementsByName !== "undefined") {
			var s = [], v = x.getElementsByName(t[1]);
			for (var u = 0, e = v.length; u < e; u++) {
				if (v[u].getAttribute("name") === t[1]) {
					s.push(v[u]);
				}
			}
			return s.length === 0 ? null : s;
		}
	}, TAG:function (e, s) {
		return s.getElementsByTagName(e[1]);
	}}, preFilter:{CLASS:function (u, s, t, e, y, z) {
		u = " " + u[1].replace(/\\/g, "") + " ";
		if (z) {
			return u;
		}
		for (var v = 0, x; (x = s[v]) != null; v++) {
			if (x) {
				if (y ^ (x.className && (" " + x.className + " ").replace(/[\t\n]/g, " ").indexOf(u) >= 0)) {
					if (!t) {
						e.push(x);
					}
				} else {
					if (t) {
						s[v] = false;
					}
				}
			}
		}
		return false;
	}, ID:function (e) {
		return e[1].replace(/\\/g, "");
	}, TAG:function (s, e) {
		return s[1].toLowerCase();
	}, CHILD:function (e) {
		if (e[1] === "nth") {
			var s = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(e[2] === "even" && "2n" || e[2] === "odd" && "2n+1" || !/\D/.test(e[2]) && "0n+" + e[2] || e[2]);
			e[2] = (s[1] + (s[2] || 1)) - 0;
			e[3] = s[3] - 0;
		}
		e[0] = k++;
		return e;
	}, ATTR:function (v, s, t, e, x, y) {
		var u = v[1].replace(/\\/g, "");
		if (!y && f.attrMap[u]) {
			v[1] = f.attrMap[u];
		}
		if (v[2] === "~=") {
			v[4] = " " + v[4] + " ";
		}
		return v;
	}, PSEUDO:function (v, s, t, e, x) {
		if (v[1] === "not") {
			if ((q.exec(v[3]) || "").length > 1 || /^\w/.test(v[3])) {
				v[3] = b(v[3], null, null, s);
			} else {
				var u = b.filter(v[3], s, t, true ^ x);
				if (!t) {
					e.push.apply(e, u);
				}
				return false;
			}
		} else {
			if (f.match.POS.test(v[0]) || f.match.CHILD.test(v[0])) {
				return true;
			}
		}
		return v;
	}, POS:function (e) {
		e.unshift(true);
		return e;
	}}, filters:{enabled:function (e) {
		return e.disabled === false && e.type !== "hidden";
	}, disabled:function (e) {
		return e.disabled === true;
	}, checked:function (e) {
		return e.checked === true;
	}, selected:function (e) {
		e.parentNode.selectedIndex;
		return e.selected === true;
	}, parent:function (e) {
		return !!e.firstChild;
	}, empty:function (e) {
		return !e.firstChild;
	}, has:function (t, s, e) {
		return !!b(e[3], t).length;
	}, header:function (e) {
		return (/h\d/i).test(e.nodeName);
	}, text:function (e) {
		return "text" === e.type;
	}, radio:function (e) {
		return "radio" === e.type;
	}, checkbox:function (e) {
		return "checkbox" === e.type;
	}, file:function (e) {
		return "file" === e.type;
	}, password:function (e) {
		return "password" === e.type;
	}, submit:function (e) {
		return "submit" === e.type;
	}, image:function (e) {
		return "image" === e.type;
	}, reset:function (e) {
		return "reset" === e.type;
	}, button:function (e) {
		return "button" === e.type || e.nodeName.toLowerCase() === "button";
	}, input:function (e) {
		return (/input|select|textarea|button/i).test(e.nodeName);
	}}, setFilters:{first:function (s, e) {
		return e === 0;
	}, last:function (t, s, e, u) {
		return s === u.length - 1;
	}, even:function (s, e) {
		return e % 2 === 0;
	}, odd:function (s, e) {
		return e % 2 === 1;
	}, lt:function (t, s, e) {
		return s < e[3] - 0;
	}, gt:function (t, s, e) {
		return s > e[3] - 0;
	}, nth:function (t, s, e) {
		return e[3] - 0 === s;
	}, eq:function (t, s, e) {
		return e[3] - 0 === s;
	}}, filter:{PSEUDO:function (t, z, y, A) {
		var e = z[1], s = f.filters[e];
		if (s) {
			return s(t, y, z, A);
		} else {
			if (e === "contains") {
				return (t.textContent || t.innerText || b.getText([t]) || "").indexOf(z[3]) >= 0;
			} else {
				if (e === "not") {
					var u = z[3];
					for (var x = 0, v = u.length; x < v; x++) {
						if (u[x] === t) {
							return false;
						}
					}
					return true;
				} else {
					b.error("Syntax error, unrecognized expression: " + e);
				}
			}
		}
	}, CHILD:function (e, u) {
		var y = u[1], s = e;
		switch (y) {
		  case "only":
		  case "first":
			while ((s = s.previousSibling)) {
				if (s.nodeType === 1) {
					return false;
				}
			}
			if (y === "first") {
				return true;
			}
			s = e;
		  case "last":
			while ((s = s.nextSibling)) {
				if (s.nodeType === 1) {
					return false;
				}
			}
			return true;
		  case "nth":
			var t = u[2], B = u[3];
			if (t === 1 && B === 0) {
				return true;
			}
			var x = u[0], A = e.parentNode;
			if (A && (A.sizcache !== x || !e.nodeIndex)) {
				var v = 0;
				for (s = A.firstChild; s; s = s.nextSibling) {
					if (s.nodeType === 1) {
						s.nodeIndex = ++v;
					}
				}
				A.sizcache = x;
			}
			var z = e.nodeIndex - B;
			if (t === 0) {
				return z === 0;
			} else {
				return (z % t === 0 && z / t >= 0);
			}
		}
	}, ID:function (s, e) {
		return s.nodeType === 1 && s.getAttribute("id") === e;
	}, TAG:function (s, e) {
		return (e === "*" && s.nodeType === 1) || s.nodeName.toLowerCase() === e;
	}, CLASS:function (s, e) {
		return (" " + (s.className || s.getAttribute("class")) + " ").indexOf(e) > -1;
	}, ATTR:function (x, u) {
		var t = u[1], e = f.attrHandle[t] ? f.attrHandle[t](x) : x[t] != null ? x[t] : x.getAttribute(t), y = e + "", v = u[2], s = u[4];
		return e == null ? v === "!=" : v === "=" ? y === s : v === "*=" ? y.indexOf(s) >= 0 : v === "~=" ? (" " + y + " ").indexOf(s) >= 0 : !s ? y && e !== false : v === "!=" ? y !== s : v === "^=" ? y.indexOf(s) === 0 : v === "$=" ? y.substr(y.length - s.length) === s : v === "|=" ? y === s || y.substr(0, s.length + 1) === s + "-" : false;
	}, POS:function (v, s, t, x) {
		var e = s[2], u = f.setFilters[e];
		if (u) {
			return u(v, t, s, x);
		}
	}}};
	var l = f.match.POS, g = function (s, e) {
		return "\\" + (e - 0 + 1);
	};
	for (var n in f.match) {
		f.match[n] = new RegExp(f.match[n].source + (/(?![^\[]*\])(?![^\(]*\))/.source));
		f.leftMatch[n] = new RegExp(/(^(?:.|\r|\n)*?)/.source + f.match[n].source.replace(/\\(\d+)/g, g));
	}
	var a = function (s, e) {
		s = Array.prototype.slice.call(s, 0);
		if (e) {
			e.push.apply(e, s);
			return e;
		}
		return s;
	};
	try {
		Array.prototype.slice.call(document.documentElement.childNodes, 0)[0].nodeType;
	}
	catch (m) {
		a = function (v, u) {
			var s = u || [], t = 0;
			if (d.call(v) === "[object Array]") {
				Array.prototype.push.apply(s, v);
			} else {
				if (typeof v.length === "number") {
					for (var e = v.length; t < e; t++) {
						s.push(v[t]);
					}
				} else {
					for (; v[t]; t++) {
						s.push(v[t]);
					}
				}
			}
			return s;
		};
	}
	var c;
	if (document.documentElement.compareDocumentPosition) {
		c = function (s, e) {
			if (!s.compareDocumentPosition || !e.compareDocumentPosition) {
				if (s == e) {
					p = true;
				}
				return s.compareDocumentPosition ? -1 : 1;
			}
			var t = s.compareDocumentPosition(e) & 4 ? -1 : s === e ? 0 : 1;
			if (t === 0) {
				p = true;
			}
			return t;
		};
	} else {
		if ("sourceIndex" in document.documentElement) {
			c = function (s, e) {
				if (!s.sourceIndex || !e.sourceIndex) {
					if (s == e) {
						p = true;
					}
					return s.sourceIndex ? -1 : 1;
				}
				var t = s.sourceIndex - e.sourceIndex;
				if (t === 0) {
					p = true;
				}
				return t;
			};
		} else {
			if (document.createRange) {
				c = function (u, s) {
					if (!u.ownerDocument || !s.ownerDocument) {
						if (u == s) {
							p = true;
						}
						return u.ownerDocument ? -1 : 1;
					}
					var t = u.ownerDocument.createRange(), e = s.ownerDocument.createRange();
					t.setStart(u, 0);
					t.setEnd(u, 0);
					e.setStart(s, 0);
					e.setEnd(s, 0);
					var v = t.compareBoundaryPoints(Range.START_TO_END, e);
					if (v === 0) {
						p = true;
					}
					return v;
				};
			}
		}
	}
	b.getText = function (e) {
		var s = "", u;
		for (var t = 0; e[t]; t++) {
			u = e[t];
			if (u.nodeType === 3 || u.nodeType === 4) {
				s += u.nodeValue;
			} else {
				if (u.nodeType !== 8) {
					s += b.getText(u.childNodes);
				}
			}
		}
		return s;
	};
	(function () {
		var s = document.createElement("div"), t = "script" + (new Date()).getTime();
		s.innerHTML = "<a name='" + t + "'/>";
		var e = document.documentElement;
		e.insertBefore(s, e.firstChild);
		if (document.getElementById(t)) {
			f.find.ID = function (v, x, y) {
				if (typeof x.getElementById !== "undefined" && !y) {
					var u = x.getElementById(v[1]);
					return u ? u.id === v[1] || typeof u.getAttributeNode !== "undefined" && u.getAttributeNode("id").nodeValue === v[1] ? [u] : undefined : [];
				}
			};
			f.filter.ID = function (x, u) {
				var v = typeof x.getAttributeNode !== "undefined" && x.getAttributeNode("id");
				return x.nodeType === 1 && v && v.nodeValue === u;
			};
		}
		e.removeChild(s);
		e = s = null;
	})();
	(function () {
		var e = document.createElement("div");
		e.appendChild(document.createComment(""));
		if (e.getElementsByTagName("*").length > 0) {
			f.find.TAG = function (s, x) {
				var v = x.getElementsByTagName(s[1]);
				if (s[1] === "*") {
					var u = [];
					for (var t = 0; v[t]; t++) {
						if (v[t].nodeType === 1) {
							u.push(v[t]);
						}
					}
					v = u;
				}
				return v;
			};
		}
		e.innerHTML = "<a href='#'></a>";
		if (e.firstChild && typeof e.firstChild.getAttribute !== "undefined" && e.firstChild.getAttribute("href") !== "#") {
			f.attrHandle.href = function (s) {
				return s.getAttribute("href", 2);
			};
		}
		e = null;
	})();
	if (document.querySelectorAll) {
		(function () {
			var e = b, t = document.createElement("div");
			t.innerHTML = "<p class='TEST'></p>";
			if (t.querySelectorAll && t.querySelectorAll(".TEST").length === 0) {
				return;
			}
			b = function (y, x, u, v) {
				x = x || document;
				if (!v && x.nodeType === 9 && !b.isXML(x)) {
					try {
						return a(x.querySelectorAll(y), u);
					}
					catch (z) {
					}
				}
				return e(y, x, u, v);
			};
			for (var s in e) {
				b[s] = e[s];
			}
			t = null;
		})();
	}
	(function () {
		var e = document.createElement("div");
		e.innerHTML = "<div class='test e'></div><div class='test'></div>";
		if (!e.getElementsByClassName || e.getElementsByClassName("e").length === 0) {
			return;
		}
		e.lastChild.className = "e";
		if (e.getElementsByClassName("e").length === 1) {
			return;
		}
		f.order.splice(1, 0, "CLASS");
		f.find.CLASS = function (s, t, u) {
			if (typeof t.getElementsByClassName !== "undefined" && !u) {
				return t.getElementsByClassName(s[1]);
			}
		};
		e = null;
	})();
	function o(s, y, x, B, z, A) {
		for (var u = 0, t = B.length; u < t; u++) {
			var e = B[u];
			if (e) {
				e = e[s];
				var v = false;
				while (e) {
					if (e.sizcache === x) {
						v = B[e.sizset];
						break;
					}
					if (e.nodeType === 1 && !A) {
						e.sizcache = x;
						e.sizset = u;
					}
					if (e.nodeName.toLowerCase() === y) {
						v = e;
						break;
					}
					e = e[s];
				}
				B[u] = v;
			}
		}
	}
	function r(s, y, x, B, z, A) {
		for (var u = 0, t = B.length; u < t; u++) {
			var e = B[u];
			if (e) {
				e = e[s];
				var v = false;
				while (e) {
					if (e.sizcache === x) {
						v = B[e.sizset];
						break;
					}
					if (e.nodeType === 1) {
						if (!A) {
							e.sizcache = x;
							e.sizset = u;
						}
						if (typeof y !== "string") {
							if (e === y) {
								v = true;
								break;
							}
						} else {
							if (b.filter(y, [e]).length > 0) {
								v = e;
								break;
							}
						}
					}
					e = e[s];
				}
				B[u] = v;
			}
		}
	}
	b.contains = document.compareDocumentPosition ? function (s, e) {
		return !!(s.compareDocumentPosition(e) & 16);
	} : function (s, e) {
		return s !== e && (s.contains ? s.contains(e) : true);
	};
	b.isXML = function (e) {
		var s = (e ? e.ownerDocument || e : 0).documentElement;
		return s ? s.nodeName !== "HTML" : false;
	};
	var h = function (e, z) {
		var u = [], v = "", x, t = z.nodeType ? [z] : z;
		while ((x = f.match.PSEUDO.exec(e))) {
			v += x[0];
			e = e.replace(f.match.PSEUDO, "");
		}
		e = f.relative[e] ? e + "*" : e;
		for (var y = 0, s = t.length; y < s; y++) {
			b(e, t[y], u);
		}
		return b.filter(v, u);
	};
	window.tinymce.dom.Sizzle = b;
})();
(function (d) {
	var f = d.each, c = d.DOM, b = d.isIE, e = d.isWebKit, a;
	d.create("tinymce.dom.EventUtils", {EventUtils:function () {
		this.inits = [];
		this.events = [];
	}, add:function (p, q, m, k) {
		var g, h = this, j = h.events, l;
		if (q instanceof Array) {
			l = [];
			f(q, function (o) {
				l.push(h.add(p, o, m, k));
			});
			return l;
		}
		if (p && p.hasOwnProperty && p instanceof Array) {
			l = [];
			f(p, function (n) {
				n = c.get(n);
				l.push(h.add(n, q, m, k));
			});
			return l;
		}
		p = c.get(p);
		if (!p) {
			return;
		}
		g = function (n) {
			if (h.disabled) {
				return;
			}
			n = n || window.event;
			if (n && b) {
				if (!n.target) {
					n.target = n.srcElement;
				}
				d.extend(n, h._stoppers);
			}
			if (!k) {
				return m(n);
			}
			return m.call(k, n);
		};
		if (q == "unload") {
			d.unloads.unshift({func:g});
			return g;
		}
		if (q == "init") {
			if (h.domLoaded) {
				g();
			} else {
				h.inits.push(g);
			}
			return g;
		}
		j.push({obj:p, name:q, func:m, cfunc:g, scope:k});
		h._add(p, q, g);
		return m;
	}, remove:function (m, p, l) {
		var h = this, g = h.events, j = false, k;
		if (m && m.hasOwnProperty && m instanceof Array) {
			k = [];
			f(m, function (n) {
				n = c.get(n);
				k.push(h.remove(n, p, l));
			});
			return k;
		}
		m = c.get(m);
		f(g, function (o, n) {
			if (o.obj == m && o.name == p && (!l || (o.func == l || o.cfunc == l))) {
				g.splice(n, 1);
				h._remove(m, p, o.cfunc);
				j = true;
				return false;
			}
		});
		return j;
	}, clear:function (l) {
		var j = this, g = j.events, h, k;
		if (l) {
			l = c.get(l);
			for (h = g.length - 1; h >= 0; h--) {
				k = g[h];
				if (k.obj === l) {
					j._remove(k.obj, k.name, k.cfunc);
					k.obj = k.cfunc = null;
					g.splice(h, 1);
				}
			}
		}
	}, cancel:function (g) {
		if (!g) {
			return false;
		}
		this.stop(g);
		return this.prevent(g);
	}, stop:function (g) {
		if (g.stopPropagation) {
			g.stopPropagation();
		} else {
			g.cancelBubble = true;
		}
		return false;
	}, prevent:function (g) {
		if (g.preventDefault) {
			g.preventDefault();
		} else {
			g.returnValue = false;
		}
		return false;
	}, destroy:function () {
		var g = this;
		f(g.events, function (j, h) {
			g._remove(j.obj, j.name, j.cfunc);
			j.obj = j.cfunc = null;
		});
		g.events = [];
		g = null;
	}, _add:function (h, j, g) {
		if (h.attachEvent) {
			h.attachEvent("on" + j, g);
		} else {
			if (h.addEventListener) {
				h.addEventListener(j, g, false);
			} else {
				h["on" + j] = g;
			}
		}
	}, _remove:function (j, k, h) {
		if (j) {
			try {
				if (j.detachEvent) {
					j.detachEvent("on" + k, h);
				} else {
					if (j.removeEventListener) {
						j.removeEventListener(k, h, false);
					} else {
						j["on" + k] = null;
					}
				}
			}
			catch (g) {
			}
		}
	}, _pageInit:function (h) {
		var g = this;
		if (g.domLoaded) {
			return;
		}
		g.domLoaded = true;
		f(g.inits, function (j) {
			j();
		});
		g.inits = [];
	}, _wait:function (j) {
		var g = this, h = j.document;
		if (j.tinyMCE_GZ && tinyMCE_GZ.loaded) {
			g.domLoaded = 1;
			return;
		}
		if (h.attachEvent) {
			h.attachEvent("onreadystatechange", function () {
				if (h.readyState === "complete") {
					h.detachEvent("onreadystatechange", arguments.callee);
					g._pageInit(j);
				}
			});
			if (h.documentElement.doScroll && j == j.top) {
				(function () {
					if (g.domLoaded) {
						return;
					}
					try {
						h.documentElement.doScroll("left");
					}
					catch (k) {
						setTimeout(arguments.callee, 0);
						return;
					}
					g._pageInit(j);
				})();
			}
		} else {
			if (h.addEventListener) {
				g._add(j, "DOMContentLoaded", function () {
					g._pageInit(j);
				});
			}
		}
		g._add(j, "load", function () {
			g._pageInit(j);
		});
	}, _stoppers:{preventDefault:function () {
		this.returnValue = false;
	}, stopPropagation:function () {
		this.cancelBubble = true;
	}}});
	a = d.dom.Event = new d.dom.EventUtils();
	a._wait(window);
	d.addUnload(function () {
		a.destroy();
	});
})(tinymce);
(function (a) {
	a.dom.Element = function (f, d) {
		var b = this, e, c;
		b.settings = d = d || {};
		b.id = f;
		b.dom = e = d.dom || a.DOM;
		if (!a.isIE) {
			c = e.get(b.id);
		}
		a.each(("getPos,getRect,getParent,add,setStyle,getStyle,setStyles,setAttrib,setAttribs,getAttrib,addClass,removeClass,hasClass,getOuterHTML,setOuterHTML,remove,show,hide,isHidden,setHTML,get").split(/,/), function (g) {
			b[g] = function () {
				var h = [f], j;
				for (j = 0; j < arguments.length; j++) {
					h.push(arguments[j]);
				}
				h = e[g].apply(e, h);
				b.update(g);
				return h;
			};
		});
		a.extend(b, {on:function (j, h, g) {
			return a.dom.Event.add(b.id, j, h, g);
		}, getXY:function () {
			return {x:parseInt(b.getStyle("left")), y:parseInt(b.getStyle("top"))};
		}, getSize:function () {
			var g = e.get(b.id);
			return {w:parseInt(b.getStyle("width") || g.clientWidth), h:parseInt(b.getStyle("height") || g.clientHeight)};
		}, moveTo:function (g, h) {
			b.setStyles({left:g, top:h});
		}, moveBy:function (g, j) {
			var h = b.getXY();
			b.moveTo(h.x + g, h.y + j);
		}, resizeTo:function (g, j) {
			b.setStyles({width:g, height:j});
		}, resizeBy:function (g, k) {
			var j = b.getSize();
			b.resizeTo(j.w + g, j.h + k);
		}, update:function (h) {
			var g;
			if (a.isIE6 && d.blocker) {
				h = h || "";
				if (h.indexOf("get") === 0 || h.indexOf("has") === 0 || h.indexOf("is") === 0) {
					return;
				}
				if (h == "remove") {
					e.remove(b.blocker);
					return;
				}
				if (!b.blocker) {
					b.blocker = e.uniqueId();
					g = e.add(d.container || e.getRoot(), "iframe", {id:b.blocker, style:"position:absolute;", frameBorder:0, src:"javascript:\"\""});
					e.setStyle(g, "opacity", 0);
				} else {
					g = e.get(b.blocker);
				}
				e.setStyles(g, {left:b.getStyle("left", 1), top:b.getStyle("top", 1), width:b.getStyle("width", 1), height:b.getStyle("height", 1), display:b.getStyle("display", 1), zIndex:parseInt(b.getStyle("zIndex", 1) || 0) - 1});
			}
		}});
	};
})(tinymce);
(function (c) {
	function e(f) {
		return f.replace(/[\n\r]+/g, "");
	}
	var b = c.is, a = c.isIE, d = c.each;
	c.create("tinymce.dom.Selection", {Selection:function (j, h, g) {
		var f = this;
		f.dom = j;
		f.win = h;
		f.serializer = g;
		d(["onBeforeSetContent", "onBeforeGetContent", "onSetContent", "onGetContent"], function (k) {
			f[k] = new c.util.Dispatcher(f);
		});
		if (!f.win.getSelection) {
			f.tridentSel = new c.dom.TridentSelection(f);
		}
		if (c.isIE && j.boxModel) {
			this._fixIESelection();
		}
		c.addUnload(f.destroy, f);
	}, getContent:function (g) {
		var f = this, h = f.getRng(), m = f.dom.create("body"), k = f.getSel(), j, l, o;
		g = g || {};
		j = l = "";
		g.get = true;
		g.format = g.format || "html";
		f.onBeforeGetContent.dispatch(f, g);
		if (g.format == "text") {
			return f.isCollapsed() ? "" : (h.text || (k.toString ? k.toString() : ""));
		}
		if (h.cloneContents) {
			o = h.cloneContents();
			if (o) {
				m.appendChild(o);
			}
		} else {
			if (b(h.item) || b(h.htmlText)) {
				m.innerHTML = h.item ? h.item(0).outerHTML : h.htmlText;
			} else {
				m.innerHTML = h.toString();
			}
		}
		if (/^\s/.test(m.innerHTML)) {
			j = " ";
		}
		if (/\s+$/.test(m.innerHTML)) {
			l = " ";
		}
		g.getInner = true;
		g.content = f.isCollapsed() ? "" : j + f.serializer.serialize(m, g) + l;
		f.onGetContent.dispatch(f, g);
		return g.content;
	}, setContent:function (k, j) {
		var g = this, l = g.getRng(), o, m = g.win.document;
		j = j || {format:"html"};
		j.set = true;
		k = j.content = k;
		g.onBeforeSetContent.dispatch(g, j);
		k = j.content;
		if (l.insertNode) {
			k += "<span id=\"__caret\">_</span>";
console.log("vao day =" + k);
			if (l.startContainer == m && l.endContainer == m) {
				m.body.innerHTML = k;
			} else {
				l.deleteContents();
				if (m.body.childNodes.length == 0) {
					m.body.innerHTML = k;
				} else {
					if (l.createContextualFragment) {
						l.insertNode(l.createContextualFragment(k));
					} else {
						var n = m.createDocumentFragment(), f = m.createElement("div");
						n.appendChild(f);
						f.outerHTML = k;
						l.insertNode(n);
					}
				}
			}
			o = g.dom.get("__caret");
			l = m.createRange();
			l.setStartBefore(o);
			l.setEndBefore(o);
			g.setRng(l);
			g.dom.remove("__caret");
			g.setRng(l);
		} else {
			if (l.item) {
				m.execCommand("Delete", false, null);
				l = g.getRng();
			}
			l.pasteHTML(k);
		}
		g.onSetContent.dispatch(g, j);
	}, getStart:function () {
		var g = this.getRng(), h, f, k, j;
		if (g.duplicate || g.item) {
			if (g.item) {
				return g.item(0);
			}
			k = g.duplicate();
			k.collapse(1);
			h = k.parentElement();
			f = j = g.parentElement();
			while (j = j.parentNode) {
				if (j == h) {
					h = f;
					break;
				}
			}
			return h;
		} else {
			h = g.startContainer;
			if (h.nodeType == 1 && h.hasChildNodes()) {
				h = h.childNodes[Math.min(h.childNodes.length - 1, g.startOffset)];
			}
			if (h && h.nodeType == 3) {
				return h.parentNode;
			}
			return h;
		}
	}, getEnd:function () {
		var g = this, h = g.getRng(), j, f;
		if (h.duplicate || h.item) {
			if (h.item) {
				return h.item(0);
			}
			h = h.duplicate();
			h.collapse(0);
			j = h.parentElement();
			if (j && j.nodeName == "BODY") {
				return j.lastChild || j;
			}
			return j;
		} else {
			j = h.endContainer;
			f = h.endOffset;
			if (j.nodeType == 1 && j.hasChildNodes()) {
				j = j.childNodes[f > 0 ? f - 1 : f];
			}
			if (j && j.nodeType == 3) {
				return j.parentNode;
			}
			return j;
		}
	}, getBookmark:function (r, s) {
		var v = this, n = v.dom, g, k, j, o, h, p, q, m = "\ufeff", u;
		function f(x, y) {
			var t = 0;
			d(n.select(x), function (A, z) {
				if (A == y) {
					t = z;
				}
			});
			return t;
		}
		if (r == 2) {
			function l() {
				var x = v.getRng(true), t = n.getRoot(), y = {};
				function z(C, H) {
					var B = C[H ? "startContainer" : "endContainer"], G = C[H ? "startOffset" : "endOffset"], A = [], D, F, E = 0;
					if (B.nodeType == 3) {
						if (s) {
							for (D = B.previousSibling; D && D.nodeType == 3; D = D.previousSibling) {
								G += D.nodeValue.length;
							}
						}
						A.push(G);
					} else {
						F = B.childNodes;
						if (G >= F.length && F.length) {
							E = 1;
							G = Math.max(0, F.length - 1);
						}
						A.push(v.dom.nodeIndex(F[G], s) + E);
					}
					for (; B && B != t; B = B.parentNode) {
						A.push(v.dom.nodeIndex(B, s));
					}
					return A;
				}
				y.start = z(x, true);
				if (!v.isCollapsed()) {
					y.end = z(x);
				}
				return y;
			}
			return l();
		}
		if (r) {
			return {rng:v.getRng()};
		}
		g = v.getRng();
		j = n.uniqueId();
		o = tinyMCE.activeEditor.selection.isCollapsed();
		u = "overflow:hidden;line-height:0px";
		if (g.duplicate || g.item) {
			if (!g.item) {
				k = g.duplicate();
				g.collapse();
				g.pasteHTML("<span data-mce-type=\"bookmark\" id=\"" + j + "_start\" style=\"" + u + "\">" + m + "</span>");
				if (!o) {
					k.collapse(false);
					k.pasteHTML("<span data-mce-type=\"bookmark\" id=\"" + j + "_end\" style=\"" + u + "\">" + m + "</span>");
				}
			} else {
				p = g.item(0);
				h = p.nodeName;
				return {name:h, index:f(h, p)};
			}
		} else {
			p = v.getNode();
			h = p.nodeName;
			if (h == "IMG") {
				return {name:h, index:f(h, p)};
			}
			k = g.cloneRange();
			if (!o) {
				k.collapse(false);
				k.insertNode(n.create("span", {"data-mce-type":"bookmark", id:j + "_end", style:u}, m));
			}
			g.collapse(true);
			g.insertNode(n.create("span", {"data-mce-type":"bookmark", id:j + "_start", style:u}, m));
		}
		v.moveToBookmark({id:j, keep:1});
		return {id:j};
	}, moveToBookmark:function (o) {
		var s = this, m = s.dom, j, h, f, r, k, u, p, q;
		if (s.tridentSel) {
			s.tridentSel.destroy();
		}
		if (o) {
			if (o.start) {
				f = m.createRng();
				r = m.getRoot();
				function g(A) {
					var t = o[A ? "start" : "end"], x, y, z, v;
					if (t) {
						for (y = r, x = t.length - 1; x >= 1; x--) {
							v = y.childNodes;
							if (v.length) {
								y = v[t[x]];
							}
						}
						if (A) {
							f.setStart(y, t[0]);
						} else {
							f.setEnd(y, t[0]);
						}
					}
				}
				g(true);
				g();
				s.setRng(f);
			} else {
				if (o.id) {
					function l(B) {
						var v = m.get(o.id + "_" + B), A, t, y, z, x = o.keep;
						if (v) {
							A = v.parentNode;
							if (B == "start") {
								if (!x) {
									t = m.nodeIndex(v);
								} else {
									A = v.firstChild;
									t = 1;
								}
								k = u = A;
								p = q = t;
							} else {
								if (!x) {
									t = m.nodeIndex(v);
								} else {
									A = v.firstChild;
									t = 1;
								}
								u = A;
								q = t;
							}
							if (!x) {
								z = v.previousSibling;
								y = v.nextSibling;
								d(c.grep(v.childNodes), function (C) {
									if (C.nodeType == 3) {
										C.nodeValue = C.nodeValue.replace(/\uFEFF/g, "");
									}
								});
								while (v = m.get(o.id + "_" + B)) {
									m.remove(v, 1);
								}
								if (z && y && z.nodeType == y.nodeType && z.nodeType == 3 && !c.isOpera) {
									t = z.nodeValue.length;
									z.appendData(y.nodeValue);
									m.remove(y);
									if (B == "start") {
										k = u = z;
										p = q = t;
									} else {
										u = z;
										q = t;
									}
								}
							}
						}
					}
					function n(t) {
						if (m.isBlock(t) && !t.innerHTML) {
							t.innerHTML = !a ? "<br data-mce-bogus=\"1\" />" : " ";
						}
						return t;
					}
					l("start");
					l("end");
					if (k) {
						f = m.createRng();
						f.setStart(n(k), p);
						f.setEnd(n(u), q);
						s.setRng(f);
					}
				} else {
					if (o.name) {
						s.select(m.select(o.name)[o.index]);
					} else {
						if (o.rng) {
							s.setRng(o.rng);
						}
					}
				}
			}
		}
	}, select:function (l, k) {
		var j = this, m = j.dom, g = m.createRng(), f;
		f = m.nodeIndex(l);
		g.setStart(l.parentNode, f);
		g.setEnd(l.parentNode, f + 1);
		if (k) {
			function h(n, p) {
				var o = new c.dom.TreeWalker(n, n);
				do {
					if (n.nodeType == 3 && c.trim(n.nodeValue).length != 0) {
						if (p) {
							g.setStart(n, 0);
						} else {
							g.setEnd(n, n.nodeValue.length);
						}
						return;
					}
					if (n.nodeName == "BR") {
						if (p) {
							g.setStartBefore(n);
						} else {
							g.setEndBefore(n);
						}
						return;
					}
				} while (n = (p ? o.next() : o.prev()));
			}
			h(l, 1);
			h(l);
		}
		j.setRng(g);
		return l;
	}, isCollapsed:function () {
		var f = this, h = f.getRng(), g = f.getSel();
		if (!h || h.item) {
			return false;
		}
		if (h.compareEndPoints) {
			return h.compareEndPoints("StartToEnd", h) === 0;
		}
		return !g || h.collapsed;
	}, collapse:function (f) {
		var h = this, g = h.getRng(), j;
		if (g.item) {
			j = g.item(0);
			g = h.win.document.body.createTextRange();
			g.moveToElementText(j);
		}
		g.collapse(!!f);
		h.setRng(g);
	}, getSel:function () {
		var g = this, f = this.win;
		return f.getSelection ? f.getSelection() : f.document.selection;
	}, getRng:function (m) {
		var g = this, h, j, l, k = g.win.document;
		if (m && g.tridentSel) {
			return g.tridentSel.getRangeAt(0);
		}
		try {
			if (h = g.getSel()) {
				j = h.rangeCount > 0 ? h.getRangeAt(0) : (h.createRange ? h.createRange() : k.createRange());
			}
		}
		catch (f) {
		}
		if (c.isIE && j.setStart && k.selection.createRange().item) {
			l = k.selection.createRange().item(0);
			j = k.createRange();
			j.setStartBefore(l);
			j.setEndAfter(l);
		}
		if (!j) {
			j = k.createRange ? k.createRange() : k.body.createTextRange();
		}
		if (g.selectedRange && g.explicitRange) {
			if (j.compareBoundaryPoints(j.START_TO_START, g.selectedRange) === 0 && j.compareBoundaryPoints(j.END_TO_END, g.selectedRange) === 0) {
				j = g.explicitRange;
			} else {
				g.selectedRange = null;
				g.explicitRange = null;
			}
		}
		return j;
	}, setRng:function (j) {
		var h, g = this;
		if (!g.tridentSel) {
			h = g.getSel();
			if (h) {
				g.explicitRange = j;
				h.removeAllRanges();
				h.addRange(j);
				g.selectedRange = h.getRangeAt(0);
			}
		} else {
			if (j.cloneRange) {
				g.tridentSel.addRange(j);
				return;
			}
			try {
				j.select();
			}
			catch (f) {
			}
		}
	}, setNode:function (g) {
		var f = this;
		f.setContent(f.dom.getOuterHTML(g));
		return g;
	}, getNode:function () {
		var h = this, g = h.getRng(), j = h.getSel(), m, l = g.startContainer, f = g.endContainer;
		if (g.setStart) {
			if (!g) {
				return h.dom.getRoot();
			}
			m = g.commonAncestorContainer;
			if (!g.collapsed) {
				if (g.startContainer == g.endContainer) {
					if (g.startOffset - g.endOffset < 2) {
						if (g.startContainer.hasChildNodes()) {
							m = g.startContainer.childNodes[g.startOffset];
						}
					}
				}
				if (c.isWebKit && j.anchorNode && j.anchorNode.nodeType == 1) {
					return j.anchorNode.childNodes[j.anchorOffset];
				}
				if (l.nodeType === 3 && f.nodeType === 3) {
					function k(q, o) {
						var p = q;
						while (q && q.nodeType === 3 && q.length === 0) {
							q = o ? q.nextSibling : q.previousSibling;
						}
						return q || p;
					}
					if (l.length === g.startOffset) {
						l = k(l.nextSibling, true);
					} else {
						l = l.parentNode;
					}
					if (g.endOffset === 0) {
						f = k(f.previousSibling, false);
					} else {
						f = f.parentNode;
					}
					if (l && l === f) {
						return l;
					}
				}
			}
			if (m && m.nodeType == 3) {
				return m.parentNode;
			}
			return m;
		}
		return g.item ? g.item(0) : g.parentElement();
	}, getSelectedBlocks:function (g, f) {
		var j = this, k = j.dom, o, h, m, l = [];
		o = k.getParent(g || j.getStart(), k.isBlock);
		h = k.getParent(f || j.getEnd(), k.isBlock);
		if (o) {
			l.push(o);
		}
		if (o && h && o != h) {
			m = o;
			while ((m = m.nextSibling) && m != h) {
				if (k.isBlock(m)) {
					l.push(m);
				}
			}
		}
		if (h && o != h) {
			l.push(h);
		}
		return l;
	}, destroy:function (g) {
		var f = this;
		f.win = null;
		if (f.tridentSel) {
			f.tridentSel.destroy();
		}
		if (!g) {
			c.removeUnload(f.destroy);
		}
	}, _fixIESelection:function () {
		var g = this.dom, n = g.doc, h = n.body, k, o, f;
		n.documentElement.unselectable = true;
		function j(p, s) {
			var q = h.createTextRange();
			try {
				q.moveToPoint(p, s);
			}
			catch (r) {
				q = null;
			}
			return q;
		}
		function m(q) {
			var p;
			if (q.button) {
				p = j(q.x, q.y);
				if (p) {
					if (p.compareEndPoints("StartToStart", o) > 0) {
						p.setEndPoint("StartToStart", o);
					} else {
						p.setEndPoint("EndToEnd", o);
					}
					p.select();
				}
			} else {
				l();
			}
		}
		function l() {
			var p = n.selection.createRange();
			if (o && !p.item && p.compareEndPoints("StartToEnd", p) === 0) {
				o.select();
			}
			g.unbind(n, "mouseup", l);
			g.unbind(n, "mousemove", m);
			o = k = 0;
		}
		g.bind(n, "mousedown", function (p) {
			if (p.target.nodeName === "HTML") {
				if (k) {
					l();
				}
				f = n.documentElement;
				if (f.scrollHeight > f.clientHeight) {
					return;
				}
				k = 1;
				o = j(p.x, p.y);
				if (o) {
					g.bind(n, "mouseup", l);
					g.bind(n, "mousemove", m);
					g.win.focus();
					o.select();
				}
			}
		});
	}});
})(tinymce);
(function (a) {
	a.dom.Serializer = function (e, j, f) {
		var h, b, d = a.isIE, g = a.each, c;
		if (!e.apply_source_formatting) {
			e.indent = false;
		}
		j = j || a.DOM;
		f = f || new a.html.Schema(e);
		e.entity_encoding = e.entity_encoding || "named";
		h = new a.util.Dispatcher(self);
		b = new a.util.Dispatcher(self);
		c = new a.html.DomParser(e, f);
		c.addAttributeFilter("src,href,style", function (l, k) {
			var p = l.length, m, r, o = "data-mce-" + k, q = e.url_converter, s = e.url_converter_scope, n;
			while (p--) {
				m = l[p];
				r = m.attributes.map[o];
				if (r !== n) {
					m.attr(k, r.length > 0 ? r : null);
					m.attr(o, null);
				} else {
					r = m.attributes.map[k];
					if (k === "style") {
						r = j.serializeStyle(j.parseStyle(r), m.name);
					} else {
						if (q) {
							r = q.call(s, r, k, m.name);
						}
					}
					m.attr(k, r.length > 0 ? r : null);
				}
			}
		});
		c.addAttributeFilter("class", function (k, l) {
			var m = k.length, n, o;
			while (m--) {
				n = k[m];
				o = n.attr("class").replace(/\s*mceItem\w+\s*/g, "");
				n.attr("class", o.length > 0 ? o : null);
			}
		});
		c.addAttributeFilter("data-mce-type", function (k, m, l) {
			var n = k.length, o;
			while (n--) {
				o = k[n];
				if (o.attributes.map["data-mce-type"] === "bookmark" && !l.cleanup) {
					o.remove();
				}
			}
		});
		c.addNodeFilter("script,style", function (l, m) {
			var n = l.length, o, p;
			function k(q) {
				return q.replace(/(<!--\[CDATA\[|\]\]-->)/g, "\n").replace(/^[\r\n]*|[\r\n]*$/g, "").replace(/^\s*(\/\/\s*<!--|\/\/\s*<!\[CDATA\[|<!--|<!\[CDATA\[)[\r\n]*/g, "").replace(/\s*(\/\/\s*\]\]>|\/\/\s*-->|\]\]>|-->|\]\]-->)\s*$/g, "");
			}
			while (n--) {
				o = l[n];
				p = o.firstChild ? o.firstChild.value : "";
				if (m === "script") {
					o.attr("type", (o.attr("type") || "text/javascript").replace(/^mce\-/, ""));
					if (p.length > 0) {
						o.firstChild.value = "// <![CDATA[\n" + k(p) + "\n// ]]>";
					}
				} else {
					if (p.length > 0) {
						o.firstChild.value = "<!--\n" + k(p) + "\n-->";
					}
				}
			}
		});
		c.addNodeFilter("br", function (k, l) {
			var n = k.length, q, p = f.getBlockElements(), m = f.getEmptyElements(), o;
			while (n--) {
				q = k[n];
				o = q.parent;
				if (p[q.parent.name] && q === o.lastChild) {
					q.remove();
					if (o.isEmpty(m)) {
						o.empty().append(new a.html.Node("#text", 3)).value = "\xa0";
					}
				}
			}
		});
		c.addNodeFilter("#comment", function (k, l) {
			var m = k.length, n;
			while (m--) {
				n = k[m];
				if (n.value.indexOf("[CDATA[") === 0) {
					n.name = "#cdata";
					n.type = 4;
					n.value = n.value.replace(/^\[CDATA\[|\]\]$/g, "");
				} else {
					if (n.value.indexOf("mce:protected ") === 0) {
						n.name = "#text";
						n.type = 3;
						n.raw = true;
						n.value = unescape(n.value).substr(14);
					}
				}
			}
		});
		c.addNodeFilter("xml:namespace,input", function (k, l) {
			var m = k.length, n;
			while (m--) {
				n = k[m];
				if (n.type === 7) {
					n.remove();
				} else {
					if (n.type === 1) {
						if (l === "input" && !("type" in n.attributes.map)) {
							n.attr("type", "text");
						}
					}
				}
			}
		});
		if (e.fix_list_elements) {
			c.addNodeFilter("ul,ol", function (l, m) {
				var n = l.length, o, k;
				while (n--) {
					o = l[n];
					k = o.parent;
					if (k.name === "ul" || k.name === "ol") {
						if (o.prev && o.prev.name === "li") {
							o.prev.append(o);
						}
					}
				}
			});
		}
		return {schema:f, addNodeFilter:c.addNodeFilter, addAttributeFilter:c.addAttributeFilter, onPreProcess:h, onPostProcess:b, serialize:function (p, n) {
			var m, q, l, k, o;
			if (d && j.select("script,style,select").length > 0) {
				o = p.innerHTML;
				p = p.cloneNode(false);
				j.setHTML(p, o);
			} else {
				p = p.cloneNode(true);
			}
			m = p.ownerDocument.implementation;
			if (m.createHTMLDocument) {
				q = m.createHTMLDocument("");
				g(p.nodeName == "BODY" ? p.childNodes : [p], function (r) {
					q.body.appendChild(q.importNode(r, true));
				});
				if (p.nodeName != "BODY") {
					p = q.body.firstChild;
				} else {
					p = q.body;
				}
				l = j.doc;
				j.doc = q;
			}
			n = n || {};
			n.format = n.format || "html";
			if (!n.no_events) {
				n.node = p;
				h.dispatch(self, n);
			}
			k = new a.html.Serializer(e, f);
			n.content = k.serialize(c.parse(n.getInner ? p.innerHTML : a.trim(j.getOuterHTML(p), n), n));
			if (!n.no_events) {
				b.dispatch(self, n);
			}
			if (l) {
				j.doc = l;
			}
			n.node = null;
			return n.content;
		}, addRules:function (k) {
			f.addValidElements(k);
		}, setRules:function (k) {
			f.setValidElements(k);
		}};
	};
})(tinymce);
(function (a) {
	a.dom.ScriptLoader = function (h) {
		var c = 0, l = 1, j = 2, m = {}, k = [], f = {}, d = [], g = 0, e;
		function b(n, v) {
			var x = this, r = a.DOM, u, p, s, o;
			function q() {
				r.remove(o);
				if (u) {
					u.onreadystatechange = u.onload = u = null;
				}
				v();
			}
			o = r.uniqueId();
			if (a.isIE6) {
				p = new a.util.URI(n);
				s = location;
				if (p.host == s.hostname && p.port == s.port && (p.protocol + ":") == s.protocol) {
					a.util.XHR.send({url:a._addVer(p.getURI()), success:function (y) {
						var t = r.create("script", {type:"text/javascript"});
						t.text = y;
						document.getElementsByTagName("head")[0].appendChild(t);
						r.remove(t);
						q();
					}});
					return;
				}
			}
			u = r.create("script", {id:o, type:"text/javascript", src:a._addVer(n)});
			if (!a.isIE) {
				u.onload = q;
			}
			if (!a.isOpera) {
				u.onreadystatechange = function () {
					var t = u.readyState;
					if (t == "complete" || t == "loaded") {
						q();
					}
				};
			}
			(document.getElementsByTagName("head")[0] || document.body).appendChild(u);
		}
		this.isDone = function (n) {
			return m[n] == j;
		};
		this.markDone = function (n) {
			m[n] = j;
		};
		this.add = this.load = function (n, r, o) {
			var p, q = m[n];
			if (q == e) {
				k.push(n);
				m[n] = c;
			}
			if (r) {
				if (!f[n]) {
					f[n] = [];
				}
				f[n].push({func:r, scope:o || this});
			}
		};
		this.loadQueue = function (o, n) {
			this.loadScripts(k, o, n);
		};
		this.loadScripts = function (n, r, q) {
			var p;
			function o(s) {
				a.each(f[s], function (t) {
					t.func.call(t.scope);
				});
				f[s] = e;
			}
			d.push({func:r, scope:q || this});
			p = function () {
				var s = a.grep(n);
				n.length = 0;
				a.each(s, function (t) {
					if (m[t] == j) {
						o(t);
						return;
					}
					if (m[t] != l) {
						m[t] = l;
						g++;
						b(t, function () {
							m[t] = j;
							g--;
							o(t);
							p();
						});
					}
				});
				if (!g) {
					a.each(d, function (t) {
						t.func.call(t.scope);
					});
					d.length = 0;
				}
			};
			p();
		};
	};
	a.ScriptLoader = new a.dom.ScriptLoader();
})(tinymce);
tinymce.dom.TreeWalker = function (a, c) {
	var b = a;
	function d(j, f, e, k) {
		var h, g;
		if (j) {
			if (!k && j[f]) {
				return j[f];
			}
			if (j != c) {
				h = j[e];
				if (h) {
					return h;
				}
				for (g = j.parentNode; g && g != c; g = g.parentNode) {
					h = g[e];
					if (h) {
						return h;
					}
				}
			}
		}
	}
	this.current = function () {
		return b;
	};
	this.next = function (e) {
		return (b = d(b, "firstChild", "nextSibling", e));
	};
	this.prev = function (e) {
		return (b = d(b, "lastChild", "lastSibling", e));
	};
};
(function (a) {
	a.dom.RangeUtils = function (c) {
		var b = "\ufeff";
		this.walk = function (d, s) {
			var h = d.startContainer, l = d.startOffset, t = d.endContainer, m = d.endOffset, j, f, o, g, r, q, e;
			e = c.select("td.mceSelected,th.mceSelected");
			if (e.length > 0) {
				a.each(e, function (u) {
					s([u]);
				});
				return;
			}
			function p(x, v, u) {
				var y = [];
				for (; x && x != u; x = x[v]) {
					y.push(x);
				}
				return y;
			}
			function n(v, u) {
				do {
					if (v.parentNode == u) {
						return v;
					}
					v = v.parentNode;
				} while (v);
			}
			function k(x, v, y) {
				var u = y ? "nextSibling" : "previousSibling";
				for (g = x, r = g.parentNode; g && g != v; g = r) {
					r = g.parentNode;
					q = p(g == x ? g : g[u], u);
					if (q.length) {
						if (!y) {
							q.reverse();
						}
						s(q);
					}
				}
			}
			if (h.nodeType == 1 && h.hasChildNodes()) {
				h = h.childNodes[l];
			}
			if (t.nodeType == 1 && t.hasChildNodes()) {
				t = t.childNodes[Math.min(m - 1, t.childNodes.length - 1)];
			}
			j = c.findCommonAncestor(h, t);
			if (h == t) {
				return s([h]);
			}
			for (g = h; g; g = g.parentNode) {
				if (g == t) {
					return k(h, j, true);
				}
				if (g == j) {
					break;
				}
			}
			for (g = t; g; g = g.parentNode) {
				if (g == h) {
					return k(t, j);
				}
				if (g == j) {
					break;
				}
			}
			f = n(h, j) || h;
			o = n(t, j) || t;
			k(h, f, true);
			q = p(f == h ? f : f.nextSibling, "nextSibling", o == t ? o.nextSibling : o);
			if (q.length) {
				s(q);
			}
			k(t, o);
		};
	};
	a.dom.RangeUtils.compareRanges = function (c, b) {
		if (c && b) {
			if (c.item || c.duplicate) {
				if (c.item && b.item && c.item(0) === b.item(0)) {
					return true;
				}
				if (c.isEqual && b.isEqual && b.isEqual(c)) {
					return true;
				}
			} else {
				return c.startContainer == b.startContainer && c.startOffset == b.startOffset;
			}
		}
		return false;
	};
})(tinymce);
(function (c) {
	var b = c.DOM, a = c.is;
	c.create("tinymce.ui.Control", {Control:function (e, d) {
		this.id = e;
		this.settings = d = d || {};
		this.rendered = false;
		this.onRender = new c.util.Dispatcher(this);
		this.classPrefix = "";
		this.scope = d.scope || this;
		this.disabled = 0;
		this.active = 0;
	}, setDisabled:function (d) {
		var f;
		if (d != this.disabled) {
			f = b.get(this.id);
			if (f && this.settings.unavailable_prefix) {
				if (d) {
					this.prevTitle = f.title;
					f.title = this.settings.unavailable_prefix + ": " + f.title;
				} else {
					f.title = this.prevTitle;
				}
			}
			this.setState("Disabled", d);
			this.setState("Enabled", !d);
			this.disabled = d;
		}
	}, isDisabled:function () {
		return this.disabled;
	}, setActive:function (d) {
		if (d != this.active) {
			this.setState("Active", d);
			this.active = d;
		}
	}, isActive:function () {
		return this.active;
	}, setState:function (f, d) {
		var e = b.get(this.id);
		f = this.classPrefix + f;
		if (d) {
			b.addClass(e, f);
		} else {
			b.removeClass(e, f);
		}
	}, isRendered:function () {
		return this.rendered;
	}, renderHTML:function () {
	}, renderTo:function (d) {
		b.setHTML(d, this.renderHTML());
	}, postRender:function () {
		var e = this, d;
		if (a(e.disabled)) {
			d = e.disabled;
			e.disabled = -1;
			e.setDisabled(d);
		}
		if (a(e.active)) {
			d = e.active;
			e.active = -1;
			e.setActive(d);
		}
	}, remove:function () {
		b.remove(this.id);
		this.destroy();
	}, destroy:function () {
		c.dom.Event.clear(this.id);
	}});
})(tinymce);
tinymce.create("tinymce.ui.Container:tinymce.ui.Control", {Container:function (b, a) {
	this.parent(b, a);
	this.controls = [];
	this.lookup = {};
}, add:function (a) {
	this.lookup[a.id] = a;
	this.controls.push(a);
	return a;
}, get:function (a) {
	return this.lookup[a];
}});
tinymce.create("tinymce.ui.Separator:tinymce.ui.Control", {Separator:function (b, a) {
	this.parent(b, a);
	this.classPrefix = "mceSeparator";
}, renderHTML:function () {
	return tinymce.DOM.createHTML("span", {"class":this.classPrefix});
}});
(function (d) {
	var c = d.is, b = d.DOM, e = d.each, a = d.walk;
	d.create("tinymce.ui.MenuItem:tinymce.ui.Control", {MenuItem:function (g, f) {
		this.parent(g, f);
		this.classPrefix = "mceMenuItem";
	}, setSelected:function (f) {
		this.setState("Selected", f);
		this.selected = f;
	}, isSelected:function () {
		return this.selected;
	}, postRender:function () {
		var f = this;
		f.parent();
		if (c(f.selected)) {
			f.setSelected(f.selected);
		}
	}});
})(tinymce);
(function (d) {
	var c = d.is, b = d.DOM, e = d.each, a = d.walk;
	d.create("tinymce.ui.Menu:tinymce.ui.MenuItem", {Menu:function (h, g) {
		var f = this;
		f.parent(h, g);
		f.items = {};
		f.collapsed = false;
		f.menuCount = 0;
		f.onAddItem = new d.util.Dispatcher(this);
	}, expand:function (g) {
		var f = this;
		if (g) {
			a(f, function (h) {
				if (h.expand) {
					h.expand();
				}
			}, "items", f);
		}
		f.collapsed = false;
	}, collapse:function (g) {
		var f = this;
		if (g) {
			a(f, function (h) {
				if (h.collapse) {
					h.collapse();
				}
			}, "items", f);
		}
		f.collapsed = true;
	}, isCollapsed:function () {
		return this.collapsed;
	}, add:function (f) {
		if (!f.settings) {
			f = new d.ui.MenuItem(f.id || b.uniqueId(), f);
		}
		this.onAddItem.dispatch(this, f);
		return this.items[f.id] = f;
	}, addSeparator:function () {
		return this.add({separator:true});
	}, addMenu:function (f) {
		if (!f.collapse) {
			f = this.createMenu(f);
		}
		this.menuCount++;
		return this.add(f);
	}, hasMenus:function () {
		return this.menuCount !== 0;
	}, remove:function (f) {
		delete this.items[f.id];
	}, removeAll:function () {
		var f = this;
		a(f, function (g) {
			if (g.removeAll) {
				g.removeAll();
			} else {
				g.remove();
			}
			g.destroy();
		}, "items", f);
		f.items = {};
	}, createMenu:function (g) {
		var f = new d.ui.Menu(g.id || b.uniqueId(), g);
		f.onAddItem.add(this.onAddItem.dispatch, this.onAddItem);
		return f;
	}});
})(tinymce);
(function (e) {
	var d = e.is, c = e.DOM, f = e.each, a = e.dom.Event, b = e.dom.Element;
	e.create("tinymce.ui.DropMenu:tinymce.ui.Menu", {DropMenu:function (h, g) {
		g = g || {};
		g.container = g.container || c.doc.body;
		g.offset_x = g.offset_x || 0;
		g.offset_y = g.offset_y || 0;
		g.vp_offset_x = g.vp_offset_x || 0;
		g.vp_offset_y = g.vp_offset_y || 0;
		if (d(g.icons) && !g.icons) {
			g["class"] += " mceNoIcons";
		}
		this.parent(h, g);
		this.onShowMenu = new e.util.Dispatcher(this);
		this.onHideMenu = new e.util.Dispatcher(this);
		this.classPrefix = "mceMenu";
	}, createMenu:function (k) {
		var h = this, j = h.settings, g;
		k.container = k.container || j.container;
		k.parent = h;
		k.constrain = k.constrain || j.constrain;
		k["class"] = k["class"] || j["class"];
		k.vp_offset_x = k.vp_offset_x || j.vp_offset_x;
		k.vp_offset_y = k.vp_offset_y || j.vp_offset_y;
		g = new e.ui.DropMenu(k.id || c.uniqueId(), k);
		g.onAddItem.add(h.onAddItem.dispatch, h.onAddItem);
		return g;
	}, update:function () {
		var j = this, k = j.settings, g = c.get("menu_" + j.id + "_tbl"), m = c.get("menu_" + j.id + "_co"), h, l;
		h = k.max_width ? Math.min(g.clientWidth, k.max_width) : g.clientWidth;
		l = k.max_height ? Math.min(g.clientHeight, k.max_height) : g.clientHeight;
		if (!c.boxModel) {
			j.element.setStyles({width:h + 2, height:l + 2});
		} else {
			j.element.setStyles({width:h, height:l});
		}
		if (k.max_width) {
			c.setStyle(m, "width", h);
		}
		if (k.max_height) {
			c.setStyle(m, "height", l);
			if (g.clientHeight < k.max_height) {
				c.setStyle(m, "overflow", "hidden");
			}
		}
	}, showMenu:function (q, o, u) {
		var A = this, B = A.settings, p, g = c.getViewPort(), v, m, z, r, j = 2, l, k, n = A.classPrefix;
		A.collapse(1);
		if (A.isMenuVisible) {
			return;
		}
		if (!A.rendered) {
			p = c.add(A.settings.container, A.renderNode());
			f(A.items, function (h) {
				h.postRender();
			});
			A.element = new b("menu_" + A.id, {blocker:1, container:B.container});
		} else {
			p = c.get("menu_" + A.id);
		}
		if (!e.isOpera) {
			c.setStyles(p, {left:-65535, top:-65535});
		}
		c.show(p);
		A.update();
		q += B.offset_x || 0;
		o += B.offset_y || 0;
		g.w -= 4;
		g.h -= 4;
		if (B.constrain) {
			v = p.clientWidth - j;
			m = p.clientHeight - j;
			z = g.x + g.w;
			r = g.y + g.h;
			if ((q + B.vp_offset_x + v) > z) {
				q = u ? u - v : Math.max(0, (z - B.vp_offset_x) - v);
			}
			if ((o + B.vp_offset_y + m) > r) {
				o = Math.max(0, (r - B.vp_offset_y) - m);
			}
		}
		c.setStyles(p, {left:q, top:o});
		A.element.update();
		A.isMenuVisible = 1;
		A.mouseClickFunc = a.add(p, "click", function (s) {
			var h;
			s = s.target;
			if (s && (s = c.getParent(s, "tr")) && !c.hasClass(s, n + "ItemSub")) {
				h = A.items[s.id];
				if (h.isDisabled()) {
					return;
				}
				l = A;
				while (l) {
					if (l.hideMenu) {
						l.hideMenu();
					}
					l = l.settings.parent;
				}
				if (h.settings.onclick) {
					h.settings.onclick(s);
				}
				return a.cancel(s);
			}
		});
		if (A.hasMenus()) {
			A.mouseOverFunc = a.add(p, "mouseover", function (x) {
				var h, t, s;
				x = x.target;
				if (x && (x = c.getParent(x, "tr"))) {
					h = A.items[x.id];
					if (A.lastMenu) {
						A.lastMenu.collapse(1);
					}
					if (h.isDisabled()) {
						return;
					}
					if (x && c.hasClass(x, n + "ItemSub")) {
						t = c.getRect(x);
						h.showMenu((t.x + t.w - j), t.y - j, t.x);
						A.lastMenu = h;
						c.addClass(c.get(h.id).firstChild, n + "ItemActive");
					}
				}
			});
		}
		A.onShowMenu.dispatch(A);
		if (B.keyboard_focus) {
			a.add(p, "keydown", A._keyHandler, A);
			c.select("a", "menu_" + A.id)[0].focus();
			A._focusIdx = 0;
		}
	}, hideMenu:function (k) {
		var g = this, j = c.get("menu_" + g.id), h;
		if (!g.isMenuVisible) {
			return;
		}
		a.remove(j, "mouseover", g.mouseOverFunc);
		a.remove(j, "click", g.mouseClickFunc);
		a.remove(j, "keydown", g._keyHandler);
		c.hide(j);
		g.isMenuVisible = 0;
		if (!k) {
			g.collapse(1);
		}
		if (g.element) {
			g.element.hide();
		}
		if (h = c.get(g.id)) {
			c.removeClass(h.firstChild, g.classPrefix + "ItemActive");
		}
		g.onHideMenu.dispatch(g);
	}, add:function (j) {
		var g = this, h;
		j = g.parent(j);
		if (g.isRendered && (h = c.get("menu_" + g.id))) {
			g._add(c.select("tbody", h)[0], j);
		}
		return j;
	}, collapse:function (g) {
		this.parent(g);
		this.hideMenu(1);
	}, remove:function (g) {
		c.remove(g.id);
		this.destroy();
		return this.parent(g);
	}, destroy:function () {
		var g = this, h = c.get("menu_" + g.id);
		a.remove(h, "mouseover", g.mouseOverFunc);
		a.remove(h, "click", g.mouseClickFunc);
		if (g.element) {
			g.element.remove();
		}
		c.remove(h);
	}, renderNode:function () {
		var j = this, k = j.settings, m, h, l, g;
		g = c.create("div", {id:"menu_" + j.id, "class":k["class"], style:"position:absolute;left:0;top:0;z-index:200000"});
		l = c.add(g, "div", {id:"menu_" + j.id + "_co", "class":j.classPrefix + (k["class"] ? " " + k["class"] : "")});
		j.element = new b("menu_" + j.id, {blocker:1, container:k.container});
		if (k.menu_line) {
			c.add(l, "span", {"class":j.classPrefix + "Line"});
		}
		m = c.add(l, "table", {id:"menu_" + j.id + "_tbl", border:0, cellPadding:0, cellSpacing:0});
		h = c.add(m, "tbody");
		f(j.items, function (n) {
			j._add(h, n);
		});
		j.rendered = true;
		return g;
	}, _keyHandler:function (k) {
		var j = this, h = k.keyCode;
		function g(n) {
			var l = j._focusIdx + n, m = c.select("a", "menu_" + j.id)[l];
			if (m) {
				j._focusIdx = l;
				m.focus();
			}
		}
		switch (h) {
		  case 38:
			g(-1);
			return;
		  case 40:
			g(1);
			return;
		  case 13:
			return;
		  case 27:
			return this.hideMenu();
		}
	}, _add:function (k, h) {
		var j, r = h.settings, q, m, l, p = this.classPrefix, g;
		if (r.separator) {
			m = c.add(k, "tr", {id:h.id, "class":p + "ItemSeparator"});
			c.add(m, "td", {"class":p + "ItemSeparator"});
			if (j = m.previousSibling) {
				c.addClass(j, "mceLast");
			}
			return;
		}
		j = m = c.add(k, "tr", {id:h.id, "class":p + "Item " + p + "ItemEnabled"});
		j = l = c.add(j, "td");
		j = q = c.add(j, "a", {href:"javascript:;", onclick:"return false;", onmousedown:"return false;"});
		c.addClass(l, r["class"]);
		g = c.add(j, "span", {"class":"mceIcon" + (r.icon ? " mce_" + r.icon : "")});
		if (r.icon_src) {
			c.add(g, "img", {src:r.icon_src});
		}
		j = c.add(j, r.element || "span", {"class":"mceText", title:h.settings.title}, h.settings.title);
		if (h.settings.style) {
			c.setAttrib(j, "style", h.settings.style);
		}
		if (k.childNodes.length == 1) {
			c.addClass(m, "mceFirst");
		}
		if ((j = m.previousSibling) && c.hasClass(j, p + "ItemSeparator")) {
			c.addClass(m, "mceFirst");
		}
		if (h.collapse) {
			c.addClass(m, p + "ItemSub");
		}
		if (j = m.previousSibling) {
			c.removeClass(j, "mceLast");
		}
		c.addClass(m, "mceLast");
	}});
})(tinymce);
(function (b) {
	var a = b.DOM;
	b.create("tinymce.ui.Button:tinymce.ui.Control", {Button:function (d, c) {
		this.parent(d, c);
		this.classPrefix = "mceButton";
	}, renderHTML:function () {
		var f = this.classPrefix, e = this.settings, d, c;
		c = a.encode(e.label || "");
		d = "<a id=\"" + this.id + "\" href=\"javascript:;\" class=\"" + f + " " + f + "Enabled " + e["class"] + (c ? " " + f + "Labeled" : "") + "\" onmousedown=\"return false;\" onclick=\"return false;\" title=\"" + a.encode(e.title) + "\">";
		if (e.image) {
			d += "<img class=\"mceIcon\" src=\"" + e.image + "\" />" + c + "</a>";
		} else {
			d += "<span class=\"mceIcon " + e["class"] + "\"></span>" + (c ? "<span class=\"" + f + "Label\">" + c + "</span>" : "") + "</a>";
		}
		return d;
	}, postRender:function () {
		var c = this, d = c.settings;
		b.dom.Event.add(c.id, "click", function (f) {
			if (!c.isDisabled()) {
				return d.onclick.call(d.scope, f);
			}
		});
	}});
})(tinymce);
(function (d) {
	var c = d.DOM, b = d.dom.Event, e = d.each, a = d.util.Dispatcher;
	d.create("tinymce.ui.ListBox:tinymce.ui.Control", {ListBox:function (h, g) {
		var f = this;
		f.parent(h, g);
		f.items = [];
		f.onChange = new a(f);
		f.onPostRender = new a(f);
		f.onAdd = new a(f);
		f.onRenderMenu = new d.util.Dispatcher(this);
		f.classPrefix = "mceListBox";
	}, select:function (h) {
		var g = this, k, j;
		if (h == undefined) {
			return g.selectByIndex(-1);
		}
		if (h && h.call) {
			j = h;
		} else {
			j = function (f) {
				return f == h;
			};
		}
		if (h != g.selectedValue) {
			e(g.items, function (l, f) {
				if (j(l.value)) {
					k = 1;
					g.selectByIndex(f);
					return false;
				}
			});
			if (!k) {
				g.selectByIndex(-1);
			}
		}
	}, selectByIndex:function (f) {
		var g = this, h, j;
		if (f != g.selectedIndex) {
			h = c.get(g.id + "_text");
			j = g.items[f];
			if (j) {
				g.selectedValue = j.value;
				g.selectedIndex = f;
				c.setHTML(h, c.encode(j.title));
				c.removeClass(h, "mceTitle");
			} else {
				c.setHTML(h, c.encode(g.settings.title));
				c.addClass(h, "mceTitle");
				g.selectedValue = g.selectedIndex = null;
			}
			h = 0;
		}
	}, add:function (j, f, h) {
		var g = this;
		h = h || {};
		h = d.extend(h, {title:j, value:f});
		g.items.push(h);
		g.onAdd.dispatch(g, h);
	}, getLength:function () {
		return this.items.length;
	}, renderHTML:function () {
		var j = "", f = this, g = f.settings, k = f.classPrefix;
		j = "<table id=\"" + f.id + "\" cellpadding=\"0\" cellspacing=\"0\" class=\"" + k + " " + k + "Enabled" + (g["class"] ? (" " + g["class"]) : "") + "\"><tbody><tr>";
		j += "<td>" + c.createHTML("a", {id:f.id + "_text", href:"javascript:;", "class":"mceText", onclick:"return false;", onmousedown:"return false;"}, c.encode(f.settings.title)) + "</td>";
		j += "<td>" + c.createHTML("a", {id:f.id + "_open", tabindex:-1, href:"javascript:;", "class":"mceOpen", onclick:"return false;", onmousedown:"return false;"}, "<span></span>") + "</td>";
		j += "</tr></tbody></table>";
		return j;
	}, showMenu:function () {
		var g = this, k, j, h = c.get(this.id), f;
		if (g.isDisabled() || g.items.length == 0) {
			return;
		}
		if (g.menu && g.menu.isMenuVisible) {
			return g.hideMenu();
		}
		if (!g.isMenuRendered) {
			g.renderMenu();
			g.isMenuRendered = true;
		}
		k = c.getPos(this.settings.menu_container);
		j = c.getPos(h);
		f = g.menu;
		f.settings.offset_x = j.x;
		f.settings.offset_y = j.y;
		f.settings.keyboard_focus = !d.isOpera;
		if (g.oldID) {
			f.items[g.oldID].setSelected(0);
		}
		e(g.items, function (l) {
			if (l.value === g.selectedValue) {
				f.items[l.id].setSelected(1);
				g.oldID = l.id;
			}
		});
		f.showMenu(0, h.clientHeight);
		b.add(c.doc, "mousedown", g.hideMenu, g);
		c.addClass(g.id, g.classPrefix + "Selected");
	}, hideMenu:function (g) {
		var f = this;
		if (f.menu && f.menu.isMenuVisible) {
			if (g && g.type == "mousedown" && (g.target.id == f.id + "_text" || g.target.id == f.id + "_open")) {
				return;
			}
			if (!g || !c.getParent(g.target, ".mceMenu")) {
				c.removeClass(f.id, f.classPrefix + "Selected");
				b.remove(c.doc, "mousedown", f.hideMenu, f);
				f.menu.hideMenu();
			}
		}
	}, renderMenu:function () {
		var g = this, f;
		f = g.settings.control_manager.createDropMenu(g.id + "_menu", {menu_line:1, "class":g.classPrefix + "Menu mceNoIcons", max_width:150, max_height:150});
		f.onHideMenu.add(g.hideMenu, g);
		f.add({title:g.settings.title, "class":"mceMenuItemTitle", onclick:function () {
			if (g.settings.onselect("") !== false) {
				g.select("");
			}
		}});
		e(g.items, function (h) {
			if (h.value === undefined) {
				f.add({title:h.title, "class":"mceMenuItemTitle", onclick:function () {
					if (g.settings.onselect("") !== false) {
						g.select("");
					}
				}});
			} else {
				h.id = c.uniqueId();
				h.onclick = function () {
					if (g.settings.onselect(h.value) !== false) {
						g.select(h.value);
					}
				};
				f.add(h);
			}
		});
		g.onRenderMenu.dispatch(g, f);
		g.menu = f;
	}, postRender:function () {
		var f = this, g = f.classPrefix;
		b.add(f.id, "click", f.showMenu, f);
		b.add(f.id + "_text", "focus", function () {
			if (!f._focused) {
				f.keyDownHandler = b.add(f.id + "_text", "keydown", function (l) {
					var h = -1, j, k = l.keyCode;
					e(f.items, function (m, n) {
						if (f.selectedValue == m.value) {
							h = n;
						}
					});
					if (k == 38) {
						j = f.items[h - 1];
					} else {
						if (k == 40) {
							j = f.items[h + 1];
						} else {
							if (k == 13) {
								j = f.selectedValue;
								f.selectedValue = null;
								f.settings.onselect(j);
								return b.cancel(l);
							}
						}
					}
					if (j) {
						f.hideMenu();
						f.select(j.value);
					}
				});
			}
			f._focused = 1;
		});
		b.add(f.id + "_text", "blur", function () {
			b.remove(f.id + "_text", "keydown", f.keyDownHandler);
			f._focused = 0;
		});
		if (d.isIE6 || !c.boxModel) {
			b.add(f.id, "mouseover", function () {
				if (!c.hasClass(f.id, g + "Disabled")) {
					c.addClass(f.id, g + "Hover");
				}
			});
			b.add(f.id, "mouseout", function () {
				if (!c.hasClass(f.id, g + "Disabled")) {
					c.removeClass(f.id, g + "Hover");
				}
			});
		}
		f.onPostRender.dispatch(f, c.get(f.id));
	}, destroy:function () {
		this.parent();
		b.clear(this.id + "_text");
		b.clear(this.id + "_open");
	}});
})(tinymce);
(function (d) {
	var c = d.DOM, b = d.dom.Event, e = d.each, a = d.util.Dispatcher;
	d.create("tinymce.ui.NativeListBox:tinymce.ui.ListBox", {NativeListBox:function (g, f) {
		this.parent(g, f);
		this.classPrefix = "mceNativeListBox";
	}, setDisabled:function (f) {
		c.get(this.id).disabled = f;
	}, isDisabled:function () {
		return c.get(this.id).disabled;
	}, select:function (h) {
		var g = this, k, j;
		if (h == undefined) {
			return g.selectByIndex(-1);
		}
		if (h && h.call) {
			j = h;
		} else {
			j = function (f) {
				return f == h;
			};
		}
		if (h != g.selectedValue) {
			e(g.items, function (l, f) {
				if (j(l.value)) {
					k = 1;
					g.selectByIndex(f);
					return false;
				}
			});
			if (!k) {
				g.selectByIndex(-1);
			}
		}
	}, selectByIndex:function (f) {
		c.get(this.id).selectedIndex = f + 1;
		this.selectedValue = this.items[f] ? this.items[f].value : null;
	}, add:function (k, g, f) {
		var j, h = this;
		f = f || {};
		f.value = g;
		if (h.isRendered()) {
			c.add(c.get(this.id), "option", f, k);
		}
		j = {title:k, value:g, attribs:f};
		h.items.push(j);
		h.onAdd.dispatch(h, j);
	}, getLength:function () {
		return this.items.length;
	}, renderHTML:function () {
		var g, f = this;
		g = c.createHTML("option", {value:""}, "-- " + f.settings.title + " --");
		e(f.items, function (h) {
			g += c.createHTML("option", {value:h.value}, h.title);
		});
		g = c.createHTML("select", {id:f.id, "class":"mceNativeListBox"}, g);
		return g;
	}, postRender:function () {
		var g = this, h;
		g.rendered = true;
		function f(k) {
			var j = g.items[k.target.selectedIndex - 1];
			if (j && (j = j.value)) {
				g.onChange.dispatch(g, j);
				if (g.settings.onselect) {
					g.settings.onselect(j);
				}
			}
		}
		b.add(g.id, "change", f);
		b.add(g.id, "keydown", function (k) {
			var j;
			b.remove(g.id, "change", h);
			j = b.add(g.id, "blur", function () {
				b.add(g.id, "change", f);
				b.remove(g.id, "blur", j);
			});
			if (k.keyCode == 13 || k.keyCode == 32) {
				f(k);
				return b.cancel(k);
			}
		});
		g.onPostRender.dispatch(g, c.get(g.id));
	}});
})(tinymce);
(function (c) {
	var b = c.DOM, a = c.dom.Event, d = c.each;
	c.create("tinymce.ui.MenuButton:tinymce.ui.Button", {MenuButton:function (f, e) {
		this.parent(f, e);
		this.onRenderMenu = new c.util.Dispatcher(this);
		e.menu_container = e.menu_container || b.doc.body;
	}, showMenu:function () {
		var g = this, k, j, h = b.get(g.id), f;
		if (g.isDisabled()) {
			return;
		}
		if (!g.isMenuRendered) {
			g.renderMenu();
			g.isMenuRendered = true;
		}
		if (g.isMenuVisible) {
			return g.hideMenu();
		}
		k = b.getPos(g.settings.menu_container);
		j = b.getPos(h);
		f = g.menu;
		f.settings.offset_x = j.x;
		f.settings.offset_y = j.y;
		f.settings.vp_offset_x = j.x;
		f.settings.vp_offset_y = j.y;
		f.settings.keyboard_focus = g._focused;
		f.showMenu(0, h.clientHeight);
		a.add(b.doc, "mousedown", g.hideMenu, g);
		g.setState("Selected", 1);
		g.isMenuVisible = 1;
	}, renderMenu:function () {
		var f = this, e;
		e = f.settings.control_manager.createDropMenu(f.id + "_menu", {menu_line:1, "class":this.classPrefix + "Menu", icons:f.settings.icons});
		e.onHideMenu.add(f.hideMenu, f);
		f.onRenderMenu.dispatch(f, e);
		f.menu = e;
	}, hideMenu:function (g) {
		var f = this;
		if (g && g.type == "mousedown" && b.getParent(g.target, function (h) {
			return h.id === f.id || h.id === f.id + "_open";
		})) {
			return;
		}
		if (!g || !b.getParent(g.target, ".mceMenu")) {
			f.setState("Selected", 0);
			a.remove(b.doc, "mousedown", f.hideMenu, f);
			if (f.menu) {
				f.menu.hideMenu();
			}
		}
		f.isMenuVisible = 0;
	}, postRender:function () {
		var e = this, f = e.settings;
		a.add(e.id, "click", function () {
			if (!e.isDisabled()) {
				if (f.onclick) {
					f.onclick(e.value);
				}
				e.showMenu();
			}
		});
	}});
})(tinymce);
(function (c) {
	var b = c.DOM, a = c.dom.Event, d = c.each;
	c.create("tinymce.ui.SplitButton:tinymce.ui.MenuButton", {SplitButton:function (f, e) {
		this.parent(f, e);
		this.classPrefix = "mceSplitButton";
	}, renderHTML:function () {
		var j, f = this, g = f.settings, e;
		j = "<tbody><tr>";
		if (g.image) {
			e = b.createHTML("img ", {src:g.image, "class":"mceAction " + g["class"]});
		} else {
			e = b.createHTML("span", {"class":"mceAction " + g["class"]}, "");
		}
		j += "<td>" + b.createHTML("a", {id:f.id + "_action", href:"javascript:;", "class":"mceAction " + g["class"], onclick:"return false;", onmousedown:"return false;", title:g.title}, e) + "</td>";
		e = b.createHTML("span", {"class":"mceOpen " + g["class"]});
		j += "<td>" + b.createHTML("a", {id:f.id + "_open", href:"javascript:;", "class":"mceOpen " + g["class"], onclick:"return false;", onmousedown:"return false;", title:g.title}, e) + "</td>";
		j += "</tr></tbody>";
		return b.createHTML("table", {id:f.id, "class":"mceSplitButton mceSplitButtonEnabled " + g["class"], cellpadding:"0", cellspacing:"0", onmousedown:"return false;", title:g.title}, j);
	}, postRender:function () {
		var e = this, f = e.settings;
		if (f.onclick) {
			a.add(e.id + "_action", "click", function () {
				if (!e.isDisabled()) {
					f.onclick(e.value);
				}
			});
		}
		a.add(e.id + "_open", "click", e.showMenu, e);
		a.add(e.id + "_open", "focus", function () {
			e._focused = 1;
		});
		a.add(e.id + "_open", "blur", function () {
			e._focused = 0;
		});
		if (c.isIE6 || !b.boxModel) {
			a.add(e.id, "mouseover", function () {
				if (!b.hasClass(e.id, "mceSplitButtonDisabled")) {
					b.addClass(e.id, "mceSplitButtonHover");
				}
			});
			a.add(e.id, "mouseout", function () {
				if (!b.hasClass(e.id, "mceSplitButtonDisabled")) {
					b.removeClass(e.id, "mceSplitButtonHover");
				}
			});
		}
	}, destroy:function () {
		this.parent();
		a.clear(this.id + "_action");
		a.clear(this.id + "_open");
	}});
})(tinymce);
(function (d) {
	var c = d.DOM, a = d.dom.Event, b = d.is, e = d.each;
	d.create("tinymce.ui.ColorSplitButton:tinymce.ui.SplitButton", {ColorSplitButton:function (h, g) {
		var f = this;
		f.parent(h, g);
		f.settings = g = d.extend({colors:"000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,008000,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF", grid_width:8, default_color:"#888888"}, f.settings);
		f.onShowMenu = new d.util.Dispatcher(f);
		f.onHideMenu = new d.util.Dispatcher(f);
		f.value = g.default_color;
	}, showMenu:function () {
		var f = this, g, k, j, h;
		if (f.isDisabled()) {
			return;
		}
		if (!f.isMenuRendered) {
			f.renderMenu();
			f.isMenuRendered = true;
		}
		if (f.isMenuVisible) {
			return f.hideMenu();
		}
		j = c.get(f.id);
		c.show(f.id + "_menu");
		c.addClass(j, "mceSplitButtonSelected");
		h = c.getPos(j);
		c.setStyles(f.id + "_menu", {left:h.x, top:h.y + j.clientHeight, zIndex:200000});
		j = 0;
		a.add(c.doc, "mousedown", f.hideMenu, f);
		f.onShowMenu.dispatch(f);
		if (f._focused) {
			f._keyHandler = a.add(f.id + "_menu", "keydown", function (l) {
				if (l.keyCode == 27) {
					f.hideMenu();
				}
			});
			c.select("a", f.id + "_menu")[0].focus();
		}
		f.isMenuVisible = 1;
	}, hideMenu:function (g) {
		var f = this;
		if (g && g.type == "mousedown" && c.getParent(g.target, function (h) {
			return h.id === f.id + "_open";
		})) {
			return;
		}
		if (!g || !c.getParent(g.target, ".mceSplitButtonMenu")) {
			c.removeClass(f.id, "mceSplitButtonSelected");
			a.remove(c.doc, "mousedown", f.hideMenu, f);
			a.remove(f.id + "_menu", "keydown", f._keyHandler);
			c.hide(f.id + "_menu");
		}
		f.onHideMenu.dispatch(f);
		f.isMenuVisible = 0;
	}, renderMenu:function () {
		var k = this, f, j = 0, l = k.settings, p, h, o, g;
		g = c.add(l.menu_container, "div", {id:k.id + "_menu", "class":l.menu_class + " " + l["class"], style:"position:absolute;left:0;top:-1000px;"});
		f = c.add(g, "div", {"class":l["class"] + " mceSplitButtonMenu"});
		c.add(f, "span", {"class":"mceMenuLine"});
		p = c.add(f, "table", {"class":"mceColorSplitMenu"});
		h = c.add(p, "tbody");
		j = 0;
		e(b(l.colors, "array") ? l.colors : l.colors.split(","), function (m) {
			m = m.replace(/^#/, "");
			if (!j--) {
				o = c.add(h, "tr");
				j = l.grid_width - 1;
			}
			p = c.add(o, "td");
			p = c.add(p, "a", {href:"javascript:;", style:{backgroundColor:"#" + m}, "data-mce-color":"#" + m});
		});
		if (l.more_colors_func) {
			p = c.add(h, "tr");
			p = c.add(p, "td", {colspan:l.grid_width, "class":"mceMoreColors"});
			p = c.add(p, "a", {id:k.id + "_more", href:"javascript:;", onclick:"return false;", "class":"mceMoreColors"}, l.more_colors_title);
			a.add(p, "click", function (m) {
				l.more_colors_func.call(l.more_colors_scope || this);
				return a.cancel(m);
			});
		}
		c.addClass(f, "mceColorSplitMenu");
		a.add(k.id + "_menu", "click", function (m) {
			var n;
			m = m.target;
			if (m.nodeName == "A" && (n = m.getAttribute("data-mce-color"))) {
				k.setColor(n);
			}
			return a.cancel(m);
		});
		return g;
	}, setColor:function (g) {
		var f = this;
		c.setStyle(f.id + "_preview", "backgroundColor", g);
		f.value = g;
		f.hideMenu();
		f.settings.onselect(g);
	}, postRender:function () {
		var f = this, g = f.id;
		f.parent();
		c.add(g + "_action", "div", {id:g + "_preview", "class":"mceColorPreview"});
		c.setStyle(f.id + "_preview", "backgroundColor", f.value);
	}, destroy:function () {
		this.parent();
		a.clear(this.id + "_menu");
		a.clear(this.id + "_more");
		c.remove(this.id + "_menu");
	}});
})(tinymce);
tinymce.create("tinymce.ui.Toolbar:tinymce.ui.Container", {renderHTML:function () {
	var l = this, e = "", g, j, b = tinymce.DOM, m = l.settings, d, a, f, k;
	k = l.controls;
	for (d = 0; d < k.length; d++) {
		j = k[d];
		a = k[d - 1];
		f = k[d + 1];
		if (d === 0) {
			g = "mceToolbarStart";
			if (j.Button) {
				g += " mceToolbarStartButton";
			} else {
				if (j.SplitButton) {
					g += " mceToolbarStartSplitButton";
				} else {
					if (j.ListBox) {
						g += " mceToolbarStartListBox";
					}
				}
			}
			e += b.createHTML("td", {"class":g}, b.createHTML("span", null, "<!-- IE -->"));
		}
		if (a && j.ListBox) {
			if (a.Button || a.SplitButton) {
				e += b.createHTML("td", {"class":"mceToolbarEnd"}, b.createHTML("span", null, "<!-- IE -->"));
			}
		}
		if (b.stdMode) {
			e += "<td style=\"position: relative\">" + j.renderHTML() + "</td>";
		} else {
			e += "<td>" + j.renderHTML() + "</td>";
		}
		if (f && j.ListBox) {
			if (f.Button || f.SplitButton) {
				e += b.createHTML("td", {"class":"mceToolbarStart"}, b.createHTML("span", null, "<!-- IE -->"));
			}
		}
	}
	g = "mceToolbarEnd";
	if (j.Button) {
		g += " mceToolbarEndButton";
	} else {
		if (j.SplitButton) {
			g += " mceToolbarEndSplitButton";
		} else {
			if (j.ListBox) {
				g += " mceToolbarEndListBox";
			}
		}
	}
	e += b.createHTML("td", {"class":g}, b.createHTML("span", null, "<!-- IE -->"));
	return b.createHTML("table", {id:l.id, "class":"mceToolbar" + (m["class"] ? " " + m["class"] : ""), cellpadding:"0", cellspacing:"0", align:l.settings.align || ""}, "<tbody><tr>" + e + "</tr></tbody>");
}});
(function (b) {
	var a = b.util.Dispatcher, c = b.each;
	b.create("tinymce.AddOnManager", {AddOnManager:function () {
		var d = this;
		d.items = [];
		d.urls = {};
		d.lookup = {};
		d.onAdd = new a(d);
	}, get:function (d) {
		return this.lookup[d];
	}, requireLangPack:function (e) {
		var d = b.settings;
		if (d && d.language && d.language_load !== false) {
			b.ScriptLoader.add(this.urls[e] + "/langs/" + d.language + ".js");
		}
	}, add:function (e, d) {
		this.items.push(d);
		this.lookup[e] = d;
		this.onAdd.dispatch(this, e, d);
		return d;
	}, load:function (h, e, d, g) {
		var f = this;
		if (f.urls[h]) {
			return;
		}
		if (e.indexOf("/") != 0 && e.indexOf("://") == -1) {
			e = b.baseURL + "/" + e;
		}
		f.urls[h] = e.substring(0, e.lastIndexOf("/"));
		if (!f.lookup[h]) {
			b.ScriptLoader.add(e, d, g);
		}
	}});
	b.PluginManager = new b.AddOnManager();
	b.ThemeManager = new b.AddOnManager();
}(tinymce));
(function (k) {
	var g = k.each, d = k.extend, l = k.DOM, j = k.dom.Event, f = k.ThemeManager, b = k.PluginManager, e = k.explode, h = k.util.Dispatcher, a, c = 0;
	k.documentBaseURL = window.location.href.replace(/[\?#].*$/, "").replace(/[\/\\][^\/]+$/, "");
	if (!/[\/\\]$/.test(k.documentBaseURL)) {
		k.documentBaseURL += "/";
	}
	k.baseURL = new k.util.URI(k.documentBaseURL).toAbsolute(k.baseURL);
	k.baseURI = new k.util.URI(k.baseURL);
	k.onBeforeUnload = new h(k);
	j.add(window, "beforeunload", function (m) {
		k.onBeforeUnload.dispatch(k, m);
	});
	k.onAddEditor = new h(k);
	k.onRemoveEditor = new h(k);
	k.EditorManager = d(k, {editors:[], i18n:{}, activeEditor:null, init:function (r) {
		var o = this, q, m = k.ScriptLoader, v, p = [], n;
		function u(y, z, t) {
			var x = y[z];
			if (!x) {
				return;
			}
			if (k.is(x, "string")) {
				t = x.replace(/\.\w+$/, "");
				t = t ? k.resolve(t) : 0;
				x = k.resolve(x);
			}
			return x.apply(t || this, Array.prototype.slice.call(arguments, 2));
		}
		r = d({theme:"simple", language:"en"}, r);
		o.settings = r;
		j.add(document, "init", function () {
			var s, x;
			u(r, "onpageload");
			switch (r.mode) {
			  case "exact":
				s = r.elements || "";
				if (s.length > 0) {
					g(e(s), function (y) {
						if (l.get(y)) {
							n = new k.Editor(y, r);
							p.push(n);
							n.render(1);
						} else {
							g(document.forms, function (z) {
								g(z.elements, function (A) {
									if (A.name === y) {
										y = "mce_editor_" + c++;
										l.setAttrib(A, "id", y);
										n = new k.Editor(y, r);
										p.push(n);
										n.render(1);
									}
								});
							});
						}
					});
				}
				break;
			  case "textareas":
			  case "specific_textareas":
				function t(z, y) {
					return y.constructor === RegExp ? y.test(z.className) : l.hasClass(z, y);
				}
				g(l.select("textarea"), function (y) {
					if (r.editor_deselector && t(y, r.editor_deselector)) {
						return;
					}
					if (!r.editor_selector || t(y, r.editor_selector)) {
						v = l.get(y.name);
						if (!y.id && !v) {
							y.id = y.name;
						}
						if (!y.id || o.get(y.id)) {
							y.id = l.uniqueId();
						}
						n = new k.Editor(y.id, r);
						p.push(n);
						n.render(1);
					}
				});
				break;
			}
			if (r.oninit) {
				s = x = 0;
				g(p, function (y) {
					x++;
					if (!y.initialized) {
						y.onInit.add(function () {
							s++;
							if (s == x) {
								u(r, "oninit");
							}
						});
					} else {
						s++;
					}
					if (s == x) {
						u(r, "oninit");
					}
				});
			}
		});
	}, get:function (m) {
		if (m === a) {
			return this.editors;
		}
		return this.editors[m];
	}, getInstanceById:function (m) {
		return this.get(m);
	}, add:function (n) {
		var m = this, o = m.editors;
		o[n.id] = n;
		o.push(n);
		m._setActive(n);
		m.onAddEditor.dispatch(m, n);
		return n;
	}, remove:function (o) {
		var n = this, m, p = n.editors;
		if (!p[o.id]) {
			return null;
		}
		delete p[o.id];
		for (m = 0; m < p.length; m++) {
			if (p[m] == o) {
				p.splice(m, 1);
				break;
			}
		}
		if (n.activeEditor == o) {
			n._setActive(p[0]);
		}
		o.destroy();
		n.onRemoveEditor.dispatch(n, o);
		return o;
	}, execCommand:function (s, q, p) {
		var r = this, o = r.get(p), m;
		switch (s) {
		  case "mceFocus":
			o.focus();
			return true;
		  case "mceAddEditor":
		  case "mceAddControl":
			if (!r.get(p)) {
				new k.Editor(p, r.settings).render();
			}
			return true;
		  case "mceAddFrameControl":
			m = p.window;
			m.tinyMCE = tinyMCE;
			m.tinymce = k;
			k.DOM.doc = m.document;
			k.DOM.win = m;
			o = new k.Editor(p.element_id, p);
			o.render();
			if (k.isIE) {
				function n() {
					o.destroy();
					m.detachEvent("onunload", n);
					m = m.tinyMCE = m.tinymce = null;
				}
				m.attachEvent("onunload", n);
			}
			p.page_window = null;
			return true;
		  case "mceRemoveEditor":
		  case "mceRemoveControl":
			if (o) {
				o.remove();
			}
			return true;
		  case "mceToggleEditor":
			if (!o) {
				r.execCommand("mceAddControl", 0, p);
				return true;
			}
			if (o.isHidden()) {
				o.show();
			} else {
				o.hide();
			}
			return true;
		}
		if (r.activeEditor) {
			return r.activeEditor.execCommand(s, q, p);
		}
		return false;
	}, execInstanceCommand:function (q, p, o, n) {
		var m = this.get(q);
		if (m) {
			return m.execCommand(p, o, n);
		}
		return false;
	}, triggerSave:function () {
		g(this.editors, function (m) {
			m.save();
		});
	}, addI18n:function (q, r) {
		var m, n = this.i18n;
		if (!k.is(q, "string")) {
			g(q, function (s, p) {
				g(s, function (u, t) {
					g(u, function (x, v) {
						if (t === "common") {
							n[p + "." + v] = x;
						} else {
							n[p + "." + t + "." + v] = x;
						}
					});
				});
			});
		} else {
			g(r, function (s, p) {
				n[q + "." + p] = s;
			});
		}
	}, _setActive:function (m) {
		this.selectedInstance = this.activeEditor = m;
	}});
})(tinymce);
(function (n) {
	var o = n.DOM, k = n.dom.Event, f = n.extend, l = n.util.Dispatcher, j = n.each, a = n.isGecko, b = n.isIE, e = n.isWebKit, d = n.is, h = n.ThemeManager, c = n.PluginManager, p = n.inArray, m = n.grep, g = n.explode;
	n.create("tinymce.Editor", {Editor:function (u, r) {
		var q = this;
		q.id = q.editorId = u;
		q.execCommands = {};
		q.queryStateCommands = {};
		q.queryValueCommands = {};
		q.isNotDirty = false;
		q.plugins = {};
		j(["onPreInit", "onBeforeRenderUI", "onPostRender", "onInit", "onRemove", "onActivate", "onDeactivate", "onClick", "onEvent", "onMouseUp", "onMouseDown", "onDblClick", "onKeyDown", "onKeyUp", "onKeyPress", "onContextMenu", "onSubmit", "onReset", "onPaste", "onPreProcess", "onPostProcess", "onBeforeSetContent", "onBeforeGetContent", "onSetContent", "onGetContent", "onLoadContent", "onSaveContent", "onNodeChange", "onChange", "onBeforeExecCommand", "onExecCommand", "onUndo", "onRedo", "onVisualAid", "onSetProgressState"], function (s) {
			q[s] = new l(q);
		});
		q.settings = r = f({id:u, language:"en", docs_language:"en", theme:"simple", skin:"default", delta_width:0, delta_height:0, popup_css:"", plugins:"", document_base_url:n.documentBaseURL, add_form_submit_trigger:1, submit_patch:1, add_unload_trigger:1, convert_urls:1, relative_urls:1, remove_script_host:1, table_inline_editing:0, object_resizing:1, cleanup:1, accessibility_focus:1, custom_shortcuts:1, custom_undo_redo_keyboard_shortcuts:1, custom_undo_redo_restore_selection:1, custom_undo_redo:1, doctype:n.isIE6 ? "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" : "<!DOCTYPE>", visual_table_class:"mceItemTable", visual:1, font_size_style_values:"xx-small,x-small,small,medium,large,x-large,xx-large", apply_source_formatting:1, directionality:"ltr", forced_root_block:"p", hidden_input:1, padd_empty_editor:1, render_ui:1, init_theme:1, force_p_newlines:1, indentation:"30px", keep_styles:1, fix_table_elements:1, inline_styles:1, convert_fonts_to_spans:true, indent:"simple", indent_before:"p,h1,h2,h3,h4,h5,h6,blockquote,div,title,style,pre,script,td,ul,li,area", indent_after:"p,h1,h2,h3,h4,h5,h6,blockquote,div,title,style,pre,script,td,ul,li,area", validate:true, entity_encoding:"named", url_converter:q.convertURL, url_converter_scope:q, ie7_compat:true}, r);
		q.documentBaseURI = new n.util.URI(r.document_base_url || n.documentBaseURL, {base_uri:tinyMCE.baseURI});
		q.baseURI = n.baseURI;
		q.contentCSS = [];
		q.execCallback("setup", q);
	}, render:function (u) {
		var v = this, x = v.settings, y = v.id, q = n.ScriptLoader;
		if (!k.domLoaded) {
			k.add(document, "init", function () {
				v.render();
			});
			return;
		}
		tinyMCE.settings = x;
		if (!v.getElement()) {
			return;
		}
		if (n.isIDevice) {
			return;
		}
		if (!/TEXTAREA|INPUT/i.test(v.getElement().nodeName) && x.hidden_input && o.getParent(y, "form")) {
			o.insertAfter(o.create("input", {type:"hidden", name:y}), y);
		}
		if (n.WindowManager) {
			v.windowManager = new n.WindowManager(v);
		}
		if (x.encoding == "xml") {
			v.onGetContent.add(function (s, t) {
				if (t.save) {
					t.content = o.encode(t.content);
				}
			});
		}
		if (x.add_form_submit_trigger) {
			v.onSubmit.addToTop(function () {
				if (v.initialized) {
					v.save();
					v.isNotDirty = 1;
				}
			});
		}
		if (x.add_unload_trigger) {
			v._beforeUnload = tinyMCE.onBeforeUnload.add(function () {
				if (v.initialized && !v.destroyed && !v.isHidden()) {
					v.save({format:"raw", no_events:true});
				}
			});
		}
		n.addUnload(v.destroy, v);
		if (x.submit_patch) {
			v.onBeforeRenderUI.add(function () {
				var s = v.getElement().form;
				if (!s) {
					return;
				}
				if (s._mceOldSubmit) {
					return;
				}
				if (!s.submit.nodeType && !s.submit.length) {
					v.formElement = s;
					s._mceOldSubmit = s.submit;
					s.submit = function () {
						n.triggerSave();
						v.isNotDirty = 1;
						return v.formElement._mceOldSubmit(v.formElement);
					};
				}
				s = null;
			});
		}
		function r() {
			if (x.language && x.language_load !== false) {
				q.add(n.baseURL + "/langs/" + x.language + ".js");
			}
			if (x.theme && x.theme.charAt(0) != "-" && !h.urls[x.theme]) {
				h.load(x.theme, "themes/" + x.theme + "/editor_template" + n.suffix + ".js");
			}
			j(g(x.plugins), function (s) {
				if (s && s.charAt(0) != "-" && !c.urls[s]) {
					if (s == "safari") {
						return;
					}
					c.load(s, "plugins/" + s + "/editor_plugin" + n.suffix + ".js");
				}
			});
			q.loadQueue(function () {
				if (!v.removed) {
					v.init();
				}
			});
		}
		r();
	}, init:function () {
		var v, G = this, H = G.settings, D, A, C = G.getElement(), r, q, E, y, B, F, z;
		n.add(G);
		if (H.theme) {
			H.theme = H.theme.replace(/-/, "");
			r = h.get(H.theme);
			G.theme = new r();
			if (G.theme.init && H.init_theme) {
				G.theme.init(G, h.urls[H.theme] || n.documentBaseURL.replace(/\/$/, ""));
			}
		}
		j(g(H.plugins.replace(/\-/g, "")), function (I) {
			var J = c.get(I), t = c.urls[I] || n.documentBaseURL.replace(/\/$/, ""), s;
			if (J) {
				s = new J(G, t);
				G.plugins[I] = s;
				if (s.init) {
					s.init(G, t);
				}
			}
		});
		if (H.popup_css !== false) {
			if (H.popup_css) {
				H.popup_css = G.documentBaseURI.toAbsolute(H.popup_css);
			} else {
				H.popup_css = G.baseURI.toAbsolute("themes/" + H.theme + "/skins/" + H.skin + "/dialog.css");
			}
		}
		if (H.popup_css_add) {
			H.popup_css += "," + G.documentBaseURI.toAbsolute(H.popup_css_add);
		}
		G.controlManager = new n.ControlManager(G);
		if (H.custom_undo_redo) {
			G.onExecCommand.add(function (t, I, u, J, s) {
				if (I != "Undo" && I != "Redo" && I != "mceRepaint" && (!s || !s.skip_undo)) {
					G.undoManager.add();
				}
			});
		}
		G.onExecCommand.add(function (s, t) {
			if (!/^(FontName|FontSize)$/.test(t)) {
				G.nodeChanged();
			}
		});
		if (a) {
			function x(s, t) {
				if (!t || !t.initial) {
					G.execCommand("mceRepaint");
				}
			}
			G.onUndo.add(x);
			G.onRedo.add(x);
			G.onSetContent.add(x);
		}
		G.onBeforeRenderUI.dispatch(G, G.controlManager);
		if (H.render_ui) {
			D = H.width || C.style.width || C.offsetWidth;
			A = H.height || C.style.height || C.offsetHeight;
			G.orgDisplay = C.style.display;
			F = /^[0-9\.]+(|px)$/i;
			if (F.test("" + D)) {
				D = Math.max(parseInt(D) + (r.deltaWidth || 0), 100);
			}
			if (F.test("" + A)) {
				A = Math.max(parseInt(A) + (r.deltaHeight || 0), 100);
			}
			r = G.theme.renderUI({targetNode:C, width:D, height:A, deltaWidth:H.delta_width, deltaHeight:H.delta_height});
			G.editorContainer = r.editorContainer;
		}
		if (document.domain && location.hostname != document.domain) {
			n.relaxedDomain = document.domain;
		}
		o.setStyles(r.sizeContainer || r.editorContainer, {width:D, height:A});
		if (H.content_css) {
			n.each(g(H.content_css), function (s) {
				G.contentCSS.push(G.documentBaseURI.toAbsolute(s));
			});
		}
		A = (r.iframeHeight || A) + (typeof (A) == "number" ? (r.deltaHeight || 0) : "");
		if (A < 100) {
			A = 100;
		}
		G.iframeHTML = H.doctype + "<html><head xmlns=\"http://www.w3.org/1999/xhtml\">";
		if (H.document_base_url != n.documentBaseURL) {
			G.iframeHTML += "<base href=\"" + G.documentBaseURI.getURI() + "\" />";
		}
		if (H.ie7_compat) {
			G.iframeHTML += "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\" />";
		} else {
			G.iframeHTML += "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />";
		}
		G.iframeHTML += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />";
		if (!a || !/Firefox\/2/.test(navigator.userAgent)) {
			for (z = 0; z < G.contentCSS.length; z++) {
				G.iframeHTML += "<link type=\"text/css\" rel=\"stylesheet\" href=\"" + G.contentCSS[z] + "\" />";
			}
			G.contentCSS = [];
		}
		y = H.body_id || "tinymce";
		if (y.indexOf("=") != -1) {
			y = G.getParam("body_id", "", "hash");
			y = y[G.id] || y;
		}
		B = H.body_class || "";
		if (B.indexOf("=") != -1) {
			B = G.getParam("body_class", "", "hash");
			B = B[G.id] || "";
		}
		G.iframeHTML += "</head><body id=\"" + y + "\" class=\"mceContentBody " + B + "\"></body></html>";
		if (n.relaxedDomain && (b || (n.isOpera && parseFloat(opera.version()) < 11))) {
			E = "javascript:(function(){document.open();document.domain=\"" + document.domain + "\";var ed = window.parent.tinyMCE.get(\"" + G.id + "\");document.write(ed.iframeHTML);document.close();ed.setupIframe();})()";
		}
		v = o.add(r.iframeContainer, "iframe", {id:G.id + "_ifr", src:E || "javascript:\"\"", frameBorder:"0", style:{width:"100%", height:A}});
		G.contentAreaContainer = r.iframeContainer;
		o.get(r.editorContainer).style.display = G.orgDisplay;
		o.get(G.id).style.display = "none";
		if (!n.relaxedDomain || !E) {
			G.setupIframe();
		}
		C = v = r = null;
	}, setupIframe:function () {
		var u = this, y = u.settings, z = o.get(u.id), A = u.getDoc(), x, q;
		if (!b || !n.relaxedDomain) {
			A.open();
			A.write(u.iframeHTML);
			A.close();
			if (n.relaxedDomain) {
				A.domain = n.relaxedDomain;
			}
		}
		if (!b) {
			try {
				if (!y.readonly) {
					A.designMode = "On";
				}
			}
			catch (r) {
			}
		}
		if (b) {
			q = u.getBody();
			o.hide(q);
			if (!y.readonly) {
				q.contentEditable = true;
			}
			o.show(q);
		}
		u.schema = new n.html.Schema(y);
		u.dom = new n.dom.DOMUtils(u.getDoc(), {keep_values:true, url_converter:u.convertURL, url_converter_scope:u, hex_colors:y.force_hex_style_colors, class_filter:y.class_filter, update_styles:1, fix_ie_paragraphs:1, schema:u.schema});
		u.parser = new n.html.DomParser(y, u.schema);
		u.parser.addAttributeFilter("src,href,style", function (s, t) {
			var B = s.length, C, E = u.dom, D;
			while (B--) {
				C = s[B];
				D = C.attr(t);
				if (t === "style") {
					C.attr("data-mce-style", E.serializeStyle(E.parseStyle(D), C.name));
				} else {
					C.attr("data-mce-" + t, u.convertURL(D, t, C.name));
				}
			}
		});
		u.parser.addNodeFilter("script", function (s, t) {
			var B = s.length;
			while (B--) {
				s[B].attr("type", "mce-text/javascript");
			}
		});
		u.parser.addNodeFilter("#cdata", function (s, t) {
			var B = s.length, C;
			while (B--) {
				C = s[B];
				C.type = 8;
				C.name = "#comment";
				C.value = "[CDATA[" + C.value + "]]";
			}
		});
		u.parser.addNodeFilter("p,h1,h2,h3,h4,h5,h6,div", function (s, t) {
			var C = s.length, D, B = u.schema.getEmptyElements();
			while (C--) {
				D = s[C];
				if (D.isEmpty(B)) {
					D.empty().append(new n.html.Node("br", 1)).empty = true;
				}
			}
		});
		u.serializer = new n.dom.Serializer(y, u.dom, u.schema);
		u.selection = new n.dom.Selection(u.dom, u.getWin(), u.serializer);
		u.formatter = new n.Formatter(this);
		u.formatter.register({alignleft:[{selector:"p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li", styles:{textAlign:"left"}}, {selector:"img,table", styles:{"float":"left"}}], aligncenter:[{selector:"p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li", styles:{textAlign:"center"}}, {selector:"img", styles:{display:"block", marginLeft:"auto", marginRight:"auto"}}, {selector:"table", styles:{marginLeft:"auto", marginRight:"auto"}}], alignright:[{selector:"p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li", styles:{textAlign:"right"}}, {selector:"img,table", styles:{"float":"right"}}], alignfull:[{selector:"p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li", styles:{textAlign:"justify"}}], bold:[{inline:"b", remove:"all"}], italic:[{inline:"i", remove:"all"}], underline:[{inline:"u", remove:"all"}], strikethrough:[{inline:"span", styles:{textDecoration:"line-through"}, exact:true}, {inline:"strike", remove:"all"}], forecolor:{inline:"span", styles:{color:"%value"}, wrap_links:false}, hilitecolor:{inline:"span", styles:{backgroundColor:"%value"}, wrap_links:false}, fontname:{inline:"span", styles:{fontFamily:"%value"}}, fontsize:{inline:"span", styles:{fontSize:"%value"}}, fontsize_class:{inline:"span", attributes:{"class":"%value"}}, blockquote:{block:"blockquote", wrapper:1, remove:"all"}, removeformat:[{selector:"b,strong,em,i,font,u,strike", remove:"all", split:true, expand:false, block_expand:true, deep:true}, {selector:"span", attributes:["style", "class"], remove:"empty", split:true, expand:false, deep:true}, {selector:"*", attributes:["style", "class"], split:false, expand:false, deep:true}]});
		j("p h1 h2 h3 h4 h5 h6 div address pre div code dt dd samp".split(/\s/), function (s) {
			u.formatter.register(s, {block:s, remove:"all"});
		});
		u.formatter.register(u.settings.formats);
		u.undoManager = new n.UndoManager(u);
		u.undoManager.onAdd.add(function (t, s) {
			if (!s.initial) {
				return u.onChange.dispatch(u, s, t);
			}
		});
		u.undoManager.onUndo.add(function (t, s) {
			return u.onUndo.dispatch(u, s, t);
		});
		u.undoManager.onRedo.add(function (t, s) {
			return u.onRedo.dispatch(u, s, t);
		});
		u.forceBlocks = new n.ForceBlocks(u, {forced_root_block:y.forced_root_block});
		u.editorCommands = new n.EditorCommands(u);
		u.serializer.onPreProcess.add(function (s, t) {
			return u.onPreProcess.dispatch(u, t, s);
		});
		u.serializer.onPostProcess.add(function (s, t) {
			return u.onPostProcess.dispatch(u, t, s);
		});
		u.onPreInit.dispatch(u);
		if (!y.gecko_spellcheck) {
			u.getBody().spellcheck = 0;
		}
		if (!y.readonly) {
			u._addEvents();
		}
		u.controlManager.onPostRender.dispatch(u, u.controlManager);
		u.onPostRender.dispatch(u);
		if (y.directionality) {
			u.getBody().dir = y.directionality;
		}
		if (y.nowrap) {
			u.getBody().style.whiteSpace = "nowrap";
		}
		if (y.handle_node_change_callback) {
			u.onNodeChange.add(function (t, s, B) {
				u.execCallback("handle_node_change_callback", u.id, B, -1, -1, true, u.selection.isCollapsed());
			});
		}
		if (y.save_callback) {
			u.onSaveContent.add(function (s, B) {
				var t = u.execCallback("save_callback", u.id, B.content, u.getBody());
				if (t) {
					B.content = t;
				}
			});
		}
		if (y.onchange_callback) {
			u.onChange.add(function (t, s) {
				u.execCallback("onchange_callback", u, s);
			});
		}
		if (y.protect) {
			u.onBeforeSetContent.add(function (s, t) {
				if (y.protect) {
					j(y.protect, function (B) {
						t.content = t.content.replace(B, function (C) {
							return "<!--mce:protected " + escape(C) + "-->";
						});
					});
				}
			});
		}
		if (y.convert_newlines_to_brs) {
			u.onBeforeSetContent.add(function (s, t) {
				if (t.initial) {
					t.content = t.content.replace(/\r?\n/g, "<br />");
				}
			});
		}
		if (y.preformatted) {
			u.onPostProcess.add(function (s, t) {
				t.content = t.content.replace(/^\s*<pre.*?>/, "");
				t.content = t.content.replace(/<\/pre>\s*$/, "");
				if (t.set) {
					t.content = "<pre class=\"mceItemHidden\">" + t.content + "</pre>";
				}
			});
		}
		if (y.verify_css_classes) {
			u.serializer.attribValueFilter = function (D, B) {
				var C, t;
				if (D == "class") {
					if (!u.classesRE) {
						t = u.dom.getClasses();
						if (t.length > 0) {
							C = "";
							j(t, function (s) {
								C += (C ? "|" : "") + s["class"];
							});
							u.classesRE = new RegExp("(" + C + ")", "gi");
						}
					}
					return !u.classesRE || /(\bmceItem\w+\b|\bmceTemp\w+\b)/g.test(B) || u.classesRE.test(B) ? B : "";
				}
				return B;
			};
		}
		if (y.cleanup_callback) {
			u.onBeforeSetContent.add(function (s, t) {
				t.content = u.execCallback("cleanup_callback", "insert_to_editor", t.content, t);
			});
			u.onPreProcess.add(function (s, t) {
				if (t.set) {
					u.execCallback("cleanup_callback", "insert_to_editor_dom", t.node, t);
				}
				if (t.get) {
					u.execCallback("cleanup_callback", "get_from_editor_dom", t.node, t);
				}
			});
			u.onPostProcess.add(function (s, t) {
				if (t.set) {
					t.content = u.execCallback("cleanup_callback", "insert_to_editor", t.content, t);
				}
				if (t.get) {
					t.content = u.execCallback("cleanup_callback", "get_from_editor", t.content, t);
				}
			});
		}
		if (y.save_callback) {
			u.onGetContent.add(function (s, t) {
				if (t.save) {
					t.content = u.execCallback("save_callback", u.id, t.content, u.getBody());
				}
			});
		}
		if (y.handle_event_callback) {
			u.onEvent.add(function (s, t, B) {
				if (u.execCallback("handle_event_callback", t, s, B) === false) {
					k.cancel(t);
				}
			});
		}
		u.onSetContent.add(function () {
			u.addVisual(u.getBody());
		});
		if (y.padd_empty_editor) {
			u.onPostProcess.add(function (s, t) {
				t.content = t.content.replace(/^(<p[^>]*>(&nbsp;|&#160;|\s|\u00a0|)<\/p>[\r\n]*|<br \/>[\r\n]*)$/, "");
			});
		}
		if (a) {
			function v(s, t) {
				j(s.dom.select("a"), function (C) {
					var B = C.parentNode;
					if (s.dom.isBlock(B) && B.lastChild === C) {
						s.dom.add(B, "br", {"data-mce-bogus":1});
					}
				});
			}
			u.onExecCommand.add(function (s, t) {
				if (t === "CreateLink") {
					v(s);
				}
			});
			u.onSetContent.add(u.selection.onSetContent.add(v));
			if (!y.readonly) {
				try {
					A.designMode = "Off";
					A.designMode = "On";
				}
				catch (r) {
				}
			}
		}
		setTimeout(function () {
			if (u.removed) {
				return;
			}
			u.load({initial:true, format:"html"});
			u.startContent = u.getContent({format:"raw"});
			u.undoManager.add();
			u.initialized = true;
			u.onInit.dispatch(u);
			u.execCallback("setupcontent_callback", u.id, u.getBody(), u.getDoc());
			u.execCallback("init_instance_callback", u);
			u.focus(true);
			u.nodeChanged({initial:1});
			j(u.contentCSS, function (s) {
				u.dom.loadCSS(s);
			});
			if (y.auto_focus) {
				setTimeout(function () {
					var s = n.get(y.auto_focus);
					s.selection.select(s.getBody(), 1);
					s.selection.collapse(1);
					s.getWin().focus();
				}, 100);
			}
		}, 1);
		z = null;
	}, focus:function (u) {
		var y, r = this, x = r.settings.content_editable, s, q, v = r.getDoc();
		if (!u) {
			s = r.selection.getRng();
			if (s.item) {
				q = s.item(0);
			}
			if (!x) {
				r.getWin().focus();
			}
			if (q && q.ownerDocument == v) {
				s = v.body.createControlRange();
				s.addElement(q);
				s.select();
			}
		}
		if (n.activeEditor != r) {
			if ((y = n.activeEditor) != null) {
				y.onDeactivate.dispatch(y, r);
			}
			r.onActivate.dispatch(r, y);
		}
		n._setActive(r);
	}, execCallback:function (v) {
		var q = this, u = q.settings[v], r;
		if (!u) {
			return;
		}
		if (q.callbackLookup && (r = q.callbackLookup[v])) {
			u = r.func;
			r = r.scope;
		}
		if (d(u, "string")) {
			r = u.replace(/\.\w+$/, "");
			r = r ? n.resolve(r) : 0;
			u = n.resolve(u);
			q.callbackLookup = q.callbackLookup || {};
			q.callbackLookup[v] = {func:u, scope:r};
		}
		return u.apply(r || q, Array.prototype.slice.call(arguments, 1));
	}, translate:function (q) {
		var t = this.settings.language || "en", r = n.i18n;
		if (!q) {
			return "";
		}
		return r[t + "." + q] || q.replace(/{\#([^}]+)\}/g, function (u, s) {
			return r[t + "." + s] || "{#" + s + "}";
		});
	}, getLang:function (r, q) {
		return n.i18n[(this.settings.language || "en") + "." + r] || (d(q) ? q : "{#" + r + "}");
	}, getParam:function (x, s, q) {
		var t = n.trim, r = d(this.settings[x]) ? this.settings[x] : s, u;
		if (q === "hash") {
			u = {};
			if (d(r, "string")) {
				j(r.indexOf("=") > 0 ? r.split(/[;,](?![^=;,]*(?:[;,]|$))/) : r.split(","), function (y) {
					y = y.split("=");
					if (y.length > 1) {
						u[t(y[0])] = t(y[1]);
					} else {
						u[t(y[0])] = t(y);
					}
				});
			} else {
				u = r;
			}
			return u;
		}
		return r;
	}, nodeChanged:function (u) {
		var q = this, r = q.selection, v = (b ? r.getNode() : r.getStart()) || q.getBody();
		if (q.initialized) {
			u = u || {};
			v = b && v.ownerDocument != q.getDoc() ? q.getBody() : v;
			u.parents = [];
			q.dom.getParent(v, function (s) {
				if (s.nodeName == "BODY") {
					return true;
				}
				u.parents.push(s);
			});
			q.onNodeChange.dispatch(q, u ? u.controlManager || q.controlManager : q.controlManager, v, r.isCollapsed(), u);
		}
	}, addButton:function (u, r) {
		var q = this;
		q.buttons = q.buttons || {};
		q.buttons[u] = r;
	}, addCommand:function (q, s, r) {
		this.execCommands[q] = {func:s, scope:r || this};
	}, addQueryStateHandler:function (q, s, r) {
		this.queryStateCommands[q] = {func:s, scope:r || this};
	}, addQueryValueHandler:function (q, s, r) {
		this.queryValueCommands[q] = {func:s, scope:r || this};
	}, addShortcut:function (s, v, q, u) {
		var r = this, x;
		if (!r.settings.custom_shortcuts) {
			return false;
		}
		r.shortcuts = r.shortcuts || {};
		if (d(q, "string")) {
			x = q;
			q = function () {
				r.execCommand(x, false, null);
			};
		}
		if (d(q, "object")) {
			x = q;
			q = function () {
				r.execCommand(x[0], x[1], x[2]);
			};
		}
		j(g(s), function (t) {
			var y = {func:q, scope:u || this, desc:v, alt:false, ctrl:false, shift:false};
			j(g(t, "+"), function (z) {
				switch (z) {
				  case "alt":
				  case "ctrl":
				  case "shift":
					y[z] = true;
					break;
				  default:
					y.charCode = z.charCodeAt(0);
					y.keyCode = z.toUpperCase().charCodeAt(0);
				}
			});
			r.shortcuts[(y.ctrl ? "ctrl" : "") + "," + (y.alt ? "alt" : "") + "," + (y.shift ? "shift" : "") + "," + y.keyCode] = y;
		});
		return true;
	}, execCommand:function (y, x, A, q) {
		var u = this, v = 0, z, r;
		if (!/^(mceAddUndoLevel|mceEndUndoLevel|mceBeginUndoLevel|mceRepaint|SelectAll)$/.test(y) && (!q || !q.skip_focus)) {
			u.focus();
		}
		z = {};
		u.onBeforeExecCommand.dispatch(u, y, x, A, z);
		if (z.terminate) {
			return false;
		}
		if (u.execCallback("execcommand_callback", u.id, u.selection.getNode(), y, x, A)) {
			u.onExecCommand.dispatch(u, y, x, A, q);
			return true;
		}
		if (z = u.execCommands[y]) {
			r = z.func.call(z.scope, x, A);
			if (r !== true) {
				u.onExecCommand.dispatch(u, y, x, A, q);
				return r;
			}
		}
		j(u.plugins, function (s) {
			if (s.execCommand && s.execCommand(y, x, A)) {
				u.onExecCommand.dispatch(u, y, x, A, q);
				v = 1;
				return false;
			}
		});
		if (v) {
			return true;
		}
		if (u.theme && u.theme.execCommand && u.theme.execCommand(y, x, A)) {
			u.onExecCommand.dispatch(u, y, x, A, q);
			return true;
		}
		if (u.editorCommands.execCommand(y, x, A)) {
			u.onExecCommand.dispatch(u, y, x, A, q);
			return true;
		}
		u.getDoc().execCommand(y, x, A);
		u.onExecCommand.dispatch(u, y, x, A, q);
	}, queryCommandState:function (v) {
		var r = this, x, u;
		if (r._isHidden()) {
			return;
		}
		if (x = r.queryStateCommands[v]) {
			u = x.func.call(x.scope);
			if (u !== true) {
				return u;
			}
		}
		x = r.editorCommands.queryCommandState(v);
		if (x !== -1) {
			return x;
		}
		try {
			return this.getDoc().queryCommandState(v);
		}
		catch (q) {
		}
	}, queryCommandValue:function (x) {
		var r = this, v, u;
		if (r._isHidden()) {
			return;
		}
		if (v = r.queryValueCommands[x]) {
			u = v.func.call(v.scope);
			if (u !== true) {
				return u;
			}
		}
		v = r.editorCommands.queryCommandValue(x);
		if (d(v)) {
			return v;
		}
		try {
			return this.getDoc().queryCommandValue(x);
		}
		catch (q) {
		}
	}, show:function () {
		var q = this;
		o.show(q.getContainer());
		o.hide(q.id);
		q.load();
	}, hide:function () {
		var q = this, r = q.getDoc();
		if (b && r) {
			r.execCommand("SelectAll");
		}
		q.save();
		o.hide(q.getContainer());
		o.setStyle(q.id, "display", q.orgDisplay);
	}, isHidden:function () {
		return !o.isHidden(this.id);
	}, setProgressState:function (q, r, s) {
		this.onSetProgressState.dispatch(this, q, r, s);
		return q;
	}, load:function (u) {
		var q = this, s = q.getElement(), r;
		if (s) {
			u = u || {};
			u.load = true;
			r = q.setContent(d(s.value) ? s.value : s.innerHTML, u);
			u.element = s;
			if (!u.no_events) {
				q.onLoadContent.dispatch(q, u);
			}
			u.element = s = null;
			return r;
		}
	}, save:function (v) {
		var q = this, u = q.getElement(), r, s;
		if (!u || !q.initialized) {
			return;
		}
		v = v || {};
		v.save = true;
		if (!v.no_events) {
			q.undoManager.typing = false;
			q.undoManager.add();
		}
		v.element = u;
		r = v.content = q.getContent(v);
		if (!v.no_events) {
			q.onSaveContent.dispatch(q, v);
		}
		r = v.content;
		if (!/TEXTAREA|INPUT/i.test(u.nodeName)) {
			u.innerHTML = r;
			if (s = o.getParent(q.id, "form")) {
				j(s.elements, function (t) {
					if (t.name == q.id) {
						t.value = r;
						return false;
					}
				});
			}
		} else {
			u.value = r;
		}
		v.element = u = null;
		return r;
	}, setContent:function (u, t) {
		var s = this, r, q = s.getBody();
		t = t || {};
		t.format = t.format || "html";
		t.set = true;
		t.content = u;
		if (!t.no_events) {
			s.onBeforeSetContent.dispatch(s, t);
		}
		if (!n.isIE && (u.length === 0 || /^\s+$/.test(u))) {
			q.innerHTML = "<br data-mce-bogus=\"1\" />";
			return;
		}
		if (t.format !== "raw") {
			t.content = new n.html.Serializer({}, s.schema).serialize(s.parser.parse(t.content));
		}
		q.innerHTML = n.trim(t.content);
		if (!t.no_events) {
			s.onSetContent.dispatch(s, t);
		}
		return t.content;
	}, getContent:function (r) {
		var q = this, s;
		r = r || {};
		r.format = r.format || "html";
		r.get = true;
		if (!r.no_events) {
			q.onBeforeGetContent.dispatch(q, r);
		}
		if (r.format == "raw") {
			s = q.getBody().innerHTML;
		} else {
			s = q.serializer.serialize(q.getBody(), r);
		}
		r.content = n.trim(s);
		if (!r.no_events) {
			q.onGetContent.dispatch(q, r);
		}
		return r.content;
	}, isDirty:function () {
		var q = this;
		return n.trim(q.startContent) != n.trim(q.getContent({format:"raw", no_events:1})) && !q.isNotDirty;
	}, getContainer:function () {
		var q = this;
		if (!q.container) {
			q.container = o.get(q.editorContainer || q.id + "_parent");
		}
		return q.container;
	}, getContentAreaContainer:function () {
		return this.contentAreaContainer;
	}, getElement:function () {
		return o.get(this.settings.content_element || this.id);
	}, getWin:function () {
		var q = this, r;
		if (!q.contentWindow) {
			r = o.get(q.id + "_ifr");
			if (r) {
				q.contentWindow = r.contentWindow;
			}
		}
		return q.contentWindow;
	}, getDoc:function () {
		var r = this, q;
		if (!r.contentDocument) {
			q = r.getWin();
			if (q) {
				r.contentDocument = q.document;
			}
		}
		return r.contentDocument;
	}, getBody:function () {
		return this.bodyElement || this.getDoc().body;        
	}, convertURL:function (q, y, x) {
		var r = this, v = r.settings;
		if (v.urlconverter_callback) {
			return r.execCallback("urlconverter_callback", q, x, true, y);
		}
		if (!v.convert_urls || (x && x.nodeName == "LINK") || q.indexOf("file:") === 0) {
			return q;
		}
		if (v.relative_urls) {
			return r.documentBaseURI.toRelative(q);
		}
		q = r.documentBaseURI.toAbsolute(q, v.remove_script_host);
		return q;
	}, addVisual:function (u) {
		var q = this, r = q.settings;
		u = u || q.getBody();
		if (!d(q.hasVisual)) {
			q.hasVisual = r.visual;
		}
		j(q.dom.select("table,a", u), function (t) {
			var s;
			switch (t.nodeName) {
			  case "TABLE":
				s = q.dom.getAttrib(t, "border");
				if (!s || s == "0") {
					if (q.hasVisual) {
						q.dom.addClass(t, r.visual_table_class);
					} else {
						q.dom.removeClass(t, r.visual_table_class);
					}
				}
				return;
			  case "A":
				s = q.dom.getAttrib(t, "name");
				if (s) {
					if (q.hasVisual) {
						q.dom.addClass(t, "mceItemAnchor");
					} else {
						q.dom.removeClass(t, "mceItemAnchor");
					}
				}
				return;
			}
		});
		q.onVisualAid.dispatch(q, u, q.hasVisual);
	}, remove:function () {
		var q = this, r = q.getContainer();
		q.removed = 1;
		q.hide();
		q.execCallback("remove_instance_callback", q);
		q.onRemove.dispatch(q);
		q.onExecCommand.listeners = [];
		n.remove(q);
		o.remove(r);
	}, destroy:function (r) {
		var q = this;
		if (q.destroyed) {
			return;
		}
		if (!r) {
			n.removeUnload(q.destroy);
			tinyMCE.onBeforeUnload.remove(q._beforeUnload);
			if (q.theme && q.theme.destroy) {
				q.theme.destroy();
			}
			q.controlManager.destroy();
			q.selection.destroy();
			q.dom.destroy();
			if (!q.settings.content_editable) {
				k.clear(q.getWin());
				k.clear(q.getDoc());
			}
			k.clear(q.getBody());
			k.clear(q.formElement);
		}
		if (q.formElement) {
			q.formElement.submit = q.formElement._mceOldSubmit;
			q.formElement._mceOldSubmit = null;
		}
		q.contentAreaContainer = q.formElement = q.container = q.settings.content_element = q.bodyElement = q.contentDocument = q.contentWindow = null;
		if (q.selection) {
			q.selection = q.selection.win = q.selection.dom = q.selection.dom.doc = null;
		}
		q.destroyed = 1;
	}, _addEvents:function () {
		var C = this, u, D = C.settings, r = C.dom, y = {mouseup:"onMouseUp", mousedown:"onMouseDown", click:"onClick", keyup:"onKeyUp", keydown:"onKeyDown", keypress:"onKeyPress", submit:"onSubmit", reset:"onReset", contextmenu:"onContextMenu", dblclick:"onDblClick", paste:"onPaste"};
		function q(t, E) {
			var s = t.type;
			if (C.removed) {
				return;
			}
			if (C.onEvent.dispatch(C, t, E) !== false) {
				C[y[t.fakeType || t.type]].dispatch(C, t, E);
			}
		}
		j(y, function (t, s) {
			switch (s) {
			  case "contextmenu":
				if (n.isOpera) {
					r.bind(C.getBody(), "mousedown", function (E) {
						if (E.ctrlKey) {
							E.fakeType = "contextmenu";
							q(E);
						}
					});
				} else {
					r.bind(C.getBody(), s, q);
				}
				break;
			  case "paste":
				r.bind(C.getBody(), s, function (E) {
					q(E);
				});
				break;
			  case "submit":
			  case "reset":
				r.bind(C.getElement().form || o.getParent(C.id, "form"), s, q);
				break;
			  default:
				r.bind(D.content_editable ? C.getBody() : C.getDoc(), s, q);
			}
		});
		r.bind(D.content_editable ? C.getBody() : (a ? C.getDoc() : C.getWin()), "focus", function (s) {
			C.focus(true);
		});
		if (n.isGecko) {
			r.bind(C.getDoc(), "DOMNodeInserted", function (t) {
				var s;
				t = t.target;
				if (t.nodeType === 1 && t.nodeName === "IMG" && (s = t.getAttribute("data-mce-src"))) {
					t.src = C.documentBaseURI.toAbsolute(s);
				}
			});
		}
		if (a) {
			function v() {
				var F = this, H = F.getDoc(), G = F.settings;
				if (a && !G.readonly) {
					if (F._isHidden()) {
						try {
							if (!G.content_editable) {
								H.designMode = "On";
							}
						}
						catch (E) {
						}
					}
					try {
						H.execCommand("styleWithCSS", 0, false);
					}
					catch (E) {
						if (!F._isHidden()) {
							try {
								H.execCommand("useCSS", 0, true);
							}
							catch (E) {
							}
						}
					}
					if (!G.table_inline_editing) {
						try {
							H.execCommand("enableInlineTableEditing", false, false);
						}
						catch (E) {
						}
					}
					if (!G.object_resizing) {
						try {
							H.execCommand("enableObjectResizing", false, false);
						}
						catch (E) {
						}
					}
				}
			}
			C.onBeforeExecCommand.add(v);
			C.onMouseDown.add(v);
		}
		if (n.isWebKit) {
			C.onClick.add(function (s, t) {
				t = t.target;
				if (t.nodeName == "IMG" || (t.nodeName == "A" && r.hasClass(t, "mceItemAnchor"))) {
					C.selection.getSel().setBaseAndExtent(t, 0, t, 1);
					C.nodeChanged();
				}
			});
		}
		C.onMouseUp.add(C.nodeChanged);
		C.onKeyUp.add(function (s, t) {
			var E = t.keyCode;
			if ((E >= 33 && E <= 36) || (E >= 37 && E <= 40) || E == 13 || E == 45 || E == 46 || E == 8 || (n.isMac && (E == 91 || E == 93)) || t.ctrlKey) {
				C.nodeChanged();
			}
		});
		C.onReset.add(function () {
			C.setContent(C.startContent, {format:"raw"});
		});
		if (D.custom_shortcuts) {
			if (D.custom_undo_redo_keyboard_shortcuts) {
				C.addShortcut("ctrl+z", C.getLang("undo_desc"), "Undo");
				C.addShortcut("ctrl+y", C.getLang("redo_desc"), "Redo");
			}
			C.addShortcut("ctrl+b", C.getLang("bold_desc"), "Bold");
			C.addShortcut("ctrl+i", C.getLang("italic_desc"), "Italic");
			C.addShortcut("ctrl+u", C.getLang("underline_desc"), "Underline");
			for (u = 1; u <= 6; u++) {
				C.addShortcut("ctrl+" + u, "", ["FormatBlock", false, "h" + u]);
			}
			C.addShortcut("ctrl+7", "", ["FormatBlock", false, "<p>"]);
			C.addShortcut("ctrl+8", "", ["FormatBlock", false, "<div>"]);
			C.addShortcut("ctrl+9", "", ["FormatBlock", false, "<address>"]);
			function x(t) {
				var s = null;
				if (!t.altKey && !t.ctrlKey && !t.metaKey) {
					return s;
				}
				j(C.shortcuts, function (E) {
					if (n.isMac && E.ctrl != t.metaKey) {
						return;
					} else {
						if (!n.isMac && E.ctrl != t.ctrlKey) {
							return;
						}
					}
					if (E.alt != t.altKey) {
						return;
					}
					if (E.shift != t.shiftKey) {
						return;
					}
					if (t.keyCode == E.keyCode || (t.charCode && t.charCode == E.charCode)) {
						s = E;
						return false;
					}
				});
				return s;
			}
			C.onKeyUp.add(function (s, t) {
				var E = x(t);
				if (E) {
					return k.cancel(t);
				}
			});
			C.onKeyPress.add(function (s, t) {
				var E = x(t);
				if (E) {
					return k.cancel(t);
				}
			});
			C.onKeyDown.add(function (s, t) {
				var E = x(t);
				if (E) {
					E.func.call(E.scope);
					return k.cancel(t);
				}
			});
		}
		if (n.isIE) {
			r.bind(C.getDoc(), "controlselect", function (E) {
				var t = C.resizeInfo, s;
				E = E.target;
				if (E.nodeName !== "IMG") {
					return;
				}
				if (t) {
					r.unbind(t.node, t.ev, t.cb);
				}
				if (!r.hasClass(E, "mceItemNoResize")) {
					ev = "resizeend";
					s = r.bind(E, ev, function (G) {
						var F;
						G = G.target;
						if (F = r.getStyle(G, "width")) {
							r.setAttrib(G, "width", F.replace(/[^0-9%]+/g, ""));
							r.setStyle(G, "width", "");
						}
						if (F = r.getStyle(G, "height")) {
							r.setAttrib(G, "height", F.replace(/[^0-9%]+/g, ""));
							r.setStyle(G, "height", "");
						}
					});
				} else {
					ev = "resizestart";
					s = r.bind(E, "resizestart", k.cancel, k);
				}
				t = C.resizeInfo = {node:E, ev:ev, cb:s};
			});
			C.onKeyDown.add(function (s, E) {
				var t;
				switch (E.keyCode) {
				  case 8:
					t = C.getDoc().selection;
					if (t.createRange && t.createRange().item) {
						s.dom.remove(t.createRange().item(0));
						return k.cancel(E);
					}
				}
			});
		}
		if (n.isOpera) {
			C.onClick.add(function (s, t) {
				k.prevent(t);
			});
		}
		if (D.custom_undo_redo) {
			function z() {
				C.undoManager.typing = false;
				C.undoManager.add();
			}
			r.bind(C.getDoc(), "focusout", function (s) {
				if (!C.removed && C.undoManager.typing) {
					z();
				}
			});
			C.onKeyUp.add(function (t, G) {
				var s, F, E;
				if (b && G.keyCode == 8) {
					s = C.selection.getRng();
					if (s.parentElement) {
						F = s.parentElement();
						E = C.selection.getBookmark();
						F.innerHTML = F.innerHTML;
						C.selection.moveToBookmark(E);
					}
				}
				if ((G.keyCode >= 33 && G.keyCode <= 36) || (G.keyCode >= 37 && G.keyCode <= 40) || G.keyCode == 13 || G.keyCode == 45 || G.ctrlKey) {
					z();
				}
			});
			C.onKeyDown.add(function (t, H) {
				var s, G, F;
				if (b && H.keyCode == 46) {
					s = C.selection.getRng();
					if (s.parentElement) {
						G = s.parentElement();
						if (H.ctrlKey) {
							s.moveEnd("word", 1);
							s.select();
						}
						C.selection.getSel().clear();
						if (s.parentElement() == G) {
							F = C.selection.getBookmark();
							try {
								G.innerHTML = G.innerHTML;
							}
							catch (E) {
							}
							C.selection.moveToBookmark(F);
						}
						H.preventDefault();
						return;
					}
				}
				if ((H.keyCode >= 33 && H.keyCode <= 36) || (H.keyCode >= 37 && H.keyCode <= 40) || H.keyCode == 13 || H.keyCode == 45) {
					if (C.undoManager.typing) {
						z();
					}
					return;
				}
				if (!C.undoManager.typing) {
					C.undoManager.add();
					C.undoManager.typing = true;
				}
			});
			C.onMouseDown.add(function () {
				if (C.undoManager.typing) {
					z();
				}
			});
		}
		if (n.isGecko) {
			function B() {
				C.undoManager.typing = false;
				C.undoManager.add();
				var s = C.dom.getAttribs(C.selection.getStart().cloneNode(false));
				return function () {
					var t = C.selection.getStart();
					C.dom.removeAllAttribs(t);
					j(s, function (E) {
						t.setAttributeNode(E.cloneNode(true));
					});
					C.undoManager.typing = false;
					C.undoManager.add();
				};
			}
			function A() {
				var t = C.selection;
				return !t.isCollapsed() && t.getStart() != t.getEnd();
			}
			C.onKeyPress.add(function (s, E) {
				if ((E.keyCode == 8 || E.keyCode == 46) && A()) {
					var t = B();
					C.getDoc().execCommand("delete", false, null);
					t();
					return k.cancel(E);
				}
			});
			C.dom.bind(C.getDoc(), "cut", function (t) {
				if (A()) {
					var s = B();
					C.onKeyUp.addToTop(k.cancel, k);
					setTimeout(function () {
						s();
						C.onKeyUp.remove(k.cancel, k);
					}, 0);
				}
			});
		}
	}, _isHidden:function () {
		var q;
		if (!a) {
			return 0;
		}
		q = this.selection.getSel();
		return (!q || !q.rangeCount || q.rangeCount == 0);
	}});
})(tinymce);
(function (c) {
	var d = c.each, e, a = true, b = false;
	c.EditorCommands = function (o) {
		var m = o.dom, q = o.selection, k = {state:{}, exec:{}, value:{}}, l = o.settings, p;
		function r(z, y, x) {
			var v;
			z = z.toLowerCase();
			if (v = k.exec[z]) {
				v(z, y, x);
				return a;
			}
			return b;
		}
		function n(x) {
			var v;
			x = x.toLowerCase();
			if (v = k.state[x]) {
				return v(x);
			}
			return -1;
		}
		function h(x) {
			var v;
			x = x.toLowerCase();
			if (v = k.value[x]) {
				return v(x);
			}
			return b;
		}
		function u(v, x) {
			x = x || "exec";
			d(v, function (z, y) {
				d(y.toLowerCase().split(","), function (A) {
					k[x][A] = z;
				});
			});
		}
		c.extend(this, {execCommand:r, queryCommandState:n, queryCommandValue:h, addCommands:u});
		function f(y, x, v) {
			if (x === e) {
				x = b;
			}
			if (v === e) {
				v = null;
			}
			return o.getDoc().execCommand(y, x, v);
		}
		function t(v) {
			return o.formatter.match(v);
		}
		function s(v, x) {
			o.formatter.toggle(v, x ? {value:x} : e);
		}
		function j(v) {
			p = q.getBookmark(v);
		}
		function g() {
			q.moveToBookmark(p);
		}
		u({"mceResetDesignMode,mceBeginUndoLevel":function () {
		}, "mceEndUndoLevel,mceAddUndoLevel":function () {
			o.undoManager.add();
		}, "Cut,Copy,Paste":function (z) {
			var y = o.getDoc(), v;
			try {
				f(z);
			}
			catch (x) {
				v = a;
			}
			if (v || !y.queryCommandSupported(z)) {
				if (c.isGecko) {
					o.windowManager.confirm(o.getLang("clipboard_msg"), function (A) {
						if (A) {
							open("http://www.mozilla.org/editor/midasdemo/securityprefs.html", "_blank");
						}
					});
				} else {
					o.windowManager.alert(o.getLang("clipboard_no_support"));
				}
			}
		}, unlink:function (v) {
			if (q.isCollapsed()) {
				q.select(q.getNode());
			}
			f(v);
			q.collapse(b);
		}, "JustifyLeft,JustifyCenter,JustifyRight,JustifyFull":function (v) {
			var x = v.substring(7);
			d("left,center,right,full".split(","), function (y) {
				if (x != y) {
					o.formatter.remove("align" + y);
				}
			});
			s("align" + x);
			r("mceRepaint");
		}, "InsertUnorderedList,InsertOrderedList":function (y) {
			var v, x;
			f(y);
			v = m.getParent(q.getNode(), "ol,ul");
			if (v) {
				x = v.parentNode;
				if (/^(H[1-6]|P|ADDRESS|PRE)$/.test(x.nodeName)) {
					j();
					m.split(x, v);
					g();
				}
			}
		}, "Bold,Italic,Underline,Strikethrough":function (v) {
			s(v);
		}, "ForeColor,HiliteColor,FontName":function (y, x, v) {
			s(y, v);
		}, FontSize:function (z, y, x) {
			var v, A;
			if (x >= 1 && x <= 7) {
				A = c.explode(l.font_size_style_values);
				v = c.explode(l.font_size_classes);
				if (v) {
					x = v[x - 1] || x;
				} else {
					x = A[x - 1] || x;
				}
			}
			s(z, x);
		}, RemoveFormat:function (v) {
			o.formatter.remove(v);
		}, mceBlockQuote:function (v) {
			s("blockquote");
		}, FormatBlock:function (y, x, v) {
			return s(v || "p");
		}, mceCleanup:function () {
			var v = q.getBookmark();
			o.setContent(o.getContent({cleanup:a}), {cleanup:a});
			q.moveToBookmark(v);
		}, mceRemoveNode:function (z, y, x) {
			var v = x || q.getNode();
			if (v != o.getBody()) {
				j();
				o.dom.remove(v, a);
				g();
			}
		}, mceSelectNodeDepth:function (z, y, x) {
			var v = 0;
			m.getParent(q.getNode(), function (A) {
				if (A.nodeType == 1 && v++ == x) {
					q.select(A);
					return b;
				}
			}, o.getBody());
		}, mceSelectNode:function (y, x, v) {
			q.select(v);
		}, mceInsertContent:function (C, B, A) {
			var y, v, x, z;
			if (A.indexOf("{$caret}") == -1) {
				A += "{$caret}";
			}
			q.setContent("<span id=\"__mce\">\ufeff</span>");
			m.setOuterHTML("__mce", A.replace(/\{\$caret\}/, "<span data-mce-type=\"bookmark\" id=\"__mce\">\ufeff</span>"));
			y = m.select("#__mce")[0];
			x = m.getRoot();
			while (y) {
				if (y === x) {
					m.setOuterHTML(z, new c.html.Serializer({}, o.schema).serialize(new c.html.DomParser({}, o.schema).parse(m.getOuterHTML(z))));
					break;
				}
				z = y;
				y = y.parentNode;
			}
			y = m.select("#__mce")[0];
			if (y) {
				if (y.parentNode.childNodes.length == 1) {
					z = y.parentNode;
					q.select(z.previousSibling, true);
					q.collapse(false);
					m.remove(z);
				} else {
					v = m.createRng();
					v.setStartBefore(y);
					v.setEndAfter(y);
					q.setRng(v);
					if (c.isIE) {
						o.getDoc().execCommand("Delete", false, null);
						m.remove(y);
					} else {
						m.remove(y);
						q.setRng(v);
					}
				}
			}
		}, mceInsertRawHTML:function (y, x, v) {
			q.setContent("tiny_mce_marker");
			o.setContent(o.getContent().replace(/tiny_mce_marker/g, function () {
				return v;
			}));
		}, mceSetContent:function (y, x, v) {
			o.setContent(v);
		}, "Indent,Outdent":function (z) {
			var x, v, y;
			x = l.indentation;
			v = /[a-z%]+$/i.exec(x);
			x = parseInt(x);
			if (!n("InsertUnorderedList") && !n("InsertOrderedList")) {
				d(q.getSelectedBlocks(), function (A) {
					if (z == "outdent") {
						y = Math.max(0, parseInt(A.style.paddingLeft || 0) - x);
						m.setStyle(A, "paddingLeft", y ? y + v : "");
					} else {
						m.setStyle(A, "paddingLeft", (parseInt(A.style.paddingLeft || 0) + x) + v);
					}
				});
			} else {
				f(z);
			}
		}, mceRepaint:function () {
			var x;
			if (c.isGecko) {
				try {
					j(a);
					if (q.getSel()) {
						q.getSel().selectAllChildren(o.getBody());
					}
					q.collapse(a);
					g();
				}
				catch (v) {
				}
			}
		}, mceToggleFormat:function (y, x, v) {
			o.formatter.toggle(v);
		}, InsertHorizontalRule:function () {
			q.setContent("<hr />");
		}, mceToggleVisualAid:function () {
			o.hasVisual = !o.hasVisual;
			o.addVisual();
		}, mceReplaceContent:function (y, x, v) {
			q.setContent(v.replace(/\{\$selection\}/g, q.getContent({format:"text"})));
		}, mceInsertLink:function (B, A, z) {
			var y = m.getParent(q.getNode(), "a"), x, v;
			if (c.is(z, "string")) {
				z = {href:z};
			}
			z.href = z.href.replace(" ", "%20");
			if (!y) {
				if (c.isWebKit) {
					x = m.getParent(q.getNode(), "img");
					if (x) {
						v = x.style.cssFloat;
						x.style.cssFloat = null;
					}
				}
				f("CreateLink", b, "javascript:mctmp(0);");
				if (v) {
					x.style.cssFloat = v;
				}
				d(m.select("a[href=javascript:mctmp(0);]"), function (C) {
					m.setAttribs(C, z);
				});
			} else {
				if (z.href) {
					m.setAttribs(y, z);
				} else {
					o.dom.remove(y, a);
				}
			}
		}, selectAll:function () {
			var x = m.getRoot(), v = m.createRng();
			v.setStart(x, 0);
			v.setEnd(x, x.childNodes.length);
			o.selection.setRng(v);
		}});
		u({"JustifyLeft,JustifyCenter,JustifyRight,JustifyFull":function (v) {
			return t("align" + v.substring(7));
		}, "Bold,Italic,Underline,Strikethrough":function (v) {
			return t(v);
		}, mceBlockQuote:function () {
			return t("blockquote");
		}, Outdent:function () {
			var v;
			if (l.inline_styles) {
				if ((v = m.getParent(q.getStart(), m.isBlock)) && parseInt(v.style.paddingLeft) > 0) {
					return a;
				}
				if ((v = m.getParent(q.getEnd(), m.isBlock)) && parseInt(v.style.paddingLeft) > 0) {
					return a;
				}
			}
			return n("InsertUnorderedList") || n("InsertOrderedList") || (!l.inline_styles && !!m.getParent(q.getNode(), "BLOCKQUOTE"));
		}, "InsertUnorderedList,InsertOrderedList":function (v) {
			return m.getParent(q.getNode(), v == "insertunorderedlist" ? "UL" : "OL");
		}}, "state");
		u({"FontSize,FontName":function (y) {
			var x = 0, v;
			if (v = m.getParent(q.getNode(), "span")) {
				if (y == "fontsize") {
					x = v.style.fontSize;
				} else {
					x = v.style.fontFamily.replace(/, /g, ",").replace(/[\'\"]/g, "").toLowerCase();
				}
			}
			return x;
		}}, "value");
		if (l.custom_undo_redo) {
			u({Undo:function () {
				o.undoManager.undo();
			}, Redo:function () {
				o.undoManager.redo();
			}});
		}
	};
})(tinymce);
(function (b) {
	var a = b.util.Dispatcher;
	b.UndoManager = function (e) {
		var c, d = 0, g = [];
		function f() {
			return b.trim(e.getContent({format:"raw", no_events:1}));
		}
		return c = {typing:false, onAdd:new a(c), onUndo:new a(c), onRedo:new a(c), add:function (l) {
			var h, j = e.settings, k;
			l = l || {};
			l.content = f();
			k = g[d];
			if (k && k.content == l.content) {
				if (d > 0 || g.length == 1) {
					return null;
				}
			}
			if (j.custom_undo_redo_levels) {
				if (g.length > j.custom_undo_redo_levels) {
					for (h = 0; h < g.length - 1; h++) {
						g[h] = g[h + 1];
					}
					g.length--;
					d = g.length;
				}
			}
			l.bookmark = e.selection.getBookmark(2, true);
			if (d < g.length - 1) {
				g.length = d + 1;
			}
			g.push(l);
			d = g.length - 1;
			c.onAdd.dispatch(c, l);
			e.isNotDirty = 0;
			return l;
		}, undo:function () {
			var j, h;
			if (c.typing) {
				c.add();
				c.typing = false;
			}
			if (d > 0) {
				j = g[--d];
				e.setContent(j.content, {format:"raw"});
				e.selection.moveToBookmark(j.bookmark);
				c.onUndo.dispatch(c, j);
			}
			return j;
		}, redo:function () {
			var h;
			if (d < g.length - 1) {
				h = g[++d];
				e.setContent(h.content, {format:"raw"});
				e.selection.moveToBookmark(h.bookmark);
				c.onRedo.dispatch(c, h);
			}
			return h;
		}, clear:function () {
			g = [];
			d = 0;
			c.typing = false;
		}, hasUndo:function () {
			return d > 0 || c.typing;
		}, hasRedo:function () {
			return d < g.length - 1;
		}};
	};
})(tinymce);
(function (n) {
	var l = n.dom.Event, c = n.isIE, a = n.isGecko, b = n.isOpera, k = n.each, j = n.extend, d = true, h = false;
	function m(q) {
		var r, p, o;
		do {
			if (/^(SPAN|STRONG|B|EM|I|FONT|STRIKE|U)$/.test(q.nodeName)) {
				if (r) {
					p = q.cloneNode(false);
					p.appendChild(r);
					r = p;
				} else {
					r = o = q.cloneNode(false);
				}
				r.removeAttribute("id");
			}
		} while (q = q.parentNode);
		if (r) {
			return {wrapper:r, inner:o};
		}
	}
	function g(p, q) {
		var o = q.ownerDocument.createRange();
		o.setStart(p.endContainer, p.endOffset);
		o.setEndAfter(q);
		return o.cloneContents().textContent.length == 0;
	}
	function f(o) {
		o = o.innerHTML;
		o = o.replace(/<(img|hr|table|input|select|textarea)[ \>]/gi, "-");
		o = o.replace(/<[^>]+>/g, "");
		return o.replace(/[ \u00a0\t\r\n]+/g, "") == "";
	}
	function e(q, s, o) {
		var p, r;
		if (f(o)) {
			p = s.getParent(o, "ul,ol");
			if (!s.getParent(p.parentNode, "ul,ol")) {
				s.split(p, o);
				r = s.create("p", 0, "<br data-mce-bogus=\"1\" />");
				s.replace(r, o);
				q.select(r, 1);
			}
			return h;
		}
		return d;
	}
	n.create("tinymce.ForceBlocks", {ForceBlocks:function (o) {
		var p = this, q = o.settings, r;
		p.editor = o;
		p.dom = o.dom;
		r = (q.forced_root_block || "p").toLowerCase();
		q.element = r.toUpperCase();
		o.onPreInit.add(p.setup, p);
		if (q.forced_root_block) {
			o.onInit.add(p.forceRoots, p);
			o.onSetContent.add(p.forceRoots, p);
			o.onBeforeGetContent.add(p.forceRoots, p);
		}
	}, setup:function () {
		var p = this, o = p.editor, r = o.settings, v = o.dom, q = o.selection;
		if (r.forced_root_block) {
			o.onBeforeExecCommand.add(p.forceRoots, p);
			o.onKeyUp.add(p.forceRoots, p);
			o.onPreProcess.add(p.forceRoots, p);
		}
		if (r.force_br_newlines) {
			if (c) {
				o.onKeyPress.add(function (s, t) {
					var x;
					if (t.keyCode == 13 && q.getNode().nodeName != "LI") {
						q.setContent("<br id=\"__\" /> ", {format:"raw"});
						x = v.get("__");
						x.removeAttribute("id");
						q.select(x);
						q.collapse();
						return l.cancel(t);
					}
				});
			}
		}
		if (r.force_p_newlines) {
			if (!c) {
				o.onKeyPress.add(function (s, t) {
					if (t.keyCode == 13 && !t.shiftKey && !p.insertPara(t)) {
						l.cancel(t);
					}
				});
			} else {
				n.addUnload(function () {
					p._previousFormats = 0;
				});
				o.onKeyPress.add(function (s, t) {
					p._previousFormats = 0;
					if (t.keyCode == 13 && !t.shiftKey && s.selection.isCollapsed() && r.keep_styles) {
						p._previousFormats = m(s.selection.getStart());
					}
				});
				o.onKeyUp.add(function (t, y) {
					if (y.keyCode == 13 && !y.shiftKey) {
						var x = t.selection.getStart(), s = p._previousFormats;
						if (!x.hasChildNodes() && s) {
							x = v.getParent(x, v.isBlock);
							if (x && x.nodeName != "LI") {
								x.innerHTML = "";
								if (p._previousFormats) {
									x.appendChild(s.wrapper);
									s.inner.innerHTML = "\ufeff";
								} else {
									x.innerHTML = "\ufeff";
								}
								q.select(x, 1);
								t.getDoc().execCommand("Delete", false, null);
								p._previousFormats = 0;
							}
						}
					}
				});
			}
			if (a) {
				o.onKeyDown.add(function (s, t) {
					if ((t.keyCode == 8 || t.keyCode == 46) && !t.shiftKey) {
						p.backspaceDelete(t, t.keyCode == 8);
					}
				});
			}
		}
		if (n.isWebKit) {
			function u(t) {
				var s = q.getRng(), x, A = v.create("div", null, " "), z, y = v.getViewPort(t.getWin()).h;
				s.insertNode(x = v.create("br"));
				s.setStartAfter(x);
				s.setEndAfter(x);
				q.setRng(s);
				if (q.getSel().focusNode == x.previousSibling) {
					q.select(v.insertAfter(v.doc.createTextNode("\xa0"), x));
					q.collapse(d);
				}
				v.insertAfter(A, x);
				z = v.getPos(A).y;
				v.remove(A);
				if (z > y) {
					t.getWin().scrollTo(0, z);
				}
			}
			o.onKeyPress.add(function (s, t) {
				if (t.keyCode == 13 && (t.shiftKey || (r.force_br_newlines && !v.getParent(q.getNode(), "h1,h2,h3,h4,h5,h6,ol,ul")))) {
					u(s);
					l.cancel(t);
				}
			});
		}
		if (c) {
			if (r.element != "P") {
				o.onKeyPress.add(function (s, t) {
					p.lastElm = q.getNode().nodeName;
				});
				o.onKeyUp.add(function (t, x) {
					var z, y = q.getNode(), s = t.getBody();
					if (s.childNodes.length === 1 && y.nodeName == "P") {
						y = v.rename(y, r.element);
						q.select(y);
						q.collapse();
						t.nodeChanged();
					} else {
						if (x.keyCode == 13 && !x.shiftKey && p.lastElm != "P") {
							z = v.getParent(y, "p");
							if (z) {
								v.rename(z, r.element);
								t.nodeChanged();
							}
						}
					}
				});
			}
		}
	}, find:function (v, q, r) {
		var p = this.editor, o = p.getDoc().createTreeWalker(v, 4, null, h), u = -1;
		while (v = o.nextNode()) {
			u++;
			if (q == 0 && v == r) {
				return u;
			}
			if (q == 1 && u == r) {
				return v;
			}
		}
		return -1;
	}, forceRoots:function (x, I) {
		var z = this, x = z.editor, M = x.getBody(), J = x.getDoc(), P = x.selection, A = P.getSel(), B = P.getRng(), N = -2, v, G, o, p, K = -16777215;
		var L, q, O, F, C, u = M.childNodes, E, D, y;
		for (E = u.length - 1; E >= 0; E--) {
			L = u[E];
			if (L.nodeType === 1 && L.getAttribute("data-mce-type")) {
				q = null;
				continue;
			}
			if (L.nodeType === 3 || (!z.dom.isBlock(L) && L.nodeType !== 8 && !/^(script|mce:script|style|mce:style)$/i.test(L.nodeName))) {
				if (!q) {
					if (L.nodeType != 3 || /[^\s]/g.test(L.nodeValue)) {
						if (N == -2 && B) {
							if (!c || B.setStart) {
								if (B.startContainer.nodeType == 1 && (D = B.startContainer.childNodes[B.startOffset]) && D.nodeType == 1) {
									y = D.getAttribute("id");
									D.setAttribute("id", "__mce");
								} else {
									if (x.dom.getParent(B.startContainer, function (r) {
										return r === M;
									})) {
										G = B.startOffset;
										o = B.endOffset;
										N = z.find(M, 0, B.startContainer);
										v = z.find(M, 0, B.endContainer);
									}
								}
							} else {
								if (B.item) {
									p = J.body.createTextRange();
									p.moveToElementText(B.item(0));
									B = p;
								}
								p = J.body.createTextRange();
								p.moveToElementText(M);
								p.collapse(1);
								O = p.move("character", K) * -1;
								p = B.duplicate();
								p.collapse(1);
								F = p.move("character", K) * -1;
								p = B.duplicate();
								p.collapse(0);
								C = (p.move("character", K) * -1) - F;
								N = F - O;
								v = C;
							}
						}
						q = x.dom.create(x.settings.forced_root_block);
						L.parentNode.replaceChild(q, L);
						q.appendChild(L);
					}
				} else {
					if (q.hasChildNodes()) {
						q.insertBefore(L, q.firstChild);
					} else {
						q.appendChild(L);
					}
				}
			} else {
				q = null;
			}
		}
		if (N != -2) {
			if (!c || B.setStart) {
				q = M.getElementsByTagName(x.settings.element)[0];
				B = J.createRange();
				if (N != -1) {
					B.setStart(z.find(M, 1, N), G);
				} else {
					B.setStart(q, 0);
				}
				if (v != -1) {
					B.setEnd(z.find(M, 1, v), o);
				} else {
					B.setEnd(q, 0);
				}
				if (A) {
					A.removeAllRanges();
					A.addRange(B);
				}
			} else {
				try {
					B = A.createRange();
					B.moveToElementText(M);
					B.collapse(1);
					B.moveStart("character", N);
					B.moveEnd("character", v);
					B.select();
				}
				catch (H) {
				}
			}
		} else {
			if ((!c || B.setStart) && (D = x.dom.get("__mce"))) {
				if (y) {
					D.setAttribute("id", y);
				} else {
					D.removeAttribute("id");
				}
				B = J.createRange();
				B.setStartBefore(D);
				B.setEndBefore(D);
				P.setRng(B);
			}
		}
	}, getParentBlock:function (p) {
		var o = this.dom;
		return o.getParent(p, o.isBlock);
	}, insertPara:function (S) {
		var G = this, x = G.editor, O = x.dom, T = x.getDoc(), X = x.settings, H = x.selection.getSel(), I = H.getRangeAt(0), W = T.body;
		var L, M, J, Q, P, u, p, v, A, o, E, V, q, z, K, N = O.getViewPort(x.getWin()), D, F, C;
		L = T.createRange();
		L.setStart(H.anchorNode, H.anchorOffset);
		L.collapse(d);
		M = T.createRange();
		M.setStart(H.focusNode, H.focusOffset);
		M.collapse(d);
		J = L.compareBoundaryPoints(L.START_TO_END, M) < 0;
		Q = J ? H.anchorNode : H.focusNode;
		P = J ? H.anchorOffset : H.focusOffset;
		u = J ? H.focusNode : H.anchorNode;
		p = J ? H.focusOffset : H.anchorOffset;
		if (Q === u && /^(TD|TH)$/.test(Q.nodeName)) {
			if (Q.firstChild.nodeName == "BR") {
				O.remove(Q.firstChild);
			}
			if (Q.childNodes.length == 0) {
				x.dom.add(Q, X.element, null, "<br />");
				V = x.dom.add(Q, X.element, null, "<br />");
			} else {
				K = Q.innerHTML;
				Q.innerHTML = "";
				x.dom.add(Q, X.element, null, K);
				V = x.dom.add(Q, X.element, null, "<br />");
			}
			I = T.createRange();
			I.selectNodeContents(V);
			I.collapse(1);
			x.selection.setRng(I);
			return h;
		}
		if (Q == W && u == W && W.firstChild && x.dom.isBlock(W.firstChild)) {
			Q = u = Q.firstChild;
			P = p = 0;
			L = T.createRange();
			L.setStart(Q, 0);
			M = T.createRange();
			M.setStart(u, 0);
		}
		Q = Q.nodeName == "HTML" ? T.body : Q;
		Q = Q.nodeName == "BODY" ? Q.firstChild : Q;
		u = u.nodeName == "HTML" ? T.body : u;
		u = u.nodeName == "BODY" ? u.firstChild : u;
		v = G.getParentBlock(Q);
		A = G.getParentBlock(u);
		o = v ? v.nodeName : X.element;
		if (K = G.dom.getParent(v, "li,pre")) {
			if (K.nodeName == "LI") {
				return e(x.selection, G.dom, K);
			}
			return d;
		}
		if (v && (v.nodeName == "CAPTION" || /absolute|relative|fixed/gi.test(O.getStyle(v, "position", 1)))) {
			o = X.element;
			v = null;
		}
		if (A && (A.nodeName == "CAPTION" || /absolute|relative|fixed/gi.test(O.getStyle(v, "position", 1)))) {
			o = X.element;
			A = null;
		}
		if (/(TD|TABLE|TH|CAPTION)/.test(o) || (v && o == "DIV" && /left|right/gi.test(O.getStyle(v, "float", 1)))) {
			o = X.element;
			v = A = null;
		}
		E = (v && v.nodeName == o) ? v.cloneNode(0) : x.dom.create(o);
		V = (A && A.nodeName == o) ? A.cloneNode(0) : x.dom.create(o);
		V.removeAttribute("id");
		if (/^(H[1-6])$/.test(o) && g(I, v)) {
			V = x.dom.create(X.element);
		}
		K = q = Q;
		do {
			if (K == W || K.nodeType == 9 || G.dom.isBlock(K) || /(TD|TABLE|TH|CAPTION)/.test(K.nodeName)) {
				break;
			}
			q = K;
		} while ((K = K.previousSibling ? K.previousSibling : K.parentNode));
		K = z = u;
		do {
			if (K == W || K.nodeType == 9 || G.dom.isBlock(K) || /(TD|TABLE|TH|CAPTION)/.test(K.nodeName)) {
				break;
			}
			z = K;
		} while ((K = K.nextSibling ? K.nextSibling : K.parentNode));
		if (q.nodeName == o) {
			L.setStart(q, 0);
		} else {
			L.setStartBefore(q);
		}
		L.setEnd(Q, P);
		E.appendChild(L.cloneContents() || T.createTextNode(""));
		try {
			M.setEndAfter(z);
		}
		catch (R) {
		}
		M.setStart(u, p);
		V.appendChild(M.cloneContents() || T.createTextNode(""));
		I = T.createRange();
		if (!q.previousSibling && q.parentNode.nodeName == o) {
			I.setStartBefore(q.parentNode);
		} else {
			if (L.startContainer.nodeName == o && L.startOffset == 0) {
				I.setStartBefore(L.startContainer);
			} else {
				I.setStart(L.startContainer, L.startOffset);
			}
		}
		if (!z.nextSibling && z.parentNode.nodeName == o) {
			I.setEndAfter(z.parentNode);
		} else {
			I.setEnd(M.endContainer, M.endOffset);
		}
		I.deleteContents();
		if (b) {
			x.getWin().scrollTo(0, N.y);
		}
		if (E.firstChild && E.firstChild.nodeName == o) {
			E.innerHTML = E.firstChild.innerHTML;
		}
		if (V.firstChild && V.firstChild.nodeName == o) {
			V.innerHTML = V.firstChild.innerHTML;
		}
		if (f(E)) {
			E.innerHTML = "<br />";
		}
		function U(y, s) {
			var r = [], Z, Y, t;
			y.innerHTML = "";
			if (X.keep_styles) {
				Y = s;
				do {
					if (/^(SPAN|STRONG|B|EM|I|FONT|STRIKE|U)$/.test(Y.nodeName)) {
						Z = Y.cloneNode(h);
						O.setAttrib(Z, "id", "");
						r.push(Z);
					}
				} while (Y = Y.parentNode);
			}
			if (r.length > 0) {
				for (t = r.length - 1, Z = y; t >= 0; t--) {
					Z = Z.appendChild(r[t]);
				}
				r[0].innerHTML = b ? "&nbsp;" : "<br />";
				return r[0];
			} else {
				y.innerHTML = b ? "&nbsp;" : "<br />";
			}
		}
		if (f(V)) {
			C = U(V, u);
		}
		if (b && parseFloat(opera.version()) < 9.5) {
			I.insertNode(E);
			I.insertNode(V);
		} else {
			I.insertNode(V);
			I.insertNode(E);
		}
		V.normalize();
		E.normalize();
		function B(r) {
			return T.createTreeWalker(r, NodeFilter.SHOW_TEXT, null, h).nextNode() || r;
		}
		I = T.createRange();
		I.selectNodeContents(a ? B(C || V) : C || V);
		I.collapse(1);
		H.removeAllRanges();
		H.addRange(I);
		D = x.dom.getPos(V).y;
		F = V.clientHeight;
		if (D < N.y || D + F > N.y + N.h) {
			x.getWin().scrollTo(0, D < N.y ? D : D - N.h + 25);
		}
		return h;
	}, backspaceDelete:function (v, C) {
		var D = this, u = D.editor, z = u.getBody(), s = u.dom, q, x = u.selection, p = x.getRng(), y = p.startContainer, q, A, B, o;
		if (!C && p.collapsed && y.nodeType == 1 && p.startOffset == y.childNodes.length) {
			o = new n.dom.TreeWalker(y.lastChild, y);
			for (q = y.lastChild; q; q = o.prev()) {
				if (q.nodeType == 3) {
					p.setStart(q, q.nodeValue.length);
					p.collapse(true);
					x.setRng(p);
					return;
				}
			}
		}
		if (y && u.dom.isBlock(y) && !/^(TD|TH)$/.test(y.nodeName) && C) {
			if (y.childNodes.length == 0 || (y.childNodes.length == 1 && y.firstChild.nodeName == "BR")) {
				q = y;
				while ((q = q.previousSibling) && !u.dom.isBlock(q)) {
				}
				if (q) {
					if (y != z.firstChild) {
						A = u.dom.doc.createTreeWalker(q, NodeFilter.SHOW_TEXT, null, h);
						while (B = A.nextNode()) {
							q = B;
						}
						p = u.getDoc().createRange();
						p.setStart(q, q.nodeValue ? q.nodeValue.length : 0);
						p.setEnd(q, q.nodeValue ? q.nodeValue.length : 0);
						x.setRng(p);
						u.dom.remove(y);
					}
					return l.cancel(v);
				}
			}
		}
	}});
})(tinymce);
(function (c) {
	var b = c.DOM, a = c.dom.Event, d = c.each, e = c.extend;
	c.create("tinymce.ControlManager", {ControlManager:function (f, j) {
		var h = this, g;
		j = j || {};
		h.editor = f;
		h.controls = {};
		h.onAdd = new c.util.Dispatcher(h);
		h.onPostRender = new c.util.Dispatcher(h);
		h.prefix = j.prefix || f.id + "_";
		h._cls = {};
		h.onPostRender.add(function () {
			d(h.controls, function (k) {
				k.postRender();
			});
		});
	}, get:function (f) {
		return this.controls[this.prefix + f] || this.controls[f];
	}, setActive:function (h, f) {
		var g = null;
		if (g = this.get(h)) {
			g.setActive(f);
		}
		return g;
	}, setDisabled:function (h, f) {
		var g = null;
		if (g = this.get(h)) {
			g.setDisabled(f);
		}
		return g;
	}, add:function (g) {
		var f = this;
		if (g) {
			f.controls[g.id] = g;
			f.onAdd.dispatch(g, f);
		}
		return g;
	}, createControl:function (j) {
		var h, g = this, f = g.editor;
		d(f.plugins, function (k) {
			if (k.createControl) {
				h = k.createControl(j, g);
				if (h) {
					return false;
				}
			}
		});
		switch (j) {
		  case "|":
		  case "separator":
			return g.createSeparator();
		}
		if (!h && f.buttons && (h = f.buttons[j])) {
			return g.createButton(j, h);
		}
		return g.add(h);
	}, createDropMenu:function (f, o, h) {
		var n = this, j = n.editor, k, g, l, m;
		o = e({"class":"mceDropDown", constrain:j.settings.constrain_menus}, o);
		o["class"] = o["class"] + " " + j.getParam("skin") + "Skin";
		if (l = j.getParam("skin_variant")) {
			o["class"] += " " + j.getParam("skin") + "Skin" + l.substring(0, 1).toUpperCase() + l.substring(1);
		}
		f = n.prefix + f;
		m = h || n._cls.dropmenu || c.ui.DropMenu;
		k = n.controls[f] = new m(f, o);
		k.onAddItem.add(function (r, q) {
			var p = q.settings;
			p.title = j.getLang(p.title, p.title);
			if (!p.onclick) {
				p.onclick = function (s) {
					if (p.cmd) {
						j.execCommand(p.cmd, p.ui || false, p.value);
					}
				};
			}
		});
		j.onRemove.add(function () {
			k.destroy();
		});
		if (c.isIE) {
			k.onShowMenu.add(function () {
				j.focus();
				g = j.selection.getBookmark(1);
			});
			k.onHideMenu.add(function () {
				if (g) {
					j.selection.moveToBookmark(g);
					g = 0;
				}
			});
		}
		return n.add(k);
	}, createListBox:function (n, j, m) {
		var h = this, g = h.editor, k, l, f;
		if (h.get(n)) {
			return null;
		}
		j.title = g.translate(j.title);
		j.scope = j.scope || g;
		if (!j.onselect) {
			j.onselect = function (o) {
				g.execCommand(j.cmd, j.ui || false, o || j.value);
			};
		}
		j = e({title:j.title, "class":"mce_" + n, scope:j.scope, control_manager:h}, j);
		n = h.prefix + n;
		if (g.settings.use_native_selects) {
			l = new c.ui.NativeListBox(n, j);
		} else {
			f = m || h._cls.listbox || c.ui.ListBox;
			l = new f(n, j);
		}
		h.controls[n] = l;
		if (c.isWebKit) {
			l.onPostRender.add(function (p, o) {
				a.add(o, "mousedown", function () {
					g.bookmark = g.selection.getBookmark(1);
				});
				a.add(o, "focus", function () {
					g.selection.moveToBookmark(g.bookmark);
					g.bookmark = null;
				});
			});
		}
		if (l.hideMenu) {
			g.onMouseDown.add(l.hideMenu, l);
		}
		return h.add(l);
	}, createButton:function (n, j, m) {
		var h = this, g = h.editor, k, l, f;
		if (h.get(n)) {
			return null;
		}
		j.title = g.translate(j.title);
		j.label = g.translate(j.label);
		j.scope = j.scope || g;
		if (!j.onclick && !j.menu_button) {
			j.onclick = function () {
				g.execCommand(j.cmd, j.ui || false, j.value);
			};
		}
		j = e({title:j.title, "class":"mce_" + n, unavailable_prefix:g.getLang("unavailable", ""), scope:j.scope, control_manager:h}, j);
		n = h.prefix + n;
		if (j.menu_button) {
			f = m || h._cls.menubutton || c.ui.MenuButton;
			l = new f(n, j);
			g.onMouseDown.add(l.hideMenu, l);
		} else {
			f = h._cls.button || c.ui.Button;
			l = new f(n, j);
		}
		return h.add(l);
	}, createMenuButton:function (h, f, g) {
		f = f || {};
		f.menu_button = 1;
		return this.createButton(h, f, g);
	}, createSplitButton:function (n, j, m) {
		var h = this, g = h.editor, k, l, f;
		if (h.get(n)) {
			return null;
		}
		j.title = g.translate(j.title);
		j.scope = j.scope || g;
		if (!j.onclick) {
			j.onclick = function (o) {
				g.execCommand(j.cmd, j.ui || false, o || j.value);
			};
		}
		if (!j.onselect) {
			j.onselect = function (o) {
				g.execCommand(j.cmd, j.ui || false, o || j.value);
			};
		}
		j = e({title:j.title, "class":"mce_" + n, scope:j.scope, control_manager:h}, j);
		n = h.prefix + n;
		f = m || h._cls.splitbutton || c.ui.SplitButton;
		l = h.add(new f(n, j));
		g.onMouseDown.add(l.hideMenu, l);
		return l;
	}, createColorSplitButton:function (f, o, h) {
		var m = this, k = m.editor, j, l, n, g;
		if (m.get(f)) {
			return null;
		}
		o.title = k.translate(o.title);
		o.scope = o.scope || k;
		if (!o.onclick) {
			o.onclick = function (p) {
				if (c.isIE) {
					g = k.selection.getBookmark(1);
				}
				k.execCommand(o.cmd, o.ui || false, p || o.value);
			};
		}
		if (!o.onselect) {
			o.onselect = function (p) {
				k.execCommand(o.cmd, o.ui || false, p || o.value);
			};
		}
		o = e({title:o.title, "class":"mce_" + f, menu_class:k.getParam("skin") + "Skin", scope:o.scope, more_colors_title:k.getLang("more_colors")}, o);
		f = m.prefix + f;
		n = h || m._cls.colorsplitbutton || c.ui.ColorSplitButton;
		l = new n(f, o);
		k.onMouseDown.add(l.hideMenu, l);
		k.onRemove.add(function () {
			l.destroy();
		});
		if (c.isIE) {
			l.onShowMenu.add(function () {
				k.focus();
				g = k.selection.getBookmark(1);
			});
			l.onHideMenu.add(function () {
				if (g) {
					k.selection.moveToBookmark(g);
					g = 0;
				}
			});
		}
		return m.add(l);
	}, createToolbar:function (l, h, k) {
		var j, g = this, f;
		l = g.prefix + l;
		f = k || g._cls.toolbar || c.ui.Toolbar;
		j = new f(l, h);
		if (g.get(l)) {
			return null;
		}
		return g.add(j);
	}, createSeparator:function (g) {
		var f = g || this._cls.separator || c.ui.Separator;
		return new f();
	}, setControlType:function (g, f) {
		return this._cls[g.toLowerCase()] = f;
	}, destroy:function () {
		d(this.controls, function (f) {
			f.destroy();
		});
		this.controls = null;
	}});
})(tinymce);
(function (d) {
	var a = d.util.Dispatcher, e = d.each, c = d.isIE, b = d.isOpera;
	d.create("tinymce.WindowManager", {WindowManager:function (f) {
		var g = this;
		g.editor = f;
		g.onOpen = new a(g);
		g.onClose = new a(g);
		g.params = {};
		g.features = {};
	}, open:function (A, h) {
		var z = this, l = "", o, n, j = z.editor.settings.dialog_type == "modal", r, q, k, g = d.DOM.getViewPort(), v;
		A = A || {};
		h = h || {};
		q = b ? g.w : screen.width;
		k = b ? g.h : screen.height;
		A.name = A.name || "mc_" + new Date().getTime();
		A.width = parseInt(A.width || 320);
		A.height = parseInt(A.height || 240);
		A.resizable = true;
		A.left = A.left || parseInt(q / 2) - (A.width / 2);
		A.top = A.top || parseInt(k / 2) - (A.height / 2);
		h.inline = false;
		h.mce_width = A.width;
		h.mce_height = A.height;
		h.mce_auto_focus = A.auto_focus;
		if (j) {
			if (c) {
				A.center = true;
				A.help = false;
				A.dialogWidth = A.width + "px";
				A.dialogHeight = A.height + "px";
				A.scroll = A.scrollbars || false;
			}
		}
		e(A, function (p, f) {
			if (d.is(p, "boolean")) {
				p = p ? "yes" : "no";
			}
			if (!/^(name|url)$/.test(f)) {
				if (c && j) {
					l += (l ? ";" : "") + f + ":" + p;
				} else {
					l += (l ? "," : "") + f + "=" + p;
				}
			}
		});
		z.features = A;
		z.params = h;
		z.onOpen.dispatch(z, A, h);
		v = A.url || A.file;
		v = d._addVer(v);
		try {
			if (c && j) {
				r = 1;
				window.showModalDialog(v, window, l);
			} else {
				r = window.open(v, A.name, l);
			}
		}
		catch (m) {
		}
		if (!r) {
			alert(z.editor.getLang("popup_blocked"));
		}
	}, close:function (f) {
		f.close();
		this.onClose.dispatch(this);
	}, createInstance:function (j, h, g, n, m, l) {
		var k = d.resolve(j);
		return new k(h, g, n, m, l);
	}, confirm:function (h, f, j, g) {
		g = g || window;
		f.call(j || this, g.confirm(this._decode(this.editor.getLang(h, h))));
	}, alert:function (h, f, k, g) {
		var j = this;
		g = g || window;
		g.alert(j._decode(j.editor.getLang(h, h)));
		if (f) {
			f.call(k || j);
		}
	}, resizeBy:function (f, g, h) {
		h.resizeBy(f, g);
	}, _decode:function (f) {
		return d.DOM.decode(f).replace(/\\n/g, "\n");
	}});
}(tinymce));
(function (a) {
	a.Formatter = function (U) {
		var L = {}, N = a.each, c = U.dom, q = U.selection, t = a.dom.TreeWalker, J = new a.dom.RangeUtils(c), d = U.schema.isValidChild, F = c.isBlock, l = U.settings.forced_root_block, s = c.nodeIndex, E = "\ufeff", e = /^(src|href|style)$/, R = false, B = true, p, O = {apply:[], remove:[]};
		function z(V) {
			return V instanceof Array;
		}
		function m(W, V) {
			return c.getParents(W, V, c.getRoot());
		}
		function b(V) {
			return V.nodeType === 1 && (V.face === "mceinline" || V.style.fontFamily === "mceinline");
		}
		function Q(V) {
			return V ? L[V] : L;
		}
		function k(V, W) {
			if (V) {
				if (typeof (V) !== "string") {
					N(V, function (Y, X) {
						k(X, Y);
					});
				} else {
					W = W.length ? W : [W];
					N(W, function (X) {
						if (X.deep === p) {
							X.deep = !X.selector;
						}
						if (X.split === p) {
							X.split = !X.selector || X.inline;
						}
						if (X.remove === p && X.selector && !X.inline) {
							X.remove = "none";
						}
						if (X.selector && X.inline) {
							X.mixed = true;
							X.block_expand = true;
						}
						if (typeof (X.classes) === "string") {
							X.classes = X.classes.split(/\s+/);
						}
					});
					L[V] = W;
				}
			}
		}
		function S(X, ad, Z) {
			var aa = Q(X), ae = aa[0], ac, W, ab;
			function Y(ah) {
				var ag = ah.startContainer, ak = ah.startOffset, aj, ai;
				if (ag.nodeType == 1 || ag.nodeValue === "") {
					ag = ag.nodeType == 1 ? ag.childNodes[ak] : ag;
					if (ag) {
						aj = new t(ag, ag.parentNode);
						for (ai = aj.current(); ai; ai = aj.next()) {
							if (ai.nodeType == 3 && !f(ai)) {
								ah.setStart(ai, 0);
								break;
							}
						}
					}
				}
				return ah;
			}
			function V(ah, ag) {
				ag = ag || ae;
				if (ah) {
					N(ag.styles, function (aj, ai) {
						c.setStyle(ah, ai, r(aj, ad));
					});
					N(ag.attributes, function (aj, ai) {
						c.setAttrib(ah, ai, r(aj, ad));
					});
					N(ag.classes, function (ai) {
						ai = r(ai, ad);
						if (!c.hasClass(ah, ai)) {
							c.addClass(ah, ai);
						}
					});
				}
			}
			function af(ah) {
				var ag = [], aj, ai;
				aj = ae.inline || ae.block;
				ai = c.create(aj);
				V(ai);
				J.walk(ah, function (ak) {
					var al;
					function am(an) {
						var aq = an.nodeName.toLowerCase(), ap = an.parentNode.nodeName.toLowerCase(), ao;
						if (g(aq, "br")) {
							al = 0;
							if (ae.block) {
								c.remove(an);
							}
							return;
						}
						if (ae.wrapper && x(an, X, ad)) {
							al = 0;
							return;
						}
						if (ae.block && !ae.wrapper && G(aq)) {
							an = c.rename(an, aj);
							V(an);
							ag.push(an);
							al = 0;
							return;
						}
						if (ae.selector) {
							N(aa, function (ar) {
								if (c.is(an, ar.selector) && !b(an)) {
									V(an, ar);
									ao = true;
								}
							});
							if (!ae.inline || ao) {
								al = 0;
								return;
							}
						}
						if ((ae.wrap_links !== false || aq != "a") && d(aj, aq) && d(ap, aj) && !(an.nodeType === 3 && an.nodeValue.length === 1 && an.nodeValue.charCodeAt(0) === 65279)) {
							if (!al) {
								al = ai.cloneNode(R);
								an.parentNode.insertBefore(al, an);
								ag.push(al);
							}
							al.appendChild(an);
						} else {
							al = 0;
							N(a.grep(an.childNodes), am);
							al = 0;
						}
					}
					N(ak, am);
				});
				N(ag, function (am) {
					var ak;
					function an(ap) {
						var ao = 0;
						N(ap.childNodes, function (aq) {
							if (!f(aq) && !H(aq)) {
								ao++;
							}
						});
						return ao;
					}
					function al(ao) {
						var aq, ap;
						N(ao.childNodes, function (ar) {
							if (ar.nodeType == 1 && !H(ar) && !b(ar)) {
								aq = ar;
								return R;
							}
						});
						if (aq && h(aq, ae)) {
							ap = aq.cloneNode(R);
							V(ap);
							c.replace(ap, ao, B);
							c.remove(aq, 1);
						}
						return ap || ao;
					}
					ak = an(am);
					if ((ag.length > 1 || !F(am)) && ak === 0) {
						c.remove(am, 1);
						return;
					}
					if (ae.inline || ae.wrapper) {
						if (!ae.exact && ak === 1) {
							am = al(am);
						}
						N(aa, function (ao) {
							N(c.select(ao.inline, am), function (ap) {
								T(ao, ad, ap, ao.exact ? ap : null);
							});
						});
						if (x(am.parentNode, X, ad)) {
							c.remove(am, 1);
							am = 0;
							return B;
						}
						if (ae.merge_with_parents) {
							c.getParent(am.parentNode, function (ao) {
								if (x(ao, X, ad)) {
									c.remove(am, 1);
									am = 0;
									return B;
								}
							});
						}
						if (am) {
							am = u(C(am), am);
							am = u(am, C(am, B));
						}
					}
				});
			}
			if (ae) {
				if (Z) {
					W = c.createRng();
					W.setStartBefore(Z);
					W.setEndAfter(Z);
					af(o(W, aa));
				} else {
					if (!q.isCollapsed() || !ae.inline) {
						ac = q.getBookmark();
						af(o(q.getRng(B), aa));
						q.moveToBookmark(ac);
						q.setRng(Y(q.getRng(B)));
						U.nodeChanged();
					} else {
						P("apply", X, ad);
					}
				}
			}
		}
		function A(X, ag, aa) {
			var ab = Q(X), ai = ab[0], af, ae, W;
			function Z(al) {
				var ak = al.startContainer, aq = al.startOffset, ap, ao, am, an;
				if (ak.nodeType == 3 && aq >= ak.nodeValue.length - 1) {
					ak = ak.parentNode;
					aq = s(ak) + 1;
				}
				if (ak.nodeType == 1) {
					am = ak.childNodes;
					ak = am[Math.min(aq, am.length - 1)];
					ap = new t(ak);
					if (aq > am.length - 1) {
						ap.next();
					}
					for (ao = ap.current(); ao; ao = ap.next()) {
						if (ao.nodeType == 3 && !f(ao)) {
							an = c.create("a", null, E);
							ao.parentNode.insertBefore(an, ao);
							al.setStart(ao, 0);
							q.setRng(al);
							c.remove(an);
							return;
						}
					}
				}
			}
			function Y(an) {
				var am, al, ak;
				am = a.grep(an.childNodes);
				for (al = 0, ak = ab.length; al < ak; al++) {
					if (T(ab[al], ag, an, an)) {
						break;
					}
				}
				if (ai.deep) {
					for (al = 0, ak = am.length; al < ak; al++) {
						Y(am[al]);
					}
				}
			}
			function ac(ak) {
				var al;
				N(m(ak.parentNode).reverse(), function (am) {
					var an;
					if (!al && am.id != "_start" && am.id != "_end") {
						an = x(am, X, ag);
						if (an && an.split !== false) {
							al = am;
						}
					}
				});
				return al;
			}
			function V(an, ak, ap, at) {
				var au, ar, aq, am, ao, al;
				if (an) {
					al = an.parentNode;
					for (au = ak.parentNode; au && au != al; au = au.parentNode) {
						ar = au.cloneNode(R);
						for (ao = 0; ao < ab.length; ao++) {
							if (T(ab[ao], ag, ar, ar)) {
								ar = 0;
								break;
							}
						}
						if (ar) {
							if (aq) {
								ar.appendChild(aq);
							}
							if (!am) {
								am = ar;
							}
							aq = ar;
						}
					}
					if (at && (!ai.mixed || !F(an))) {
						ak = c.split(an, ak);
					}
					if (aq) {
						ap.parentNode.insertBefore(aq, ap);
						am.appendChild(ap);
					}
				}
				return ak;
			}
			function ah(ak) {
				return V(ac(ak), ak, ak, true);
			}
			function ad(am) {
				var al = c.get(am ? "_start" : "_end"), ak = al[am ? "firstChild" : "lastChild"];
				if (H(ak)) {
					ak = ak[am ? "firstChild" : "lastChild"];
				}
				c.remove(al, true);
				return ak;
			}
			function aj(ak) {
				var al, am;
				ak = o(ak, ab, B);
				if (ai.split) {
					al = I(ak, B);
					am = I(ak);
					if (al != am) {
						al = M(al, "span", {id:"_start", "data-mce-type":"bookmark"});
						am = M(am, "span", {id:"_end", "data-mce-type":"bookmark"});
						ah(al);
						ah(am);
						al = ad(B);
						am = ad();
					} else {
						al = am = ah(al);
					}
					ak.startContainer = al.parentNode;
					ak.startOffset = s(al);
					ak.endContainer = am.parentNode;
					ak.endOffset = s(am) + 1;
				}
				J.walk(ak, function (an) {
					N(an, function (ao) {
						Y(ao);
					});
				});
			}
			if (aa) {
				W = c.createRng();
				W.setStartBefore(aa);
				W.setEndAfter(aa);
				aj(W);
				return;
			}
			if (!q.isCollapsed() || !ai.inline) {
				af = q.getBookmark();
				aj(q.getRng(B));
				q.moveToBookmark(af);
				if (j(X, ag, q.getStart())) {
					Z(q.getRng(true));
				}
				U.nodeChanged();
			} else {
				P("remove", X, ag);
			}
		}
		function D(V, X, W) {
			if (j(V, X, W)) {
				A(V, X, W);
			} else {
				S(V, X, W);
			}
		}
		function x(W, V, ab, Z) {
			var X = Q(V), ac, aa, Y;
			function ad(ah, aj, ak) {
				var ag, ai, ae = aj[ak], af;
				if (ae) {
					if (ae.length === p) {
						for (ag in ae) {
							if (ae.hasOwnProperty(ag)) {
								if (ak === "attributes") {
									ai = c.getAttrib(ah, ag);
								} else {
									ai = K(ah, ag);
								}
								if (Z && !ai && !aj.exact) {
									return;
								}
								if ((!Z || aj.exact) && !g(ai, r(ae[ag], ab))) {
									return;
								}
							}
						}
					} else {
						for (af = 0; af < ae.length; af++) {
							if (ak === "attributes" ? c.getAttrib(ah, ae[af]) : K(ah, ae[af])) {
								return aj;
							}
						}
					}
				}
				return aj;
			}
			if (X && W) {
				for (aa = 0; aa < X.length; aa++) {
					ac = X[aa];
					if (h(W, ac) && ad(W, ac, "attributes") && ad(W, ac, "styles")) {
						if (Y = ac.classes) {
							for (aa = 0; aa < Y.length; aa++) {
								if (!c.hasClass(W, Y[aa])) {
									return;
								}
							}
						}
						return ac;
					}
				}
			}
		}
		function j(X, aa, Z) {
			var W, Y;
			function V(ab) {
				ab = c.getParent(ab, function (ac) {
					return !!x(ac, X, aa, true);
				});
				return x(ab, X, aa);
			}
			if (Z) {
				return V(Z);
			}
			if (q.isCollapsed()) {
				for (Y = O.apply.length - 1; Y >= 0; Y--) {
					if (O.apply[Y].name == X) {
						return true;
					}
				}
				for (Y = O.remove.length - 1; Y >= 0; Y--) {
					if (O.remove[Y].name == X) {
						return false;
					}
				}
				return V(q.getNode());
			}
			Z = q.getNode();
			if (V(Z)) {
				return B;
			}
			W = q.getStart();
			if (W != Z) {
				if (V(W)) {
					return B;
				}
			}
			return R;
		}
		function v(ac, ab) {
			var Z, aa = [], Y = {}, X, W, V;
			if (q.isCollapsed()) {
				for (W = 0; W < ac.length; W++) {
					for (X = O.remove.length - 1; X >= 0; X--) {
						V = ac[W];
						if (O.remove[X].name == V) {
							Y[V] = true;
							break;
						}
					}
				}
				for (X = O.apply.length - 1; X >= 0; X--) {
					for (W = 0; W < ac.length; W++) {
						V = ac[W];
						if (!Y[V] && O.apply[X].name == V) {
							Y[V] = true;
							aa.push(V);
						}
					}
				}
			}
			Z = q.getStart();
			c.getParent(Z, function (af) {
				var ae, ad;
				for (ae = 0; ae < ac.length; ae++) {
					ad = ac[ae];
					if (!Y[ad] && x(af, ad, ab)) {
						Y[ad] = true;
						aa.push(ad);
					}
				}
			});
			return aa;
		}
		function y(Z) {
			var ab = Q(Z), Y, X, aa, W, V;
			if (ab) {
				Y = q.getStart();
				X = m(Y);
				for (W = ab.length - 1; W >= 0; W--) {
					V = ab[W].selector;
					if (!V) {
						return B;
					}
					for (aa = X.length - 1; aa >= 0; aa--) {
						if (c.is(X[aa], V)) {
							return B;
						}
					}
				}
			}
			return R;
		}
		a.extend(this, {get:Q, register:k, apply:S, remove:A, toggle:D, match:j, matchAll:v, matchNode:x, canApply:y});
		function h(V, W) {
			if (g(V, W.inline)) {
				return B;
			}
			if (g(V, W.block)) {
				return B;
			}
			if (W.selector) {
				return c.is(V, W.selector);
			}
		}
		function g(W, V) {
			W = W || "";
			V = V || "";
			W = "" + (W.nodeName || W);
			V = "" + (V.nodeName || V);
			return W.toLowerCase() == V.toLowerCase();
		}
		function K(W, V) {
			var X = c.getStyle(W, V);
			if (V == "color" || V == "backgroundColor") {
				X = c.toHex(X);
			}
			if (V == "fontWeight" && X == 700) {
				X = "bold";
			}
			return "" + X;
		}
		function r(V, W) {
			if (typeof (V) != "string") {
				V = V(W);
			} else {
				if (W) {
					V = V.replace(/%(\w+)/g, function (Y, X) {
						return W[X] || Y;
					});
				}
			}
			return V;
		}
		function f(V) {
			return V && V.nodeType === 3 && /^([\s\r\n]+|)$/.test(V.nodeValue);
		}
		function M(X, W, V) {
			var Y = c.create(W, V);
			X.parentNode.insertBefore(Y, X);
			Y.appendChild(X);
			return Y;
		}
		function o(V, ad, Y) {
			var X = V.startContainer, aa = V.startOffset, ag = V.endContainer, ab = V.endOffset, af, ac;
			function ae(aj, ak, ah, ai) {
				var al, am;
				ai = ai || c.getRoot();
				for (; ; ) {
					al = aj.parentNode;
					if (al == ai || (!ad[0].block_expand && F(al))) {
						return aj;
					}
					for (af = al[ak]; af && af != aj; af = af[ah]) {
						if (af.nodeType == 1 && !H(af)) {
							return aj;
						}
						if (af.nodeType == 3 && !f(af)) {
							return aj;
						}
					}
					aj = aj.parentNode;
				}
				return aj;
			}
			if (X.nodeType == 1 && X.hasChildNodes()) {
				ac = X.childNodes.length - 1;
				X = X.childNodes[aa > ac ? ac : aa];
				if (X.nodeType == 3) {
					aa = 0;
				}
			}
			if (ag.nodeType == 1 && ag.hasChildNodes()) {
				ac = ag.childNodes.length - 1;
				ag = ag.childNodes[ab > ac ? ac : ab - 1];
				if (ag.nodeType == 3) {
					ab = ag.nodeValue.length;
				}
			}
			if (H(X.parentNode)) {
				X = X.parentNode;
			}
			if (H(X)) {
				X = X.nextSibling || X;
			}
			if (H(ag.parentNode)) {
				ag = ag.parentNode;
			}
			if (H(ag)) {
				ag = ag.previousSibling || ag;
			}
			if (ad[0].inline || ad[0].block_expand) {
				X = ae(X, "firstChild", "nextSibling");
				ag = ae(ag, "lastChild", "previousSibling");
			}
			if (ad[0].selector && ad[0].expand !== R && !ad[0].inline) {
				function Z(ai, ah) {
					var aj, ak, al;
					if (ai.nodeType == 3 && ai.nodeValue.length == 0 && ai[ah]) {
						ai = ai[ah];
					}
					aj = m(ai);
					for (ak = 0; ak < aj.length; ak++) {
						for (al = 0; al < ad.length; al++) {
							if (c.is(aj[ak], ad[al].selector)) {
								return aj[ak];
							}
						}
					}
					return ai;
				}
				X = Z(X, "previousSibling");
				ag = Z(ag, "nextSibling");
			}
			if (ad[0].block || ad[0].selector) {
				function W(ai, ah, ak) {
					var aj;
					if (!ad[0].wrapper) {
						aj = c.getParent(ai, ad[0].block);
					}
					if (!aj) {
						aj = c.getParent(ai.nodeType == 3 ? ai.parentNode : ai, F);
					}
					if (aj && ad[0].wrapper) {
						aj = m(aj, "ul,ol").reverse()[0] || aj;
					}
					if (!aj) {
						aj = ai;
						while (aj[ah] && !F(aj[ah])) {
							aj = aj[ah];
							if (g(aj, "br")) {
								break;
							}
						}
					}
					return aj || ai;
				}
				X = W(X, "previousSibling");
				ag = W(ag, "nextSibling");
				if (ad[0].block) {
					if (!F(X)) {
						X = ae(X, "firstChild", "nextSibling");
					}
					if (!F(ag)) {
						ag = ae(ag, "lastChild", "previousSibling");
					}
				}
			}
			if (X.nodeType == 1) {
				aa = s(X);
				X = X.parentNode;
			}
			if (ag.nodeType == 1) {
				ab = s(ag) + 1;
				ag = ag.parentNode;
			}
			return {startContainer:X, startOffset:aa, endContainer:ag, endOffset:ab};
		}
		function T(ab, aa, Y, V) {
			var X, W, Z;
			if (!h(Y, ab)) {
				return R;
			}
			if (ab.remove != "all") {
				N(ab.styles, function (ad, ac) {
					ad = r(ad, aa);
					if (typeof (ac) === "number") {
						ac = ad;
						V = 0;
					}
					if (!V || g(K(V, ac), ad)) {
						c.setStyle(Y, ac, "");
					}
					Z = 1;
				});
				if (Z && c.getAttrib(Y, "style") == "") {
					Y.removeAttribute("style");
					Y.removeAttribute("data-mce-style");
				}
				N(ab.attributes, function (ae, ac) {
					var ad;
					ae = r(ae, aa);
					if (typeof (ac) === "number") {
						ac = ae;
						V = 0;
					}
					if (!V || g(c.getAttrib(V, ac), ae)) {
						if (ac == "class") {
							ae = c.getAttrib(Y, ac);
							if (ae) {
								ad = "";
								N(ae.split(/\s+/), function (af) {
									if (/mce\w+/.test(af)) {
										ad += (ad ? " " : "") + af;
									}
								});
								if (ad) {
									c.setAttrib(Y, ac, ad);
									return;
								}
							}
						}
						if (ac == "class") {
							Y.removeAttribute("className");
						}
						if (e.test(ac)) {
							Y.removeAttribute("data-mce-" + ac);
						}
						Y.removeAttribute(ac);
					}
				});
				N(ab.classes, function (ac) {
					ac = r(ac, aa);
					if (!V || c.hasClass(V, ac)) {
						c.removeClass(Y, ac);
					}
				});
				W = c.getAttribs(Y);
				for (X = 0; X < W.length; X++) {
					if (W[X].nodeName.indexOf("_") !== 0) {
						return R;
					}
				}
			}
			if (ab.remove != "none") {
				n(Y, ab);
				return B;
			}
		}
		function n(X, Y) {
			var V = X.parentNode, W;
			if (Y.block) {
				if (!l) {
					function Z(ab, aa, ac) {
						ab = C(ab, aa, ac);
						return !ab || (ab.nodeName == "BR" || F(ab));
					}
					if (F(X) && !F(V)) {
						if (!Z(X, R) && !Z(X.firstChild, B, 1)) {
							X.insertBefore(c.create("br"), X.firstChild);
						}
						if (!Z(X, B) && !Z(X.lastChild, R, 1)) {
							X.appendChild(c.create("br"));
						}
					}
				} else {
					if (V == c.getRoot()) {
						if (!Y.list_block || !g(X, Y.list_block)) {
							N(a.grep(X.childNodes), function (aa) {
								if (d(l, aa.nodeName.toLowerCase())) {
									if (!W) {
										W = M(aa, l);
									} else {
										W.appendChild(aa);
									}
								} else {
									W = 0;
								}
							});
						}
					}
				}
			}
			if (Y.selector && Y.inline && !g(Y.inline, X)) {
				return;
			}
			c.remove(X, 1);
		}
		function C(W, V, X) {
			if (W) {
				V = V ? "nextSibling" : "previousSibling";
				for (W = X ? W : W[V]; W; W = W[V]) {
					if (W.nodeType == 1 || !f(W)) {
						return W;
					}
				}
			}
		}
		function H(V) {
			return V && V.nodeType == 1 && V.getAttribute("data-mce-type") == "bookmark";
		}
		function u(Z, Y) {
			var V, X, W;
			function ab(ae, ad) {
				if (ae.nodeName != ad.nodeName) {
					return R;
				}
				function ac(ag) {
					var ah = {};
					N(c.getAttribs(ag), function (ai) {
						var aj = ai.nodeName.toLowerCase();
						if (aj.indexOf("_") !== 0 && aj !== "style") {
							ah[aj] = c.getAttrib(ag, aj);
						}
					});
					return ah;
				}
				function af(aj, ai) {
					var ah, ag;
					for (ag in aj) {
						if (aj.hasOwnProperty(ag)) {
							ah = ai[ag];
							if (ah === p) {
								return R;
							}
							if (aj[ag] != ah) {
								return R;
							}
							delete ai[ag];
						}
					}
					for (ag in ai) {
						if (ai.hasOwnProperty(ag)) {
							return R;
						}
					}
					return B;
				}
				if (!af(ac(ae), ac(ad))) {
					return R;
				}
				if (!af(c.parseStyle(c.getAttrib(ae, "style")), c.parseStyle(c.getAttrib(ad, "style")))) {
					return R;
				}
				return B;
			}
			if (Z && Y) {
				function aa(ad, ac) {
					for (X = ad; X; X = X[ac]) {
						if (X.nodeType == 3 && !f(X)) {
							return ad;
						}
						if (X.nodeType == 1 && !H(X)) {
							return X;
						}
					}
					return ad;
				}
				Z = aa(Z, "previousSibling");
				Y = aa(Y, "nextSibling");
				if (ab(Z, Y)) {
					for (X = Z.nextSibling; X && X != Y; ) {
						W = X;
						X = X.nextSibling;
						Z.appendChild(W);
					}
					c.remove(Y);
					N(a.grep(Y.childNodes), function (ac) {
						Z.appendChild(ac);
					});
					return Z;
				}
			}
			return Y;
		}
		function G(V) {
			return /^(h[1-6]|p|div|pre|address|dl|dt|dd)$/.test(V);
		}
		function I(W, Z) {
			var V, Y, X;
			V = W[Z ? "startContainer" : "endContainer"];
			Y = W[Z ? "startOffset" : "endOffset"];
			if (V.nodeType == 1) {
				X = V.childNodes.length - 1;
				if (!Z && Y) {
					Y--;
				}
				V = V.childNodes[Y > X ? X : Y];
			}
			return V;
		}
		function P(aa, W, Z) {
			var X, V = O[aa], ab = O[aa == "apply" ? "remove" : "apply"];
			function ac() {
				return O.apply.length || O.remove.length;
			}
			function Y() {
				O.apply = [];
				O.remove = [];
			}
			function ad(ae) {
				N(O.apply.reverse(), function (af) {
					S(af.name, af.vars, ae);
				});
				N(O.remove.reverse(), function (af) {
					A(af.name, af.vars, ae);
				});
				c.remove(ae, 1);
				Y();
			}
			for (X = V.length - 1; X >= 0; X--) {
				if (V[X].name == W) {
					return;
				}
			}
			V.push({name:W, vars:Z});
			for (X = ab.length - 1; X >= 0; X--) {
				if (ab[X].name == W) {
					ab.splice(X, 1);
				}
			}
			if (ac()) {
				U.getDoc().execCommand("FontName", false, "mceinline");
				O.lastRng = q.getRng();
				N(c.select("font,span"), function (af) {
					var ae;
					if (b(af)) {
						ae = q.getBookmark();
						ad(af);
						q.moveToBookmark(ae);
						U.nodeChanged();
					}
				});
				if (!O.isListening && ac()) {
					O.isListening = true;
					N("onKeyDown,onKeyUp,onKeyPress,onMouseUp".split(","), function (ae) {
						U[ae].addToTop(function (af, ag) {
							if (ac() && !a.dom.RangeUtils.compareRanges(O.lastRng, q.getRng())) {
								N(c.select("font,span"), function (ai) {
									var aj, ah;
									if (b(ai)) {
										aj = ai.firstChild;
										if (aj) {
											ad(ai);
											ah = c.createRng();
											ah.setStart(aj, aj.nodeValue.length);
											ah.setEnd(aj, aj.nodeValue.length);
											q.setRng(ah);
											af.nodeChanged();
										} else {
											c.remove(ai);
										}
									}
								});
								if (ag.type == "keyup" || ag.type == "mouseup") {
									Y();
								}
							}
						});
					});
				}
			}
		}
	};
})(tinymce);
tinymce.onAddEditor.add(function (e, a) {
	var d, h, g, c = a.settings;
	if (c.inline_styles) {
		h = e.explode(c.font_size_style_values);
		function b(k, j) {
			e.each(j, function (m, l) {
				if (m) {
					g.setStyle(k, l, m);
				}
			});
			g.rename(k, "span");
		}
		d = {font:function (k, j) {
			b(j, {backgroundColor:j.style.backgroundColor, color:j.color, fontFamily:j.face, fontSize:h[parseInt(j.size) - 1]});
		}, u:function (k, j) {
			b(j, {textDecoration:"underline"});
		}, strike:function (k, j) {
			b(j, {textDecoration:"line-through"});
		}};
		function f(j, k) {
			g = j.dom;
			if (c.convert_fonts_to_spans) {
				e.each(g.select("font,u,strike", k.node), function (l) {
					d[l.nodeName.toLowerCase()](a.dom, l);
				});
			}
		}
		a.onPreProcess.add(f);
		a.onInit.add(function () {
			a.selection.onSetContent.add(f);
		});
	}
});

