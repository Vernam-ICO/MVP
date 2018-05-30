'use strict';

app.controller('AppController', function ($scope, $rootScope, $location, $anchorScroll, authService, userService) {
        $scope.$on('$routeChangeSuccess', function (event, current) {
            $anchorScroll();
        });
        $scope.authService = authService;

        if (authService.isLoggedIn()) {
            userService.getUser().then(function (response) {
                $scope.user = response.data;
                $rootScope.user = response.data;
            }, function (error) {
                delete localStorage['currentUser'];
            });
        }

        $scope.logout = function () {
            console.log("logging out");
            authService.logout();
            /!*notifyService.showInfo('Logout successful');*!/
            $location.url('#/login');
        };

        $scope.loadScript = function (url, type, charset) {
            if (type === undefined) type = 'text/javascript';
            if (url) {
                let script = document.querySelector("script[src*='" + url + "']");
                let heads = document.getElementsByTagName("head");
                if (heads && heads.length) {
                    let head = heads[0];
                    if (head) {
                        script = document.createElement('script');
                        script.setAttribute('src', url);
                        script.setAttribute('type', type);
                        if (charset) script.setAttribute('charset', charset);
                        head.appendChild(script);
                    }
                }

                return script;
            }
        };
    }
);


