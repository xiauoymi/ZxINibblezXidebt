'use strict';
app.directive('loading', function ($http)
    {
        return {
            link: function (scope, elm, attrs)
            {
                scope.isLoading = function () {
                	if ($http.pendingRequests != null && $http.pendingRequests.length > 0) {
                        for (var i=0; i < $http.pendingRequests.length; i++) {
                            var url = $http.pendingRequests[i].url;
                            console.log(url);
                            if (url.match(NibbleUtils.getServicesUrl()) && !NibbleUtils.checkExceptedURLs(url)) {
                                return true;
                            }
                        }
                    }else{
                    	if(angular.element('iframe').length>0){
                        	if(angular.element('iframe').contents().find("div").length>0){
                        		return false;
                        	}else{
                        		return true;
                        	}
                        } 
                    	return false;
                    }
                };

                scope.$watch(scope.isLoading, function (v)
                {
                    if(v){
                        elm.modal();
                    }else{
                        elm.modal('hide');
                    }
                });

            }
        };

    });