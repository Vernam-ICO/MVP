'use strict';

let app = angular.module('app',
    [
        'ngRoute',
        'ngResource',
        'ui.bootstrap',
        'xeditable',
        'ui.bootstrap.modal',
        'directives.common',
        'ngMaterial',
        'ngMessages',
        'ngAnimate',
        'md-steppers',
        'material.svgAssetsCache'
    ]);

app.constant('baseServiceUrl', 'http://92.247.94.82:7771/mvp');
//app.constant('baseServiceUrl', 'http://localhost:8080/api');
app.constant('pageSize', 10);

app.directive('script', function () {
    return {
        restrict: 'E',
        scope: false,
        link: function (scope, elem, attr) {
            if (attr.type == 'text/javascript-lazy') {
                let code = elem.text();
                let f = new Function(code);
                f();
            }
        }
    };
});

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            let model = $parse(attrs.fileModel);
            let modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.config(function ($routeProvider) {

    $routeProvider.when('/', {
        templateUrl: 'templates/home.html',
        controller: 'HomeController'
    });

    $routeProvider.when('/login', {
        templateUrl: 'templates/login.html',
        controller: 'LoginController'
    });

    $routeProvider.when('/register', {
        templateUrl: 'templates/register.html',
        controller: 'RegisterController'
    });

    $routeProvider.when('/contact', {
        templateUrl: 'templates/contact.html',
        controller: 'ContactController'
    });

    $routeProvider.when('/under-construction', {
        templateUrl: 'templates/partials/under_construction.html',
        controller: 'ConstructionController'
    });

    $routeProvider.when('/travel-insurance', {
        templateUrl: 'templates/calculator.html',
        controller: 'CalculatorController'
    });

    $routeProvider.when('/purchase-history', {
        templateUrl: 'templates/users/purchase-history.html',
        controller: 'PurchaseHistoryController'
    });

    $routeProvider.when('/privacy-policy', {
        templateUrl: 'templates/privacy-policy.html',
        controller: 'PrivacyPolicyController'
    });

    $routeProvider.when('/user-wallet', {
        templateUrl: 'templates/users/user-wallet.html',
        controller: 'UserWalletController'
    });

    $routeProvider.when('/user-profile', {
        templateUrl: 'templates/users/user-profile.html',
        controller: 'UserProfileController'
    });

    $routeProvider.when('/admin/users/list', {
        templateUrl: 'templates/users/list-users.html',
        controller: 'AdminController'
    });

    $routeProvider.when('/thank-you', {
        templateUrl: 'templates/thankYou.html',
        controller: 'ThankYouController'
    });

    $routeProvider.when('/travel-insurance', {
        templateUrl: 'templates/calculator.html',
        controller: 'CalculatorController'
    });

    $routeProvider.otherwise(
        {redirectTo: '/'}
    );

});

function loadjscssfile(filename, filetype) {
    if (filetype == "js") {
        // if filename is a external JavaScript file
        let fileref = document.createElement('script');
        fileref.setAttribute("type", "text/javascript");
        fileref.setAttribute("src", filename);
    }
    else if (filetype == "css") {
        //if filename is an external CSS file
        let fileref = document.createElement("link");
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", filename)
    }
    if (typeof fileref != "undefined") {
        document.getElementsByTagName("head")[0].appendChild(fileref)
    }
}

app.run(function ($rootScope, $location, authService) {
    $rootScope.$on('$locationChangeStart', function (event) {
        if ($location.path().indexOf("/admin/") != -1 && !authService.isLoggedIn()) {
            // Authorization check: anonymous site visitors cannot access user routes
            $location.path("/");
        }
    });
});

app.run(['editableOptions', function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
}]);

app.config(function($mdIconProvider) {
    $mdIconProvider.iconSet("avatars", 'icons/avatar-icons.svg',128);
});