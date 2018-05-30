'use strict';

app.factory('paymentService',
    function ($http, baseServiceUrl, authService) {
        return {
            create: function (data, success, error) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'POST',
                    url: baseServiceUrl + '/payment/create',
                    headers: headers,
                    data: data
                };
                return $http(request).then(success, error);
            }
        }
    }
);
