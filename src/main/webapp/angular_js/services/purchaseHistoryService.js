'use strict';

app.factory('purchaseHistoryService',
    function ($http, baseServiceUrl, authService) {
        return {
            getAll: function (success, error) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    url: baseServiceUrl + '/payment/all',
                    headers: headers
                };
                return $http(request).then(success, error);
            },

            // TODO : by id
            getFile: function (id, success, error) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    responseType: 'arraybuffer',
                    url: baseServiceUrl + '/policy/retrieve-pdf/' + id,
                    headers: headers,
                };
                return $http(request).then(success, error);
            }
        }
    }
);
