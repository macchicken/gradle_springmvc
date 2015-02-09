jQuery.validator.addMethod("isNinedigitNum", function(value, element) {   
			var phoneexpr = /\d{3}-\d{3}-\d{4}/;
			return this.optional(element) || (phoneexpr.test(value));
		}, "plz enter valid phone number");
jQuery.validator.addMethod("onlyword", function(value, element) {   
			var wordexpr = /^[A-Za-z0-9 @.]+$/;
			var result=false;
			try{result=this.optional(element) || (wordexpr.test(value));}catch(e){result=false;}
			return result;
		}, "plz enter valid word");