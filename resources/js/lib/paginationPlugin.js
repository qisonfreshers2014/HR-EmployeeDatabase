(function($) {

	var methods = {
		init : function(options) {
			var o = $.extend({
				items : 1,
				itemsOnPage : 1,
				pages : 0,
				displayedPages : 5,
				edges : 2,
				currentPage : 1,
				hrefText : '#',/*'#page-'*/
				prevText : '&laquo; Prev',
				nextText : '&raquo; Next',
				ellipseText : '&hellip;',
				cssStyle : 'light-theme',
				selectOnClick : true,
				paginatorLoc : '',
				contentStatusText : 'Displaying some of all articles',
				newOptionForPageSize : 0,
				limitIndex : 0,
				onContentLimitChange : function(howmay) {

				},
				onPageClick : function(pageNumber) {
					// Callback triggered when a page is clicked
					// Page number is given as an optional parameter
				},
				onInit : function() {
					// Callback triggered immediately after initialization
				}
			}, options || {});

			var self = this;

			o.pages = o.pages ? o.pages
					: Math.ceil(o.items / o.itemsOnPage) ? Math.ceil(o.items
							/ o.itemsOnPage) : 1;
			o.currentPage = o.currentPage - 1;
			o.halfDisplayed = o.displayedPages / 2;

			this.each(function() {
				self.addClass(o.cssStyle).data('paginate', o);
				methods._draw.call(self);
			});

			o.onInit();

			return this;
		},

		selectPage : function(page) {
			methods._selectPage.call(this, page - 1);
			return this;
		},

		prevPage : function() {
			var o = this.data('paginate');
			if (o.currentPage > 0) {
				methods._selectPage.call(this, o.currentPage - 1);
			}
			return this;
		},

		nextPage : function() {
			var o = this.data('paginate');
			if (o.currentPage < o.pages - 1) {
				methods._selectPage.call(this, o.currentPage + 1);
			}
			return this;
		},

		_draw : function() {
			var $panel = this.empty(), o = $panel.data('paginate'), interval = methods
					._getInterval(o), i;

			// Generate Prev link
			if (o.prevText) {
				methods._appendItem.call(this, o.currentPage - 1, {
					text : o.prevText,
					classes : 'prev'
				});
			}

			// Generate start edges
			if (interval.start > 0 && o.edges > 0) {
				var end = Math.min(o.edges, interval.start);
				for (i = 0; i < end; i++) {
					methods._appendItem.call(this, i);
				}
				if (o.edges < interval.start && o.ellipseText) {
					$panel.append('<span class="ellipse">' + o.ellipseText
							+ '</span>');
				}
			}

			// Generate interval links
			for (i = interval.start; i < interval.end; i++) {
				methods._appendItem.call(this, i);
			}

			// Generate end edges
			if (interval.end < o.pages && o.edges > 0) {
				if (o.pages - o.edges > interval.end && o.ellipseText) {
					$panel.append('<span class="ellipse">' + o.ellipseText
							+ '</span>');
				}
				var begin = Math.max(o.pages - o.edges, interval.end);
				for (i = begin; i < o.pages; i++) {
					methods._appendItem.call(this, i);
				}
			}

			// Generate Next link
			if (o.nextText) {
				methods._appendItem.call(this, o.currentPage + 1, {
					text : o.nextText,
					classes : 'next'
				});
			}
			if (o.contentStatusText) {
				
				$panel
						.append("<span class='contentStatus'> <select class='limit' id='limit"+o.paginatorLoc+"' title='Decide how many items to load'><option>5</option><option>10</option><option>15</option><option>20</option><option>25</option></select> </span>");
				var option = o.newOptionForPageSize;
				if(option){
					$(".limit").prepend("<option>"+option+"</option>");
				}
				var newLimit = o.limitIndex;
				if(newLimit){
					/*$(".limit").addClass(newLimit);*/
					$(".limit").attr('class',"limit"+newLimit);
					$(".limit"+newLimit).val(o.itemsOnPage);
					$(".limit"+newLimit).on('change', function() {
						o.itemsOnPage=$(this).val();
						o.onContentLimitChange($(this).val());
						$(".limit"+newLimit).val(o.itemsOnPage);
					});
					$(".limit"+newLimit).css('border-radius','3px');
					$(".limit"+newLimit).css('padding','inherit');
					$(".limit"+newLimit).css('height','24px');
				}
				else{
					$(".limit").val(o.itemsOnPage);
					$(".limit").on('change', function() {
						o.itemsOnPage=$(this).val();
						o.onContentLimitChange($(this).val());
						$(".limit").val(o.itemsOnPage);
					});
				}
				
			}
		},

		_perPage : function(limit) {
			var o = this.data('paginate');
			o.onContentLimitChange(limit);
		},

		_getInterval : function(o) {
			return {
				start : Math.ceil(o.currentPage > o.halfDisplayed ? Math.max(
						Math.min(o.currentPage - o.halfDisplayed,
								(o.pages - o.displayedPages)), 0) : 0),
				end : Math.ceil(o.currentPage > o.halfDisplayed ? Math.min(
						o.currentPage + o.halfDisplayed, o.pages) : Math.min(
						o.displayedPages, o.pages))
			};
		},

		_appendItem : function(pageIndex, opts) {
			var self = this, options, $link, o = self.data('paginate');

			pageIndex = pageIndex < 0 ? 0 : (pageIndex < o.pages ? pageIndex
					: o.pages - 1);

			options = $.extend({
				text : pageIndex + 1,
				classes : ''
			}, opts || {});

			if (pageIndex == o.currentPage) {
				$link = $('<span class="current">' + (options.text) + '</span>');
			} else {
				$link = $('<a href="' + o.hrefText /*+ (pageIndex + 1)*/
						+ '" class="page-link">' + (options.text) + '</a>');
				$link.click(function() {
					methods._selectPage.call(self, pageIndex);
				});
			}

			if (options.classes) {
				$link.addClass(options.classes);
			}

			self.append($link);
		},

		_selectPage : function(pageIndex) {
			var o = this.data('paginate');
			o.currentPage = pageIndex;
			if (o.selectOnClick) {
				o.onPageClick(pageIndex + 1);

				methods._draw.call(this);
			} else {
				o.onPageClick(pageIndex + 1);
			}
		}

	};

	$.fn.paginate = function(method) {

		// Method calling logic
		if (methods[method] && method.charAt(0) != '_') {
			return methods[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.paginate');
		}

	};

})(jQuery);
