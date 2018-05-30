'use strict';

app.factory('userProfileService',
    function ($http, $route, baseServiceUrl, authService) {
        return {
            edit: function (userData) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'POST',
                    url: baseServiceUrl + '/user/edit',
                    headers: headers,
                    data: userData
                };
                $http(request).then(
                    function (response) {
                        $route.reload();
                    },
                    function (error) {
                        console.log(error);
                    }
                );
            }
        }
    }
);
