'use strict';

app.factory('authService',
    function ($http, $route, $location, $window, baseServiceUrl) {
        return {
            login: function (userData) {
                let request = {
                    method: 'POST',
                    url: baseServiceUrl + '/user/login',
                    data: userData
                };
                $http(request).then(
                    function success(response) {
                        localStorage['currentUser'] = JSON.stringify(response.data);
                        $window.location.href = '#!/';
                        $route.reload();
                        $window.scrollTo(0, 0);
                    },
                    function error(error) {
                        console.log(error);
                    }
                );
            },

            register: function (userData) {
                let request = {
                    method: 'POST',
                    url: baseServiceUrl + '/user/register',
                    data: userData
                };
                $http(request).then(
                    function (response) {
                        localStorage['currentUser'] = JSON.stringify(response.data);
                        $window.location.href = '#!/'
                    },
                    function (error) {
                        console.log(error)
                    }
                );
            },

            logout: function () {
                delete localStorage['currentUser'];
            },

            getCurrentUser: function () {
                let userObject = localStorage['currentUser'];
                if (userObject) {
                    return JSON.parse(localStorage['currentUser']);
                }
            },

            isAnonymous: function () {
                return localStorage['currentUser'] == undefined;
            },

            isLoggedIn: function () {
                return localStorage['currentUser'] != undefined;
            },

            isNormalUser: function () {
                let currentUser = this.getCurrentUser();
                return (currentUser != undefined) && (!currentUser.isAdmin);
            },

            isAdmin: function () {
                let currentUser = this.getCurrentUser();
                return (currentUser != undefined) && (currentUser.admin);
            },

            getAuthHeaders: function () {
                let headers = {};
                let currentUser = this.getCurrentUser();
                if (currentUser) {
                    headers['Authorization'] = 'Bearer ' + currentUser.tokenId;
                }
                return headers;
            }
        }
    }
);

