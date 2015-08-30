$(document).ready(function(){
	$("#siteTitle").html(json.website_name);
	$("footer.site-title").html("&copy; "+json.website_name);
	var passedItemId = GLOB_SITE.getUrlParameter("item_id");
	for (var i = 0; i < json.category.length; i++){
		for (var j = 0; j < json.category[i].items.length; j++){
			var itemName = json.category[i].items[j].item_name;
			var itemId = json.category[i].items[j].sku;
			var itemPrice = json.category[i].items[j].price;
			var productDesc = json.category[i].items[j].description;
			if (passedItemId === itemId) {
				$("#productTitle").html(itemName.toUpperCase());
				$("#productPrice").html(itemPrice);
				$("#productDesc").html(productDesc);
			}
		}
	}
});