'use strict';

app.factory('userWalletService',
    function ($http, baseServiceUrl, authService) {
        return {
            getLastPayment: function (success, error) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    url: baseServiceUrl + '/user-wallet/last-payment',
                    headers: headers
                };
                return $http(request).then(success, error);
            },
            tokensTransfer: function (amount) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    url: baseServiceUrl + '/user/tokens-transfer/amount/' + amount,
                    headers: headers
                };
                return $http(request);
            }
        }
    }
);
