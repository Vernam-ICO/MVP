'use strict';

app.factory('userService',
    function ($http, baseServiceUrl, authService) {
        return {
            getUser: function (success, error) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    url: baseServiceUrl + '/user/current',
                    headers: headers
                };
                return $http(request).then(success, error);
            },
            getAllUsers: function (params) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    url: baseServiceUrl + '/admin/users/list?page=' + params.page + '&size=' + params.size,
                    headers: headers
                };
                return $http(request);
            },
            deleteUser: function (id) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'GET',
                    url: baseServiceUrl + '/admin/users/delete/' + id,
                    headers: headers
                };
                return $http(request);
            },
            editUser: function (userData) {
                let headers = authService.getAuthHeaders();
                let request = {
                    method: 'POST',
                    url: baseServiceUrl + '/admin/users/edit',
                    headers: headers,
                    data: userData
                };
                return $http(request);
            }
        }
    }
);
