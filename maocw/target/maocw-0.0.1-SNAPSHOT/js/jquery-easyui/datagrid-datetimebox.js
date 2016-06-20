$.extend($.fn.datagrid.defaults.editors, {
	datetimebox : {
		init : function(_275, _276) {
			var _277 = $('<input type="text">').appendTo(_275);
			_277.datetimebox(_276);
			return _277;
		},
		destroy : function(_278) {
			$(_278).datetimebox("destroy");
		},
		getValue : function(_279) {
			return $(_279).datetimebox("getValue");
		},
		setValue : function(_27a, _27b) {
			$(_27a).datetimebox("setValue", _27b);
		},
		resize : function(_27c, _27d) {
			$(_27c).datetimebox("resize", _27d);
		}
	}
});
