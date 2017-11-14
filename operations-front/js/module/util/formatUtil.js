define(['util/utils', 'formatCurrency'], function(utils) {
  var Export = {};

  /**
   * 替换productImage这个url为80X80的url，如果该url不存在，则返回80X80的图片不存在的图片（拗口吗？就是给一个默认图）
   * @param url
   * @param size
   * @returns {String}
   */
  Export.prdImgUrl = function(url, size, defautImg) {
    if (url) {
      if (!utils.endWith(url, "/")) {
        url = url + "/";
      }
      if (!size) {
        size = "";
      } else if (utils.startWith(size, "/")) {
        size = size.substring(1, size.length);
      }
      
      return (utils.getPlatformImgUri(url) + url + size);
    } else {
      if (defautImg) {
        return "../theme/default/image/default/" + defautImg;
      }
    }
    
    if (size) {
      if (size.indexOf("80X80") > -1) {
        return "../theme/default/image/default/product_default_80.png";
      } else if (size.indexOf("180X180") > -1) {
        return "../theme/default/image/default/product_default_180.png";
      } else if (size.indexOf("300X300") > -1) {
        return "../theme/default/image/default/product_default_300.png";
      } else {
        return "../theme/default/image/default/product_default_180.png";
      }
    } else {
      return "../theme/default/image/default/product_default_300.png";
    }
  };
  
  Export.photoImgUrl = function(url, size, defautImg) {
    if (url) {
      if (!utils.endWith(url, "/")) {
        url = url + "/";
      }
      if (!size) {
        size = "";
      } else if (utils.startWith(size, "/")) {
        size = size.substring(1, size.length);
      }
      
      return (utils.getPlatformImgUri(url) + url + size);
    } else {
      if (defautImg) {
        return "../theme/default/image/default/" + defautImg;
      }
    }
    
    if (size) {
      if (size.indexOf("180X180") > -1) {
        return "../theme/default/image/default/upload_180.png";
      } else if (size.indexOf("300X300") > -1) {
        return "../theme/default/image/default/upload_300.png";
      } else {
        return "../theme/default/image/default/upload_180.png";
      }
    } else {
      return "../theme/default/image/default/upload_300.png";
    }
  };

  /**
   * 非只读显示不要千位符
   * @param c
   * @returns {Number}
   */
  Export.fromCurrencyNoPadding = function(c) {
    return Number(c) / 10000;
  };

  /**
   * 只读显示用千位符函数
   * @param c
   * @returns
   */
  Export.fromCurrency = function(c) {
    var result = Number(c) / 10000;
    return $.formatCurrency.format(result);
  };

  /**
   * 中间计算结果不用再除以10000了
   * 
   * @param c
   * @returns
   */
  Export.formatCurrency = function(c) {
    return $.formatCurrency.format(c);
  };
  
  Export.fromCurrencyWithCutZero = function(c) {
    var result = Number(c) / 10000;
    return cutZero($.formatCurrency.format(result));
  };
  
  function cutZero(old){  
    //拷贝一份 返回去掉零的新串  
    newstr=old;  
    //循环变量 小数部分长度  
    var leng = old.length-old.indexOf(".")-1  
    //判断是否有效数  
    if(old.indexOf(".")>-1){  
        //循环小数部分  
        for(i=leng;i>0;i--){  
                //如果newstr末尾有0  
                if(newstr.lastIndexOf("0")>-1 && newstr.substr(newstr.length-1,1)==0){  
                    var k = newstr.lastIndexOf("0");  
                    //如果小数点后只有一个0 去掉小数点  
                    if(newstr.charAt(k-1)=="."){  
                        return  newstr.substring(0,k-1);  
                    }else{  
                    //否则 去掉一个0  
                        newstr=newstr.substring(0,k);  
                    }  
                }else{  
                //如果末尾没有0  
                    return newstr;  
                }  
            }  
        }  
        return old;  
  };
  
  /**
   * 非钱币使用，不要小数点，例如件数，个数
   */
  Export.formatNumber = function(c) {
    return $.formatCurrency.format(c, {roundToDecimalPlace : 0});
  };
  
  /**
   * 保存到服务器，去掉千位符合小数位
   * @param c
   * @returns {Number}
   */
  Export.toCurrency = function(c) {
    c = String(c).replace(/\,/g, "");
    var count = this.getPrecision(c);
    c = c.replace(".", "");
    count = (4-count);
    if (count < 0) {
      count = 0;
    }
    for (var i=0; i<count; i++) {
      c += "0";
    }
    
    return parseInt(c);
  };
  
  Export.getPrecision = function(d) {
    d = d + "";
    var start = d.indexOf(".");
    if (start == -1) {
      return 0;
    }
    
    return d.length - start - 1;
  };
  
  function subStr(str, start, length) {
	  if (!str) {
		  return "";
	  }
	  
	  if ((start + 1) > str.length) {
		  return "";
	  }
	  
	  if (!length || ((start + length) > str.length)) {
		  return str.substr(start);
	  } else {
		  return str.substr(start, length);
	  }
  }
  
  //手机账号*格式化
  Export.cellPhoneFormat = function(phone) {
	  if(phone != null && phone != "")
		  return subStr(phone, 0, 3) + "****" + subStr(phone, 7);
	  else
		  return ;
  };
  //银行账号*格式化
  Export.bankNoFormat = function(bankNo){
	  if(bankNo != null && bankNo != "")
		  return subStr(bankNo, 0, 5) + "********" + subStr(bankNo, 13);
	  else
		  return ;
  };
  
	Export.midHtmlTag = function(str, len) {
		str = $.trim(str);
		if (!str) return "";
		len = len || 100;
		str = $.trim(str.replace(/<[^>]+>/g, "").replace(/&[^;]{2,6};/g, ""));// 去掉所有的html标记
		return this.subStr(str, 0, len);
	};
	
	Export.subStr = function(str, start, length) {
		if (!str) {
			return "";
		}

		if ((start + 1) > str.length) {
			return "";
		}

		if (!length || ((start + length) > str.length)) {
			return str.substr(start);
		} else {
			return str.substr(start, length) + "···";
		}
	};
  
  return Export;
});
