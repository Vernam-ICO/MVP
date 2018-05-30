'user strict';

app.controller('ConstructionController',
    function ($scope) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
    }
);